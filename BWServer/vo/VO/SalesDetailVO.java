package VO;

import java.io.Serializable;
import java.util.Date;

import export.Exportable;
import export.annotation.ExportField;

/**
 *
 * @author 82646 这个vo是在销售明细表里面使用，包含了一次销售(只是销售单)的一件产品的销售情况
 * 默认排序：先按照商品名排序，商品名相同按照销售时间排序
 *
 */
public class SalesDetailVO implements Serializable, Exportable<SalesDetailVO>,Comparable<SalesDetailVO> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ExportField(name = "日期", nameRow = 0, nameCol = 0)
	private Date date;

	@ExportField(name = "商品名稱", nameRow = 0, nameCol = 1)
	private String commodityName;

	@ExportField(name = "商品型號", nameRow = 0, nameCol = 2)
	private String version;// 商品的型号

	@ExportField(name = "數量", nameRow = 0, nameCol = 3)
	private int quantity;

	@ExportField(name = "單價", nameRow = 0, nameCol = 4)
	private double priceForEach;

	@ExportField(name = "總價", nameRow = 0, nameCol = 5)
	private double totalPrice;

	public SalesDetailVO(Date date, String commodityName, String version, int quantity, double priceForEach,
			double totalPrice) {
		super();
		this.date = date;
		this.commodityName = commodityName;
		this.version = version;
		this.quantity = quantity;
		this.priceForEach = priceForEach;
		this.totalPrice = totalPrice;
	}

	public SalesDetailVO() {

	}

	public Date getDate() {
		return date;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public String getVersion() {
		return version;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPriceForEach() {
		return priceForEach;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPriceForEach(double priceForEach) {
		this.priceForEach = priceForEach;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "SalesDetailVO [date=" + date + ", commodityName=" + commodityName + ", version=" + version
				+ ", quantity=" + quantity + ", priceForEach=" + priceForEach + ", totalPrice=" + totalPrice + "]";
	}

	@Override
	public int compareTo(SalesDetailVO o) {
		// TODO Auto-generated method stub
		if(this.commodityName.compareTo(o.commodityName) != 0){
			return this.commodityName.compareTo(o.commodityName);
		}

		return o.getDate().compareTo(this.getDate());
	}

}
