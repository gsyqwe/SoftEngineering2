
package financialreceiptdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import PO.FinancialReceiptPO;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;

public class FinancialReceiptDataUseTxt extends FinancialReceiptData {
	private static Map<String, FinancialReceiptPO> data = new HashMap<String, FinancialReceiptPO>();
	private static final String txtName = "FinancialReceiptPO";

	public FinancialReceiptDataUseTxt() {
		setIoStrategy(new IOStrategyUseTxt<>(txtName));
		backUpFromFile();
	}

	private void backUpFromFile() {
		Iterator<FinancialReceiptPO> iterator = (Iterator<FinancialReceiptPO>) getIoStrategy()
				.outAll(FinancialReceiptPO.class);
		while (iterator.hasNext()) {
			FinancialReceiptPO po = iterator.next();
			data.put(po.getId(), po);
		}
	}

	public static String getTxtname() {
		return txtName;
	}

	private Boolean hasThisId(String id) {
		return data.containsKey(id);
	}

	@Override
	public FinancialReceiptPO findByID(String targetID) {
		if (targetID == null)
			throw new IllegalArgumentException();
		if (!hasThisId(targetID))
			return null;
		return data.get(targetID);
	}

	@Override
	protected ResultMessage insertHooked(FinancialReceiptPO po) {
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
	protected ResultMessage updateHooked(String targetID, FinancialReceiptPO replacement) {
		if (!hasThisId(targetID))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		data.replace(targetID, replacement);
		if (!getIoStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<FinancialReceiptPO> getAllList() {
		return data.values().parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

}
