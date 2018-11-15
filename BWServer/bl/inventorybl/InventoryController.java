package inventorybl;



import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import java.util.Date;

import PO.RecordPO;

import VO.RecordVO;

import enums.ResultMessage;

import service.InventoryblService;



public class InventoryController extends UnicastRemoteObject implements InventoryblService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Inventory inventory;

	

	public InventoryController() throws RemoteException{

		super();

		inventory = new Inventory();

	}

	public InventoryController(Inventory i)throws RemoteException{

		inventory=i;

	}

	@Override

	public ArrayList<RecordVO> showRecord(Date startTime, Date endTime) throws RemoteException {

		return inventory.showRecord(startTime, endTime);

	}

	@Override

	public ResultMessage addRecord(RecordVO vo) throws RemoteException {

		return inventory.addRecord(vo);

	}

	

}