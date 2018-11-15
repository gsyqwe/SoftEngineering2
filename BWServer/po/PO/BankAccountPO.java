package PO;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import VO.BankAccountVO;
import idhelper.IDHelper;

public final class BankAccountPO {
	private static Calendar CALENDAR_1 = Calendar.getInstance();
	private static Calendar CALENDAR_2 = Calendar.getInstance();
	private static Calendar CALENDAR_3 = Calendar.getInstance();
	static {
		CALENDAR_1.set(2017, 11, 02);
		CALENDAR_2.set(2017, 11, 22);
		CALENDAR_3.set(2017, 11, 11);
		System.out.println(CALENDAR_1.getTime());
		System.out.println(CALENDAR_2.getTime());
		System.out.println(CALENDAR_3.getTime());
	}
	public static BankAccountPO SAMPLE_1 = new BankAccountPO("中國銀行",
			"BAT-" + IDHelper.getIDInfixOf(CALENDAR_1.getTime()) + "-00001", 1000.123, "123456789", "123456",
			CALENDAR_1.getTime());

	public static BankAccountPO SAMPLE_2 = new BankAccountPO("工商銀行",
			"BAT-" + IDHelper.getIDInfixOf(CALENDAR_1.getTime()) + "-00002", 123.111, "000000000", "111111",
			CALENDAR_1.getTime());

	public static BankAccountPO SAMPLE_3 = new BankAccountPO("瑞士銀行",
			"BAT-" + IDHelper.getIDInfixOf(CALENDAR_2.getTime()) + "-00001", 223.0, "987654321", "654321",
			CALENDAR_2.getTime());

	public static BankAccountPO SAMPLE_4 = new BankAccountPO("農業銀行","BAT-" + IDHelper.getIDInfixOf(CALENDAR_2.getTime()) + "-00002", 0, "111112345", "798123",CALENDAR_2.getTime());


	public static BankAccountPO SAMPLE_5 = new BankAccountPO("大豐銀行",
			"BAT-" + IDHelper.getIDInfixOf(CALENDAR_3.getTime()) + "-00001", 40, "121312345", "711123",
			CALENDAR_3.getTime());
	/*
	 * every bank account has a unique id. Format "BAT-yyyyMMdd-xxxxx" key for
	 * database
	 */
	private String id;
	/*
	 * how many money left
	 */
	private double amount;
	/*
	 * 9 bits of number, every bank account has a unique card number
	 */
	private String cardNumber;
	/*
	 * 6 bits of number, DID NOT show while display
	 */
	private String password;
	private String name;
	private Date date;
	/*
	 * database mapping
	 */
	private Set<InitializeAccountPO> initializeAccountPOs = new HashSet<>();
	private boolean isDeleted;

	public BankAccountPO() {
		// TODO Auto-generated constructor stub
	}

	public BankAccountPO(String name, String id, double amount, String cardNumber, String password, Date date) {
		this.name = name;
		this.id = id;
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.password = password;
		this.date = date;
	}

	public BankAccountPO(String name, String id, double amount, String cardNumber, String password) {
		this.name = name;
		this.id = id;
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.password = password;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// for database
	public Set<InitializeAccountPO> getInitializeAccountPOs() {
		return initializeAccountPOs;
	}

	// for database
	public void setInitializeAccountPOs(Set<InitializeAccountPO> initializeAccountPOs) {
		this.initializeAccountPOs = initializeAccountPOs;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	// getter
	public double getAmount() {
		return amount;
	}

	// getter
	public String getCardNumber() {
		return cardNumber;
	}

	// getter
	public String getId() {
		return id;
	}

	// getter
	public String getPassword() {
		return password;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDate() {
		return this.date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BankAccountPO [id=" + id + ", amount=" + amount + ", cardNumber=" + cardNumber + ", password="
				+ password + ", name=" + name + ", date=" + date + ", initializeAccountPOs=" + initializeAccountPOs
				+ "]";
	}

	public BankAccountVO toVO() {
		return new BankAccountVO(id, amount, cardNumber, password, name, date);
	}
} // class end
