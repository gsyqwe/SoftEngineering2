package salesdata;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Test;

import PO.MemberPO;
import PO.SalesReceiptPO;
import PO.UserPO;
import enums.ReceiptState;
import enums.ResultMessage;
import enums.SalesReceiptType;

public class SalesDataUseDataBaseTest {
	SalesDataUseDataBase test = new SalesDataUseDataBase();

	@Test
	public void testCase_1() {
		/*
		 * insert normally
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_1));
		/*
		 * insert SalesReceiptPO that already exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED, test.insert(SalesReceiptPO.SAMPLE_1));
		/*
		 * update normally
		 */
		SalesReceiptPO.SAMPLE_1.setMemberID("memberID testing");
		assertEquals(ResultMessage.SUCCESS, test.update(SalesReceiptPO.SAMPLE_1.getId(), SalesReceiptPO.SAMPLE_1));
		/*
		 * update SalesReceiptPO that does not exist
		 */
		SalesReceiptPO.SAMPLE_1.setId("Testing ID");
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.update(SalesReceiptPO.SAMPLE_1.getId(), SalesReceiptPO.SAMPLE_1));
		/*
		 * delete SalesReceiptPO that does not exist
		 */
		System.out.println(SalesReceiptPO.SAMPLE_2);
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, test.delete(SalesReceiptPO.SAMPLE_2.getId()));
	}

	@Test
	public void testCase_2() {
		ArrayList<SalesReceiptPO> list = new ArrayList<>();
		list.add(SalesReceiptPO.SAMPLE_1);
		/*
		 * if there is only one data, test findByID
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(list.get(0)));
		assertEquals(list.get(0).getId(), test.findByID(list.get(0).getId()).getId());

		/*
		 * if there are more than one data, test findByID
		 */
		list.add(SalesReceiptPO.SAMPLE_2);
		assertEquals(ResultMessage.SUCCESS, test.insert(list.get(1)));
		assertEquals(list.get(0).getId(), test.findByID(list.get(0).getId()).getId());

		/*
		 * return null if there is no match
		 */
		assertEquals(null, test.findByID(SalesReceiptPO.SAMPLE_3.getId()));
	}

	@Test
	public void testCase_3() {
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_5));
		test.getAllList().forEach(po -> System.out.println(po.getId()));
	}

	@Test
	public void testCase_4() {
		ArrayList<SalesReceiptPO> list = new ArrayList<>();
		list.add(SalesReceiptPO.SAMPLE_1);
		list.add(SalesReceiptPO.SAMPLE_2);
		list.add(SalesReceiptPO.SAMPLE_3);
		list.add(SalesReceiptPO.SAMPLE_4);
		list.add(SalesReceiptPO.SAMPLE_5);

		/*
		 * insert all
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_5));

		ArrayList<SalesReceiptPO> expect = new ArrayList<>();
		ArrayList<SalesReceiptPO> actual = new ArrayList<>();
		/*
		 * the SalesReceiptPOs with specific memberID
		 */
		String memberID = MemberPO.SAMPLE_1.getId();
		expect = list.parallelStream().filter(po -> po.getMemberID().equals(memberID))
				.collect(Collectors.toCollection(ArrayList::new));
		actual = test.findByMember(memberID);

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
			for (SalesReceiptPO po : actual) {
				boolean flag = false;
				for (SalesReceiptPO po2 : expect) {
					if (po.getId().equals(po2.getId())) {
						flag = true;
						break;
					}
				}
				assertEquals(true, flag);
			}

		/*
		 * the SalesReceiptPOs with specific SalesReceiptType
		 */
		SalesReceiptType salesReceiptType = SalesReceiptType.SALES;
		expect = list.parallelStream().filter(po -> po.getSalesType().equals(salesReceiptType))
				.collect(Collectors.toCollection(ArrayList::new));
		actual = test.findBySalesType(salesReceiptType);
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
			for (SalesReceiptPO po : actual) {
				boolean flag = false;
				for (SalesReceiptPO po2 : expect) {
					if (po.getId().equals(po2.getId())) {
						flag = true;
						break;
					}
				}
				assertEquals(true, flag);
			}

		/*
		 * the SalesReceiptPOs with specific userID
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
			for (SalesReceiptPO po : actual) {
				boolean flag = false;
				for (SalesReceiptPO po2 : expect) {
					if (po.getId().equals(po2.getId())) {
						flag = true;
						break;
					}
				}
				assertEquals(true, flag);
			}

		/*
		 * the SalesReceiptPOs with specific date
		 */
		Date from = SalesReceiptPO.SAMPLE_1.getDate();
		Date to = new Date(SalesReceiptPO.SAMPLE_5.getDate().getTime());
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
			for (SalesReceiptPO po : actual) {
				boolean flag = false;
				for (SalesReceiptPO po2 : expect) {
					if (po.getId().equals(po2.getId())) {
						flag = true;
						break;
					}
				}
				assertEquals(true, flag);
			}

		/*
		 * the SalesReceiptPOs with specific ReceiptState
		 */
		ReceiptState receiptState = SalesReceiptPO.SAMPLE_1.getState();
		expect = list.parallelStream().filter(po -> po.getState().equals(receiptState))
				.collect(Collectors.toCollection(ArrayList::new));
		actual = test.findByReceiptState(receiptState);
		/*
		 * output to console
		 */
		System.err.println("*****Expecting*****");
		expect.forEach(po -> System.out.println(po.getId()));
		System.err.println("*****Actual*****");
		actual.forEach(po -> System.out.println(po.getId()));
		System.err.println("***************");
		for (SalesReceiptPO po : actual) {
			boolean flag = false;
			for (SalesReceiptPO po2 : expect) {
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
		SalesReceiptPO.SAMPLE_1.setSalesType(SalesReceiptType.PURCHASE);
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_1));
		assertEquals("00002",
				test.getIDSuffix(SalesReceiptPO.SAMPLE_1.getDate(), SalesReceiptPO.SAMPLE_1.getSalesType()));
		/*
		 * two data, test getIDSuffix, insert a data with different SalesType
		 * and same date, the actual result of getIDSuffix should be the same
		 */
		SalesReceiptPO.SAMPLE_2.setSalesType(SalesReceiptType.PURCHASE_RETURN);
		SalesReceiptPO.SAMPLE_2.setDate(SalesReceiptPO.SAMPLE_1.getDate());
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_2));
		assertEquals("00002",
				test.getIDSuffix(SalesReceiptPO.SAMPLE_1.getDate(), SalesReceiptPO.SAMPLE_1.getSalesType()));

		/*
		 * three data, test getIDSuffix, insert a data with same
		 * SalesReceiptType and same date, the actual result of getIDSuffix
		 * should be increased
		 */
		SalesReceiptPO.SAMPLE_3.setSalesType(SalesReceiptType.PURCHASE);
		SalesReceiptPO.SAMPLE_3.setDate(SalesReceiptPO.SAMPLE_1.getDate());
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_3));
		assertEquals("00003",
				test.getIDSuffix(SalesReceiptPO.SAMPLE_1.getDate(), SalesReceiptPO.SAMPLE_1.getSalesType()));

		/*
		 * four data, test getIDSuffix, insert a data with same SalesReceiptType
		 * but different date, the actual result of getIDSuffix should be the
		 * same as last result
		 */
		SalesReceiptPO.SAMPLE_4.setSalesType(SalesReceiptType.PURCHASE);
		SalesReceiptPO.SAMPLE_4.setDate(new Date(new Date().getTime() - 999999999));
		assertEquals(ResultMessage.SUCCESS, test.insert(SalesReceiptPO.SAMPLE_4));
		assertEquals("00003",
				test.getIDSuffix(SalesReceiptPO.SAMPLE_1.getDate(), SalesReceiptPO.SAMPLE_1.getSalesType()));

	}

	/*
	 * delete all data in database after every test
	 */
	@After
	public void deleteAll() {
		ArrayList<SalesReceiptPO> list = test.getAllList();
		System.out.println("Database has : ");
		list.forEach(po -> System.out.println(po.getId()));
		if (!list.isEmpty()) {
			for (SalesReceiptPO po : list) {
				assertEquals(ResultMessage.SUCCESS, test.delete(po.getId()));
			}
		}
	}
}
