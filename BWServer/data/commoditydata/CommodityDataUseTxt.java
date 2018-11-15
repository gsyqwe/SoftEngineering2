package commoditydata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import PO.CommodityPO;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;

public class CommodityDataUseTxt extends CommodityData {
	private static Map<String, CommodityPO> data = new HashMap<String, CommodityPO>();

	private static final String txtName = "CommodityPO";

	public CommodityDataUseTxt() {
		setIoStrategy(new IOStrategyUseTxt<>(txtName));
		backUpFormFile();
	}

	private void backUpFormFile() {
		Iterator<CommodityPO> iterator = (Iterator<CommodityPO>) getIoStrategy().outAll(CommodityPO.class);
		while (iterator.hasNext()) {
			CommodityPO po = iterator.next();
			data.put(po.getId(), po);
		}
	}

	private Boolean hasThisId(String id) {
		return data.containsKey(id);
	}

	@Override
	public CommodityPO findByID(String id) {
		if (id == null)
			throw new IllegalArgumentException();
		if (!hasThisId(id))
			return null;
		return data.get(id);
	}

	@Override
	protected ResultMessage insertHooked(CommodityPO po) {
		if (hasThisId(po.getId()))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		data.put(po.getId(), po);
		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage deleteHooked(String targetID) {
		if (!hasThisId(targetID))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		data.remove(targetID);
		if (!getIoStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage updateHooked(String targetID, CommodityPO replacememt) {
		if (!hasThisId(targetID))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		data.replace(targetID, replacememt);
		if (!getIoStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CommodityPO> getAllList() {
		return data.values().parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}
}
