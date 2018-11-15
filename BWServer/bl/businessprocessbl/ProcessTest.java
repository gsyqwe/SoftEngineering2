package businessprocessbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.FinancialReceiptPO;
import PO.InventoryReceiptPO;
import PO.SalesReceiptPO;
import VO.ReceiptVO;
import financialreceiptdata.FinancialReceiptDataUseDataBase;
import financialreceiptdataservice.FinancialReceiptDataService;
import inventoryreceiptdata.InventoryReceiptDataUseDataBase;
import inventoryreceiptdataservice.InventoryReceiptDataService;
import receiptFinder.FinancialFinder;
import receiptFinder.FindByDate;
import receiptFinder.FindByMember;
import receiptFinder.FindByUser;
import receiptFinder.Finder;
import receiptFinder.InventoryFinder;
import receiptFinder.ReceiptDataServiceInfo;
import receiptFinder.SalesFinder;
import salesdata.SalesDataUseDataBase;
import salesdataService.SalesDataService;

public class ProcessTest {

	public static void main(String [] args){
		ProcessTest test = new ProcessTest();
		//test.getAllListTest();
		//test.testRedCopy();
		//test.testRedCopyFinancial();
		test.testRedCopySales();
	}

	public void findTest(){
		ArrayList<ReceiptDataServiceInfo> service = new ArrayList<>();
		service.add(new FinancialFinder());
		service.add(new InventoryFinder());
		service.add(new SalesFinder());

		ArrayList<Finder> finders = new ArrayList<>();
		finders.add(new FindByUser(service));

		ArrayList<String> keywords = new ArrayList<>();
		keywords.add("MAN-002");

		finders.add(new FindByMember(service));
		keywords.add("XSS-00001");
		finders.add(new FindByDate(service));
		keywords.add("2011-01-02->2011-01-04");

		try {
			BusinessProcessController controller = new BusinessProcessController();
			ArrayList<ReceiptVO> receipts = controller.finByAllMeans(finders, keywords);
			for(ReceiptVO receipt:receipts){
				System.out.println(receipt.toPO());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testRedCopy(){
		try {
			BusinessProcessController controller = new BusinessProcessController();
			InventoryReceiptDataService inven = new InventoryReceiptDataUseDataBase();
			InventoryReceiptPO receiptp = inven.findByID("IVE-20180302-00002");
			System.out.println(controller.redCopyInventory(receiptp.toVO(), "MAN-00001"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void testRedCopySales(){
		try {
			BusinessProcessController controller = new BusinessProcessController();
			SalesDataService inven = new SalesDataUseDataBase();
			SalesReceiptPO receiptp = inven.findByID("XSTHD-20180330-00001");
			System.out.println(controller.redCopySales(receiptp.toVO(), "MAN-00001"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void testRedCopyFinancial(){
		try {
			BusinessProcessController controller = new BusinessProcessController();
			FinancialReceiptDataService fin = new FinancialReceiptDataUseDataBase();
			FinancialReceiptPO receipt = fin.findByID("FKD-20180102-00004");
			//System.out.println(receipt.getLineItemAsList().size());
			System.out.println(controller.redCopyFinancial(receipt.toVO(), "MAN-00001"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getAllListTest(){
		try {
			ArrayList<ReceiptDataServiceInfo> service = new ArrayList<>();
			service.add(new FinancialFinder());
			service.add(new InventoryFinder());
			service.add(new SalesFinder());

			BusinessProcessController controller = new BusinessProcessController();
			ArrayList<ReceiptVO> receipts = controller.getAllList(service);
			for(ReceiptVO receipt:receipts){
				System.out.println(receipt.getId());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
