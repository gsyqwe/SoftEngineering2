package VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import PO.LineItemPO;
import PO.SalesReceiptPO;
import PO.VoucherPO;
import enums.ReceiptState;
import enums.ReceiptType;
import enums.SalesReceiptType;

public class SalesReceiptVO extends ReceiptVO implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String comment;

	private double discount;

	private ArrayList<LineItemVO> lineItemReceived = new ArrayList<>();

	private String memberID;

	private String receivedID;// 由销售单触发的库存赠送单的ID

	private String repositoryName;

	private SalesReceiptType salesType;

	private double sumAfterDiscount;

	private double sumBeforeDiscount;

	private ArrayList<VoucherVO> voucherReceived = new ArrayList<>();

	private VoucherVO voucher;

	public SalesReceiptVO() {
		super();
	}

	public SalesReceiptVO(ReceiptType type, ReceiptState state, String id,
			String operatorID, Date date, String comment, double discount,
			ArrayList<LineItemVO> lineItemReceived, String memberID,
			String receivedID, String repositoryName,
			SalesReceiptType salesType, double sumAfterDiscount,
			double sumBeforeDiscount, ArrayList<VoucherVO> voucherReceived,
			VoucherVO voucher,ArrayList<LineItemVO> item) {
		super(type, state, id, operatorID, date);
		this.comment = comment;
		this.discount = discount;
		this.lineItemReceived = lineItemReceived;
		this.memberID = memberID;
		this.receivedID = receivedID;
		this.repositoryName = repositoryName;
		this.salesType = salesType;
		this.sumAfterDiscount = sumAfterDiscount;
		this.sumBeforeDiscount = sumBeforeDiscount;
		this.voucherReceived = voucherReceived;
		this.voucher = voucher;
		this.lineItem = item;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public ArrayList<LineItemVO> getLineItemReceived() {
		return lineItemReceived;
	}

	public void setLineItemReceived(ArrayList<LineItemVO> lineItemReceived) {
		this.lineItemReceived = lineItemReceived;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getReceivedID() {
		return receivedID;
	}

	public void setReceivedID(String receivedID) {
		this.receivedID = receivedID;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public SalesReceiptType getSalesType() {
		return salesType;
	}

	public void setSalesType(SalesReceiptType salesType) {
		this.salesType = salesType;
	}

	public double getSumAfterDiscount() {
		return sumAfterDiscount;
	}

	public void setSumAfterDiscount(double sumAfterDiscount) {
		this.sumAfterDiscount = sumAfterDiscount;
	}

	public double getSumBeforeDiscount() {
		return sumBeforeDiscount;
	}

	public void setSumBeforeDiscount(double sumBeforeDiscount) {
		this.sumBeforeDiscount = sumBeforeDiscount;
	}

	public ArrayList<VoucherVO> getVoucherReceived() {
		return voucherReceived;
	}

	public void setVoucherReceived(ArrayList<VoucherVO> voucherReceived) {
		this.voucherReceived = voucherReceived;
	}

	public VoucherVO getVoucher() {
		return voucher;
	}

	public void setVoucher(VoucherVO voucher) {
		this.voucher = voucher;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public SalesReceiptPO toPO(){
		 ArrayList<LineItemPO> items = new ArrayList<>();
		 ArrayList<LineItemPO> itemsReceived = new ArrayList<>();
		 ArrayList<VoucherPO> vouchersReceived = new ArrayList<>();
		 for(LineItemVO i:super.lineItem)
			 items.add(i.toPO());

		 for(LineItemVO item:this.lineItemReceived){
			 itemsReceived.add(item.toPO());
		 }

		 for(VoucherVO the_voucher:this.voucherReceived){
			 vouchersReceived.add(the_voucher.toPO());
		 }

		 VoucherPO voucherPO = null;
		 if(voucher != null){
			 voucherPO = voucher.toPO();
		 }
		 return new SalesReceiptPO(ReceiptType.SALE_RECEIPT,state, id, operatorID,date,items,memberID,repositoryName,
					comment, sumBeforeDiscount,discount, voucherPO,salesType,sumAfterDiscount,vouchersReceived,itemsReceived,this.receivedID);
	}


}
