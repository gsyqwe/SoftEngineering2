package receiptFinder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import PO.FinancialReceiptPO;
import enums.ReceiptState;
import financialreceiptdata.FinancialReceiptDataUseDataBase;
import financialreceiptdataservice.FinancialReceiptDataService;

public class FinancialFinder implements ReceiptDataServiceInfo ,Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	FinancialReceiptDataService dataService = new FinancialReceiptDataUseDataBase();

	@Override
	public FinancialReceiptPO findByID(String targetID) {// 可以将接口方法的返回值改为子类
		// TODO Auto-generated method stub
		return dataService.findByID(targetID);
	}

	@Override
	public ArrayList<FinancialReceiptPO> findByUser(String userID) {
		// TODO Auto-generated method stub
		ArrayList<FinancialReceiptPO> financial = dataService.findByUser(userID);
		if (financial == null) {
			return new ArrayList<>();
		}
		return financial;
	}

	@Override
	public ArrayList<FinancialReceiptPO> findByDate(Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		ArrayList<FinancialReceiptPO> financial = dataService.findByDate(startTime, endTime);

		if (financial == null) {
			return new ArrayList<>();
		}

		return financial;
	}

	@Override
	public ArrayList<FinancialReceiptPO> findByReceiptState(ReceiptState receiptState) {
		// TODO Auto-generated method stub
		ArrayList<FinancialReceiptPO> financial = dataService.findByReceiptState(receiptState);

		if (financial == null) {
			return new ArrayList<>();
		}

		return financial;
	}

	@Override
	public ArrayList<FinancialReceiptPO> findByMember(String memberID) {
		// TODO Auto-generated method stub
		ArrayList<FinancialReceiptPO> financial = dataService.findByMember(memberID);

		if (financial == null) {
			return new ArrayList<>();
		}

		return financial;
	}

	@Override
	public ArrayList<FinancialReceiptPO> findByRepository(String repositoryName) {
		// TODO Auto-generated method stub
		return new ArrayList<FinancialReceiptPO>();
	}

}
