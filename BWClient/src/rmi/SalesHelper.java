package rmi;

import java.rmi.Remote;

import service.SalesblService;

public class SalesHelper {
	private Remote remote;
	private static SalesHelper salesHelper;
	public static SalesHelper getInstance(){
		if(salesHelper==null)
			salesHelper=new SalesHelper();
		return salesHelper;
	}
	public static SalesHelper getInstance(Remote r){
		if(salesHelper==null)
			salesHelper=new SalesHelper(r);
		return salesHelper;
	}

	private  SalesHelper() {
	}
	private  SalesHelper(Remote r) {
		this.remote=r;
	}

	public SalesblService getService(){
		return (SalesblService) remote;
	}
}
