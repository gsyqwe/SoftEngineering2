package inventoryreceiptdataservice;

import java.util.ArrayList;
import java.util.Date;

import PO.InventoryReceiptPO;
import enums.InventoryReceiptType;
import enums.ReceiptState;
import enums.ResultMessage;

public interface InventoryReceiptDataService {
	public ResultMessage insert(InventoryReceiptPO po);

	public ResultMessage delete(String id); // 草稿單的id是用戶加時間

	public ResultMessage update(String targetID, InventoryReceiptPO replacement);

	public ArrayList<InventoryReceiptPO> getAllList();

	public InventoryReceiptPO findByID(String id);

	// public ArrayList<InventoryReceiptPO> findByUser(String userId);
	//
	// public ArrayList<InventoryReceiptPO> findByDate(Date startTime, Date
	// endTime);
	//
	// public ArrayList<InventoryReceiptPO> findByReceiptState(ReceiptState
	// receiptState);

	// 小分類 不是大分類
	public ArrayList<InventoryReceiptPO> findByInventoryReceiptType(InventoryReceiptType inventoryReceiptType);

	/*
	 * id userId date state type
	 */

	public ArrayList<InventoryReceiptPO> findByUser(String userID);

	public ArrayList<InventoryReceiptPO> findByDate(Date startTime, Date endTime);

	public ArrayList<InventoryReceiptPO> findByReceiptState(ReceiptState receiptState);// 在查看经营历程表的时候，查看的一定是已经审核通过的单据

	// public ArrayList<ReceiptPO> findByRepository(String repositoryName);//
	// 通过仓库查找
	public String getIDSuffix(Date date, InventoryReceiptType inventoryReceiptType) throws IllegalArgumentException;

}
