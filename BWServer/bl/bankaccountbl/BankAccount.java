package bankaccountbl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import PO.BankAccountPO;
import VO.BankAccountVO;
import bankaccountdata.BankAccountDataUseDataBase;
import bankaccountdataService.BankAccountDataService;
import enums.ResultMessage;

public class BankAccount{
	BankAccountDataService bankAccountDataService;
	public BankAccount(){
		bankAccountDataService=new BankAccountDataUseDataBase();
	}
	public BankAccountVO findByName(String name){
		BankAccountVO vo=new BankAccountVO();
		BankAccountPO po=bankAccountDataService.findByName(name);
		vo.setAmount(po.getAmount());
		vo.setCardNumber(po.getCardNumber());
		vo.setId(po.getId());
		vo.setName(po.getName());
		return vo;
	}
	public ResultMessage addBankAccount(BankAccountVO bankAccountVO){
		if(bankAccountVO.getAmount()<0){
			return ResultMessage.BANK_ACCOUNT_ADD_FAIL_NEGATIVE_AMOUNT;
		}
		String cardNumber=bankAccountVO.getCardNumber();
		for(int i=0;i<cardNumber.length();i++){
			if('9'<cardNumber.charAt(i) || cardNumber.charAt(i)<'0' ||cardNumber.length()<16 || cardNumber.length()>21){
				return ResultMessage.BANK_ACCOUNT_ADD_FAIL_ILLEGAL_CARD_NUMBER;
			}
		}
		String password=bankAccountVO.getPassword();
		for(int i=0;i<password.length();i++){
			if('9'<password.charAt(i) || password.charAt(i)<'0' || password.length()!=6){
				return ResultMessage.BANK_ACCOUNT_ADD_FAIL_ILLEGAL_PASSWORD;
			}
		}
		BankAccountPO po=bankAccountDataService.findByCardNumber(bankAccountVO.getCardNumber());
		if(po!=null)
			return ResultMessage.BANK_ACCOUNT_ADD_FAIL_CARD_NUMBER_REPEATED;
		po=bankAccountDataService.findByName(bankAccountVO.getName());
		if(po!=null)
			return ResultMessage.BANK_ACCOUNT_ADD_FAIL_NAME_REPEATED;
		Date date=new Date();
		String id=bankAccountDataService.getIDSuffix(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String ID="BAT-"+format.format(date)+"-"+id;
		bankAccountVO.setId(ID);
		return bankAccountDataService.insert(bankAccountVO.toPO());
	}

	public ResultMessage deleteBankAccount(String ID,String password){
		BankAccountPO bank=bankAccountDataService.findByID(ID);
		if(!password.equals(bank.getPassword()))
			return ResultMessage.BANK_ACCOUNT_DELETE_FAIL_PASSWORD_WRONG;
		double amount=bank.getAmount();
		if(amount!=0)
			return ResultMessage.BANK_ACCOUNT_DELETE_FAIL_AMOUNT;
		return bankAccountDataService.delete(ID);
	}


	public BankAccountVO findByCardNumber(String cardNumber){
		return bankAccountDataService.findByCardNumber(cardNumber).toVO();
	}


	public ResultMessage modifyBankAccount(String targetCardNumber, BankAccountVO bankAccountVO)
			throws RemoteException {
		if(bankAccountVO.getAmount()<0)
			return ResultMessage.BANK_ACCOUNT_UPDATE_FAIL_NEGATIVE_AMOUNT;
		return bankAccountDataService.update(targetCardNumber, bankAccountVO.toPO());
	}


	public ArrayList<BankAccountVO> getBankAccountList(){
		ArrayList<BankAccountVO> vo=new ArrayList<BankAccountVO>();
		ArrayList<BankAccountPO> po=bankAccountDataService.getAllList();
		for(BankAccountPO i:po)
			vo.add(i.toVO());
		return vo;
	}


	public BankAccountVO findByID(String targetID){
		return bankAccountDataService.findByID(targetID).toVO();
	}

	public Boolean checkPassword(String ID,String passWord){
		BankAccountPO po=bankAccountDataService.findByID(ID);
		if(po.getPassword().equals(passWord))
			return true;
		else
			return false;
	}


}
