package categorydata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import PO.CategoryPO;
import PO.FinancialReceiptPO;
import categorydataService.CategorydataService;
import date.helper.DateUtils;
import enums.ResultMessage;
import idhelper.IDHelper;
import persistence.txt.service.IOStrategy;

/**
 * 實現CategoryDataService接口,並提供部分方法的默認實現
 * 檢查參數,詳見isValidIfNotThrowIllegalArgumentException,
 * 參數非法會抛出IllegalArgumentException()
 * 默認所有findByxxx使用getAllList實現,而getAllList為抽象方法交由具體子類實現
 * 
 * @author 161250051
 * @since 2017/12/26
 * @version 2017/12/28
 */
public abstract class CategoryData implements CategorydataService {
	private IOStrategy<CategoryPO> ioStrategy; // 持久化策略

	/**
	 * String類型的參數要求
	 * 
	 * @param str
	 */
	private void isValidIfNotThrowIllegalArgumentException(String str) {
		if (null == str || str.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Object類型的參數要求
	 * 
	 * @param obj
	 */
	private void isValidIfNotThrowIllegalArgumentException(Object obj) {
		if (null == obj) {
			throw new IllegalArgumentException();
		}
	}

	protected void setIoStrategy(IOStrategy<CategoryPO> ioStrategy) {
		this.ioStrategy = ioStrategy;
	}

	protected IOStrategy<CategoryPO> getIoStrategy() {
		return this.ioStrategy;
	}

	/**
	 * 具體增加的操作,由具體子類實現 被insert調用
	 * 
	 * @param po
	 * @return
	 */
	protected abstract ResultMessage insertHooked(CategoryPO po);

	/**
	 * 具體刪除的操作,由具體子類實現 被delete調用
	 * 
	 * @param targetID
	 * @return
	 */
	protected abstract ResultMessage deleteHooked(String targetID);

	/**
	 * 具體更新的操作,由具體子類實現 被update調用
	 * 
	 * @param targetID
	 * @param replacement
	 * @return
	 */
	protected abstract ResultMessage updateHooked(String targetID, CategoryPO replacement);

	/**
	 * 取得全部的數據,不同子類使用不同的方式實現
	 * 
	 * @return
	 */
	protected abstract ArrayList<CategoryPO> getAllList();

	@Override
	public ResultMessage insert(CategoryPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		return insertHooked(po);
	}

	@Override
	public ResultMessage delete(String id) {
		isValidIfNotThrowIllegalArgumentException(id);
		return deleteHooked(id);
	}

	@Override
	public ResultMessage update(String targetID, CategoryPO replacement) {
		isValidIfNotThrowIllegalArgumentException(targetID);
		isValidIfNotThrowIllegalArgumentException(replacement);
		/*
		 * 此處應該檢查targetId == replacements.getID
		 */
		if (!replacement.getId().equals(targetID))
			throw new IllegalArgumentException("目標ID與覆蓋ID不同");
		return updateHooked(targetID, replacement);
	}

	@Override
	public String getMaxID() {
		Iterator<CategoryPO> iterator = ioStrategy.outAll(CategoryPO.class);
		int count = 0;
		while (iterator.hasNext()) {
			count++;
			iterator.next();
		}
		return IDHelper.toKBitString(count + 1, 5);
	}

	@Override
	public CategoryPO findByID(String id) {
		isValidIfNotThrowIllegalArgumentException(id);
		ArrayList<CategoryPO> findResult = this.getAllList();
		if (findResult == null)
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getId().equals(id))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult.get(0);
	}

	@Override
	public CategoryPO findByName(String name) {
		isValidIfNotThrowIllegalArgumentException(name);
		ArrayList<CategoryPO> findResult = this.getAllList();
		if (findResult == null)
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getName().equals(name))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult.get(0);
	}

}
