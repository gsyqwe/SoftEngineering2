package rmi;

import java.rmi.Remote;

import service.EmailBLService;

public class EmailHelper {
	private Remote remote;
	private static EmailHelper emailHelper;
	public static EmailHelper getInstance(){
		if(emailHelper==null)
			emailHelper=new EmailHelper();
		return emailHelper;
	}
	public static EmailHelper getInstance(Remote r){
		if(emailHelper==null)
			emailHelper=new EmailHelper(r);
		return emailHelper;
	}

	private  EmailHelper() {
	}
	private  EmailHelper(Remote r) {
		this.remote=r;
	}

	public EmailBLService getService(){
		return (EmailBLService) remote;
	}
}
