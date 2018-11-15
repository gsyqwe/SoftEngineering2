package userdata;

import java.util.ArrayList;
import java.util.Iterator;

import PO.BankAccountPO;
import PO.UserPO;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;
import persistence.hibernate.service.IODataBaseExtendion;

/**
 * 使用數據庫保存數據 策略模式:使用IODataBaseExtention, 該接口繼承IOStrategy
 * 
 * @author 161250051
 * @since 2017/11/19
 * @version 2018/01/05
 */
public class UserDataUseDataBase extends UserData {
	public UserDataUseDataBase() {
		// IOStrategyUseHibernate implement IODatabaseExendtion
		// IODatabaseExtendtion extends IOStrategy
		setIoStrategy(new IOStrategyUseHibernate<>());
	}

	@Override
	public ArrayList<UserPO> getAllList() {
		ArrayList<UserPO> reUserPos = new ArrayList<>();
		Iterator<UserPO> iterator = getIoStrategy().outAll(UserPO.class);
		while (iterator.hasNext()) {
			UserPO po = iterator.next();
			// isDeleted = true means it has been deleted logically
			if (!po.getIsDeleted())
				reUserPos.add(po);
		}
		return reUserPos.isEmpty() ? null : reUserPos;
	}

	@Override
	protected ResultMessage insertHooked(UserPO po) {
		((IODataBaseExtendion<UserPO>) getIoStrategy()).setSearChCommand("from UserPO po where po.id= ?");
		((IODataBaseExtendion<UserPO>) getIoStrategy()).setParameter(po.getId());
		UserPO findResult = getIoStrategy().outOne(UserPO.class);
		if (findResult != null && findResult.getIsDeleted()) {
			po.setIsDeleted(false);
			return update(po.getId(), po);
		}
		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage deleteHooked(String targetID) {
		UserPO deleteTarget = findByID(targetID);
		if (deleteTarget == null)
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		deleteTarget.setIsDeleted(true); // logical delete
		return update(targetID, deleteTarget);
	}

	@Override
	protected ResultMessage updateHooked(String targetID, UserPO replacement) {
		if (!getIoStrategy().replaceOne(replacement))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		return ResultMessage.SUCCESS;
	}
}