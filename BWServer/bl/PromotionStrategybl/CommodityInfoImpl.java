package PromotionStrategybl;

import java.util.ArrayList;

import PO.CommodityPO;
import commoditydata.CommodityDataUseDataBase;
import commoditydataService.CommodityDataService;

public class CommodityInfoImpl implements CommodityInfo{
	private CommodityDataService dataService = new CommodityDataUseDataBase();//具体用什么dataService由最后数据层来决定，在此先空出

	public CommodityInfoImpl(){
		dataService = new CommodityDataUseDataBase();
	}

	@Override
	public CommodityPO findByID(String id) {
		// TODO Auto-generated method stub
		return dataService.findByID(id);
	}

	@Override
	public ArrayList<CommodityPO> findWithKeyword(String keyword) {
		// TODO Auto-generated method stub
		return dataService.findWithKeyword(keyword);
	}

	@Override
	public ArrayList<CommodityPO> getAllList() {
		// TODO Auto-generated method stub
		return dataService.getAllList();
	}


}
