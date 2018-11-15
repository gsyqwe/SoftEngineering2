package export.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import PO.CommodityPO;
import VO.BusinessConditionVO;
import VO.CommodityVO;
import VO.SalesDetailVO;
import export.interpreter.ExportInterpreter;

public class ExportTest {

	@Test
	public void testExportBusinessConditionVO() {
		BusinessConditionVO vo = new BusinessConditionVO();
		vo.setBuySaleDifferenceIncome(100);
		vo.setCommidityCostChangeIncome(120);
		vo.setCommidityGiftCost(786);
		vo.setCommidityLostCost(8976);
		vo.setCommidityOverFlowIncome(987);
		vo.setIncomeAfterdiscount(687);
		vo.setVoucherActualDifferenceIncome(7689);
		vo.setSumOfDiscount(123);
		vo.setSumOfCost(8712);
		vo.setSaleIncome(14323);
		vo.setSaleCost(321456);
		BusinessConditionVO vo2 = new BusinessConditionVO();
		vo2.setBuySaleDifferenceIncome(100);
		vo2.setCommidityCostChangeIncome(120);
		vo2.setCommidityGiftCost(786);
		vo2.setCommidityLostCost(8976);
		vo2.setCommidityOverFlowIncome(987);
		vo2.setIncomeAfterdiscount(687);
		vo2.setVoucherActualDifferenceIncome(7689);
		vo2.setSumOfDiscount(123);
		vo2.setSumOfCost(8712);
		vo2.setSaleIncome(14323);
		vo2.setSaleCost(321456);
		ArrayList<BusinessConditionVO> list = new ArrayList<>();
		list.add(vo);
		list.add(vo2);
		ExportInterpreter<BusinessConditionVO> interpreter = new ExportInterpreter<>(
				list.toArray(new BusinessConditionVO[1]));
		// ExportInterpreter<BusinessConditionVO> interpreter = new
		// ExportInterpreter<BusinessConditionVO>(list);
		Export export = new Export(interpreter.getExportContent(), "BusinessCondition.xlsx");
		export.export();
	}

	@Test
	public void testExportCommodityVO() {
		ArrayList<CommodityVO> list = new ArrayList<>();
		list.add(CommodityPO.SAMPLE_1.toVO());
		list.add(CommodityPO.SAMPLE_2.toVO());
		list.add(CommodityPO.SAMPLE_3.toVO());
		list.add(CommodityPO.SAMPLE_4.toVO());
		ExportInterpreter<CommodityVO> interpreter = new ExportInterpreter<CommodityVO>(
				list.toArray(new CommodityVO[1]));
		Export export = new Export(interpreter.getExportContent(), "Commodity.xlsx");
		export.export();
	}

	@Test
	public void testExportSalesDetail() {
		List<SalesDetailVO> list = new LinkedList<>();
		SalesDetailVO salesDetailVO_1 = new SalesDetailVO(new Date(new Date().getTime() - 124111), "commodity_1",
				"version_1", 100, 99.9, 999);
		SalesDetailVO salesDetailVO_2 = new SalesDetailVO(new Date(new Date().getTime() - 8976567), "commodity_2",
				"version_2", 100, 88.8, 888);
		SalesDetailVO salesDetailVO_3 = new SalesDetailVO(new Date(new Date().getTime() - 2544356), "commodity_3",
				"version_3", 100, 12, 1200);
		SalesDetailVO salesDetailVO_4 = new SalesDetailVO(new Date(new Date().getTime() - 21345), "commodity_4",
				"version_4", 100, 14, 1400);
		SalesDetailVO salesDetailVO_5 = new SalesDetailVO(new Date(new Date().getTime() - 2152354), "commodity_5",
				"version_5", 100, 144, 14000);
		SalesDetailVO salesDetailVO_6 = new SalesDetailVO(new Date(new Date().getTime() - 1248987), "commodity_6",
				"version_6", 100, 80, 8000);
		list.add(salesDetailVO_1);
		list.add(salesDetailVO_2);
		list.add(salesDetailVO_3);
		list.add(salesDetailVO_4);
		list.add(salesDetailVO_5);
		list.add(salesDetailVO_6);
		ExportInterpreter<SalesDetailVO> interpreter = new ExportInterpreter<>(list.toArray(new SalesDetailVO[0]));
		Export export = new Export(interpreter.getExportContent(), "SalesDetail.xlsx");
		export.export();
	}
}
