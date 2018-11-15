package rmi;

import java.rmi.Remote;

import service.InventoryReceiptblService;

public class InventoryReceiptHelper {

	private Remote remote;

	private static InventoryReceiptHelper inventoryReceiptHelper;

	public static InventoryReceiptHelper getInstance() {

		if (inventoryReceiptHelper == null)

			inventoryReceiptHelper = new InventoryReceiptHelper();

		return inventoryReceiptHelper;

	}

	public static InventoryReceiptHelper getInstance(Remote r) {

		if (inventoryReceiptHelper == null)

			inventoryReceiptHelper = new InventoryReceiptHelper(r);

		return inventoryReceiptHelper;

	}

	private InventoryReceiptHelper() {

	}

	private InventoryReceiptHelper(Remote r) {

		this.remote = r;

	}

	public InventoryReceiptblService getService() {

		return (InventoryReceiptblService) remote;

	}

}