package auditreceiptdataservice;

import java.util.ArrayList;
import java.util.Date;

import PO.ReceiptPO;
import enums.FinancialReceiptType;
import enums.InventoryReceiptType;
import enums.SalesReceiptType;

public interface AuditReceiptDataService {
	public ArrayList<ReceiptPO> getUnapproveReceipt();

	public ArrayList<ReceiptPO> getUnapproveReceiptByOperator(String operatorID);

	public ArrayList<ReceiptPO> getUnapproveReceiptByReceipType(FinancialReceiptType financialReceiptType);

	public ArrayList<ReceiptPO> getUnapproveReceiptByReceipType(InventoryReceiptType inventoryReceiptType);

	public ArrayList<ReceiptPO> getUnapproveReceiptByReceipType(SalesReceiptType salesReceiptType);

	public ArrayList<ReceiptPO> getUnapproveReceiptByTime(Date startTime, Date endTime);
	
	/*
	 * operatorID type time
	 */
}
