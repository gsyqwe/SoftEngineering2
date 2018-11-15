package VO;

import java.io.Serializable;

import PO.LineItemPO;

public class LineItemVO implements Serializable{//单据里面的一行
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private int quantity;

	private double price;

	private String comment;
	
	public LineItemVO(String id, int quantity, double price) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}

	public LineItemVO(String id, int quantity, double price, String comment) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.comment = comment;
	}
	public LineItemVO() {
		super();
	}

	public String getComment() {
		return comment;
	}

	public String getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LineItemPO toPO(){
		return new LineItemPO(id,quantity,price,comment);
	}

}
