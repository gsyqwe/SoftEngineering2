package initializeaccountdataService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import PO.InitializeAccountPO;
import enums.ResultMessage;

public interface InitializeAccountDataService {
	public ResultMessage insert(InitializeAccountPO po);

	public ArrayList<InitializeAccountPO> findByTime(Date startTime, Date endTime);

	public InitializeAccountPO findByDate(Date date);

	public List<InitializeAccountPO> getAllList();

}
