package emailbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import VO.EmailVO;
import enums.ResultMessage;

public class EmailTest {

	public static void main(String [] args){
		EmailTest test = new EmailTest();
		//test.saveTest();
		//test.emailPOSaveTest();
		test.deleteTest();
		//test.readTest();
		//test.getEmailTest();
	}

	public void getEmailTest(){
		try {
			EmailBLController controller = new EmailBLController();
			ArrayList<EmailVO> emails = controller.getEmailByUser("MAN-001");
			for(EmailVO email:emails){
				System.out.println(email);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteTest(){
		try {
			String content = "You are a stupid ass.";
			ArrayList<String> contents = new ArrayList<>();
			contents.add(content);
			EmailBLController controller = new EmailBLController();
			@SuppressWarnings("deprecation")
			Date date = new Date(108,1,1,1,1,11);
			ResultMessage message = controller.deleteEmail(new EmailVO("MustRead",contents,"MAN-003","MAN-004",date,false));
			System.out.println(message);
		} catch (RemoteException e) {

		}
	}

	public void readTest(){
		try {
			String content = "You are a stupid ass.";
			ArrayList<String> contents = new ArrayList<>();
			contents.add(content);

			@SuppressWarnings("deprecation")
			Date date = new Date(118,1,1,1,1,11);
			EmailBLController controller = new EmailBLController();
			System.out.println(controller.readEmail(new EmailVO("MustRead",contents,"MAN-001","MAN-002",date,false)));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveTest(){
		String content = "You are a stupid ass.";
		ArrayList<String> contents = new ArrayList<>();
		contents.add(content);
		@SuppressWarnings("deprecation")
		Date date = new Date(118,1,1,1,1,11);
		EmailVO email = new EmailVO("MustRead",contents,"MAN-003","MAN-004",date,false);

		try {
			EmailBLController controller = new EmailBLController();
			ResultMessage message = controller.completeAddEmail(email);
			System.out.println(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void emailPOSaveTest(){
		String content = "You are a stupid ass.";
		ArrayList<String> contents = new ArrayList<>();
		contents.add(content);
		EmailVO email = new EmailVO("haha",contents,"MAN-001","MAN-002",new Date(),false);
		EmailForServer controller = new EmailForServer();
		System.out.println(controller.saveEmail(email));
	}

}
