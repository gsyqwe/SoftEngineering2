package receiptFinder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import service.FindReceiptService;

public class FindReceiptFactory extends UnicastRemoteObject implements FindReceiptService{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public FindReceiptFactory() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public FinancialFinder getFinancialFinder(){
		return new FinancialFinder();
	}

	@Override
	public InventoryFinder getInventoryFinder(){
		return new InventoryFinder();
	}

	@Override
	public SalesFinder getSalesFinder(){
		return new SalesFinder();
	}

	@Override
	public FindByCategory getFindByCategory(ArrayList<ReceiptDataServiceInfo> info){
		return new FindByCategory(info);
	}

	@Override
	public FindByDate getFindByDate(ArrayList<ReceiptDataServiceInfo> info){
		return new FindByDate(info);
	}

	@Override
	public FindByMember getFindByMember(ArrayList<ReceiptDataServiceInfo> info){
		return new FindByMember(info);
	}

	@Override
	public FindByRepository getFindByRepository(ArrayList<ReceiptDataServiceInfo> info){
		return new FindByRepository(info);
	}

	@Override
	public FindByUser getFindByUser(ArrayList<ReceiptDataServiceInfo> info){
		return new FindByUser(info);
	}

}
