package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import PO.ReceiptPO;
import VO.ReceiptVO;
import enums.ResultMessage;

/**
 *
 * @author JiYuzhe
 * problem:其实没想像中的那么简单，除了有一个生产单据的简单工厂之外，每个单据要组合一些dataService
 * 要给总经理提供一个接口修改单据的内容，还要再组合一个生成单据的controller负责对单据进行更新
 *感觉不再需要getReceiptPO方法了，因为修改直接调用controller的方法
 */

public interface ReceiptWorks extends Remote{

	public ReceiptVO prepareToShow() throws RemoteException;//显示单据，返回ReceiptVO供界面显示

	public ResultMessage passAudit(String managerID) throws RemoteException;//在通过审核后调用这个方法，更改单据的状态和修改系统数据，其中修改系统数据可以通过调用changeData方法实现

	public ResultMessage failPassing(String managerID) throws RemoteException;//在审核不通过时调用的方法

	public ResultMessage setReceiptPO(ReceiptVO newReceipt,String managerID) throws RemoteException;//如果总经理想修改单据内容的时候调用这个方法

	public ReceiptPO getReceiptPO() throws RemoteException;//get方法，得到现在的PO对象，然后可以分别对当前po的各项属性进行操作和修改

}

