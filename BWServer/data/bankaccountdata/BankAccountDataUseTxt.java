package bankaccountdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import PO.BankAccountPO;
import date.helper.DateUtils;
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
public class BankAccountDataUseTxt extends BankAccountData {
	// key為銀行賬戶ID
	private static Map<String, BankAccountPO> data = new HashMap<String, BankAccountPO>();
	private static final String txtName = "BankAccountPO"; // Txt的保存名稱,不需要.txt

	public static String getTxtName() {
		return txtName;
	}

	public BankAccountDataUseTxt() {
		super.setIOStrategy(new IOStrategyUseTxt<>(txtName));
		backUpFromFile();
	}

	@Override
	public ArrayList<BankAccountPO> getAllList() {
		return data.values().parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * 從txt中讀取所有數據
	 */
	private void backUpFromFile() {
		Date now = new Date();
		Iterator<BankAccountPO> iterator = (Iterator<BankAccountPO>) super.getIOStrategy().outAll(BankAccountPO.class);
		while (iterator.hasNext()) {
			BankAccountPO po = iterator.next();
			if (DateUtils.isSameYear(now, po.getDate()))
				data.put(po.getId(), po);
		}
	}

	private Boolean hasThisId(String id) {
		return data.containsKey(id);
	}

	/**
	 * 若在Cache中存在,則在Cache中刪除,並將整個Cache覆寫txt
	 */
	@Override
	protected ResultMessage deleteHooked(String targetID) {
		if (!hasThisId(targetID))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		data.remove(targetID);
		if (!super.getIOStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	/**
	 * 若在Cache中存在,則返回ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED
	 * 反之,在Cache中增加,並txt追加一條數據
	 */
	@Override
	protected ResultMessage insertHooked(BankAccountPO po) {
		if (hasThisId(po.getId()))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		data.put(po.getId(), po);
		if (!super.getIOStrategy().inOne(po)) // write to file
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;

	}

	/**
	 * 若Cache中存在,則返回之 若不存在,則表示沒有匹配
	 */
	@Override
	public BankAccountPO findByID(String targetID) {
		if (targetID == null)
			throw new IllegalArgumentException();
		if (!hasThisId(targetID))
			return null;
		return data.get(targetID);
	}

	/**
	 * 若在Cache存在,則使用replacement覆蓋之,並使用Cache覆寫Txt文件 若不存在,則表示沒有匹配
	 */
	@Override
	protected ResultMessage updateHooked(String targetId, BankAccountPO replacement) {
		if (!hasThisId(targetId))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		data.replace(targetId, replacement);
		if (!super.getIOStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

}