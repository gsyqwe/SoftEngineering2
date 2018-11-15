package PO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import VO.EmailVO;

public class EmailPO {
	/*
	 * key for database self-increment
	 */
	private long id;
	/*
	 * database mapping
	 */
	private String title;
	private List<String> content = new ArrayList<>();
	private String receiverID; // 接收者ID
	private String senderID; // 發送者ID
	private Date date; // 創建時間
	private Boolean isReaded; // 是否已讀
	private boolean isDeleted;
	private static List<String> CONTENT_1 = new ArrayList<>();
	private static List<String> CONTENT_2 = new ArrayList<>();
	private static List<String> CONTENT_3 = new ArrayList<>();
	private static List<String> CONTENT_4 = new ArrayList<>();
	private static List<String> CONTENT_5 = new ArrayList<>();
	static {
		CONTENT_1.add("你所提交的現金費用單據已獲得通過");
		CONTENT_2.add("你所提交的收款單據由總經理修改金額後獲得通過");
		CONTENT_3.add("你所提交的銷售單據已獲得通過");
		CONTENT_4.add("你所提交的現金費用單據沒有獲得通過");
		CONTENT_5.add("總經理已看過你所提交的庫存報溢單據");
	}
	public static EmailPO SAMPLE_1 = new EmailPO("Titile_1", CONTENT_1, UserPO.SAMPLE_1.getId(),
			UserPO.SAMPLE_2.getId(), new Date(new Date().getTime() - 756409), false);
	public static EmailPO SAMPLE_2 = new EmailPO("Titile_2", CONTENT_2, UserPO.SAMPLE_4.getId(),
			UserPO.SAMPLE_2.getId(), new Date(new Date().getTime() - 176409), false);
	public static EmailPO SAMPLE_3 = new EmailPO("Titile_3", CONTENT_3, UserPO.SAMPLE_1.getId(),
			UserPO.SAMPLE_2.getId(), new Date(new Date().getTime() - 179409), true);
	public static EmailPO SAMPLE_4 = new EmailPO("Titile_4", CONTENT_4, UserPO.SAMPLE_4.getId(),
			UserPO.SAMPLE_3.getId(), new Date(new Date().getTime() - 756409), false);
	public static EmailPO SAMPLE_5 = new EmailPO("Titile_5", CONTENT_1, UserPO.SAMPLE_2.getId(),
			UserPO.SAMPLE_3.getId(), new Date(new Date().getTime() - 1756409), false);

	public EmailPO() {
		// TODO Auto-generated constructor stub
	}

	public EmailPO(ArrayList<String> content, String receiverID, String senderID, Date date, Boolean isReaded) {
		this.content = content;
		this.receiverID = receiverID;
		this.senderID = senderID;
		this.date = date;
		this.isReaded = isReaded;
	}

	public EmailPO(String title, List<String> content, String receiverID, String senderID, Date date,
			Boolean isReaded) {
		super();
		this.title = title;
		this.content = content;
		this.receiverID = receiverID;
		this.senderID = senderID;
		this.date = date;
		this.isReaded = isReaded;
	}

	public EmailPO(List<String> content, String receiverID, String senderID, Date date, Boolean isReaded) {
		super();
		this.content = content;
		this.receiverID = receiverID;
		this.senderID = senderID;
		this.date = date;
		this.isReaded = isReaded;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// for database
	public long getId() {
		return id;
	}

	// for database
	public void setId(long id) {
		this.id = id;
	}

	// for database
	public List<String> getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	public Boolean getIsReaded() {
		return isReaded;
	}

	public String getReceiverID() {
		return receiverID;
	}

	public String getSenderID() {
		return senderID;
	}

	// for database
	public void setContent(List<String> content) {
		this.content = content;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setIsReaded(Boolean isReaded) {
		this.isReaded = isReaded;
	}

	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}

	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "EmailPO [id=" + id + ", content=" + content + ", receiverID=" + receiverID + ", senderID=" + senderID
				+ ", date=" + date + ", isReaded=" + isReaded + "]";
	}

	public EmailVO toVO() {
		return new EmailVO(title, content, receiverID, senderID, date, isReaded);
	}
}
