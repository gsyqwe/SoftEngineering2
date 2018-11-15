package salesbl;

import java.util.ArrayList;

import PO.MemberPO;
import enums.MemberType;
import memberdata.MemberDataUseDataBase;
import memberdataservice.MemberDataService;

public class MemberInfoImpl implements MemberInfo{
	private MemberDataService dataService = new MemberDataUseDataBase();

	@Override
	public ArrayList<MemberPO> findByMemberType(MemberType type) {
		// TODO Auto-generated method stub
		return dataService.findByMemberType(type);
	}


}
