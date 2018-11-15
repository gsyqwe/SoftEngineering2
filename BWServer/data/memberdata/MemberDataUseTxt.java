package memberdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import PO.MemberPO;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;

public class MemberDataUseTxt extends MemberData {
	private static Map<String, MemberPO> data = new HashMap<String, MemberPO>();
	private static final String txtName = "MemberPO";

	public MemberDataUseTxt() {
		setIoStrategy(new IOStrategyUseTxt<>(txtName));
		backUpFormTxt();
	}

	private void backUpFormTxt() {
		Iterator<MemberPO> iterator = (Iterator<MemberPO>) getIoStrategy().outAll(MemberPO.class);
		while (iterator.hasNext()) {
			MemberPO po = iterator.next();
			data.put(po.getId(), po);
		}
	}

	protected static String getTxtname() {
		return txtName;
	}

	@Override
	protected ResultMessage deleteHooked(String memberID) {
		if (!hasThisId(memberID))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		data.remove(memberID);
		if (!getIoStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage updateHooked(String memberID, MemberPO replacement) {
		if (!hasThisId(memberID))
			return ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND;
		data.replace(memberID, replacement);
		if (!getIoStrategy().replaceAll(data.values().iterator()))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	protected ResultMessage insertHooked(MemberPO po) {
		if (hasThisId(po.getId()))
			return ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED;
		data.put(po.getId(), po);
		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<MemberPO> getAll() {
		return data.values().parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public MemberPO findByID(String id) {
		if (id == null)
			throw new IllegalArgumentException();
		if (!hasThisId(id))
			return null;
		return data.get(id);
	}

	private Boolean hasThisId(String id) {
		return data.containsKey(id);
	}
}
