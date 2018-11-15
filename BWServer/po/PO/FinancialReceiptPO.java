package PO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import VO.FinancialReceiptVO;
import VO.LineItemVO;
import enums.FinancialReceiptType;
import enums.ReceiptState;
import enums.ReceiptType;

public final class FinancialReceiptPO extends ReceiptPO {
	private static Set<LineItemPO> LINE_ITEM_1 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_2 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_3 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_4 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_5 = new HashSet<>();
	public static FinancialReceiptPO SAMPLE_1 = new FinancialReceiptPO(new Date(), "FAN-00001",
			FinancialReceiptType.DEBIT_NOTE, "MEM-00001", "MAN-00001", LINE_ITEM_1, 999.9);
	public static FinancialReceiptPO SAMPLE_2 = new FinancialReceiptPO(new Date(), "FAN-00002",
			FinancialReceiptType.BILL, "MEM-00001", "MAN-00001", LINE_ITEM_2, 299.9);
	public static FinancialReceiptPO SAMPLE_3 = new FinancialReceiptPO(new Date(), "FAN-00003",
			FinancialReceiptType.DEBIT_NOTE, "MEM-00001", "MAN-00001", LINE_ITEM_3, 99.9);
	public static FinancialReceiptPO SAMPLE_4 = new FinancialReceiptPO(new Date(), "FAN-00004",
			FinancialReceiptType.CASH_CLAIM, "MEM-00001", "MAN-00001", LINE_ITEM_4, 599.9);
	public static FinancialReceiptPO SAMPLE_5 = new FinancialReceiptPO(new Date(), "FAN-00005",
			FinancialReceiptType.CASH_CLAIM, "MEM-00001", "MAN-00001", LINE_ITEM_5, 799.9);
	static {
		LINE_ITEM_1.add(LineItemPO.SAMPLE_1);
		LINE_ITEM_1.add(LineItemPO.SAMPLE_2);
		LINE_ITEM_2.add(LineItemPO.SAMPLE_3);
		LINE_ITEM_2.add(LineItemPO.SAMPLE_4);
		LINE_ITEM_2.add(LineItemPO.SAMPLE_5);
		LINE_ITEM_3.add(LineItemPO.SAMPLE_1);
		LINE_ITEM_4.add(LineItemPO.SAMPLE_2);
		LINE_ITEM_4.add(LineItemPO.SAMPLE_3);
		LINE_ITEM_5.add(LineItemPO.SAMPLE_4);
		LINE_ITEM_5.add(LineItemPO.SAMPLE_5);
		LINE_ITEM_5.add(LineItemPO.SAMPLE_1);
	}

	private FinancialReceiptType financialReceiptType;
	private String memberID;
	// private ArrayList<LineItemPO> lineItem;
	private double sum;

	public FinancialReceiptPO() {
		super();
	}

	public FinancialReceiptPO(ReceiptState state,Date date, String id, FinancialReceiptType financialReceiptType, String memberID,
			String operatorID, ArrayList<LineItemPO> lineItem, double sum) {
		super(ReceiptType.FINANCIAL_RECEIPT, state, id, operatorID, date);
		this.financialReceiptType = financialReceiptType;
		this.memberID = memberID;
		Set<LineItemPO> resultItem = new HashSet<>(lineItem);
		this.lineItem = resultItem;
		this.sum = sum;
	}

	public FinancialReceiptPO(Date date, String id, FinancialReceiptType financialReceiptType, String memberID,
			String operatorID, Set<LineItemPO> lineItem, double sum) {
		super(ReceiptType.FINANCIAL_RECEIPT, ReceiptState.SUBMITTED, id, operatorID, date);
		this.financialReceiptType = financialReceiptType;
		this.memberID = memberID;
		this.lineItem = lineItem;
		this.sum = sum;
	}

	public void addLineItem(LineItemPO lineItem) {
		this.lineItem.add(lineItem);
	}

	@Override
	public Date getDate() {
		return super.getDate();
	}

	public FinancialReceiptType getFinancialReceiptType() {
		return financialReceiptType;
	}

	@Override
	public String getId() {
		return super.getId();
	}

	// for database
	@Override
	public Set<LineItemPO> getLineItem() {
		return super.getLineItem();
	}

	@Override
	public ArrayList<LineItemPO> getLineItemAsList() {
		return super.getLineItemAsList();
	}

	public String getMemberID() {
		return memberID;
	}

	@Override
	public String getOperatorID() {
		return super.getOperatorID();
	}

	/*
	 * public ArrayList<LineItemPO> getLineItem() { return lineItem; }
	 *
	 * public void setLineItem(ArrayList<LineItemPO> lineItem) { this.lineItem =
	 * lineItem; }
	 */

	@Override
	public ReceiptState getState() {
		return super.getState();
	}

	public double getSum() {
		return sum;
	}

	@Override
	public ReceiptType getType() {
		return super.getType();
	}

	@Override
	public void setDate(Date date) {
		super.setDate(date);
	}

	public void setFinancialReceiptType(FinancialReceiptType financialReceiptType) {
		this.financialReceiptType = financialReceiptType;
	}

	@Override
	public void setId(String id) {
		super.setId(id);
	}

	@Override
	public void setLineItem(ArrayList<LineItemPO> line) {
		super.setLineItem(line);
	}

	// for database
	@Override
	public void setLineItem(Set<LineItemPO> line) {
		super.setLineItem(line);
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	@Override
	public void setOperatorID(String operatorID) {
		super.setOperatorID(operatorID);
	}

	@Override
	public void setState(ReceiptState state) {
		super.setState(state);
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Override
	public void setType(ReceiptType type) {
		super.setType(type);
	}

	@Override
	public String toString() {
		return "FinancialReceiptPO [financialReceiptType=" + financialReceiptType + ", memberID=" + memberID + ", sum="
				+ sum + ", type=" + type + ", state=" + state + ", id=" + id + ", operatorID=" + operatorID + ", date="
				+ date + ", lineItem=" + lineItem + "]";
	}

	@Override
	public FinancialReceiptVO toVO() {
		ArrayList<LineItemVO> line = new ArrayList<LineItemVO>();
		for (LineItemPO i : lineItem)
			line.add(i.toVO());
		return new FinancialReceiptVO(this.state,date, id, financialReceiptType, memberID, super.operatorID, line, sum);
	}

}
