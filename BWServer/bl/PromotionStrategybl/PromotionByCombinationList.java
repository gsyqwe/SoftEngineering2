package PromotionStrategybl;

import java.util.ArrayList;
import java.util.Collections;

import PO.LineItemPO;
import PO.PromotionByCombinationListPO;
import PO.SalesReceiptPO;

/**
 *
 * @author JiYuzhe
 *	客户代码在使用这个类的时候，只需要调用totalDiscount()方法即可，times是不会暴露给客户代码的
 */

public class PromotionByCombinationList extends Promotion{

	PromotionByCombinationListPO promotion;
	ArrayList<Double> priceList = new ArrayList<>();
	private int times = 0;//这个属性用来保存这个商品组合被触发了几次，比如说2件A商品，3件B商品触发，那么4件A商品，6件B商品就会触发两次

	public PromotionByCombinationList(SalesReceiptPO sales, PromotionByCombinationListPO promotion) {
		super(sales);
		this.promotion = promotion;
	}

	public PromotionByCombinationList() {
		super();
	}

	@Override
	public boolean judgeValid() {
		// TODO Auto-generated method stub
		ArrayList<LineItemPO> promotionLineItem = promotion.getCombinationAsList();
		ArrayList<LineItemPO> salesLineItem = sales.getLineItemAsList();
		for(LineItemPO lineItem:promotionLineItem){
			boolean flag = false;
			for(LineItemPO salesLine:salesLineItem){
				if(lineItem.getId().equals(salesLine.getId()) && lineItem.getQuantity() <= salesLine.getQuantity()){
					flag = true;
					priceList.add(salesLine.getPrice());
					break;
				}
			}

			if(flag == false){
				return false;
			}

		}
		return true;
	}

	private void setTimes(){//在setTimes的时候已经确定是触发了这个promotion，计算能触发几次
		ArrayList<LineItemPO> promotionLineItem = promotion.getCombinationAsList();
		ArrayList<LineItemPO> salesLineItem = sales.getLineItemAsList();

		ArrayList<Double> time_count = new ArrayList<>();
		for(LineItemPO lineItem:promotionLineItem){
			for(LineItemPO salesLine:salesLineItem){
				if(lineItem.getId().equals(salesLine.getId())){
					double times = (salesLine.getQuantity() * 1.0) / lineItem.getQuantity();
					time_count.add(times);
					break;
				}
			}
		}

		Collections.sort(time_count);
		times = time_count.get(0).intValue();

		return;
	}

	public String toShow(){
		return this.promotion.toShow();
	}

	public double totalDiscount(){//供客户代码使用，用times*dicount
		setTimes();//
		double before = 0;

		for(int i = 0;i < promotion.getCombinationAsList().size();i++){
			before = before + promotion.getCombinationAsList().get(i).getQuantity() * priceList.get(i);
		}

		if(before <= promotion.getPriceAfter()){
			return 0;
		}

		double discountEach = before - promotion.getPriceAfter();
		return discountEach * times;
	}

}
