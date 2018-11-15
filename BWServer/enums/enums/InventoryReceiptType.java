package enums;

public enum InventoryReceiptType {
	OVERFLOW("Overflow"), // ����
	BREAKAGE("Breakage"), // ���l
	GIFT("Gift"), // �ذe
	ALARM("Alarm"); // ��ĵ
	
	private String name;
	
	private InventoryReceiptType(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
