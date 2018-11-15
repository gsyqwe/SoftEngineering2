package VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import enums.ReceiptState;
import enums.ReceiptType;
import enums.SalesReceiptType;

public class SalesBasicInformationVO extends ReceiptVO implements Serializable{

	/**
	 * 这个vo用于填销售类表单时在填写完所有属性的时候调用，对于所有销售类单据都是一样的
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberID;

	private String repositoryName;

	private String comment;
	
	private SalesReceiptType salesType;

	public SalesBasicInformationVO() {//默认构造函数，指明当前的Receipt的具体type
		//super(ReceiptType.SALE_RECEIPT);
	}

	public SalesBasicInformationVO(ReceiptState state, String id, String operatorID, Date date,
			String memberID, String repositoryName, String comment, ArrayList<LineItemVO> item,
			SalesReceiptType salesType) {
		super(ReceiptType.SALE_RECEIPT, state, id, operatorID, date);
		this.memberID = memberID;
		this.repositoryName = repositoryName;
		this.comment = comment;
		super.lineItem = item;
		this.salesType = salesType;
	}
	
	public String getMemberID() {
		return memberID;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public String getComment() {
		return comment;
	}

	public ArrayList<LineItemVO> getItem() {
		return super.lineItem;
	}

	public SalesReceiptType getSalesType() {
		return salesType;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setItem(ArrayList<LineItemVO> item) {
		super.lineItem = item;
	}

	public void setSalesType(SalesReceiptType salesType) {
		this.salesType = salesType;
	}
	
}
