package inventorydata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import PO.InventoryPO;
import PO.RecordPO;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;
import persistence.txt.service.IOStrategy;

public class InventoryDataUseTxt extends InventoryData {
	private static Set<InventoryPO> inventoryPOs = new HashSet<InventoryPO>();
	private static Set<RecordPO> recordPOs = new HashSet<RecordPO>();
	private static final String txtNameOfRecord = "RecordPO";
	private static final String txtNameOfInventory = "InventoryPO";

	public InventoryDataUseTxt() {
		setIoStrategyOfInventory(new IOStrategyUseTxt<>(txtNameOfInventory));
		setIoStrategyOfRecord(new IOStrategyUseTxt<>(txtNameOfRecord));
		backUpFormTxt(inventoryPOs, getIoStrategyOfInventory(), InventoryPO.class);
		backUpFormTxt(recordPOs, getIoStrategyOfRecord(), RecordPO.class);
	}

	protected static String getTxtnameofrecord() {
		return txtNameOfRecord;
	}

	protected static String getTxtnameofinventory() {
		return txtNameOfInventory;
	}

	private <T> void backUpFormTxt(Collection<T> data, IOStrategy<T> ioStrategy, Class clazz) {
		Iterator<T> iterator = ioStrategy.outAll(clazz);
		while (iterator.hasNext()) {
			T po = iterator.next();
			data.add(po);
		}
	}

	@Override
	protected ArrayList<RecordPO> getAllRecord() {
		return recordPOs.parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected ResultMessage insertHooked(RecordPO po) {
		if (!getIoStrategyOfRecord().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		recordPOs.add(po);
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage insertHooked(InventoryPO po) {
		if (!getIoStrategyOfInventory().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		inventoryPOs.add(po);
		return ResultMessage.SUCCESS;
	}

}
