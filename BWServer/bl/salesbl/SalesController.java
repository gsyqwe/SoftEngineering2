package salesbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import PO.CommodityPO;
import PO.InventoryReceiptPO;
import PO.LineItemPO;
import PO.MemberPO;
import PO.PromotionByCombinationListPO;
import PO.PromotionByLevelPO;
import PO.PromotionBySumPO;
import PO.SalesReceiptPO;
import PO.VoucherPO;
import PromotionStrategybl.PromotionByCombinationList;
import PromotionStrategybl.PromotionByLevel;
import PromotionStrategybl.PromotionBySum;
import VO.CommodityVO;
import VO.LineItemVO;
import VO.MemberVO;
import VO.SalesReceiptVO;
import enums.InventoryReceiptType;
import enums.MemberType;
import enums.ReceiptState;
import enums.ResultMessage;
import enums.SalesReceiptType;
import inventoryreceiptdata.InventoryReceiptDataUseDataBase;
import inventoryreceiptdataservice.InventoryReceiptDataService;
import salesdata.SalesDataUseDataBase;
import salesdataService.SalesDataService;
import service.SalesblService;

/**
 * 制定单据时候使用的Controller,持有获得当前促销策略，当前可用商品和当前客户的Info，但是因为这只是制定单据，所以没有修改系统数据的权限
 * SalesController直接持有SalesReceiptPO，完成制定销售类单据的职责，而提交后就将此单据的PO设定状态后保存起来
 * SalesReceipt的填写分几个阶段
 * ，首先填写表头信息，然后一行一行的添加商品，如果是销售单据，每添加一件商品，就要根据当前的商品计算一次当前能触发的促销策略
 */

