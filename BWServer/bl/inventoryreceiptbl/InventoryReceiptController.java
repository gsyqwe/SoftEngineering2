package inventoryreceiptbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import PO.CommodityPO;
import PO.InventoryReceiptPO;
import PO.LineItemPO;
import VO.CommodityVO;
import VO.InventoryReceiptVO;
import commoditydata.CommodityDataUseDataBase;
import commoditydataService.CommodityDataService;
import enums.InventoryReceiptType;
import enums.ReceiptState;
import enums.ResultMessage;
import inventoryreceiptdata.InventoryReceiptDataUseDataBase;
import inventoryreceiptdataservice.InventoryReceiptDataService;
import service.InventoryReceiptblService;

/**
 *
 * @author 82646 库存类单据里面的LineItem只包括商品id和数量，不包括单价
 */
public class InventoryReceiptController extends UnicastRemoteObject implements InventoryReceiptblService {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	InventoryReceiptPO receipt;
	CommodityDataService commodityDataService = new CommodityDataUseDataBase();
	InventoryReceiptDataService dataService = new InventoryReceiptDataUseDataBase();

	public InventoryReceiptController(InventoryReceiptPO receipt) throws RemoteException {
		this();
		this.receipt = receipt;
	}

	public InventoryReceiptController() throws RemoteException {
		super();
		commodityDataService = new CommodityDataUseDataBase();
		dataService = new InventoryReceiptDataUseDataBase();
	}

	public InventoryReceiptPO getReceipt() {
		return receipt;
	}

	public void setReceipt(InventoryReceiptPO receipt) {
		this.receipt = receipt;
	}

	@Override
	public ResultMessage sendAudit(InventoryReceiptVO receipt) throws RemoteException {
		// TODO Auto-generated method stub
		this.receipt = receipt.toPO();

		String suffix = dataService.getIDSuffix(this.receipt.getDate(), receipt.getInventoryType());
		String inventoryID = "IVE-" + dateToString(this.receipt.getDate()) + suffix;
		this.receipt.setId(inventoryID);

		this.receipt.setState(ReceiptState.SUBMITTED);// 修改单据状态的属性
		ResultMessage insertMessage = this.dataService.insert(this.receipt);// insertMessage包括成功和id重复
		if (insertMessage == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED) {
			ResultMessage updateMessage = this.dataService.update(receipt.getId(), this.receipt);
			return updateMessage;
		}

		return insertMessage;
	}

	@Override // 供保存草稿使用OperatorID+date是命名规则
	public ResultMessage endReceipt(InventoryReceiptVO receipt) throws RemoteException {
		// TODO Auto-generated method stub
		this.receipt = receipt.toPO();
		this.receipt.setState(ReceiptState.UNCOMMITTED);

		String draftID = "";
		String date = dateToStringSpecific(receipt.getDate());
		draftID = receipt.getOperatorID() + " " + date;
		this.receipt.setId(draftID);

		ResultMessage insertMessage = dataService.insert(this.receipt);
		if (insertMessage == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED) {
			return dataService.update(this.receipt.getId(), this.receipt);
		}
		return insertMessage;
	}

	@Override
	public ArrayList<CommodityVO> getCommodities() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CommodityVO> resultList = new ArrayList<>();
		ArrayList<CommodityPO> findList = commodityDataService.getAllList();
		for (CommodityPO commodity : findList) {
			resultList.add(commodity.toVO());
		}
		return resultList;
	}

	@Override
	public ResultMessage modifyReceipt(String targetID, InventoryReceiptVO inventoryReceipt) throws RemoteException {
		// TODO Auto-generated method stub
		return dataService.update(targetID, inventoryReceipt.toPO());
	}

	@Override
	public ResultMessage deleteReceipt(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return dataService.delete(ID);
	}

	@Override // 在这个方法中只检查单据所填写数量是否大于库存数量
	public ResultMessage completeAdd(InventoryReceiptVO receipt) throws RemoteException {
		// TODO Auto-generated method stub
		this.receipt = receipt.toPO();

		if (this.receipt.getInventoryType() == InventoryReceiptType.BREAKAGE) {
			for (LineItemPO item : this.receipt.getLineItemAsList()) {
				CommodityPO com = commodityDataService.findByID(item.getId());
				if (item.getQuantity() > com.getQuantity()) {
					return ResultMessage.INVENTORY_RECEIPT_BREAKAGE_NEGITIVE;
				}
			}
		}

		return this.sendAudit(receipt);
	}

	@Override
	public InventoryReceiptVO findByID(String id) throws RemoteException {
		// TODO Auto-generated method stub

		return dataService.findByID(id).toVO();
	}

	@Override
	public ArrayList<InventoryReceiptVO> findByUser(String userId) throws RemoteException {
		// TODO Auto-generated method stub
		return changeListToVo(dataService.findByUser(userId));
	}

	@Override
	public ArrayList<InventoryReceiptVO> findByDate(Date startTime, Date endTime) throws RemoteException {
		// TODO Auto-generated method stub
		return changeListToVo(dataService.findByDate(startTime, endTime));
	}

	@Override
	public ArrayList<InventoryReceiptVO> findByReceiptState(ReceiptState receiptState) throws RemoteException {
		// TODO Auto-generated method stub
		return changeListToVo(dataService.findByReceiptState(receiptState));
	}

	@Override
	public ArrayList<InventoryReceiptVO> findByInventoryReceiptType(InventoryReceiptType inventoryReceiptType)
			throws RemoteException {
		// TODO Auto-generated method stub
		return changeListToVo(dataService.findByInventoryReceiptType(inventoryReceiptType));
	}

	private ArrayList<InventoryReceiptVO> changeListToVo(ArrayList<InventoryReceiptPO> findList) {
		ArrayList<InventoryReceiptVO> resultList = new ArrayList<>();

		for (InventoryReceiptPO receipt : findList) {
			resultList.add(receipt.toVO());
		}

		return resultList;
	}

	@Override
	public ArrayList<InventoryReceiptVO> getDraftList(String userId) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<InventoryReceiptVO> userReceipt = this.findByUser(userId);
		ArrayList<InventoryReceiptVO> resultReceipt = new ArrayList<>();

		for (InventoryReceiptVO receipt : userReceipt) {
			if (receipt.getState() == ReceiptState.UNCOMMITTED) {
				resultReceipt.add(receipt);
			}
		}

		// 将得到的草稿时间从晚到早排序
		Collections.sort(resultReceipt, new Comparator<InventoryReceiptVO>() {
			@Override
			public int compare(InventoryReceiptVO fruit1, InventoryReceiptVO fruit2) {

				return fruit2.getDate().compareTo(fruit1.getDate());
			}
		});

		return resultReceipt;
	}

	// 工具方法，将时间以yyyyMMdd的字符串形式打印出来
	private String dateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String s = formatter.format(date);
		return s + "-";
	}

	// 工具方法，将时间以yyyyMMdd HH:mm:ss的字符串形式打印出来
	private String dateToStringSpecific(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String s = formatter.format(date);
		return s + "-";
	}

}
