package VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import PO.EmailPO;

public class EmailVO implements Serializable,Comparable<EmailVO> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private List<String> content = new ArrayList<>();
	private String receiverID; // 接收者ID
	private String senderID; // 發送者ID
	private Date date; // 創建時間
	private Boolean isReaded; // 是否已讀

	public EmailVO(List<String> content, String receiverID, String senderID, Date date, Boolean isReaded) {
		super();
		this.content = content;
		this.receiverID = receiverID;
		this.senderID = senderID;
		this.date = date;
		this.isReaded = isReaded;
	}

	public EmailVO(String title,List<String> content, String receiverID, String senderID, Date date, Boolean isReaded) {
		super();
		this.title = title;
		this.content = content;
		this.receiverID = receiverID;
		this.senderID = senderID;
		this.date = date;
		this.isReaded = isReaded;
	}

	public EmailVO(String receiverID, String senderID, Date date) {
		super();
		this.receiverID = receiverID;
		this.senderID = senderID;
		this.date = date;
		content = new ArrayList<>();
		this.isReaded = false;
	}



	public EmailVO() {
		super();
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}

	public String getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}

	public String getSenderID() {
		return senderID;
	}

	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getIsReaded() {
		return isReaded;
	}

	public void setIsReaded(Boolean isReaded) {
		this.isReaded = isReaded;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EmailPO toPO() {

		return new EmailPO(title,content, receiverID, senderID, date, isReaded);

	}

	public void addContentItem(String item){
		ArrayList<String> content = (ArrayList<String>) this.getContent();
		content.add(item);
		this.setContent(content);
	}



	@Override
	public int compareTo(EmailVO o) {
		// TODO Auto-generated method stub
		if(isReaded.compareTo(o.isReaded) != 0){
			return isReaded.compareTo(o.isReaded);
		}
		else{
			return o.getDate().compareTo(this.getDate());
		}
	}

	@Override
	public String toString() {
		return "EmailVO [title=" + title + ", content=" + content + ", receiverID=" + receiverID + ", senderID="
				+ senderID + ", date=" + date + ", isReaded=" + isReaded + "]";
	}

}
