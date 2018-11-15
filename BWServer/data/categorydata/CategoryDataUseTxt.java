package categorydata;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import PO.CategoryPO;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;


/**
 * 使用txt保存數據, 使用Cache保存數據
 * 若己啟動並沒有在啟動過程中修改Txt,則保證Cache和Txt文件中數據一致
 * 在啟動過程中修改Txt,需要重啟這個類
 * @author 161250051
 * @since 2017/11/25
 * @version 2017/12/28
 *
 */
public class CategoryDataUseTxt extends CategoryData {
	// key 為Categroy的ID
	private static Map<String, CategoryPO> data = new HashMap<>();
	private static final String txtName = "CategoryPO"; // 文件的名稱,不需要.txt

	public CategoryDataUseTxt() {
		setIoStrategy(new IOStrategyUseTxt<>(txtName));
		backUpFormFile();
	}

	public static String getTxtname() {
		return txtName;
	}

	/**
	 * 從Txt讀取所有數據
	 */
	private void backUpFormFile() {
		Iterator<CategoryPO> iterator = getIoStrategy().outAll(CategoryPO.class);
		while (iterator.hasNext()) {
			CategoryPO po = iterator.next();
			data.put(po.getId(), po);
		}
	}

	/**
	 * 若在Cache中存在,則返回ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED
	 * 反之,在Cache中增加,並txt追加一條數據
	 */
	@Override
	protected ResultMessage insertHooked(CategoryPO po) {
		if (data.containsKey(po.getId()))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		data.put(po.getId(), po);
		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage deleteHooked(String targetID) {
		if (!data.containsKey(targetID))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		data.remove(targetID);
		if (!getIoStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage updateHooked(String targetID, CategoryPO replacement) {
		if (!data.containsKey(targetID))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		data.replace(targetID, replacement);
		if (!getIoStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ArrayList<CategoryPO> getAllList() {
		return data.values().parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public CategoryPO findByID(String id) {
		if (id == null)
			throw new IllegalArgumentException();
		if (!data.containsKey(id))
			return null;
		return data.get(id);
	}

}
