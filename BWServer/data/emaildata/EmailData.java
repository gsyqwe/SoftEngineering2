package emaildata;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import PO.EmailPO;
import emaildataservice.EmailDataService;
import enums.ResultMessage;
import persistence.txt.service.IOStrategy;

public abstract class EmailData implements EmailDataService {
	private IOStrategy<EmailPO> ioStrategy;

	protected void setIoStrategy(IOStrategy<EmailPO> ioStrategy) {
		this.ioStrategy = ioStrategy;
	}

	protected IOStrategy<EmailPO> getIoStrategy() {
		return ioStrategy;
	}

	private void isValidIfNotThrowIllegalArgumentException(String str) {
		if (null == str || str.isEmpty())
			throw new IllegalArgumentException();
	}

	private void isValidIfNotThrowIllegalArgumentException(Object obj) {
		if (null == obj)
			throw new IllegalArgumentException();
	}

	protected abstract ResultMessage insertHooked(EmailPO email);

	protected abstract ResultMessage deleteHooked(String receiverID, Date date);

	protected abstract ResultMessage updateHooked(String receiverID, Date date, EmailPO email);

	protected abstract ArrayList<EmailPO> getAllList();

	@Override
	public ResultMessage insertEmail(EmailPO email) {
		isValidIfNotThrowIllegalArgumentException(email);
		return insertHooked(email);
	}

	@Override
	public ArrayList<EmailPO> getEmailByUser(String receiverID) {
		isValidIfNotThrowIllegalArgumentException(receiverID);
		ArrayList<EmailPO> resultList = getAllList();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream().filter(po -> po.getReceiverID().equals(receiverID))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

	@Override
	public ResultMessage deleteEmail(String receiverID, Date date) {
		isValidIfNotThrowIllegalArgumentException(receiverID);
		isValidIfNotThrowIllegalArgumentException(date);
		return deleteHooked(receiverID, date);
	}

	@Override
	public ResultMessage updateEmail(String receiverID, Date date, EmailPO email) {
		isValidIfNotThrowIllegalArgumentException(receiverID);
		isValidIfNotThrowIllegalArgumentException(date);
		isValidIfNotThrowIllegalArgumentException(email);
		return updateHooked(receiverID, date, email);
	}

}
