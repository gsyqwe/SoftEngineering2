package Receipt;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import PO.LineItemPO;
import PO.MemberPO;
import PO.ReceiptPO;
import PO.SalesReceiptPO;
import PO.UserPO;
import PO.VoucherPO;
import VO.EmailForAlarmVO;
import VO.EmailForAuditVO;
import VO.LineItemVO;
import VO.ReceiptVO;
import VO.SalesReceiptVO;
import VO.StockInAndOutVO;
import commoditybl.CommodityController;
import emailbl.EmailForServer;
import enums.JobType;
import enums.ReceiptState;
import enums.ResultMessage;
import enums.SalesReceiptType;
import enums.StockInAndOut;
import memberdata.MemberDataUseDataBase;
import memberdataservice.MemberDataService;
import salesdata.SalesDataUseDataBase;
import salesdataService.SalesDataService;
import userdata.UserDataUseDataBase;
import userdataService.UserDataService;

/**
 *
 * @author JiYuzhe
 *         关于在通过审核再触发促销策略的实现方法，因为现在在每个SalesReceiptPO里面都有一个字段表示所得到的赠品和获得的代金券，所以无需SalesController
 *         但是总经理要修改单据的内容，所以要得到当前能销售的商品列表，总经理只能添加，减少列表里面的项，可以修改列表里面的值，但是不能修改表头和其他属性
 */

public class SalesReceipt extends Receipt {
	SalesReceiptPO receipt;
	MemberDataService memberDataService = new MemberDataUseDataBase();
	SalesDataService receiptDataService = new SalesDataUseDataBase();
	CommodityController commodityService;// 用其中的StockInAndOut方法进行出入库
	UserDataService userService = new UserDataUseDataBase();

