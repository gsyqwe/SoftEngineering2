package emaildata;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.Test;

import PO.EmailPO;
import PO.UserPO;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;

public class EmailDataUseDataBaseTest {
	ArrayList<EmailPO> backUp = new ArrayList<>();
	EmailDataUseDataBase test = new EmailDataUseDataBase();
	IOStrategyUseHibernate<EmailPO> directAccessor = new IOStrategyUseHibernate<>();

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
		assertEquals(null, test.getEmailByUser(UserPO.SAMPLE_1.getId()));
	}

	@Test
	public void testCase_2() {
		/*
		 * insert new data
		 */
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_3));
		/*
		 * load this data from database
		 */
		assertEquals(EmailPO.SAMPLE_1.getReceiverID(),
				test.getEmailByUser(EmailPO.SAMPLE_1.getReceiverID()).get(0).getReceiverID());
		/*
		 * find a data that does not exist
		 */
		assertEquals(null, test.getEmailByUser(EmailPO.SAMPLE_2.getReceiverID()));
	}

	@Test
	public void testCase_3() {
		/*
		 * test logical delete, insert first
		 */
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_5));

		/*
		 * do logical deletion
		 */
		assertEquals(ResultMessage.SUCCESS,
				test.deleteEmail(EmailPO.SAMPLE_1.getReceiverID(), EmailPO.SAMPLE_1.getDate()));
		assertEquals(ResultMessage.SUCCESS,
				test.deleteEmail(EmailPO.SAMPLE_2.getReceiverID(), EmailPO.SAMPLE_2.getDate()));
		assertEquals(ResultMessage.SUCCESS,
				test.deleteEmail(EmailPO.SAMPLE_3.getReceiverID(), EmailPO.SAMPLE_3.getDate()));
		assertEquals(ResultMessage.SUCCESS,
				test.deleteEmail(EmailPO.SAMPLE_4.getReceiverID(), EmailPO.SAMPLE_4.getDate()));
		assertEquals(ResultMessage.SUCCESS,
				test.deleteEmail(EmailPO.SAMPLE_5.getReceiverID(), EmailPO.SAMPLE_5.getDate()));

		/*
		 * can't not use EmailDataUseData's getAllList, use
		 * IOStrategyUseHibernate access database
		 */
		Iterator<EmailPO> iterator = directAccessor.outAll(EmailPO.class);
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
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_5));

		/*
		 * do logical deletion
		 */
		assertEquals(ResultMessage.SUCCESS,
				test.deleteEmail(EmailPO.SAMPLE_1.getReceiverID(), EmailPO.SAMPLE_1.getDate()));
		assertEquals(ResultMessage.SUCCESS,
				test.deleteEmail(EmailPO.SAMPLE_2.getReceiverID(), EmailPO.SAMPLE_2.getDate()));
		assertEquals(ResultMessage.SUCCESS,
				test.deleteEmail(EmailPO.SAMPLE_3.getReceiverID(), EmailPO.SAMPLE_3.getDate()));

		ArrayList<EmailPO> expect = new ArrayList<>();
		expect.add(EmailPO.SAMPLE_4); // has not been deleted
		expect.add(EmailPO.SAMPLE_5); // has not been deleted

		ArrayList<EmailPO> actual = test.getAllList();
		assertEquals(false, actual == null);

		/*
		 * output to console
		 */
		System.err.println("*****Expecting*****");
		expect.forEach(po -> System.out.println(po.getId()));
		System.err.println("*****Actual*****");
		actual.forEach(po -> System.out.println(po.getId()));
		System.err.println("***************");

		for (EmailPO po : actual) {
			boolean flag = false;
			for (EmailPO po2 : expect) {
				if (po.getId() == po2.getId()) {
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
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_3));

		/*
		 * update data that does not exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.updateEmail(EmailPO.SAMPLE_4.getReceiverID(), EmailPO.SAMPLE_4.getDate(), EmailPO.SAMPLE_4));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.updateEmail(EmailPO.SAMPLE_5.getReceiverID(), EmailPO.SAMPLE_5.getDate(), EmailPO.SAMPLE_5));
		/*
		 * update data that actual exist
		 */
		EmailPO.SAMPLE_1.setContent(new ArrayList<>());
		EmailPO.SAMPLE_2.setContent(new ArrayList<>());
		EmailPO.SAMPLE_3.setContent(new ArrayList<>());
		assertEquals(ResultMessage.SUCCESS,
				test.updateEmail(EmailPO.SAMPLE_1.getReceiverID(), EmailPO.SAMPLE_1.getDate(), EmailPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS,
				test.updateEmail(EmailPO.SAMPLE_2.getReceiverID(), EmailPO.SAMPLE_2.getDate(), EmailPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS,
				test.updateEmail(EmailPO.SAMPLE_3.getReceiverID(), EmailPO.SAMPLE_3.getDate(), EmailPO.SAMPLE_3));

		/*
		 * delete data that does not exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.deleteEmail(EmailPO.SAMPLE_4.getReceiverID(), EmailPO.SAMPLE_4.getDate()));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.deleteEmail(EmailPO.SAMPLE_4.getReceiverID(), EmailPO.SAMPLE_4.getDate()));

		/*
		 * delete data that actual exist
		 */
		assertEquals(ResultMessage.SUCCESS,
				test.deleteEmail(EmailPO.SAMPLE_1.getReceiverID(), EmailPO.SAMPLE_1.getDate()));
		assertEquals(ResultMessage.SUCCESS,
				test.deleteEmail(EmailPO.SAMPLE_2.getReceiverID(), EmailPO.SAMPLE_2.getDate()));
		assertEquals(ResultMessage.SUCCESS,
				test.deleteEmail(EmailPO.SAMPLE_3.getReceiverID(), EmailPO.SAMPLE_3.getDate()));

		/*
		 * insert the data that has been deleted logically
		 */
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insertEmail(EmailPO.SAMPLE_3));
		test.getAllList().forEach(po -> System.out.println(po.getId()));
	}


	@After
	public void recoveryUseBackUp() {
		/*
		 * delete the data that used for testing, real delete not logic delete
		 */
		Iterator<EmailPO> testData = directAccessor.outAll(EmailPO.class);
		if (testData != null) {
			while (testData.hasNext()) {
				assertEquals(true, this.directAccessor.deleteOne(testData.next()));
			}
		}
		// /*
		// * recovery the database
		// */
		// if (backUp != null && !backUp.isEmpty()) {
		// for (EmailPO po : backUp) {
		// assertEquals(ResultMessage.SUCCESS, test.insert(po));
		// }
		// }
	}
}
