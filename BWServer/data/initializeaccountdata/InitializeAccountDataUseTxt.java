package initializeaccountdata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import PO.InitializeAccountPO;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;

public class InitializeAccountDataUseTxt extends InitializeAccountData {
	private static Set<InitializeAccountPO> data = new HashSet<InitializeAccountPO>();
	private static final String txtName = "InitializeAccountPO";

	public InitializeAccountDataUseTxt() {
		setIoStrategy(new IOStrategyUseTxt<>(txtName));
		Iterator<InitializeAccountPO> iterator = (Iterator<InitializeAccountPO>) getIoStrategy()
				.outAll(InitializeAccountPO.class);
		while (iterator.hasNext()) {
			InitializeAccountPO po = iterator.next();
			data.add(po);
		}
	}

	public static String getTxtname() {
		return txtName;
	}

	@Override
	public List<InitializeAccountPO> getAllList() {
		return data.parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected ResultMessage insertHooked(InitializeAccountPO po) {
		data.add(po);
		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

}
