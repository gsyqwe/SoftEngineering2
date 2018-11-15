package bankaccountdata;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.junit.After;
import org.junit.Test;

import PO.BankAccountPO;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;

public class BankAccountDataUseDataBaseTest {
	ArrayList<BankAccountPO> backUp = new ArrayList<>();
	BankAccountDataUseDataBase test = new BankAccountDataUseDataBase();
	IOStrategyUseHibernate<BankAccountPO> directAccessor = new IOStrategyUseHibernate<>();

	// @Before
	// public void doTheBackUp() {
	// backUp = test.getAllList();
	// }

	@Test
	public void testCase_1() {
		/*
		 * empty database, nothing can be found
		 */
		assertEquals(null, test.getAllList());
		assertEquals(null, test.findByCardNumber(BankAccountPO.SAMPLE_1.getCardNumber()));
		assertEquals(null, test.findByID(BankAccountPO.SAMPLE_1.getId()));
		assertEquals(null, test.findByName(BankAccountPO.SAMPLE_1.getName()));
	}

	@Test
	public void testCase_2() {
		/*
		 * insert a new data
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_1));
		/*
		 * primary key conflicts
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED, test.insert(BankAccountPO.SAMPLE_1));
		/*
		 * load this data from database
		 */
		assertEquals(BankAccountPO.SAMPLE_1.getId(), test.findByID(BankAccountPO.SAMPLE_1.getId()).getId());
		assertEquals(BankAccountPO.SAMPLE_1.getId(),
				test.findByCardNumber(BankAccountPO.SAMPLE_1.getCardNumber()).getId());
		assertEquals(BankAccountPO.SAMPLE_1.getId(), test.findByName(BankAccountPO.SAMPLE_1.getName()).getId());
		/*
		 * find a data that does not exist
		 */
		assertEquals(null, test.findByCardNumber(BankAccountPO.SAMPLE_2.getCardNumber()));
		assertEquals(null, test.findByID(BankAccountPO.SAMPLE_2.getId()));
		assertEquals(null, test.findByName(BankAccountPO.SAMPLE_2.getName()));
	}

	@Test
	public void testCase_3() {
		/*
		 * test logical delete, insert first
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_5));

		/*
		 * do logical deletion
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(BankAccountPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(BankAccountPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(BankAccountPO.SAMPLE_3.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(BankAccountPO.SAMPLE_4.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(BankAccountPO.SAMPLE_5.getId()));

		/*
		 * can't not use BankAccountDataUseData's getAllList, use
		 * IOStrategyUseHibernate access database
		 */
		Iterator<BankAccountPO> iterator = directAccessor.outAll(BankAccountPO.class);
		assertEquals(false, iterator == null);
		while (iterator.hasNext()) {
			assertEquals(true, iterator.next().getIsDeleted());
		}
	}

	@Test
	public void testCase_4() {
		/*
		 * partial logical deletion
		 */
		/*
		 * test logical delete, insert first
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_5));

		/*
		 * do logical deletion
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(BankAccountPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(BankAccountPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(BankAccountPO.SAMPLE_3.getId()));

		ArrayList<BankAccountPO> expect = new ArrayList<>();
		expect.add(BankAccountPO.SAMPLE_4); // has not been deleted
		expect.add(BankAccountPO.SAMPLE_5); // has not been deleted

		ArrayList<BankAccountPO> actual = test.getAllList();
		assertEquals(false, actual == null);

		/*
		 * output to console
		 */
		System.err.println("*****Expecting*****");
		expect.forEach(po -> System.out.println(po.getId()));
		System.err.println("*****Actual*****");
		actual.forEach(po -> System.out.println(po.getId()));
		System.err.println("***************");

		for (BankAccountPO po : actual) {
			boolean flag = false;
			for (BankAccountPO po2 : expect) {
				if (po.getId().equals(po2.getId())) {
					flag = true;
					break;
				}
			}
			assertEquals(true, flag);
		}
	}

	@Test
	public void testCase_5() {
		/*
		 * insert data first
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_3));

		/*
		 * update data that does not exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.update(BankAccountPO.SAMPLE_4.getId(), BankAccountPO.SAMPLE_4));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.update(BankAccountPO.SAMPLE_5.getId(), BankAccountPO.SAMPLE_5));
		/*
		 * update data that actual exist
		 */
		BankAccountPO.SAMPLE_1.setCardNumber("cardNumber_test1");
		BankAccountPO.SAMPLE_2.setCardNumber("cardNumber_test2");
		BankAccountPO.SAMPLE_3.setCardNumber("cardNumber_test3");
		assertEquals(ResultMessage.SUCCESS, test.update(BankAccountPO.SAMPLE_1.getId(), BankAccountPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.update(BankAccountPO.SAMPLE_2.getId(), BankAccountPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.update(BankAccountPO.SAMPLE_3.getId(), BankAccountPO.SAMPLE_3));

		/*
		 * delete data that does not exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, test.delete(BankAccountPO.SAMPLE_4.getId()));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, test.delete(BankAccountPO.SAMPLE_4.getId()));

		/*
		 * delete data that actual exist
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(BankAccountPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(BankAccountPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(BankAccountPO.SAMPLE_3.getId()));

		/*
		 * insert the data that has been deleted logically
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_3));
		test.getAllList().forEach(po -> System.out.println(po.getId()));
	}

	@Test
	public void testCase_6() {
		/*
		 * no data in database, id should begin with 00001
		 */
		assertEquals("00001", test.getIDSuffix(new Date()));

		/*
		 * one valid data in database
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_1));
		assertEquals("00002", test.getIDSuffix(BankAccountPO.SAMPLE_1.getDate()));

		/*
		 * two valid data in database
		 */
		BankAccountPO.SAMPLE_2.setDate(BankAccountPO.SAMPLE_1.getDate());
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_2));
		assertEquals("00003", test.getIDSuffix(BankAccountPO.SAMPLE_2.getDate()));

		/*
		 * delete one data
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(BankAccountPO.SAMPLE_1.getId()));

		/*
		 * one valid data and one data which has been deleted logically
		 */
		assertEquals("00003", test.getIDSuffix(BankAccountPO.SAMPLE_1.getDate()));

		/*
		 * two valid data and one data which has been deleted logically
		 */
		BankAccountPO.SAMPLE_3.setDate(BankAccountPO.SAMPLE_1.getDate());
		assertEquals(ResultMessage.SUCCESS, test.insert(BankAccountPO.SAMPLE_3));
		assertEquals("00004", test.getIDSuffix(BankAccountPO.SAMPLE_3.getDate()));
	}

	@After
	public void recoveryUseBackUp() {
		/*
		 * delete the data that used for testing, real delete not logic delete
		 */
		Iterator<BankAccountPO> testData = directAccessor.outAll(BankAccountPO.class);
		if (testData != null) {
			while (testData.hasNext()) {
				assertEquals(true, this.directAccessor.deleteOne(testData.next()));
			}
		}
		// /*
		// * recovery the database
		// */
		// if (backUp != null && !backUp.isEmpty()) {
		// for (BankAccountPO po : backUp) {
		// assertEquals(ResultMessage.SUCCESS, test.insert(po));
		// }
		// }
	}
}
