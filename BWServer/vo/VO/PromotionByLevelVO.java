package VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import enums.MemberVipLevel;
import PO.LineItemPO;
import PO.PromotionByLevelPO;
import PO.VoucherPO;

public class PromotionByLevelVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date beginDate;
	private Date endDate;
	private String promotionID;
	private MemberVipLevel level;
	private double discount;
	private ArrayList<VoucherVO> voucherVOs = new ArrayList<>();
	private ArrayList<LineItemVO> commodityVOs = new ArrayList<>();

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPromotionID() {
		return promotionID;
	}

	public void setPromotionID(String promotionID) {
		this.promotionID = promotionID;
	}

	public MemberVipLevel getLevel() {
		return level;
	}

	public void setLevel(MemberVipLevel level) {
		this.level = level;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public ArrayList<VoucherVO> getVoucherPOs() {
		return voucherVOs;
	}

	public void setVoucherVOs(ArrayList<VoucherVO> voucherPOs) {
		this.voucherVOs = voucherPOs;
	}

	public ArrayList<LineItemVO> getCommodityVOs() {
		return commodityVOs;
	}

	public void setCommodityVOs(ArrayList<LineItemVO> commodityVOs) {
		this.commodityVOs = commodityVOs;
	}
	
	public PromotionByLevelVO(){
		
	}

	public PromotionByLevelVO(Date beginDate, Date endDate, String promotionID, MemberVipLevel level, double discount,
			ArrayList<VoucherVO> voucherPOs, ArrayList<LineItemVO> commodityPOs) {
		super();
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.promotionID = promotionID;
		this.level = level;
		this.discount = discount;
		this.voucherVOs = voucherPOs;
		this.commodityVOs = commodityPOs;
	}
	
	public PromotionByLevelPO toPO() {
		ArrayList<VoucherPO> voucherResult = new ArrayList<>();
		ArrayList<LineItemPO> itemResult = new ArrayList<>();
		
		for(VoucherVO voucher:this.voucherVOs){
			voucherResult.add(voucher.toPO());			
		}
		
		for(LineItemVO item:this.commodityVOs){
			itemResult.add(item.toPO());
		}
		
		return new PromotionByLevelPO(this.beginDate,this.endDate,this.promotionID,this.level,this.discount,voucherResult,itemResult);
	}
}
