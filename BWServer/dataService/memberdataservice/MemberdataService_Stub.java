//package memberdataservice;
//
//import java.util.ArrayList;
//
//import PO.MemberPO;
//
//public class MemberdataService_Stub implements MemberDataService{
//
//	@Override
//	public MemberPO find(String index) {
//		// TODO Auto-generated method stub
//		System.out.println("Find the member!");
//		return new MemberPO(index, null, null, index, index, index, index, 0, 0, 0, index, null);
//	}
//
//	@Override
//	public ArrayList<MemberPO> finds(String keywords) {
//		// TODO Auto-generated method stub
//		ArrayList<MemberPO> po = new ArrayList<>();
//		System.out.println("Find the result!");
//		return po;
//	}
//
//	@Override
//	public Boolean insert(MemberPO po) {
//		// TODO Auto-generated method stub  向数据库中添加
//		System.out.println("Add succeed!");
//		return;
//	}
//
//	@Override
//	public Boolean delete(String memberID) {
//		// TODO Auto-generated method stub
//		System.out.println("Remove succeed!");
//		return;
//	}
//
//	@Override
//	public Boolean update(String targetID, MemberPO replacement) {
//		// TODO Auto-generated method stub
//		System.out.println("Modify succeed!");
//		return;
//	}
//
//	@Override
//	public void init() {
//		// TODO Auto-generated method stub
//		System.out.println("database start!");
//		return;
//	}
//
//	@Override
//	public void finish() {
//		// TODO Auto-generated method stub
//		System.out.println("finish!");
//		return;
//	}
//
//	@Override
//	public ArrayList<MemberPO> getAll() {
//		// TODO Auto-generated method stub   得到数据库中所有的MemberPO
//		System.out.println("Print the member list!");
//		return new ArrayList<MemberPO>();
//	}
//
//}
