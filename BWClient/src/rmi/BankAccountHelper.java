package rmi;

import java.rmi.Remote;
import service.BankAccountBLService;

public class BankAccountHelper {
	private Remote remote;
	private static BankAccountHelper bankHelper;
	public static BankAccountHelper getInstance(){
		if(bankHelper==null)
			bankHelper=new BankAccountHelper();
		return bankHelper;
	}
	public static BankAccountHelper getInstance(Remote r){
		if(bankHelper==null)
			bankHelper=new BankAccountHelper(r);
		return bankHelper;
	}
	
	private  BankAccountHelper(){
	}
	private BankAccountHelper(Remote r){
		this.remote=r;
	}

	
	public BankAccountBLService getService(){
		return (BankAccountBLService) remote;
	}
}
