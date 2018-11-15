package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.EmailVO;
import VO.UserVO;
import enums.ResultMessage;

public interface EmailBLService extends Remote {
	public ArrayList<EmailVO> getEmailByUser(String receiverID) throws RemoteException;

	public ResultMessage completeAddEmail(EmailVO email) throws RemoteException;

	public ArrayList<UserVO> getAllUserExcept(String userID) throws RemoteException;

	//在每次阅读完email的时候要更新界面使用这个方法
	public ArrayList<EmailVO> getEmailByState(String receiverID, boolean isRead) throws RemoteException;

	//这个方法在点击一个email进行阅读的时候进行调用，将email的状态设为已读
	public ResultMessage readEmail(EmailVO email) throws RemoteException;

	public ResultMessage deleteEmail(EmailVO email) throws RemoteException;

	public ResultMessage deleteEmailByList(ArrayList<EmailVO> emails) throws RemoteException;

}
