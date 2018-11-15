package categorydata;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.Test;

import PO.CategoryPO;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;

public class CategoryDataUseDataBaseTest {
	ArrayList<CategoryPO> backUp = new ArrayList<>();
	CategoryDataUseDataBase test = new CategoryDataUseDataBase();
	IOStrategyUseHibernate<CategoryPO> directAccessor = new IOStrategyUseHibernate<>();

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
		assertEquals(null, test.findByID(CategoryPO.SAMPLE_1.getId()));
		assertEquals(null, test.findByName(CategoryPO.SAMPLE_1.getName()));
	}

	@Test
	public void testCase_2() {
		/*
		 * insert a new data
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_1));
		/*
		 * primary key conflicts
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED, test.insert(CategoryPO.SAMPLE_1));
		/*
		 * load this data from database
		 */
		assertEquals(CategoryPO.SAMPLE_1.getId(), test.findByID(CategoryPO.SAMPLE_1.getId()).getId());
		assertEquals(CategoryPO.SAMPLE_1.getId(), test.findByName(CategoryPO.SAMPLE_1.getName()).getId());
		/*
		 * find a data that does not exist
		 */
		CategoryPO.SAMPLE_2.setName("我保證沒有名字叫這個吧!!!!");
		assertEquals(null, test.findByID(CategoryPO.SAMPLE_2.getId()));
		assertEquals(null, test.findByName(CategoryPO.SAMPLE_2.getName()));
	}

	@Test
	public void testCase_3() {
		/*
		 * test logical delete, insert first
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_5));

		/*
		 * do logical deletion
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(CategoryPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(CategoryPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(CategoryPO.SAMPLE_3.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(CategoryPO.SAMPLE_4.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(CategoryPO.SAMPLE_5.getId()));

		/*
		 * can't not use CategoryDataUseData's getAllList, use
		 * IOStrategyUseHibernate access database
		 */
		Iterator<CategoryPO> iterator = directAccessor.outAll(CategoryPO.class);
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
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_5));

		/*
		 * do logical deletion
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(CategoryPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(CategoryPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(CategoryPO.SAMPLE_3.getId()));

		ArrayList<CategoryPO> expect = new ArrayList<>();
		expect.add(CategoryPO.SAMPLE_4); // has not been deleted
		expect.add(CategoryPO.SAMPLE_5); // has not been deleted

		ArrayList<CategoryPO> actual = test.getAllList();
		assertEquals(false, actual == null);

		/*
		 * output to console
		 */
		System.err.println("*****Expecting*****");
		expect.forEach(po -> System.out.println(po.getId()));
		System.err.println("*****Actual*****");
		actual.forEach(po -> System.out.println(po.getId()));
		System.err.println("***************");

		for (CategoryPO po : actual) {
			boolean flag = false;
			for (CategoryPO po2 : expect) {
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
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_3));

		/*
		 * update data that does not exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.update(CategoryPO.SAMPLE_4.getId(), CategoryPO.SAMPLE_4));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.update(CategoryPO.SAMPLE_5.getId(), CategoryPO.SAMPLE_5));
		/*
		 * update data that actual exist
		 */
		CategoryPO.SAMPLE_1.setName("Name_test1");
		CategoryPO.SAMPLE_2.setName("Name_test2");
		CategoryPO.SAMPLE_3.setName("Name_test3");
		assertEquals(ResultMessage.SUCCESS, test.update(CategoryPO.SAMPLE_1.getId(), CategoryPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.update(CategoryPO.SAMPLE_2.getId(), CategoryPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.update(CategoryPO.SAMPLE_3.getId(), CategoryPO.SAMPLE_3));

		/*
		 * delete data that does not exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, test.delete(CategoryPO.SAMPLE_4.getId()));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, test.delete(CategoryPO.SAMPLE_4.getId()));

		/*
		 * delete data that actual exist
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(CategoryPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(CategoryPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.SUCCESS, test.delete(CategoryPO.SAMPLE_3.getId()));

		/*
		 * insert the data that has been deleted logically
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_3));
		test.getAllList().forEach(po -> System.out.println(po.getId()));
	}

	@Test
	public void testCase_6() {
		/*
		 * no data in database, id should begin with 00001
		 */
		assertEquals("00001", test.getMaxID());

		/*
		 * one valid data in database
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_1));
		assertEquals("00002", test.getMaxID());

		/*
		 * two valid data in database
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_2));
		assertEquals("00003", test.getMaxID());

		/*
		 * delete one data
		 */
		assertEquals(ResultMessage.SUCCESS, test.delete(CategoryPO.SAMPLE_1.getId()));

		/*
		 * one valid data and one data which has been deleted logically
		 */
		assertEquals("00003", test.getMaxID());

		/*
		 * two valid data and one data which has been deleted logically
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(CategoryPO.SAMPLE_3));
		assertEquals("00004", test.getMaxID());
	}

	@After
	public void recoveryUseBackUp() {
		/*
		 * delete the data that used for testing, real delete not logic delete
		 */
		Iterator<CategoryPO> testData = directAccessor.outAll(CategoryPO.class);
		if (testData != null) {
			while (testData.hasNext()) {
				assertEquals(true, this.directAccessor.deleteOne(testData.next()));
			}
		}
		// /*
		// * recovery the database
		// */
		// if (backUp != null && !backUp.isEmpty()) {
		// for (CategoryPO po : backUp) {
		// assertEquals(ResultMessage.SUCCESS, test.insert(po));
		// }
		// }
	}
}
