package receiptFinder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import PO.InventoryReceiptPO;
import enums.ReceiptState;
import inventoryreceiptdata.InventoryReceiptDataUseDataBase;
import inventoryreceiptdataservice.InventoryReceiptDataService;

public class InventoryFinder implements ReceiptDataServiceInfo,Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	InventoryReceiptDataService dataService = new InventoryReceiptDataUseDataBase();

	@Override
	public InventoryReceiptPO findByID(String targetID) {
		// TODO Auto-generated method stub
		return dataService.findByID(targetID);
	}

	@Override
	public ArrayList<InventoryReceiptPO> findByUser(String userID) {
		// TODO Auto-generated method stub
		ArrayList<InventoryReceiptPO> inventory = dataService.findByUser(userID);
		if(inventory == null){
			return new ArrayList<>();
		}

		return inventory;
	}

	@Override
	public ArrayList<InventoryReceiptPO> findByDate(Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		ArrayList<InventoryReceiptPO> inventory = dataService.findByDate(startTime, endTime);
		if(inventory == null){
			return new ArrayList<>();
		}

		return inventory;
	}

	@Override
	public ArrayList<InventoryReceiptPO> findByReceiptState(ReceiptState receiptState) {
		// TODO Auto-generated method stub
		ArrayList<InventoryReceiptPO> inventory = dataService.findByReceiptState(receiptState);
		if(inventory == null){
			return new ArrayList<>();
		}

		return inventory;
	}

	@Override
	public ArrayList<InventoryReceiptPO> findByMember(String memberID) {//库存管理人员不存在findByMember,返回一个空的ArrayList
		// TODO Auto-generated method stub
		return new ArrayList<InventoryReceiptPO>();
	}

	@Override
	public ArrayList<InventoryReceiptPO> findByRepository(String repositoryName) {
		// TODO Auto-generated method stub
		return new ArrayList<>();//没有通过Repository查找，直接返回空ArrayList
	}

}
