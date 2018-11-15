package promotionstrategydata;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import PO.PromotionByCombinationListPO;
import PO.PromotionByLevelPO;
import PO.PromotionBySumPO;
import PromotionStrategybl.PromotionBySum;
import enums.PromotionType;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;

public class PromotionStrategyDataUseDataBaseTest {
	ArrayList<PromotionByCombinationListPO> backUpOfCombination = new ArrayList<>();
	ArrayList<PromotionByLevelPO> backUpOfLevel = new ArrayList<>();
	ArrayList<PromotionBySumPO> backUpOfSum = new ArrayList<>();
	PromotionStrategyDataUseDataBase test = new PromotionStrategyDataUseDataBase();
	IOStrategyUseHibernate<PromotionByCombinationListPO> directAccessorOfCombination = new IOStrategyUseHibernate<>();
	IOStrategyUseHibernate<PromotionByLevelPO> directAccessorOfLevel = new IOStrategyUseHibernate<>();
	IOStrategyUseHibernate<PromotionBySumPO> directAccessorOfSum = new IOStrategyUseHibernate<>();

	@Before
	public void doTheBackUp() {
		ArrayList<PromotionByCombinationListPO> list = test.getAllCombination();
		if (list != null)
			list.forEach(po -> test.deletePromotionByCombination(po));
		ArrayList<PromotionBySumPO> list2 = test.getAllSum();
		if (list2 != null)
			list2.forEach(po -> test.deletePromotionBySum(po));
		ArrayList<PromotionByLevelPO> list3 = test.getAllLevel();
		if (list3 != null)
			list3.forEach(po -> test.deletePromotionByLevel(po));
	}

	@Test
	public void testDelete() {
		assertEquals(ResultMessage.SUCCESS, test.addPromotionByCombinationList(PromotionByCombinationListPO.SAMPLE_1));
		ArrayList<PromotionByCombinationListPO> list = test.getAllCombination();
		assertEquals(1, list.size());
		assertEquals(PromotionByCombinationListPO.SAMPLE_1.getPromotionID(), list.get(0).getPromotionID());
		assertEquals(ResultMessage.SUCCESS, test.deletePromotionByCombination(PromotionByCombinationListPO.SAMPLE_1));
	}

	@Test
	public void testCase_1() {
		/*
		 * empty database, nothing can be found
		 */
		assertEquals(null, test.getAllSum());
		assertEquals(null, test.getAllCombination());
		assertEquals(null, test.getAllLevel());
		assertEquals(null, test.findPromotionByCombinationByTime(new Date()));
		assertEquals(null, test.findPromotionByLevelByTime(new Date()));
		assertEquals(null, test.findPromotionBySumByTime(new Date()));

	}

	@Test
	public void testCase_2() {
		/*
		 * insert a new data
		 */
		assertEquals(ResultMessage.SUCCESS, test.addPromotionByCombinationList(PromotionByCombinationListPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.addPromotionBySum(PromotionBySumPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.addPromotionByLevelPO(PromotionByLevelPO.SAMPLE_1));
		/*
		 * primary key conflicts
		 */
		assertEquals(ResultMessage.PROMOTION_ADD_FALL,
				test.addPromotionByCombinationList(PromotionByCombinationListPO.SAMPLE_1));
		assertEquals(ResultMessage.PROMOTION_ADD_FALL, test.addPromotionByLevelPO(PromotionByLevelPO.SAMPLE_1));
		assertEquals(ResultMessage.PROMOTION_ADD_FALL, test.addPromotionBySum(PromotionBySumPO.SAMPLE_1));
		/*
		 * delete successfully
		 */
		assertEquals(ResultMessage.SUCCESS, test.deletePromotionByCombination(PromotionByCombinationListPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.deletePromotionBySum(PromotionBySumPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.deletePromotionByLevel(PromotionByLevelPO.SAMPLE_1));
	}

	@Test
	public void testCase_3() {
		/*
		 * no data in database, id should begin with 00001
		 */
		assertEquals("00001", test.getSuffix(PromotionType.PromotionBySum));

		/*
		 * one valid data in database
		 */
		assertEquals(ResultMessage.SUCCESS, test.addPromotionBySum(PromotionBySumPO.SAMPLE_1));
		assertEquals("00002", test.getSuffix(PromotionType.PromotionBySum));

		/*
		 * two valid data in database
		 */
		assertEquals(ResultMessage.SUCCESS, test.addPromotionBySum(PromotionBySumPO.SAMPLE_2));
		assertEquals("00003", test.getSuffix(PromotionType.PromotionBySum));

		/*
		 * different PromotionType
		 */
		assertEquals(ResultMessage.SUCCESS, test.addPromotionByCombinationList(PromotionByCombinationListPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.addPromotionByLevelPO(PromotionByLevelPO.SAMPLE_1));
		assertEquals("00002", test.getSuffix(PromotionType.PromotionByCombination));
		assertEquals("00002", test.getSuffix(PromotionType.PromotionByLevel));
		assertEquals("00003", test.getSuffix(PromotionType.PromotionBySum));
	}

	// @After
	// public void recoveryUseBackUp() {
	// /*
	// * delete the data that used for testing, real delete not logic delete
	// */
	// Iterator<PromotionByCombinationListPO> testDataOfCombination =
	// directAccessorOfCombination
	// .outAll(PromotionByCombinationListPO.class);
	// Iterator<PromotionByLevelPO> testDataOfLevel =
	// directAccessorOfLevel.outAll(PromotionByLevelPO.class);
	// Iterator<PromotionBySumPO> testDataOfSum =
	// directAccessorOfSum.outAll(PromotionBySumPO.class);
	// if (testDataOfCombination != null) {
	// while (testDataOfCombination.hasNext()) {
	// PromotionByCombinationListPO po = testDataOfCombination.next();
	// System.err.println("***************");
	// System.err.println(po.getPromotionID());
	// System.err.println("***************");
	// test.getAllCombination().forEach(po2 ->
	// System.out.println(po2.getPromotionID()));
	// assertEquals(true, this.directAccessorOfCombination.deleteOne(po));
	// }
	// }
	// if (testDataOfLevel != null) {
	// while (testDataOfLevel.hasNext()) {
	// PromotionByLevelPO po = testDataOfLevel.next();
	// System.err.println("***************");
	// System.err.println(po.getPromotionID());
	// System.err.println("***************");
	// test.getAllLevel().forEach(po2 ->
	// System.out.println(po2.getPromotionID()));
	// assertEquals(true, this.directAccessorOfLevel.deleteOne(po));
	// }
	// }
	// if (testDataOfSum != null) {
	// while (testDataOfSum.hasNext()) {
	// PromotionBySumPO po = testDataOfSum.next();
	// System.err.println("***************");
	// System.err.println(po.getPromotionID());
	// System.err.println("***************");
	// test.getAllSum().forEach(po2->System.out.println(po.getPromotionID()));
	// assertEquals(true, this.directAccessorOfSum.deleteOne(po));
	// }
	// }
	// /*
	// * recovery the database
	// */
	// if (backUp != null && !backUp.isEmpty()) {
	// for (PromotionStrategyPO po : backUp) {
	// assertEquals(ResultMessage.SUCCESS, test.insert(po));
	// }
	// }
	// }
}
