package persistence.txt.implement;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.junit.Test;

import PO.BankAccountPO;
import PO.CategoryPO;
import PO.CommodityPO;
import PO.EmailPO;
import PO.FinancialReceiptPO;
import PO.InitializeAccountPO;
import PO.InventoryPO;
import PO.InventoryReceiptPO;
import PO.LogPO;
import PO.MemberPO;
import PO.PromotionByCombinationListPO;
import PO.PromotionByLevelPO;
import PO.PromotionBySumPO;
import PO.RecordPO;
import PO.SalesReceiptPO;
import PO.UserPO;
import PO.VoucherPO;
import persistence.txt.service.IOStrategy;

public class IOStrategyUseTxtTest {

	@Test
	public void testOutAll() {
		IOStrategyUseTxt<BankAccountPO> ioStrategyUseTxt = new IOStrategyUseTxt<>("BankAccountPO");
		ioStrategyUseTxt.outAll(BankAccountPO.class);

		IOStrategyUseTxt<CommodityPO> ioStrategyUseTxt2 = new IOStrategyUseTxt<>("CommodityPO");
		ioStrategyUseTxt2.outAll(CommodityPO.class);

		IOStrategyUseTxt<UserPO> ioStrategyUseTxt3 = new IOStrategyUseTxt<>("UserPO");
		ioStrategyUseTxt3.outAll(UserPO.class);

		IOStrategyUseTxt<VoucherPO> ioStrategyUseTxt4 = new IOStrategyUseTxt<>("VoucherPO");
		ioStrategyUseTxt4.outAll(VoucherPO.class);

		IOStrategyUseTxt<MemberPO> ioStrategyUseTxt5 = new IOStrategyUseTxt<>("MemberPO");
		ioStrategyUseTxt5.outAll(MemberPO.class);

		IOStrategyUseTxt<CategoryPO> ioStrategyUseTxt6 = new IOStrategyUseTxt<>("CategoryPO");
		ioStrategyUseTxt6.outAll(CategoryPO.class);

		IOStrategyUseTxt<EmailPO> ioStrategyUseTxt8 = new IOStrategyUseTxt<>("EmailPO");
		ioStrategyUseTxt8.outAll(EmailPO.class);

		IOStrategyUseTxt<InventoryPO> ioStrategyUseTxt9 = new IOStrategyUseTxt<>("InventoryPO");
		ioStrategyUseTxt9.outAll(InventoryPO.class);

		IOStrategyUseTxt<LogPO> ioStrategyUseTxt10 = new IOStrategyUseTxt<>("LogPO");
		ioStrategyUseTxt10.outAll(LogPO.class);

		IOStrategyUseTxt<RecordPO> ioStrategyUseTxt11 = new IOStrategyUseTxt<>("RecordPO");
		ioStrategyUseTxt11.outAll(RecordPO.class);

		IOStrategyUseTxt<FinancialReceiptPO> ioStrategyUseTxt12 = new IOStrategyUseTxt<>("FinancialReceiptPO");
		ioStrategyUseTxt12.outAll(FinancialReceiptPO.class);

		IOStrategyUseTxt<InventoryReceiptPO> ioStrategyUseTxt13 = new IOStrategyUseTxt<>("InventoryReceiptPO");
		ioStrategyUseTxt13.outAll(InventoryReceiptPO.class);

		IOStrategyUseTxt<SalesReceiptPO> ioStrategyUseTxt14 = new IOStrategyUseTxt<>("SalesReceiptPO");
		ioStrategyUseTxt14.outAll(SalesReceiptPO.class);

		IOStrategyUseTxt<PromotionByCombinationListPO> ioStrategyUseTxt15 = new IOStrategyUseTxt<>(
				"PromotionByCombinationListPO");
		ioStrategyUseTxt15.outAll(PromotionByCombinationListPO.class);

		IOStrategyUseTxt<PromotionBySumPO> ioStrategyUseTxt16 = new IOStrategyUseTxt<>("PromotionBySumPO");
		ioStrategyUseTxt16.outAll(PromotionBySumPO.class);

		IOStrategyUseTxt<InitializeAccountPO> ioStrategyUseTxt17 = new IOStrategyUseTxt<>("InitializeAccountPO");
		ioStrategyUseTxt17.outAll(InitializeAccountPO.class);

		IOStrategyUseTxt<PromotionByLevelPO> ioStrategyUseTxt7 = new IOStrategyUseTxt<>("PromotionByLevelPO");
		ioStrategyUseTxt7.outAll(PromotionByLevelPO.class);
	}

