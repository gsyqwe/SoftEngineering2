package PromotionStrategybl;

import java.util.ArrayList;

import PO.CommodityPO;

public interface CommodityInfo {
	public CommodityPO findByID(String id);

	public ArrayList<CommodityPO> findWithKeyword(String keyword);

	public ArrayList<CommodityPO> getAllList();
}
