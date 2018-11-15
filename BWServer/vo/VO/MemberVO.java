package VO;

import java.io.Serializable;
import java.util.ArrayList;

import PO.MemberPO;
import PO.VoucherPO;
import enums.MemberType;
import enums.MemberVipLevel;

public class MemberVO implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String address;
	private double credit; // 淇＄敤椤嶅害
	private String defaultSalesman; // 榛樿獚妤嫏鍝�
	private String email;
	private String id;
	private MemberType memberType;
	private MemberVipLevel memberVipLevel;
	// 锟斤拷锟斤拷锟斤拷锟斤拷锟界话锟斤拷锟斤拷址锟斤拷锟绞编、锟斤拷锟斤拷锟斤拷锟戒、应锟秸讹拷取锟接︼拷铡锟接︼拷锟斤拷锟斤拷锟斤拷械拇锟斤拷锟斤拷锟斤拷锟斤拷锟侥拷锟揭碉拷锟皆憋拷锟�
	private String name;
	private double payment; // 鎳変粯
	private String phoneNumber;
	private String postcode;
	private double receivable; // 鎳夋敹
	private ArrayList<VoucherVO> vouchers = new ArrayList<>(); // 浠ｉ噾鍗�,
																// 铏涙摤浠ｉ噾鍗�,
																// 涓嶈兘杞夎畵

	public MemberVO(String address, double credit, String defaultSalesman, String email, String id,
			MemberType memberType, MemberVipLevel memberVipLevel, String name, double payment, String phoneNumber,
			String postcode, double receivable, ArrayList<VoucherVO> vouchers) {
		super();
		this.address = address;
		this.credit = credit;
		this.defaultSalesman = defaultSalesman;
		this.email = email;
		this.id = id;
		this.memberType = memberType;
		this.memberVipLevel = memberVipLevel;
		this.name = name;
		this.payment = payment;
		this.phoneNumber = phoneNumber;
		this.postcode = postcode;
		this.receivable = receivable;
		this.vouchers = vouchers;
	}

	public MemberVO() {

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public String getDefaultSalesman() {
		return defaultSalesman;
	}

	public void setDefaultSalesman(String defaultSalesman) {
		this.defaultSalesman = defaultSalesman;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public MemberVipLevel getMemberVipLevel() {
		return memberVipLevel;
	}

	public void setMemberVipLevel(MemberVipLevel memberVipLevel) {
		this.memberVipLevel = memberVipLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public double getReceivable() {
		return receivable;
	}

	public void setReceivable(double receivable) {
		this.receivable = receivable;
	}

	public ArrayList<VoucherVO> getVouchers() {
		return vouchers;
	}

	public void setVouchers(ArrayList<VoucherVO> vouchers) {
		this.vouchers = vouchers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MemberPO toPO(){
		ArrayList<VoucherPO> v = new ArrayList<>();
		for(VoucherVO i:vouchers)
			v.add(i.toPO());
		return new MemberPO(id,memberType,memberVipLevel, name, phoneNumber,
				address, postcode,credit,receivable, payment,
				v, defaultSalesman,email);
	}

	@Override
	public String toString() {
		return "MemberPO [address=" + address + ", credit=" + credit + ", defaultSalesman=" + defaultSalesman
				+ ", email=" + email + ", id=" + id + ", memberType="
				+ memberType + ", memberVipLevel=" + memberVipLevel + ", name=" + name + ", payment=" + payment
				+ ", phoneNumber=" + phoneNumber + ", postcode=" + postcode + ", receivable=" + receivable
				+ ", vouchers=" + vouchers + "]";
	}

}
