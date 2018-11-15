package inventorybl;



import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import PO.RecordPO;
import VO.RecordVO;
import enums.ResultMessage;
import inventorydata.InventoryDataUseDataBase;
import inventorydataService.InventoryDataService;





public class Inventory {

	InventoryDataService inventoryDataService = new InventoryDataUseDataBase();

	public Inventory(){

		inventoryDataService=new InventoryDataUseDataBase();

	}



	public ArrayList<RecordVO> showRecord(Date startTime, Date endTime) throws RemoteException {

		ArrayList<RecordPO> po=inventoryDataService.findByTime(startTime, endTime);

		ArrayList<RecordVO> vo=new ArrayList<RecordVO>();

		for(RecordPO i:po){

			vo.add(i.toVO());

		}

		return vo;

	}



	public ResultMessage addRecord(RecordVO vo){

		return inventoryDataService.insert(vo.toPO());



	}





}