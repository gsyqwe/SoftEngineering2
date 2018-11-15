package persistence.hibernate.implement;

import static org.junit.Assert.*;

import javax.swing.plaf.TreeUI;

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
import PO.ReceiptPO;
import PO.RecordPO;
import PO.SalesReceiptPO;
import PO.UserPO;
import PO.VoucherPO;
import enums.ResultMessage;

public class IOStrategyUseHibernateTest {
	public static void main(String[] args) {
		// testInOne();
	}

	@Test
	public void testInsertSalesReceipt() {
		IOStrategyUseHibernate<SalesReceiptPO> ioStrategyUseHibernate = new IOStrategyUseHibernate<>();
		assertEquals(true, ioStrategyUseHibernate.inOne(SalesReceiptPO.SAMPLE_1));
	}

	@Test
	public void testDelete() {
		IOStrategyUseHibernate<FinancialReceiptPO> ioStrategyUseHibernate = new IOStrategyUseHibernate<>();
		assertEquals(true, ioStrategyUseHibernate.inOne(FinancialReceiptPO.SAMPLE_1));
		assertEquals(true, ioStrategyUseHibernate.inOne(FinancialReceiptPO.SAMPLE_2));
		assertEquals(true, ioStrategyUseHibernate.deleteOne(FinancialReceiptPO.SAMPLE_1));
		assertEquals(true, ioStrategyUseHibernate.deleteOne(FinancialReceiptPO.SAMPLE_2));

		assertEquals(false, ioStrategyUseHibernate.deleteOne(FinancialReceiptPO.SAMPLE_1));
	}

