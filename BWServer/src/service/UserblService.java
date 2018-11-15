package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.UserVO;
import enums.JobType;
import enums.ResultMessage;

public interface UserblService extends Remote{
       public ResultMessage Log(String ID,String Password)throws RemoteException;
       public UserVO findByID(String ID)throws RemoteException;
       public ArrayList<UserVO> getAllList()throws RemoteException;
       public ResultMessage addUser(UserVO user)throws RemoteException;
       public ResultMessage deleteUser(String ID)throws RemoteException;
       public ResultMessage modifyUser(UserVO user)throws RemoteException;
       public String getMaxID(JobType job)throws RemoteException;
}
