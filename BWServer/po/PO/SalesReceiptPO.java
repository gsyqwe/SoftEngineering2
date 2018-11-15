package PO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import VO.LineItemVO;
import VO.SalesReceiptVO;
import VO.VoucherVO;
import enums.ReceiptState;
import enums.ReceiptType;
import enums.SalesReceiptType;


public class SalesReceiptPO extends ReceiptPO {
	private static Set<LineItemPO> LINE_ITEM_1 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_2 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_3 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_4 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_5 = new HashSet<>();
	private static Set<VoucherPO> VOUCHER_1 = new HashSet<>();
	private static Set<VoucherPO> VOUCHER_2 = new HashSet<>();
	private static Set<VoucherPO> VOUCHER_3 = new HashSet<>();
	private static Set<VoucherPO> VOUCHER_4 = new HashSet<>();
	private static Set<VoucherPO> VOUCHER_5 = new HashSet<>();
	
	static {
		VOUCHER_1.add(VoucherPO.SAMPLE_4);
		VOUCHER_1.add(VoucherPO.SAMPLE_3);
		VOUCHER_2.add(VoucherPO.SAMPLE_2);
		VOUCHER_2.add(VoucherPO.SAMPLE_1);
		VOUCHER_3.add(VoucherPO.SAMPLE_5);
		VOUCHER_3.add(VoucherPO.SAMPLE_4);
		VOUCHER_3.add(VoucherPO.SAMPLE_3);
		VOUCHER_4.add(VoucherPO.SAMPLE_2);
		VOUCHER_4.add(VoucherPO.SAMPLE_1);
		VOUCHER_4.add(VoucherPO.SAMPLE_5);
		VOUCHER_4.add(VoucherPO.SAMPLE_4);
		VOUCHER_5.add(VoucherPO.SAMPLE_3);
		VOUCHER_5.add(VoucherPO.SAMPLE_2);
		VOUCHER_5.add(VoucherPO.SAMPLE_1);
		LINE_ITEM_1.add(LineItemPO.SAMPLE_4);
		LINE_ITEM_1.add(LineItemPO.SAMPLE_3);
		LINE_ITEM_1.add(LineItemPO.SAMPLE_2);
		LINE_ITEM_1.add(LineItemPO.SAMPLE_1);
		LINE_ITEM_2.add(LineItemPO.SAMPLE_5);
		LINE_ITEM_2.add(LineItemPO.SAMPLE_4);
		LINE_ITEM_3.add(LineItemPO.SAMPLE_3);
		LINE_ITEM_3.add(LineItemPO.SAMPLE_2);
		LINE_ITEM_3.add(LineItemPO.SAMPLE_1);
		LINE_ITEM_4.add(LineItemPO.SAMPLE_5);
		LINE_ITEM_4.add(LineItemPO.SAMPLE_4);
		LINE_ITEM_5.add(LineItemPO.SAMPLE_3);
		LINE_ITEM_5.add(LineItemPO.SAMPLE_2);
		LINE_ITEM_5.add(LineItemPO.SAMPLE_1);

	}
	public static SalesReceiptPO SAMPLE_1 = new SalesReceiptPO(ReceiptState.UNAPPROVEAL, "SAL-00001", new Date(),
			UserPO.SAMPLE_1.getId(), MemberPO.SAMPLE_1.getId(), "Repository1", "comment_1", LINE_ITEM_1, 20,
			VoucherPO.SAMPLE_1, SalesReceiptType.PURCHASE_RETURN, 50, 10);
	public static SalesReceiptPO SAMPLE_2 = new SalesReceiptPO(ReceiptState.UNCOMMITTED, "SAL-00002", new Date(),
			UserPO.SAMPLE_2.getId(), MemberPO.SAMPLE_1.getId(), "Repository1", "comment_2", LINE_ITEM_2, 20,
			VoucherPO.SAMPLE_2, SalesReceiptType.PURCHASE, 60, 22);
	public static SalesReceiptPO SAMPLE_3 = new SalesReceiptPO(ReceiptState.SUBMITTED, "SAL-00003", new Date(),
			UserPO.SAMPLE_3.getId(), MemberPO.SAMPLE_1.getId(), "Repository1", "comment_3", LINE_ITEM_3, 20,
			VoucherPO.SAMPLE_3, SalesReceiptType.SALES_RETURN, 70, 30);
	public static SalesReceiptPO SAMPLE_4 = new SalesReceiptPO(ReceiptState.VERIFIED, "SAL-00004", new Date(),
			UserPO.SAMPLE_4.getId(), MemberPO.SAMPLE_1.getId(), "Repository1", "comment_4", LINE_ITEM_4, 20,
			VoucherPO.SAMPLE_4, SalesReceiptType.PURCHASE, 80, 40);
	public static SalesReceiptPO SAMPLE_5 = new SalesReceiptPO(ReceiptState.SUBMITTED, "SAL-00005", new Date(),
			UserPO.SAMPLE_3.getId(), MemberPO.SAMPLE_1.getId(), "Repository1", "comment_5", LINE_ITEM_5, 20,
			VoucherPO.SAMPLE_5, SalesReceiptType.SALES, 90, 50);
	static{
		SAMPLE_1.setVoucherReceived(VOUCHER_1);
		SAMPLE_2.setVoucherReceived(VOUCHER_2);
		SAMPLE_3.setVoucherReceived(VOUCHER_3);
		SAMPLE_4.setVoucherReceived(VOUCHER_4);
		SAMPLE_5.setVoucherReceived(VOUCHER_5);
	}
	private String comment;

