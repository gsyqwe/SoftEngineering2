package financialreceiptdata;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Test;

import PO.FinancialReceiptPO;
import PO.MemberPO;
import PO.UserPO;
import enums.FinancialReceiptType;
import enums.ReceiptState;
import enums.ResultMessage;

public class FinancialReceiptDataUseDataBaseTest {
	FinancialReceiptDataUseDataBase test = new FinancialReceiptDataUseDataBase();

	@Test
	public void testCase_1() {
		/*
		 * insert normally
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_1));
		/*
		 * insert FinancialReceiptPO that already exist
		 */
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED, test.insert(FinancialReceiptPO.SAMPLE_1));
		/*
		 * update normally
		 */
		FinancialReceiptPO.SAMPLE_1.setMemberID("memberID testing");
		assertEquals(ResultMessage.SUCCESS,
				test.update(FinancialReceiptPO.SAMPLE_1.getId(), FinancialReceiptPO.SAMPLE_1));
		/*
		 * update FinancialReceiptPO that does not exist
		 */
		FinancialReceiptPO.SAMPLE_1.setId("Testing ID");
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				test.update(FinancialReceiptPO.SAMPLE_1.getId(), FinancialReceiptPO.SAMPLE_1));
		/*
		 * delete FinancialReceiptPO that does not exist
		 */
		System.out.println(FinancialReceiptPO.SAMPLE_2);
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, test.delete(FinancialReceiptPO.SAMPLE_2.getId()));
	}

	@Test
	public void testCase_2() {
		ArrayList<FinancialReceiptPO> list = new ArrayList<>();
		list.add(FinancialReceiptPO.SAMPLE_1);
		/*
		 * if there is only one data, test findByID
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(list.get(0)));
		assertEquals(list.get(0).getId(), test.findByID(list.get(0).getId()).getId());

		/*
		 * if there are more than one data, test findByID
		 */
		list.add(FinancialReceiptPO.SAMPLE_2);
		assertEquals(ResultMessage.SUCCESS, test.insert(list.get(1)));
		assertEquals(list.get(0).getId(), test.findByID(list.get(0).getId()).getId());

		/*
		 * return null if there is no match
		 */
		assertEquals(null, test.findByID(FinancialReceiptPO.SAMPLE_3.getId()));
	}

	@Test
	public void testCase_3() {
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_5));
		test.getAllList().forEach(po -> System.out.println(po.getId()));
	}

	@Test
	public void testCase_4() {
		ArrayList<FinancialReceiptPO> list = new ArrayList<>();
		list.add(FinancialReceiptPO.SAMPLE_1);
		list.add(FinancialReceiptPO.SAMPLE_2);
		list.add(FinancialReceiptPO.SAMPLE_3);
		list.add(FinancialReceiptPO.SAMPLE_4);
		list.add(FinancialReceiptPO.SAMPLE_5);

		/*
		 * insert all
		 */
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_5));

		/*
		 * the FinancialReceiptPOs with specific memberID
		 */
		String memberID = MemberPO.SAMPLE_1.getId();
		ArrayList<FinancialReceiptPO> expect = list.parallelStream().filter(po -> po.getMemberID().equals(memberID))
				.collect(Collectors.toCollection(ArrayList::new));
		ArrayList<FinancialReceiptPO> actual = test.findByMember(memberID);
		/*
		 * output to console
		 */
		System.err.println("*****Expecting*****");
		expect.forEach(po -> System.out.println(po.getId()));
		System.err.println("*****Actual*****");
		actual.forEach(po -> System.out.println(po.getId()));
		System.err.println("***************");

		for (FinancialReceiptPO po : actual) {
			boolean flag = false;
			for (FinancialReceiptPO po2 : expect) {
				if (po.getId().equals(po2.getId())) {
					flag = true;
					break;
				}
			}
			assertEquals(true, flag);
		}

		/*
		 * the FinancialReceiptPOs with specific FinancialReceiptType
		 */
		FinancialReceiptType financialReceiptType = FinancialReceiptType.CASH_CLAIM;
		expect = list.parallelStream().filter(po -> po.getFinancialReceiptType().equals(financialReceiptType))
				.collect(Collectors.toCollection(ArrayList::new));
		actual = test.findByFinancialReceipType(financialReceiptType);
		/*
		 * output to console
		 */
		System.err.println("*****Expecting*****");
		expect.forEach(po -> System.out.println(po.getId()));
		System.err.println("*****Actual*****");
		actual.forEach(po -> System.out.println(po.getId()));
		System.err.println("***************");

		for (FinancialReceiptPO po : actual) {
			boolean flag = false;
			for (FinancialReceiptPO po2 : expect) {
				if (po.getId().equals(po2.getId())) {
					flag = true;
					break;
				}
			}
			assertEquals(true, flag);
		}

		/*
		 * the FinancialReceiptPOs with specific userID
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
			for (FinancialReceiptPO po : actual) {
				boolean flag = false;
				for (FinancialReceiptPO po2 : expect) {
					if (po.getId().equals(po2.getId())) {
						flag = true;
						break;
					}
				}
				assertEquals(true, flag);
			}

		/*
		 * the FinancialReceiptPOs with specific date
		 */
		Date from = FinancialReceiptPO.SAMPLE_1.getDate();
		Date to = new Date(FinancialReceiptPO.SAMPLE_5.getDate().getTime());
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

			for (FinancialReceiptPO po : actual) {
				boolean flag = false;
				for (FinancialReceiptPO po2 : expect) {
					if (po.getId().equals(po2.getId())) {
						flag = true;
						break;
					}
				}
				assertEquals(true, flag);
			}

		/*
		 * the FinancialReceiptPOs with specific ReceiptState
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
			for (FinancialReceiptPO po : actual) {
				boolean flag = false;
				for (FinancialReceiptPO po2 : expect) {
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
		FinancialReceiptPO.SAMPLE_1.setFinancialReceiptType(FinancialReceiptType.BILL);
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_1));
		assertEquals("00002", test.getIDSuffix(FinancialReceiptPO.SAMPLE_1.getDate(),
				FinancialReceiptPO.SAMPLE_1.getFinancialReceiptType()));
		/*
		 * two data, test getIDSuffix, insert a data with different
		 * FinancailReceiptType and same date, the actual result of getIDSuffix
		 * should be the same
		 */
		FinancialReceiptPO.SAMPLE_2.setFinancialReceiptType(FinancialReceiptType.CASH_CLAIM);
		FinancialReceiptPO.SAMPLE_2.setDate(FinancialReceiptPO.SAMPLE_1.getDate());
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_2));
		assertEquals("00002", test.getIDSuffix(FinancialReceiptPO.SAMPLE_1.getDate(),
				FinancialReceiptPO.SAMPLE_1.getFinancialReceiptType()));

		/*
		 * three data, test getIDSuffix, insert a data with same
		 * FinancialReceiptType and same date, the actual result of getIDSuffix
		 * should be increased
		 */
		FinancialReceiptPO.SAMPLE_3.setFinancialReceiptType(FinancialReceiptType.BILL);
		FinancialReceiptPO.SAMPLE_3.setDate(FinancialReceiptPO.SAMPLE_1.getDate());
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_3));
		assertEquals("00003", test.getIDSuffix(FinancialReceiptPO.SAMPLE_1.getDate(),
				FinancialReceiptPO.SAMPLE_1.getFinancialReceiptType()));

		/*
		 * four data, test getIDSuffix, insert a data with same
		 * FinancialReceiptType but different date, the actual result of
		 * getIDSuffix should be the same as last result
		 */
		FinancialReceiptPO.SAMPLE_4.setFinancialReceiptType(FinancialReceiptType.BILL);
		FinancialReceiptPO.SAMPLE_4.setDate(new Date(new Date().getTime() - 999999999));
		assertEquals(ResultMessage.SUCCESS, test.insert(FinancialReceiptPO.SAMPLE_4));
		assertEquals("00003", test.getIDSuffix(FinancialReceiptPO.SAMPLE_1.getDate(),
				FinancialReceiptPO.SAMPLE_1.getFinancialReceiptType()));

	}

	/*
	 * delete all data in database after every test
	 */
	@After
	public void deleteAll() {
		ArrayList<FinancialReceiptPO> list = test.getAllList();
		System.out.println("Database has : ");
		list.forEach(po -> System.out.println(po.getId()));
		if (!list.isEmpty()) {
			for (FinancialReceiptPO po : list) {
				assertEquals(ResultMessage.SUCCESS, test.delete(po.getId()));
			}
		}
	}
}
