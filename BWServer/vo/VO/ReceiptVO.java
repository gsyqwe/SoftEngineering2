package VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import PO.LineItemPO;
import PO.ReceiptPO;
import enums.ReceiptState;
import enums.ReceiptType;

public class ReceiptVO implements Serializable,Comparable<ReceiptVO>{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	protected ReceiptType type;
	protected ReceiptState state;
	protected String id; // note that different receipt have different id format
	protected String operatorID; // the id of the user that open this receipt
	protected Date date; // the time that this receipt opened
	protected ArrayList<LineItemVO> lineItem = new ArrayList<>();
	//鏈�澶х殑鐖剁被锛屽湪杩欓噷娣诲姞浜哻heckbox灏卞彲浠ヤ簡
	protected ReceiptVO() {

	}

	public ReceiptVO(ReceiptType type, ReceiptState state, String id,
			String operatorID, Date date) {
		super();
		this.type = type;
		this.state = state;
		this.id = id;
		this.operatorID = operatorID;
		this.date = date;
	}

	public ReceiptVO(ReceiptType type, ReceiptState state, String id, String operatorID, Date date,
			ArrayList<LineItemVO> lineItem) {
		super();
		this.type = type;
		this.state = state;
		this.id = id;
		this.operatorID = operatorID;
		this.date = date;
		this.lineItem = lineItem;
	}

	public ReceiptType getType() {
		return type;
	}
	public void setType(ReceiptType type) {
		this.type = type;
	}
	public ReceiptState getState() {
		return state;
	}
	public void setState(ReceiptState state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperatorID() {
		return operatorID;
	}
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ArrayList<LineItemVO> getLineItem() {
		return lineItem;
	}
	public void setLineItem(ArrayList<LineItemVO> lineItem) {
		this.lineItem = lineItem;
	}

	public ReceiptPO toPO(){
		ArrayList<LineItemPO> result = new ArrayList<>();
		for(LineItemVO item:this.lineItem){
			result.add(item.toPO());
		}

		return new ReceiptPO(type,state,id,operatorID,date,result);
	}

	@Override
	public int compareTo(ReceiptVO o) {
		// TODO Auto-generated method stub
		if(this.getType().compareTo(o.getType()) < 0){
			return -1;
		}
		else if(this.getType().compareTo(o.getType()) == 0){
			return this.getDate().compareTo(o.getDate());
		}
		else{
			return 1;
		}
	}
}
