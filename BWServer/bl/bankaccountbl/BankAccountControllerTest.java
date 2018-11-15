package bankaccountbl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import VO.BankAccountVO;
import enums.ResultMessage;

public class BankAccountControllerTest {
	private BankAccountController bank;
	@Before
	public void setUp() throws Exception {
		bank=new BankAccountController();
	}

	@Test
	public void testAddBankAccount() throws RemoteException {
		BankAccountVO vo=new BankAccountVO();
		vo.setAmount(23.33);
		vo.setCardNumber("1234567890123456");
		vo.setDate(new Date());
		vo.setName("血汗钱");
		vo.setPassword("666666");
		vo.setId("");
		assertEquals(ResultMessage.BANK_ACCOUNT_ADD_SUCCESS,bank.addBankAccount(vo));
	}
	
	@Test
	public void testModifyBankAccount() throws RemoteException {
		BankAccountVO vo1=bank.findByCardNumber("1234567890123456");
		BankAccountVO vo=new BankAccountVO();
		vo.setAmount(0);
		vo.setCardNumber("1234567890123456");
		vo.setDate(vo.getDate());
		vo.setName("血汗钱emmmm");
		vo.setPassword("666666");
		vo.setId(vo1.getId());
		assertEquals(ResultMessage.BANK_ACCOUNT_ADD_SUCCESS,bank.modifyBankAccount(vo1.getId(),vo));
	}


	@Test
	public void testFindByCardNumber() throws RemoteException {
		BankAccountVO vo1=bank.findByCardNumber("1234567890123456");
		BankAccountVO vo=new BankAccountVO();
		vo.setAmount(0);
		vo.setCardNumber("1234567890123456");
		vo.setDate(null);
		vo.setName("血汗钱emmmm");
		vo.setPassword("666666");
		vo.setId(vo1.getId());
		boolean result=vo1.equals(vo);
		System.out.println(vo1.toPO().toString());
		assertEquals(true,result);
	}

	@Test
	public void testGetBankAccountList() throws RemoteException {
		//ArrayList<BankAccountVO> vos1=new ArrayList<BankAccountVO>();
		//vos1.add(bank.findByCardNumber("1234567890123456"));
		ArrayList<BankAccountVO> vos2=bank.getBankAccountList();
		boolean result=true;
		for(BankAccountVO temp:vos2){
			System.out.println(temp.getName());
		}
		//if(vos1.size()!=vos2.size())
		//	result=false;
		//else if(!vos1.get(0).equals(vos2.get(0)))
		//	result=false;
		//assertEquals(true,result);
	}

	@Test
	public void testFindByID() throws RemoteException {
		String id=bank.findByCardNumber("1234567890123456").getId();
		BankAccountVO vo1=bank.findByID(id);
		BankAccountVO vo=new BankAccountVO();
		vo.setAmount(0);
		vo.setCardNumber("1234567890123456");
		vo.setDate(null);
		vo.setName("血汗钱emmmm");
		vo.setPassword("666666");
		vo.setId(id);
		boolean result=vo1.equals(vo);
		assertEquals(true,result);
	}

	@Test
	public void testCheckPassword() throws RemoteException {
		assertEquals(true,bank.checkPassword(bank.findByCardNumber("1234567890123456").getId(),"666666"));
	}
	
	@Test
	public void testCheckPassword1() throws RemoteException {
		assertEquals(false,bank.checkPassword(bank.findByCardNumber("1234567890123456").getId(),"666662"));
	}

	@Test
	public void testFindByName() throws RemoteException {
		BankAccountVO vo1=bank.findByName("血汗钱emmmm");
		System.out.println(vo1.getName());
		BankAccountVO vo=new BankAccountVO();
		vo.setAmount(0);
		vo.setCardNumber("1234567890123456");
		vo.setDate(null);
		vo.setName("血汗钱emmmm");
		vo.setPassword("666666");
		vo.setId(vo1.getId());
		System.out.println(vo.getPassword()+" "+vo1.getPassword());
		boolean result=vo1.equals(vo);
		assertEquals(true,result);
	}
	
	@Test
	public void testDeleteBankAccount() throws RemoteException {
		String id=bank.findByCardNumber("1234567890123456").getId();
		assertEquals(ResultMessage.BANK_ACCOUNT_DELETE_SUCCESS,bank.deleteBankAccount(id, "666666"));
	}

}
