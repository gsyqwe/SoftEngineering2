package service;

import java.rmi.Remote;

import java.rmi.RemoteException;

import java.util.ArrayList;



import VO.*;

import enums.ResultMessage;

public interface CategoryblService extends Remote {

	public ResultMessage addCategory(CategoryVO vo)throws RemoteException;
	public ResultMessage modifyCategory(CategoryVO vo)throws RemoteException;
	public CategoryVO findByName(String name)throws RemoteException;
	public ResultMessage deleteCategory(String id)throws RemoteException;
	public CategoryVO findById(String id)throws RemoteException;
	public ArrayList<String> getSubCategoryList(String categoryID)throws RemoteException;
	public ArrayList<String> getCommodityList(String categoryID)throws RemoteException;

}