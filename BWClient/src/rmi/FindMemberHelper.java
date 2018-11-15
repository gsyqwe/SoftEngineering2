package rmi;

import java.rmi.Remote;

import service.FindMemberService;

public class FindMemberHelper {

	private Remote remote;
	private static FindMemberHelper findMemberHelper;
	public static FindMemberHelper getInstance(){
		if(findMemberHelper==null)
			findMemberHelper=new FindMemberHelper();
		return findMemberHelper;
	}
	public static FindMemberHelper getInstance(Remote r){
		if(findMemberHelper==null)
			findMemberHelper=new FindMemberHelper(r);
		return findMemberHelper;
	}

	private  FindMemberHelper() {
	}
	private  FindMemberHelper(Remote r) {
		this.remote=r;
	}

	public FindMemberService getService(){
		return (FindMemberService) remote;
	}
}
