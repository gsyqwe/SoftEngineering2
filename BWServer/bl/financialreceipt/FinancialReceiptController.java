package financialreceipt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import PO.BankAccountPO;
import PO.FinancialReceiptPO;
import PO.LineItemPO;
import VO.BankAccountVO;
import VO.FinancialReceiptVO;
import VO.LineItemVO;
import bankaccountdata.BankAccountDataUseDataBase;
import bankaccountdataService.BankAccountDataService;
import enums.FinancialReceiptType;
import enums.ReceiptState;
import enums.ResultMessage;
import financialreceiptdata.FinancialReceiptDataUseDataBase;
import financialreceiptdataservice.FinancialReceiptDataService;
import service.FinancialReceiptBLService;

/*
* Module Comments:  		FinancialReceipt������H
* Author:					161250051/Lai Kin Meng
* Create Date�G  				2017-10-25
* Modified By�G  	 			161250051/Lai Kin Meng
* Modified Date:
* Why & What is modified�G
*/
public class FinancialReceiptController extends UnicastRemoteObject implements FinancialReceiptBLService{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	FinancialReceiptPO receipt;
	BankAccountDataService accountDataService = new BankAccountDataUseDataBase();
	FinancialReceiptDataService dataService = new FinancialReceiptDataUseDataBase();

	public FinancialReceiptController() throws RemoteException{
		super();
		receipt= new FinancialReceiptPO();
	}

	public FinancialReceiptController(FinancialReceiptPO receipt) throws RemoteException{
		super();
		this.receipt = receipt;
	}

	@Override//可以专门有一个按钮在总价旁，叫做计算总价，每次点击就会重新计算当前的总价，或者监听那个文本框，在被点击后自动填入总价
	public double getNowSum(ArrayList<LineItemVO> items) throws RemoteException{
		ArrayList<LineItemPO> resultItems = new ArrayList<>();
		for(LineItemVO item:items){
			resultItems.add(item.toPO());
		}

		receipt.setLineItem(resultItems);//重新设定当前的LineItem
		double sum = this.getSum();
		receipt.setSum(sum);//设定现在的sum

		return sum;
	}

	@Override
	public ResultMessage completeAdd(FinancialReceiptVO receipt) throws RemoteException {//专门有一个检查按钮，点击后检查填写的数据是否有问题
		 //TODO Auto-generated method stub  对付款单和现金费用单要注意，判断所付款金额是否超出了账户上所有的金额
		if(receipt.getFinancialReceiptType() == FinancialReceiptType.CASH_CLAIM || receipt.getFinancialReceiptType() == FinancialReceiptType.BILL){
			for(LineItemVO item:receipt.getLineItem()){
				BankAccountPO account = accountDataService.findByID(item.getId());
				if(item.getPrice() > account.getAmount()){
					return ResultMessage.FINANCIAL_RECEIPT_ACCOUNT_NEGATIVE;
				}
			}
		}
		this.receipt = receipt.toPO();

		double sum = this.getSum();
		receipt.setSum(sum);
		this.receipt = receipt.toPO();
		return this.sendAudit(receipt);
	}

	@Override
	public ResultMessage deleteDraft(String receiptID) throws RemoteException {
		// TODO Auto-generated method stub
		return dataService.delete(receiptID);
	}

	@Override
	public ResultMessage endFinancial(FinancialReceiptVO receipt) throws RemoteException {
		// TODO Auto-generated method stub
		this.receipt = receipt.toPO();

		String draftID = "";
		String date = dateToStringSpecific(receipt.getDate());
		draftID = receipt.getOperatorID() + " " + date;
		this.receipt.setId(draftID);

		this.receipt.setState(ReceiptState.UNCOMMITTED);//标识为草稿状态然后将该单据保存

		ResultMessage insertMessage = dataService.insert(this.receipt);
		if(insertMessage == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED){
			return dataService.update(this.receipt.getId(), this.receipt);
		}
		return insertMessage;
	}

	@Override
	public ResultMessage modifyReceipt(String targetID, FinancialReceiptVO receipt) throws RemoteException {
		// TODO Auto-generated method stub
		return dataService.update(targetID, this.receipt);
	}