	public static void main(String[] args) {
		IOStrategyUseTxt<BankAccountPO> ioStrategyUseTxt = new IOStrategyUseTxt<>("BankAccountPO");
		ioStrategyUseTxt.inOne(BankAccountPO.SAMPLE_1);
		ioStrategyUseTxt.inOne(BankAccountPO.SAMPLE_2);
		ioStrategyUseTxt.inOne(BankAccountPO.SAMPLE_3);
		ioStrategyUseTxt.inOne(BankAccountPO.SAMPLE_4);

		IOStrategyUseTxt<CommodityPO> ioStrategyUseTxt2 = new IOStrategyUseTxt<>("CommodityPO");
		CommodityPO.SAMPLE_1.setId("Tesing");
		ioStrategyUseTxt2.inOne(CommodityPO.SAMPLE_1);
		ioStrategyUseTxt2.inOne(CommodityPO.SAMPLE_2);
		ioStrategyUseTxt2.inOne(CommodityPO.SAMPLE_3);
		ioStrategyUseTxt2.inOne(CommodityPO.SAMPLE_4);

		IOStrategyUseTxt<UserPO> ioStrategyUseTxt3 = new IOStrategyUseTxt<>("UserPO");
		ioStrategyUseTxt3.inOne(UserPO.SAMPLE_1);
		ioStrategyUseTxt3.inOne(UserPO.SAMPLE_2);
		ioStrategyUseTxt3.inOne(UserPO.SAMPLE_3);

		IOStrategyUseTxt<VoucherPO> ioStrategyUseTxt4 = new IOStrategyUseTxt<>("VoucherPO");
		ioStrategyUseTxt4.inOne(VoucherPO.SAMPLE_1);
		ioStrategyUseTxt4.inOne(VoucherPO.SAMPLE_2);
		ioStrategyUseTxt4.inOne(VoucherPO.SAMPLE_3);

		IOStrategyUseTxt<MemberPO> ioStrategyUseTxt5 = new IOStrategyUseTxt<>("MemberPO");
		ioStrategyUseTxt5.inOne(MemberPO.SAMPLE_1);

		IOStrategyUseTxt<CategoryPO> ioStrategyUseTxt6 = new IOStrategyUseTxt<>("CategoryPO");
		ioStrategyUseTxt6.inOne(CategoryPO.SAMPLE_1);
		System.err.println(CategoryPO.SAMPLE_1);
		ioStrategyUseTxt6.inOne(CategoryPO.SAMPLE_2);
		ioStrategyUseTxt6.inOne(CategoryPO.SAMPLE_3);
		ioStrategyUseTxt6.inOne(CategoryPO.SAMPLE_4);
		ioStrategyUseTxt6.inOne(CategoryPO.SAMPLE_5);

		//// IOStrategyUseTxt<CategoryOfNodePO> ioStrategyUseTxt7 =
		//// new IOStrategyUseTxt<>();
		//// ioStrategyUseTxt7.inOne(CategoryOfNodePO.SAMPLE_1);
		//// ioStrategyUseTxt7.inOne(CategoryOfNodePO.SAMPLE_2);
		//// ioStrategyUseTxt7.inOne(CategoryOfNodePO.SAMPLE_3);

		IOStrategyUseTxt<EmailPO> ioStrategyUseTxt8 = new IOStrategyUseTxt<>("EmailPO");
		ioStrategyUseTxt8.inOne(EmailPO.SAMPLE_1);
		ioStrategyUseTxt8.inOne(EmailPO.SAMPLE_2);
		ioStrategyUseTxt8.inOne(EmailPO.SAMPLE_3);
		ioStrategyUseTxt8.inOne(EmailPO.SAMPLE_4);
		ioStrategyUseTxt8.inOne(EmailPO.SAMPLE_5);

		IOStrategyUseTxt<InventoryPO> ioStrategyUseTxt9 = new IOStrategyUseTxt<>("InventoryPO");
		ioStrategyUseTxt9.inOne(InventoryPO.SAMPLE_1);
		ioStrategyUseTxt9.inOne(InventoryPO.SAMPLE_2);
		ioStrategyUseTxt9.inOne(InventoryPO.SAMPLE_3);
		ioStrategyUseTxt9.inOne(InventoryPO.SAMPLE_4);
		ioStrategyUseTxt9.inOne(InventoryPO.SAMPLE_5);

		IOStrategyUseTxt<LogPO> ioStrategyUseTxt10 = new IOStrategyUseTxt<>("LogPO");
		ioStrategyUseTxt10.inOne(LogPO.SAMPLE_1);
		ioStrategyUseTxt10.inOne(LogPO.SAMPLE_2);
		ioStrategyUseTxt10.inOne(LogPO.SAMPLE_3);
		ioStrategyUseTxt10.inOne(LogPO.SAMPLE_4);
		ioStrategyUseTxt10.inOne(LogPO.SAMPLE_5);

		IOStrategyUseTxt<RecordPO> ioStrategyUseTxt11 = new IOStrategyUseTxt<>("RecordPO");
		ioStrategyUseTxt11.inOne(RecordPO.SAMPLE_1);
		ioStrategyUseTxt11.inOne(RecordPO.SAMPLE_2);
		ioStrategyUseTxt11.inOne(RecordPO.SAMPLE_3);
		ioStrategyUseTxt11.inOne(RecordPO.SAMPLE_4);
		ioStrategyUseTxt11.inOne(RecordPO.SAMPLE_5);
		//
		// // System.err.println("what the fuck");
		//
		IOStrategyUseTxt<FinancialReceiptPO> ioStrategyUseTxt12 = new IOStrategyUseTxt<>("FinancialReceiptPO");
		// System.err.println(FinancialReceiptPO.SAMPLE_1);
		// // System.err.println(FinancialReceiptPO.SAMPLE_2);
		// // System.err.println(FinancialReceiptPO.SAMPLE_3);
		// // System.err.println(FinancialReceiptPO.SAMPLE_4);

		ioStrategyUseTxt12.inOne(FinancialReceiptPO.SAMPLE_1);
		ioStrategyUseTxt12.inOne(FinancialReceiptPO.SAMPLE_2);
		ioStrategyUseTxt12.inOne(FinancialReceiptPO.SAMPLE_3);
		ioStrategyUseTxt12.inOne(FinancialReceiptPO.SAMPLE_4);
		ioStrategyUseTxt12.inOne(FinancialReceiptPO.SAMPLE_5);

		IOStrategyUseTxt<InventoryReceiptPO> ioStrategyUseTxt13 = new IOStrategyUseTxt<>("InventoryReceiptPO");
		ioStrategyUseTxt13.inOne(InventoryReceiptPO.SAMPLE_1);
		ioStrategyUseTxt13.inOne(InventoryReceiptPO.SAMPLE_2);
		ioStrategyUseTxt13.inOne(InventoryReceiptPO.SAMPLE_3);
		ioStrategyUseTxt13.inOne(InventoryReceiptPO.SAMPLE_4);
		ioStrategyUseTxt13.inOne(InventoryReceiptPO.SAMPLE_5);

		IOStrategyUseTxt<SalesReceiptPO> ioStrategyUseTxt14 = new IOStrategyUseTxt<>("SalesReceiptPO");
		ioStrategyUseTxt14.inOne(SalesReceiptPO.SAMPLE_1);
		ioStrategyUseTxt14.inOne(SalesReceiptPO.SAMPLE_2);
		ioStrategyUseTxt14.inOne(SalesReceiptPO.SAMPLE_3);
		ioStrategyUseTxt14.inOne(SalesReceiptPO.SAMPLE_4);
		ioStrategyUseTxt14.inOne(SalesReceiptPO.SAMPLE_5);

		IOStrategyUseTxt<PromotionByCombinationListPO> ioStrategyUseTxt15 = new IOStrategyUseTxt<>(
				"PromotionByCombinationListPO");
		ioStrategyUseTxt15.inOne(PromotionByCombinationListPO.SAMPLE_1);
		ioStrategyUseTxt15.inOne(PromotionByCombinationListPO.SAMPLE_2);
		ioStrategyUseTxt15.inOne(PromotionByCombinationListPO.SAMPLE_3);
		ioStrategyUseTxt15.inOne(PromotionByCombinationListPO.SAMPLE_4);
		ioStrategyUseTxt15.inOne(PromotionByCombinationListPO.SAMPLE_5);

		IOStrategyUseTxt<PromotionBySumPO> ioStrategyUseTxt16 = new IOStrategyUseTxt<>("PromotionBySumPO");
		ioStrategyUseTxt16.inOne(PromotionBySumPO.SAMPLE_1);
		ioStrategyUseTxt16.inOne(PromotionBySumPO.SAMPLE_2);
		ioStrategyUseTxt16.inOne(PromotionBySumPO.SAMPLE_3);
		ioStrategyUseTxt16.inOne(PromotionBySumPO.SAMPLE_4);
		ioStrategyUseTxt16.inOne(PromotionBySumPO.SAMPLE_5);

		IOStrategyUseTxt<InitializeAccountPO> ioStrategyUseTxt17 = new IOStrategyUseTxt<>("InitializeAccountPO");
		ioStrategyUseTxt17.inOne(InitializeAccountPO.SAMPLE_1);
		ioStrategyUseTxt17.inOne(InitializeAccountPO.SAMPLE_2);
		ioStrategyUseTxt17.inOne(InitializeAccountPO.SAMPLE_3);
		ioStrategyUseTxt17.inOne(InitializeAccountPO.SAMPLE_4);
		ioStrategyUseTxt17.inOne(InitializeAccountPO.SAMPLE_5);

		IOStrategyUseTxt<PromotionByLevelPO> ioStrategyUseTxt7 = new IOStrategyUseTxt<>("PromotionByLevelPO");
		ioStrategyUseTxt7.inOne(PromotionByLevelPO.SAMPLE_1);
		ioStrategyUseTxt7.inOne(PromotionByLevelPO.SAMPLE_2);
		ioStrategyUseTxt7.inOne(PromotionByLevelPO.SAMPLE_3);
		ioStrategyUseTxt7.inOne(PromotionByLevelPO.SAMPLE_4);
		ioStrategyUseTxt7.inOne(PromotionByLevelPO.SAMPLE_5);
	}

