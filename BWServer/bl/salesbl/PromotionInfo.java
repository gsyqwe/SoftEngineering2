package salesbl;

import java.util.ArrayList;
import java.util.Date;

import PO.PromotionByCombinationListPO;
import PO.PromotionByLevelPO;
import PO.PromotionBySumPO;

public interface PromotionInfo {
	public ArrayList<PromotionByLevelPO> findPromotionByLevelByTime(Date nowTime);

	public ArrayList<PromotionByCombinationListPO> findPromotionByCombinationByTime(Date nowTime);

	public ArrayList<PromotionBySumPO> findPromotionBySumByTime(Date nowTime);

}
