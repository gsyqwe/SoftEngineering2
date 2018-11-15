package memberdataservice;

import java.util.ArrayList;

import PO.MemberPO;
import enums.MemberType;
import enums.MemberVipLevel;
import enums.ResultMessage;

public interface MemberDataService {
	public MemberPO findByID(String id);

	public ArrayList<MemberPO> findByLevel(MemberVipLevel level);

	public ArrayList<MemberPO> findByMemberType(MemberType type);

	public ArrayList<MemberPO> findByName(String name);

	public ArrayList<MemberPO> findByPostcode(String postcode); // 郵編

	public ArrayList<MemberPO> findByVoucher(String voucherID);// 这个方法应该去掉，Voucher是没有ID的

	public ArrayList<MemberPO> findBySalesman(String salesmanId);

	public ResultMessage insert(MemberPO po);

	public ResultMessage delete(String memberID);

	public ResultMessage update(String targetID, MemberPO replacement);

	public String getMemberID(MemberType type);

	public ArrayList<MemberPO> getAll();

}
