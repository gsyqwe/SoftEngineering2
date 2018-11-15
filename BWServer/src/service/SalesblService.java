package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.CommodityVO;
import VO.MemberVO;
import VO.SalesReceiptVO;
import enums.MemberType;
import enums.ReceiptState;
import enums.ResultMessage;

/**
 *
 * @author JiYuzhe 删除了一些不需要的多余方法，更改了addLineItem的接口
 */

public interface SalesblService extends Remote {

	// 现在因为在界面上维护了一个ArrayList<LineItemVO>所以删除一行和添加一行对于逻辑层来说可以调用同一个方法
	public SalesReceiptVO addLineItem(SalesReceiptVO salesVO,
			ArrayList<String> promotionDescription, ArrayList<Double> discount)
			throws RemoteException;// 添加LineItem，传入当前的lineItem的arrayList和一个接受促销信息的ArrayList<String>
	// 这里面的promotionDescription是用来保存所触发的促销策略的string描述的，调用的时候传入空的ArrayList

	// 这个方法在除了销售单之外的其他单据的制定时使用，传入完整的SalesReceiptVO
	public ResultMessage completeAdd(SalesReceiptVO salesReceipt)
			throws RemoteException;

	public SalesReceiptVO getReceiptByID(String id) throws RemoteException;

	public ResultMessage endSale(SalesReceiptVO salesVO) throws RemoteException;

	public ResultMessage sendAudit(SalesReceiptVO salesVO)
			throws RemoteException;

	public ArrayList<CommodityVO> getCommodities() throws RemoteException;

	public ArrayList<MemberVO> getNowMembers(MemberType type)
			throws RemoteException;// 根据传入的客户类型筛选客户

	public ResultMessage deleteReceipt(String ID) throws RemoteException;// 用于删除草稿箱里面的单据

	public ArrayList<SalesReceiptVO> getOperatorReceipts(String userID)
			throws RemoteException;

	public ArrayList<SalesReceiptVO> getListByReceiptState(ReceiptState state,
			String operatorID) throws RemoteException;

	public ArrayList<SalesReceiptVO> getDraftList(String userid)
			throws RemoteException;
}
