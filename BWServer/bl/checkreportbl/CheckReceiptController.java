package checkreportbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;

import PO.BankAccountPO;
import PO.CommodityPO;
import PO.FinancialReceiptPO;
import PO.InventoryReceiptPO;
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
import enums.ReceiptState;
import enums.ResultMessage;
import financialreceiptdata.FinancialReceiptDataUseDataBase;
import financialreceiptdataservice.FinancialReceiptDataService;
import inventoryreceiptdata.InventoryReceiptDataUseDataBase;
import inventoryreceiptdataservice.InventoryReceiptDataService;
import receiptFinder.FinancialFinder;
import receiptFinder.InventoryFinder;
import receiptFinder.ReceiptDataServiceInfo;
import receiptFinder.SalesFinder;
import salesdata.SalesDataUseDataBase;
import salesdataService.SalesDataService;
import service.CheckReceiptBLService;

/**
 *
 * @author JiYuzhe
 * 总经理在审核单据界面可以批量审核，可以查看单个单据并且修改单据数据
 *
 */

public class CheckReceiptController extends UnicastRemoteObject implements CheckReceiptBLService{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public CheckReceiptController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	InventoryReceiptDataService inventoryDataService = new InventoryReceiptDataUseDataBase();
	FinancialReceiptDataService financialDataService = new FinancialReceiptDataUseDataBase();
	SalesDataService salesDataService = new SalesDataUseDataBase();
	CommodityDataService commodityDataService = new CommodityDataUseDataBase();
	BankAccountDataService accountDataService = new BankAccountDataUseDataBase();

	@Override//因为最好要打印出来是哪张单据在通过审核的时候发生错误，所以使用ArrayList<String>返回给客户端
	public ArrayList<String> passByList(ArrayList<ReceiptVO> receiptList,String managerID) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<Receipt> checkList = new ArrayList<>();
		for(int i = 0;i < receiptList.size();i++){
			checkList.add(ReceiptFactory.creatReceipt(receiptList.get(i).toPO()));//将选中的每个单据包装成为Receipt逻辑对象
		}

		ArrayList<String> resultMessage = new ArrayList<>();
		for(Receipt receipt:checkList){
			ResultMessage checkMessage = receipt.passAudit(managerID);//对每个单据调用passAudit方法进行通过
			if(checkMessage != ResultMessage.SUCCESS){
				String faultTrack = receipt.getReceiptPO().getId() + " has occurred an promble while passing audit.";
				resultMessage.add(faultTrack);
			}
		}