	@Override
	public ArrayList<BankAccountVO> getAllAccount() throws RemoteException {//在getAllAccount里面可以显示在界面上BankAccount的编号和余额
		// TODO Auto-generated method stub
		ArrayList<BankAccountVO> result = new ArrayList<>();
		ArrayList<BankAccountPO> account_po = accountDataService.getAllList();

		if(account_po == null || account_po.size() == 0){
			return new ArrayList<>();
		}

		for(BankAccountPO account:account_po){
			result.add(account.toVO());
		}
		return result;
	}

	@Override
	public FinancialReceiptVO findByID(String targetID) {
		// TODO Auto-generated method stub
		return dataService.findByID(targetID).toVO();
	}

	@Override
	public ArrayList<FinancialReceiptVO> findByUser(String userID) {
		// TODO Auto-generated method stub
		ArrayList<FinancialReceiptPO> find_list = dataService.findByUser(userID);
		return changeListToShow(find_list);
	}

	@Override
	public ArrayList<FinancialReceiptVO> findByReceiptState(ReceiptState receiptState) {
		// TODO Auto-generated method stub
		ArrayList<FinancialReceiptPO> find_list = dataService.findByReceiptState(receiptState);
		return changeListToShow(find_list);
	}

	private ArrayList<FinancialReceiptVO> changeListToShow(ArrayList<FinancialReceiptPO> find_list){
		ArrayList<FinancialReceiptVO> result = new ArrayList<>();
		for(FinancialReceiptPO item:find_list){
			result.add(item.toVO());
		}
		return result;
	}

	@Override
	public ResultMessage sendAudit(FinancialReceiptVO receipt) throws RemoteException {//点击提交按钮触发，有可能在没有检查的时候触发，所以先调用检查方法，没问题再提交
		// TODO Auto-generated method stub

		//设定单据的id
		String financialIDSuffix = dataService.getIDSuffix(receipt.getDate(),receipt.getFinancialReceiptType());
		String financialID = this.getPrefix(receipt.getFinancialReceiptType());
		financialID = financialID + dateToString(receipt.getDate()) + financialIDSuffix;
		this.receipt.setId(financialID);
		this.receipt.setState(ReceiptState.SUBMITTED);
		ResultMessage insertMessage = dataService.insert(this.receipt);

		if(insertMessage == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED){
			return dataService.update(this.receipt.getId(), this.receipt);
		}

		return insertMessage;
	}

	@Override
	public ArrayList<FinancialReceiptVO> getDraftList(String userID) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<FinancialReceiptVO> userReceipt = this.findByUser(userID);
		ArrayList<FinancialReceiptVO> resultReceipt = new ArrayList<>();

		for(FinancialReceiptVO receipt:userReceipt){
			if(receipt.getState() == ReceiptState.UNCOMMITTED){
				resultReceipt.add(receipt);
			}
		}

		//将得到的草稿时间从晚到早排序
		Collections.sort(resultReceipt, new Comparator<FinancialReceiptVO>() {
	        @Override
	        public int compare(FinancialReceiptVO fruit1, FinancialReceiptVO fruit2)
	        {

	            return fruit2.getDate().compareTo(fruit1.getDate());
	        }
	    });

		return resultReceipt;
	}

	//工具方法：根据lineItem计算当前总价,被completeAdd和getNowSum使用
	private double getSum() throws RemoteException {
		// TODO Auto-generated method stub
		double sum = 0;
		for(LineItemPO item:receipt.getLineItemAsList()){
			sum = sum + item.getPrice();
		}

		receipt.setSum(sum);
		return sum;
	}

	//工具方法，将时间以yyyyMMdd的字符串形式打印出来
	private String dateToString(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String s = formatter.format(date);
		return s + "-";
	}

	private String dateToStringSpecific(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String s = formatter.format(date);
		return s + "-";
	}

	private String getPrefix(FinancialReceiptType type){
		String result = "";
		switch(type){
		case CASH_CLAIM:{
			result = "XJFYD-";
			break;
		}
		case DEBIT_NOTE:{
			result = "SKD-";
			break;
		}
		case BILL:{
			result = "FKD-";
			break;
		}
		default:{

		}
		}

		return result;
	}
}
