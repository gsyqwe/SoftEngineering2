package VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import PO.LineItemPO;
import PO.PromotionByCombinationListPO;

public class PromotionByCombinationListVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<LineItemVO> combination;
	private double priceAfter;
	private Date beginTime;
	private Date endTime;
	private String promotionID;//PBC-xxxxx promotion的命名不需要时间
	private String promotionName;

	public PromotionByCombinationListVO() {
		combination = new ArrayList<LineItemVO>();
	}

	public PromotionByCombinationListVO(Date beginTime, Date endTime, ArrayList<LineItemVO> combination,
			String promotionName) {
		this(beginTime, endTime, promotionName);
		this.combination = combination;
	}

	public String getPromotionID() {
		return promotionID;
	}

	public void setPromotionID(String promotionID) {
		this.promotionID = promotionID;
	}

	public PromotionByCombinationListVO(ArrayList<LineItemVO> combination, double priceAfter, Date beginTime,
			Date endTime, String promotionName,String promotionID) {
		super();
		this.combination = combination;
		this.priceAfter = priceAfter;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.promotionName = promotionName;
		this.promotionID = promotionID;
	}

	public PromotionByCombinationListVO(Date beginTime, Date endTime, String promotionName) {
		this();
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.promotionName = promotionName;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public ArrayList<LineItemVO> getCombination() {
		return combination;
	}

	public Date getEndTime() {
		return endTime;
	}

	public double getPriceAfter() {
		return priceAfter;
	}

	public double getPriceBefore() {// 对商品组合中的每个商品进行求价格并累加求得组合前的价格
		double priceBefore = 0;
		for (LineItemVO commodity : combination) {
			priceBefore = priceBefore + commodity.getPrice() * commodity.getQuantity();
		}

		return priceBefore;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public void setCombination(ArrayList<LineItemVO> combination) {
		this.combination = combination;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setPriceAfter(double priceAfter) {
		this.priceAfter = priceAfter;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	@Override
	public String toString() {
		return "PromotionByCombinationListPO [combination=" + combination + ", priceAfter=" + priceAfter
				+ ", beginTime=" + beginTime + ", endTime=" + endTime + ", promotionName=" + promotionName + "]";
	}
	public PromotionByCombinationListPO toPO(){
		ArrayList<LineItemPO> com = new ArrayList<LineItemPO>();
		for(LineItemVO i:combination)
			com.add(i.toPO());
		return new PromotionByCombinationListPO(com,priceAfter,beginTime,
			endTime, promotionName,promotionID);
	}
}