	private double discount;

	/*
	 * database mapping
	 */
	private Set<LineItemPO> lineItemReceived = new HashSet<>();

	private String memberID;

	private String receivedID;// 由销售单触发的库存赠送单的ID

	private String repositoryName;

	private SalesReceiptType salesType;

	private double sumAfterDiscount;

	private double sumBeforeDiscount;
	/*
	 * database mapping
	 */
	private Set<VoucherPO> voucherReceived = new HashSet<>();

	private VoucherPO voucher;

	public SalesReceiptPO() {
		super();
	}

	public SalesReceiptPO(ReceiptState state, String id, Date date, String operatorID, String memberID,
			String repository, String comment, ArrayList<LineItemPO> item, double discount, VoucherPO voucher,
			SalesReceiptType salesType, double sumBeforeDiscount, double sumAfterDiscount) {
		super(ReceiptType.SALE_RECEIPT, state, id, operatorID, date);
		this.memberID = memberID;
		this.repositoryName = repository;
		this.comment = comment;
		Set<LineItemPO> resultitem = new HashSet<>(item);
		this.lineItem = resultitem;
		this.discount = discount;
		this.voucher = voucher;
		this.salesType = salesType;
		this.sumBeforeDiscount = sumBeforeDiscount;
		this.sumAfterDiscount = sumAfterDiscount;
	}

	/**
	 * @author JiYuzhe 想在SalesReceiptPO里面增加两个属性，ArrayList<VoucherPO>
	 *         vouchersReceive 表示这个SalesReceipt里面所触发赠送的vouchers
	 *         ArrayList<LineItemPO> lineItemReceive
	 *         表示这个SalesReceipt里面所触发得到的赠送的商品列表 再增加一个String
	 *         receivedID来表示该单据触发的库存赠送单的编号 这两个属性在进货单和进货退货单里面都为null，在销售退货单中
	 */

	public SalesReceiptPO(ReceiptState state, String id, Date date, String operatorID, String memberID,
			String repository, String comment, Set<LineItemPO> item, double discount, VoucherPO voucher,
			SalesReceiptType salesType, double sumBeforeDiscount, double sumAfterDiscount) {
		super(ReceiptType.SALE_RECEIPT, state, id, operatorID, date);
		this.memberID = memberID;
		this.repositoryName = repository;
		this.comment = comment;
		this.lineItem = item;
		this.discount = discount;
		this.voucher = voucher;
		this.salesType = salesType;
		this.sumBeforeDiscount = sumBeforeDiscount;
		this.sumAfterDiscount = sumAfterDiscount;
	}

	public SalesReceiptPO(ReceiptType type, ReceiptState state, String id, Date date, String operatorID) {
		super(ReceiptType.SALE_RECEIPT, state, id, operatorID, date);
	}

	public SalesReceiptPO(ReceiptType type, ReceiptState state, String id, String operatorID, Date date,
			ArrayList<LineItemPO> lineItem, String memberID, String repositoryName, String comment,
			double sumBeforeDiscount, double discount, VoucherPO voucher, SalesReceiptType salesType,
			double sumAfterDiscount, ArrayList<VoucherPO> voucherReceived, ArrayList<LineItemPO> lineItemReceived,
			String receivedID) {
		super(type, state, id, operatorID, date, lineItem);
		this.memberID = memberID;
		this.repositoryName = repositoryName;
		this.comment = comment;
		this.sumBeforeDiscount = sumBeforeDiscount;
		this.discount = discount;
		this.voucher = voucher;
		this.salesType = salesType;
		this.sumAfterDiscount = sumAfterDiscount;
		Set<VoucherPO> resultVoucher = new HashSet<>(voucherReceived);
		Set<LineItemPO> resultItem = new HashSet<>(lineItemReceived);
		this.voucherReceived = resultVoucher;
		this.lineItemReceived = resultItem;
		this.receivedID = receivedID;
	}

