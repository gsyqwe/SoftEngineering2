package memberbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import VO.MemberVO;
import VO.VoucherVO;
import enums.MemberType;
import enums.MemberVipLevel;
import enums.ResultMessage;

public class MemberTest {

	public static void main(String [] args){
		MemberTest test = new MemberTest();
		//test.deleteMemberTest();
		//test.findMemberTest();
		test.addMemberTest();
		//test.getListTest();
		//test.memberUpdateTest();
	}

	public static Date getEarlyDate(Date nowDate){
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(nowDate);
		rightNow.add(Calendar.DATE, -33);// 日期减1年
		Date preDate = rightNow.getTime();
		return preDate;
	}

	public void memberUpdateTest(){
		try {
			MemberController controller = new MemberController();
			MemberVO member = controller.findByID("XSS-00003");
			member.setAddress("Shanghai");
			ResultMessage message = controller.ModifyMember(member.getId(), member);
			System.out.println(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void findMemberTest(){
		ArrayList<FindMember> finders = new ArrayList<>();
		ArrayList<String> words = new ArrayList<>();
		finders.add(new FindMemberBySalesman());
		words.add("MAN-001");
		finders.add(new FindMemberByType());
		words.add("Retailer");
		finders.add(new FindMemberByLevel());
		words.add("Diamond");

		try {
			MemberController controller = new MemberController();
			ArrayList<MemberVO> members = controller.findByAllMeans(words, finders);
			if(members == null || members.isEmpty()){
				System.out.println("Y");
				return;
			}

			for(MemberVO member:members){
				System.out.println(member);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getListTest(){
		try {
			MemberController controller = new MemberController();
			ArrayList<MemberVO> members = controller.getMemberList();
			if(members == null || members.size() == 0){
				System.out.println("null");
				return;
			}

			for(MemberVO member:members){
				System.out.println(member);
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addMemberTest(){
		MemberVO member = new MemberVO();
		member.setAddress("Beijing");
		member.setCredit(10000);
		member.setDefaultSalesman("MAN-003");
		member.setMemberType(MemberType.RETAILER);
		member.setMemberVipLevel(MemberVipLevel.ORDINARY);
		member.setName("hehe");
		member.setPayment(1000);
		member.setPhoneNumber("1d33330");
		member.setPostcode("71sdfsfs8");
		member.setEmail("dfsaf3@163.com");
		member.setReceivable(0);
		ArrayList<VoucherVO> voucher = new ArrayList<>();
		voucher.add(new VoucherVO(new Date(),1200,getEarlyDate(new Date())));
		member.setVouchers(voucher);

		try {
			MemberController controller = new MemberController();
			String id = controller.getMemberId(member.getMemberType());
			System.out.println(id);
			member.setId(id);
			ResultMessage message = controller.completeAdd(member);
			System.out.println(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteMemberTest(){
		try {
			MemberController controller = new MemberController();
			ResultMessage message = controller.deleteMember("XSS-00007");
			System.out.println(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
