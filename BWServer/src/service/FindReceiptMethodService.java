package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.ReceiptVO;

public interface FindReceiptMethodService extends Remote{

	public ArrayList<ReceiptVO> findByCategory(String word) throws RemoteException;

	public ArrayList<ReceiptVO> findByDate(String word) throws RemoteException;

	public ArrayList<ReceiptVO> findByMember(String word) throws RemoteException;

	public ArrayList<ReceiptVO> findByUser(String word) throws RemoteException;


}
