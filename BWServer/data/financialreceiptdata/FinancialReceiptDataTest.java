package financialreceiptdata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import PO.FinancialReceiptPO;
import PO.UserPO;
import enums.FinancialReceiptType;
import enums.ReceiptState;
import financialreceiptdataservice.FinancialReceiptDataService;

public class FinancialReceiptDataTest {
	FinancialReceiptDataService test = new FinancialReceiptDataUseDataBase();
	List<FinancialReceiptPO> allPO = new ArrayList<>();

	// @Before
	// public void beforeTest() {
	// allPO = test.getAllList();
	// System.out.println("數據庫裡有");
	// allPO.forEach(po -> System.out.println(po));
	// }

	// @After
	// public void afterTest() {
	// allPO.forEach(po -> test.insert(po));
	// }

	@Test
	public void testInsert() {
		// assertEquals(ResultMessage.BANK_ACCOUNT_ADD_SUCCESS,test.insert(FinancialReceiptPO.SAMPLE_1.setId();));
	}

	@Test
	public void testDelete() {
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllList() {
		allPO = test.getAllList();
		System.out.println("數據庫裡有");
		allPO.forEach(po -> System.out.println(po));
	}

	@Test
	public void testFindByID() {
		ArrayList<FinancialReceiptPO> list = test.getAllList();
		assertEquals(0, test.getIDSuffix(list.get(0).getDate(), list.get(0).getFinancialReceiptType()));

	}

	@Test
	public void testFindByUser() {
		ArrayList<FinancialReceiptPO> list = test.findByUser(UserPO.SAMPLE_1.getId());
		list.forEach(po -> System.out.println(po));
	}

	@Test
	public void testFindByFinancialReceipType() {
		ArrayList<FinancialReceiptPO> list = test.findByFinancialReceipType(FinancialReceiptType.CASH_CLAIM);
		list.forEach(po -> System.out.println(po));
	}

	@Test
	public void testFindByDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByReceiptState() {
		ArrayList<FinancialReceiptPO> list = test.getAllList();
		list.parallelStream().filter(po -> po.getState().equals(ReceiptState.SUBMITTED))
				.forEach(po -> System.out.println(po));
		test.findByReceiptState(ReceiptState.SUBMITTED).forEach(po -> System.out.println(po));

	}

	@Test
	public void testGetIDSuffix() {
		ArrayList<FinancialReceiptPO> list = test.getAllList();
		assertEquals(2, test.getIDSuffix(list.get(2).getDate(), list.get(2).getFinancialReceiptType()));
		assertEquals(1, test.getIDSuffix(list.get(0).getDate(), list.get(0).getFinancialReceiptType()));

	}

	@Test
	public void testFindByMember() {
	}

}
