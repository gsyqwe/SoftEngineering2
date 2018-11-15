package runner;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import mainframeui.Log;
import rmi.BankAccountHelper;
import rmi.BusinessConditionHelper;
import rmi.BusinessProcessHelper;
import rmi.CategoryHelper;
import rmi.CheckReceiptHelper;
import rmi.CommodityHelper;
import rmi.EmailHelper;
import rmi.FinancialReceiptHelper;
import rmi.FindReceiptHelper;
import rmi.InitializeAccountHelper;
import rmi.InventoryHelper;
import rmi.InventoryReceiptHelper;
import rmi.MemberHelper;
import rmi.OperationLogHelper;
import rmi.PromotionStrategyHelper;
import rmi.SalesDetailHelper;
import rmi.SalesHelper;
import rmi.UserHelper;

public class client {

	public static void main(String[] args) throws RemoteException {

		client cr = new client();

	}

	private BankAccountHelper bankAccountHelper;

	private BusinessConditionHelper businessConditionHelper;

	private BusinessProcessHelper businessProcessHelper;

	private CategoryHelper categoryHelper;

	private CheckReceiptHelper checkReceiptHelper;

	private CommodityHelper commodityHelper;

	private FinancialReceiptHelper financialReceiptHelper;

	private InitializeAccountHelper initializeAccountHelper;

	private InventoryHelper inventoryHelper;

	private InventoryReceiptHelper inventoryReceiptHelper;

	private MemberHelper memberHelper;

	private OperationLogHelper operationLogHelper;

	private PromotionStrategyHelper promotionStrategyHelper;

	private UserHelper userHelper;

	private EmailHelper emailHelper;
	private SalesHelper salesHelper;

	private FindReceiptHelper findReceiptHelper;

	private SalesDetailHelper salesDetailHelper;
	public client() throws RemoteException {

		linkToServer();

		initGUI();

		// ResultMessage n=commodityHelper.getService().addCommodity(null);

		// ResultMessage m=categoryHelper.getService().addCategory(null);

		// System.out.println(n+"   Success");

		// System.out.println(m+"   success");

	}

	private void linkToServer() {

		try {

			String pre = "rmi://127.0.0.1:8715/";

			bankAccountHelper = BankAccountHelper.getInstance(Naming.lookup(pre
					+ "BankAccountController"));

			 businessConditionHelper =

			BusinessConditionHelper.getInstance(Naming.lookup(pre+"BusinessConditionController"));

			businessProcessHelper = BusinessProcessHelper.getInstance(Naming
					.lookup(pre + "BusinessProcessController"));

			categoryHelper = CategoryHelper.getInstance(Naming.lookup(pre
					+ "CategoryController"));

			checkReceiptHelper = CheckReceiptHelper.getInstance(Naming
					.lookup(pre + "CheckReceiptController"));

			commodityHelper = CommodityHelper.getInstance(Naming.lookup(pre
					+ "CommodityController"));

			financialReceiptHelper = FinancialReceiptHelper.getInstance(Naming
					.lookup(pre + "FinancialReceiptController"));

			initializeAccountHelper = InitializeAccountHelper
					.getInstance(Naming.lookup(pre
							+ "InitializeAccountController"));

			inventoryHelper = InventoryHelper.getInstance(Naming.lookup(pre
					+ "InventoryController"));

			inventoryReceiptHelper = InventoryReceiptHelper.getInstance(Naming
					.lookup(pre + "InventoryReceiptController"));

			memberHelper = MemberHelper.getInstance(Naming.lookup(pre
					+ "MemberController"));

			operationLogHelper = OperationLogHelper.getInstance(Naming
					.lookup(pre + "OperationLogController"));

			promotionStrategyHelper = PromotionStrategyHelper
					.getInstance(Naming.lookup(pre
							+ "PromotionStrategyController"));

			userHelper = UserHelper.getInstance(Naming.lookup(pre
					+ "UserController"));

			emailHelper = EmailHelper.getInstance(Naming.lookup(pre
					+ "EmailController"));

			salesHelper=SalesHelper.getInstance(Naming.lookup(pre
					+ "SalesController"));


			findReceiptHelper = FindReceiptHelper.getInstance(Naming.lookup(pre + "FindReceipt"));
			
			salesDetailHelper=SalesDetailHelper.getInstance(Naming.lookup(pre + "SalesDetailController"));

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (RemoteException e) {

			e.printStackTrace();

		} catch (NotBoundException e) {

			e.printStackTrace();

		}

	}

	private void initGUI() {

		Log.main(null);

	}

}