package inventorybl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import VO.RecordVO;
import enums.ResultMessage;
import enums.StockInAndOut;

public class InventoryControllerTest {
	public static InventoryController in;

	@Before
	public void setUp() throws Exception {
		in=new InventoryController();
	}

	@Test
	public void testShowRecord() throws RemoteException, ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<RecordVO> vo=in.showRecord(df.parse("2018-01-03"), df.parse("2018-01-04"));
		for(RecordVO temp:vo){
			System.out.println(temp);	
		}
	}

	@Test
	public void testAddRecord() throws RemoteException {
		RecordVO vo=new RecordVO();
		vo.setAmount(100);
		vo.setCommodityID("COM-20180103-00001");
		vo.setQuantity(5);
		vo.setStockInAndOutTime(new Date());
		vo.setStockInAndOutType(StockInAndOut.STOCK_IN);
		assertEquals(ResultMessage.SUCCESS,in.addRecord(vo));
	}

}
