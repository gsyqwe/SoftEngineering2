package categorydata;

import java.util.ArrayList;
import java.util.Iterator;

import PO.BankAccountPO;
import PO.CategoryPO;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;
import persistence.hibernate.service.IODataBaseExtendion;

public class CategoryDataUseDataBase extends CategoryData {
	public static void main(String[] args) {
		CategoryDataUseDataBase tester = new CategoryDataUseDataBase();
		tester.getAllList().forEach(po -> System.out.println(po));
	}

	public CategoryDataUseDataBase() {
		setIoStrategy(new IOStrategyUseHibernate<>());
	}

	@Override
	protected ResultMessage insertHooked(CategoryPO po) {
		/*
		 * check if po already exist in database, but it has been deleted
		 * logically
		 */
		((IODataBaseExtendion<CategoryPO>) getIoStrategy()).setSearChCommand("from CategoryPO po where po.id= ?");
		((IODataBaseExtendion<CategoryPO>) getIoStrategy()).setParameter(po.getId());
		CategoryPO findResult = getIoStrategy().outOne(CategoryPO.class);
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
		CategoryPO deleteTarget = findByID(targetID);
		if (deleteTarget == null)
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		deleteTarget.setIsDeleted(true);
		return update(targetID, deleteTarget);
	}

	@Override
	protected ResultMessage updateHooked(String targetID, CategoryPO replacement) {
		if (!getIoStrategy().replaceOne(replacement)) {
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		}
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ArrayList<CategoryPO> getAllList() {
		ArrayList<CategoryPO> reCategoryPOs = new ArrayList<>();
		Iterator<CategoryPO> iterator = getIoStrategy().outAll(CategoryPO.class);
		while (iterator.hasNext()) {
			CategoryPO po = iterator.next();
			if (!po.getIsDeleted())
				reCategoryPOs.add(po);
		}
		return reCategoryPOs.isEmpty() ? null : reCategoryPOs;
	}

}