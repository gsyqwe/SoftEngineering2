package receiptFinder;

import java.util.ArrayList;
import java.util.Date;

import PO.ReceiptPO;
import enums.ReceiptState;

public interface ReceiptDataServiceInfo {
	
	public ReceiptPO findByID(String targetID);//在看到列表后，点击一个列表项进入
	
	public ArrayList<? extends ReceiptPO> findByUser(String userID);
	
	public ArrayList<? extends ReceiptPO> findByDate(Date startTime, Date endTime);
	
	public ArrayList<? extends ReceiptPO> findByReceiptState(ReceiptState receiptState);//在查看经营历程表的时候，查看的一定是已经审核通过的单据
	
	public ArrayList<? extends ReceiptPO> findByMember(String memberID);//通过客户查找单据
	
	public ArrayList<? extends ReceiptPO> findByRepository(String repositoryName);//通过仓库查找

}
