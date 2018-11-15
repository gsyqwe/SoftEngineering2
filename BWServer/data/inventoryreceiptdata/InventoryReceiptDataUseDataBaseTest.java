package inventoryreceiptdata;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Test;

import PO.InventoryReceiptPO;
import PO.MemberPO;
import PO.UserPO;
import enums.InventoryReceiptType;
import enums.ReceiptState;
import enums.ResultMessage;

public class InventoryReceiptDataUseDataBaseTest {
	InventoryReceiptDataUseDataBase test = new InventoryReceiptDataUseDataBase();

	@Test
	public void testCase_1() {
		/*
		 * insert normally
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_1));
		/*
		 * insert InventoryReceiptPO that already exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED, test.insert(InventoryReceiptPO.SAMPLE_1));
		/*
		 * update normally
		 */
		InventoryReceiptPO.SAMPLE_1.setOperatorID("testing");
		assertEquals(ResultMessage.SUCCESS,
				test.update(InventoryReceiptPO.SAMPLE_1.getId(), InventoryReceiptPO.SAMPLE_1));
		/*
		 * update InventoryReceiptPO that does not exist
		 */
		InventoryReceiptPO.SAMPLE_1.setId("Testing ID");
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.update(InventoryReceiptPO.SAMPLE_1.getId(), InventoryReceiptPO.SAMPLE_1));
		/*
		 * delete InventoryReceiptPO that does not exist
		 */
		System.out.println(InventoryReceiptPO.SAMPLE_2);
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, test.delete(InventoryReceiptPO.SAMPLE_2.getId()));
	}

	@Test
	public void testCase_2() {
		ArrayList<InventoryReceiptPO> list = new ArrayList<>();
		list.add(InventoryReceiptPO.SAMPLE_1);
		/*
		 * if there is only one data, test findByID
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(list.get(0)));
		assertEquals(list.get(0).getId(), test.findByID(list.get(0).getId()).getId());

		/*
		 * if there are more than one data, test findByID
		 */
		list.add(InventoryReceiptPO.SAMPLE_2);
		assertEquals(ResultMessage.SUCCESS, test.insert(list.get(1)));
		assertEquals(list.get(0).getId(), test.findByID(list.get(0).getId()).getId());

		/*
		 * return null if there is no match
		 */
		assertEquals(null, test.findByID(InventoryReceiptPO.SAMPLE_3.getId()));
	}

	@Test
	public void testCase_3() {
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_5));
		test.getAllList().forEach(po -> System.out.println(po.getId()));
	}

	@Test
	public void testCase_4() {
		ArrayList<InventoryReceiptPO> list = new ArrayList<>();
		list.add(InventoryReceiptPO.SAMPLE_1);
		list.add(InventoryReceiptPO.SAMPLE_2);
		list.add(InventoryReceiptPO.SAMPLE_3);
		list.add(InventoryReceiptPO.SAMPLE_4);
		list.add(InventoryReceiptPO.SAMPLE_5);

		/*
		 * insert all
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_5));

		ArrayList<InventoryReceiptPO> expect = new ArrayList<>();
		ArrayList<InventoryReceiptPO> actual = new ArrayList<>();

		/*
		 * the InventoryReceiptPOs with specific InventoryReceiptType
		 */
		InventoryReceiptType financialReceiptType = InventoryReceiptType.ALARM;
		expect = list.parallelStream().filter(po -> po.getInventoryType().equals(financialReceiptType))
				.collect(Collectors.toCollection(ArrayList::new));
		actual = test.findByInventoryReceiptType(financialReceiptType);
		/*
		 * output to console
		 */
		System.err.println("*****Expecting*****");
		expect.forEach(po -> System.out.println(po.getId()));
		System.err.println("*****Actual*****");
		if (actual != null)
			actual.forEach(po -> System.out.println(po.getId()));
		System.err.println("***************");

		if (actual != null)
			for (InventoryReceiptPO po : actual) {
				boolean flag = false;
				for (InventoryReceiptPO po2 : expect) {
					if (po.getId().equals(po2.getId())) {
						flag = true;
						break;
					}
				}
				assertEquals(true, flag);
			}

		/*
		 * the InventoryReceiptPOs with specific userID
		 */
		String userID = UserPO.SAMPLE_1.getId();
		expect = list.parallelStream().filter(po -> po.getOperatorID().equals(userID))
				.collect(Collectors.toCollection(ArrayList::new));
		actual = test.findByUser(userID);
		/*
		 * output to console
		 */
		System.err.println("*****Expecting*****");
		expect.forEach(po -> System.out.println(po.getId()));
		System.err.println("*****Actual*****");
		if (actual != null)
			actual.forEach(po -> System.out.println(po.getId()));
		System.err.println("***************");

		if (actual != null)
			for (InventoryReceiptPO po : actual) {
				boolean flag = false;
				for (InventoryReceiptPO po2 : expect) {
					if (po.getId().equals(po2.getId())) {
						flag = true;
						break;
					}
				}
				assertEquals(true, flag);
			}

		/*
		 * the InventoryReceiptPOs with specific date
		 */
		Date from = InventoryReceiptPO.SAMPLE_1.getDate();
		Date to = new Date(InventoryReceiptPO.SAMPLE_5.getDate().getTime());
		expect = list.parallelStream().filter(po -> po.getDate().before(to) && po.getDate().after(from))
				.collect(Collectors.toCollection(ArrayList::new));
		actual = test.findByDate(from, to);
		/*
		 * output to console
		 */
		System.err.println("*****Expecting*****");
		expect.forEach(po -> System.out.println(po.getId()));
		System.err.println("*****Actual*****");
		if (actual != null)
			actual.forEach(po -> System.out.println(po.getId()));
		System.err.println("***************");
		if (actual != null)
			for (InventoryReceiptPO po : actual) {
				boolean flag = false;
				for (InventoryReceiptPO po2 : expect) {
					if (po.getId().equals(po2.getId())) {
						flag = true;
						break;
					}
				}
				assertEquals(true, flag);
			}

		/*
		 * the InventoryReceiptPOs with specific ReceiptState
		 */
		ReceiptState receiptState = ReceiptState.UNAPPROVEAL;
		expect = list.parallelStream().filter(po -> po.getState().equals(receiptState))
				.collect(Collectors.toCollection(ArrayList::new));
		actual = test.findByReceiptState(receiptState);
		/*
		 * output to console
		 */
		System.err.println("*****Expecting*****");
		expect.forEach(po -> System.out.println(po.getId()));
		System.err.println("*****Actual*****");
		if (actual != null)
			actual.forEach(po -> System.out.println(po.getId()));
		System.err.println("***************");

		if (actual != null)
			for (InventoryReceiptPO po : actual) {
				boolean flag = false;
				for (InventoryReceiptPO po2 : expect) {
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
		 * only one data, test getIDSuffix
		 */
		InventoryReceiptPO.SAMPLE_1.setInventoryType(InventoryReceiptType.ALARM);
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_1));
		assertEquals("00002", test.getIDSuffix(InventoryReceiptPO.SAMPLE_1.getDate(),
				InventoryReceiptPO.SAMPLE_1.getInventoryType()));
		/*
		 * two data, test getIDSuffix, insert a data with different
		 * InventoryType and same date, the actual result of getIDSuffix should
		 * be the same
		 */
		InventoryReceiptPO.SAMPLE_2.setInventoryType(InventoryReceiptType.BREAKAGE);
		InventoryReceiptPO.SAMPLE_2.setDate(InventoryReceiptPO.SAMPLE_1.getDate());
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_2));
		assertEquals("00002", test.getIDSuffix(InventoryReceiptPO.SAMPLE_1.getDate(),
				InventoryReceiptPO.SAMPLE_1.getInventoryType()));

		/*
		 * three data, test getIDSuffix, insert a data with same
		 * InventoryReceiptType and same date, the actual result of getIDSuffix
		 * should be increased
		 */
		InventoryReceiptPO.SAMPLE_3.setInventoryType(InventoryReceiptType.ALARM);
		InventoryReceiptPO.SAMPLE_3.setDate(InventoryReceiptPO.SAMPLE_1.getDate());
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_3));
		assertEquals("00003", test.getIDSuffix(InventoryReceiptPO.SAMPLE_1.getDate(),
				InventoryReceiptPO.SAMPLE_1.getInventoryType()));

		/*
		 * four data, test getIDSuffix, insert a data with same
		 * InventoryReceiptType but different date, the actual result of
		 * getIDSuffix should be the same as last result
		 */
		InventoryReceiptPO.SAMPLE_4.setInventoryType(InventoryReceiptType.ALARM);
		InventoryReceiptPO.SAMPLE_4.setDate(new Date(new Date().getTime() - 999999999));
		assertEquals(ResultMessage.SUCCESS, test.insert(InventoryReceiptPO.SAMPLE_4));
		assertEquals("00003", test.getIDSuffix(InventoryReceiptPO.SAMPLE_1.getDate(),
				InventoryReceiptPO.SAMPLE_1.getInventoryType()));

	}

	/*
	 * delete all data in database after every test
	 */
	@After
	public void deleteAll() {
		ArrayList<InventoryReceiptPO> list = test.getAllList();
		System.out.println("Database has : ");
		list.forEach(po -> System.out.println(po.getId()));
		if (!list.isEmpty()) {
			for (InventoryReceiptPO po : list) {
				assertEquals(ResultMessage.SUCCESS, test.delete(po.getId()));
			}
		}
	}
}
