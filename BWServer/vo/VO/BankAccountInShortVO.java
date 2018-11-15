package VO;

import java.io.Serializable;


public class BankAccountInShortVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BankAccountInShortVO() {
		super();
	}

	/*
	 * every bank account has a unique id. Format "BAT-yyyyMMdd-xxxxx"
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

	//复选框添加
	public BankAccountInShortVO(String id, double amount, String cardNumber) {
		this.id = id;
		this.amount = amount;
		this.cardNumber = cardNumber;
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


	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setId(String id) {
		this.id = id;
	}
	

} // class end

