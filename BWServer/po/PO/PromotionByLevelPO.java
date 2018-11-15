package PO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import VO.LineItemVO;
import VO.PromotionByLevelVO;
import VO.VoucherVO;
import enums.MemberVipLevel;

/**
 * 
 * @author JiYuzhe problem:在赠送商品的时候不应该用LineItem吗，怎么是CommodityPO？
 *
 */
public class PromotionByLevelPO {
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
		LINE_ITEM_1.add(LineItemPO.SAMPLE_3);
		LINE_ITEM_1.add(LineItemPO.SAMPLE_2);
		LINE_ITEM_2.add(LineItemPO.SAMPLE_1);
		LINE_ITEM_2.add(LineItemPO.SAMPLE_5);
		LINE_ITEM_3.add(LineItemPO.SAMPLE_4);
		LINE_ITEM_3.add(LineItemPO.SAMPLE_3);
		LINE_ITEM_4.add(LineItemPO.SAMPLE_2);
		LINE_ITEM_5.add(LineItemPO.SAMPLE_1);

		VOUCHER_1.add(VoucherPO.SAMPLE_1);
		VOUCHER_1.add(VoucherPO.SAMPLE_3);
		VOUCHER_2.add(VoucherPO.SAMPLE_2);
		VOUCHER_3.add(VoucherPO.SAMPLE_1);
		VOUCHER_3.add(VoucherPO.SAMPLE_3);
		VOUCHER_4.add(VoucherPO.SAMPLE_2);
		VOUCHER_5.add(VoucherPO.SAMPLE_1);
	}
	public static PromotionByLevelPO SAMPLE_1 = new PromotionByLevelPO(new Date(),
			new Date(new Date().getTime() + 123679765), "PBL-00001", MemberVipLevel.SLIVER, 200, VOUCHER_1,
			LINE_ITEM_1);
	public static PromotionByLevelPO SAMPLE_2 = new PromotionByLevelPO(new Date(),
			new Date(new Date().getTime() + 2134565), "PBL-00002", MemberVipLevel.DIAMOND, 300, VOUCHER_2, LINE_ITEM_2);
	public static PromotionByLevelPO SAMPLE_3 = new PromotionByLevelPO(new Date(),
			new Date(new Date().getTime() + 921876765), "PBL-00003", MemberVipLevel.SLIVER, 900, VOUCHER_3,
			LINE_ITEM_3);
	public static PromotionByLevelPO SAMPLE_4 = new PromotionByLevelPO(new Date(),
			new Date(new Date().getTime() + 833179765), "PBL-00004", MemberVipLevel.BRONZE, 200, VOUCHER_4,
			LINE_ITEM_4);
	public static PromotionByLevelPO SAMPLE_5 = new PromotionByLevelPO(new Date(),
			new Date(new Date().getTime() + 389712365), "PBL-00005", MemberVipLevel.GOLD, 1200, VOUCHER_5, LINE_ITEM_5);
	private Date beginDate;

	private Date endDate;
	private String promotionID;// PBL-xxxxx
	/*
	 * key for database
	 */
	private MemberVipLevel level;
	private double discount;
	/*
	 * database mapping
	 */
	private Set<VoucherPO> voucherPOs = new HashSet<>();
	/*
	 * database mapping
	 */
	private Set<LineItemPO> commodityPOs = new HashSet<>();
	private boolean isDeleted;

	public PromotionByLevelPO() {
		super();
	}

	public PromotionByLevelPO(Date beginDate, Date endDate, MemberVipLevel level) {
		this();
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.level = level;
	}

	public PromotionByLevelPO(Date beginDate, Date endDate, String promotionID, MemberVipLevel level, double discount,
			ArrayList<VoucherPO> voucherPOs, ArrayList<LineItemPO> commodityPOs) {
		this();
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.promotionID = promotionID;
		this.level = level;
		this.discount = discount;
		Set<VoucherPO> resultVoucher = new HashSet<>(voucherPOs);
		Set<LineItemPO> resultItem = new HashSet<>(commodityPOs);
		this.voucherPOs = resultVoucher;
		this.commodityPOs = resultItem;
	}

	public PromotionByLevelPO(Date beginDate, Date endDate, String promotionID, MemberVipLevel level, double discount,
			Set<VoucherPO> voucherPOs, Set<LineItemPO> commodityPOs) {
		this();
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.promotionID = promotionID;
		this.level = level;
		this.discount = discount;
		Set<VoucherPO> resultVoucher = new HashSet<>(voucherPOs);
		Set<LineItemPO> resultItem = new HashSet<>(commodityPOs);
		this.voucherPOs = resultVoucher;
		this.commodityPOs = resultItem;
	}

	public PromotionByLevelPO(MemberVipLevel level, double discount, ArrayList<VoucherPO> voucherPOs,
			ArrayList<LineItemPO> commodityPOs) {
		this();
		this.level = level;
		this.discount = discount;
		Set<VoucherPO> resultVoucher = new HashSet<>(voucherPOs);
		Set<LineItemPO> resultItem = new HashSet<>(commodityPOs);
		this.voucherPOs = resultVoucher;
		this.commodityPOs = resultItem;
	}

	public PromotionByLevelPO(MemberVipLevel level, double discount, double faceValue, Date startTime, Date endTime,
			Set<LineItemPO> commodityPOs) {
		this();
		this.level = level;
		this.discount = discount;
		VoucherPO voucher = new VoucherPO(faceValue, startTime, endTime);
		voucherPOs = new HashSet<>();
		voucherPOs.add(voucher);
		Set<LineItemPO> resultCom = new HashSet<>(commodityPOs);
		this.commodityPOs = resultCom;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	// for database
	public Set<LineItemPO> getCommodityPOs() {
		return commodityPOs;
	}

	public double getDiscount() {
		return discount;
	}

	public Date getEndDate() {
		return endDate;
	}

	public ArrayList<LineItemPO> getGiftListAsList() {
		ArrayList<LineItemPO> result = new ArrayList<>(commodityPOs);
		return result;
	}

	public MemberVipLevel getLevel() {
		return level;
	}

	public String getPromotionID() {
		return promotionID;
	}

	public ArrayList<VoucherPO> getVoucherAsList() {
		ArrayList<VoucherPO> voucherResult = new ArrayList<>(voucherPOs);
		return voucherResult;
	}

	// for database
	public Set<VoucherPO> getVoucherPOs() {
		return voucherPOs;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setCommodityPOs(ArrayList<LineItemPO> commodityPOs) {
		Set<LineItemPO> itemResult = new HashSet<>(commodityPOs);
		this.commodityPOs = itemResult;
	}

	// for database
	public void setCommodityPOs(Set<LineItemPO> commodityPOs) {
		this.commodityPOs = commodityPOs;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setLevel(MemberVipLevel level) {
		this.level = level;
	}

	public void setPromotionID(String promotionID) {
		this.promotionID = promotionID;
	}

	public void setVoucher(ArrayList<VoucherPO> voucher) {
		Set<VoucherPO> result = new HashSet<>(voucher);
		this.voucherPOs = result;
	}

	public void setVoucher(double value, Date startTime, Date endTime) {
		voucherPOs.add(new VoucherPO(value, startTime, endTime));
	}

	// for database
	public void setVoucherPOs(Set<VoucherPO> voucherPOs) {
		this.voucherPOs = voucherPOs;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String toShow() {
		String gift_list = "";
		for (LineItemPO item : this.commodityPOs) {
			gift_list = gift_list + "\r\n" + item.getId() + " " + item.getQuantity() + "件。";
		}
		String voucher_list = "";
		for (VoucherPO voucher : this.voucherPOs) {
			voucher_list = voucher_list + "\r\n" + "面值为" + voucher.getFaceValue() + "的代金券。";
		}

		return "触发了" + promotionID + "折让了" + this.discount + "元。赠送了:" + gift_list + "\r\n" + "获赠的代金券为:" + voucher_list;
	}

	@Override
	public String toString() {
		return "PromotionByLevelPO [beginDate=" + beginDate + ", endDate=" + endDate + ", promotionID=" + promotionID
				+ ", level=" + level + ", discount=" + discount + ", voucherPOs=" + voucherPOs + ", commodityPOs="
				+ commodityPOs + "]";
	}

	public PromotionByLevelVO toVO() {
		ArrayList<VoucherVO> voucher = new ArrayList<>();
		ArrayList<LineItemVO> com = new ArrayList<>();
		for (VoucherPO i : voucherPOs)
			voucher.add(i.toVO());
		for (LineItemPO i : commodityPOs)
			com.add(i.toVO());

		return new PromotionByLevelVO(this.beginDate, this.endDate, this.promotionID, level, discount, voucher, com);
	}

}
