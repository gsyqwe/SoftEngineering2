package PO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import VO.LineItemVO;
import VO.PromotionByCombinationListVO;

public final class PromotionByCombinationListPO {
	private static Set<LineItemPO> LINE_ITEM_1 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_2 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_3 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_4 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_5 = new HashSet<>();
	static {
		LINE_ITEM_1.add(LineItemPO.SAMPLE_1);
		LINE_ITEM_1.add(LineItemPO.SAMPLE_2);
		LINE_ITEM_2.add(LineItemPO.SAMPLE_3);
		LINE_ITEM_2.add(LineItemPO.SAMPLE_4);
		LINE_ITEM_3.add(LineItemPO.SAMPLE_5);
		LINE_ITEM_3.add(LineItemPO.SAMPLE_1);
		LINE_ITEM_3.add(LineItemPO.SAMPLE_2);
		LINE_ITEM_4.add(LineItemPO.SAMPLE_3);
		LINE_ITEM_4.add(LineItemPO.SAMPLE_4);
		LINE_ITEM_5.add(LineItemPO.SAMPLE_5);

	}
	public static PromotionByCombinationListPO SAMPLE_1 = new PromotionByCombinationListPO(
			LINE_ITEM_1.stream().collect(Collectors.toCollection(ArrayList::new)), 99.9,
			new Date(new Date().getTime() - 235467), new Date(new Date().getTime() + 24351349), "紅綠燈大特賣", "PBC-00001");
	public static PromotionByCombinationListPO SAMPLE_2 = new PromotionByCombinationListPO(
			LINE_ITEM_2.stream().collect(Collectors.toCollection(ArrayList::new)), 299.9,
			new Date(new Date().getTime() - 4567897), new Date(new Date().getTime() + 12356789), "旺仔大禮包", "PBC-00002");
	public static PromotionByCombinationListPO SAMPLE_3 = new PromotionByCombinationListPO(
			LINE_ITEM_3.stream().collect(Collectors.toCollection(ArrayList::new)), 399.9,
			new Date(new Date().getTime() - 457689), new Date(new Date().getTime() + 9999999), "雙十一特價賤賣", "PBC-00003");
	public static PromotionByCombinationListPO SAMPLE_4 = new PromotionByCombinationListPO(
			LINE_ITEM_4.stream().collect(Collectors.toCollection(ArrayList::new)), 499.9,
			new Date(new Date().getTime() - 2345678), new Date(new Date().getTime() + 242303589), "要錢不要貨", "PBC-00004");
	public static PromotionByCombinationListPO SAMPLE_5 = new PromotionByCombinationListPO(
			LINE_ITEM_5.stream().collect(Collectors.toCollection(ArrayList::new)), 599.9,
			new Date(new Date().getTime() - 211317645), new Date(new Date().getTime() + 42356789), "換季大特賣",
			"PBC-00005");

	private Date beginTime;
	/*
	 * database mapping
	 */
	private Set<LineItemPO> combination = new HashSet<>();
	private Date endTime;
	private double priceAfter;
	private String promotionName;// 组合出来的总商品的名字,PBC-xxxxx promotion的命名不需要时间6
	/*
	 * key for database
	 */
	private String promotionID;// 这个promotion的ID
	private boolean isDeleted;

	public PromotionByCombinationListPO() {
	}

	public PromotionByCombinationListPO(ArrayList<LineItemPO> combination, double priceAfter, Date beginTime,
			Date endTime, String promotionName, String promotionID) {
		super();
		Set<LineItemPO> result = new HashSet<>(combination);
		this.combination = result;
		this.priceAfter = priceAfter;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.promotionName = promotionName;
		this.promotionID = promotionID;
	}

	public PromotionByCombinationListPO(Date beginTime, Date endTime, ArrayList<LineItemPO> combination,
			String promotionName) {
		this(beginTime, endTime, promotionName);
		Set<LineItemPO> result = new HashSet<>(combination);
		this.combination = result;
	}

	public PromotionByCombinationListPO(Date beginTime, Date endTime, String promotionName) {
		this();
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.promotionName = promotionName;
	}

	public PromotionByCombinationListPO(Set<LineItemPO> combination, double priceAfter, Date beginTime, Date endTime,
			String promotionName) {
		super();
		Set<LineItemPO> result = new HashSet<>(combination);
		this.combination = result;
		this.priceAfter = priceAfter;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.promotionName = promotionName;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	// for database
	public Set<LineItemPO> getCombination() {
		return combination;
	}

	public ArrayList<LineItemPO> getCombinationAsList() {
		ArrayList<LineItemPO> item = new ArrayList<>(combination);
		return item;
	}

	public double getDiscount() {
		return this.getPriceAfter() - this.getPriceBefore();
	}

	public Date getEndTime() {
		return endTime;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public double getPriceAfter() {
		return priceAfter;
	}

	public double getPriceBefore() {// 对商品组合中的每个商品进行求价格并累加求得组合前的价格
		double priceBefore = 0;
		for (LineItemPO commodity : combination) {
			priceBefore = priceBefore + commodity.getPrice() * commodity.getQuantity();
		}

		return priceBefore;
	}

	// for database
	public String getPromotionID() {
		return promotionID;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public void setCombination(ArrayList<LineItemPO> combination) {
		Set<LineItemPO> result = new HashSet<>(combination);
		this.combination = result;
	}

	// for database
	public void setCombination(Set<LineItemPO> combination) {
		this.combination = combination;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setPriceAfter(double priceAfter) {
		this.priceAfter = priceAfter;
	}

	// for database
	public void setPromotionID(String promotionID) {
		this.promotionID = promotionID;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String toShow() {
		return "触发了" + promotionName + "的组合商品折价" + "，优惠了" + (this.getPriceBefore() - this.priceAfter) + "元";// 这个返回值可以任意修改，就是为了显示触发了什么促销策略而已
	}

	@Override
	public String toString() {
		return "PromotionByCombinationListPO [beginTime=" + beginTime + ", combination=" + combination + ", endTime="
				+ endTime + ", priceAfter=" + priceAfter + ", promotionName=" + promotionName + ", promotionID="
				+ promotionID + "]";
	}

	public PromotionByCombinationListVO toVO() {
		ArrayList<LineItemVO> com = new ArrayList<>();
		for (LineItemPO i : combination)
			com.add(i.toVO());
		return new PromotionByCombinationListVO(com, priceAfter, beginTime, endTime, promotionName, promotionID);
	}
}
