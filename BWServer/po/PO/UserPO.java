package PO;

import VO.UserVO;
import enums.JobType;

public final class UserPO {
	public static final UserPO SAMPLE_1 = new UserPO("MAN-001", "123456", JobType.FINANCIAL, "賴健明");
	public static final UserPO SAMPLE_2 = new UserPO("MAN-002", "654321", JobType.SALESMAN, "吉宇哲");
	public static final UserPO SAMPLE_3 = new UserPO("MAN-003", "112233", JobType.INVENTORY, "袁玉婷");
	public static final UserPO SAMPLE_4 = new UserPO("MAN-004", "332211", JobType.MANAGER, "顧詩玉");

	/*
	 * key for database
	 */
	private String id; // every one has a unique id. Format "MAN-xxx" xxx為加入公司順序
	private String password; //
	private JobType job; //
	private String name; //
	// private final ArrayList<String> email;
	private boolean isDeleted;


	public UserPO() {
		// TODO Auto-generated constructor stub
	}

	public UserPO(String id, String password, JobType job, String name) {
		this.id = id;
		this.password = password;
		this.job = job;
		this.name = name;
	}

	// for database
	public String getId() {
		return id;
	}

	public JobType getJob() {
		return job;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	// for database
	public void setId(String id) {
		this.id = id;
	}

	public void setJob(JobType job) {
		this.job = job;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "UserPO [id=" + id + ", password=" + password + ", job=" + job + ", name=" + name + "]";
	}

	public UserVO toVO() {
		return new UserVO(id, password, job, name);
	}

}
