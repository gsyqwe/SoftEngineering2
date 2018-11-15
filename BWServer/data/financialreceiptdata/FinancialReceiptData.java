package financialreceiptdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

import PO.FinancialReceiptPO;
import date.helper.DateUtils;
import enums.FinancialReceiptType;
import enums.ReceiptState;
import enums.ResultMessage;
import financialreceiptdataservice.FinancialReceiptDataService;
import idhelper.IDHelper;
import persistence.txt.service.IOStrategy;

public abstract class FinancialReceiptData implements FinancialReceiptDataService,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IOStrategy<FinancialReceiptPO> ioStrategy;

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

	protected IOStrategy<FinancialReceiptPO> getIoStrategy() {
		return ioStrategy;
	}

	protected void setIoStrategy(IOStrategy<FinancialReceiptPO> ioStrategy) {
		this.ioStrategy = ioStrategy;
	}

	protected abstract ResultMessage insertHooked(FinancialReceiptPO po);

	protected abstract ResultMessage deleteHooked(String targetID);

	protected abstract ResultMessage updateHooked(String tartgetID, FinancialReceiptPO replacement);

	@Override
	public ResultMessage insert(FinancialReceiptPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		return insertHooked(po);
	}

	@Override
	public ResultMessage delete(String targetID) {
		isValidIfNotThrowIllegalArgumentException(targetID);
		return deleteHooked(targetID);
	}

	@Override
	public ResultMessage update(String targetId, FinancialReceiptPO replacement) {
		isValidIfNotThrowIllegalArgumentException(targetId);
		isValidIfNotThrowIllegalArgumentException(replacement);
		return updateHooked(targetId, replacement);
	}

	@Override
	public abstract ArrayList<FinancialReceiptPO> getAllList();

	@Override
	public FinancialReceiptPO findByID(String targetID) {
		isValidIfNotThrowIllegalArgumentException(targetID);
		ArrayList<FinancialReceiptPO> resultList = this.getAllList();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getId().equals(targetID))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@Override
	public ArrayList<FinancialReceiptPO> findByUser(String userID) {
		isValidIfNotThrowIllegalArgumentException(userID);
		ArrayList<FinancialReceiptPO> resultList = this.getAllList();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getOperatorID().equals(userID))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

	@Override
	public ArrayList<FinancialReceiptPO> findByFinancialReceipType(FinancialReceiptType financialReceiptType) {
		isValidIfNotThrowIllegalArgumentException(financialReceiptType);
		ArrayList<FinancialReceiptPO> resultList = this.getAllList();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getFinancialReceiptType().equals(financialReceiptType))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

	@Override
	public ArrayList<FinancialReceiptPO> findByDate(Date startTime, Date endTime) {
		isValidIfNotThrowIllegalArgumentException(endTime);
		isValidIfNotThrowIllegalArgumentException(startTime);
		ArrayList<FinancialReceiptPO> resultList = this.getAllList();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream()
				.filter(po -> po.getDate().after(startTime) && po.getDate().before(endTime))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

	@Override
	public ArrayList<FinancialReceiptPO> findByReceiptState(ReceiptState receiptState) {
		isValidIfNotThrowIllegalArgumentException(receiptState);
		ArrayList<FinancialReceiptPO> resultList = this.getAllList();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getState().equals(receiptState))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

	@Override
	public String getIDSuffix(Date date, FinancialReceiptType financialReceiptType) throws IllegalArgumentException {
		isValidIfNotThrowIllegalArgumentException(date);
		isValidIfNotThrowIllegalArgumentException(financialReceiptType);
		Iterator<FinancialReceiptPO> iterator = ioStrategy.outAll(FinancialReceiptPO.class);
		int count = 0;
		while (iterator.hasNext()) {
			FinancialReceiptPO po = iterator.next();
			if (DateUtils.isSameDate(date, po.getDate()) && po.getFinancialReceiptType().equals(financialReceiptType)) {
				count++;
			}
		}
		return IDHelper.toKBitString(count + 1, 5);
	}

	@Override
	public ArrayList<FinancialReceiptPO> findByMember(String memberID) {
		isValidIfNotThrowIllegalArgumentException(memberID);
		ArrayList<FinancialReceiptPO> resultList = this.getAllList();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getMemberID().equals(memberID))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

}
