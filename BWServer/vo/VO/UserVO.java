package VO;

import java.io.Serializable;

import PO.UserPO;
import enums.JobType;

public class UserVO implements Serializable{
       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id; // every one has a unique id. Format "MAN-xxx" xxx為加入公司順序
	private String password; //
	private JobType job; //
	private String name; //
	// private final ArrayList<String> email;

	public UserVO(String id, String password, JobType job, String name) {
		this.id = id;
		this.password = password;
		this.job = job;
		this.name = name;
	}

	public UserVO() {
		super();
	}

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
	
	public String getJobName(){
		return job.toString();
	}

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

	public UserPO toPO(){
		UserPO u=new UserPO();
		u.setName(name);
		u.setJob(job);
		u.setPassword(password);
		u.setId(id);
		return u;
	}
}
