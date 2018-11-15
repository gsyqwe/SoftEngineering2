package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import memberbl.FindMemberByID;
import memberbl.FindMemberByLevel;
import memberbl.FindMemberByName;
import memberbl.FindMemberByPostcode;
import memberbl.FindMemberBySalesman;
import memberbl.FindMemberByType;

public interface FindMemberService extends Remote{
	public FindMemberByID getFindMemberByID() throws RemoteException;

	public FindMemberByLevel getFindMemberByLevel() throws RemoteException;

	public FindMemberByName getFindMemberByName() throws RemoteException;

	public FindMemberByPostcode getFindMemberByPostcode() throws RemoteException;

	public FindMemberBySalesman getFindMemberBySalesman() throws RemoteException;

	public FindMemberByType getFindMemberByType() throws RemoteException;
}
