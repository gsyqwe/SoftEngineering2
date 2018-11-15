package businessprocessbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import PO.BankAccountPO;
import PO.CommodityPO;
import PO.FinancialReceiptPO;
import PO.InventoryReceiptPO;
import PO.LineItemPO;
import PO.ReceiptPO;
import PO.SalesReceiptPO;
import Receipt.Receipt;
import Receipt.ReceiptFactory;
import VO.BankAccountVO;
import VO.CommodityVO;
import VO.FinancialReceiptVO;
import VO.InventoryReceiptVO;
import VO.LineItemVO;
import VO.ReceiptVO;
import VO.SalesReceiptVO;
import bankaccountdata.BankAccountDataUseDataBase;
import bankaccountdataService.BankAccountDataService;
import commoditydata.CommodityDataUseDataBase;
import commoditydataService.CommodityDataService;
import enums.InventoryReceiptType;
import enums.ReceiptState;
import enums.ResultMessage;
import financialreceiptdata.FinancialReceiptDataUseDataBase;
import financialreceiptdataservice.FinancialReceiptDataService;
import inventoryreceiptdata.InventoryReceiptDataUseDataBase;
import inventoryreceiptdataservice.InventoryReceiptDataService;
import receiptFinder.FinancialFinder;
import receiptFinder.Finder;
import receiptFinder.InventoryFinder;
import receiptFinder.ReceiptDataServiceInfo;
import receiptFinder.SalesFinder;
import salesdata.SalesDataUseDataBase;
import salesdataService.SalesDataService;
import service.BusinessProcessBLService;

/*
* Module Comments:  		BusinessProcess������H
* Author:					161250051/Lai Kin Meng
* Create Date�G  				2017-10-25
* Modified By�G  	 			161250051/Lai Kin Meng
* Modified Date:
* Why & What is modified�G	更新修改了一个bug，销售单红冲的时候要将总额取负，因为要修改客户的应收应付
*/
public class BusinessProcessController extends UnicastRemoteObject implements BusinessProcessBLService {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public BusinessProcessController() throws RemoteException {
		super();
		financialDataService = new FinancialReceiptDataUseDataBase();
		salesDataService = new SalesDataUseDataBase();
		inventoryDataService = new InventoryReceiptDataUseDataBase();
		commodityDataService = new CommodityDataUseDataBase();
		accountDataService = new BankAccountDataUseDataBase();

		// TODO Auto-generated constructor stub
	}

	InventoryFinder inventoryFinder;
	SalesFinder salesFinder;
	FinancialFinder financialFinder;
	FinancialReceiptDataService financialDataService = new FinancialReceiptDataUseDataBase();
	SalesDataService salesDataService = new SalesDataUseDataBase();
	InventoryReceiptDataService inventoryDataService = new InventoryReceiptDataUseDataBase();
	CommodityDataService commodityDataService = new CommodityDataUseDataBase();
	BankAccountDataService accountDataService = new BankAccountDataUseDataBase();

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ReceiptVO> finByAllMeans(ArrayList<Finder> finder, ArrayList<String> keywords)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<ReceiptVO> result_list = finder.get(0).find(keywords.get(0));// 先按照第一个条件找到一个初始列表

		for (int i = 1; i < finder.size(); i++) {// 对每重筛选进行排查
			ArrayList<ReceiptVO> tem_list = finder.get(i).find(keywords.get(i));
			result_list = (ArrayList<ReceiptVO>) fliter_effective(result_list, tem_list);
		}

		Collections.sort(result_list);

