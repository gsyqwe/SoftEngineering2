package PromotionStrategydataService;

import java.util.ArrayList;
import java.util.Date;

import PO.PromotionByCombinationListPO;
import PO.PromotionByLevelPO;
import PO.PromotionBySumPO;
import enums.PromotionType;
import enums.ResultMessage;

public interface PromotionStrategydataService {
	public ResultMessage addPromotionByLevelPO(PromotionByLevelPO po);

	public ResultMessage addPromotionByCombinationList(PromotionByCombinationListPO po);

	public ResultMessage addPromotionBySum(PromotionBySumPO po);

	public ResultMessage deletePromotionByLevel(PromotionByLevelPO po);

	public ResultMessage deletePromotionByCombination(PromotionByCombinationListPO po);

	public ResultMessage deletePromotionBySum(PromotionBySumPO po);

	public ArrayList<PromotionByLevelPO> findPromotionByLevelByTime(Date nowTime);

	public ArrayList<PromotionByCombinationListPO> findPromotionByCombinationByTime(Date nowTime);

	public ArrayList<PromotionBySumPO> findPromotionBySumByTime(Date nowTime);

	public String getSuffix(PromotionType promotionType);

}
