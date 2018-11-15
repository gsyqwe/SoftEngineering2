package salesbl;

import java.util.ArrayList;
import java.util.Date;

import PO.PromotionByCombinationListPO;
import PO.PromotionByLevelPO;
import PO.PromotionBySumPO;
import PromotionStrategydataService.PromotionStrategydataService;
import promotionstrategydata.PromotionStrategyDataUseDataBase;

public class PromotionInfoImpl implements PromotionInfo{
	private PromotionStrategydataService dataService = new PromotionStrategyDataUseDataBase();

	@Override
	public ArrayList<PromotionByLevelPO> findPromotionByLevelByTime(Date nowTime) {
		// TODO Auto-generated method stub

		return dataService.findPromotionByLevelByTime(nowTime);
	}

	@Override
	public ArrayList<PromotionByCombinationListPO> findPromotionByCombinationByTime(Date nowTime) {
		// TODO Auto-generated method stub
		return dataService.findPromotionByCombinationByTime(nowTime);
	}

	@Override
	public ArrayList<PromotionBySumPO> findPromotionBySumByTime(Date nowTime) {
		// TODO Auto-generated method stub
		return dataService.findPromotionBySumByTime(nowTime);
	}

}
