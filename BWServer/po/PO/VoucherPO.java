package PO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import VO.VoucherVO;

public final class VoucherPO {
	public static VoucherPO SAMPLE_1 = new VoucherPO(300.0, new Date(new Date().getTime() - 864000000),
			new Date(new Date().getTime() + 864000000));
	public static VoucherPO SAMPLE_2 = new VoucherPO(600.0, new Date(new Date().getTime() - 864000000),
			new Date(new Date().getTime() + 2 * 864000000));
	public static VoucherPO SAMPLE_3 = new VoucherPO(900.0, new Date(new Date().getTime() - 864000000),
			new Date(new Date().getTime() + 823000000));
	public static VoucherPO SAMPLE_4 = new VoucherPO(1000.0, new Date(new Date().getTime() - 864000000),
			new Date(new Date().getTime() + 823000000));
	public static VoucherPO SAMPLE_5 = new VoucherPO(9020.0, new Date(new Date().getTime() - 864000000),
			new Date(new Date().getTime() + 823000000));
	public static VoucherPO SAMPLE_6 = new VoucherPO(90.0, new Date(new Date().getTime() - 864000000),
			new Date(new Date().getTime() + 823000000));

	private Date endTime;
	private double faceValue;
	/*
	 * the owner of this voucher
	 */
	private MemberPO memberPO;
	private Date startTime;
	/*
	 * database mapping
	 */
	private Set<MemberPO> memberPOs = new HashSet<>();
	/*
	 * database mapping
	 */
	private Set<PromotionBySumPO> promotionBySumPOs = new HashSet<>();
	/*
	 * database mapping
	 */
	private Set<PromotionByLevelPO> promotionByLevelPOs = new HashSet<>();
	/*
	 * database mapping
	 */
	private Set<SalesReceiptPO> salesReceiptPOs = new HashSet<>();
	/*
	 * key for database self-increment
	 */
	private long voucherId;
	private boolean isDeleted;


	public VoucherPO() {
		// TODO Auto-generated constructor stub
	}

	public VoucherPO(double faceValue, Date startTime, Date endTime) {
		this.faceValue = faceValue;
		this.startTime = startTime; // 檢查 start time < endTime
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public double getFaceValue() {
		return faceValue;
	}

	public MemberPO getMemberPO() {
		return memberPO;
	}

	// for database
	public Set<MemberPO> getMemberPOs() {
		return memberPOs;
	}

	// for database
	public Set<PromotionByLevelPO> getPromotionByLevelPOs() {
		return promotionByLevelPOs;
	}

	// for database
	public Set<PromotionBySumPO> getPromotionBySumPOs() {
		return promotionBySumPOs;
	}

	public Date getStartTime() {
		return startTime;
	}

	// for database
	public long getVoucherId() {
		return voucherId;
	}

	public boolean isSame(VoucherPO voucher) {// 用来判断两个代金券是否是一样的
		if (this.getFaceValue() == voucher.getFaceValue() && this.getEndTime() == voucher.getEndTime()
				&& this.getStartTime() == voucher.getStartTime()) {
			return true;
		} else {
			return false;
		}
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setFaceValue(double faceValue) {
		this.faceValue = faceValue;
	}

	public void setMemberPO(MemberPO memberPO) {
		this.memberPO = memberPO;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// for database
	public void setMemberPOs(Set<MemberPO> memberPOs) {
		this.memberPOs = memberPOs;
	}

	// for database
	public void setPromotionByLevelPOs(Set<PromotionByLevelPO> promotionByLevelPOs) {
		this.promotionByLevelPOs = promotionByLevelPOs;
	}

	// for database
	public void setPromotionBySumPOs(Set<PromotionBySumPO> promotionBySumPOs) {
		this.promotionBySumPOs = promotionBySumPOs;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setVoucherId(long voucherId) {
		this.voucherId = voucherId;
	}

	public Set<SalesReceiptPO> getSalesReceiptPOs() {
		return salesReceiptPOs;
	}

	public void setSalesReceiptPOs(Set<SalesReceiptPO> salesReceiptPOs) {
		this.salesReceiptPOs = salesReceiptPOs;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "VoucherPO [endTime=" + endTime + ", faceValue=" + faceValue + ", memberPO=" + memberPO + ", startTime="
				+ startTime + ", memberPOs=" + memberPOs + ", promotionBySumPOs=" + promotionBySumPOs
				+ ", promotionByLevelPOs=" + promotionByLevelPOs + ", voucherId=" + voucherId + "]";
	}

	public VoucherVO toVO() {
		return new VoucherVO(endTime, faceValue, startTime);
	}
}
