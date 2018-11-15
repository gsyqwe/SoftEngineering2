package VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import PO.LineItemPO;
import PO.PromotionBySumPO;
import PO.VoucherPO;

public class PromotionBySumVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date startTime;
	private Date endTime;
	private double minAmount;
	private double maxAmount;
	private ArrayList<LineItemVO> giftList;
	private ArrayList<VoucherVO> voucherList;
	private String promotionID;

	public PromotionBySumVO(Date startTime, Date endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		giftList = new ArrayList<LineItemVO>();
		voucherList = new ArrayList<VoucherVO>();
	}

	public PromotionBySumVO() {
		super();
	}

	public PromotionBySumVO(Date startTime, Date endTime, double minAmount, double maxAmount) {
		this(startTime, endTime);
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}

	public PromotionBySumVO(Date startTime, Date endTime, String promotionID,double minAmount, double maxAmount,
			ArrayList<LineItemVO> giftList, ArrayList<VoucherVO> voucherList) {
		this(startTime, endTime, minAmount, maxAmount);
		this.giftList = giftList;
		this.voucherList = voucherList;
		this.promotionID = promotionID;
	}

	public Date getEndTime() {
		return endTime;
	}

	public ArrayList<LineItemVO> getGiftList() {
		return giftList;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public double getMinAmount() {
		return minAmount;
	}

	public Date getStartTime() {
		return startTime;
	}

	public ArrayList<VoucherVO> getVoucherList() {
		return voucherList;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setGiftList(ArrayList<LineItemVO> giftList) {
		this.giftList = giftList;
	}

	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setVoucherList(ArrayList<VoucherVO> voucherList) {
		this.voucherList = voucherList;
	}

	public String getPromotionID() {
		return promotionID;
	}

	public void setPromotionID(String promotionID) {
		this.promotionID = promotionID;
	}

	public void addGift(LineItemVO item){
		this.giftList.add(item);
	}

	public void addVoucher(VoucherVO voucher){
		this.voucherList.add(voucher);
	}

	@Override
	public String toString() {
		return "PromotionBySumPO [endTime=" + endTime + ", maxAmount=" + maxAmount + ", minAmount=" + minAmount
				+ ", promotionID=" + promotionID + ", startTime=" + startTime + ", voucherList=" + voucherList
				+ ", giftList=" + giftList + "]";
	}

	public PromotionBySumPO toPO(){
		ArrayList<LineItemPO> gift=new ArrayList<LineItemPO>();
		ArrayList<VoucherPO> voucher=new ArrayList<VoucherPO>();
		for(LineItemVO i:giftList)
			gift.add(i.toPO());
		for(VoucherVO i:voucherList)
			voucher.add(i.toPO());
		return new PromotionBySumPO(startTime,endTime,promotionID,minAmount,maxAmount,
				gift, voucher);
	}
}
