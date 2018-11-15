package PromotionStrategybl;

import java.util.ArrayList;

import PO.LineItemPO;
import PO.PromotionBySumPO;
import PO.SalesReceiptPO;
import PO.VoucherPO;

public class PromotionBySum extends Promotion{
	PromotionBySumPO promotion;
	
	public PromotionBySum(PromotionBySumPO promotion){
		super();
		this.promotion = promotion;
	}

	public PromotionBySum(SalesReceiptPO sales,PromotionBySumPO promotion){
		super(sales);
		this.promotion = promotion;
	}

	public PromotionBySum() {
		super();
	}

	public PromotionBySumPO getPromotion() {
		return promotion;
	}

	public void setPromotion(PromotionBySumPO promotion) {
		this.promotion = promotion;
	}
	
	public ArrayList<LineItemPO> getGiftList(){
		return promotion.getGiftAsList();
	}
	
	public ArrayList<VoucherPO> getVoucherList(){
		return promotion.getVoucherAsList();
	}

	@Override
	public boolean judgeValid() {
		// TODO Auto-generated method stub
		if(sales.getSumBeforeDiscount() >= promotion.getMinAmount() && sales.getSumBeforeDiscount() < promotion.getMaxAmount()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String toShow(){
		return this.promotion.toShow();
	}

}
