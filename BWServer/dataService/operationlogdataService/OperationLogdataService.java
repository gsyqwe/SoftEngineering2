package operationlogdataService;

import java.util.ArrayList;
import java.util.Date;

import PO.LogPO;
import enums.ResultMessage;

public interface OperationLogdataService {
	/**
	 * 
	 * @param po
	 * @return
	 */
	public ResultMessage insert(LogPO po);

	/**
	 * 
	 * @return
	 */
	public ArrayList<LogPO> getAllLog();

	/**
	 * 
	 * @param id
	 * @return
	 */
	public LogPO findLogByID(String id);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList<LogPO> findLogByUser(String userId);

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public ArrayList<LogPO> findLogByCommetWithKeyword(String keyword);

	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public ArrayList<LogPO> findLogByTime(Date startTime, Date endTime);

	public String getIDSuffix(Date date) throws IllegalArgumentException;

}
