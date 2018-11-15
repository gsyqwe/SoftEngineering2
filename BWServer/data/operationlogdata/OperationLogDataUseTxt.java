package operationlogdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import PO.LogPO;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;

public class OperationLogDataUseTxt extends OperationLogData {
	private static Map<String, LogPO> data = new HashMap<String, LogPO>();
	private static final String txtName = "LogPO";

	public OperationLogDataUseTxt() {
		setIoStrategy(new IOStrategyUseTxt<>(txtName));
		backUpFormFile();
	}

	private void backUpFormFile() {
		Iterator<LogPO> iterator = (Iterator<LogPO>) getIoStrategy().outAll(LogPO.class);
		while (iterator.hasNext()) {
			LogPO po = iterator.next();
			data.put(po.getId(), po);
		}
	}

	@Override
	public ArrayList<LogPO> getAllLog() {
		return data.values().parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected ResultMessage insertHooked(LogPO po) {
		if (hasThisId(po.getId()))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;

		data.put(po.getId(), po);

		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;

		return ResultMessage.SUCCESS;
	}

	private Boolean hasThisId(String id) {
		return data.containsKey(id);
	}
}