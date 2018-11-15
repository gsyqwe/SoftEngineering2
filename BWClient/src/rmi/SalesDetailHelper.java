package rmi;

import java.rmi.Remote;

import service.SalesDetailBLService;

public class SalesDetailHelper {

	private Remote remote;
	private static SalesDetailHelper salesDetailHelper;
	public static SalesDetailHelper getInstance(){
		if(salesDetailHelper==null)
			salesDetailHelper=new SalesDetailHelper();
		return salesDetailHelper;
	}
	public static SalesDetailHelper getInstance(Remote r){
		if(salesDetailHelper==null)
			salesDetailHelper=new SalesDetailHelper(r);
		return salesDetailHelper;
	}
	private  SalesDetailHelper() {
	}
	private  SalesDetailHelper(Remote r) {
		this.remote=r;
	}
	public SalesDetailBLService getService(){
		return (SalesDetailBLService) remote;
	}
}
