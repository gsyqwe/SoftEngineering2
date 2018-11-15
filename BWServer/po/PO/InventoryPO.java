package PO;

import VO.InventoryVO;

// 庫存盤點 快照
public final class InventoryPO {
	public static InventoryPO SAMPLE_1 = new InventoryPO("USB燈", "v1.0", 20, 19.9);
	public static InventoryPO SAMPLE_2 = new InventoryPO("高級水晶燈", "v2.0", 9, 799.9);
	public static InventoryPO SAMPLE_3 = new InventoryPO("酒吧炫目射燈", "v2.0", 17, 559.9);
	public static InventoryPO SAMPLE_4 = new InventoryPO("學生必備護眼台燈", "v1.0", 50, 29.9);
	public static InventoryPO SAMPLE_5 = new InventoryPO("紅綠燈", "v1.0", 2, 99999.9);

	private double averagePrice; // 庫存均價, 是進價, 不是售價
	/*
	 * key for database self-increment
	 */
	private long id;
	private String name; // 商品名稱
	private int quantity; // 庫存數量
	private String version; // 型號
	private boolean isDeleted;

	public InventoryPO() {
	}

	public InventoryPO(String name, String version, int quantity, double averagePrice) {
		this.name = name;
		this.version = version;
		this.quantity = quantity;
		this.averagePrice = averagePrice;
	}

	public double getAveragePrice() {
		return averagePrice;
	}

	// for database
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getVersion() {
		return version;
	}

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}

	// for database
	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "InventoryPO [averagePrice=" + averagePrice + ", id=" + id + ", name=" + name + ", quantity=" + quantity
				+ ", version=" + version + "]";
	}

	public InventoryVO toVO() {
		return new InventoryVO(averagePrice, name, quantity, version);
	}
}
