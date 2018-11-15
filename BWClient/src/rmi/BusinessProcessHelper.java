package rmi;

import java.rmi.Remote;
import service.BusinessProcessBLService;

public class BusinessProcessHelper {
	private Remote remote;
	private static BusinessProcessHelper businessProcessHelper ;
	public static BusinessProcessHelper getInstance(){
		if(businessProcessHelper==null)
			businessProcessHelper=new BusinessProcessHelper();
		return businessProcessHelper;
	}
	public static BusinessProcessHelper getInstance(Remote r){
		if(businessProcessHelper==null)
			businessProcessHelper=new BusinessProcessHelper(r);
		return businessProcessHelper;
	}
	

	private BusinessProcessHelper() {
	}
	private BusinessProcessHelper(Remote r) {
		this.remote=r;
	}

	public BusinessProcessBLService getService(){
		return (BusinessProcessBLService) remote;
	}
}
