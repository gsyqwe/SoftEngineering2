package PO;

import java.util.HashSet;
import java.util.Set;

import VO.LineItemVO;

public class LineItemPO {

	public static LineItemPO SAMPLE_1 = new LineItemPO("COM-00001", 29, 19.9);
	public static LineItemPO SAMPLE_2 = new LineItemPO("COM-00002", 19, 9.9);
	public static LineItemPO SAMPLE_3 = new LineItemPO("COM-00003", 90, 789.2);
	public static LineItemPO SAMPLE_4 = new LineItemPO("COM-00004", 56, 89.1);
	public static LineItemPO SAMPLE_5 = new LineItemPO("COM-00005", 25, 99.2);

	static {
		SAMPLE_1.setComment("特價商品");
		SAMPLE_2.setComment("特價商品");
		SAMPLE_3.setComment("VIP優惠");
		SAMPLE_4.setComment("優惠價");
		SAMPLE_5.setComment("組合商品");
	}

	private String comment;
	private String id;
	private int quantity;// 在用于财务类单据的时候，quantity不进行填写
	private double price;
	private boolean isDeleted;

	/*
	 * key for database self-incrment
	 */
	private long lineItemID;
	/*
	 * database mapping
	 */
	private Set<PromotionByCombinationListPO> promotionByCombinationListPOs = new HashSet<>();
	/*
	 * database mapping
	 */
	private Set<PromotionBySumPO> promotionBySumPOs = new HashSet<>();
	/*
	 * database mapping
	 */
	private Set<ReceiptPO> receiptPOs = new HashSet<>();
	/*
	 * database mapping
	 */
	private Set<PromotionByLevelPO> promotionByLevelPOs = new HashSet<>();

	public LineItemPO() {

	}

	public LineItemPO(String id, int quantity, double price) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}

	public LineItemPO(String id, int quantity, double price, String comment) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public String getId() {
		return id;
	}

	// for database
	public long getLineItemID() {
		return lineItemID;
	}

	public double getPrice() {
		return price;
	}

	// for database
	public Set<PromotionByCombinationListPO> getPromotionByCombinationListPOs() {
		return promotionByCombinationListPOs;
	}

	// for database
	public Set<PromotionByLevelPO> getPromotionByLevelPOs() {
		return promotionByLevelPOs;
	}

	// for database
	public Set<PromotionBySumPO> getPromotionBySumPOs() {
		return promotionBySumPOs;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getQuantity() {
		return quantity;
	}

	// for database
	public Set<ReceiptPO> getReceiptPOs() {
		return receiptPOs;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setId(String id) {
		this.id = id;
	}

	// for database
	public void setLineItemID(long lineItemID) {
		this.lineItemID = lineItemID;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// for database
	public void setPromotionByCombinationListPOs(Set<PromotionByCombinationListPO> promotionByCombinationListPOs) {
		this.promotionByCombinationListPOs = promotionByCombinationListPOs;
	}

	// for database
	public void setPromotionByLevelPOs(Set<PromotionByLevelPO> promotionByLevelPOs) {
		this.promotionByLevelPOs = promotionByLevelPOs;
	}

	// for database
	public void setPromotionBySumPOs(Set<PromotionBySumPO> promotionBySumPOs) {
		this.promotionBySumPOs = promotionBySumPOs;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// for database
	public void setReceiptPOs(Set<ReceiptPO> receiptPOs) {
		this.receiptPOs = receiptPOs;
	}

	@Override
	public String toString() {
		return "LineItemPO [comment=" + comment + ", id=" + id + ", quantity=" + quantity + ", price=" + price
				+ ", lineItemID=" + lineItemID + ", promotionByCombinationListPOs=" + promotionByCombinationListPOs
				+ ", promotionBySumPOs=" + promotionBySumPOs + ", receiptPOs=" + receiptPOs + ", promotionByLevelPOs="
				+ promotionByLevelPOs + "]";
	}

	public LineItemVO toVO() {
		return new LineItemVO(id, quantity, price, comment);
	}
}
