package PO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import VO.LineItemVO;
import VO.PromotionBySumVO;
import VO.VoucherVO;

public class PromotionBySumPO {
	/**
	 * 根据总价制定优惠政策，注意有minAmount表示该政策的最低门槛，maxAmount可以用来表示政策的不叠加性，里面的赠品用SalesLineItemPO而非CommodityPO
	 */

	private static Set<LineItemPO> LINE_ITEM_1 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_2 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_3 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_4 = new HashSet<>();
	private static Set<LineItemPO> LINE_ITEM_5 = new HashSet<>();
	private static Set<VoucherPO> VOUCHER_1 = new HashSet<>();
	private static Set<VoucherPO> VOUCHER_2 = new HashSet<>();
	private static Set<VoucherPO> VOUCHER_3 = new HashSet<>();
	private static Set<VoucherPO> VOUCHER_4 = new HashSet<>();
	private static Set<VoucherPO> VOUCHER_5 = new HashSet<>();
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

		VOUCHER_1.add(VoucherPO.SAMPLE_1);
		VOUCHER_1.add(VoucherPO.SAMPLE_3);
		VOUCHER_2.add(VoucherPO.SAMPLE_2);
		VOUCHER_2.add(VoucherPO.SAMPLE_1);
		VOUCHER_3.add(VoucherPO.SAMPLE_3);
		VOUCHER_3.add(VoucherPO.SAMPLE_2);
		VOUCHER_4.add(VoucherPO.SAMPLE_1);
		VOUCHER_4.add(VoucherPO.SAMPLE_3);
		VOUCHER_5.add(VoucherPO.SAMPLE_2);
		VOUCHER_5.add(VoucherPO.SAMPLE_1);

	}

	public static PromotionBySumPO SAMPLE_1 = new PromotionBySumPO(new Date(new Date().getTime() - 3345678),
			new Date(new Date().getTime() + 3426178), "PBS-00001", 188, 299,
			LINE_ITEM_1.stream().collect(Collectors.toCollection(ArrayList::new)),
			VOUCHER_1.stream().collect(Collectors.toCollection(ArrayList::new)));
	public static PromotionBySumPO SAMPLE_2 = new PromotionBySumPO(new Date(new Date().getTime() - 4345678),
			new Date(new Date().getTime() + 312345670), "PBS-00002", 22, 99,
			LINE_ITEM_2.stream().collect(Collectors.toCollection(ArrayList::new)),
			VOUCHER_2.stream().collect(Collectors.toCollection(ArrayList::new)));
	public static PromotionBySumPO SAMPLE_3 = new PromotionBySumPO(new Date(new Date().getTime() - 1345678),
			new Date(new Date().getTime() + 993456178), "PBS-00003", 89, 109,
			LINE_ITEM_3.stream().collect(Collectors.toCollection(ArrayList::new)),
			VOUCHER_3.stream().collect(Collectors.toCollection(ArrayList::new)));
	public static PromotionBySumPO SAMPLE_4 = new PromotionBySumPO(new Date(new Date().getTime() - 395678),
			new Date(new Date().getTime() + 4356178), "PBS-00004", 108, 123,
			LINE_ITEM_4.stream().collect(Collectors.toCollection(ArrayList::new)),
			VOUCHER_4.stream().collect(Collectors.toCollection(ArrayList::new)));
	public static PromotionBySumPO SAMPLE_5 = new PromotionBySumPO(new Date(new Date().getTime() - 134568),
			new Date(new Date().getTime() + 13456178), "PBS-00005", 97, 119,
			LINE_ITEM_5.stream().collect(Collectors.toCollection(ArrayList::new)),
			VOUCHER_5.stream().collect(Collectors.toCollection(ArrayList::new)));

	private Date endTime;
	private double maxAmount;
	private double minAmount;
	/*
	 * key for database
	 */
	private String promotionID;// 这个是promotion的id PBS
	private Date startTime;
	/*
	 * database mapping
	 */
	private Set<VoucherPO> voucherList = new HashSet<>();
	/*
	 * database mapping
	 */
	private Set<LineItemPO> giftList = new HashSet<>();
	private boolean isDeleted;

	public PromotionBySumPO() {
	}

	public PromotionBySumPO(Date startTime, Date endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public PromotionBySumPO(Date startTime, Date endTime, double minAmount, double maxAmount) {
		this(startTime, endTime);
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}

	public PromotionBySumPO(Date startTime, Date endTime, double minAmount, double maxAmount, Set<LineItemPO> giftList,
			Set<VoucherPO> voucherList) {
		this(startTime, endTime, minAmount, maxAmount);
		this.giftList = giftList;
		this.voucherList = voucherList;
	}

	public PromotionBySumPO(Date startTime, Date endTime, String promotionID, double minAmount, double maxAmount,
			ArrayList<LineItemPO> giftList, ArrayList<VoucherPO> voucherList) {
		this(startTime, endTime, minAmount, maxAmount);
		Set<LineItemPO> resultItem = new HashSet<>(giftList);
		Set<VoucherPO> resultVoucher = new HashSet<>(voucherList);
		this.giftList = resultItem;
		this.voucherList = resultVoucher;
		this.promotionID = promotionID;
	}

	public Date getEndTime() {
		return endTime;
	}

	public ArrayList<LineItemPO> getGiftAsList() {
		ArrayList<LineItemPO> resultList = new ArrayList<>(giftList);
		return resultList;
	}

	// for database
	public Set<LineItemPO> getGiftList() {
		return giftList;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public double getMinAmount() {
		return minAmount;
	}

	// for database
	public String getPromotionID() {
		return promotionID;
	}

	public Date getStartTime() {
		return startTime;
	}

	public ArrayList<VoucherPO> getVoucherAsList() {
		ArrayList<VoucherPO> resultVoucher = new ArrayList<>(voucherList);
		return resultVoucher;
	}

	// for database
	public Set<VoucherPO> getVoucherList() {
		return voucherList;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setGiftList(ArrayList<LineItemPO> giftList) {
		Set<LineItemPO> resultList = new HashSet<>(giftList);
		this.giftList = resultList;
	}

	// for database
	public void setGiftList(Set<LineItemPO> giftList) {
		this.giftList = giftList;
	}

	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	// for database
	public void setPromotionID(String promotionID) {
		this.promotionID = promotionID;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setVoucherList(ArrayList<VoucherPO> voucherList) {
		Set<VoucherPO> resultList = new HashSet<>(voucherList);
		this.voucherList = resultList;
	}

	// for database
	public void setVoucherList(Set<VoucherPO> voucherList) {
		this.voucherList = voucherList;
	}

	public String toShow() {
		String gift_list = "";
		for (LineItemPO item : this.giftList) {
			gift_list = gift_list + "\r\n" + item.getId() + " " + item.getQuantity() + "件。";
		}
		String voucher_list = "";
		for (VoucherPO voucher : this.voucherList) {
			voucher_list = voucher_list + "\r\n" + "面值为" + voucher.getFaceValue() + "的代金券。";
		}

		return "触发了" + promotionID + "赠送了:" + gift_list + "\r\n" + "获赠的代金券为:" + voucher_list;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "PromotionBySumPO [endTime=" + endTime + ", maxAmount=" + maxAmount + ", minAmount=" + minAmount
				+ ", promotionID=" + promotionID + ", startTime=" + startTime + ", voucherList=" + voucherList
				+ ", giftList=" + giftList + "]";
	}

	public PromotionBySumVO toVO() {
		ArrayList<LineItemVO> gift = new ArrayList<>();
		ArrayList<VoucherVO> voucher = new ArrayList<>();
		for (LineItemPO i : giftList)
			gift.add(i.toVO());
		for (VoucherPO i : voucherList)
			voucher.add(i.toVO());
		return new PromotionBySumVO(startTime, endTime, promotionID, minAmount, maxAmount, gift, voucher);
	}

}