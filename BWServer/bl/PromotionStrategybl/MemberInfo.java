package PromotionStrategybl;

import java.util.ArrayList;

import PO.MemberPO;

public interface MemberInfo {
	public ArrayList<MemberPO> getAll();
	
	public MemberPO findByID(String id);
}
