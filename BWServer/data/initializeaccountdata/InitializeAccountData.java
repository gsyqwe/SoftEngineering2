package initializeaccountdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import PO.InitializeAccountPO;
import date.helper.DateUtils;
import enums.ResultMessage;
import initializeaccountdataService.InitializeAccountDataService;
import persistence.txt.service.IOStrategy;

public abstract class InitializeAccountData implements InitializeAccountDataService {
	private IOStrategy<InitializeAccountPO> ioStrategy;

	private void isValidIfNotThrowIllegalArgumentException(Object obj) {
		if (null == obj)
			throw new IllegalArgumentException();
	}

	protected abstract ResultMessage insertHooked(InitializeAccountPO po);

	@Override
	public ResultMessage insert(InitializeAccountPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		if (null == po.getBankAccountPOList() || po.getBankAccountPOList().isEmpty())
			return ResultMessage.INITIALIZE_ACCOUNT_FAIL_EMPTY_BANK_ACCOUNT_ADDED;
		else if (null == po.getCommodityPOList() || po.getCommodityPOList().isEmpty())
			return ResultMessage.INITIALIZE_ACCOUNT_FAIL_EMPTY_COMMODITY_ADDED;
		else if (null == po.getMemberPOList() || po.getMemberPOList().isEmpty())
			return ResultMessage.INITIALIZE_ACCOUNT_FAIL_EMPTY_MEMBER_ADDED;
		return insertHooked(po);
	}

	@Override
	public ArrayList<InitializeAccountPO> findByTime(Date startTime, Date endTime) {
		isValidIfNotThrowIllegalArgumentException(startTime);
		isValidIfNotThrowIllegalArgumentException(endTime);
		return this.getAllList().parallelStream()
				.filter(po -> po.getDate().after(startTime) && po.getDate().before(endTime))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public InitializeAccountPO findByDate(Date date) {
		isValidIfNotThrowIllegalArgumentException(date);
		List<InitializeAccountPO> reList = this.getAllList().parallelStream()
				.filter(po -> DateUtils.isSameDate(date, po.getDate()))
				.collect(Collectors.toCollection(ArrayList::new));
		return reList.isEmpty() ? null : reList.get(0); // 返回第一個
	}

	protected void setIoStrategy(IOStrategy<InitializeAccountPO> ioStrategy) {
		this.ioStrategy = ioStrategy;
	}

	protected IOStrategy<InitializeAccountPO> getIoStrategy() {
		return ioStrategy;
	}

	@Override
	public abstract List<InitializeAccountPO> getAllList();

}
