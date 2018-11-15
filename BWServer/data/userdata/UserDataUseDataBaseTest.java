package userdata;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.Test;

import PO.UserPO;
import enums.JobType;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;

public class UserDataUseDataBaseTest {
	ArrayList<UserPO> backUp = new ArrayList<>();
	UserDataUseDataBase test = new UserDataUseDataBase();
	IOStrategyUseHibernate<UserPO> directAccessor = new IOStrategyUseHibernate<>();

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
		assertEquals(null, test.findByUserType(JobType.SALESMAN));
		assertEquals(null, test.findByID(UserPO.SAMPLE_1.getId()));
		assertEquals(null, test.findByUserType(JobType.ADMINOR));
	}

	@Test
	public void testCase_2() {
		/*
		 * insert a new data
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_1));
		/*
		 * primary key conflicts
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED, test.insert(UserPO.SAMPLE_1));
		/*
		 * load this data from database
		 */
		assertEquals(UserPO.SAMPLE_1.getId(), test.findByID(UserPO.SAMPLE_1.getId()).getId());
		assertEquals(UserPO.SAMPLE_1.getJob(), test.findByUserType(UserPO.SAMPLE_1.getJob()).get(0).getJob());
		/*
		 * find a data that does not exist
		 */
		assertEquals(null, test.findByID(UserPO.SAMPLE_2.getId()));
		assertEquals(null, test.findByUserType(UserPO.SAMPLE_2.getJob()));
	}

	@Test
	public void testCase_3() {
		/*
		 * test logical delete, insert first
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_4));

		/*
		 * do logical deletion
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(UserPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(UserPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(UserPO.SAMPLE_3.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(UserPO.SAMPLE_4.getId()));

		/*
		 * can't not use UserDataUseData's getAllList, use
		 * IOStrategyUseHibernate access database
		 */
		Iterator<UserPO> iterator = directAccessor.outAll(UserPO.class);
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
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_4));

		/*
		 * do logical deletion
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(UserPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(UserPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(UserPO.SAMPLE_3.getId()));

		ArrayList<UserPO> expect = new ArrayList<>();
		expect.add(UserPO.SAMPLE_4); // has not been deleted

		ArrayList<UserPO> actual = test.getAllList();
		assertEquals(false, actual == null);

		/*
		 * output to console
		 */
		System.err.println("*****Expecting*****");
		expect.forEach(po -> System.out.println(po.getId()));
		System.err.println("*****Actual*****");
		actual.forEach(po -> System.out.println(po.getId()));
		System.err.println("***************");

		for (UserPO po : actual) {
			boolean flag = false;
			for (UserPO po2 : expect) {
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
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_3));

		/*
		 * update data that does not exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.update(UserPO.SAMPLE_4.getId(), UserPO.SAMPLE_4));
		/*
		 * update data that actual exist
		 */
		UserPO.SAMPLE_1.setName("test1");
		UserPO.SAMPLE_2.setName("test2");
		UserPO.SAMPLE_3.setName("test3");
		assertEquals(ResultMessage.SUCCESS, test.update(UserPO.SAMPLE_1.getId(), UserPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.update(UserPO.SAMPLE_2.getId(), UserPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.update(UserPO.SAMPLE_3.getId(), UserPO.SAMPLE_3));

		/*
		 * delete data that does not exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, test.delete(UserPO.SAMPLE_4.getId()));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, test.delete(UserPO.SAMPLE_4.getId()));

		/*
		 * delete data that actual exist
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(UserPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(UserPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(UserPO.SAMPLE_3.getId()));

		/*
		 * insert the data that has been deleted logically
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_3));
		test.getAllList().forEach(po -> System.out.println(po.getId()));
	}

	@Test
	public void testCase_6() {
		/*
		 * no data in database, id should begin with 00001
		 */
		assertEquals("00001", test.getIDSuffix(JobType.SALESMAN));

		/*
		 * one valid data in database
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_1));
		assertEquals("00002", test.getIDSuffix(UserPO.SAMPLE_1.getJob()));

		/*
		 * two valid data in database
		 */
		UserPO.SAMPLE_2.setJob(UserPO.SAMPLE_1.getJob());
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_2));
		assertEquals("00003", test.getIDSuffix(UserPO.SAMPLE_2.getJob()));

		/*
		 * delete one data
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(UserPO.SAMPLE_1.getId()));

		/*
		 * one valid data and one data which has been deleted logically
		 */
		assertEquals("00003", test.getIDSuffix(UserPO.SAMPLE_1.getJob()));

		/*
		 * two valid data and one data which has been deleted logically
		 */
		UserPO.SAMPLE_3.setJob(UserPO.SAMPLE_1.getJob());
		assertEquals(ResultMessage.SUCCESS, test.insert(UserPO.SAMPLE_3));
		assertEquals("00004", test.getIDSuffix(UserPO.SAMPLE_3.getJob()));
	}

	@After
	public void recoveryUseBackUp() {
		/*
		 * delete the data that used for testing, real delete not logic delete
		 */
		Iterator<UserPO> testData = directAccessor.outAll(UserPO.class);
		if (testData != null) {
			while (testData.hasNext()) {
				assertEquals(true, this.directAccessor.deleteOne(testData.next()));
			}
		}
		// /*
		// * recovery the database
		// */
		// if (backUp != null && !backUp.isEmpty()) {
		// for (UserPO po : backUp) {
		// assertEquals(ResultMessage.SUCCESS, test.insert(po));
		// }
		// }
	}
}
