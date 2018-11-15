package VO;

import java.io.Serializable;
import java.util.Date;

import PO.*;
import enums.StockInAndOut;

public class RecordVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double amount; // 金額
	private String commodityID;
	private long id;
	private int quantity; // 數量
	private Date stockInAndOutTime;
	private StockInAndOut stockInAndOutType;

	// 期初建账记录，也不需要复选框

	public RecordVO() {
		super();
	}

	public RecordVO(double amount, String commodityID, long id,
			int quantity, Date stockInAndOutTime,
			StockInAndOut stockInAndOutType) {
		super();
		this.amount = amount;
		this.commodityID = commodityID;
		this.id = id;
		this.quantity = quantity;
		this.stockInAndOutTime = stockInAndOutTime;
		this.stockInAndOutType = stockInAndOutType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCommodityID() {
		return commodityID;
	}

	public void setCommodityID(String commodityID) {
		this.commodityID = commodityID;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getStockInAndOutTime() {
		return stockInAndOutTime;
	}

	public void setStockInAndOutTime(Date stockInAndOutTime) {
		this.stockInAndOutTime = stockInAndOutTime;
	}

	public StockInAndOut getStockInAndOutType() {
		return stockInAndOutType;
	}

	public void setStockInAndOutType(StockInAndOut stockInAndOutType) {
		this.stockInAndOutType = stockInAndOutType;
	}
	public RecordPO toPO(){
		RecordPO po=new RecordPO();
		po.setAmount(amount);
		po.setCommodityID(commodityID);
		po.setQuantity(quantity);
		po.setQuantity(quantity);
		po.setStockInAndOutTime(stockInAndOutTime);
		po.setStockInAndOutTime(stockInAndOutTime);
		return po;
		}
}