	public SalesReceipt(SalesReceiptPO receipt) {
		this.receipt = receipt;
		memberDataService = new MemberDataUseDataBase();
		receiptDataService = new SalesDataUseDataBase();
		userService = new UserDataUseDataBase();
		try {
			commodityService = new CommodityController();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ReceiptVO prepareToShow() throws RemoteException {
		// TODO Auto-generated method stub
		return receipt.toVO();
	}

	@Override
	public ResultMessage passAudit(String managerID) throws RemoteException {
		// TODO Auto-generated method
		// stub,通过审核后会修改客户的应收数据，系统的库存情况和触发促销策略的效果，促销策略的赠品是单独审核的，不在这里触发
		double sum = receipt.getSumAfterDiscount();
		MemberPO member = memberDataService.findByID(receipt.getMemberID());
		ResultMessage memberMessage = null;

		if (receipt.getSalesType() == SalesReceiptType.PURCHASE
				|| receipt.getSalesType() == SalesReceiptType.SALES_RETURN) {// 客户的
			if (receipt.getSalesType() == SalesReceiptType.PURCHASE) {
				member.setReceivable(member.getReceivable() + sum);// 客户的应收增加
			} else {
				member.setPayment(member.getPayment() - sum);
			}
		} else {
			if (receipt.getSalesType() == SalesReceiptType.SALES) {
				member.setPayment(member.getPayment() + sum);
				ArrayList<VoucherPO> voucherList = member.getVouchersAsList();
				voucherList.addAll(receipt.getVoucherReceivedAsList());
				member.setVouchers(voucherList);// 将得到的代金券发放给用户
			} else {
				member.setReceivable(member.getReceivable() - sum);
			}
		}

		// 在此修改系统的库存状况
		ArrayList<UserPO> userList = userService.findByUserType(JobType.INVENTORY);// 在userdataService里面添加一个方法，通过用户种类筛选用户
		ArrayList<String> userName = new ArrayList<>();

		for (UserPO user : userList) {
			userName.add(user.getId());
		}

		ArrayList<EmailForAlarmVO> emails = EmailForAlarmVO.generateEmail(managerID, userName, new Date());

		StockInAndOut stockType;
		if (receipt.getSalesType() == SalesReceiptType.PURCHASE
				|| receipt.getSalesType() == SalesReceiptType.SALES_RETURN) {
			stockType = StockInAndOut.STOCK_IN;
		} else {
			stockType = StockInAndOut.STOCK_OUT;
		}

		int index = -1;
		ArrayList<LineItemPO> itemList = receipt.getLineItemAsList();
		for (int i = 0; i < itemList.size(); i++) {
			StockInAndOutVO stock = new StockInAndOutVO(stockType, itemList.get(i).getQuantity(),
					itemList.get(i).getId(), itemList.get(i).getPrice() * itemList.get(i).getQuantity());
			ResultMessage stockMessage = commodityService.stockInAndOut(stock, emails);
			if (stockMessage == ResultMessage.COMMODITY_STOCKIN_FAIL) {
				index = i;
				break;
			}
		}

		if (index != -1) {// 出现了库存为负的情况，将之前的出入库抵消
			if (stockType == StockInAndOut.STOCK_IN) {
				stockType = StockInAndOut.STOCK_OUT;
			} else {
				stockType = StockInAndOut.STOCK_IN;
			}
			for (int i = 0; i < index; i++) {
				StockInAndOutVO stock = new StockInAndOutVO(stockType, itemList.get(i).getQuantity(),
						itemList.get(i).getId(), itemList.get(i).getPrice() * itemList.get(i).getQuantity());
				commodityService.stockInAndOut(stock, emails);// 这个时候不用判断返回值
			}

			return ResultMessage.COMMODITY_STOCKIN_FAIL;
		}

		memberMessage = memberDataService.update(member.getId(), member);

		if (memberMessage == null || memberMessage == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND) {
			return ResultMessage.MEMBER_UPDATE_FAULT;// 修改客户出现问题,一般不会出现这个问题
		}

		receipt.setState(ReceiptState.VERIFIED);
		System.out.println(receipt.getId());
		ResultMessage updateMessage = receiptDataService.update(receipt.getId(), receipt);// 别忘了最后将receipt的状态进行更新后在dataService更新此单据

		if (updateMessage == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND) {
			return ResultMessage.RECEIPT_UPDATE_FAULT;// 正常情况下不会出现这个问题
		}
		// 还有向操作员发送消息的逻辑

		EmailForServer controller = new EmailForServer();
		EmailForAuditVO email = EmailForAuditVO.generateEmail(managerID, receipt.getOperatorID(), new Date(),
				receipt.getId(), true);
		ResultMessage emailMessage = controller.saveEmail(email);// 保存email的data操作的message

		if (emailMessage != ResultMessage.SUCCESS) {
			return emailMessage;
		}

		for (EmailForAlarmVO e : emails) {
			ResultMessage mess = controller.saveEmail(e);
			if (mess != ResultMessage.SUCCESS) {
				return mess;
			}
		}

		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage failPassing(String managerID) throws RemoteException {
		// TODO Auto-generated method stub
		receipt.setState(ReceiptState.UNAPPROVEAL);

		ResultMessage message = receiptDataService.update(receipt.getId(), receipt);
		if (message == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND) {
			return ResultMessage.RECEIPT_UPDATE_FAULT;
		}

		// 发送邮件
		EmailForServer controller = new EmailForServer();
		EmailForAuditVO email = EmailForAuditVO.generateEmail(managerID, receipt.getOperatorID(), new Date(),
				receipt.getId(), false);
		ResultMessage emailMessage = controller.saveEmail(email);

		if(emailMessage != ResultMessage.SUCCESS){
			return emailMessage;
		}

		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage setReceiptPO(ReceiptVO newReceipt, String managerID) throws RemoteException {// 在总经理点击确定的时候触发。
		// TODO Auto-generated method stub 先省略参数正确性判断，总经理修改的时候不再进行促销策略的判断了
		// 因为不知道总经理修改了那些属性，所以总价要重新计算,计算折让前的总价和折让后的总价
		double amount = 0;
		for (LineItemVO item : newReceipt.getLineItem()) {
			amount = amount + item.getPrice() * item.getQuantity();
		}

		((SalesReceiptVO) newReceipt).setSumBeforeDiscount(amount);
		((SalesReceiptVO) newReceipt).setSumAfterDiscount(amount - ((SalesReceiptVO) newReceipt).getDiscount()
				- ((SalesReceiptVO) newReceipt).getVoucher().getFaceValue());

		this.receipt = ((SalesReceiptVO) newReceipt).toPO();
		ResultMessage message2 = this.passAudit(managerID);

		return message2;
	}

	@Override
	public ReceiptPO getReceiptPO() throws RemoteException {
		// TODO Auto-generated method stub
		return receipt;
	}

}
