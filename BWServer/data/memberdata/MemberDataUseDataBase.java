package memberdata;

import java.util.ArrayList;
import java.util.Iterator;

import PO.MemberPO;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;
import persistence.hibernate.service.IODataBaseExtendion;

public class MemberDataUseDataBase extends MemberData {

	public MemberDataUseDataBase() {
		setIoStrategy(new IOStrategyUseHibernate<>());
	}

	@Override
	protected ResultMessage deleteHooked(String memberID) {
		MemberPO deleteTarget = findByID(memberID);
		if (null == deleteTarget) {
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		}
		deleteTarget.setIsDeleted(true);
		return update(memberID, deleteTarget);
	}

	@Override
	protected ResultMessage updateHooked(String memberID, MemberPO replacement) {
		if (!getIoStrategy().replaceOne(replacement))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage insertHooked(MemberPO po) {
		/*
		 * check if po already exist in database, but it has been deleted
		 * logically
		 */
		((IODataBaseExtendion<MemberPO>) getIoStrategy()).setSearChCommand("from MemberPO po where po.id= ?");
		((IODataBaseExtendion<MemberPO>) getIoStrategy()).setParameter(po.getId());
		MemberPO findResult = getIoStrategy().outOne(MemberPO.class);
		if (findResult != null && findResult.getIsDeleted()) {
			po.setIsDeleted(false); // reset
			return update(po.getId(), po);
		}
		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<MemberPO> getAll() {
		ArrayList<MemberPO> reMemberPos = new ArrayList<>();
		Iterator<MemberPO> iterator = getIoStrategy().outAll(MemberPO.class);
		while (iterator.hasNext()) {
			MemberPO po = iterator.next();
			if (!po.getIsDeleted())
				reMemberPos.add(po);
		}
		return reMemberPos.isEmpty() ? null : reMemberPos;
	}
}