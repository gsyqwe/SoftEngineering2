package receiptFinder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import PO.SalesReceiptPO;
import enums.ReceiptState;
import enums.SalesReceiptType;
import salesdata.SalesDataUseDataBase;
import salesdataService.SalesDataService;

public class SalesFinder implements ReceiptDataServiceInfo,Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	SalesDataService dataService = new SalesDataUseDataBase();

	@Override
	public SalesReceiptPO findByID(String targetID) {
		// TODO Auto-generated method stub
		return dataService.findByID(targetID);
	}

	@Override
	public ArrayList<SalesReceiptPO> findByUser(String userID) {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptPO> sales = dataService.findByUser(userID);
		if(sales == null){
			return new ArrayList<>();
		}

		return sales;
	}

	@Override
	public ArrayList<SalesReceiptPO> findByDate(Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptPO> sales = dataService.findByTime(startTime, endTime);
		if(sales == null){
			return new ArrayList<>();
		}

		return sales;
	}

	@Override
	public ArrayList<SalesReceiptPO> findByReceiptState(ReceiptState receiptState) {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptPO> sales = dataService.findByReceiptState(receiptState);
		if(sales == null){
			return new ArrayList<>();
		}

		return sales;
	}

	@Override
	public ArrayList<SalesReceiptPO> findByMember(String memberID) {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptPO> sales = dataService.findByMember(memberID);;
		if(sales == null){
			return new ArrayList<>();
		}

		return sales;
	}

	@Override
	public ArrayList<SalesReceiptPO> findByRepository(String repositoryName) {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptPO> sales = dataService.findByRepository(repositoryName);
		if(sales == null){
			return new ArrayList<>();
		}

		return sales;
	}

	//这个方法是提供给SalesDetail使用的
	public ArrayList<SalesReceiptPO> findBySalesReceiptType(SalesReceiptType type){
		ArrayList<SalesReceiptPO> sales = dataService.findBySalesType(type);
		if(sales == null){
			return new ArrayList<>();
		}

		return sales;
	}

}
