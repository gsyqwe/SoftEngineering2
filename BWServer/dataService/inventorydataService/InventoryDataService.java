package inventorydataService;

import java.util.ArrayList;
import java.util.Date;

import PO.InventoryPO;
import PO.RecordPO;
import enums.FinancialReceiptType;
import enums.InventoryReceiptType;
import enums.ResultMessage;

public interface InventoryDataService {

	/**
	 * 看出庫入庫的紀錄
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public ArrayList<RecordPO> findByTime(Date startTime, Date endTime);

	public ResultMessage insert(RecordPO po);

	public ResultMessage insert(InventoryPO po);

}
