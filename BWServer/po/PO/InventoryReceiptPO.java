package PO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import VO.InventoryReceiptVO;
import VO.LineItemVO;
import enums.InventoryReceiptType;
import enums.ReceiptState;
import enums.ReceiptType;

public final class InventoryReceiptPO extends ReceiptPO {
	private static Set<LineItemPO> LINE_ITEM_1 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_2 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_3 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_4 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_5 = new HashSet<>();
	static {
		LINE_ITEM_1.add(LineItemPO.SAMPLE_5);
		LINE_ITEM_1.add(LineItemPO.SAMPLE_4);
		LINE_ITEM_2.add(LineItemPO.SAMPLE_3);
		LINE_ITEM_2.add(LineItemPO.SAMPLE_2);
		LINE_ITEM_2.add(LineItemPO.SAMPLE_1);
		LINE_ITEM_3.add(LineItemPO.SAMPLE_5);
		LINE_ITEM_4.add(LineItemPO.SAMPLE_4);
		LINE_ITEM_4.add(LineItemPO.SAMPLE_3);
		LINE_ITEM_5.add(LineItemPO.SAMPLE_2);
		LINE_ITEM_5.add(LineItemPO.SAMPLE_1);
		LINE_ITEM_5.add(LineItemPO.SAMPLE_5);
	}
	public static InventoryReceiptPO SAMPLE_1 = new InventoryReceiptPO(InventoryReceiptType.OVERFLOW,
			ReceiptState.SUBMITTED, "IVE-00001", new Date(new Date().getTime() - 100000), "MEM-00001", LINE_ITEM_1);
	public static InventoryReceiptPO SAMPLE_2 = new InventoryReceiptPO(InventoryReceiptType.BREAKAGE,
			ReceiptState.UNAPPROVEAL, "IVE-00002", new Date(new Date().getTime() - 100000), "MEM-00002", LINE_ITEM_2);
	public static InventoryReceiptPO SAMPLE_3 = new InventoryReceiptPO(InventoryReceiptType.OVERFLOW,
			ReceiptState.VERIFIED, "IVE-00003", new Date(new Date().getTime() - 100000), "MEM-00003", LINE_ITEM_3);
	public static InventoryReceiptPO SAMPLE_4 = new InventoryReceiptPO(InventoryReceiptType.GIFT,
			ReceiptState.UNAPPROVEAL, "IVE-00004", new Date(new Date().getTime() - 100000), "MEM-00001", LINE_ITEM_4);
	public static InventoryReceiptPO SAMPLE_5 = new InventoryReceiptPO(InventoryReceiptType.BREAKAGE,
			ReceiptState.UNCOMMITTED, "IVE-00005", new Date(new Date().getTime() - 100000), "MEM-00002", LINE_ITEM_5);

	private InventoryReceiptType inventoryType;
	// private ArrayList<LineItemPO> lineItem;

	public InventoryReceiptPO() {
		super();
	}

	public InventoryReceiptPO(InventoryReceiptType inventoryType, ReceiptState state, String id, Date date,
			String operatorID, Set<LineItemPO> lineItem) {
		super(ReceiptType.INVENTORY_RECEIPT, state, id, operatorID, date);
		this.inventoryType = inventoryType;
		this.lineItem = lineItem;
	}

	public InventoryReceiptPO(InventoryReceiptType inventoryType, ReceiptState state, String id, Date date,
			String operatorID, ArrayList<LineItemPO> lineItem) {
		super(ReceiptType.INVENTORY_RECEIPT, state, id, operatorID, date);
		this.inventoryType = inventoryType;
		Set<LineItemPO> resultitem = new HashSet<>(lineItem);
		this.lineItem = resultitem;
	}

	public InventoryReceiptType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(InventoryReceiptType inventoryType) {
		this.inventoryType = inventoryType;
	}

	/*
	 * public ArrayList<LineItemPO> getLineItem() { return lineItem;
	 * 
	 * }
	 * 
	 * public void setLineItem(ArrayList<LineItemPO> lineItem) { this.lineItem =
	 * lineItem; }
	 */

	public InventoryReceiptVO toVO() {
		ArrayList<LineItemVO> line = new ArrayList<LineItemVO>();
		for (LineItemPO i : lineItem)
			line.add(i.toVO());
		return new InventoryReceiptVO(type, state, id, operatorID, date, inventoryType, line);
	}

	@Override
	public String toString() {
		return "InventoryReceiptPO [inventoryType=" + inventoryType + ", type=" + type + ", state=" + state + ", id="
				+ id + ", operatorID=" + operatorID + ", date=" + date + ", lineItem=" + lineItem + "]";
	}
}
