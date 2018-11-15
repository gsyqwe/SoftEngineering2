package memberbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.FindMemberService;

public class FindMemberFactory extends UnicastRemoteObject implements FindMemberService{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public FindMemberFactory() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public FindMemberByID getFindMemberByID(){
		return new FindMemberByID();
	}

	@Override
	public FindMemberByLevel getFindMemberByLevel(){
		return new FindMemberByLevel();
	}

	@Override
	public FindMemberByName getFindMemberByName(){
		return new FindMemberByName();
	}

	@Override
	public FindMemberByPostcode getFindMemberByPostcode(){
		return new FindMemberByPostcode();
	}

	@Override
	public FindMemberBySalesman getFindMemberBySalesman(){
		return new FindMemberBySalesman();
	}

	@Override
	public FindMemberByType getFindMemberByType(){
		return new FindMemberByType();
	}

}
