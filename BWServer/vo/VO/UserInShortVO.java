package VO;

import java.io.Serializable;

import enums.JobType;

public class UserInShortVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private JobType job;
	private String name;

	public UserInShortVO(String id,JobType job, String name) {
		this.id = id;
		this.job = job;
		this.name = name;
	}

	public UserInShortVO() {
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
	
}
