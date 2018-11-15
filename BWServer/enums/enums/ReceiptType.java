package enums;

public enum ReceiptType {
	INVENTORY_RECEIPT("Inventory_receipt"),FINANCIAL_RECEIPT("Financial_receipt"),SALE_RECEIPT("Sale_receipt");

	private String name;

	private ReceiptType(String name){
		this.name = name;
	}

	@Override
	public String toString(){
		return name;
	}

}
