package financialreceiptdataservice;

import java.util.ArrayList;
import java.util.Date;

import PO.FinancialReceiptPO;
import enums.FinancialReceiptType;
import enums.ReceiptState;
import enums.ResultMessage;

public interface FinancialReceiptDataService {
	public ResultMessage insert(FinancialReceiptPO po);

	public ResultMessage delete(String targetID);

	public ResultMessage update(String targetId, FinancialReceiptPO replacement);

	public ArrayList<FinancialReceiptPO> getAllList();

	public FinancialReceiptPO findByID(String targetID);

//	public ArrayList<FinancialReceiptPO> findByUser(String userID);

	public ArrayList<FinancialReceiptPO> findByFinancialReceipType(FinancialReceiptType financialReceiptType);

//	public ArrayList<FinancialReceiptPO> findByDate(Date startTime, Date endTime);

//	public ArrayList<FinancialReceiptPO> findByReceiptState(ReceiptState receiptState);

	// id user type time state

	/**
	 *
	 * @param date
	 * @return
	 * @throws IllegalArgumentException
	 */
	public String getIDSuffix(Date date, FinancialReceiptType financialReceiptType) throws IllegalArgumentException;

	public ArrayList<FinancialReceiptPO> findByUser(String userID);

	public ArrayList<FinancialReceiptPO> findByDate(Date startTime, Date endTime);

	public ArrayList<FinancialReceiptPO> findByReceiptState(ReceiptState receiptState);// 在查看经营历程表的时候，查看的一定是已经审核通过的单据

	public ArrayList<FinancialReceiptPO> findByMember(String memberID);// 通过客户查找单据

}