	@Test
	public void test_01() {
		IOStrategy<Person> ioStrategy = new IOStrategyUseTxt<Person>("Txt_test_01");
		Person father = new Person("Tom", "China", 20, 0);
		Person mother = new Person("Cathy", "China", 19, 10000);
		assertEquals(true, ioStrategy.inOne(father));
		// assertEquals(false, ioStrategy.inOne(father));
		assertEquals(true, ioStrategy.inOne(mother));
		ioStrategy.replaceAll(Collections.emptyIterator());
	}

	@Test
	public void test_02() {
		IOStrategy<Person> ioStrategy = new IOStrategyUseTxt<Person>("Txt_test_02");
		Person father = new Person("Tom", "China", 20, 0);
		Person mother = new Person("Cathy", "China", 19, 10000);
		ArrayList<Person> persons = new ArrayList<>();
		persons.add(father);
		persons.add(mother);
		assertEquals(true, ioStrategy.inAll(persons.iterator()));

		Iterator<Person> result = ioStrategy.outAll(Person.class);
		ArrayList<Person> resultList = new ArrayList<>();
		while (result.hasNext()) {
			resultList.add(result.next());
		}

		assertEquals(persons.toString(), resultList.toString());

		ioStrategy.replaceAll(Collections.emptyIterator());
	}

