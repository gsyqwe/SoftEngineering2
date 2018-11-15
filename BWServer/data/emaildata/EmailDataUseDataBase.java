package emaildata;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import PO.EmailPO;
import date.helper.DateUtils;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;

public class EmailDataUseDataBase extends EmailData {
	public EmailDataUseDataBase() {
		setIoStrategy(new IOStrategyUseHibernate<>());
	}

	@Override
	protected ResultMessage insertHooked(EmailPO email) {
		if (!getIoStrategy().inOne(email))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage deleteHooked(String receiverID, Date date) {
		List<EmailPO> list = getAllList().parallelStream()
				.filter(po -> po.getReceiverID().equals(receiverID) && DateUtils.isSameSec(date, po.getDate()))
				.collect(Collectors.toCollection(ArrayList::new));
		if (list.size() > 1)
			return ResultMessage.DATA_OPERATE_FAIL_NOT_UNIQUE;
		if (list.size() == 0)
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		list.get(0).setIsDeleted(true);
		return updateEmail(receiverID, date, list.get(0));
	}

	@Override
	protected ResultMessage updateHooked(String receiverID, Date date, EmailPO email) {
		List<EmailPO> list = getAllList().parallelStream()
				.filter(po -> po.getReceiverID().equals(receiverID) && DateUtils.isSameSec(date, po.getDate()))
				.collect(Collectors.toCollection(ArrayList::new));
		if (list.size() > 1)
			return ResultMessage.DATA_OPERATE_FAIL_NOT_UNIQUE;
		if (list.size() == 0)
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		email.setId(list.get(0).getId());
		if (!getIoStrategy().replaceOne(email))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ArrayList<EmailPO> getAllList() {
		ArrayList<EmailPO> recordPOs = new ArrayList<>();
		Iterator<EmailPO> iterator = getIoStrategy().outAll(EmailPO.class);
		while (iterator.hasNext()) {
			EmailPO po = iterator.next();
			if (!po.getIsDeleted())
				recordPOs.add(po);
		}
		return recordPOs.isEmpty() ? null : recordPOs;
	}
}
