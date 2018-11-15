package memberdata;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.Test;

import PO.MemberPO;
import enums.MemberType;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;

public class MemberDataUseDataBaseTest {
	ArrayList<MemberPO> backUp = new ArrayList<>();
	MemberDataUseDataBase test = new MemberDataUseDataBase();
	IOStrategyUseHibernate<MemberPO> directAccessor = new IOStrategyUseHibernate<>();

	// @Before
	// public void doTheBackUp() {
	// backUp = test.getAllList();
	// }

	@Test
	public void testCase_1() {
		/*
		 * empty database, nothing can be found
		 */
		assertEquals(null, test.getAll());
		assertEquals(null, test.findByID(MemberPO.SAMPLE_1.getId()));
		assertEquals(null, test.findByName(MemberPO.SAMPLE_1.getName()));
	}

	@Test
	public void testCase_2() {
		/*
		 * insert a new data
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_1));
		/*
		 * primary key conflicts
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED, test.insert(MemberPO.SAMPLE_1));
		/*
		 * load this data from database
		 */
		assertEquals(MemberPO.SAMPLE_1.getId(), test.findByID(MemberPO.SAMPLE_1.getId()).getId());
		assertEquals(MemberPO.SAMPLE_1.getId(), test.findByName(MemberPO.SAMPLE_1.getName()).get(0).getId());
		/*
		 * find a data that does not exist
		 */
		MemberPO.SAMPLE_2.setName("我保證沒有名字叫這個吧!!!!");
		assertEquals(null, test.findByID(MemberPO.SAMPLE_2.getId()));
		assertEquals(null, test.findByName(MemberPO.SAMPLE_2.getName()));
	}

	@Test
	public void testCase_3() {
		/*
		 * test logical delete, insert first
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_5));

		/*
		 * do logical deletion
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(MemberPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(MemberPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(MemberPO.SAMPLE_3.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(MemberPO.SAMPLE_4.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(MemberPO.SAMPLE_5.getId()));

		/*
		 * can't not use MemberDataUseData's getAllList, use
		 * IOStrategyUseHibernate access database
		 */
		Iterator<MemberPO> iterator = directAccessor.outAll(MemberPO.class);
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
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_5));

		/*
		 * do logical deletion
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(MemberPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(MemberPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(MemberPO.SAMPLE_3.getId()));

		ArrayList<MemberPO> expect = new ArrayList<>();
		expect.add(MemberPO.SAMPLE_4); // has not been deleted
		expect.add(MemberPO.SAMPLE_5); // has not been deleted

		ArrayList<MemberPO> actual = test.getAll();
		assertEquals(false, actual == null);

		/*
		 * output to console
		 */
		System.err.println("*****Expecting*****");
		expect.forEach(po -> System.out.println(po.getId()));
		System.err.println("*****Actual*****");
		actual.forEach(po -> System.out.println(po.getId()));
		System.err.println("***************");

		for (MemberPO po : actual) {
			boolean flag = false;
			for (MemberPO po2 : expect) {
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
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_3));

		/*
		 * update data that does not exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.update(MemberPO.SAMPLE_4.getId(), MemberPO.SAMPLE_4));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.update(MemberPO.SAMPLE_4.getId(), MemberPO.SAMPLE_4));
		/*
		 * update data that actual exist
		 */
		MemberPO.SAMPLE_1.setName("Name_test1");
		MemberPO.SAMPLE_2.setName("Name_test2");
		MemberPO.SAMPLE_3.setName("Name_test3");
		assertEquals(ResultMessage.SUCCESS, test.update(MemberPO.SAMPLE_1.getId(), MemberPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.update(MemberPO.SAMPLE_2.getId(), MemberPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.update(MemberPO.SAMPLE_3.getId(), MemberPO.SAMPLE_3));

		/*
		 * delete data that does not exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, test.delete(MemberPO.SAMPLE_4.getId()));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, test.delete(MemberPO.SAMPLE_4.getId()));

		/*
		 * delete data that actual exist
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(MemberPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(MemberPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(MemberPO.SAMPLE_3.getId()));

		/*
		 * insert the data that has been deleted logically
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_3));
	}

	@Test
	public void testCase_6() {
		/*
		 * no data in database, id should begin with 00001
		 */
		assertEquals("00001", test.getMemberID(MemberType.RETAILER));

		/*
		 * one valid data in database
		 */
		MemberPO.SAMPLE_1.setMemberType(MemberType.SUPPLIER);
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_1));
		assertEquals("00002", test.getMemberID(MemberPO.SAMPLE_1.getMemberType()));

		/*
		 * two valid data in database
		 */
		MemberPO.SAMPLE_2.setMemberType(MemberPO.SAMPLE_1.getMemberType());
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_2));
		assertEquals("00003", test.getMemberID(MemberPO.SAMPLE_1.getMemberType()));

		/*
		 * delete one data
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(MemberPO.SAMPLE_1.getId()));

		/*
		 * one valid data and one data which has been deleted logically
		 */
		assertEquals("00003", test.getMemberID(MemberPO.SAMPLE_1.getMemberType()));

		/*
		 * two valid data and one data which has been deleted logically
		 */
		MemberPO.SAMPLE_3.setMemberType(MemberPO.SAMPLE_1.getMemberType());
		assertEquals(ResultMessage.SUCCESS, test.insert(MemberPO.SAMPLE_3));
		assertEquals("00004", test.getMemberID(MemberPO.SAMPLE_1.getMemberType()));
		
		/*
		 * different MemberType
		 */
		assertEquals("00001", test.getMemberID(MemberType.RETAILER));
	}

	@After
	public void recoveryUseBackUp() {
		/*
		 * delete the data that used for testing, real delete not logic delete
		 */
		Iterator<MemberPO> testData = directAccessor.outAll(MemberPO.class);
		if (testData != null) {
			while (testData.hasNext()) {
				assertEquals(true, this.directAccessor.deleteOne(testData.next()));
			}
		}
		// /*
		// * recovery the database
		// */
		// if (backUp != null && !backUp.isEmpty()) {
		// for (MemberPO po : backUp) {
		// assertEquals(ResultMessage.SUCCESS, test.insert(po));
		// }
		// }
	}
}
