package runner;

import java.rmi.RemoteException;
import rmi.RemoteHelper;

public class ServerRunner {
	
	public ServerRunner() throws RemoteException {
		new RemoteHelper();
	}
	
	public static void main(String[] args) throws RemoteException {
		new ServerRunner();
		System.out.println("Server is running!");
	}
}
