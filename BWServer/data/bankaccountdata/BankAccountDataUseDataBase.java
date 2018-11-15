package bankaccountdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.hibernate.transform.ResultTransformer;

import PO.BankAccountPO;
import PO.FinancialReceiptPO;
import date.helper.DateUtils;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;
import persistence.hibernate.service.IODataBaseExtendion;

/**
 * 使用數據庫保存數據 策略模式:使用IODataBaseExtention, 該接口繼承IOStrategy
 * 
 * @author 161250051
 * @since 2017/11/19
 * @version 2017/12/28
 */
public class BankAccountDataUseDataBase extends BankAccountData {

	public BankAccountDataUseDataBase() {
		// IOStrategyUseHibernate implements IODataBaseExtendtion
		// IODataBaseExtendtion extends IOStrategy
		setIOStrategy(new IOStrategyUseHibernate<BankAccountPO>());
	}

	/**
	 * 往數據庫插入一條數據 若插入失敗,則表示數據庫中已有具體相同的ID
	 */
	@Override
	protected ResultMessage insertHooked(BankAccountPO po) {
		((IODataBaseExtendion<BankAccountPO>) getIOStrategy())
				.setSearChCommand("from BankAccountPO bankAccountPO where bankAccountPO.id= ?");
		((IODataBaseExtendion<BankAccountPO>) getIOStrategy()).setParameter(po.getId());
		BankAccountPO findResult = getIOStrategy().outOne(BankAccountPO.class);
		if (findResult != null && findResult.getIsDeleted()) {
			po.setIsDeleted(false);
			return update(po.getId(), po);
		}
		if (!getIOStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		return ResultMessage.SUCCESS;
	}

	/**
	 * 在數據庫中假刪除一條數據 若刪除失敗,則表示數據庫中找不到對應ID的數據 deleteOne 是IODataBaseExtendion的方法
	 * 使用targetID創建一個只有id的BankAccountPO
	 */
	@Override
	protected ResultMessage deleteHooked(String targetID) {
		BankAccountPO deleteTarget = findByID(targetID);
		if (deleteTarget == null)
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		deleteTarget.setIsDeleted(true);
		return update(targetID, deleteTarget);
	}

	/**
	 * 在數據庫更新一條數據, 若更新失敗,則表示數據庫中找不到對應ID的數據 僅使用replacement
	 */
	@Override
	protected ResultMessage updateHooked(String targetId, BankAccountPO replacement) {
		if (!getIOStrategy().replaceOne(replacement))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		return ResultMessage.SUCCESS;
	}

	/**
	 * 從數據庫讀出所有數據 不保證順序性不變
	 */
	@Override
	public ArrayList<BankAccountPO> getAllList() {
		ArrayList<BankAccountPO> reBankAccountPOs = new ArrayList<>();
		Iterator<BankAccountPO> iterator = getIOStrategy().outAll(BankAccountPO.class);
		while (iterator.hasNext()) {
			BankAccountPO po = iterator.next();
			if (!po.getIsDeleted())
				reBankAccountPOs.add(po);
		}
		return reBankAccountPOs.isEmpty() ? null : reBankAccountPOs;
	}

}