package inventoryreceiptdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import PO.InventoryReceiptPO;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;
import persistence.hibernate.service.IODataBaseExtendion;

public class InventoryReceiptDataUseDataBase extends InventoryReceiptData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InventoryReceiptDataUseDataBase() {
		setIoStrategy(new IOStrategyUseHibernate<>());
	}

	@Override
	public ArrayList<InventoryReceiptPO> getAllList() {
		ArrayList<InventoryReceiptPO> receiptPOs = new ArrayList<>();
		Iterator<InventoryReceiptPO> iterator = getIoStrategy().outAll(InventoryReceiptPO.class);
		while (iterator.hasNext())
			receiptPOs.add(iterator.next());
		return receiptPOs;
	}

	@Override
	protected ResultMessage insertHooked(InventoryReceiptPO po) {
		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage deleteHooked(String id) {
		InventoryReceiptPO deleteTarget = new InventoryReceiptPO();
		deleteTarget.setId(id);
		if (!((IODataBaseExtendion<InventoryReceiptPO>) getIoStrategy()).deleteOne(deleteTarget))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage updateHooked(String targetID, InventoryReceiptPO replacement) {
		if (!getIoStrategy().replaceOne(replacement))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		return ResultMessage.SUCCESS;
	}

}