package inventorydata;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import PO.InventoryPO;
import PO.RecordPO;
import enums.ResultMessage;
import inventorydataService.InventoryDataService;
import persistence.txt.service.IOStrategy;

public abstract class InventoryData implements InventoryDataService {
	private IOStrategy<InventoryPO> ioStrategyOfInventory;
	private IOStrategy<RecordPO> ioStrategyOfRecord;

	private void isValidIfNotThrowIllegalArgumentException(Object obj) {
		if (null == obj)
			throw new IllegalArgumentException();
	}

	protected abstract ArrayList<RecordPO> getAllRecord();

	// protected abstract ArrayList<InventoryPO> getAllInventory();

	protected abstract ResultMessage insertHooked(RecordPO po);

	protected abstract ResultMessage insertHooked(InventoryPO po);

	@Override
	public ArrayList<RecordPO> findByTime(Date startTime, Date endTime) {
		isValidIfNotThrowIllegalArgumentException(startTime);
		isValidIfNotThrowIllegalArgumentException(endTime);

		return getAllRecord().parallelStream()
				.filter(po -> po.getStockInAndOutTime().after(startTime) && po.getStockInAndOutTime().before(endTime))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	protected void setIoStrategyOfInventory(IOStrategy<InventoryPO> ioStrategyOfInventory) {
		this.ioStrategyOfInventory = ioStrategyOfInventory;
	}

	protected void setIoStrategyOfRecord(IOStrategy<RecordPO> ioStrategyOfRecord) {
		this.ioStrategyOfRecord = ioStrategyOfRecord;
	}

	protected IOStrategy<InventoryPO> getIoStrategyOfInventory() {
		return ioStrategyOfInventory;
	}

	protected IOStrategy<RecordPO> getIoStrategyOfRecord() {
		return ioStrategyOfRecord;
	}

	@Override
	public ResultMessage insert(InventoryPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		return insertHooked(po);
	}

	@Override
	public ResultMessage insert(RecordPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		return insertHooked(po);
	}
}