		return resultMessage;
	}

	@Override
	public ArrayList<String> failPassByList(ArrayList<ReceiptVO> receiptList,String managerID) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<Receipt> checkList = new ArrayList<>();
		for(int i = 0;i < receiptList.size();i++){
			checkList.add(ReceiptFactory.creatReceipt(receiptList.get(i).toPO()));//将选中的每个单据包装成为Receipt逻辑对象
		}

		ArrayList<String> resultMessage = new ArrayList<>();
		for(Receipt receipt:checkList){
			ResultMessage checkMessage = receipt.failPassing(managerID);//对每个单据调用failPassing方法不通过
			if(checkMessage != ResultMessage.SUCCESS){
				String faultTrack = receipt.getReceiptPO().getId() + "has occurred an promble while passing audit.";
				resultMessage.add(faultTrack);
			}
		}

		return resultMessage;
	}

	@Override
	public ArrayList<ReceiptVO> showTheUncheckedReceiptList() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<ReceiptDataServiceInfo> dataServiceInfo = new ArrayList<>();
		dataServiceInfo.add(new InventoryFinder());
		dataServiceInfo.add(new FinancialFinder());
		dataServiceInfo.add(new SalesFinder());

		ArrayList<ReceiptPO> uncheckList = new ArrayList<>();
		for(ReceiptDataServiceInfo info:dataServiceInfo){
			ArrayList<? extends ReceiptPO> tem = info.findByReceiptState(ReceiptState.SUBMITTED);
			uncheckList.addAll(tem);
		}

		ArrayList<ReceiptVO> resultUncheckList = change_list_to_vo(uncheckList);

		Collections.sort(resultUncheckList);
		return resultUncheckList;
	}

	//在界面上有更新和确认修改两个按钮，更新按钮算出来当前的sum然后在界面上更新结果，确认修改先更新结果，然后调用这个方法通过审核，在数据库更新
	@Override
	public ResultMessage completeModifyFinancial(FinancialReceiptVO financialReceipt,String managerID) throws RemoteException {
		// TODO Auto-generated method stub
		double sum = 0;
		for(LineItemVO item:financialReceipt.getLineItem()){
			sum = sum + item.getPrice();
		}

		financialReceipt.setSum(sum);

		FinancialReceiptPO receiptPO = financialReceipt.toPO();

		ResultMessage updateMessage = financialDataService.update(receiptPO.getId(), receiptPO);

		if(updateMessage == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND){
			return ResultMessage.RECEIPT_UPDATE_FAULT;//一般不会出现该问题
		}

		Receipt theCheckReceipt = ReceiptFactory.creatReceipt(receiptPO);
		ResultMessage checkMessage = theCheckReceipt.passAudit(managerID);

		return checkMessage;
	}

	@Override
	public ResultMessage completeModifyInventory(InventoryReceiptVO inventoryReceipt,String managerID) throws RemoteException {
		// TODO Auto-generated method stub
		InventoryReceiptPO receiptPO = inventoryReceipt.toPO();

		ResultMessage updateMessage = inventoryDataService.update(receiptPO.getId(), receiptPO);

		if(updateMessage == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND){
			return ResultMessage.RECEIPT_UPDATE_FAULT;//一般不会出现该问题
		}

		Receipt theCheckReceipt = ReceiptFactory.creatReceipt(receiptPO);
		ResultMessage checkMessage = theCheckReceipt.passAudit(managerID);

		return checkMessage;
	}

	@Override
	public ResultMessage completeModifySales(SalesReceiptVO salesReceipt,String managerID) throws RemoteException {
		// TODO Auto-generated method stub
		double sum = 0;
		for(LineItemVO item:salesReceipt.getLineItem()){
			sum = sum + item.getPrice() * item.getQuantity();
		}

		salesReceipt.setSumBeforeDiscount(sum);
		salesReceipt.setSumAfterDiscount(salesReceipt.getSumBeforeDiscount() - salesReceipt.getDiscount() - salesReceipt.getVoucher().getFaceValue());//设置折让后总价

		SalesReceiptPO receiptPO = salesReceipt.toPO();

		ResultMessage updateMessage = salesDataService.update(receiptPO.getId(), receiptPO);

		if(updateMessage == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND){
			return ResultMessage.RECEIPT_UPDATE_FAULT;//一般不会出现该问题
		}

		Receipt theCheckReceipt = ReceiptFactory.creatReceipt(receiptPO);//运用多态
		ResultMessage checkMessage = theCheckReceipt.passAudit(managerID);

		return checkMessage;
	}

	//工具方法，供getAllList使用
	private ArrayList<ReceiptVO> change_list_to_vo(ArrayList<ReceiptPO> find_list){
		ArrayList<ReceiptVO> result = new ArrayList<>();
		for(ReceiptPO receipt:find_list){
			result.add(receipt.toVO());
		}

		return result;
	}

	@Override
	public ArrayList<CommodityVO> getCommodities() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CommodityPO> commodityList = commodityDataService.getAllList();
		ArrayList<CommodityVO> resultCommodityList = new ArrayList<>();
		for(CommodityPO commodity:commodityList){
			resultCommodityList.add(commodity.toVO());
		}

		return resultCommodityList;
	}

	@Override
	public ArrayList<BankAccountVO> getAllAccount() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<BankAccountPO> accountList = accountDataService.getAllList();
		ArrayList<BankAccountVO> resultAccountList = new ArrayList<>();
		for(BankAccountPO account:accountList){
			resultAccountList.add(account.toVO());
		}

		return resultAccountList;
	}
}
