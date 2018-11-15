package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import VO.LogVO;
import enums.ResultMessage;

public interface OperationLogblService extends Remote{
	public ArrayList<LogVO> getAllLog()throws RemoteException;
	public LogVO findByID(String ID)throws RemoteException;
	public ArrayList<LogVO> findByUser(String userID)throws RemoteException;
	public ArrayList<LogVO> findByTime(Date startTime,Date endTime) throws RemoteException;
	public ArrayList<LogVO> findLogByCommetWithKeyword(String keyword) throws RemoteException;
	public ResultMessage add(LogVO log)throws RemoteException;
}
