package commoditydata;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.transform.ResultTransformer;

import PO.CategoryPO;
import PO.CommodityPO;
import commoditybl.Commodity;
import commoditydataService.CommodityDataService;
import date.helper.DateUtils;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;
import persistence.hibernate.service.IODataBaseExtendion;
import sun.launcher.resources.launcher;

public class CommodityDataUseDataBase extends CommodityData {
	public static void main(String[] args) {
		CommodityDataService tester = new CommodityDataUseDataBase();
		tester.getAllList().forEach(po -> System.out.println(po));
	}

	public CommodityDataUseDataBase() {
		setIoStrategy(new IOStrategyUseHibernate<>());
	}

	@Override
	protected ResultMessage insertHooked(CommodityPO po) {
		/*
		 * check if po already exist in database, but it has been deleted
		 * logically
		 */
		((IODataBaseExtendion<CommodityPO>) getIoStrategy()).setSearChCommand("from CommodityPO po where po.id= ?");
		((IODataBaseExtendion<CommodityPO>) getIoStrategy()).setParameter(po.getId());
		CommodityPO findResult = getIoStrategy().outOne(CommodityPO.class);
		if (findResult != null && findResult.getIsDeleted()) {
			po.setIsDeleted(false); // reset
			return update(po.getId(), po);
		}
		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage deleteHooked(String targetID) {
		CommodityPO deleteTarget = findByID(targetID);
		if (deleteTarget == null) {
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		}
		deleteTarget.setIsDeleted(true);
		return update(targetID, deleteTarget);
	}

	@Override
	protected ResultMessage updateHooked(String targetID, CommodityPO replacememt) {
		if (!getIoStrategy().replaceOne(replacememt))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CommodityPO> getAllList() {
		ArrayList<CommodityPO> reCommodityPOs = new ArrayList<>();
		Iterator<CommodityPO> iterator = getIoStrategy().outAll(CommodityPO.class);
		while (iterator.hasNext()) {
			CommodityPO po = iterator.next();
			if (!po.getIsDeleted())
				reCommodityPOs.add(po);
		}
		return reCommodityPOs.isEmpty() ? null : reCommodityPOs;
	}

}