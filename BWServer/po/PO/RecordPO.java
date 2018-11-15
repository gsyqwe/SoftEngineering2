package PO;

import java.util.Date;

import VO.RecordVO;
import enums.StockInAndOut;

// 出庫入庫紀綠 
public final class RecordPO {
	public static RecordPO SAMPLE_1 = new RecordPO("COM-00001", 20, new Date(new Date().getTime() - 12433610),
			StockInAndOut.STOCK_OUT, 97.5);
	public static RecordPO SAMPLE_2 = new RecordPO("COM-00002", 18, new Date(new Date().getTime() - 124322360),
			StockInAndOut.STOCK_IN, 49.4);
	public static RecordPO SAMPLE_3 = new RecordPO("COM-00003", 36, new Date(new Date().getTime() - 124533360),
			StockInAndOut.STOCK_OUT, 39.9);
	public static RecordPO SAMPLE_4 = new RecordPO("COM-00004", 99, new Date(new Date().getTime() - 12453360),
			StockInAndOut.STOCK_OUT, 9.0);
	public static RecordPO SAMPLE_5 = new RecordPO("COM-00005", 34, new Date(new Date().getTime() - 21243360),
			StockInAndOut.STOCK_IN, 20.4);

	private double amount; // 金額
	private String commodityID;
	/*
	 * key for database self-increment
	 */
	private long id;
	private int quantity; // 數量
	private Date stockInAndOutTime;
	private StockInAndOut stockInAndOutType;
	private boolean isDeleted;

	public RecordPO() {
		// TODO Auto-generated constructor stub
	}
	public RecordPO(String commodityID, int quantity, Date stockInAndOutTime, StockInAndOut stockInAndOutType,
			double amount) {
		this.commodityID = commodityID;
		this.quantity = quantity;
		this.stockInAndOutTime = stockInAndOutTime;
		this.stockInAndOutType = stockInAndOutType;
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public String getCommodityID() {
		return commodityID;
	}


	// for database
	public long getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public Date getStockInAndOutTime() {
		return stockInAndOutTime;
	}

	public StockInAndOut getStockInAndOutType() {
		return stockInAndOutType;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setCommodityID(String commodityID) {
		this.commodityID = commodityID;
	}


	// for database
	public void setId(long id) {
		this.id = id;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setStockInAndOutTime(Date stockInAndOutTime) {
		this.stockInAndOutTime = stockInAndOutTime;
	}

	public void setStockInAndOutType(StockInAndOut stockInAndOutType) {
		this.stockInAndOutType = stockInAndOutType;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "RecordPO [amount=" + amount + ", commodityID=" + commodityID + ",  id=" + id
				+ ", quantity=" + quantity + ", stockInAndOutTime=" + stockInAndOutTime + ", stockInAndOutType="
				+ stockInAndOutType + "]";
	}

	public RecordVO toVO() {
		return new RecordVO(amount, commodityID, id, quantity, stockInAndOutTime, stockInAndOutType);
	}

}
