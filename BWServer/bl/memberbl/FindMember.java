package memberbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.MemberVO;
import memberdata.MemberDataUseDataBase;
import memberdataservice.MemberDataService;

public abstract class FindMember {

	MemberDataService dataService = new MemberDataUseDataBase();//具体用那个dataService到后面应该是定下来的，在此先省略

	public abstract ArrayList<MemberVO> find(String keyWords) throws RemoteException;

}
