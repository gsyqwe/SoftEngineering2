package commoditybl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import PO.CommodityPO;
import VO.CommodityVO;
import VO.EmailForAlarmVO;
import VO.RecordVO;
import VO.StockInAndOutVO;
import categorybl.CategoryController;
import commoditydata.CommodityDataUseDataBase;
import commoditydataService.CommodityDataService;
import enums.ResultMessage;
import enums.StockInAndOut;
import inventorybl.InventoryController;
import service.CategoryblService;
import service.InventoryblService;

public class Commodity {

	CommodityDataService commodityDataService;

	CategoryblService categoryblService;


	public Commodity(){

		commodityDataService=new CommodityDataUseDataBase();
	}


	public ResultMessage addCommodity(CommodityVO vo) throws RemoteException {
		CommodityPO temp = commodityDataService.findByName(vo.getName());
		if (temp != null)
			return ResultMessage.COMMODITY_INSERT_FAIL_NAME_ALREADY_EXSIT;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String id = "COM-"+sdf.format(new Date())+"-"+commodityDataService.getIDSuffix();//得到目前最大的分类编号
		vo.setId(id);
		return commodityDataService.insert(vo.toPO());
	}

	public ResultMessage modifyCommodity(CommodityVO vo) throws RemoteException {

		return commodityDataService.update(vo.getId(), vo.toPO());

	}

	public CommodityVO findByName(String name)throws RemoteException{
		return commodityDataService.findByName(name).toVO();
	}

	public ResultMessage deleteCommodity(String id) throws RemoteException {

		CommodityVO vo = findByID(id);

		if (vo.getQuantity() != 0)

			return ResultMessage.COMMODITY_DELETE_FAIL;

		return commodityDataService.delete(id);

	}

	public CommodityVO findByID(String id) throws RemoteException {

		return commodityDataService.findByID(id).toVO();

	}

	public ArrayList<CommodityVO> findWithKeyword(String keyword) throws RemoteException {

		ArrayList<CommodityPO> po = commodityDataService.findWithKeyword(keyword);

		ArrayList<CommodityVO> vo = new ArrayList<CommodityVO>();

		for (CommodityPO i : po) {

			vo.add(i.toVO());

		}

		return vo;

	}

	public ResultMessage stockInAndOut(StockInAndOutVO vo, ArrayList<EmailForAlarmVO> emails) throws RemoteException {

		CommodityVO com = findByID(vo.getCommodityID());

		if (vo.getType() == StockInAndOut.STOCK_IN) {

			com.setQuantity(com.getQuantity() + vo.getNumber());
			com.setRecentBid(vo.getAmount() / vo.getNumber());
			com.setBid((com.getBid() * com.getQuantity() + vo.getAmount()) / (com.getQuantity() + vo.getNumber()));
		}

		else {

			com.setQuantity(com.getQuantity() - vo.getNumber());
			com.setRecentRetailPrice(vo.getAmount() / vo.getNumber());
			com.setRetailPrice(
					(com.getRetailPrice() * com.getQuantity() + vo.getAmount()) / (com.getQuantity() + vo.getNumber()));
		}

		if (com.getQuantity() < 0)

			return ResultMessage.COMMODITY_STOCKIN_FAIL;

		else {

			InventoryblService inventoryService = new InventoryController();

			RecordVO record = new RecordVO();
			record.setAmount(vo.getAmount());
			record.setCommodityID(vo.getCommodityID());
			record.setQuantity(vo.getNumber());
			record.setStockInAndOutType(vo.getType());
			record.setStockInAndOutTime(new Date());
			inventoryService.addRecord(record);
			if (com.getQuantity() < com.getAlertQuantity())

				alarm(com.getId(), emails);//为所有emails添加一行信息

		}

		return commodityDataService.update(com.getId(), com.toPO());

	}

	public void alarm(String commodityID, ArrayList<EmailForAlarmVO> emails) {
		CommodityPO commodity = commodityDataService.findByID(commodityID);

		emails = EmailForAlarmVO.AddEmailItem(commodity.toVO(), emails);
	}

	public ArrayList<CommodityVO> showCommodityList() throws RemoteException {

		ArrayList<CommodityPO> po = commodityDataService.getAllList();

		ArrayList<CommodityVO> vo = new ArrayList<CommodityVO>();

		for (CommodityPO i : po) {

			vo.add(i.toVO());

		}

		return vo;

	}

	/*public String generate(String categoryID) throws IllegalArgumentException,RemoteException {

		categoryblService = new CategoryController();

		ArrayList<String> commodityNameAndID = categoryblService.getCommodityList(categoryID);
		int ID = 0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String date=sdf.format(new Date());
		if(commodityNameAndID!=null){
			for (String i : commodityNameAndID) {
	
				String[] nameAndID=i.split("/");
				String[] dateAndid=nameAndID[1].split("-");
	
				if(Integer.parseInt(date)==Integer.parseInt(dateAndid[1]) && ID<Integer.parseInt(dateAndid[2]) )
	
					ID=Integer.parseInt(nameAndID[2]);
	
			}
		}
		return "COM-"+sdf.format(new Date())+"-"+String.format("%05d",ID+1);

	}*/

}