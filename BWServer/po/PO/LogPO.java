package PO;

import java.util.Date;

import VO.LogVO;

public final class LogPO {
	public static LogPO SAMPLE_1 = new LogPO("LOG-00001", new Date(new Date().getTime() - 100000), "MEM-00004",
			"FINANCIAL_RECEIPT_ACCEPTED");
	public static LogPO SAMPLE_2 = new LogPO("LOG-00002", new Date(new Date().getTime() - 200000), "MEM-00002",
			"FINANCIAL_RECEIPT_REJECTED");
	public static LogPO SAMPLE_3 = new LogPO("LOG-00003", new Date(new Date().getTime() - 300000), "MEM-00001",
			"INITIALIZA_ACCOUNT");
	public static LogPO SAMPLE_4 = new LogPO("LOG-00004", new Date(new Date().getTime() - 400000), "MEM-00001",
			"NEW_INVENTORY_EMPLOYER");
	public static LogPO SAMPLE_5 = new LogPO("LOG-00005", new Date(new Date().getTime() - 500000), "MEM-00003",
			"I_HAVE_NO_IDEA");

	/*
	 * key for database
	 */
	private String id; // 日誌編號
	private Date time; // 日誌操作的時間
	private String userID; // 操作員
	private String content; // 日誌的內容
	private boolean isDeleted;

	public LogPO(String id, Date time, String userID, String content) {
		super();
		this.id = id;
		this.time = time;
		this.userID = userID;
		this.content = content;
	}

	public LogPO() {
		// TODO Auto-generated constructor stub
	}

	public String getContent() {
		return content;
	}

	// for database
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

	// for database
	public void setId(String id) {
		this.id = id;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "LogPO [id=" + id + ", time=" + time + ", userID=" + userID + ", content=" + content + "]";
	}

	public LogVO toVO() {
		return new LogVO(id, time, userID, content);
	}
}
