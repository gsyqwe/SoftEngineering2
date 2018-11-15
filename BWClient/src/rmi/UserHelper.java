package rmi;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import service.UserblService;

public class UserHelper {
	private Remote remote;
	private static UserHelper userHelper;
	public static UserHelper getInstance(){
		if(userHelper==null)
			userHelper=new UserHelper();
		return userHelper;
	}
	public static UserHelper getInstance(Remote r){
		if(userHelper==null){
			userHelper=new UserHelper(r);
		}
		return userHelper;
	}
	
	private  UserHelper(){
	}
	private UserHelper(Remote r){
		this.remote=r;
	}
	
	public void setRemote(Remote r){
		this.remote=r;
	}
	
	public UserblService getService(){
		return (UserblService) remote;
	}
}
