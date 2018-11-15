package rmi;

import java.rmi.Remote;
import service.FinancialReceiptBLService;

public class FinancialReceiptHelper {
	private Remote remote;
	private static FinancialReceiptHelper financialReceiptHelper;
	public static FinancialReceiptHelper getInstance(){
		if(financialReceiptHelper==null)
			financialReceiptHelper=new FinancialReceiptHelper();
		return financialReceiptHelper;
	}
	public static FinancialReceiptHelper getInstance(Remote r){
		if(financialReceiptHelper==null)
			financialReceiptHelper=new FinancialReceiptHelper(r);
		return financialReceiptHelper;
	}
	
	private  FinancialReceiptHelper(Remote r) {
		this.remote=r;
	}
	private  FinancialReceiptHelper() {
	}

	public FinancialReceiptBLService getService(){
		return (FinancialReceiptBLService) remote;
	}
}
