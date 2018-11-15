package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.BankAccountVO;
import VO.CommodityVO;
import VO.FinancialReceiptVO;
import VO.InventoryReceiptVO;
import VO.ReceiptVO;
import VO.SalesReceiptVO;
import enums.ResultMessage;

/**
 *
 * @author 82646,因为现在所用的vo都是完整的vo，所以都使用vo进行参数传递
 *
 */
public interface CheckReceiptBLService extends Remote{

	//表示审核是否通过的方法，如果是对一个单据进行审核，只需要在receiptList里面传入一个单据即可
	public ArrayList<String> passByList(ArrayList<ReceiptVO> receiptList,String manangerID) throws RemoteException;

	public ArrayList<String> failPassByList(ArrayList<ReceiptVO> receiptList,String managerID) throws RemoteException;

	//用来显示未被审核的单据，在界面刚刷新的时候触发，在每次有单据通过或者不通过审核的时候触发
	public ArrayList<ReceiptVO> showTheUncheckedReceiptList() throws RemoteException;

	//下面在将单据修改完后触发，修改的要求同BusinessCondition，只能修改LineItem，将最后修改的单据直接入账，传到data层进行保存,别忘了这个时候的保存是更新单据，不是insert
	public ResultMessage completeModifyFinancial(FinancialReceiptVO financialReceipt,String managerID) throws RemoteException;

	public ResultMessage completeModifyInventory(InventoryReceiptVO inventoryReceipt,String managerID) throws RemoteException;

	public ResultMessage completeModifySales(SalesReceiptVO salesReceipt,String managerID) throws RemoteException;

	//以下两个方法是用作修改单据的时候使用，因为只能改变单据的LineItem，所以只需要得到当前的商品列表和当前的账户列表即可
	public ArrayList<CommodityVO> getCommodities() throws RemoteException;

	public ArrayList<BankAccountVO> getAllAccount() throws RemoteException;
}
