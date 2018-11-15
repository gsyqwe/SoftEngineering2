package PromotionStrategybl;

import java.util.ArrayList;

import PO.LineItemPO;
import PO.MemberPO;
import PO.PromotionByLevelPO;
import PO.SalesReceiptPO;
import PO.VoucherPO;

public class PromotionByLevel extends Promotion{

	PromotionByLevelPO promotion;
	MemberInfo memberdataService = new MemberInfoImpl();

	public PromotionByLevel(SalesReceiptPO sales, PromotionByLevelPO promotion) {
		super(sales);
		this.promotion = promotion;
	}

	public PromotionByLevel() {
		super();
	}

	public double getDiscount(){
		return promotion.getDiscount();
	}

	public ArrayList<LineItemPO> getGiftList(){
		return promotion.getGiftListAsList();
	}

	public ArrayList<VoucherPO> getVoucherList(){
		return promotion.getVoucherAsList();
	}

	@Override
	public boolean judgeValid() {
		// TODO Auto-generated method stub
		String memberID = sales.getMemberID();
		MemberPO member = memberdataService.findByID(memberID);
		if(member.getMemberVipLevel() == promotion.getLevel()){
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
