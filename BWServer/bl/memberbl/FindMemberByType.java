package memberbl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.MemberPO;
import VO.MemberVO;
import enums.MemberType;
import memberdata.MemberDataUseDataBase;

public class FindMemberByType extends FindMember implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public FindMemberByType(){
		super.dataService = new MemberDataUseDataBase();
	}
	@Override
	public ArrayList<MemberVO> find(String keyWords) throws RemoteException {
		// TODO Auto-generated method stub
		MemberType type = MemberType.getEnumByValue(keyWords);
		ArrayList<MemberPO> members = super.dataService.findByMemberType(type);

		if(members == null || members.size() == 0){
			return new ArrayList<>();
		}

		ArrayList<MemberVO> result = new ArrayList<>();
		for(MemberPO member:members){
			result.add(member.toVO());
		}
		return result;
	}

}
