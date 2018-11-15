package PO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import VO.BankAccountVO;
import VO.CommodityVO;
import VO.InitializeAccountVO;
import VO.MemberVO;

public final class InitializeAccountPO {
	private static Set<BankAccountPO> BANK_ACCOUNT_1 = new HashSet<>();
	private static Set<BankAccountPO> BANK_ACCOUNT_2 = new HashSet<>();
	private static Set<BankAccountPO> BANK_ACCOUNT_3 = new HashSet<>();
	private static Set<BankAccountPO> BANK_ACCOUNT_4 = new HashSet<>();
	private static Set<BankAccountPO> BANK_ACCOUNT_5 = new HashSet<>();
	private static Set<CommodityPO> COMMODITY_1 = new HashSet<>();
	private static Set<CommodityPO> COMMODITY_2 = new HashSet<>();
	private static Set<CommodityPO> COMMODITY_3 = new HashSet<>();
	private static Set<CommodityPO> COMMODITY_4 = new HashSet<>();
	private static Set<CommodityPO> COMMODITY_5 = new HashSet<>();
	private static Set<MemberPO> MEMBER_1 = new HashSet<>();
	private static Set<MemberPO> MEMBER_2 = new HashSet<>();
	private static Set<MemberPO> MEMBER_3 = new HashSet<>();
	private static Set<MemberPO> MEMBER_4 = new HashSet<>();
	private static Set<MemberPO> MEMBER_5 = new HashSet<>();
	static {
		BANK_ACCOUNT_1.add(BankAccountPO.SAMPLE_1);
		BANK_ACCOUNT_1.add(BankAccountPO.SAMPLE_2);
		BANK_ACCOUNT_2.add(BankAccountPO.SAMPLE_3);
		BANK_ACCOUNT_2.add(BankAccountPO.SAMPLE_4);
		BANK_ACCOUNT_3.add(BankAccountPO.SAMPLE_5);
		BANK_ACCOUNT_3.add(BankAccountPO.SAMPLE_1);
		BANK_ACCOUNT_3.add(BankAccountPO.SAMPLE_2);
		BANK_ACCOUNT_4.add(BankAccountPO.SAMPLE_3);
		BANK_ACCOUNT_4.add(BankAccountPO.SAMPLE_4);
		BANK_ACCOUNT_5.add(BankAccountPO.SAMPLE_5);

		COMMODITY_1.add(CommodityPO.SAMPLE_1);
		COMMODITY_1.add(CommodityPO.SAMPLE_2);
		COMMODITY_2.add(CommodityPO.SAMPLE_3);
		COMMODITY_2.add(CommodityPO.SAMPLE_4);
		COMMODITY_2.add(CommodityPO.SAMPLE_5);
		COMMODITY_3.add(CommodityPO.SAMPLE_1);
		COMMODITY_3.add(CommodityPO.SAMPLE_2);
		COMMODITY_4.add(CommodityPO.SAMPLE_3);
		COMMODITY_4.add(CommodityPO.SAMPLE_4);
		COMMODITY_5.add(CommodityPO.SAMPLE_5);

		MEMBER_1.add(MemberPO.SAMPLE_1);
	}
	public static InitializeAccountPO SAMPLE_1 = new InitializeAccountPO(UserPO.SAMPLE_1.getId(), new Date(),
			COMMODITY_1.iterator(), MEMBER_1.iterator(), BANK_ACCOUNT_1.iterator());
	public static InitializeAccountPO SAMPLE_2 = new InitializeAccountPO(UserPO.SAMPLE_1.getId(), new Date(),
			COMMODITY_2.iterator(), MEMBER_2.iterator(), BANK_ACCOUNT_2.iterator());
	public static InitializeAccountPO SAMPLE_3 = new InitializeAccountPO(UserPO.SAMPLE_1.getId(), new Date(),
			COMMODITY_3.iterator(), MEMBER_3.iterator(), BANK_ACCOUNT_3.iterator());
	public static InitializeAccountPO SAMPLE_4 = new InitializeAccountPO(UserPO.SAMPLE_1.getId(), new Date(),
			COMMODITY_4.iterator(), MEMBER_4.iterator(), BANK_ACCOUNT_4.iterator());
	public static InitializeAccountPO SAMPLE_5 = new InitializeAccountPO(UserPO.SAMPLE_1.getId(), new Date(),
			COMMODITY_5.iterator(), MEMBER_5.iterator(), BANK_ACCOUNT_5.iterator());
	/*
	 * key for database self-increment
	 */
	private long id;
	/*
	 * database mapping
	 */
	private Set<BankAccountPO> bankAccountPOList = new HashSet<>();
	/*
	 * database mapping
	 */
	private Set<CommodityPO> commodityPOList = new HashSet<>();
	/*
	 * database mapping
	 */
	private Set<MemberPO> memberPOList = new HashSet<>();
	private Date date;
	private String name; // it should be the operatorID
	private boolean isDeleted;

	public InitializeAccountPO() {
	}

	public InitializeAccountPO(String name, Date date, Iterator<CommodityPO> commodityPOs, Iterator<MemberPO> memberPOs,
			Iterator<BankAccountPO> bankAccountPOs) {
		this.date = date;
		while (commodityPOs.hasNext()) {
			this.commodityPOList.add(commodityPOs.next());
		}
		while (memberPOs.hasNext()) {
			this.memberPOList.add(memberPOs.next());
		}
		while (bankAccountPOs.hasNext()) {
			this.bankAccountPOList.add(bankAccountPOs.next());
		}
	}

	// for database
	public Set<BankAccountPO> getBankAccountPOList() {
		return bankAccountPOList;
	}

	// for database
	public Set<CommodityPO> getCommodityPOList() {
		return commodityPOList;
	}

	public Date getDate() {
		return date;
	}

	public long getId() {
		return id;
	}

	// for database
	public Set<MemberPO> getMemberPOList() {
		return memberPOList;
	}

	// for database
	public void setBankAccountPOList(Set<BankAccountPO> bankAccountPOList) {
		this.bankAccountPOList = bankAccountPOList;
	}

	// for database
	public void setCommodityPOList(Set<CommodityPO> commodityPOList) {
		this.commodityPOList = commodityPOList;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setId(long id) {
		this.id = id;
	}

	// for database
	public void setMemberPOList(Set<MemberPO> memberPOList) {
		this.memberPOList = memberPOList;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "InitializeAccountPO [id=" + id + ", bankAccountPOList=" + bankAccountPOList + ", commodityPOList="
				+ commodityPOList + ", memberPOList=" + memberPOList + ", date=" + date + ", name=" + name + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public InitializeAccountVO toVO() {
		ArrayList<CommodityVO> com = new ArrayList<>();
		for (CommodityPO i : commodityPOList)
			com.add(i.toVO());
		ArrayList<MemberVO> member = new ArrayList<>();
		for (MemberPO i : memberPOList)
			member.add(i.toVO());
		ArrayList<BankAccountVO> bank = new ArrayList<>();
		for (BankAccountPO i : bankAccountPOList)
			bank.add(i.toVO());
		return new InitializeAccountVO(bank, com, date, name, member);
	}

}
