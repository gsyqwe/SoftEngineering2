package PO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import VO.MemberVO;
import VO.VoucherVO;
import enums.MemberType;
import enums.MemberVipLevel;

public class MemberPO {
	private static Set<VoucherPO> voucherPOs = new HashSet<>();
	static {
		voucherPOs.add(VoucherPO.SAMPLE_1);
		voucherPOs.add(VoucherPO.SAMPLE_2);
		voucherPOs.add(VoucherPO.SAMPLE_3);
	}
	public static MemberPO SAMPLE_1 = new MemberPO("MEM-00001", MemberType.SUPPLIER, MemberVipLevel.DIAMOND,
			"Patrick Lai", "13236537528", "Nanjing", "postcode_nanjing", 200.0, 204.0, 300.0, voucherPOs,
			UserPO.SAMPLE_1.getId(), "");
	public static MemberPO SAMPLE_2 = new MemberPO("MEM-00002", MemberType.SUPPLIER, MemberVipLevel.DIAMOND,
			"Patrick Lai", "13236537528", "Nanjing", "postcode_nanjing", 200.0, 204.0, 300.0, voucherPOs,
			UserPO.SAMPLE_1.getId(), "");
	public static MemberPO SAMPLE_3 = new MemberPO("MEM-00003", MemberType.SUPPLIER, MemberVipLevel.DIAMOND,
			"Patrick Lai", "13236537528", "Nanjing", "postcode_nanjing", 200.0, 204.0, 300.0, voucherPOs,
			UserPO.SAMPLE_1.getId(), "");
	public static MemberPO SAMPLE_4 = new MemberPO("MEM-00004", MemberType.SUPPLIER, MemberVipLevel.DIAMOND,
			"Patrick Lai", "13236537528", "Nanjing", "postcode_nanjing", 200.0, 204.0, 300.0, voucherPOs,
			UserPO.SAMPLE_1.getId(), "");
	public static MemberPO SAMPLE_5 = new MemberPO("MEM-00005", MemberType.SUPPLIER, MemberVipLevel.DIAMOND,
			"Patrick Lai", "13236537528", "Nanjing", "postcode_nanjing", 200.0, 204.0, 300.0, voucherPOs,
			UserPO.SAMPLE_1.getId(), "");

	private String address;
	private double credit; // 信用額度

	private String defaultSalesman; // 默認業務員

	private String email;
	/*
	 * key for database
	 */
	private String id;
	private Set<InitializeAccountPO> initializeAccountPOs = new HashSet<>();
	private MemberType memberType;
	private MemberVipLevel memberVipLevel;
	// ���������绰����ַ���ʱࡢ�������䡢Ӧ�ն�ȡ�Ӧ�ա�Ӧ�������еĴ���������Ĭ��ҵ��Ա��
	private String name;
	private double payment; // 應付
	private String phoneNumber;
	private String postcode;
	private double receivable; // 應收
	/*
	 * database mapping
	 */
	private Set<VoucherPO> vouchers = new HashSet<>(); // 代金卷, 虛擬代金卷, 不能轉讓

	private boolean isDeleted;

	public MemberPO() {
		// TODO Auto-generated constructor stub
	}

	public MemberPO(String id, MemberType memberType, MemberVipLevel memberVipLevel, String name, String phoneNumber,
			String address, String postcode, double credit, double receivable, double payment,
			ArrayList<VoucherPO> vouchers, String defaultSalesman, String email) {
		this.id = id;
		this.memberType = memberType;
		this.memberVipLevel = memberVipLevel;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.postcode = postcode;
		this.credit = credit;
		this.receivable = receivable;
		this.payment = payment;
		Set<VoucherPO> resultVoucher = new HashSet<>(vouchers);
		this.vouchers = resultVoucher;
		this.defaultSalesman = defaultSalesman;
		this.email = email;
	}

	public MemberPO(String id, MemberType memberType, MemberVipLevel memberVipLevel, String name, String phoneNumber,
			String address, String postcode, double credit, double receivable, double payment, Set<VoucherPO> vouchers,
			String defaultSalesman, String email) {
		this.id = id;
		this.memberType = memberType;
		this.memberVipLevel = memberVipLevel;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.postcode = postcode;
		this.credit = credit;
		this.receivable = receivable;
		this.payment = payment;
		this.vouchers = vouchers;
		this.defaultSalesman = defaultSalesman;
		this.email = email;
	}

	public void addVoucher(VoucherPO voucher) {
		vouchers.add(voucher);
	}

	public String getAddress() {
		return address;
	}

	public double getCredit() {
		return credit;
	}

	public String getDefaultSalesman() {
		return defaultSalesman;
	}

	public String getEmail() {
		return email;
	}

	// for database
	public String getId() {
		return id;
	}

	// for database
	public Set<InitializeAccountPO> getInitializeAccountPOs() {
		return initializeAccountPOs;
	}

	public MemberType getMemberType() {
		return memberType;
	}

	public MemberVipLevel getMemberVipLevel() {
		return memberVipLevel;
	}

	public String getName() {
		return name;
	}

	public double getPayment() {
		return payment;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPostcode() {
		return postcode;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public double getReceivable() {
		return receivable;
	}

	// for database
	public Set<VoucherPO> getVouchers() {
		return vouchers;
	}

	public ArrayList<VoucherPO> getVouchersAsList() {
		ArrayList<VoucherPO> vouchersResult = new ArrayList<>(vouchers);
		return vouchersResult;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public void setDefaultSalesman(String defaultSalesman) {
		this.defaultSalesman = defaultSalesman;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// for database
	public void setId(String id) {
		this.id = id;
	}

	// for database
	public void setInitializeAccountPOs(Set<InitializeAccountPO> initializeAccountPOs) {
		this.initializeAccountPOs = initializeAccountPOs;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public void setMemberVipLevel(MemberVipLevel memberVipLevel) {
		this.memberVipLevel = memberVipLevel;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setReceivable(double receivable) {
		this.receivable = receivable;
	}

	public void setVouchers(ArrayList<VoucherPO> vouchers) {
		Set<VoucherPO> resultVouchers = new HashSet<>(vouchers);
		this.vouchers = resultVouchers;
	}

	// for database
	public void setVouchers(Set<VoucherPO> vouchers) {
		this.vouchers = vouchers;
	}

	@Override
	public String toString() {
		return "MemberPO [address=" + address + ", credit=" + credit + ", defaultSalesman=" + defaultSalesman
				+ ", email=" + email + ", id=" + id + ", initializeAccountPOs=" + initializeAccountPOs + ", memberType="
				+ memberType + ", memberVipLevel=" + memberVipLevel + ", name=" + name + ", payment=" + payment
				+ ", phoneNumber=" + phoneNumber + ", postcode=" + postcode + ", receivable=" + receivable
				+ ", vouchers=" + vouchers + "]";
	}

	public MemberVO toVO() {
		ArrayList<VoucherVO> v = new ArrayList<>();
		for (VoucherPO i : vouchers)
			v.add(i.toVO());
		return new MemberVO(address, credit, defaultSalesman, email, id, memberType, memberVipLevel, name, payment,
				phoneNumber, postcode, receivable, v);
	}

	public void useVoucher(VoucherPO voucher) {// 使用代金券时要保证该用户有这个代金券，否则什么都不会触发；
		Iterator<VoucherPO> it = vouchers.iterator();
		while (it.hasNext()) {
			VoucherPO own_voucher = it.next();
			if (own_voucher.isSame(voucher)) {
				it.remove();
				break;
			}
		}
	}

}
