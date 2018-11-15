package userdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import PO.UserPO;
import enums.JobType;
import enums.ResultMessage;
import idhelper.IDHelper;
import persistence.txt.service.IOStrategy;
import userdataService.UserDataService;

/**
 * 實現UserDataService接口,並提供部分方法的默認實現
 * 檢查參數,詳見isValidIfNotThrowIllegalArgumentException,
 * 參數非法會抛出IllegalArgumentException()
 * 默認所有findByxxx使用getAllList實現,而getAllList為抽象方法交由具體子類實現
 * 
 * @author 161250051
 * @since 2017/12/26
 * @version 2017/12/29
 */
public abstract class UserData implements UserDataService {
	/**
	 * 持久化策略
	 */
	private IOStrategy<UserPO> ioStrategy;

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
	 * 其它類型的參數要求
	 * 
	 * @param obj
	 */
	private void isValidIfNotThrowIllegalArgumentException(Object obj) {
		if (null == obj) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 具體的增加操作
	 * 
	 * @param po
	 * @return
	 */
	protected abstract ResultMessage insertHooked(UserPO po);

	/**
	 * 具體的刪除操作
	 * 
	 * @param targetID
	 * @return
	 */
	protected abstract ResultMessage deleteHooked(String targetID);

	/**
	 * 具體的更新操作
	 * 
	 * @param targetID
	 * @param replacement
	 * @return
	 */
	protected abstract ResultMessage updateHooked(String targetID, UserPO replacement);

	@Override
	public final ResultMessage insert(UserPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		return insertHooked(po);
	}

	@Override
	public ResultMessage delete(String id) {
		isValidIfNotThrowIllegalArgumentException(id);
		return deleteHooked(id);
	}

	@Override
	public ResultMessage update(String targetId, UserPO replacement) {
		isValidIfNotThrowIllegalArgumentException(targetId);
		isValidIfNotThrowIllegalArgumentException(replacement);
		/*
		 * 此處應該檢查targetId == replacements.getID
		 */
		if (!replacement.getId().equals(targetId))
			throw new IllegalArgumentException("目標ID與覆蓋ID不同");
		return updateHooked(targetId, replacement);
	}

	/**
	 * 根據不同的持久化策略有不同的getAllList實現
	 */
	@Override
	public abstract ArrayList<UserPO> getAllList();

	@Override
	public UserPO findByID(String tartgetID) {
		isValidIfNotThrowIllegalArgumentException(tartgetID);
		ArrayList<UserPO> findResult = this.getAllList();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getId().equals(tartgetID))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult.get(0);
	}

	@Override
	public String getIDSuffix(JobType jobType) throws IllegalArgumentException {
		isValidIfNotThrowIllegalArgumentException(jobType);
		Iterator<UserPO> iterator = ioStrategy.outAll(UserPO.class);
		int count = 0;
		while (iterator.hasNext()) {
			if (iterator.next().getJob().equals(jobType))
				count++;
		}
		return IDHelper.toKBitString(count + 1, 5);
	}

	/**
	 * 取得持久化策略
	 * 
	 * @return
	 */
	protected IOStrategy<UserPO> getIoStrategy() {
		return ioStrategy;
	}

	/**
	 * 設定持久化策略
	 * 
	 * @param ioStrategy
	 */
	protected void setIoStrategy(IOStrategy<UserPO> ioStrategy) {
		this.ioStrategy = ioStrategy;
	}

	@Override
	public ArrayList<UserPO> findByUserType(JobType type) {
		ArrayList<UserPO> findResult = getAllList();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getJob().equals(type))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult;
	}

}
