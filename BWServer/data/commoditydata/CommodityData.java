package commoditydata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import PO.CommodityPO;
import commoditydataService.CommodityDataService;
import enums.ResultMessage;
import idhelper.IDHelper;
import persistence.txt.service.IOStrategy;

public abstract class CommodityData implements CommodityDataService {
	private IOStrategy<CommodityPO> ioStrategy;

	private void isValidIfNotThrowIllegalArgumentException(String str) {
		if (null == str || str.isEmpty())
			throw new IllegalArgumentException();
	}

	private void isValidIfNotThrowIllegalArgumentException(Object obj) {
		if (null == obj)
			throw new IllegalArgumentException();
	}

	protected abstract ResultMessage insertHooked(CommodityPO po);

	protected abstract ResultMessage deleteHooked(String targetID);

	protected abstract ResultMessage updateHooked(String targetID, CommodityPO replacememt);

	@Override
	public ResultMessage insert(CommodityPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		return insertHooked(po);
	}

	@Override
	public ResultMessage delete(String id) {
		isValidIfNotThrowIllegalArgumentException(id);
		return deleteHooked(id);
	}

	@Override
	public ResultMessage update(String targetID, CommodityPO replacememt) {
		isValidIfNotThrowIllegalArgumentException(targetID);
		isValidIfNotThrowIllegalArgumentException(replacememt);
		/*
		 * 此處應該檢查targetId == replacements.getID
		 */
		if (!replacememt.getId().equals(targetID))
			throw new IllegalArgumentException("目標ID與覆蓋ID不同");
		return updateHooked(targetID, replacememt);
	}

	@Override
	public abstract ArrayList<CommodityPO> getAllList();

	@Override
	public CommodityPO findByID(String id) {
		isValidIfNotThrowIllegalArgumentException(id);
		List<CommodityPO> findResult = this.getAllList();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getId().equals(id))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult.get(0);
	}

	@Override
	public ArrayList<CommodityPO> findWithKeyword(String keyword) {
		isValidIfNotThrowIllegalArgumentException(keyword);
		ArrayList<CommodityPO> findResult = getAllList();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getName().contains(keyword))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult;
	}

	@Override
	public String getIDSuffix() throws IllegalArgumentException {
		Iterator<CommodityPO> iterator = ioStrategy.outAll(CommodityPO.class);
		int count = 0;
		while (iterator.hasNext()) {
			count++;
			iterator.next();
		}
		return IDHelper.toKBitString(count + 1, 5);
	}

	protected void setIoStrategy(IOStrategy<CommodityPO> ioStrategy) {
		this.ioStrategy = ioStrategy;
	}

	protected IOStrategy<CommodityPO> getIoStrategy() {
		return ioStrategy;
	}

	@Override
	public CommodityPO findByName(String name) {
		isValidIfNotThrowIllegalArgumentException(name);
		ArrayList<CommodityPO> resultList = this.getAllList();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getName().equals(name))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList.get(0);
	}
}
