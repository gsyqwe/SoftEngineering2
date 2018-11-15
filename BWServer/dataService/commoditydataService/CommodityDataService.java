package commoditydataService;

import java.util.ArrayList;

import PO.CommodityPO;
import enums.ResultMessage;

public interface CommodityDataService {
	public ResultMessage delete(String id);

	public CommodityPO findByID(String id);

	public ArrayList<CommodityPO> findWithKeyword(String keyword);

	public ArrayList<CommodityPO> getAllList();

	/**
	 * 
	 * @param date
	 * @return
	 * @throws IllegalArgumentException
	 */
	public String getIDSuffix() throws IllegalArgumentException;

	public ResultMessage insert(CommodityPO po);

	public ResultMessage update(String targetID, CommodityPO replacememt);

	public CommodityPO findByName(String name);
}
