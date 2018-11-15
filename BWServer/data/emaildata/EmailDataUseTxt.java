package emaildata;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import PO.EmailPO;
import date.helper.DateUtils;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;

public class EmailDataUseTxt extends EmailData {
	private static Set<EmailPO> data = new HashSet<>();
	private static final String txtName = "EmailPO";

	public EmailDataUseTxt() {
		setIoStrategy(new IOStrategyUseTxt<>(txtName));
		backUpFromFile();
	}

	private void backUpFromFile() {
		Iterator<EmailPO> iterator = getIoStrategy().outAll(EmailPO.class);
		while (iterator.hasNext()) {
			EmailPO po = iterator.next();
			data.add(po);
		}
	}

	@Override
	protected ResultMessage insertHooked(EmailPO email) {
		if (!getIoStrategy().inOne(email))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		data.add(email);
		return ResultMessage.RECORD_INSERT_SUCCESS;
	}

	@Override
	protected ResultMessage deleteHooked(String receiverID, Date date) {
		List<EmailPO> list = data.parallelStream()
				.filter(po -> po.getReceiverID().equals(receiverID) && DateUtils.isSameDate(date, po.getDate()))
				.collect(Collectors.toCollection(ArrayList::new));
		if (list.size() > 1)
			return ResultMessage.DATA_OPERATE_FAIL_NOT_UNIQUE;
		data.remove(list.get(0));
		if (!getIoStrategy().replaceAll(data.iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage updateHooked(String receiverID, Date date, EmailPO email) {
		List<EmailPO> list = data.parallelStream()
				.filter(po -> po.getReceiverID().equals(receiverID) && DateUtils.isSameDate(date, po.getDate()))
				.collect(Collectors.toCollection(ArrayList::new));
		if (list.size() > 1)
			return ResultMessage.DATA_OPERATE_FAIL_NOT_UNIQUE;
		data.remove(list.get(0));
		data.add(email);
		if (!getIoStrategy().replaceAll(data.iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ArrayList<EmailPO> getAllList() {
		return data.parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

}
