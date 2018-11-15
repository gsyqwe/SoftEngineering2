package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import receiptFinder.FinancialFinder;
import receiptFinder.FindByCategory;
import receiptFinder.FindByDate;
import receiptFinder.FindByMember;
import receiptFinder.FindByRepository;
import receiptFinder.FindByUser;
import receiptFinder.InventoryFinder;
import receiptFinder.ReceiptDataServiceInfo;
import receiptFinder.SalesFinder;

public interface FindReceiptService extends Remote{

	public FinancialFinder getFinancialFinder() throws RemoteException;

	public InventoryFinder getInventoryFinder() throws RemoteException;

	public SalesFinder getSalesFinder() throws RemoteException;

	public FindByCategory getFindByCategory(ArrayList<ReceiptDataServiceInfo> info) throws RemoteException;

	public FindByDate getFindByDate(ArrayList<ReceiptDataServiceInfo> info) throws RemoteException;

	public FindByMember getFindByMember(ArrayList<ReceiptDataServiceInfo> info) throws RemoteException;

	public FindByRepository getFindByRepository(ArrayList<ReceiptDataServiceInfo> info) throws RemoteException;

	public FindByUser getFindByUser(ArrayList<ReceiptDataServiceInfo> info) throws RemoteException;

}
