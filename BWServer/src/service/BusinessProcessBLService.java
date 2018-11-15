package service;

import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.BankAccountVO;
import VO.CommodityVO;
import VO.FinancialReceiptVO;
import VO.InventoryReceiptVO;
import VO.ReceiptVO;
import VO.SalesReceiptVO;
import enums.ResultMessage;
import receiptFinder.Finder;
import receiptFinder.ReceiptDataServiceInfo;
/*
* Module Comments:  �w�q�d�ݸg����{�����һݭn�����f
* Author:	161250051/Lai Kin Meng
* Create Date�G  2017-10-25
* Modified By�G   161250051/Lai Kin Meng
* Modified Date:  2017-11-05
* Why & What is modified�Gadd comments & change some arguments from po to vo
*/

/**
 *
 * @author JiYuzhe
 * 红冲和红冲并复制的单据直接入账，红冲的单据在编号后面加上-RC 红冲并复制的修改单在编号后面加上-RCM
 */
public interface BusinessProcessBLService{

	//红冲的时候新建一个各项取负的单据编号是在原来的编号最后加上-RC字段  具体选择调用哪个方法，看选中的什么单据，根据选中的ID去判断调用哪个方法
	public ResultMessage redCopyFinancial(FinancialReceiptVO financialReceipt,String financialID) throws RemoteException;//红冲，只是将单据内容取负

	public ResultMessage redCopyInventory(InventoryReceiptVO inventoryReceipt,String financialID) throws RemoteException;

	public ResultMessage redCopySales(SalesReceiptVO salesReceipt,String financialID) throws RemoteException;

	//下面在红冲并复制单据时将单据修改完后触发，将最后修改的单据直接入账，传到data层进行保存
	public ResultMessage completeModifyFinancial(FinancialReceiptVO financialReceipt,String financialID) throws RemoteException;

	public ResultMessage completeModifyInventory(InventoryReceiptVO inventoryReceipt,String financialID) throws RemoteException;

	public ResultMessage completeModifySales(SalesReceiptVO salesReceipt,String financialID) throws RemoteException;

	//这个方法是调用筛选方法的时候触发，确保每个Finder被同一个ArrayList<ReceiptDataServiceInfo>初始化过，每个finder对应一个keyword
	//keywords里面不包括大的单据类型
	public ArrayList<ReceiptVO> finByAllMeans(ArrayList<Finder> finder,ArrayList<String> keywords) throws RemoteException;

	//得到所有已经生效的单据列表，请确保传入的ArrayList<ReceiptDataService>里面包含了所有类型的dataService且不能重复
	public ArrayList<ReceiptVO> getAllList(ArrayList<ReceiptDataServiceInfo> dataServiceInfos) throws RemoteException;

	//以下两个方法是用作红冲并复制的时候使用，因为只能改变单据的LineItem，所以只需要得到当前的商品列表和当前的账户列表即可
	public ArrayList<CommodityVO> getCommodities() throws RemoteException;

	public ArrayList<BankAccountVO> getAllAccount() throws RemoteException;

}
