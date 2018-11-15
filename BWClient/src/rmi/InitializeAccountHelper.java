package rmi;

import java.rmi.Remote;
import service.InitializeAccountBLService;

public class InitializeAccountHelper {
	private Remote remote;
	private static InitializeAccountHelper initializeAccountHelper;
	public static InitializeAccountHelper getInstance(){
		if(initializeAccountHelper==null)
			initializeAccountHelper=new InitializeAccountHelper();
		return initializeAccountHelper;
	}
	public static InitializeAccountHelper getInstance(Remote r){
		if(initializeAccountHelper==null)
			initializeAccountHelper=new InitializeAccountHelper(r);
		return initializeAccountHelper;
	}
	
	private  InitializeAccountHelper() {
	}
	private  InitializeAccountHelper(Remote r) {
		this.remote=r;
	}

	public InitializeAccountBLService getService(){
		return (InitializeAccountBLService) remote;
	}
}
