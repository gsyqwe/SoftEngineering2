package bankaccountdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import PO.BankAccountPO;
import bankaccountdataService.BankAccountDataService;
import date.helper.DateUtils;
import enums.ResultMessage;
import idhelper.IDHelper;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import persistence.txt.service.IOStrategy;

/**
 * 實現BankAccountDataService接口,並提供部分方法的默認實現
 * 檢查參數,詳見isValidIfNotThrowIllegalArgumentException,
 * 參數非法會抛出IllegalArgumentException()
 * 默認所有findByxxx使用getAllList實現,而getAllList為抽象方法交由具體子類實現
 * 
 * @author 161250051
 * @since 2017/12/26
 * @version 2017/12/28
 */
public abstract class BankAccountData implements BankAccountDataService {
	private IOStrategy<BankAccountPO> ioStrategy;

	/**
	 * 其它類型的參數要求
	 * 
	 * @param obj
	 */
	private void isValidIfNotThrowIllegalArgumentException(Object obj) {
		if (null == obj)
			throw new IllegalArgumentException();
	}

	/**
	 * String類型的參數要求
	 * 
	 * @param obj
	 */
	private void isValidIfNotThrowIllegalArgumentException(String str) {
		if (null == str || str.isEmpty())
			throw new IllegalArgumentException();
	}

	/**
	 * 具體的插入操作,由具體子類實現
	 * 
	 * @param po
	 * @return
	 */
	protected abstract ResultMessage insertHooked(BankAccountPO po);

	/**
	 * 具體的刪除操作,由具體子類實現
	 * 
	 * @param targetID
	 * @return
	 */
	protected abstract ResultMessage deleteHooked(String targetID);

	/**
	 * 具體的更新操作,由具體子類實現
	 * 
	 * @param targetId
	 * @param replacement
	 * @return
	 */
	protected abstract ResultMessage updateHooked(String targetId, BankAccountPO replacement);

	protected void setIOStrategy(IOStrategy<BankAccountPO> ioStrategy) {
		this.ioStrategy = ioStrategy;
	}

	protected IOStrategy<BankAccountPO> getIOStrategy() {
		return this.ioStrategy;
	}

	@Override
	public ResultMessage insert(BankAccountPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		return insertHooked(po);
	}

	@Override
	public ResultMessage delete(String targetID) {
		isValidIfNotThrowIllegalArgumentException(targetID);
		return deleteHooked(targetID);
	}

	@Override
	public ResultMessage update(String targetId, BankAccountPO replacement) {
		isValidIfNotThrowIllegalArgumentException(targetId);
		isValidIfNotThrowIllegalArgumentException(replacement);
		/*
		 * 此處應該檢查targetId == replacements.getID
		 */
		if (!replacement.getId().equals(targetId))
			throw new IllegalArgumentException("目標ID與覆蓋ID不同");
		return updateHooked(targetId, replacement);
	}

	/**
	 * 由具體子類實現
	 */
	@Override
	public abstract ArrayList<BankAccountPO> getAllList();

	@Override
	public BankAccountPO findByCardNumber(String targetCardNumber) {
		isValidIfNotThrowIllegalArgumentException(targetCardNumber);
		ArrayList<BankAccountPO> list = this.getAllList();
		if (null == list)
			return null;
		list = list.parallelStream().filter(po -> po.getCardNumber().equals(targetCardNumber))
				.collect(Collectors.toCollection(ArrayList::new));
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public BankAccountPO findByID(String targetID) {
		isValidIfNotThrowIllegalArgumentException(targetID);
		ArrayList<BankAccountPO> list = getAllList();
		if (list == null)
			return null;
		list = list.parallelStream().filter(po -> po.getId().equals(targetID))
				.collect(Collectors.toCollection(ArrayList::new));
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public String getIDSuffix(Date date) throws IllegalArgumentException {
		isValidIfNotThrowIllegalArgumentException(date);
		Iterator<BankAccountPO> iterator = ioStrategy.outAll(BankAccountPO.class);
		int count = 0;
		while (iterator.hasNext()) {
			BankAccountPO po = iterator.next();
			if (DateUtils.isSameDate(date, po.getDate())) {
				count++;
			}
		}
		return IDHelper.toKBitString(count + 1, 5);
	}

	@Override
	public BankAccountPO findByName(String name) {
		isValidIfNotThrowIllegalArgumentException(name);
		ArrayList<BankAccountPO> resultList = this.getAllList();
		if (resultList == null)
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getName().equals(name))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList.get(0);
	}

}
