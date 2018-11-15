package PromotionStrategybl;

import java.util.ArrayList;

import PO.MemberPO;
import memberdata.MemberDataUseDataBase;
import memberdataservice.MemberDataService;

public class MemberInfoImpl implements MemberInfo{
	private MemberDataService dataService = new MemberDataUseDataBase();

	public MemberInfoImpl(){
		dataService = new MemberDataUseDataBase();
	}

	@Override
	public ArrayList<MemberPO> getAll() {
		// TODO Auto-generated method stub
		return dataService.getAll();
	}

	@Override
	public MemberPO findByID(String id) {
		// TODO Auto-generated method stub
		return dataService.findByID(id);
	}


}
