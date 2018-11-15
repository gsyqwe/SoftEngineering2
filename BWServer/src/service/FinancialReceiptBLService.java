package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.BankAccountVO;
import VO.FinancialReceiptVO;
import VO.LineItemVO;
import enums.ReceiptState;
import enums.ResultMessage;

/**
 *
 * @author 82646 在界面Recover from draft的时候，要在跳转的同时删除掉原来的draft，否则会出现draft被重新提交以前的草稿还在的情况
 *
 */
public interface FinancialReceiptBLService extends Remote{

	public double getNowSum(ArrayList<LineItemVO> items) throws RemoteException;//因为在界面层要显示当前总价，可以有一个计算总价的按钮，触发当前方法，传入当前的LineItemVO，计算当前的总价

	public ResultMessage completeAdd(FinancialReceiptVO receipt) throws RemoteException;

	public ResultMessage deleteDraft(String receiptID) throws RemoteException;//其实就是调用data层的删除单据方法，在设计中只有草稿单能被删去

	public ResultMessage sendAudit(FinancialReceiptVO receipt) throws RemoteException;//在单据填写完成后将单据发出进行审核

	public ResultMessage endFinancial(FinancialReceiptVO receipt) throws RemoteException;//在单据未填写完成的时候点击保存

	public ResultMessage modifyReceipt(String targetID,FinancialReceiptVO receipt) throws RemoteException;//保留一个供总经理修改已存在单据的方法，在逻辑层的基本做法和completeAdd方法类似

	public ArrayList<BankAccountVO> getAllAccount() throws RemoteException;

	public FinancialReceiptVO findByID(String targetID) throws RemoteException;

	public ArrayList<FinancialReceiptVO> findByUser(String userID) throws RemoteException;

	public ArrayList<FinancialReceiptVO> findByReceiptState(ReceiptState receiptState) throws RemoteException;

	public ArrayList<FinancialReceiptVO> getDraftList(String userID) throws RemoteException;//得到当前的草稿
}
