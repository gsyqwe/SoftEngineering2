package PromotionStrategybl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import PO.CommodityPO;
import PO.PromotionByCombinationListPO;
import PO.PromotionByLevelPO;
import PO.PromotionBySumPO;
import PromotionStrategydataService.PromotionStrategydataService;
import VO.CommodityVO;
import VO.PromotionByCombinationListVO;
import VO.PromotionByLevelVO;
import VO.PromotionBySumVO;
import enums.PromotionType;
import enums.ResultMessage;
import promotionstrategydata.PromotionStrategyDataUseDataBase;
import service.PromotionStrategyblService;

/**
 * @author JiYuzhe
 * 先没有对每个VO进行检查，直接转为PO
 */

public class PromotionStrategyController extends UnicastRemoteObject implements PromotionStrategyblService{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public PromotionStrategyController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	PromotionStrategydataService dataService = new PromotionStrategyDataUseDataBase();
	CommodityInfo commodity = new CommodityInfoImpl();

	@Override
	public ArrayList<CommodityVO> getCommodityList() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CommodityPO> commodities = commodity.getAllList();
		ArrayList<CommodityVO> result = new ArrayList<>();
		for(CommodityPO com:commodities){
			CommodityVO tem = com.toVO();
			result.add(tem);
		}
		return result;
	}

	@Override
	public ResultMessage completeAddPromotionByLevel(PromotionByLevelVO promotion) throws RemoteException {
		promotion.setPromotionID(this.getSuffix(PromotionType.PromotionByLevel));
		// TODO Auto-generated method stub
		PromotionByLevelPO promotion_loaded = promotion.toPO();
		return dataService.addPromotionByLevelPO(promotion_loaded);
	}

	@Override
	public ResultMessage completeAddPromotionByCombinationList(PromotionByCombinationListVO promotion)
			throws RemoteException {
		promotion.setPromotionID(this.getSuffix(PromotionType.PromotionByCombination));
		// TODO Auto-generated method stub
		PromotionByCombinationListPO promotion_loaded = promotion.toPO();
		return dataService.addPromotionByCombinationList(promotion_loaded);
	}

	@Override
	public ResultMessage completeAddPromotionBySum(PromotionBySumVO promotion) throws RemoteException {
		// TODO Auto-generated method stub
		promotion.setPromotionID(this.getSuffix(PromotionType.PromotionBySum));
		System.out.println(promotion);
		PromotionBySumPO promotion_loaded = promotion.toPO();
		System.out.println(promotion_loaded);
		return dataService.addPromotionBySum(promotion_loaded);
	}

	public String getSuffix(PromotionType promotionType) throws RemoteException {
		// TODO Auto-generated method stub
		String index = getPre(promotionType) + "-";
		String suffix = dataService.getSuffix(promotionType);
		return index + suffix;
	}

	private String getPre(PromotionType type){
		if(type == PromotionType.PromotionBySum){
			return "PBS";
		}
		else if(type == PromotionType.PromotionByLevel){
			return "PBL";
		}
		else if(type == PromotionType.PromotionByCombination){
			return "PBC";
		}

		return "wrong";
	}

	@Override
	public ArrayList<PromotionByCombinationListVO> getEffectivePBC(Date date) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PromotionByCombinationListPO> find_promotion = dataService.findPromotionByCombinationByTime(date);
		ArrayList<PromotionByCombinationListVO> result = new ArrayList<>();
		for(PromotionByCombinationListPO promotion:find_promotion){
			result.add(promotion.toVO());
		}

		return result;
	}

	@Override
	public ArrayList<PromotionByLevelVO> getEffectivePBL(Date date) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PromotionByLevelPO> find_promotion = dataService.findPromotionByLevelByTime(date);
		ArrayList<PromotionByLevelVO> result = new ArrayList<>();
		for(PromotionByLevelPO promotion:find_promotion){
			result.add(promotion.toVO());
		}

		return result;
	}

	@Override
	public ArrayList<PromotionBySumVO> getEffectivePBS(Date date) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PromotionBySumPO> find_promotion = dataService.findPromotionBySumByTime(date);
		ArrayList<PromotionBySumVO> result = new ArrayList<>();
		for(PromotionBySumPO promotion:find_promotion){
			result.add(promotion.toVO());
		}

		return result;
	}

}