	@Test
	public void test_03() {
		IOStrategy<UserPO> ioStrategy = new IOStrategyUseTxt<UserPO>("User_Test");
		ioStrategy.outAll(UserPO.class);
	}

	@Test
	public void test_04() {
		IOStrategy<Person> ioStrategy = new IOStrategyUseTxt("Change_T_Test");
		ioStrategy.inOne(new Person("Fancie", "DC", 66, 2000));
		// ioStrategy = (IOStrategy<Apartmemt>) ioStrategy;
	}

	private class Person {
		String name;
		String address;
		ArrayList<Person> friends;
		int age;
		double momeyLeft;

		public Person(String name, String address, int age, double momeyLeft) {
			super();
			friends = new ArrayList<>();
			this.name = name;
			this.address = address;
			this.age = age;
			this.momeyLeft = momeyLeft;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", address=" + address + ", friends=" + friends + ", age=" + age
					+ ", momeyLeft=" + momeyLeft + "]";
		}
	}

	private class Apartmemt {
		String address;
		double area;
		Boolean isAnyone;

		@Override
		public String toString() {
			return "Apartmemt [address=" + address + ", area=" + area + ", isAnyone=" + isAnyone + "]";
		}

		public Apartmemt(String address, double area, Boolean isAnyone) {
			super();
			this.address = address;
			this.area = area;
			this.isAnyone = isAnyone;
		}

	}
}
