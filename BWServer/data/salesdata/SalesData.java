package salesdata;

import idhelper.IDHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

import persistence.txt.service.IOStrategy;
import salesdataService.SalesDataService;
import PO.SalesReceiptPO;
import date.helper.DateUtils;
import enums.ReceiptState;
import enums.ResultMessage;
import enums.SalesReceiptType;

/**
 * 實現SalesDataService接口,並提供部分方法的默認實現
 * 檢查參數,詳見isValidIfNotThrowIllegalArgumentException,
 * 參數非法會抛出IllegalArgumentException()
 * 默認所有findByxxx使用getAllList實現,而getAllList為抽象方法交由具體子類實現
 * 
 * @author 161250051
 * @since 2017/11/01
 * @version 2017/12/29
 */
public abstract class SalesData implements SalesDataService,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 持久化策略
	 */
	private IOStrategy<SalesReceiptPO> ioStrategy;

	/**
	 * String類型的參數要求
	 * 
	 * @param str
	 */
	private void isValidIfNotThrowIllegalArgumentException(String str) {
		if (null == str || str.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 其它類型的參數要求
	 * 
	 * @param obj
	 */
	private void isValidIfNotThrowIllegalArgumentException(Object obj) {
		if (null == obj) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public SalesReceiptPO findByID(String id) {
		isValidIfNotThrowIllegalArgumentException(id);
		ArrayList<SalesReceiptPO> findResult = this.getAllList();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getId().equals(id))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult.get(0);
	}

	@Override
	public ArrayList<SalesReceiptPO> findByTime(Date beginTime, Date endTime) {
		isValidIfNotThrowIllegalArgumentException(beginTime);
		isValidIfNotThrowIllegalArgumentException(endTime);
		ArrayList<SalesReceiptPO> findResult = this.getAllList();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream()
				.filter(po -> po.getDate().after(beginTime) && po.getDate().before(endTime))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult;
	}

	protected abstract ResultMessage insertHooked(SalesReceiptPO po);

	@Override
	public ResultMessage insert(SalesReceiptPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		return insertHooked(po);
	}

	protected abstract ResultMessage updateHooked(String targetID, SalesReceiptPO replacement);

	@Override
	public ResultMessage update(String targetID, SalesReceiptPO replacement) {
		isValidIfNotThrowIllegalArgumentException(targetID);
		isValidIfNotThrowIllegalArgumentException(replacement);
		/*
		 * 此處應該檢查targetId == replacements.getID
		 */
		if (!replacement.getId().equals(targetID))
			throw new IllegalArgumentException("目標ID與覆蓋ID不同");
		return updateHooked(targetID, replacement);
	}

	protected abstract ResultMessage deleteHooked(String id);

	@Override
	public ResultMessage delete(String id) {
		isValidIfNotThrowIllegalArgumentException(id);
		return deleteHooked(id);
	}

	/**
	 * 取得持久化策略
	 * 
	 * @return
	 */
	protected IOStrategy<SalesReceiptPO> getIoStrategy() {
		return ioStrategy;
	}

	/**
	 * 設定持久化策略
	 * 
	 * @param ioStrategy
	 */
	protected void setIoStrategy(IOStrategy<SalesReceiptPO> ioStrategy) {
		this.ioStrategy = ioStrategy;
	}

	@Override
	public ArrayList<SalesReceiptPO> findByUser(String userID) {
		isValidIfNotThrowIllegalArgumentException(userID);
		ArrayList<SalesReceiptPO> findResult = this.getAllList();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getOperatorID().equals(userID))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult;
	}

	@Override
	public ArrayList<SalesReceiptPO> findByDate(Date startTime, Date endTime) {
		isValidIfNotThrowIllegalArgumentException(endTime);
		isValidIfNotThrowIllegalArgumentException(endTime);
		ArrayList<SalesReceiptPO> findResult = this.getAllList();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream()
				.filter(po -> po.getDate().after(startTime) && po.getDate().before(endTime))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult;
	}

	@Override
	public ArrayList<SalesReceiptPO> findByReceiptState(ReceiptState receiptState) {
		isValidIfNotThrowIllegalArgumentException(receiptState);
		ArrayList<SalesReceiptPO> findResult = this.getAllList();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getState().equals(receiptState))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult;
	}

	@Override
	public ArrayList<SalesReceiptPO> findByMember(String memberID) {
		isValidIfNotThrowIllegalArgumentException(memberID);
		ArrayList<SalesReceiptPO> findResult = this.getAllList();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getMemberID().equals(memberID))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult;
	}

	@Override
	public ArrayList<SalesReceiptPO> findByRepository(String repositoryName) {
		ArrayList<SalesReceiptPO> findResult = this.getAllList();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getRepositoryName().equals(repositoryName))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult;
	}

	@Override
	public abstract ArrayList<SalesReceiptPO> getAllList();

	@Override
	public String getIDSuffix(Date date, SalesReceiptType salesReceiptType) throws IllegalArgumentException {
		isValidIfNotThrowIllegalArgumentException(date);
		isValidIfNotThrowIllegalArgumentException(salesReceiptType);
		Iterator<SalesReceiptPO> iterator = ioStrategy.outAll(SalesReceiptPO.class);
		int count = 0;
		while (iterator.hasNext()) {
			SalesReceiptPO po = iterator.next();
			if (DateUtils.isSameDate(date, po.getDate()) && po.getSalesType().equals(salesReceiptType)) {
				count++;
			}
		}
		return IDHelper.toKBitString(count + 1, 5);
	}

	@Override
	public ArrayList<SalesReceiptPO> findBySalesType(SalesReceiptType type) {
		isValidIfNotThrowIllegalArgumentException(type);
		ArrayList<SalesReceiptPO> findResult = this.getAllList();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getSalesType().equals(type))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult;
	}

}
