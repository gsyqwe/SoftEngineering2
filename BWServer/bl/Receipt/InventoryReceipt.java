package Receipt;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import PO.InventoryReceiptPO;
import PO.LineItemPO;
import PO.ReceiptPO;
import PO.UserPO;
import VO.CommodityVO;
import VO.EmailForAlarmVO;
import VO.EmailForAuditVO;
import VO.InventoryReceiptVO;
import VO.ReceiptVO;
import VO.StockInAndOutVO;
import commoditybl.CommodityController;
import emailbl.EmailForServer;
import enums.InventoryReceiptType;
import enums.JobType;
import enums.ReceiptState;
import enums.ResultMessage;
import enums.StockInAndOut;
import inventoryreceiptdata.InventoryReceiptDataUseDataBase;
import inventoryreceiptdataservice.InventoryReceiptDataService;
import userdata.UserDataUseDataBase;
import userdataService.UserDataService;

public class InventoryReceipt extends Receipt {
	InventoryReceiptPO receipt;
	CommodityController commodityService;
	InventoryReceiptDataService dataService = new InventoryReceiptDataUseDataBase();
	UserDataService userService = new UserDataUseDataBase();

	public InventoryReceipt(InventoryReceiptPO receipt) {
		this.receipt = receipt;
		dataService = new InventoryReceiptDataUseDataBase();
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
		// TODO Auto-generated method stub
		if (receipt.getInventoryType() == InventoryReceiptType.ALARM) {
			// 给进货销售人员发送消息通知进货
			ArrayList<UserPO> userList = userService.findByUserType(JobType.SALESMAN);//在userdataService里面添加一个方法，通过用户种类筛选用户
			ArrayList<String> userName = new ArrayList<>();

			for(UserPO user:userList){
				userName.add(user.getId());
			}

			ArrayList<EmailForAlarmVO> emails = EmailForAlarmVO.generateEmail(receipt.getOperatorID(), userName, new Date());
			for(LineItemPO item:receipt.getLineItem()){
				CommodityVO com = commodityService.findByID(item.getId());
				emails = EmailForAlarmVO.AddEmailItem(com, emails,1);//传入了一个多余的int参数
			}

			EmailForServer controller = new EmailForServer();

			for(EmailForAlarmVO e:emails){
				ResultMessage mess = controller.saveEmail(e);
				if(mess != ResultMessage.SUCCESS){
					return mess;
				}
			}

			return ResultMessage.SUCCESS;
		}

		StockInAndOut stockType = null;
		if (receipt.getInventoryType() == InventoryReceiptType.OVERFLOW) {
			stockType = StockInAndOut.STOCK_IN;
		} else if (receipt.getInventoryType() == InventoryReceiptType.BREAKAGE
				|| receipt.getInventoryType() == InventoryReceiptType.GIFT) {
			stockType = StockInAndOut.STOCK_OUT;
		}

		int index = -1;
		ArrayList<LineItemPO> itemList = receipt.getLineItemAsList();

		ArrayList<UserPO> userList = userService.findByUserType(JobType.INVENTORY);//在userdataService里面添加一个方法，通过用户种类筛选用户
		ArrayList<String> userName = new ArrayList<>();

		for(UserPO user:userList){
			userName.add(user.getId());
		}

		ArrayList<EmailForAlarmVO> emails = EmailForAlarmVO.generateEmail(managerID, userName, new Date());

		for (int i = 0; i < itemList.size(); i++) {
			StockInAndOutVO stock = new StockInAndOutVO(stockType, itemList.get(i).getQuantity(),
					itemList.get(i).getId(), itemList.get(i).getPrice() * itemList.get(i).getQuantity());
			ResultMessage stockMessage = commodityService.stockInAndOut(stock,emails);
			if (stockMessage == ResultMessage.COMMODITY_STOCKIN_FAIL) {
				index = i;
				break;
			}
		}

		if (index != -1) {// 出现了库存为负的情况，将之前的出入库抵消
			if (receipt.getInventoryType() == InventoryReceiptType.OVERFLOW) {
				stockType = StockInAndOut.STOCK_OUT;
			} else if (receipt.getInventoryType() == InventoryReceiptType.BREAKAGE
					|| receipt.getInventoryType() == InventoryReceiptType.GIFT) {
				stockType = StockInAndOut.STOCK_IN;
			}
			for (int i = 0; i < index; i++) {
				StockInAndOutVO stock = new StockInAndOutVO(stockType, itemList.get(i).getQuantity(),
						itemList.get(i).getId(), itemList.get(i).getPrice() * itemList.get(i).getQuantity());
				commodityService.stockInAndOut(stock,emails);// 这个时候不用判断返回值
			}

			return ResultMessage.COMMODITY_STOCKIN_FAIL;
		}

		receipt.setState(ReceiptState.VERIFIED);
		ResultMessage updateMessage = dataService.update(receipt.getId(), receipt);

		if (updateMessage == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND) {
			return ResultMessage.RECEIPT_UPDATE_FAULT;
		}

		EmailForServer controller = new EmailForServer();
		EmailForAuditVO email = EmailForAuditVO.generateEmail(managerID, receipt.getOperatorID(), new Date(),
				receipt.getId(), true);
		ResultMessage emailMessage = controller.saveEmail(email);// 保存email的data操作的message

		if(emailMessage != ResultMessage.SUCCESS){
			return emailMessage;
		}

		for(EmailForAlarmVO e:emails){
			ResultMessage mess = controller.saveEmail(e);
			if(mess != ResultMessage.SUCCESS){
				return mess;
			}
		}

		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage failPassing(String managerID) throws RemoteException {
		// TODO Auto-generated method stub
		receipt.setState(ReceiptState.UNAPPROVEAL);
		ResultMessage updateMessage = dataService.update(receipt.getId(), receipt);

		if (updateMessage == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND) {
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
	public ResultMessage setReceiptPO(ReceiptVO newReceipt, String managerID) throws RemoteException {

		if (((InventoryReceiptVO) newReceipt).getInventoryType() == InventoryReceiptType.ALARM) {
			return ResultMessage.MODIFY_ALARM_FAULT;
		}

		InventoryReceiptPO theReceipt = ((InventoryReceiptVO) newReceipt).toPO();
		this.receipt = theReceipt;

		ResultMessage checkMessage = this.passAudit(managerID);
		return checkMessage;

	}

	@Override
	public ReceiptPO getReceiptPO() throws RemoteException {
		// TODO Auto-generated method stub
		return receipt;
	}

}
