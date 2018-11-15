package operationlogdata;

import java.util.ArrayList;
import java.util.Iterator;

import PO.LogPO;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;

public class OperationLogDataUseDataBase extends OperationLogData {

	public OperationLogDataUseDataBase() {
		setIoStrategy(new IOStrategyUseHibernate<>());
	}

	@Override
	public ArrayList<LogPO> getAllLog() {
		ArrayList<LogPO> reLogPOs = new ArrayList<>();
		Iterator<LogPO> iterator = getIoStrategy().outAll(LogPO.class);
		while (iterator.hasNext()) {
			reLogPOs.add(iterator.next());
		}
		return reLogPOs.isEmpty() ? null : reLogPOs;
	}

	@Override
	protected ResultMessage insertHooked(LogPO po) {
		if (!getIoStrategy().inOne(po)) {
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		}
		return ResultMessage.SUCCESS;
	}

}
