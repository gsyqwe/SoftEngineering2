package financialreceiptdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import PO.FinancialReceiptPO;
import Receipt.Receipt;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;
import persistence.hibernate.service.IODataBaseExtendion;

public class FinancialReceiptDataUseDataBase extends FinancialReceiptData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		FinancialReceiptDataUseDataBase tester = new FinancialReceiptDataUseDataBase();
		tester.getAllList().forEach(po -> System.out.println(po));
	}

	public FinancialReceiptDataUseDataBase() {
		setIoStrategy(new IOStrategyUseHibernate<>());
	}

	@Override
	protected ResultMessage insertHooked(FinancialReceiptPO po) {
		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage deleteHooked(String targetID) {
		FinancialReceiptPO deleteTarget = new FinancialReceiptPO();
		deleteTarget.setId(targetID);
		if (!((IODataBaseExtendion<FinancialReceiptPO>) getIoStrategy()).deleteOne(deleteTarget)) {
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		}

		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage updateHooked(String tartgetID, FinancialReceiptPO replacement) {
		if (!getIoStrategy().replaceOne(replacement))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<FinancialReceiptPO> getAllList() {
		ArrayList<FinancialReceiptPO> reFinancialReceiptPOs = new ArrayList<>();
		Iterator<FinancialReceiptPO> iterator = getIoStrategy().outAll(FinancialReceiptPO.class);
		while (iterator.hasNext()) {
			reFinancialReceiptPOs.add(iterator.next());
		}
		return reFinancialReceiptPOs;
	}

}