package VO;

import java.io.Serializable;

import PO.*;
import export.Exportable;
import export.annotation.ExportField;

public class CommodityVO implements Serializable,Exportable<CommodityVO> {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@ExportField(name = "警戒數量", nameRow = 0, nameCol = 0)
	private int alertQuantity;// 警戒数量

	@ExportField(name = "進價", nameRow = 0, nameCol = 1)
	private double bid; // 进价

	@ExportField(name = "分類", nameRow = 0, nameCol = 2)
	private String category; // 分类 categoryid

	@ExportField(name = "編號", nameRow = 0, nameCol = 3)
	private String id; // 编号

	@ExportField(name = "名稱", nameRow = 0, nameCol = 4)
	private String name; // 名称

	@ExportField(name = "庫存數量", nameRow = 0, nameCol = 5)
	private int quantity; // 库存数量

	@ExportField(name = "最近進價", nameRow = 0, nameCol = 6)
	private double recentBid; // 最近进价

	@ExportField(name = "最近零售價", nameRow = 0, nameCol = 7)
	private double recentRetailPrice; // 最近零售价

	@ExportField(name = "零售價", nameRow = 0, nameCol = 8)
	private double retailPrice; // 零售价

	@ExportField(name = "型號", nameRow = 0, nameCol = 9)
	private String version; // 型号

	public CommodityVO() {
		super();
	}

	public CommodityVO(int alertQuantity, double bid, String category, String id, String name, int quantity,
			double recentBid, double recentRetailPrice, double retailPrice, String version) {
		super();
		this.alertQuantity = alertQuantity;
		this.bid = bid;
		this.category = category;
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.recentBid = recentBid;
		this.recentRetailPrice = recentRetailPrice;
		this.retailPrice = retailPrice;
		this.version = version;
	}

	public int getAlertQuantity() {
		return alertQuantity;
	}

	public void setAlertQuantity(int alertQuantity) {
		this.alertQuantity = alertQuantity;
	}

	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getRecentBid() {
		return recentBid;
	}

	public void setRecentBid(double recentBid) {
		this.recentBid = recentBid;
	}

	public double getRecentRetailPrice() {
		return recentRetailPrice;
	}

	public void setRecentRetailPrice(double recentRetailPrice) {
		this.recentRetailPrice = recentRetailPrice;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public CommodityPO toPO() {
		return new CommodityPO(name, id, category, version, quantity, bid, retailPrice, recentBid, recentRetailPrice,
				alertQuantity);
	}

}
