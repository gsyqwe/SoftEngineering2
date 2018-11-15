package financialreceipt;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import PO.FinancialReceiptPO;
import VO.BankAccountVO;
import VO.FinancialReceiptVO;
import VO.LineItemVO;
import enums.FinancialReceiptType;
import enums.ReceiptType;
import enums.ResultMessage;
import financialreceiptdata.FinancialReceiptDataUseDataBase;
import financialreceiptdataservice.FinancialReceiptDataService;

public class FinancialReceiptTest {

	public static void main(String[] args) {
		FinancialReceiptTest test = new FinancialReceiptTest();
		// test.getAccountTest();
		// test.addReceiptTest();
		 //test.deleteTest();
		// test.endFinancialTest();
		test.findByMemberTest();
	}

	public void findByMemberTest() {

		FinancialReceiptDataService data = new FinancialReceiptDataUseDataBase();
		ArrayList<FinancialReceiptPO> receipts = data.findByMember("XSS-00001");
		for (FinancialReceiptPO receipt : receipts) {
			System.out.println(receipt.getId());
		}

	}

	public void deleteTest() {
		try {
			FinancialReceiptController controller = new FinancialReceiptController();
			System.out.println(controller.deleteDraft("MAN-002 20171230 12:11:10-"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void endFinancialTest() {
		FinancialReceiptVO receipt = new FinancialReceiptVO();
		@SuppressWarnings("deprecation")
		Date thedate = new Date(118, 1, 1, 12, 11, 10);
		Date date = getEarlyDate(thedate);
		receipt.setDate(date);
		receipt.setFinancialReceiptType(FinancialReceiptType.BILL);
		receipt.setOperatorID("MAN-002");
		try {
			FinancialReceiptController controller = new FinancialReceiptController();
			System.out.println(controller.endFinancial(receipt));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getAccountTest() {
		try {
			FinancialReceiptController controller = new FinancialReceiptController();
			ArrayList<BankAccountVO> accounts = controller.getAllAccount();
			if (accounts == null || accounts.size() == 0) {
				System.out.println("y");
				return;
			}

			for (BankAccountVO account : accounts) {
				System.out.println(account);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addReceiptTest() {
		FinancialReceiptVO receipt = new FinancialReceiptVO();
		@SuppressWarnings("deprecation")
		Date thedate = new Date(118, 1, 6, 12, 11, 10);
		Date date = getEarlyDate(thedate);
		receipt.setDate(date);
		receipt.setFinancialReceiptType(FinancialReceiptType.BILL);// 如果在这一天，把类型改成bill，就会报错
		LineItemVO item = new LineItemVO("BAT-20171222-00001", 0, 10000, "");
		ArrayList<LineItemVO> items = new ArrayList<>();
		items.add(item);
		receipt.setLineItem(items);
		receipt.setMemberID("XSS-00001");
		receipt.setOperatorID("MAN-002");
		receipt.setType(ReceiptType.FINANCIAL_RECEIPT);
		try {
			FinancialReceiptController controller = new FinancialReceiptController();
			ResultMessage message = controller.completeAdd(receipt);
			System.out.println(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Date getEarlyDate(Date nowDate) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(nowDate);
		rightNow.add(Calendar.DATE, -33);// 日期减1年
		Date preDate = rightNow.getTime();
		return preDate;
	}
}
