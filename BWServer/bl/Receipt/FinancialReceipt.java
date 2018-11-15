package Receipt;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import PO.BankAccountPO;
import PO.FinancialReceiptPO;
import PO.LineItemPO;
import PO.MemberPO;
import PO.ReceiptPO;
import VO.EmailForAuditVO;
import VO.FinancialReceiptVO;
import VO.LineItemVO;
import VO.ReceiptVO;
import bankaccountdata.BankAccountDataUseDataBase;
import bankaccountdataService.BankAccountDataService;
import emailbl.EmailForServer;
import enums.FinancialReceiptType;
import enums.ReceiptState;
import enums.ResultMessage;
import financialreceiptdata.FinancialReceiptDataUseDataBase;
import financialreceiptdataservice.FinancialReceiptDataService;
import memberdata.MemberDataUseDataBase;
import memberdataservice.MemberDataService;
import service.ReceiptWorks;

/**
 *
 * @author 82646
 */

public class FinancialReceipt extends Receipt implements ReceiptWorks{

	FinancialReceiptPO receipt;
	FinancialReceiptDataService dataService = new FinancialReceiptDataUseDataBase();
	MemberDataService memberDataService = new MemberDataUseDataBase();
	BankAccountDataService accountDataService = new BankAccountDataUseDataBase();

	public FinancialReceipt(FinancialReceiptPO receipt){
		this.receipt = receipt;
		dataService = new FinancialReceiptDataUseDataBase();
		memberDataService = new MemberDataUseDataBase();
		accountDataService = new BankAccountDataUseDataBase();
	}

	@Override
	public ReceiptVO prepareToShow() throws RemoteException{
		// TODO Auto-generated method stub

		return this.receipt.toVO();
	}

	@Override//发现逻辑上的问题，在一件商品出入库或一个账户减钱出现为负的情况的时候，要将之前的更新取消，否则会出大乱子
	public ResultMessage passAudit(String managerID) throws RemoteException{
		// TODO Auto-generated method stub
		boolean isRich = false;//判断是向账户里面加钱还是减钱
		ResultMessage memberUpdateMessage = null;
		MemberPO member = null;
		if(receipt.getFinancialReceiptType() == FinancialReceiptType.DEBIT_NOTE){//收款单,更改客户的应付
			isRich = true;
			member = memberDataService.findByID(receipt.getMemberID());
			member.setPayment(member.getPayment() - receipt.getSum());
		}
		else if(receipt.getFinancialReceiptType() == FinancialReceiptType.BILL){//付款单，更改客户的应收
			isRich = false;
			member = memberDataService.findByID(receipt.getMemberID());
			member.setReceivable(member.getReceivable() - receipt.getSum());
		}
		else{//现金费用单，不改变客户的应收应付，直接在后面改变银行账户的金额
			isRich = false;
		}

		//修改银行账户的金额,如果有出现账号的余额为负，就返回错误信息并将之前的操作撤销
		ArrayList<ResultMessage> accountResult = new ArrayList<>();
		ArrayList<BankAccountPO> accountList = new ArrayList<>();
		for(LineItemPO item:receipt.getLineItemAsList()){
			BankAccountPO account = accountDataService.findByID(item.getId());//我猜测应该是根据ID访问数据库和修改银行卡信息
			if(isRich){
				account.setAmount(account.getAmount() + item.getPrice());
			}
			else{
				account.setAmount(account.getAmount() - item.getPrice());
			}

			accountList.add(account);
		}

		//不会出现账户为负的问题了
		for(BankAccountPO account:accountList){
			if(account.getAmount() < 0){
				System.out.println("negetive");
				return ResultMessage.BANK_ACCOUNT_UPDATE_FAIL_NEGATIVE_AMOUNT;
			}
		}

		for(BankAccountPO account:accountList){
		accountResult.add(accountDataService.update(account.getId(), account));
		}

		for(ResultMessage message:accountResult){
			if(message == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND){
				return ResultMessage.BANK_ACCOUNT_UPDATE_FAIL_CARD_NUMER_DISMATCH;//如果在正常流程里面不会出现这个问题
			}
		}

		if(receipt.getFinancialReceiptType() != FinancialReceiptType.CASH_CLAIM){
			memberUpdateMessage = memberDataService.update(receipt.getMemberID(), member);
			if(memberUpdateMessage == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND){
				return ResultMessage.MEMBER_UPDATE_FAULT;//正常流程不会出现这个问题
			}
		}

		receipt.setState(ReceiptState.VERIFIED);
		ResultMessage updateMessage = dataService.update(receipt.getId(), receipt);

		if(updateMessage == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND){
			return ResultMessage.RECEIPT_UPDATE_FAULT;//正常流程不会出现该问题
		}

		//发送邮件
		EmailForServer controller = new EmailForServer();
		EmailForAuditVO email = EmailForAuditVO.generateEmail(managerID, receipt.getOperatorID(), new Date(), receipt.getId(), true);
		ResultMessage emailMessage = controller.saveEmail(email);//保存email的data操作的message
		if(emailMessage != ResultMessage.SUCCESS){
			return emailMessage;
		}

		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage failPassing(String managerID) throws RemoteException{
		// TODO Auto-generated method stub
		receipt.setState(ReceiptState.UNAPPROVEAL);
		ResultMessage message = dataService.update(receipt.getId(), receipt);
		if(message == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND){
			return ResultMessage.RECEIPT_UPDATE_FAULT;
		}

		//发送邮件
		EmailForServer controller = new EmailForServer();
		EmailForAuditVO email = EmailForAuditVO.generateEmail(managerID, receipt.getOperatorID(), new Date(), receipt.getId(), false);
		ResultMessage emailMessage = controller.saveEmail(email);
		if(emailMessage != ResultMessage.SUCCESS){
			return emailMessage;
		}

		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage setReceiptPO(ReceiptVO newReceipt,String managerID) throws RemoteException{//先重新计算总额,别忘了在界面上更新总额；先更新然后直接调用passAudit方法
		double sum = 0;
		for(LineItemVO item:newReceipt.getLineItem()){
			sum = sum + item.getPrice();
		}

		((FinancialReceiptVO) newReceipt).setSum(sum);
		this.receipt = ((FinancialReceiptVO) newReceipt).toPO();
		return this.passAudit(managerID);
		// TODO Auto-generated method stub

	}

	@Override
	public ReceiptPO getReceiptPO() throws RemoteException{
		// TODO Auto-generated method stub
		return receipt;
	}

}
