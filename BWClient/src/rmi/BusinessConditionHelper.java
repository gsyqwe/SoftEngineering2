package rmi;

import java.rmi.Remote;
import service.BusinessConditionBLService;

public class BusinessConditionHelper {
	private Remote remote;
	private static BusinessConditionHelper businessConditionHelper;
	public static BusinessConditionHelper getInstance(){
		if(businessConditionHelper==null){
			return new BusinessConditionHelper();
		}
		return businessConditionHelper;
	}
	public static BusinessConditionHelper getInstance(Remote r){
		if(businessConditionHelper==null){
			return new BusinessConditionHelper(r);
		}
		return businessConditionHelper;
	}
	
	private BusinessConditionHelper() {
	}
	private BusinessConditionHelper(Remote r) {
		this.remote=r;
	}
	
	public BusinessConditionBLService getService(){
		return (BusinessConditionBLService) remote;
	}
}
