package service;



import java.rmi.Remote;

import java.rmi.RemoteException;

import java.util.ArrayList;

import java.util.Date;

import PO.RecordPO;

import VO.*;

import enums.ResultMessage;



public interface InventoryblService extends Remote{

	ArrayList<RecordVO> showRecord(Date startTime,Date endTime)throws RemoteException;

	ResultMessage addRecord(RecordVO vo)throws RemoteException;

}