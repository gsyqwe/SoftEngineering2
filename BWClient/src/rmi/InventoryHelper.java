package rmi;

import java.rmi.Remote;

import service.InventoryblService;

public class InventoryHelper {

	private Remote remote;

	private static InventoryHelper inventoryHelper;

	public static InventoryHelper getInstance() {

		if (inventoryHelper == null)

			inventoryHelper = new InventoryHelper();

		return inventoryHelper;

	}

	public static InventoryHelper getInstance(Remote r) {

		if (inventoryHelper == null)

			inventoryHelper = new InventoryHelper(r);

		return inventoryHelper;

	}

	private InventoryHelper() {

	}

	private InventoryHelper(Remote r) {

		this.remote = r;

	}

	public InventoryblService getService() {

		return (InventoryblService) remote;

	}

}