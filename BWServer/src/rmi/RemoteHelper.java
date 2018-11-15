package rmi;

import initializeaccountbl.InitializeAccountController;
import inventorybl.InventoryController;
import inventoryreceiptbl.InventoryReceiptController;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import memberbl.MemberController;
import operationlogbl.OperationLogController;
import receiptFinder.FindReceipt;
import salesbl.SalesController;
import salesdetailbl.SalesDetailController;
import userbl.UserController;
import PromotionStrategybl.PromotionStrategyController;
import bankaccountbl.BankAccountController;
import businessconditionbl.BusinessConditionController;
import businessprocessbl.BusinessProcessController;
import categorybl.CategoryController;
import checkreportbl.CheckReceiptController;
import commoditybl.CommodityController;
import emailbl.EmailBLController;
import financialreceipt.FinancialReceiptController;

public class RemoteHelper {
	public RemoteHelper() throws RemoteException{
		initServer();
	}

	public void initServer()throws RemoteException{
		BankAccountController bankAccount;
		BusinessConditionController businessCondition;
		BusinessProcessController businessProcess;
		CategoryController category;
		CommodityController commodity;
		FinancialReceiptController financialReceipt;
		InitializeAccountController initializeAccount;
		InventoryController inventory;
		InventoryReceiptController inventoryReceipt;
		MemberController member;
		OperationLogController operationLog;
		PromotionStrategyController promotionStrategy;
		SalesController sales;
		UserController user;
		CheckReceiptController checkReceipt;
		EmailBLController email;
		FindReceipt find;
		SalesDetailController salesDetail;

		try {
			bankAccount=new BankAccountController();
			businessCondition=new BusinessConditionController();
			businessProcess=new BusinessProcessController();
			commodity = new CommodityController();
			category=new CategoryController();
			financialReceipt=new FinancialReceiptController();
			initializeAccount=new InitializeAccountController();
			inventory=new InventoryController();
			inventoryReceipt=new InventoryReceiptController();
			member=new MemberController();
			operationLog=new OperationLogController();
			promotionStrategy=new PromotionStrategyController();
			sales=new SalesController();
			user=new UserController();
			checkReceipt=new CheckReceiptController();
			email = new EmailBLController();
			find = new FindReceipt();
			salesDetail=new SalesDetailController();
			

			LocateRegistry.createRegistry(8715);
			String pre="rmi://127.0.0.1:8715/";
			Naming.bind(pre+"CommodityController",commodity);
			Naming.bind(pre+"CategoryController", category);
			Naming.bind(pre+"BusinessConditionController", businessCondition);
			Naming.bind(pre+"BusinessProcessController",businessProcess);
			Naming.bind(pre+"BankAccountController", bankAccount);
			Naming.bind(pre+"FinancialReceiptController", financialReceipt);
			Naming.bind(pre+"InitializeAccountController", initializeAccount);
			Naming.bind(pre+"InventoryController", inventory);
			Naming.bind(pre+"InventoryReceiptController", inventoryReceipt);
			Naming.bind(pre+"MemberController",member);
			Naming.bind(pre+"OperationLogController", operationLog);
			Naming.bind(pre+"PromotionStrategyController", promotionStrategy);
			Naming.bind(pre+"SalesController", sales);
			Naming.bind(pre+"UserController",user);
			Naming.bind(pre+"CheckReceiptController",checkReceipt);
			Naming.bind(pre + "EmailController", email);
			Naming.bind(pre + "FindReceipt", find);
			Naming.bind(pre + "SalesDetailController", salesDetail);

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}

	}
}
