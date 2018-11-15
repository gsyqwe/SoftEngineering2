package rmi;

import java.rmi.Remote;

import service.MemberblService;

public class MemberHelper {
	private Remote remote;
	private static MemberHelper memberHelper;

	public static MemberHelper getInstance() {
		if (memberHelper == null)
			memberHelper = new MemberHelper();
		return memberHelper;
	}

	public static MemberHelper getInstance(Remote r) {
		if (memberHelper == null)
			memberHelper = new MemberHelper(r);
		return memberHelper;
	}

	private MemberHelper(Remote r) {
		this.remote = r;
	}

	public MemberHelper() {
	}

	public MemberblService getService() {
		return (MemberblService) remote;
	}
}
