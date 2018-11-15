package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.MemberVO;
import enums.MemberType;
import enums.ResultMessage;
import memberbl.FindMember;

public interface MemberblService extends Remote{

	public ResultMessage completeAdd(MemberVO member) throws RemoteException;//填写完成后调用这个方法，系统对所填信息进行检查，返回ResultMessage

	public ResultMessage deleteMember(String MemberID) throws RemoteException;

	public ResultMessage ModifyMember(String targetID,MemberVO member) throws RemoteException;

	public ArrayList<MemberVO> getMemberList() throws RemoteException;

	public ArrayList<MemberVO> findByAllMeans(ArrayList<String> keyWords,ArrayList<FindMember> finders) throws RemoteException;//在搜索的时候传入一个关键词列表
	//再传入一个FindMember的finder列表

	public MemberVO findByID(String ID) throws RemoteException;//这个搜索方法只需要一个ID，返回一个确定的对象

	//这个方法是双击Member的ID一栏得到，在调用这个方法的时候要确保MemberType那一栏是填写完成的 memberID的格式是JHS-xxxxx 或XSS-xxxxx
	public String getMemberId(MemberType type) throws RemoteException;

}
