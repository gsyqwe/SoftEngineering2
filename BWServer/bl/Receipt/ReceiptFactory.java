package Receipt;

import PO.FinancialReceiptPO;
import PO.InventoryReceiptPO;
import PO.ReceiptPO;
import PO.SalesReceiptPO;
import enums.ReceiptType;

/**
 * 
 * @author JiYuzhe
 *一个生产单据的简单工厂，根据单据类别的不同生成不同的逻辑领域对象,可以通过强制类型转换来生产
 */

public class ReceiptFactory {
	
	public static Receipt creatReceipt(ReceiptPO receiptPO){
		Receipt receipt = null;
		
		if(receiptPO.getType() == ReceiptType.FINANCIAL_RECEIPT){
			receipt = new FinancialReceipt((FinancialReceiptPO) receiptPO);
		}
		else if(receiptPO.getType() == ReceiptType.INVENTORY_RECEIPT){
			receipt = new InventoryReceipt((InventoryReceiptPO) receiptPO);
		}
		else if(receiptPO.getType() == ReceiptType.SALE_RECEIPT){
			receipt = new SalesReceipt((SalesReceiptPO) receiptPO);
		}
		
		return receipt;
	}

}
