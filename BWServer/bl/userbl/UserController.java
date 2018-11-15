package userbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import VO.UserVO;
import enums.JobType;
import enums.ResultMessage;
import service.UserblService;

public class UserController extends UnicastRemoteObject implements UserblService{
	/**
	 */
	/*public static void main(String[] arg) throws RemoteException{
		UserController u=new UserController();
		ArrayList<UserVO> po=u.getAllList();
		for(int i=0;i<po.size();i++){
			System.out.println(po.get(i).getName());
		}
		UserVO vo=new UserVO("","112233",JobType.getEnumByValue("管理员"),"许墨");
		//System.out.println(u.addUser(vo));
		//System.out.println(u.deleteUser("MAN-00001"));
		//System.out.println(u.deleteUser("MAN-00002"));
	}*/
	private static final long serialVersionUID = 1L;
	User user;
	public UserController() throws RemoteException{
		user=new User();
	}
	@Override
	public ResultMessage Log(String ID, String Password) throws RemoteException {
		return user.Log(ID, Password);
	}
	@Override
	public ArrayList<UserVO> getAllList() throws RemoteException {
		return user.getAllList();
	}
	@Override
	public ResultMessage addUser(UserVO vo) throws RemoteException {
		return user.addUser(vo);
	}
	@Override
	public ResultMessage deleteUser(String ID) throws RemoteException {
		return user.deleteUser(ID);
	}
	@Override
	public ResultMessage modifyUser(UserVO vo) throws RemoteException {
		return user.modifyUser(vo);
	}
	@Override
	public UserVO findByID(String ID) throws RemoteException {
		return user.findByID(ID);
	}
	@Override
	public String getMaxID(JobType job) throws RemoteException {
		return user.getMaxId(job);
	}

}
