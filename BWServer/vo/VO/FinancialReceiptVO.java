package VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import PO.FinancialReceiptPO;
import PO.LineItemPO;
import enums.FinancialReceiptType;
import enums.ReceiptState;
import enums.ReceiptType;

public class FinancialReceiptVO extends ReceiptVO implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private FinancialReceiptType financialReceiptType;
	private String memberID;
	private double sum;

	public FinancialReceiptVO(ReceiptState state,Date date, String id, FinancialReceiptType financialReceiptType, String memberID,
			String operatorID, ArrayList<LineItemVO> lineItem, double sum) {
		super(ReceiptType.FINANCIAL_RECEIPT, state, id, operatorID, date);
		this.financialReceiptType = financialReceiptType;
		this.memberID = memberID;
		this.sum = sum;
		this.lineItem = lineItem;
	}

	public FinancialReceiptVO() {
		super();
	}

	public FinancialReceiptType getFinancialReceiptType() {
		return financialReceiptType;
	}

	public void setFinancialReceiptType(FinancialReceiptType financialReceiptType) {
		this.financialReceiptType = financialReceiptType;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Override
	public FinancialReceiptPO toPO() {
		ArrayList<LineItemPO> resultItem = new ArrayList<>();
		for (LineItemVO item : this.lineItem) {
			resultItem.add(item.toPO());
		}
		return new FinancialReceiptPO(this.state, date, this.id, this.financialReceiptType, this.memberID,
				this.operatorID, resultItem, this.sum);
	}
}
