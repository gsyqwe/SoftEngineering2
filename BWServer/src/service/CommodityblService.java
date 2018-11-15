package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.CommodityVO;
import VO.EmailForAlarmVO;
import VO.StockInAndOutVO;
import enums.ResultMessage;

public interface CommodityblService extends Remote{

	public ResultMessage addCommodity(CommodityVO vo)throws RemoteException;

	public ResultMessage modifyCommodity(CommodityVO vo)throws RemoteException;

	public ResultMessage deleteCommodity(String id)throws RemoteException;

	public CommodityVO findByID(String id)throws RemoteException;
	
	public CommodityVO findByName(String name)throws RemoteException;

	public ArrayList<CommodityVO> findWithKeyWord(String keyword)throws RemoteException;

	public ArrayList<CommodityVO> showCommodityList()throws RemoteException;

	public ResultMessage stockInAndOut(StockInAndOutVO vo,ArrayList<EmailForAlarmVO> emails)throws RemoteException;

}