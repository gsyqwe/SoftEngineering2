package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import VO.CommodityVO;
import VO.PromotionByCombinationListVO;
import VO.PromotionByLevelVO;
import VO.PromotionBySumVO;
import enums.ResultMessage;

/**
 *
 * @author JiYuzhe
 * promotion得到后缀的时候只需要传入当前promotion的类型就好了，不需要时间
 *
 */
public interface PromotionStrategyblService extends Remote{

	public ArrayList<CommodityVO> getCommodityList() throws RemoteException;

	public ResultMessage completeAddPromotionByLevel(PromotionByLevelVO promotion) throws RemoteException;

	public ResultMessage completeAddPromotionByCombinationList(PromotionByCombinationListVO promotion) throws RemoteException;

	public ResultMessage completeAddPromotionBySum(PromotionBySumVO promotion) throws RemoteException;

	public ArrayList<PromotionByCombinationListVO> getEffectivePBC(Date date) throws RemoteException;

	public ArrayList<PromotionByLevelVO> getEffectivePBL(Date date) throws RemoteException;

	public ArrayList<PromotionBySumVO> getEffectivePBS(Date date) throws RemoteException;
}