	@Test
	public void testInOne() {
		IOStrategyUseHibernate<BankAccountPO> ioStrategyUseHibernate = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate.inOne(BankAccountPO.SAMPLE_1);
		ioStrategyUseHibernate.inOne(BankAccountPO.SAMPLE_2);
		ioStrategyUseHibernate.inOne(BankAccountPO.SAMPLE_3);
		ioStrategyUseHibernate.inOne(BankAccountPO.SAMPLE_4);

		IOStrategyUseHibernate<CommodityPO> ioStrategyUseHibernate2 = new IOStrategyUseHibernate<>();
		CommodityPO.SAMPLE_1.setId("Tesing");
		ioStrategyUseHibernate2.inOne(CommodityPO.SAMPLE_1);
		ioStrategyUseHibernate2.inOne(CommodityPO.SAMPLE_2);
		ioStrategyUseHibernate2.inOne(CommodityPO.SAMPLE_3);
		ioStrategyUseHibernate2.inOne(CommodityPO.SAMPLE_4);

		IOStrategyUseHibernate<UserPO> ioStrategyUseHibernate3 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate3.inOne(UserPO.SAMPLE_1);
		ioStrategyUseHibernate3.inOne(UserPO.SAMPLE_2);
		ioStrategyUseHibernate3.inOne(UserPO.SAMPLE_3);

		IOStrategyUseHibernate<VoucherPO> ioStrategyUseHibernate4 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate4.inOne(VoucherPO.SAMPLE_1);
		ioStrategyUseHibernate4.inOne(VoucherPO.SAMPLE_2);
		ioStrategyUseHibernate4.inOne(VoucherPO.SAMPLE_3);
		ioStrategyUseHibernate4.inOne(VoucherPO.SAMPLE_4);
		ioStrategyUseHibernate4.inOne(VoucherPO.SAMPLE_5);
		ioStrategyUseHibernate4.inOne(VoucherPO.SAMPLE_6);

		IOStrategyUseHibernate<MemberPO> ioStrategyUseHibernate5 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate5.inOne(MemberPO.SAMPLE_1);

		IOStrategyUseHibernate<CategoryPO> ioStrategyUseHibernate6 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate6.inOne(CategoryPO.SAMPLE_1);
		System.err.println(CategoryPO.SAMPLE_1);
		ioStrategyUseHibernate6.inOne(CategoryPO.SAMPLE_2);
		ioStrategyUseHibernate6.inOne(CategoryPO.SAMPLE_3);
		ioStrategyUseHibernate6.inOne(CategoryPO.SAMPLE_4);
		ioStrategyUseHibernate6.inOne(CategoryPO.SAMPLE_5);

		IOStrategyUseHibernate<EmailPO> ioStrategyUseHibernate8 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate8.inOne(EmailPO.SAMPLE_1);
		ioStrategyUseHibernate8.inOne(EmailPO.SAMPLE_2);
		ioStrategyUseHibernate8.inOne(EmailPO.SAMPLE_3);
		ioStrategyUseHibernate8.inOne(EmailPO.SAMPLE_4);
		ioStrategyUseHibernate8.inOne(EmailPO.SAMPLE_5);

		IOStrategyUseHibernate<InventoryPO> ioStrategyUseHibernate9 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate9.inOne(InventoryPO.SAMPLE_1);
		ioStrategyUseHibernate9.inOne(InventoryPO.SAMPLE_2);
		ioStrategyUseHibernate9.inOne(InventoryPO.SAMPLE_3);
		ioStrategyUseHibernate9.inOne(InventoryPO.SAMPLE_4);
		ioStrategyUseHibernate9.inOne(InventoryPO.SAMPLE_5);

		IOStrategyUseHibernate<LogPO> ioStrategyUseHibernate10 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate10.inOne(LogPO.SAMPLE_1);
		ioStrategyUseHibernate10.inOne(LogPO.SAMPLE_2);
		ioStrategyUseHibernate10.inOne(LogPO.SAMPLE_3);
		ioStrategyUseHibernate10.inOne(LogPO.SAMPLE_4);
		ioStrategyUseHibernate10.inOne(LogPO.SAMPLE_5);

		IOStrategyUseHibernate<RecordPO> ioStrategyUseHibernate11 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate11.inOne(RecordPO.SAMPLE_1);
		ioStrategyUseHibernate11.inOne(RecordPO.SAMPLE_2);
		ioStrategyUseHibernate11.inOne(RecordPO.SAMPLE_3);
		ioStrategyUseHibernate11.inOne(RecordPO.SAMPLE_4);
		ioStrategyUseHibernate11.inOne(RecordPO.SAMPLE_5);
		// //
		// // // System.err.println("what the fuck");
		// //
		IOStrategyUseHibernate<ReceiptPO> ioStrategyUseHibernate12 = new IOStrategyUseHibernate<>();

		ioStrategyUseHibernate12.inOne(FinancialReceiptPO.SAMPLE_1);
		ioStrategyUseHibernate12.inOne(FinancialReceiptPO.SAMPLE_2);
		ioStrategyUseHibernate12.inOne(FinancialReceiptPO.SAMPLE_3);
		ioStrategyUseHibernate12.inOne(FinancialReceiptPO.SAMPLE_4);
		ioStrategyUseHibernate12.inOne(FinancialReceiptPO.SAMPLE_5);

		IOStrategyUseHibernate<InventoryReceiptPO> ioStrategyUseHibernate13 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate13.inOne(InventoryReceiptPO.SAMPLE_1);
		ioStrategyUseHibernate13.inOne(InventoryReceiptPO.SAMPLE_2);
		ioStrategyUseHibernate13.inOne(InventoryReceiptPO.SAMPLE_3);
		ioStrategyUseHibernate13.inOne(InventoryReceiptPO.SAMPLE_4);
		ioStrategyUseHibernate13.inOne(InventoryReceiptPO.SAMPLE_5);

		IOStrategyUseHibernate<SalesReceiptPO> ioStrategyUseHibernate14 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate14.inOne(SalesReceiptPO.SAMPLE_1);
		ioStrategyUseHibernate14.inOne(SalesReceiptPO.SAMPLE_2);
		ioStrategyUseHibernate14.inOne(SalesReceiptPO.SAMPLE_3);
		ioStrategyUseHibernate14.inOne(SalesReceiptPO.SAMPLE_4);
		ioStrategyUseHibernate14.inOne(SalesReceiptPO.SAMPLE_5);

		IOStrategyUseHibernate<PromotionByCombinationListPO> ioStrategyUseHibernate15 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate15.inOne(PromotionByCombinationListPO.SAMPLE_1);
		ioStrategyUseHibernate15.inOne(PromotionByCombinationListPO.SAMPLE_2);
		ioStrategyUseHibernate15.inOne(PromotionByCombinationListPO.SAMPLE_3);
		ioStrategyUseHibernate15.inOne(PromotionByCombinationListPO.SAMPLE_4);
		ioStrategyUseHibernate15.inOne(PromotionByCombinationListPO.SAMPLE_5);

		IOStrategyUseHibernate<PromotionBySumPO> ioStrategyUseHibernate16 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate16.inOne(PromotionBySumPO.SAMPLE_1);
		ioStrategyUseHibernate16.inOne(PromotionBySumPO.SAMPLE_2);
		ioStrategyUseHibernate16.inOne(PromotionBySumPO.SAMPLE_3);
		ioStrategyUseHibernate16.inOne(PromotionBySumPO.SAMPLE_4);
		ioStrategyUseHibernate16.inOne(PromotionBySumPO.SAMPLE_5);

		IOStrategyUseHibernate<InitializeAccountPO> ioStrategyUseHibernate17 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate17.inOne(InitializeAccountPO.SAMPLE_1);
		ioStrategyUseHibernate17.inOne(InitializeAccountPO.SAMPLE_2);
		ioStrategyUseHibernate17.inOne(InitializeAccountPO.SAMPLE_3);
		ioStrategyUseHibernate17.inOne(InitializeAccountPO.SAMPLE_4);
		ioStrategyUseHibernate17.inOne(InitializeAccountPO.SAMPLE_5);

		IOStrategyUseHibernate<PromotionByLevelPO> ioStrategyUseHibernate7 = new IOStrategyUseHibernate<>();
		ioStrategyUseHibernate7.inOne(PromotionByLevelPO.SAMPLE_1);
		ioStrategyUseHibernate7.inOne(PromotionByLevelPO.SAMPLE_2);
		ioStrategyUseHibernate7.inOne(PromotionByLevelPO.SAMPLE_3);
		ioStrategyUseHibernate7.inOne(PromotionByLevelPO.SAMPLE_4);
		ioStrategyUseHibernate7.inOne(PromotionByLevelPO.SAMPLE_5);
	}

