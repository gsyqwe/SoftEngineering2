package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import enums.ResultMessage;
import VO.BankAccountVO;
/*                                                          
* Module Comments:  瀹氱京瀵︾従璩埗绠＄悊鐣岄潰鎵�闇�鐨勪緵鎺ュ彛                  
* Author:	161250051/Lai Kin Meng            
* Create Date锛�  2017-10-25 
* Modified By锛�   161250051/Lai Kin Meng                                         
* Modified Date:  2017-11-05                                
* Why & What is modified锛歛dd comments                     
*/ 
public interface BankAccountBLService extends Remote{
	
	/* 
	 * 鏂板涓�鍊嬮妧琛岃超鎴�,杩斿洖ResultMessage瑾槑绲愭灉
	 * BankAccountVO鐐哄睍绀哄堡鑸囬倧杓堡鍌抽�掔殑鏁告摎鍖�,鍖呮嫭鍚嶇ū銆佸崱铏熴�侀椤嶅拰瀵嗙⒓
	 */
	public ResultMessage addBankAccount(BankAccountVO bankAccountVO) throws RemoteException;
	
	
	
	/*
	 * 鍒櫎涓�鍊嬮妧琛岃超鎴�,杩斿洖ResultMessage瑾槑绲愭灉
	 * String cardNumber鐐烘兂瑕佸埅闄ょ殑閵�琛岃超鎴剁殑鍗¤櫉
	 */
	public ResultMessage deleteBankAccount(String ID,String passWord)  throws RemoteException;
	
	/*
	 * 鏌ヨ鎸囧畾閵�琛岃超鎴�,杩斿洖ResultMessage瑾槑绲愭灉
	 * String cardNumber鐐烘兂瑕佸埅闄ょ殑閵�琛岃超鎴剁殑鍗¤櫉
	 */

	public BankAccountVO findByCardNumber(String cardNumber)  throws RemoteException;
	
	/*
	 * 淇敼鎸囧畾閵�琛岃超鎴�,杩斿洖ResultMessage瑾槑绲愭灉
	 * String targetId鐐虹洰妯欒超鎴剁殑鍗¤櫉
	 * BankAccountVO鐐哄睍绀哄堡鑸囬倧杓堡鍌抽�掔殑鏁告摎鍖�,鍖呮嫭鍚嶇ū銆佸崱铏熴�侀椤嶅拰瀵嗙⒓
	 */
	public ResultMessage modifyBankAccount(String targetCardNumber, BankAccountVO bankAccountVO)  throws RemoteException;
	
	/*
	 * 鍙栧緱鎵�鏈夐妧琛岃超鎴跺垪琛�
	 * BankAccountVO鐐烘暩鎿氬堡鑸囬倧杓堡鍌抽�掔殑鏁告摎鍖�,鍖呮嫭鍚嶇ū銆佸崱铏熴�侀椤嶅拰瀵嗙⒓,閫欒！鏈冩妸瀵嗙⒓闅辫棌鎺�
	 */
	public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException;

	public BankAccountVO findByID(String targetID)  throws RemoteException;
	
	public BankAccountVO findByName(String name)  throws RemoteException;
	
	public Boolean checkPassword(String ID,String password) throws RemoteException;
}
