package userdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import PO.UserPO;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;

/**
 * 使用txt保存數據, 使用Cache保存數據 若己啟動並沒有在啟動過程中修改Txt,則保證Cache和Txt文件中數據一致
 * 在啟動過程中修改Txt,需要重啟這個類
 * 
 * @author 161250051
 * @since 2017/11/25
 * @version 2017/12/28
 *
 */
public class UserDataUseTxt extends UserData {
	// key為UserID
	private static Map<String, UserPO> data = new HashMap<String, UserPO>();
	private static final String txtName = "UserPO";// Txt的保存名稱,不需要.txt

	public UserDataUseTxt() {
		setIoStrategy(new IOStrategyUseTxt<>(txtName));
		backUpFormTxt();
	}

	private void backUpFormTxt() {
		Iterator<UserPO> iterator = (Iterator<UserPO>) getIoStrategy().outAll(UserPO.class);
		while (iterator.hasNext()) {
			UserPO po = iterator.next();
			data.put(po.getId(), po);
		}
	}

	private Boolean hasThisId(String id) {
		return data.containsKey(id);
	}

	public static String getTxtname() {
		return txtName;
	}

	@Override
	public ArrayList<UserPO> getAllList() {
		return data.values().parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * 因為Cache裡有Key對應UserID
	 */
	@Override
	public UserPO findByID(String tartgetID) {
		if (tartgetID == null)
			throw new IllegalArgumentException();

		if (!hasThisId(tartgetID))
			return null;

		return data.get(tartgetID);
	}

	@Override
	protected ResultMessage insertHooked(UserPO po) {
		if (hasThisId(po.getId()))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		data.put(po.getId(), po);
		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage deleteHooked(String targetID) {
		if (!hasThisId(targetID))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		data.remove(targetID);
		// if data.values().isEmpty, delete all content in file
		if (!getIoStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage updateHooked(String targetID, UserPO replacement) {
		if (!hasThisId(targetID))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		data.replace(targetID, replacement);
		if (!getIoStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

}
