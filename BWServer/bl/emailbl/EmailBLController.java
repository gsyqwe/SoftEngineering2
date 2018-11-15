package emailbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;

import PO.EmailPO;
import VO.EmailVO;
import VO.UserVO;
import emaildata.EmailDataUseDataBase;
import emaildataservice.EmailDataService;
import enums.ResultMessage;
import service.EmailBLService;

public class EmailBLController extends UnicastRemoteObject implements EmailBLService {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public EmailBLController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	EmailDataService dataService = new EmailDataUseDataBase();

	@Override
	public ArrayList<EmailVO> getEmailByUser(String receiverID) throws RemoteException {
		ArrayList<EmailPO> emails = dataService.getEmailByUser(receiverID);
		ArrayList<EmailVO> result = new ArrayList<>();

		for (EmailPO email : emails) {
			result.add(email.toVO());
		}

		Collections.sort(result);// 根据已读和未读来排序，未读的在前面，都未读的时间晚的在前面

		return result;
	}

	@Override
	public ResultMessage completeAddEmail(EmailVO email) throws RemoteException {
		return dataService.insertEmail(email.toPO());
	}

	@Override
	public ArrayList<UserVO> getAllUserExcept(String userID) throws RemoteException {// 得到除了当前用户以外的用户
		return new ArrayList<>();
	}

	@Override
	public ArrayList<EmailVO> getEmailByState(String receiverID, boolean isRead) throws RemoteException {
		ArrayList<EmailPO> emails = dataService.getEmailByUser(receiverID);
		ArrayList<EmailVO> result = new ArrayList<>();

		for (EmailPO email : emails) {
			if (email.getIsReaded() == isRead) {
				result.add(email.toVO());
			}
		}

		Collections.sort(result);
		return result;
	}

	@Override
	public ResultMessage readEmail(EmailVO email) throws RemoteException {
		// TODO Auto-generated method stub
		EmailPO emailPO = email.toPO();
		if(emailPO.getIsReaded() == false){
			emailPO.setIsReaded(true);
			return dataService.updateEmail(emailPO.getReceiverID(), emailPO.getDate(), emailPO);
		}

		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deleteEmail(EmailVO email) throws RemoteException{
		ResultMessage message = dataService.deleteEmail(email.getReceiverID(), email.getDate());
		return message;
	}

	@Override
	public ResultMessage deleteEmailByList(ArrayList<EmailVO> emails) throws RemoteException{
		for(EmailVO email:emails){
			ResultMessage message = dataService.deleteEmail(email.getReceiverID(), email.getDate());
			if(message != ResultMessage.SUCCESS){
				return message;
			}
		}

		return ResultMessage.SUCCESS;
	}

}
