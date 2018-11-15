package PromotionStrategybl;

import PO.SalesReceiptPO;
/**
 *Promotion逻辑领域对象，组合SalesReceiptPO，对当前Sales是否符合Promotion的触发条件进行判断，如果符合，将自己加入那个Sales中
 *只是提供给Sales使用，在实际填写创建Promotion的时候不使用这个Promotion 
 *
 */
public abstract class Promotion {
	SalesReceiptPO sales;
	
	public Promotion(SalesReceiptPO sales) {
		this();
		this.sales = sales;
	}
	
	public Promotion(){
		
	}
	
	public SalesReceiptPO getSalesReceipt(){
		return sales;
	}

	public void setSales(SalesReceiptPO sales) {
		this.sales = sales;
	}
	
	public abstract boolean judgeValid();
	
}
