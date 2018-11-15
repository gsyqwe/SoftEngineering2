package salesdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import PO.SalesReceiptPO;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;

public class SalesDataUseTxt extends SalesData {
	private static Map<String, SalesReceiptPO> data = new HashMap<String, SalesReceiptPO>();
	private static final String txtName = "SalesReceiptPO";

	public SalesDataUseTxt() {
		setIoStrategy(new IOStrategyUseTxt<>(txtName));
		backUpFromFile();
	}

	private void backUpFromFile() {
		Iterator<SalesReceiptPO> iterator = (Iterator<SalesReceiptPO>) getIoStrategy().outAll(SalesReceiptPO.class);
		while (iterator.hasNext()) {
			SalesReceiptPO po = iterator.next();
			data.put(po.getId(), po);
		}
	}

	private Boolean hasThisId(String id) {
		return data.containsKey(id);
	}

	@Override
	public SalesReceiptPO findByID(String id) {
		if (id == null)
			throw new IllegalArgumentException();

		if (!hasThisId(id))
			return null;

		return data.get(id);
	}

	@Override
	public ArrayList<SalesReceiptPO> getAllList() {
		return data.values().parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected ResultMessage insertHooked(SalesReceiptPO po) {
		if (hasThisId(po.getId()))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;

		data.put(po.getId(), po);

		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;

		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage updateHooked(String targetID, SalesReceiptPO replacement) {
		if (!hasThisId(targetID))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;

		data.replace(targetID, replacement);

		if (!getIoStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;

		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage deleteHooked(String id) {
		if (!hasThisId(id))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;

		data.remove(id);

		if (!getIoStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;

		return ResultMessage.SUCCESS;
	}

}
