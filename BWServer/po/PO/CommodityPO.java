package PO;

import java.util.HashSet;
import java.util.Set;

import VO.CommodityVO;

public final class CommodityPO {
	public static final CommodityPO SAMPLE_1 = new CommodityPO("Name_1_Keyword_1", "COM-20171207-00001", "Category_1",
			"Version_1", 20, 15, 10, 17, 12, 5);
	public static final CommodityPO SAMPLE_2 = new CommodityPO("Name_2_Keyword_1", "COM-20171207-00002", "Category_2",
			"Version_2", 220, 215, 170, 137, 122, 50);
	public static final CommodityPO SAMPLE_3 = new CommodityPO("Name_3_Keyword_2", "COM-20171207-00003", "Category_3",
			"Version_2", 100, 105, 10, 17, 12, 53);
	public static final CommodityPO SAMPLE_4 = new CommodityPO("Name_4_Keyword_2", "COM-20171207-00004", "Category_4",
			"Version_3", 1120, 187, 120, 127, 122, 10);
	public static final CommodityPO SAMPLE_5 = new CommodityPO("Name_5_Keyword_3", "COM-20171207-00005", "Category_5",
			"Version_4", 290, 195, 170, 137, 12, 15);
	private int alertQuantity;// 警戒数量
	private double bid; // 进价
	private String category; // 分类 categoryid
	/*
	 * key for database
	 */
	private String id; // 编号
	private String name; // 名称
	private int quantity; // 库存数量
	private double recentBid; // 最近进价
	private double recentRetailPrice; // 最近零售价
	private double retailPrice; // 零售价
	private String version; // 型号
	/*
	 * database mapping
	 */
	private Set<InitializeAccountPO> initializeAccountPOs = new HashSet<>();
	private boolean isDeleted;

	public CommodityPO() {
		// TODO Auto-generated constructor stub
	}

	public CommodityPO(String name, String id, String category, String version, int quantity, double bid,
			double retailPrice, double recentBid, double recentRetailPrice, int alertQuantity) {
		this.name = name;
		this.id = id;
		this.category = category;
		this.version = version;
		this.quantity = quantity;
		this.bid = bid;
		this.retailPrice = retailPrice;
		this.recentBid = recentBid;
		this.recentRetailPrice = recentRetailPrice;
		this.alertQuantity = alertQuantity;
	}

	public int getAlertQuantity() {
		return alertQuantity;
	}

	public double getBid() {
		return bid;
	}

	public String getCategory() {
		return category;
	}

	public String getId() {
		return id;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// for database
	public Set<InitializeAccountPO> getInitializeAccountPOs() {
		return initializeAccountPOs;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getRecentBid() {
		return recentBid;
	}

	public double getRecentRetailPrice() {
		return recentRetailPrice;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public String getVersion() {
		return version;
	}

	public void setAlertQuantity(int alertQuantity) {
		this.alertQuantity = alertQuantity;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setId(String id) {
		this.id = id;
	}

	// for database
	public void setInitializeAccountPOs(Set<InitializeAccountPO> initializeAccountPOs) {
		this.initializeAccountPOs = initializeAccountPOs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setRecentBid(double recentBid) {
		this.recentBid = recentBid;
	}

	public void setRecentRetailPrice(double recentRetailPrice) {
		this.recentRetailPrice = recentRetailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "CommodityPO [alertQuantity=" + alertQuantity + ", bid=" + bid + ", category=" + category + ", id=" + id
				+ ", name=" + name + ", quantity=" + quantity + ", recentBid=" + recentBid + ", recentRetailPrice="
				+ recentRetailPrice + ", retailPrice=" + retailPrice + ", version=" + version
				+ ", initializeAccountPOs=" + initializeAccountPOs + "]";
	}

	public CommodityVO toVO() {
		return new CommodityVO(alertQuantity, bid, category, id, name, quantity, recentBid, recentRetailPrice,
				retailPrice, version);
	}

}
