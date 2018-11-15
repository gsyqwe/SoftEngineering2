package rmi;

import java.rmi.Remote;

import service.CheckReceiptBLService;

public class CheckReceiptHelper {
	private Remote remote;
	private static CheckReceiptHelper checkReceiptHelper;
	public static CheckReceiptHelper getInstance(){
		if(checkReceiptHelper==null)
			checkReceiptHelper=new CheckReceiptHelper();
		return checkReceiptHelper;
	}
	public static CheckReceiptHelper getInstance(Remote r){
		if(checkReceiptHelper==null)
			checkReceiptHelper=new CheckReceiptHelper(r);
		return checkReceiptHelper;
	}
	
	private CheckReceiptHelper() {
	}
	private CheckReceiptHelper(Remote r) {
		this.remote=r;
	}

	public CheckReceiptBLService getService(){
		return (CheckReceiptBLService) remote;
	}
}
