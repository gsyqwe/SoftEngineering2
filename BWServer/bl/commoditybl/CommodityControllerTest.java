package commoditybl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import VO.CommodityVO;
import VO.StockInAndOutVO;
import enums.ResultMessage;
import enums.StockInAndOut;

public class CommodityControllerTest {
	CommodityController com;
	@Before
	public void setUp() throws Exception {
		com=new CommodityController();
	}

	@Test
	public void testAddCommodity() throws RemoteException {
		CommodityVO vo=new CommodityVO();
		vo.setAlertQuantity(10);
		vo.setBid(5);
		vo.setCategory("李泽言/CAT-00007");
		vo.setName("李泽言的灯1");
		vo.setQuantity(20);
		vo.setRecentBid(5);
		vo.setRecentRetailPrice(10);
		vo.setRetailPrice(10);
		vo.setVersion("X100");
		assertEquals(ResultMessage.SUCCESS,com.addCommodity(vo));
		
	}

	@Test
	public void testModifyCommodity() throws RemoteException {
		CommodityVO vo=new CommodityVO();
		vo.setAlertQuantity(10);
		vo.setBid(5);
		vo.setCategory("李泽言/CAT-00007");
		vo.setName("李泽言的灯1");
		vo.setQuantity(0);
		vo.setRecentBid(5);
		vo.setRecentRetailPrice(10);
		vo.setRetailPrice(10);
		vo.setVersion("X100");
		vo.setId("COM-20180106-00006");
		assertEquals(ResultMessage.SUCCESS,com.modifyCommodity(vo));
	}

	@Test
	public void testDeleteCommodity() throws RemoteException {
		String id="COM-20180106-00006";
		assertEquals(ResultMessage.SUCCESS,com.deleteCommodity(id));
	}

	@Test
	public void testFindByID() throws RemoteException {
		String id="COM-20171207-00002";
		CommodityVO vo=com.findByID(id);
		System.out.println(vo.getName());
		System.out.println(vo.getId());
	}

	@Test
	public void testFindWithKeyWord() throws RemoteException {
		ArrayList<CommodityVO> coms=com.findWithKeyWord("白");
		for(CommodityVO temp:coms)
			System.out.println(temp.getName());
	}

	@Test
	public void testShowCommodityList() throws RemoteException {
		ArrayList<CommodityVO> coms=com.showCommodityList();
		for(CommodityVO temp:coms)
			System.out.println(temp.getName());
	}

	@Test
	public void testStockInAndOut() throws RemoteException {
		StockInAndOutVO vo=new StockInAndOutVO();
		vo.setAmount(20);
		String id="COM-20180105-00001";
		vo.setCommodityID(id);
		vo.setNumber(4);
		vo.setAmount(40);
		vo.setType(StockInAndOut.STOCK_IN);
		assertEquals(ResultMessage.SUCCESS,com.stockInAndOut(vo, null));
	}

}
