package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import VO.CommodityVO;
import VO.InventoryReceiptVO;
import enums.InventoryReceiptType;
import enums.ReceiptState;
import enums.ResultMessage;

/**
 *
 * @author 82646，草稿单没有id，唯一标识的
 *
 */
public interface InventoryReceiptblService extends Remote{
	public ResultMessage sendAudit(InventoryReceiptVO receipt) throws RemoteException;//在此方法中增加对所填信息进行检查

	public ResultMessage endReceipt(InventoryReceiptVO receipt) throws RemoteException;//在点击保存或者退出保存草稿的时候调用，是不需要传参数的
	//,所有调用endReceipt方法之前都要检查有没有填写时间，这个检查是在界面层进行的

	public ArrayList<CommodityVO> getCommodities() throws RemoteException;//得到当前能使用的commodities

	public ResultMessage modifyReceipt(String targetID,InventoryReceiptVO inventoryReceipt) throws RemoteException;

	public ResultMessage deleteReceipt(String ID) throws RemoteException;//用于删除草稿箱里面的单据

	public ResultMessage completeAdd(InventoryReceiptVO receipt) throws RemoteException;//有一个检查按钮，点击检查按钮触发检查当前填写是否完整

	public InventoryReceiptVO findByID(String id)throws RemoteException;

	public ArrayList<InventoryReceiptVO> findByUser(String userId)throws RemoteException;

	public ArrayList<InventoryReceiptVO> findByDate(Date startTime, Date endTime)throws RemoteException;

	public ArrayList<InventoryReceiptVO> findByReceiptState(ReceiptState receiptState)throws RemoteException;

	public ArrayList<InventoryReceiptVO> findByInventoryReceiptType(InventoryReceiptType inventoryReceiptType)throws RemoteException;

	public ArrayList<InventoryReceiptVO> getDraftList(String userId) throws RemoteException;//双重筛选，将userid和单据状态进行双重筛选，得到当前的草稿单
}
