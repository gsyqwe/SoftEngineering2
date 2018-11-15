package VO;

import java.io.Serializable;

import PO.*;

public class InventoryVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double averagePrice; // 搴瓨鍧囧児, 鏄�插児, 涓嶆槸鍞児
	private String name; // 鍟嗗搧鍚嶇ū
	private int quantity; // 搴瓨鏁搁噺
	private String version; // 鍨嬭櫉

	public InventoryVO(double averagePrice,String name, int quantity,
			String version) {
		super();
		this.averagePrice = averagePrice;
		this.name = name;
		this.quantity = quantity;
		this.version = version;
	}

	public InventoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public InventoryPO toPO(){
		InventoryPO po=new InventoryPO();
		po.setAveragePrice(averagePrice);
		po.setName(name);
		po.setQuantity(quantity);
		po.setVersion(version);
		return po;
	}
}
