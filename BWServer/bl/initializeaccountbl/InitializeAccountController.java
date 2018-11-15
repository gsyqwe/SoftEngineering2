package initializeaccountbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import VO.BankAccountVO;
import VO.CommodityVO;
import VO.InitializeAccountInShortVO;
import VO.InitializeAccountVO;
import VO.MemberVO;
import bankaccountbl.BankAccountController;
import commoditybl.CommodityController;
import enums.ResultMessage;
import memberbl.MemberController;
import service.BankAccountBLService;
import service.CommodityblService;
import service.InitializeAccountBLService;
import service.MemberblService;
/*                                                          
* Module Comments:  		InitializeAccout������H          
* Author:					161250051/Lai Kin Meng            
* Create Date�G  				2017-10-25 
* Modified By�G  	 			161250051/Lai Kin Meng                                         
* Modified Date:  			
* Why & What is modified�G	
*/ 
public class InitializeAccountController extends UnicastRemoteObject implements InitializeAccountBLService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InitializeAccount initializeAccount;
	public InitializeAccountController() throws RemoteException{
		initializeAccount=new InitializeAccount();
	}
	@Override
	public InitializeAccountVO getRemainingData() throws RemoteException {
		CommodityblService commodity=new CommodityController();
		ArrayList<CommodityVO> commodities=commodity.showCommodityList();
		MemberblService member=new MemberController();
		ArrayList<MemberVO> members=member.getMemberList();
		BankAccountBLService bankAccount=new BankAccountController();
		ArrayList<BankAccountVO> bankAccounts=bankAccount.getBankAccountList();
		InitializeAccountVO vo=new InitializeAccountVO();
		vo.setBankAccountVOList(bankAccounts);
		vo.setCommodityVOList(commodities);
		vo.setMemberPOList(members);
		return vo;
	}

	@Override
	public ResultMessage setUp(InitializeAccountVO vo) throws RemoteException {
		return initializeAccount.setUp(vo);
	}

	@Override
	public ArrayList<InitializeAccountInShortVO> getList() throws RemoteException {
		return initializeAccount.getList();
	}

	@Override
	public InitializeAccountVO findByDate(Date date) throws RemoteException {
		return initializeAccount.findByDate(date);
	}
	@Override
	public ArrayList<InitializeAccountInShortVO> findByTime(Date startTime, Date endTime) throws RemoteException {
		return initializeAccount.findByTime(startTime, endTime);
	}

}
