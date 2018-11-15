package VO;

import java.io.Serializable;
import enums.StockInAndOut;

public class StockInAndOutVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StockInAndOut type;
	int number;
	String commodityID;
	double amount;
	public StockInAndOutVO(StockInAndOut type, int number, String commodityID,double amount) {
		super();
		this.type = type;
		this.number = number;
		this.commodityID = commodityID;
		this.amount=amount;
	}
	public StockInAndOutVO() {
		super();
	}
	public StockInAndOut getType() {
		return type;
	}
	public int getNumber() {
		return number;
	}
	public String getCommodityID() {
		return commodityID;
	}
	public double getAmount(){
		return amount;
	}
	public void setType(StockInAndOut type) {
		this.type = type;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void setCommodityID(String commodityID) {
		this.commodityID = commodityID;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
