package inventoryreceiptdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import PO.InventoryReceiptPO;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;

public class InventoryReceiptDataUseTxt extends InventoryReceiptData {
	private static Map<String, InventoryReceiptPO> data = new HashMap<String, InventoryReceiptPO>();
	private static final String txtName = "InventoryReceiptPO";

	public InventoryReceiptDataUseTxt() {
		setIoStrategy(new IOStrategyUseTxt<>(txtName));
		backUpFromFile();
	}

	protected static String getTxtname() {
		return txtName;
	}

	private void backUpFromFile() {
		Iterator<InventoryReceiptPO> iterator = (Iterator<InventoryReceiptPO>) getIoStrategy()
				.outAll(InventoryReceiptPO.class);
		while (iterator.hasNext()) {
			InventoryReceiptPO po = iterator.next();
			data.put(po.getId(), po);
		}
	}

	private Boolean hasThisId(String id) {
		return data.containsKey(id);
	}

	@Override
	public ArrayList<InventoryReceiptPO> getAllList() {
		return data.values().parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public InventoryReceiptPO findByID(String id) {
		if (null == id || id.length() == 0)
			throw new IllegalArgumentException();
		if (!hasThisId(id))
			return null; // not exist

		return data.get(id);
	}

	@Override
	protected ResultMessage insertHooked(InventoryReceiptPO po) {
		if (hasThisId(po.getId()))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;

		data.put(po.getId(), po);
		if (!getIoStrategy().inOne(po))
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

	@Override
	protected ResultMessage updateHooked(String targetID, InventoryReceiptPO replacement) {
		if (!hasThisId(targetID))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		data.replace(targetID, replacement);
		if (!getIoStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}
}
