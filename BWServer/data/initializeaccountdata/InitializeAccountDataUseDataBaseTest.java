package initializeaccountdata;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import PO.BankAccountPO;
import PO.InitializeAccountPO;
import bankaccountdata.BankAccountDataUseDataBase;
import enums.ResultMessage;
import persistence.hibernate.implement.IOStrategyUseHibernate;

public class InitializeAccountDataUseDataBaseTest {
	InitializeAccountDataUseDataBase test = new InitializeAccountDataUseDataBase();
	IOStrategyUseHibernate<InitializeAccountPO> directAccess = new IOStrategyUseHibernate<>();

	/*
	 * delete all data in database before test
	 */
	@Before
	@Test
	public void deleteAll() {
		ArrayList<InitializeAccountPO> list = (ArrayList<InitializeAccountPO>) test.getAllList();
		if (list != null && !list.isEmpty()) {
			System.out.println("Database has : ");
			list.forEach(po -> System.out.println(po.getId()));
		}
		if (list != null && !list.isEmpty()) {
			for (InitializeAccountPO po : list) {
				assertEquals(true, directAccess.deleteOne(po));
			}
		}
	}

	@Test
	public void testCase_1() {
		assertEquals(ResultMessage.SUCCESS, test.insert(InitializeAccountPO.SAMPLE_1));
		assertEquals(ResultMessage.SUCCESS, test.insert(InitializeAccountPO.SAMPLE_2));
		assertEquals(ResultMessage.SUCCESS, test.insert(InitializeAccountPO.SAMPLE_3));
		assertEquals(ResultMessage.SUCCESS, test.insert(InitializeAccountPO.SAMPLE_4));
		assertEquals(ResultMessage.SUCCESS, test.insert(InitializeAccountPO.SAMPLE_5));
	}
}
