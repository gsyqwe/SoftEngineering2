package checkreportbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import VO.FinancialReceiptVO;
import VO.InventoryReceiptVO;
import VO.LineItemVO;
import VO.ReceiptVO;
import VO.SalesReceiptVO;
import enums.FinancialReceiptType;
import enums.InventoryReceiptType;
import enums.ReceiptState;
import enums.ReceiptType;
import enums.SalesReceiptType;

public class CheckReceiptTest {
	public static void main(String[] args) {
		CheckReceiptTest test = new CheckReceiptTest();
		//test.passFinancialTest();
		//test.passInventoryTest();
		test.passSalesReturnTest();
	}

	public void passFinancialTest() {
		try {
			CheckReceiptController controller = new CheckReceiptController();
			ArrayList<ReceiptVO> receipts = new ArrayList<>();
			FinancialReceiptVO receipt = new FinancialReceiptVO();
			@SuppressWarnings("deprecation")
			Date thedate = new Date(118,1,4,12,11,10);
			Date date = getEarlyDate(thedate);
			receipt.setDate(date);
			receipt.setFinancialReceiptType(FinancialReceiptType.CASH_CLAIM);
			LineItemVO item = new LineItemVO("BAT-20171222-00001",0,10,"");
			ArrayList<LineItemVO> items = new ArrayList<>();
			items.add(item);
			receipt.setLineItem(items);
			receipt.setOperatorID("MAN-002");
			receipt.setType(ReceiptType.FINANCIAL_RECEIPT);
			receipt.setMemberID("MAN-003");
			receipt.setSum(10);
			receipt.setState(ReceiptState.SUBMITTED);
			receipt.setId("XJFYD-20180104-00003");

			receipts.add(receipt);
			ArrayList<String> warning = controller.passByList(receipts,"MAN-001");
			for(String warn:warning){
				System.out.println(warn);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void passInventoryTest(){
		InventoryReceiptVO receipt = new InventoryReceiptVO();
		@SuppressWarnings("deprecation")
		Date date = new Date(118,2,30,1,1,1);
		receipt.setDate(date);
		receipt.setInventoryType(InventoryReceiptType.ALARM);
		receipt.setOperatorID("MAN-001");
		receipt.setType(ReceiptType.INVENTORY_RECEIPT);
		ArrayList<LineItemVO> items = new ArrayList<>();
		LineItemVO item = new LineItemVO("COM-20171207-00002", 100, 137, "");
		LineItemVO item2 = new LineItemVO("COM-20171207-00003",20,155,"");
		items.add(item);
		items.add(item2);
		receipt.setLineItem(items);
		receipt.setId("IVE-20180330-00001");
		try {
			CheckReceiptController controller = new CheckReceiptController();
			ArrayList<ReceiptVO> receipts = new ArrayList<>();
			receipts.add(receipt);
			ArrayList<String> warning = controller.passByList(receipts,"MAN-001");
			for(String warn:warning){
				System.out.println(warn);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void passSalesReturnTest(){
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
		sales.setId("XSTHD-20180330-00002");
		sales.setSumBeforeDiscount(16800);
		sales.setSumAfterDiscount(16700);
		sales.setState(ReceiptState.SUBMITTED);

		try {
			CheckReceiptController controller = new CheckReceiptController();
			ArrayList<ReceiptVO> receipts = new ArrayList<>();
			receipts.add(sales);
			ArrayList<String> warning = controller.passByList(receipts,"MAN-001");
			for(String warn:warning){
				System.out.println(warn);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Date getEarlyDate(Date nowDate){
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(nowDate);
		rightNow.add(Calendar.DATE, -33);// 日期减1年
		Date preDate = rightNow.getTime();
		return preDate;
	}
}
