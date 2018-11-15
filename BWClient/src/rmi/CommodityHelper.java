package rmi;

import java.rmi.Remote;

import service.CommodityblService;

public class CommodityHelper {
	private Remote remote;
	private static CommodityHelper commodityHelper;
	public static CommodityHelper getInstance(){
		if(commodityHelper==null)
			commodityHelper=new CommodityHelper();
		return commodityHelper;
	}
	public static CommodityHelper getInstance(Remote r){
		if(commodityHelper==null)
			commodityHelper=new CommodityHelper(r);
		return commodityHelper;
	}
	
	private  CommodityHelper() {
	}
	private  CommodityHelper(Remote r) {
		this.remote=r;
	}
	
	public CommodityblService getService(){
		return (CommodityblService) remote;
	}
}
