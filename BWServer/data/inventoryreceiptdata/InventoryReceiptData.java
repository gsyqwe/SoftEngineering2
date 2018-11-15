package inventoryreceiptdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

import PO.InventoryReceiptPO;
import date.helper.DateUtils;
import enums.InventoryReceiptType;
import enums.ReceiptState;
import enums.ResultMessage;
import idhelper.IDHelper;
import inventoryreceiptdataservice.InventoryReceiptDataService;
import persistence.txt.service.IOStrategy;

public abstract class InventoryReceiptData implements InventoryReceiptDataService,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IOStrategy<InventoryReceiptPO> ioStrategy;

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

	protected abstract ResultMessage insertHooked(InventoryReceiptPO po);

	protected abstract ResultMessage deleteHooked(String id);

	protected abstract ResultMessage updateHooked(String targetID, InventoryReceiptPO replacement);

	@Override
	public ResultMessage insert(InventoryReceiptPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		return insertHooked(po);
	}

	@Override
	public ResultMessage delete(String id) {
		isValidIfNotThrowIllegalArgumentException(id);
		return deleteHooked(id);
	}

	public ResultMessage update(String targetID, InventoryReceiptPO replacement) {
		isValidIfNotThrowIllegalArgumentException(targetID);
		isValidIfNotThrowIllegalArgumentException(replacement);
		/*
		 * 此處應該檢查targetId == replacements.getID
		 */
		if (!replacement.getId().equals(targetID))
			throw new IllegalArgumentException("目標ID與覆蓋ID不同");
		return updateHooked(targetID, replacement);
	}

	@Override
	public abstract ArrayList<InventoryReceiptPO> getAllList();

	@Override
	public InventoryReceiptPO findByID(String id) {
		isValidIfNotThrowIllegalArgumentException(id);
		ArrayList<InventoryReceiptPO> resultList = this.getAllList();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getId().equals(id))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@Override
	public ArrayList<InventoryReceiptPO> findByUser(String userId) {
		isValidIfNotThrowIllegalArgumentException(userId);
		ArrayList<InventoryReceiptPO> resultList = getAllList();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getOperatorID().equals(userId))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

	@Override
	public ArrayList<InventoryReceiptPO> findByDate(Date startTime, Date endTime) {
		isValidIfNotThrowIllegalArgumentException(startTime);
		isValidIfNotThrowIllegalArgumentException(endTime);
		ArrayList<InventoryReceiptPO> resultList = getAllList();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream()
				.filter(po -> po.getDate().after(startTime) && po.getDate().before(endTime))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

	@Override
	public ArrayList<InventoryReceiptPO> findByReceiptState(ReceiptState receiptState) {
		isValidIfNotThrowIllegalArgumentException(receiptState);
		ArrayList<InventoryReceiptPO> resultList = getAllList();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getState().equals(receiptState))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

	@Override
	public ArrayList<InventoryReceiptPO> findByInventoryReceiptType(InventoryReceiptType inventoryReceiptType) {
		isValidIfNotThrowIllegalArgumentException(inventoryReceiptType);
		ArrayList<InventoryReceiptPO> resulitList = this.getAllList();
		if (resulitList == null || resulitList.isEmpty())
			return null;
		resulitList = resulitList.parallelStream().filter(po -> po.getInventoryType().equals(inventoryReceiptType))
				.collect(Collectors.toCollection(ArrayList::new));
		return resulitList.isEmpty() ? null : resulitList;
	}

	protected void setIoStrategy(IOStrategy<InventoryReceiptPO> ioStrategy) {
		this.ioStrategy = ioStrategy;
	}

	@Override
	public String getIDSuffix(Date date, InventoryReceiptType inventoryReceiptType) throws IllegalArgumentException {
		Iterator<InventoryReceiptPO> iterator = ioStrategy.outAll(InventoryReceiptPO.class);
		int count = 0;
		while (iterator.hasNext()) {
			InventoryReceiptPO po = iterator.next();
			if (DateUtils.isSameDate(date, po.getDate()) && po.getInventoryType().equals(inventoryReceiptType)) {
				count++;
			}
		}
		return IDHelper.toKBitString(count + 1, 5);
	}

	protected IOStrategy<InventoryReceiptPO> getIoStrategy() {
		return ioStrategy;
	}

}
