package rmi;

import java.rmi.Remote;
import service.OperationLogblService;

public class OperationLogHelper {
	private Remote remote;
	private static OperationLogHelper operationLogHelper ;
	public static OperationLogHelper getInstance(){
		if(operationLogHelper==null)
			operationLogHelper=new OperationLogHelper();
		return operationLogHelper;
	}
	public static OperationLogHelper getInstance(Remote r){
		if(operationLogHelper==null)
			operationLogHelper=new OperationLogHelper(r);
		return operationLogHelper;
	}
	
	private  OperationLogHelper() {
	}
	private  OperationLogHelper(Remote r) {
		this.remote=r;
	}

	public OperationLogblService getService(){
		return (OperationLogblService) remote;
	}
}
