package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import enums.ResultMessage;
import VO.InitializeAccountInShortVO;
import VO.InitializeAccountVO;

public interface InitializeAccountBLService extends Remote{
	public InitializeAccountVO getRemainingData() throws RemoteException;
	public ResultMessage setUp(InitializeAccountVO vo) throws RemoteException;
	public ArrayList<InitializeAccountInShortVO> getList() throws RemoteException;
	public InitializeAccountVO findByDate(Date date)throws RemoteException;
	public ArrayList<InitializeAccountInShortVO> findByTime(Date startTime,Date endTime)throws RemoteException;
}
