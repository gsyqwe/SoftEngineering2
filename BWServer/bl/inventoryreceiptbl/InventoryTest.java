package inventoryreceiptbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import VO.InventoryReceiptVO;
import VO.LineItemVO;
import enums.InventoryReceiptType;
import enums.ReceiptType;

public class InventoryTest {

	public static void main(String [] args){
		InventoryTest test = new InventoryTest();
		test.deleteTest();
	}

	public void addTest(){
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
		try {
			InventoryReceiptController controller = new InventoryReceiptController();
			System.out.println(controller.completeAdd(receipt));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteTest(){
		try {
			InventoryReceiptController controller = new InventoryReceiptController();
			System.out.println(controller.deleteReceipt("IVE-00003"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
