package VO;

import java.io.Serializable;
import java.util.Date;

public class InitializeAccountInShortVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;//操作员姓名
    private Date date;//操作时间
    public InitializeAccountInShortVO(String name,Date date){
    	this.name=name;
    	this.date=date;
    }
	public InitializeAccountInShortVO() {
		super();
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
}
