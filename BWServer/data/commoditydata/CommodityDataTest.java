package commoditydata;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import PO.CommodityPO;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;
import persistence.txt.service.IOStrategy;

public class CommodityDataTest {
	private CommodityDataUseTxt commodityData = new CommodityDataUseTxt();

	@Test
	public void testInsertDelete() {
		// test insert success
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_1));
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_2));
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_3));

		// test insert repeated
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED, commodityData.insert(CommodityPO.SAMPLE_1));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED, commodityData.insert(CommodityPO.SAMPLE_2));

		// test delete not found
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, commodityData.delete(CommodityPO.SAMPLE_5.getId()));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, commodityData.delete(CommodityPO.SAMPLE_4.getId()));

		// test delete success
		assertEquals(ResultMessage.COMMODITY_DELETE_SUCCESS, commodityData.delete(CommodityPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.COMMODITY_DELETE_SUCCESS, commodityData.delete(CommodityPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.COMMODITY_DELETE_SUCCESS, commodityData.delete(CommodityPO.SAMPLE_3.getId()));
	}

	@Test
	public void testUpdate() {
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_1));
		assertEquals(ResultMessage.COMMODITY_UPDATE_SUCCESS,
				commodityData.update(CommodityPO.SAMPLE_1.getId(), CommodityPO.SAMPLE_2));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND,
				commodityData.update(CommodityPO.SAMPLE_3.getId(), CommodityPO.SAMPLE_4));
	}

	@Test
	public void testFindByID() {
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_1));
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_5));
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_4));

		assertEquals(CommodityPO.SAMPLE_1, commodityData.findByID(CommodityPO.SAMPLE_1.getId()));
		assertEquals(CommodityPO.SAMPLE_5, commodityData.findByID(CommodityPO.SAMPLE_5.getId()));
		assertEquals(CommodityPO.SAMPLE_4, commodityData.findByID(CommodityPO.SAMPLE_4.getId()));

		assertEquals(ResultMessage.COMMODITY_DELETE_SUCCESS, commodityData.delete(CommodityPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.COMMODITY_DELETE_SUCCESS, commodityData.delete(CommodityPO.SAMPLE_4.getId()));
		assertEquals(ResultMessage.COMMODITY_DELETE_SUCCESS, commodityData.delete(CommodityPO.SAMPLE_5.getId()));
	}

	@Test
	public void testGetAllList() {
		ArrayList<CommodityPO> commodityPOs = new ArrayList<>();
		commodityPOs.add(CommodityPO.SAMPLE_1);
		commodityPOs.add(CommodityPO.SAMPLE_2);
		commodityPOs.add(CommodityPO.SAMPLE_3);

		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_1));
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_2));
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_3));

		assertEquals(commodityPOs, commodityData.getAllList());
	}

	@Test
	public void testGetIDSuffix() {
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_1));
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_2));
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_3));

		assertEquals(3, commodityData.getIDSuffix());

	}

	@Test
	public void testFindByKeyWord() {
		ArrayList<CommodityPO> keyword1 = new ArrayList<>();
		keyword1.add(CommodityPO.SAMPLE_2);
		keyword1.add(CommodityPO.SAMPLE_1);

		ArrayList<CommodityPO> keyword2 = new ArrayList<>();
		keyword2.add(CommodityPO.SAMPLE_4);
		keyword2.add(CommodityPO.SAMPLE_3);

		ArrayList<CommodityPO> keyword3 = new ArrayList<>();
		keyword3.add(CommodityPO.SAMPLE_5);

		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_1));
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_2));
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_3));
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_4));
		assertEquals(ResultMessage.COMMODITY_INSERT_SUCCESS, commodityData.insert(CommodityPO.SAMPLE_5));

		// System.err.println(keyword1.toString());
		// System.err.println(commodityData.findWithKeyword("Keyword_1").toString());
		// System.err.println(keyword2.toString());
		// System.err.println(commodityData.findWithKeyword("Keyword_2").toString());
		// System.err.println(keyword3.toString());
		// System.err.println(commodityData.findWithKeyword("Keyword_3").toString());
		assertEquals(keyword1.toString(), commodityData.findWithKeyword("Keyword_1").toString());
		assertEquals(keyword2.toString(), commodityData.findWithKeyword("Keyword_2").toString());
		assertEquals(keyword3.toString(), commodityData.findWithKeyword("Keyword_3").toString());
	}
}