	@Test
	public void testInAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testOutAll() {
		// IOStrategyUseHibernate<FinancialReceiptPO> ioStrategyUseHibernate =
		// new IOStrategyUseHibernate<>();
		//
		// ioStrategyUseHibernate.outAll(FinancialReceiptPO.class);

		IOStrategyUseHibernate<UserPO> ioStrategyUseHibernate3 = new IOStrategyUseHibernate<>();

		ioStrategyUseHibernate3.outAll(UserPO.class);

		IOStrategyUseHibernate<VoucherPO> ioStrategyUseHibernate4 = new IOStrategyUseHibernate<>();

		ioStrategyUseHibernate4.outAll(VoucherPO.class);
	}

	@Test
	public void testOutOne() {
		IOStrategyUseHibernate<BankAccountPO> tsHibernate = new IOStrategyUseHibernate<>();
		tsHibernate.setSearChCommand("from BankAccountPO bankAccountPO where bankAccountPO.id= ?");
//		assertEquals(true, tsHibernate.inOne(BankAccountPO.SAMPLE_2));
		tsHibernate.setParameter(BankAccountPO.SAMPLE_2.getId());
		System.err.println(tsHibernate.outOne(BankAccountPO.class));
	}

	@Test
	public void testReplaceAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testReplaceOne() {
		fail("Not yet implemented");
	}

}