	public void addLineItem(LineItemPO po) {
		this.lineItem.add(po);
	}

	public String getComment() {
		return comment;
	}

	public double getDiscount() {
		return discount;
	}

	// for database
	public Set<LineItemPO> getLineItemReceived() {
		return lineItemReceived;
	}

	public ArrayList<LineItemPO> getLineItemReceivedAsList() {
		ArrayList<LineItemPO> resultitem = new ArrayList<>(lineItemReceived);
		return resultitem;
	}

	public String getMemberID() {
		return memberID;
	}

	public String getReceivedID() {
		return receivedID;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public SalesReceiptType getSalesType() {
		return salesType;
	}

	public double getSumAfterDiscount() {
		return sumAfterDiscount;
	}

	public double getSumBeforeDiscount() {
		return sumBeforeDiscount;
	}

	public VoucherPO getVoucher() {
		return voucher;
	}

	// for database
	public Set<VoucherPO> getVoucherReceived() {
		return voucherReceived;
	}

	public ArrayList<VoucherPO> getVoucherReceivedAsList() {
		ArrayList<VoucherPO> resultVoucher = new ArrayList<>(voucherReceived);
		return resultVoucher;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setLineItemReceived(ArrayList<LineItemPO> lineItemReceived) {
		Set<LineItemPO> resultItem = new HashSet<>(lineItemReceived);
		this.lineItemReceived = resultItem;
	}

	// for database
	public void setLineItemReceived(Set<LineItemPO> lineItemReceived) {
		this.lineItemReceived = lineItemReceived;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public void setReceivedID(String receivedID) {
		this.receivedID = receivedID;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public void setSalesType(SalesReceiptType salesType) {
		this.salesType = salesType;
	}

	public void setSumAfterDiscount(double sumAfterDiscount) {
		this.sumAfterDiscount = sumAfterDiscount;
	}

	public void setSumBeforeDiscount(double sumBeforeDiscount) {
		this.sumBeforeDiscount = sumBeforeDiscount;
	}

	public void setVoucher(VoucherPO voucher) {
		this.voucher = voucher;
	}

	public void setVoucherReceived(ArrayList<VoucherPO> voucherReceived) {
		Set<VoucherPO> resultSet = new HashSet<>(voucherReceived);
		this.voucherReceived = resultSet;
	}

	// for database
	public void setVoucherReceived(Set<VoucherPO> voucherReceived) {
		this.voucherReceived = voucherReceived;
	}

	@Override
	public String toString() {
		return "SalesReceiptPO [comment=" + comment + ", discount=" + discount + ", lineItemReceived="
				+ lineItemReceived + ", memberID=" + memberID + ", receivedID=" + receivedID + ", repositoryName="
				+ repositoryName + ", salesType=" + salesType + ", sumAfterDiscount=" + sumAfterDiscount
				+ ", sumBeforeDiscount=" + sumBeforeDiscount + ", voucherReceived=" + voucherReceived + ", voucher="
				+ voucher + ", type=" + type + ", state=" + state + ", id=" + id + ", operatorID=" + operatorID
				+ ", date=" + date + ", lineItem=" + lineItem + "]";
	}

	@Override
	public SalesReceiptVO toVO() {
		ArrayList<LineItemVO> items = new ArrayList<>();
		ArrayList<LineItemVO> itemsReceived = new ArrayList<>();
		ArrayList<VoucherVO> voucherReceived = new ArrayList<>();

		for (LineItemPO i : lineItem) {
			items.add(i.toVO());
		}

		for (LineItemPO i : this.lineItemReceived) {
			itemsReceived.add(i.toVO());
		}

		for (VoucherPO i : this.voucherReceived) {
			voucherReceived.add(i.toVO());
		}

		VoucherVO voucherVO = null;
		if(voucher != null){
			voucherVO = voucher.toVO();
		}
		return new SalesReceiptVO(ReceiptType.SALE_RECEIPT, state, id, operatorID, date, comment, discount,
				itemsReceived, memberID, this.receivedID, repositoryName, salesType, sumAfterDiscount,
				sumBeforeDiscount, voucherReceived, voucherVO, items);
	}

}