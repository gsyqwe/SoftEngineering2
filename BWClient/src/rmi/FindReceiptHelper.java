package rmi;

import java.rmi.Remote;

import service.FindReceiptMethodService;

public class FindReceiptHelper {
	private Remote remote;
	private static FindReceiptHelper findReceiptHelper;

	public static FindReceiptHelper getInstance() {
		if (findReceiptHelper == null)
			findReceiptHelper = new FindReceiptHelper();
		return findReceiptHelper;
	}

	public static FindReceiptHelper getInstance(Remote r) {
		if (findReceiptHelper == null)
			findReceiptHelper = new FindReceiptHelper(r);
		return findReceiptHelper;
	}

	private FindReceiptHelper() {
	}

	private FindReceiptHelper(Remote r) {
		this.remote = r;
	}

	public FindReceiptMethodService getService() {
		return (FindReceiptMethodService) remote;
	}
}
