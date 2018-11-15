
package VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import PO.InventoryReceiptPO;
import PO.LineItemPO;
import enums.InventoryReceiptType;
import enums.ReceiptState;
import enums.ReceiptType;

public class InventoryReceiptVO extends ReceiptVO implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private InventoryReceiptType inventoryType;

	public InventoryReceiptVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InventoryReceiptVO(ReceiptType type, ReceiptState state, String id, String operatorID, Date date,
			InventoryReceiptType inventoryType, ArrayList<LineItemVO> lineItem) {
		super(type, state, id, operatorID, date);
		this.inventoryType = inventoryType;
		this.lineItem = lineItem;
	}

	public InventoryReceiptType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(InventoryReceiptType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public InventoryReceiptPO toPO() {
		ArrayList<LineItemPO> line = new ArrayList<LineItemPO>();
		for (LineItemVO i : lineItem)
			line.add(i.toPO());

		return new InventoryReceiptPO(inventoryType, state, id, date, operatorID, line);
	}
}
