package PromotionStrategybl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import PromotionStrategydataService.PromotionStrategydataService;
import VO.LineItemVO;
import VO.PromotionByCombinationListVO;
import VO.PromotionByLevelVO;
import VO.PromotionBySumVO;
import VO.VoucherVO;
import enums.MemberVipLevel;
import enums.PromotionType;
import promotionstrategydata.PromotionStrategyDataUseDataBase;

public class PromotionTest {

	public static void main(String[] args) {
		PromotionTest test = new PromotionTest();
		//test.addPromotionBySumTest();
		//test.getPromotionBySumTest();
		//test.getSuffixTest();
		test.addPromotionByCombinationTest();
	}

	public void getSuffixTest(){
		PromotionStrategydataService data = new PromotionStrategyDataUseDataBase();
		String id = data.getSuffix(PromotionType.PromotionByLevel);
		System.out.println(id);
	}

	public void addPromotionBySumTest() {
		Date endDate = new Date();
		Date begin = getEarlyDate(endDate);
		PromotionByLevelVO promotion = new PromotionByLevelVO();
		promotion.setBeginDate(begin);
		promotion.setEndDate(endDate);
		promotion.setLevel(MemberVipLevel.DIAMOND);
		ArrayList<LineItemVO> items = new ArrayList<>();
		LineItemVO item = new LineItemVO("COM-20171207-00002", 10, 137, "");
		items.add(item);
		promotion.setCommodityVOs(items);
		ArrayList<VoucherVO> vouchers = new ArrayList<>();
		VoucherVO voucher = new VoucherVO(endDate, 10, begin);
		vouchers.add(voucher);
		promotion.setVoucherVOs(vouchers);

		try {
			PromotionStrategyController controller = new PromotionStrategyController();
			System.out.println(controller.completeAddPromotionByLevel(promotion));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addPromotionByCombinationTest() {
		Date endDate = new Date();
		Date begin = getEarlyDate(endDate);
		PromotionByCombinationListVO promotion = new PromotionByCombinationListVO();
		promotion.setBeginTime(begin);
		promotion.setEndTime(endDate);
		ArrayList<LineItemVO> items = new ArrayList<>();
		LineItemVO item = new LineItemVO("COM-20171207-00002", 10, 137, "");
		LineItemVO item2 = new LineItemVO("COM-20171207-00003",10,5,"");

		items.add(item);
		items.add(item2);

		promotion.setCombination(items);
		promotion.setPromotionName("Big Mac");
		promotion.setPriceAfter(1300);

		try {
			PromotionStrategyController controller = new PromotionStrategyController();
			System.out.println(controller.completeAddPromotionByCombinationList(promotion));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addPromotionByLevelTest() {

	}

	public void getPromotionBySumTest(){
		@SuppressWarnings("deprecation")
		Date date = new Date(118,0,4,20,0,0);
		try {
			PromotionStrategyController controller = new PromotionStrategyController();
			ArrayList<PromotionBySumVO> pro = controller.getEffectivePBS(date);
			for(PromotionBySumVO a:pro){
				System.out.println(a.toPO());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Date getEarlyDate(Date nowDate) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(nowDate);
		rightNow.add(Calendar.DATE, -33);// 日期减1年
		Date preDate = rightNow.getTime();
		return preDate;
	}

}
