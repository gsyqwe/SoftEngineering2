package memberbl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.MemberPO;
import VO.MemberVO;
import memberdata.MemberDataUseDataBase;

public class FindMemberBySalesman extends FindMember implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public FindMemberBySalesman(){
		super.dataService = new MemberDataUseDataBase();
	}
	@Override
	public ArrayList<MemberVO> find(String keyWords) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<MemberPO> memberList = dataService.findBySalesman(keyWords);

		if(memberList == null || memberList.size() == 0){
			return new ArrayList<>();
		}

		ArrayList<MemberVO> resultList = new ArrayList<>();
		for(MemberPO member:memberList){
			MemberVO tem = member.toVO();
			resultList.add(tem);
		}
		return resultList;
	}

}