public class SalesController extends UnicastRemoteObject implements
		SalesblService {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	SalesReceiptPO sales;
	PromotionInfo promotionInfo = new PromotionInfoImpl();
	CommodityInfo commodityInfo = new CommodityInfoImpl();
	MemberInfo memberInfo = new MemberInfoImpl();
	SalesDataService dataService = new SalesDataUseDataBase();
	InventoryReceiptDataService receivedDataService = new InventoryReceiptDataUseDataBase();

	public SalesController(SalesReceiptPO sales) throws RemoteException {
		this();
		this.sales = sales;
	}

	@Override
	public SalesReceiptVO getReceiptByID(String id) {
		return dataService.findByID(id).toVO();
	}

	public SalesController() throws RemoteException {
		super();
		sales = new SalesReceiptPO();
	}

	public SalesController(SalesReceiptType type) throws RemoteException {// 根据单据的类型初始化，在界面跳转到不同位置的时候直接将Type设定
		this();
		sales.setSalesType(type);
	}

	public SalesReceiptPO getSales() {
		return sales;
	}

	// 得到当前后缀然后保存该ID
	@Override
	public ResultMessage sendAudit(SalesReceiptVO salesVO)
			throws RemoteException {
		// TODO Auto-generated method stub 在送出审核的同时将所触发的库存赠送单一起触发
		// 得到当前的ID并保存
		sales = salesVO.toPO();// 现在所有状态的维护都是依靠界面层的salesVO
		Date date = sales.getDate();// 现在时间是在填表的时候填写的，所以没必要获取当前系统时间，只需要得到单据的当前时间即可
		String salesIDSuffix = dataService.getIDSuffix(date,
				sales.getSalesType());
		String salesID = "";
		salesID = getPrefix(sales.getSalesType()) + dateToString(date)
				+ salesIDSuffix;

		sales.setId(salesID);
		sales.setState(ReceiptState.SUBMITTED);
		if (sales.getSalesType() == SalesReceiptType.SALES) {
			// 存在一个InventoryReceiptDataService的getSuffix的方法
			if (sales.getLineItemReceivedAsList().size() != 0) {
				String inventoryIDSuffix = receivedDataService.getIDSuffix(
						sales.getDate(), InventoryReceiptType.GIFT);// 库存类单据是不需要根据小种类分别编号的，只需要传入时间即可
				String inventoryID = "IVE-" + dateToString(sales.getDate())
						+ inventoryIDSuffix;// 使用KCD标示inventoryReceipt
				receivedDataService.insert(new InventoryReceiptPO(
						InventoryReceiptType.GIFT, ReceiptState.SUBMITTED,
						inventoryID, sales.getDate(), sales.getOperatorID(),
						sales.getLineItemReceivedAsList()));
				sales.setReceivedID(inventoryID);
			}
		}

		ResultMessage message2 = dataService.insert(sales);

		if (message2 == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED) {
			return dataService.update(sales.getId(), sales);// 有可能sales单据的id是之前存在过的，转而调用update方法
		} else {
			return ResultMessage.SUCCESS;
		}
	}

	@Override
	public ArrayList<CommodityVO> getCommodities() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CommodityPO> commodities = commodityInfo.getAllList();
		ArrayList<CommodityVO> result = new ArrayList<>();

		for (CommodityPO commodity : commodities) {
			result.add(commodity.toVO());
		}
		return result;
	}

	@Override
	public ArrayList<MemberVO> getNowMembers(MemberType type)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<MemberVO> result = new ArrayList<>();
		ArrayList<MemberPO> findList = new ArrayList<>();
		ArrayList<MemberPO> resultList = new ArrayList<>();

		findList = memberInfo.findByMemberType(type);
		if (type == MemberType.RETAILER) {
			for (MemberPO member : findList) {
				if (member.getPayment() <= member.getCredit()) {// 筛选出欠款超过应收额度的人
					resultList.add(member);
				}
			}
			findList = resultList;
		}

		for (MemberPO member : findList) {
			result.add(member.toVO());
		}

		return result;
	}

	@Override
	public ResultMessage endSale(SalesReceiptVO salesVO) throws RemoteException {
		sales = salesVO.toPO();
		sales.setId(sales.getOperatorID() + " " + dateToStr(sales.getDate()));
		sales.setState(ReceiptState.UNCOMMITTED);
		ResultMessage message = dataService.insert(sales);
		if (message == ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED) {
			return dataService.update(sales.getId(), sales);// 在insert失败后调用update方法
		}

		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deleteReceipt(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return dataService.delete(ID);
	}

	@Override
	// addLineItem之前要将这个LineItem的所有的属性都判断好了再传进来
	// 传进来一个空的ArrayList保存当前的PromotionDescription,再传入一个double,返回SalesReceiptVO
	public SalesReceiptVO addLineItem(SalesReceiptVO salesVO,
			ArrayList<String> promotionDescription, ArrayList<Double> discount)
			throws RemoteException {
		// TODO Auto-generated method stub
		sales = salesVO.toPO();
		double count = getTotal(promotionDescription);
		discount.add(count);
		// System.out.println(sales.getLineItemReceivedAsList().size());
		sales.setDiscount(count);
		salesVO = sales.toVO();// 将得到的改变返还给界面

		return salesVO;
	}

	// 这个getTotal()方法在每次添加一个LineItem的时候触发，计算出来原价，调用getCurrentPromotion来判断促销策略，获得折让
	public double getTotal(ArrayList<String> promotionDescription)
			throws RemoteException {
		// TODO Auto-generated method stub

		double sum = 0;

		for (LineItemPO lineItem : sales.getLineItemAsList()) {
			sum = sum + lineItem.getPrice() * lineItem.getQuantity();
		}

		sales.setSumBeforeDiscount(sum);

		double discount = 0;
		if (sales.getSalesType() == SalesReceiptType.SALES) {// 正常情况下只有这种情况会被经历
			discount = getCurrentPromotion(promotionDescription);
			sales.setDiscount(discount);
			sales.setSumAfterDiscount(sales.getSumBeforeDiscount() - discount);
		} else {
			sales.setSumAfterDiscount(sum);
		}

		return discount;
	}

	// 可以通过传入一个空的ArrayList<String>来保存现在所触发的促销策略
	public double getCurrentPromotion(ArrayList<String> promotionDescription)
			throws RemoteException {
		// TODO Auto-generated method stub
		if (sales.getSalesType() != SalesReceiptType.SALES) {// 一般不会出现
			throw new IllegalArgumentException("Isnot Sales!");
		} else {
			Date date = this.sales.getDate();
			ArrayList<PromotionBySumPO> promotion_list1 = promotionInfo
					.findPromotionBySumByTime(date);
			ArrayList<PromotionByLevelPO> promotion_list2 = promotionInfo
					.findPromotionByLevelByTime(date);
			ArrayList<PromotionByCombinationListPO> promotion_list3 = promotionInfo
					.findPromotionByCombinationByTime(date);
			sales.setLineItemReceived(new ArrayList<LineItemPO>());// 每次添加商品的时候都要重新计算，所以每次都先将上次的计算结果清空
			sales.setVoucherReceived(new ArrayList<VoucherPO>());
			sales.setDiscount(0);

			for (PromotionBySumPO pro : promotion_list1) {
				PromotionBySum promotion_first = new PromotionBySum(sales, pro);
				if (promotion_first.judgeValid()) {
					ArrayList<VoucherPO> nowVouchers = sales
							.getVoucherReceivedAsList();
					ArrayList<VoucherPO> newVouchers = promotion_first
							.getVoucherList();
					nowVouchers.addAll(newVouchers);
					sales.setVoucherReceived(nowVouchers);
					promotionDescription.add(pro.toShow());// 得到当前触发promotion的描述
					ArrayList<LineItemPO> nowLineItemReceived = sales
							.getLineItemReceivedAsList();
					ArrayList<LineItemPO> newLineItemReceived = promotion_first
							.getGiftList();
					nowLineItemReceived.addAll(newLineItemReceived);
					sales.setLineItemReceived(nowLineItemReceived);
				}
			}

			for (PromotionByLevelPO pro : promotion_list2) {
				PromotionByLevel promotion_second = new PromotionByLevel(sales,
						pro);
				if (promotion_second.judgeValid()) {
					ArrayList<VoucherPO> nowVouchers = sales
							.getVoucherReceivedAsList();
					ArrayList<VoucherPO> newVouchers = promotion_second
							.getVoucherList();
					nowVouchers.addAll(newVouchers);
					sales.setVoucherReceived(nowVouchers);
					promotionDescription.add(pro.toShow());// 得到当前触发promotion的描述
					ArrayList<LineItemPO> nowLineItemReceived = sales
							.getLineItemReceivedAsList();
					ArrayList<LineItemPO> newLineItemReceived = promotion_second
							.getGiftList();
					nowLineItemReceived.addAll(newLineItemReceived);
					sales.setLineItemReceived(nowLineItemReceived);

					double nowDiscount = sales.getDiscount();
					sales.setDiscount(nowDiscount
							+ promotion_second.getDiscount());
				}
			}

			for (PromotionByCombinationListPO pro : promotion_list3) {// 在sales的折让后面追加组合商品所获得的折让，这个折让是动态的
				PromotionByCombinationList promotion_third = new PromotionByCombinationList(
						sales, pro);
				if (promotion_third.judgeValid()) {
					double discount = promotion_third.totalDiscount();
					double nowDiscount = sales.getDiscount();
					sales.setDiscount(nowDiscount + discount);
					String specialDescription = "触发了" + pro.getPromotionName()
							+ "的组合商品折价" + "，一共优惠了" + nowDiscount + "元";
					promotionDescription.add(specialDescription);// 得到当前触发promotion的描述
				}
			}
		}

		return sales.getDiscount();
	}

	@Override
	public ArrayList<SalesReceiptVO> getOperatorReceipts(String userID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return change_list_to_vo(dataService.findByUser(userID));
	}

	@Override
	public ArrayList<SalesReceiptVO> getListByReceiptState(ReceiptState state,
			String operatorID) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptVO> receipts = this
				.getOperatorReceipts(operatorID);

		Collections.sort(receipts, new Comparator<SalesReceiptVO>() {
			@Override
			public int compare(SalesReceiptVO fruit1, SalesReceiptVO fruit2) {

				return fruit2.getDate().compareTo(fruit1.getDate());
			}
		});

		return receipts;
	}

	@Override
	// 在完成添加里面调用sendAudit方法将单据直接进入待审核状态
	public ResultMessage completeAdd(SalesReceiptVO salesReceipt)
			throws RemoteException {
		// TODO Auto-generated method stub
		double nowSum = getNowSum(salesReceipt.getLineItem());// 防止在界面层没有双击获取当前总价和折让后总价
		salesReceipt.setSumBeforeDiscount(nowSum);
		salesReceipt.setSumAfterDiscount(nowSum - salesReceipt.getDiscount());
		this.sales = salesReceipt.toPO();
		return this.sendAudit(salesReceipt);

	}

	private double getNowSum(ArrayList<LineItemVO> items)
			throws RemoteException {
		// TODO Auto-generated method stub
		double sum = 0;
		for (LineItemVO item : items) {
			sum = sum + item.getPrice() * item.getQuantity();
		}
		return sum;
	}

	@Override
	public ArrayList<SalesReceiptVO> getDraftList(String userid)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptVO> userReceipt = this
				.getOperatorReceipts(userid);
		ArrayList<SalesReceiptVO> resultReceipt = new ArrayList<>();

		for (SalesReceiptVO receipt : userReceipt) {
			if (receipt.getState() == ReceiptState.UNCOMMITTED) {
				resultReceipt.add(receipt);
			}
		}

		// 将得到的草稿时间从晚到早排序
		Collections.sort(resultReceipt, new Comparator<SalesReceiptVO>() {
			@Override
			public int compare(SalesReceiptVO fruit1, SalesReceiptVO fruit2) {

				return fruit2.getDate().compareTo(fruit1.getDate());
			}
		});

		return resultReceipt;
	}

	private ArrayList<SalesReceiptVO> change_list_to_vo(
			ArrayList<SalesReceiptPO> sales) {
		ArrayList<SalesReceiptVO> result = new ArrayList<>();
		for (SalesReceiptPO saleItem : sales) {
			result.add(saleItem.toVO());
		}
		return result;
	}

	// 工具方法，将时间以yyyyMMdd的字符串形式打印出来
	private String dateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String s = formatter.format(date);
		return s + "-";
	}

	// 工具方法，根据销售类单据的具体种类得到前缀
	private String getPrefix(SalesReceiptType type) {
		String result = "";
		switch (type) {
		case PURCHASE: {
			result = "JHD-";
			break;
		}
		case SALES: {
			result = "XSD-";
			break;
		}
		case SALES_RETURN: {
			result = "XSTHD-";
			break;
		}
		case PURCHASE_RETURN: {
			result = "JHTHD-";
		}
		default: {

		}
		}

		return result;
	}

	// 工具方法：使用在草稿单的保存上来设定草稿单的id
	private static String dateToStr(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		return df.format(date);
	}
}
