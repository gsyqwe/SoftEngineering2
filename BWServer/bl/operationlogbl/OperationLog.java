package operationlogbl;

import java.util.ArrayList;
import java.util.Date;
import operationlogdata.OperationLogData;
import operationlogdata.OperationLogDataUseDataBase;
import operationlogdata.OperationLogDataUseTxt;
import operationlogdataService.OperationLogdataService;
import PO.LogPO;
import VO.LogVO;
import enums.ResultMessage;

public class OperationLog {
	OperationLogdataService operationLogDataService;

	public OperationLog() {
		operationLogDataService = (OperationLogdataService) new OperationLogDataUseDataBase();
	}

	public ArrayList<LogVO> getAllLog() {
		ArrayList<LogVO> vo = new ArrayList<LogVO>();
		ArrayList<LogPO> po = operationLogDataService.getAllLog(); // note by 161250051: 檢查是否為空
		for (LogPO temp : po) {
			vo.add(temp.toVO());
		}
		return vo;

	}

	public LogVO findByID(String ID) {
		return operationLogDataService.findLogByID(ID).toVO();
	}

	public ArrayList<LogVO> findByUser(String userID) {
		ArrayList<LogVO> vo = new ArrayList<LogVO>();
		ArrayList<LogPO> po = operationLogDataService.findLogByUser(userID);
		for (LogPO temp : po) {
			vo.add(temp.toVO());
		}
		return vo;
	}

	public ArrayList<LogVO> findByTime(Date startTime, Date endTime) {
		ArrayList<LogVO> vo = new ArrayList<LogVO>();
		ArrayList<LogPO> po = operationLogDataService.findLogByTime(startTime, endTime);
		for (LogPO temp : po) {
			vo.add(temp.toVO());
		}
		return vo;
	}

	public ArrayList<LogVO> findLogByCommetWithKeyword(String keyword) {
		ArrayList<LogVO> vo = new ArrayList<LogVO>();
		ArrayList<LogPO> po = operationLogDataService.findLogByCommetWithKeyword(keyword);
		for (LogPO temp : po) {
			vo.add(temp.toVO());
		}
		return vo;
	}

	public ResultMessage add(LogVO log) {
		String ID = "LOG-" + operationLogDataService.getIDSuffix(new Date());
		log.setId(ID);
		return operationLogDataService.insert(log.toPO());
	}
}
