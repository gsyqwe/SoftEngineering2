package persistence.txt.format;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import PO.BankAccountPO;
import PO.CategoryPO;
import PO.CommodityPO;
import PO.FinancialReceiptPO;
import PO.InitializeAccountPO;
import PO.MemberPO;
import PO.UserPO;
import PO.VoucherPO;

public class JsonFormatTest {
	JsonFormat jsonFormat = new JsonFormat();

	@Test
	public void testCase_1() {
		String jsString_1 = jsonFormat.format(UserPO.SAMPLE_1);
		String jsString_2 = jsonFormat.format(BankAccountPO.SAMPLE_1);
		String jsString_3 = jsonFormat.format(MemberPO.SAMPLE_1);
		String jsString_4 = jsonFormat.format(CommodityPO.SAMPLE_1);
		String jsString_5 = jsonFormat.format(VoucherPO.SAMPLE_1);

		assertEquals(UserPO.SAMPLE_1.toString(), jsonFormat.unFormat(jsString_1, UserPO.class).toString());
		assertEquals(BankAccountPO.SAMPLE_1.toString(),
				jsonFormat.unFormat(jsString_2, BankAccountPO.class).toString());
		assertEquals(MemberPO.SAMPLE_1.toString(), jsonFormat.unFormat(jsString_3, MemberPO.class).toString());
		assertEquals(CommodityPO.SAMPLE_1.toString(), jsonFormat.unFormat(jsString_4, CommodityPO.class).toString());
		assertEquals(VoucherPO.SAMPLE_1.toString(), jsonFormat.unFormat(jsString_5, VoucherPO.class).toString());
	}

	@Test
	public void testCase_2() {
		String jsString_1 = jsonFormat.format(InitializeAccountPO.SAMPLE_1);
		String jsString_2 = jsonFormat.format(FinancialReceiptPO.SAMPLE_2);
		String jsString_3 = jsonFormat.format(CategoryPO.SAMPLE_3);

		assertEquals(InitializeAccountPO.SAMPLE_1.toString(), jsonFormat.unFormat(jsString_1, InitializeAccountPO.class).toString());
		assertEquals(FinancialReceiptPO.SAMPLE_2.toString(),
				jsonFormat.unFormat(jsString_2, FinancialReceiptPO.class).toString());
		assertEquals(CategoryPO.SAMPLE_3.toString(), jsonFormat.unFormat(jsString_3, CategoryPO.class).toString());

	}
}
