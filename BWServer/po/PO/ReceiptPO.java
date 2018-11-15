package PO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import VO.LineItemVO;
import VO.ReceiptVO;
import enums.ReceiptState;
import enums.ReceiptType;

public class ReceiptPO {
	public static ReceiptPO SAMPLE_1 = new ReceiptPO(ReceiptType.FINANCIAL_RECEIPT, ReceiptState.UNAPPROVEAL,
			"FAN-00001", "MAN-00001", new Date(new Date().getTime() - 35465789));
	public static ReceiptPO SAMPLE_2 = new ReceiptPO(ReceiptType.SALE_RECEIPT, ReceiptState.VERIFIED, "SAL-00002",
			"MAN-00003", new Date(new Date().getTime() - 687897789));
	public static ReceiptPO SAMPLE_3 = new ReceiptPO(ReceiptType.FINANCIAL_RECEIPT, ReceiptState.UNCOMMITTED,
			"FAN-00002", "MAN-00004", new Date(new Date().getTime() - 11465789));
	public static ReceiptPO SAMPLE_4 = new ReceiptPO(ReceiptType.SALE_RECEIPT, ReceiptState.VERIFIED, "SAL-00001",
			"MAN-00001", new Date(new Date().getTime() - 3344678));
	public static ReceiptPO SAMPLE_5 = new ReceiptPO(ReceiptType.INVENTORY_RECEIPT, ReceiptState.SUBMITTED, "INE-00001",
			"MAN-00002", new Date(new Date().getTime() - 9809785));
	public static LineItemPO LINE_ITEM_1 = new LineItemPO("COM-00001", 29, 19.9);
	public static LineItemPO LINE_ITEM_2 = new LineItemPO("COM-00002", 19, 9.9);
	public static LineItemPO LINE_ITEM_3 = new LineItemPO("COM-00003", 90, 789.2);
	public static LineItemPO LINE_ITEM_4 = new LineItemPO("COM-00004", 56, 89.1);
	public static LineItemPO LINE_ITEM_5 = new LineItemPO("COM-00005", 25, 99.2);
	public static Set<LineItemPO> LINE_ITEM_LIST_1 = new HashSet<>();
	public static Set<LineItemPO> LINE_ITEM_LIST_2 = new HashSet<>();
	public static Set<LineItemPO> LINE_ITEM_LIST_3 = new HashSet<>();
	public static Set<LineItemPO> LINE_ITEM_LIST_4 = new HashSet<>();
	public static Set<LineItemPO> LINE_ITEM_LIST_5 = new HashSet<>();
	static {
		LINE_ITEM_LIST_1.add(LINE_ITEM_1);
		LINE_ITEM_LIST_1.add(LINE_ITEM_2);
		LINE_ITEM_LIST_2.add(LINE_ITEM_3);
		LINE_ITEM_LIST_2.add(LINE_ITEM_4);
		LINE_ITEM_LIST_2.add(LINE_ITEM_5);
		LINE_ITEM_LIST_2.add(LINE_ITEM_1);
		LINE_ITEM_LIST_3.add(LINE_ITEM_2);
		LINE_ITEM_LIST_3.add(LINE_ITEM_3);
		LINE_ITEM_LIST_3.add(LINE_ITEM_4);
		LINE_ITEM_LIST_4.add(LINE_ITEM_5);
		LINE_ITEM_LIST_4.add(LINE_ITEM_1);
		LINE_ITEM_LIST_5.add(LINE_ITEM_2);
		LINE_ITEM_LIST_5.add(LINE_ITEM_3);
		SAMPLE_1.setLineItem(LINE_ITEM_LIST_1);
		SAMPLE_2.setLineItem(LINE_ITEM_LIST_2);
		SAMPLE_3.setLineItem(LINE_ITEM_LIST_3);
		SAMPLE_4.setLineItem(LINE_ITEM_LIST_4);
		SAMPLE_5.setLineItem(LINE_ITEM_LIST_5);
	}

	protected ReceiptType type;
	protected ReceiptState state;
	/*
	 * key for database
	 */
	protected String id; // note that different receipt have different id format
	protected String operatorID; // the id of the user that open this receipt
	protected Date date; // the time that this receipt opened
	/*
	 * database mapping
	 */
	protected Set<LineItemPO> lineItem = new HashSet<>();
	private boolean isDeleted;

	protected ReceiptPO() {

	}

	public ReceiptPO(ReceiptType type, ReceiptState state, String id, String operatorID, Date date) {
		super();
		this.type = type;
		this.state = state;
		this.id = id;
		this.operatorID = operatorID;
		this.date = date;
	}

	public ReceiptPO(ReceiptType type, ReceiptState state, String id, String operatorID, Date date,
			ArrayList<LineItemPO> lineItem) {
		super();
		this.type = type;
		this.state = state;
		this.id = id;
		this.operatorID = operatorID;
		this.date = date;
		Set<LineItemPO> resultItem = new HashSet<>(lineItem);
		this.lineItem = resultItem;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setLineItem(ArrayList<LineItemPO> line) {
		Set<LineItemPO> resultItem = new HashSet<>(line);
		lineItem = resultItem;
	}

	// for database
	public void setLineItem(Set<LineItemPO> line) {
		Set<LineItemPO> resultItem = new HashSet<>(line);
		lineItem = resultItem;
	}

	public ArrayList<LineItemPO> getLineItemAsList() {
		ArrayList<LineItemPO> resultItem = new ArrayList<>(lineItem);
		return resultItem;
	}

	// for database
	public Set<LineItemPO> getLineItem() {
		return lineItem;
	}

	public void setType(ReceiptType type) {
		this.type = type;
	}

	public void setState(ReceiptState state) {
		this.state = state;
	}

	// for database
	public void setId(String id) {
		this.id = id;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	// for database
	public String getId() {
		return id;
	}

	public ReceiptState getState() {
		return state;
	}

	public ReceiptType getType() {
		return type;
	}

	public String getOperatorID() {
		return operatorID;
	}

	@Override
	public String toString() {
		return "ReceiptPO [type=" + type + ", state=" + state + ", id=" + id + ", operatorID=" + operatorID + ", date="
				+ date + ", lineItem=" + lineItem + "]";
	}

	public ReceiptVO toVO() {
		ArrayList<LineItemVO> itemResult = new ArrayList<>();
		for (LineItemPO item : this.lineItem) {
			itemResult.add(item.toVO());
		}
		return new ReceiptVO(type, state, id, operatorID, date, itemResult);
	}

}
