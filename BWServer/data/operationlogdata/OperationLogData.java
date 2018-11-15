package operationlogdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.hibernate.dialect.PostgreSQL81Dialect;

import PO.LogPO;
import date.helper.DateUtils;
import enums.ResultMessage;
import idhelper.IDHelper;
import operationlogdataService.OperationLogdataService;
import persistence.txt.service.IOStrategy;

public abstract class OperationLogData implements OperationLogdataService {
	private IOStrategy<LogPO> ioStrategy;

	private void isValidIfNotThrowIllegalArgumentException(String str) {
		if (null == str || str.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	private void isValidIfNotThrowIllegalArgumentException(Object obj) {
		if (null == obj) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public abstract ArrayList<LogPO> getAllLog();

	protected abstract ResultMessage insertHooked(LogPO po);

	@Override
	public LogPO findLogByID(String id) {
		isValidIfNotThrowIllegalArgumentException(id);
		ArrayList<LogPO> resultList = getAllLog();
		if (null == resultList || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getId().equals(id))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@Override
	public ArrayList<LogPO> findLogByUser(String userID) {
		isValidIfNotThrowIllegalArgumentException(userID);
		ArrayList<LogPO> resultList = getAllLog();
		if (null == resultList || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getUserID().equals(userID))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

	@Override
	public ArrayList<LogPO> findLogByCommetWithKeyword(String keyword) {
		isValidIfNotThrowIllegalArgumentException(keyword);
		ArrayList<LogPO> resultList = getAllLog();
		if (null == resultList || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getContent().contains(keyword))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

	@Override
	public ResultMessage insert(LogPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		return insertHooked(po);
	}

	@Override
	public ArrayList<LogPO> findLogByTime(Date startTime, Date endTime) {
		isValidIfNotThrowIllegalArgumentException(endTime);
		isValidIfNotThrowIllegalArgumentException(startTime);
		ArrayList<LogPO> resultList = getAllLog();
		if (null == resultList || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream()
				.filter(po -> po.getTime().after(startTime) && po.getTime().before(endTime))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

	protected IOStrategy<LogPO> getIoStrategy() {
		return ioStrategy;
	}

	protected void setIoStrategy(IOStrategy<LogPO> ioStrategy) {
		this.ioStrategy = ioStrategy;
	}

	@Override
	public String getIDSuffix(Date date) throws IllegalArgumentException {
		isValidIfNotThrowIllegalArgumentException(date);
		int count = 0;
		Iterator<LogPO> iterator = ioStrategy.outAll(LogPO.class);
		while (iterator.hasNext()) {
			if (DateUtils.isSameDate(date, iterator.next().getTime())) {
				count++;
			}
		}
		return IDHelper.toKBitString(count + 1, 5);
	}
}
