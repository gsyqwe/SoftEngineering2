package salesbl;

import java.util.ArrayList;

import PO.CommodityPO;
import commoditydata.CommodityDataUseDataBase;
import commoditydataService.CommodityDataService;

public class CommodityInfoImpl implements CommodityInfo{
	private CommodityDataService dataService = new CommodityDataUseDataBase();

	@Override
	public ArrayList<CommodityPO> getAllList() {
		// TODO Auto-generated method stub
		return dataService.getAllList();
	}


}
