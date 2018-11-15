package Receipt;

import java.rmi.RemoteException;

import PO.ReceiptPO;
import VO.ReceiptVO;
import enums.ResultMessage;
import service.ReceiptWorks;

/**
 *
 * @author JiYuzhe
 * 这是一个被架空的父类，就是为了多态，父类本身一点卵用都没有
 *
 */

public class Receipt implements ReceiptWorks{

	@Override
	public ReceiptVO prepareToShow() throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage passAudit(String managerID) throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage failPassing(String managerID) throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage setReceiptPO(ReceiptVO newReceipt,String managerID) throws RemoteException{
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public ReceiptPO getReceiptPO() throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}


}
