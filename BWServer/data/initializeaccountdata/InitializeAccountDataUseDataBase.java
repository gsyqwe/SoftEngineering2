package initializeaccountdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import PO.InitializeAccountPO;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;

public class InitializeAccountDataUseDataBase extends InitializeAccountData {
	public static void main(String[] args) {
		InitializeAccountDataUseDataBase tester = new InitializeAccountDataUseDataBase();
		tester.getAllList().forEach(po -> System.out.println(po));
	}

	public InitializeAccountDataUseDataBase() {
		setIoStrategy(new IOStrategyUseHibernate<>());
	}

	@Override
	public List<InitializeAccountPO> getAllList() {
		ArrayList<InitializeAccountPO> reInitializeAccountPOs = new ArrayList<>();
		Iterator<InitializeAccountPO> iterator = getIoStrategy().outAll(InitializeAccountPO.class);
		while (iterator.hasNext()) {
			reInitializeAccountPOs.add(iterator.next());
		}
		return reInitializeAccountPOs;

	}

	@Override
	protected ResultMessage insertHooked(InitializeAccountPO po) {
		if (!getIoStrategy().inOne(po))
			return ResultMessage.DATA_OPERATE_FAIL_FILE_CRUSH;
		return ResultMessage.SUCCESS;
	}

}