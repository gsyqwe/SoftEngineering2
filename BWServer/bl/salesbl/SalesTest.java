package salesbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import VO.CommodityVO;
import VO.LineItemVO;
import VO.MemberVO;
import VO.SalesReceiptVO;
import enums.MemberType;
import enums.SalesReceiptType;

public class SalesTest {

	public static void main(String[] args) {
		SalesTest test = new SalesTest();
		//test.testAddPurchase();
		test.addSalesInformation();
		//test.deleteTest();
		//test.getMemberTest();
		//test.getCommodityTest();
	}

	public void getCommodityTest(){
		try {
			SalesController controller = new SalesController();
			ArrayList<CommodityVO> commodities = controller.getCommodities();
			for(CommodityVO com:commodities){
				System.out.println(com.getId());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addSalesInformation(){
		SalesReceiptVO sales = new SalesReceiptVO();
		@SuppressWarnings("deprecation")
		Date date = new Date(118,0,6,20,0,0);
		sales.setDate(date);
		sales.setMemberID("XSS-00003");
		sales.setOperatorID("MAN-002");
		sales.setRepositoryName("Category_2");
		ArrayList<LineItemVO> lineItem = new ArrayList<>();
		LineItemVO item = new LineItemVO("COM-20171207-00003",10,10,"");

		lineItem.add(item);
		sales.setLineItem(lineItem);
		sales.setSalesType(SalesReceiptType.SALES);
		ArrayList<String> promotion = new ArrayList<>();
		ArrayList<Double> discount = new ArrayList<>();

		try {
			SalesController controller = new SalesController();
			sales = controller.addLineItem(sales, promotion,discount);
			System.out.println(sales.getDiscount());

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		double sumBefore = 0;
		for(LineItemVO it:receipt.getLineItem()){
			sumBefore = sumBefore + it.getPrice() * it.getQuantity();
		}
		receipt.setSumBeforeDiscount(sumBefore);

		receipt.setSumAfterDiscount(sumBefore - receipt.getDiscount());

		try {
			SalesController controller = new SalesController();
			System.out.println(controller.sendAudit(receipt));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(String pro:promotion){
			System.out.println(pro);
		}

		System.out.println(receipt.getDiscount());
		*/
	}

	public void deleteTest(){
		try {
			SalesController controller = new SalesController();
			System.out.println(controller.deleteReceipt("SAL-00004"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getMemberTest(){
		try {
			SalesController controller = new SalesController();
			ArrayList<MemberVO> members = controller.getNowMembers(MemberType.RETAILER);
			for(MemberVO member:members){
				System.out.println(member.getId());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public void testAddPurchase() {
		SalesReceiptVO sales = new SalesReceiptVO();
		sales.setMemberID("XSS-00001");
		sales.setOperatorID("MAN-002");
		@SuppressWarnings("deprecation")
		Date date = new Date(118, 2, 30, 1, 1, 1);
		sales.setDate(date);
		ArrayList<LineItemVO> items = new ArrayList<>();
		LineItemVO item = new LineItemVO("COM-20171207-00002", 100, 137, "");
		LineItemVO item2 = new LineItemVO("COM-20171207-00003", 20, 155, "");
		items.add(item);
		items.add(item2);
		sales.setLineItem(items);
		sales.setDiscount(100);
		sales.setRepositoryName("Category_1");
		sales.setSalesType(SalesReceiptType.SALES_RETURN);

		try {
			SalesController controller = new SalesController();
			System.out.println(controller);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
