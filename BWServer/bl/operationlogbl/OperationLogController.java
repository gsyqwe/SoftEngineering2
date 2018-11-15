package operationlogbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import VO.LogVO;
import enums.ResultMessage;
import service.OperationLogblService;

public class OperationLogController extends UnicastRemoteObject implements OperationLogblService {
	/**
	 * @throws RemoteException
	 * 
	 */
	/*
	 * public static void main(String[] args) throws RemoteException{
	 * OperationLogController o=new OperationLogController(); LogVO vo=new
	 * LogVO(); vo.setId(""); vo.setTime(new Date()); vo.setUserID("00000");
	 * String content="跟踪许墨撩妹"; vo.setContent(content);
	 * System.out.println(o.add(vo)); //System.out.println(o.add(vo)); }
	 */
	private static final long serialVersionUID = 1L;
	OperationLog operationLog;

	public OperationLogController() throws RemoteException {
		operationLog = new OperationLog();
	}

	@Override
	public ArrayList<LogVO> getAllLog() throws RemoteException {
		return operationLog.getAllLog();
	}

	@Override
	public LogVO findByID(String ID) throws RemoteException {
		return operationLog.findByID(ID);
	}

	@Override
	public ArrayList<LogVO> findByUser(String userID) throws RemoteException {
		return operationLog.findByUser(userID);
	}

	@Override
	public ArrayList<LogVO> findByTime(Date startTime, Date endTime) throws RemoteException {
		return operationLog.findByTime(startTime, endTime);
	}

	@Override
	public ArrayList<LogVO> findLogByCommetWithKeyword(String keyword) throws RemoteException {
		return operationLog.findLogByCommetWithKeyword(keyword);
	}

	@Override
	public ResultMessage add(LogVO log) throws RemoteException {
		return operationLog.add(log);
	}

}