		return result_list;
	}

	// 在调用这个方法时，一定要将三个ReceiptDataServiceInfo全部传入来进行查找
	@Override
	public ArrayList<ReceiptVO> getAllList(ArrayList<ReceiptDataServiceInfo> dataServiceInfos) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<ReceiptVO> result_list = new ArrayList<>();
		for (ReceiptDataServiceInfo info : dataServiceInfos) {
			@SuppressWarnings("unchecked")
			ArrayList<ReceiptPO> temp = (ArrayList<ReceiptPO>) info.findByReceiptState(ReceiptState.VERIFIED);
			result_list.addAll(change_list_to_vo(temp));
		}

		Collections.sort(result_list);
		return result_list;
	}

	// 工具方法，供getAllList使用
	private ArrayList<ReceiptVO> change_list_to_vo(ArrayList<ReceiptPO> find_list) {
		ArrayList<ReceiptVO> result = new ArrayList<>();
		for (ReceiptPO receipt : find_list) {
			result.add(receipt.toVO());
		}

		return result;
	}

	@Override
	public ResultMessage redCopyFinancial(FinancialReceiptVO financialReceiptFromUI, String financialID)
			throws RemoteException {
		// TODO Auto-generated method stub
		FinancialReceiptPO financialReceipt = financialReceiptFromUI.toPO();
		financialReceipt.setId(financialReceipt.getId() + "-RC");
		ArrayList<LineItemPO> lineItems = financialReceipt.getLineItemAsList();
		//System.out.println(financialReceipt.getLineItemAsList().size());
		for (LineItemPO item : lineItems) {
			item.setPrice(item.getPrice() * (-1));// 将每一行内容取负,销售类单据没有数量，只有金额
		}
		financialReceipt.setLineItem(lineItems);
		financialReceipt.setSum(-financialReceipt.getSum());// 将总价也取负
		//System.out.println(financialReceipt.getLineItem().size());

		ResultMessage insertMessage = financialDataService.insert(financialReceipt);
		if (insertMessage == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED) {
			return ResultMessage.RED_COPY_TWICE;// 试图将单据红冲两次
		}

		Receipt theCheckReceipt = ReceiptFactory.creatReceipt(financialReceipt);
		ResultMessage checkMessage = theCheckReceipt.passAudit(financialID);// 直接对单据调用check方法，修改数据

		return checkMessage;
	}

	// 报警单不能红冲，如果红冲的是报警单，返回红冲报警单错误
	@Override
	public ResultMessage redCopyInventory(InventoryReceiptVO inventoryReceiptFromUI, String financialID)
			throws RemoteException {
		// TODO Auto-generated method stub
		InventoryReceiptPO inventoryReceipt = inventoryReceiptFromUI.toPO();
		if (inventoryReceipt.getInventoryType() == InventoryReceiptType.ALARM) {
			return ResultMessage.RED_COPY_ALARM_FAULT;// 红冲报警单错误
		}
		inventoryReceipt.setId(inventoryReceipt.getId() + "-RC");
		ArrayList<LineItemPO> lineItems = inventoryReceipt.getLineItemAsList();
		for (LineItemPO item : lineItems) {
			item.setQuantity(-item.getQuantity());// 将数量取负即可
		}
		inventoryReceipt.setLineItem(lineItems);

		ResultMessage insertMessage = inventoryDataService.insert(inventoryReceipt);

		if (insertMessage == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED) {
			return ResultMessage.RED_COPY_TWICE;// 试图将单据红冲两次
		}

		Receipt theCheckReceipt = ReceiptFactory.creatReceipt(inventoryReceipt);
		ResultMessage checkMessage = theCheckReceipt.passAudit(financialID);

		return checkMessage;
	}

	// 在对销售类单据进行红冲，
	@Override
	public ResultMessage redCopySales(SalesReceiptVO salesReceiptFromUI, String financialID) throws RemoteException {
		// TODO Auto-generated method stub
		SalesReceiptPO salesReceipt = salesReceiptFromUI.toPO();
		salesReceipt.setId(salesReceipt.getId() + "-RC");
		ArrayList<LineItemPO> lineItems = salesReceipt.getLineItemAsList();

		for (LineItemPO item : lineItems) {
			item.setQuantity(-item.getQuantity());
		}

		salesReceipt.setLineItem(lineItems);

		// 库存赠送单和销售单是分开审核的，所以在这里不用考虑库存赠送单的问题，倒是应该将该单据所得到的代金券清空
		salesReceipt.setVoucherReceived(new ArrayList<>());// 将代金券清空
		salesReceipt.setSumBeforeDiscount(-salesReceipt.getSumBeforeDiscount());// 别忘了将最后金额也改成负值
		salesReceipt.setSumAfterDiscount(-salesReceipt.getSumAfterDiscount());

		ResultMessage insertMessage = salesDataService.insert(salesReceipt);

		if (insertMessage == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED) {
			return ResultMessage.RED_COPY_TWICE;// 试图将单据红冲两次
		}

		Receipt theCheckReceipt = ReceiptFactory.creatReceipt(salesReceipt);
		ResultMessage checkMessage = theCheckReceipt.passAudit(financialID);

		return checkMessage;
	}

	@Override // 改变totalSum,要重新计算totalSum，在调用完该方法后用同样的方法对FinancialReceiptVO进行更新然后刷新界面，显示新的totalSum
	public ResultMessage completeModifyFinancial(FinancialReceiptVO financialReceipt, String financialID)
			throws RemoteException {
		// TODO Auto-generated method stub 参数检查在界面层调用之前做
		double sum = 0;
		for (LineItemVO item : financialReceipt.getLineItem()) {
			sum = sum + item.getPrice();
		}

		financialReceipt.setSum(sum);

		FinancialReceiptPO receiptPO = financialReceipt.toPO();
		receiptPO.setId(receiptPO.getId() + "-RCM");// 红冲并复制的单据后面加入-RCM

		ResultMessage insertMessage = financialDataService.insert(receiptPO);

		if (insertMessage == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED) {
			return ResultMessage.RED_COPY_MODIFY_TWICE;// 试图将单据红冲并复制两次
		}

		Receipt theCheckReceipt = ReceiptFactory.creatReceipt(receiptPO);
		ResultMessage checkMessage = theCheckReceipt.passAudit(financialID);

		return checkMessage;
	}

	// 如果是报警单，不会到这一步，所以不需要判断
	@Override
	public ResultMessage completeModifyInventory(InventoryReceiptVO inventoryReceipt, String financialID)
			throws RemoteException {
		// TODO Auto-generated method stub
		InventoryReceiptPO receiptPO = inventoryReceipt.toPO();
		receiptPO.setId(receiptPO.getId() + "-RCM");

		ResultMessage insertMessage = inventoryDataService.insert(receiptPO);

		if (insertMessage == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED) {
			return ResultMessage.RED_COPY_MODIFY_TWICE;// 试图将单据红冲并复制两次
		}

		Receipt theCheckReceipt = ReceiptFactory.creatReceipt(receiptPO);
		ResultMessage checkMessage = theCheckReceipt.passAudit(financialID);

		return checkMessage;
	}

	// 再在此修改销售单的话，不会重新触发Promotion的判断，有关Promotion的字段不会修改
	@Override
	public ResultMessage completeModifySales(SalesReceiptVO salesReceipt, String financialID) throws RemoteException {
		// TODO Auto-generated method stub
		double sum = 0;
		for (LineItemVO item : salesReceipt.getLineItem()) {
			sum = sum + item.getPrice() * item.getQuantity();
		}

		salesReceipt.setSumBeforeDiscount(sum);
		salesReceipt.setSumAfterDiscount(salesReceipt.getSumBeforeDiscount() - salesReceipt.getDiscount()
				- salesReceipt.getVoucher().getFaceValue());// 设置折让后总价

		SalesReceiptPO receiptPO = salesReceipt.toPO();
		receiptPO.setId(receiptPO.getId() + "-RCM");// 红冲并复制的单据后面加入-RCM

		ResultMessage insertMessage = salesDataService.insert(receiptPO);

		if (insertMessage == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED) {
			return ResultMessage.RED_COPY_MODIFY_TWICE;// 试图将单据红冲并复制两次
		}

		Receipt theCheckReceipt = ReceiptFactory.creatReceipt(receiptPO);// 运用多态
		ResultMessage checkMessage = theCheckReceipt.passAudit(financialID);

		return checkMessage;
	}

	@Override
	public ArrayList<CommodityVO> getCommodities() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CommodityPO> commodityList = commodityDataService.getAllList();
		ArrayList<CommodityVO> resultCommodityList = new ArrayList<>();
		for (CommodityPO commodity : commodityList) {
			resultCommodityList.add(commodity.toVO());
		}

		return resultCommodityList;
	}

	@Override
	public ArrayList<BankAccountVO> getAllAccount() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<BankAccountPO> accountList = accountDataService.getAllList();
		ArrayList<BankAccountVO> resultAccountList = new ArrayList<>();
		for (BankAccountPO account : accountList) {
			resultAccountList.add(account.toVO());
		}

		return resultAccountList;
	}

	// 返回第二个列表元素同时也在第一个列表出现的单据
	private ArrayList<? extends ReceiptVO> fliter_effective(ArrayList<? extends ReceiptVO> effective_list,
			ArrayList<? extends ReceiptVO> find_list) {

		Iterator<? extends ReceiptVO> it = find_list.iterator();// 在需要删除元素的时候不要用迭代for循环，用迭代器安全

		while (it.hasNext()) {
			ReceiptVO find_item = it.next();
			boolean is_effective = false;
			for (ReceiptVO effective_item : effective_list) {
				if (find_item.getId().equals(effective_item.getId())) {
					is_effective = true;
					break;
				}
			}

			if (!is_effective) {
				it.remove();
			}
		}

		return find_list;
	}
}
