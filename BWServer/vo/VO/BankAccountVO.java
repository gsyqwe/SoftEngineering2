package VO;

import java.io.Serializable;
import java.util.Date;

import PO.BankAccountPO;

public class BankAccountVO implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
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
	private String password;
	private String name;
	private Date date;

	public BankAccountVO(String id, double amount, String cardNumber,
			String password, String name, Date date) {
		super();
		this.id = id;
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.password = password;
		this.name = name;
		this.date=date;
	}
	public BankAccountVO(){

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BankAccountPO toPO(){
		BankAccountPO po=new BankAccountPO();
		po.setAmount(amount);
		po.setCardNumber(cardNumber);
		po.setDate(date);
		po.setId(id);
		po.setName(name);
		po.setPassword(password);
		return po;
	}
	public boolean equals(BankAccountVO vo){
		if(vo.getAmount()!=amount)
			return false;
		else if(!vo.getCardNumber().equals(cardNumber))
			return false;
		//else if(!vo.getDate().equals(date))
			//return false;
		else if(!vo.getId().equals(id))
			return false;
		else if(!vo.getName().equals(name))
			return false;
		else if(!vo.getPassword().equals(password))
			return false;
		else
			return true;
	}

	@Override
	public String toString() {
		return "BankAccountPO [id=" + id + ", amount=" + amount + ", cardNumber=" + cardNumber + ", password="
				+ password + ", name=" + name + ", date=" + date + ","
				+ "]";
	}
}
