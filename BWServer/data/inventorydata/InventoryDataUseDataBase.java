package inventorydata;

import java.util.ArrayList;
import java.util.Iterator;

import PO.InventoryPO;
import PO.RecordPO;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;

public class InventoryDataUseDataBase extends InventoryData {
	public static void main(String[] args) {
		InventoryDataUseDataBase tester = new InventoryDataUseDataBase();
		// tester.getAllInventory().forEach(po->System.out.println(po));
		tester.getAllRecord().forEach(po -> System.out.println(po));
	}

	public InventoryDataUseDataBase() {
		setIoStrategyOfInventory(new IOStrategyUseHibernate<>());
		setIoStrategyOfRecord(new IOStrategyUseHibernate<>());
	}

	@Override
	protected ArrayList<RecordPO> getAllRecord() {
		ArrayList<RecordPO> recordPOs = new ArrayList<>();
		Iterator<RecordPO> iterator = getIoStrategyOfRecord().outAll(RecordPO.class);
		while (iterator.hasNext()) {
			recordPOs.add(iterator.next());
		}
		return recordPOs;
	}

	@Override
	protected ResultMessage insertHooked(RecordPO po) {
		if (!getIoStrategyOfRecord().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage insertHooked(InventoryPO po) {
		if (!getIoStrategyOfInventory().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;

		return ResultMessage.SUCCESS;
	}

}
