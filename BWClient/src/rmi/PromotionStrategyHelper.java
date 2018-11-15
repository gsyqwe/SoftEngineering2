package rmi;

import java.rmi.Remote;
import service.PromotionStrategyblService;

public class PromotionStrategyHelper {
	private Remote remote;
	private static PromotionStrategyHelper promotionStrategyHelper;
	public static PromotionStrategyHelper getInstance(){
		if(promotionStrategyHelper==null)
			promotionStrategyHelper=new PromotionStrategyHelper();
		return promotionStrategyHelper;
	}
	public static PromotionStrategyHelper getInstance(Remote r){
		if(promotionStrategyHelper==null)
			promotionStrategyHelper=new PromotionStrategyHelper(r);
		return promotionStrategyHelper;
	}
	
	private  PromotionStrategyHelper() {
	}
	private  PromotionStrategyHelper(Remote r) {
		this.remote=r;
	}
	public PromotionStrategyblService getService(){
		return (PromotionStrategyblService) remote;
	}
}
