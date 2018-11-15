package memberbl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.MemberPO;
import VO.MemberVO;
import memberdata.MemberDataUseDataBase;

public class FindMemberByID extends FindMember implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public FindMemberByID(){
		super.dataService = new MemberDataUseDataBase();
	}

	@Override
	public ArrayList<MemberVO> find(String keyWords) throws RemoteException {
		// TODO Auto-generated method stub
		MemberPO theOne = dataService.findByID(keyWords);
		ArrayList<MemberVO> members = new ArrayList<>();
		members.add(theOne.toVO());
		return members;
	}

}
