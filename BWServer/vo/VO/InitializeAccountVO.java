package VO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import PO.BankAccountPO;
import PO.CommodityPO;
import PO.InitializeAccountPO;
import PO.MemberPO;
import VO.BankAccountVO;
import VO.CommodityVO;
import VO.InitializeAccountVO;
import VO.MemberVO;
public class InitializeAccountVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private ArrayList<BankAccountVO> bankAccountVOList = new ArrayList<BankAccountVO>();
	private ArrayList<CommodityVO> commodityVOList = new ArrayList<CommodityVO>();
	private Date date;
	private String name; // it should be the operatorID
	private ArrayList<MemberVO> memberVOList = new ArrayList<MemberVO>();
	public InitializeAccountVO(ArrayList<BankAccountVO> bankAccountVOList,
			ArrayList<CommodityVO> commodityVOList, Date date, String name,
			ArrayList<MemberVO> memberVOList) {
		super();
		this.bankAccountVOList = bankAccountVOList;
		this.commodityVOList = commodityVOList;
		this.date = date;
		this.name = name;
		this.memberVOList = memberVOList;
	}
	
	public InitializeAccountVO() {
		super();
	}

	public InitializeAccountVO(ArrayList<BankAccountVO> bankAccounts, ArrayList<CommodityVO> commodities, ArrayList<MemberVO> members, ArrayList<BankAccountVO> bankAccounts2, Object object, Object object2) {
		super();
	}
	public ArrayList<BankAccountVO> getBankAccountPOList() {
		return bankAccountVOList;
	}
	public void setBankAccountVOList(ArrayList<BankAccountVO> bankAccountPOList) {
		this.bankAccountVOList = bankAccountPOList;
	}
	public ArrayList<CommodityVO> getCommodityVOList() {
		return commodityVOList;
	}
	public void setCommodityVOList(ArrayList<CommodityVO> commodityVOList) {
		this.commodityVOList = commodityVOList;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<MemberVO> getMemberPOList() {
		return memberVOList;
	}
	public void setMemberPOList(ArrayList<MemberVO> memberPOList) {
		this.memberVOList = memberPOList;
	}

	public InitializeAccountPO toPO(){
		InitializeAccountPO po=new InitializeAccountPO();
		Set<BankAccountPO> resultAccount = new HashSet<>();
		for(BankAccountVO account:bankAccountVOList){
			resultAccount.add(account.toPO());
		}
		po.setBankAccountPOList(resultAccount);
		
		Set<CommodityPO> resultCommodity = new HashSet<>();
		for(CommodityVO account:commodityVOList){
			resultCommodity.add(account.toPO());
		}
		
		po.setCommodityPOList(resultCommodity);
		po.setDate(date);
		
		Set<MemberPO> resultMember = new HashSet<>();
		for(MemberVO member:memberVOList){
			resultMember.add(member.toPO());
		}
		po.setMemberPOList(resultMember);
		po.setName(name);
		return po;
	}
}
