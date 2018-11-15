package salesdataService;

import java.util.ArrayList;
import java.util.Date;

import PO.SalesReceiptPO;
import enums.ReceiptState;
import enums.ResultMessage;
import enums.SalesReceiptType;

/**
 * @version
 * @author
 *
 */

// 擱置 : 一些find by 甚麼的接口，需要一个像其他类一样的查找后缀的方法
public interface SalesDataService {
	public ResultMessage delete(String id);

	public SalesReceiptPO findByID(String id);

	public ArrayList<SalesReceiptPO> findByTime(Date beginTime, Date endTime);

	public ResultMessage insert(SalesReceiptPO po);

	public ResultMessage update(String targetID, SalesReceiptPO replacement);

	public ArrayList<SalesReceiptPO> getAllList();

	public ArrayList<SalesReceiptPO> findByUser(String userID);

	public ArrayList<SalesReceiptPO> findByDate(Date startTime, Date endTime);

	public ArrayList<SalesReceiptPO> findByReceiptState(ReceiptState receiptState);// 在查看经营历程表的时候，查看的一定是已经审核通过的单据

	public ArrayList<SalesReceiptPO> findByMember(String memberID);// 通过客户查找单据

	public ArrayList<SalesReceiptPO> findByRepository(String repositoryName);// 通过仓库查找

	public String getIDSuffix(Date date, SalesReceiptType salesReceiptType) throws IllegalArgumentException;

	public ArrayList<SalesReceiptPO> findBySalesType(SalesReceiptType type);//新添加方法，通过SalesReciptType来查找销售类单据

}
