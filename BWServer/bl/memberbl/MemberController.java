package memberbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

import PO.MemberPO;
import VO.MemberVO;
import enums.MemberType;
import enums.ResultMessage;
import memberdata.MemberDataUseDataBase;
import memberdataservice.MemberDataService;
import service.MemberblService;

public class MemberController extends UnicastRemoteObject implements MemberblService{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	MemberDataService dataService = new MemberDataUseDataBase();
	MemberPO member;//在MemberController里面维护一个MemberPO对象

	public MemberController() throws RemoteException{
		super();
		dataService = new MemberDataUseDataBase();
		member = new MemberPO();
	}

	public MemberController(ArrayList<FindMember> finders,MemberPO member) throws RemoteException{
		this();
		this.member = member;
	}

	public MemberController(MemberPO member) throws RemoteException{
		this();
		this.member = member;
	}

	public MemberDataService getDataService() {
		return dataService;
	}

	public MemberPO getMember() {
		return member;
	}

	public void setDataService(MemberDataService dataService) {
		this.dataService = dataService;
	}

	public void setMember(MemberPO member) {
		this.member = member;
	}

	@Override
	public String getMemberId(MemberType type) throws RemoteException{
		String suffix = dataService.getMemberID(type);
		String pre = "";
		if(type == MemberType.SUPPLIER){
			pre = "JHS-";
		}
		else{
			pre = "XSS-";
		}

		return pre + suffix;
	}
	@Override
	public ResultMessage completeAdd(MemberVO member) throws RemoteException {
		// TODO Auto-generated method stub,具体的检查工作，应收应付不应该修改
		String email = member.getEmail();
		if(!email.contains("@")){
			return ResultMessage.MEMBER_EMAIL_FAULT;//邮件格式不对
		}
		this.setMember(member.toPO());
		ResultMessage message = dataService.insert(this.member);
		if(message == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED){
			return dataService.update(member.getId(), this.member);
		}

		return ResultMessage.SUCCESS;
	}


	@Override
	public ResultMessage deleteMember(String memberID) throws RemoteException {
		// TODO Auto-generated method stub
		return dataService.delete(memberID);
	}

	@Override
	public ResultMessage ModifyMember(String memberID,MemberVO member) throws RemoteException {
		// TODO Auto-generated method stub
		String email = member.getEmail();
		if(!email.contains("@")){
			return ResultMessage.MEMBER_EMAIL_FAULT;//邮件格式不对
		}
		return dataService.update(memberID, member.toPO());
	}

	@Override
	public ArrayList<MemberVO> getMemberList() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<MemberPO> memberList = dataService.getAll();
		ArrayList<MemberVO> resultList = new ArrayList<>();
		for(MemberPO member:memberList){
			resultList.add(member.toVO());
		}
		return resultList;
	}

	@Override
	public ArrayList<MemberVO> findByAllMeans(ArrayList<String> keyWords,ArrayList<FindMember> finders) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<MemberVO> memberFound = finders.get(0).find(keyWords.get(0));//先将结果按照第一个关键词查找初始化在一个ArrayList里，然后再在此ArrayList里面筛选
		if(memberFound == null || memberFound.size() == 0){
			return new ArrayList<>();
		}

		for(int i = 1;i < keyWords.size();i++){
			ArrayList<MemberVO> temMemberFound = finders.get(i).find(keyWords.get(i));
			if(temMemberFound == null || temMemberFound.size() == 0){
				return new ArrayList<>();
			}
			Iterator<MemberVO> it = memberFound.iterator();

			while(it.hasNext()){
				MemberVO the_one = it.next();
				boolean found = false;
				for(MemberVO mem:temMemberFound){
					if(mem.getId().equals(the_one.getId())){
						found = true;
						break;
					}
				}

				if(found == false){
					it.remove();
				}
			}
		}
		return memberFound;
	}

	@Override
	public MemberVO findByID(String ID) throws RemoteException {
		// TODO Auto-generated method stub,调用数据层的方法，原样传输到界面层，如果没有查找到结果，在界面层内解析，然后弹出新窗口打印错误
		MemberPO targetMember = dataService.findByID(ID);
		return targetMember.toVO();
	}

}