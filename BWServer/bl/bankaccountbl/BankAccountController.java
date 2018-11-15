package bankaccountbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import VO.BankAccountVO;
import enums.ResultMessage;
import service.BankAccountBLService;

/*                                                          
* Module Comments:  		BankAccount������H          
* Author:					161250051/Lai Kin Meng            
* Create Date�G  				2017-10-25 
* Modified By�G  	 			161250051/Lai Kin Meng                                         
* Modified Date:  			
* Why & What is modified�G	
*/ 
public class BankAccountController extends UnicastRemoteObject implements BankAccountBLService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BankAccount bankAccount;
	public BankAccountController() throws RemoteException{
		bankAccount=new BankAccount();
	}

	@Override
	public ResultMessage addBankAccount(BankAccountVO bankAccountVO) throws RemoteException {
		return (bankAccount).addBankAccount(bankAccountVO);
	}

	@Override
	public ResultMessage deleteBankAccount(String ID,String password) throws RemoteException {
		return bankAccount.deleteBankAccount(ID,password);
	}

	@Override
	public BankAccountVO findByCardNumber(String cardNumber) throws RemoteException {
		return bankAccount.findByCardNumber(cardNumber);
	}

	@Override
	public ResultMessage modifyBankAccount(String targetCardNumber, BankAccountVO bankAccountVO)
			throws RemoteException {
		return bankAccount.modifyBankAccount(targetCardNumber, bankAccountVO);
	}

	@Override
	public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException {
		return bankAccount.getBankAccountList();
	}

	@Override
	public BankAccountVO findByID(String targetID) throws RemoteException {
		return bankAccount.findByID(targetID);
	}

	@Override
	public Boolean checkPassword(String ID, String password) throws RemoteException {
		return bankAccount.checkPassword(ID, password);
	}

	@Override
	public BankAccountVO findByName(String name) throws RemoteException {
		return bankAccount.findByName(name);
	}

}
