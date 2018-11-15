package initializeaccountbl;

import java.util.ArrayList;
import java.util.Date;

import PO.InitializeAccountPO;
import VO.BankAccountVO;
import VO.CommodityVO;
import VO.InitializeAccountInShortVO;
import VO.InitializeAccountVO;
import VO.MemberVO;
import enums.ResultMessage;
import initializeaccountdata.InitializeAccountDataUseDataBase;
import initializeaccountdataService.InitializeAccountDataService;

public class InitializeAccount {
	InitializeAccountDataService initializeAccountDataService = new InitializeAccountDataUseDataBase();
	public InitializeAccount(){
		initializeAccountDataService=new InitializeAccountDataUseDataBase();
	}

	public ResultMessage setUp(InitializeAccountVO vo){
		ArrayList<MemberVO> member=vo.getMemberPOList();
		ArrayList<BankAccountVO> bankAccount=vo.getBankAccountPOList();
		ArrayList<CommodityVO> commodity=vo.getCommodityVOList();
		return initializeAccountDataService.insert(vo.toPO());
	}

	public ArrayList<InitializeAccountInShortVO> getList(){
		ArrayList<InitializeAccountPO> po=(ArrayList)initializeAccountDataService.getAllList();
		ArrayList<InitializeAccountInShortVO> vo=new ArrayList<InitializeAccountInShortVO>();
		for(InitializeAccountPO temp:po){
			vo.add(new InitializeAccountInShortVO(temp.getName(),temp.getDate()));
		}
		return vo;
	}

	public ArrayList<InitializeAccountInShortVO> findByTime(Date startTime,Date endTime){
		ArrayList<InitializeAccountPO> po=initializeAccountDataService.findByTime(startTime, endTime);
		ArrayList<InitializeAccountInShortVO> vo=new ArrayList<InitializeAccountInShortVO>();
		for(InitializeAccountPO temp:po){
			vo.add(new InitializeAccountInShortVO(temp.getName(),temp.getDate()));
		}
		return vo;
	}

	public InitializeAccountVO findByDate(Date date){
		return initializeAccountDataService.findByDate(date).toVO();
	}
}
