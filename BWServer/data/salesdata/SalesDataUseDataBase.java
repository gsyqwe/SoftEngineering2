package salesdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import persistence.hibernate.implement.IOStrategyUseHibernate;
import persistence.hibernate.service.IODataBaseExtendion;
import PO.SalesReceiptPO;
import enums.ResultMessage;

public class SalesDataUseDataBase extends SalesData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SalesDataUseDataBase tester = new SalesDataUseDataBase();
		tester.getAllList().forEach(po -> System.out.println(po));
	}

	public SalesDataUseDataBase() {
		setIoStrategy(new IOStrategyUseHibernate<>());
	}

	@Override
	public ArrayList<SalesReceiptPO> getAllList() {
		ArrayList<SalesReceiptPO> receiptPOs = new ArrayList<>();
		Iterator<SalesReceiptPO> iterator = getIoStrategy().outAll(SalesReceiptPO.class);
		while (iterator.hasNext())
			receiptPOs.add(iterator.next());
		return receiptPOs;
	}

	@Override
	protected ResultMessage insertHooked(SalesReceiptPO po) {
		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage updateHooked(String targetID, SalesReceiptPO replacement) {
		if (!getIoStrategy().replaceOne(replacement))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage deleteHooked(String id) {
		SalesReceiptPO deleteTarget = new SalesReceiptPO();
		deleteTarget.setId(id);
		if (!((IODataBaseExtendion<SalesReceiptPO>) getIoStrategy()).deleteOne(deleteTarget))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		return ResultMessage.SUCCESS;
	}

}