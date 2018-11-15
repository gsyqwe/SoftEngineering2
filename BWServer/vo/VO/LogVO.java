package VO;

import java.io.Serializable;
import java.util.Date;

import PO.LogPO;

public class LogVO implements Serializable{//操作日志
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id; // 日誌編號
	private Date time; // 日誌操作的時間
	private String userID; // 操作員
	private String content; // 日誌的內容

	public LogVO(String id, Date time, String userID, String content) {
		super();
		this.id = id;
		this.time = time;
		this.userID = userID;
		this.content = content;
	}

	public LogVO() {
		super();
	}

	public String getContent() {
		return content;
	}

	public String getId() {
		return id;
	}

	public Date getTime() {
		return time;
	}

	public String getUserID() {
		return userID;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "LogPO [id=" + id + ", time=" + time + ", userID=" + userID + ", content=" + content + "]";
	}
	public LogPO toPO(){
		return new LogPO(id,time,userID,content);
	}
}
