package businessconditionbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import PO.CommodityPO;
import PO.InventoryReceiptPO;
import PO.LineItemPO;
import PO.ReceiptPO;
import PO.SalesReceiptPO;
import VO.BusinessConditionVO;
import commoditydata.CommodityDataUseDataBase;
import commoditydataService.CommodityDataService;
import enums.InventoryReceiptType;
import enums.ReceiptState;
import enums.SalesReceiptType;
import inventoryreceiptdata.InventoryReceiptDataUseDataBase;
import inventoryreceiptdataservice.InventoryReceiptDataService;
import salesdata.SalesDataUseDataBase;
import salesdataService.SalesDataService;
import service.BusinessConditionBLService;

/*
* Module Comments:  		BusinessCondition������H
* Author:					161250051/Lai Kin Meng
* Create Date�G  				2017-10-25
* Modified By�G  	 			161250051/Lai Kin Meng
* Modified Date:
* Why & What is modified�G
*/

/**
 *
 * @author JiYuzhe
 *         因为要寻找单据具体的type，所以不使用receiptFinder对单据进行查找，转而使用各个单据的dataService,利润最后在界面层自己算，传入VO
 * @param <T>
 *
 */
public class BusinessConditionController extends UnicastRemoteObject implements BusinessConditionBLService {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	BusinessConditionVO businessCondition;
	SalesDataService salesDataService = new SalesDataUseDataBase();
	InventoryReceiptDataService inventoryDataService = new InventoryReceiptDataUseDataBase();
	CommodityDataService commodityDataService = new CommodityDataUseDataBase();

	public BusinessConditionController() throws RemoteException {
		businessCondition = new BusinessConditionVO();
		salesDataService = new SalesDataUseDataBase();
		inventoryDataService = new InventoryReceiptDataUseDataBase();
		commodityDataService = new CommodityDataUseDataBase();
	}

	@Override
	public BusinessConditionVO getBusinessConditionList(Date startTime, Date endTime) throws RemoteException {
		double salesIncome = 0;
		ArrayList<SalesReceiptPO> salesReceipt = getSalesByType(SalesReceiptType.SALES, startTime, endTime);
		for (SalesReceiptPO sales : salesReceipt) {
			salesIncome += sales.getSumAfterDiscount();
		}

		double commodityOverflowIncome = 0;
		ArrayList<InventoryReceiptPO> overflowReceipt = getInventoryByType(InventoryReceiptType.OVERFLOW, startTime,
				endTime);
		CommodityPO nowCommodity = null;
		for (InventoryReceiptPO receipt : overflowReceipt) {
			for (LineItemPO item : receipt.getLineItemAsList()) {
				nowCommodity = commodityDataService.findByID(item.getId());
				System.out.println(nowCommodity.getId());
				commodityOverflowIncome = commodityOverflowIncome + item.getQuantity() * nowCommodity.getBid();// 数量乘以默认进价
			}
		}

		double costChangeIncome = 0;// 成本调价收入，对于每个进货单，如果进货的价格低于默认进价的话，算入成本调价收入
		ArrayList<SalesReceiptPO> purchaseReceipt = getSalesByType(SalesReceiptType.PURCHASE, startTime, endTime);// 得到进货单
		for (SalesReceiptPO receipt : purchaseReceipt) {
			for (LineItemPO item : receipt.getLineItemAsList()) {
				nowCommodity = commodityDataService.findByID(item.getId());
				if (item.getPrice() < nowCommodity.getBid()) {
					costChangeIncome = costChangeIncome
							+ item.getQuantity() * (nowCommodity.getBid() - item.getPrice());
				}
			}
		}

		double salesAndReturnIncome = 0;// 进货退货差价
		ArrayList<SalesReceiptPO> salesReturnReceipt = getSalesByType(SalesReceiptType.SALES_RETURN, startTime,
				endTime);// 得到退货单
		ArrayList<LineItemPO> salesLineItemSet = new ArrayList<>();// 这个set里面的quantitity是这个商品全部的销售个数，price是全部的销售总额
		for (SalesReceiptPO receipt : salesReceipt) {
			for (LineItemPO item : receipt.getLineItemAsList()) {
				salesLineItemSet = addNewItem(salesLineItemSet, item);
			}
		}

		ArrayList<LineItemPO> returnLineItemSet = new ArrayList<>();// 这个set和上面的相似
		for (SalesReceiptPO receipt : salesReturnReceipt) {
			for (LineItemPO item : receipt.getLineItemAsList()) {
				returnLineItemSet = addNewItem(returnLineItemSet, item);
			}
		}

		// 在此计算每个LineItem的平均售价和退货价
		salesLineItemSet = averagePrice(salesLineItemSet);
		returnLineItemSet = averagePrice(returnLineItemSet);

		for (LineItemPO returnItem : returnLineItemSet) {
			for (LineItemPO salesItem : salesLineItemSet) {
				if (returnItem.getId().equals(salesItem.getId())) {
					if (returnItem.getPrice() < salesItem.getPrice()) {
						salesAndReturnIncome = salesAndReturnIncome
								+ (salesItem.getPrice() - returnItem.getPrice()) * returnItem.getQuantity();
					}
				}
			}
		}

		double voucherAndActual = 0;// 代金券和实际收款差额收入
		double discount = 0;// 显示折让了多少
		for (SalesReceiptPO receipt : salesReceipt) {
			if ((receipt.getSumBeforeDiscount() - receipt.getDiscount()) < receipt.getVoucher().getFaceValue()) {
				voucherAndActual += (receipt.getVoucher().getFaceValue()
						- (receipt.getSumBeforeDiscount() - receipt.getDiscount()));
			}
			discount += (receipt.getSumBeforeDiscount() - receipt.getSumAfterDiscount());
		}

		double costOfPurchase = 0;
		for (SalesReceiptPO purchase : purchaseReceipt) {
			costOfPurchase += purchase.getSumBeforeDiscount();
		}

		ArrayList<InventoryReceiptPO> giftReceipt = getInventoryByType(InventoryReceiptType.GIFT, startTime, endTime);
		ArrayList<InventoryReceiptPO> breakageReceipt = getInventoryByType(InventoryReceiptType.BREAKAGE, startTime,
				endTime);

		double breakageCost = 0;
		double giftCost = 0;
		for (InventoryReceiptPO receipt : breakageReceipt) {
			for (LineItemPO item : receipt.getLineItemAsList()) {
				nowCommodity = commodityDataService.findByID(item.getId());
				breakageCost = breakageCost + item.getQuantity() * nowCommodity.getBid();// 数量乘以默认进价
			}
		}

		for (InventoryReceiptPO receipt : giftReceipt) {
			for (LineItemPO item : receipt.getLineItemAsList()) {
				nowCommodity = commodityDataService.findByID(item.getId());
				giftCost = giftCost + item.getQuantity() * nowCommodity.getBid();// 数量乘以默认进价
			}
		}

		double incomeAfterDiscount = salesIncome + commodityOverflowIncome + costChangeIncome + voucherAndActual
				+ salesAndReturnIncome - discount;
		double sumOfCost = costOfPurchase + breakageCost + giftCost;

		BusinessConditionVO businessCondition = new BusinessConditionVO(incomeAfterDiscount, discount, salesIncome,
				commodityOverflowIncome, costChangeIncome, salesAndReturnIncome, voucherAndActual, sumOfCost,
				costOfPurchase, breakageCost, giftCost);

		return businessCondition;
	}

	// 相当于三重条件筛选：时间，小的单据类型，单据状态：已生效
	@SuppressWarnings("unchecked")
	private ArrayList<InventoryReceiptPO> getInventoryByType(InventoryReceiptType type, Date startTime, Date endTime) {
		ArrayList<InventoryReceiptPO> theEffectiveReceipt = inventoryDataService
				.findByReceiptState(ReceiptState.VERIFIED);
		ArrayList<InventoryReceiptPO> timeWithinReceipt = inventoryDataService.findByDate(startTime, endTime);
		ArrayList<InventoryReceiptPO> typeCorrectReceipt = inventoryDataService.findByInventoryReceiptType(type);

		ArrayList<InventoryReceiptPO> resultReceipt = (ArrayList<InventoryReceiptPO>) this
				.fliter_effective(theEffectiveReceipt, timeWithinReceipt);
		resultReceipt = (ArrayList<InventoryReceiptPO>) this.fliter_effective(resultReceipt, typeCorrectReceipt);

		return resultReceipt;
	}

	// 工具方法，用来将LineItem合并去重
	private ArrayList<LineItemPO> addNewItem(ArrayList<LineItemPO> salesLineItemSet, LineItemPO newItem) {
		boolean isIn = false;
		for (LineItemPO item : salesLineItemSet) {
			if (item.getId().equals(newItem.getId())) {
				isIn = true;
				item.setQuantity(item.getQuantity() + newItem.getQuantity());
				item.setPrice(item.getPrice() + newItem.getQuantity() * newItem.getPrice());
			}
		}

		if (isIn == false) {
			newItem.setPrice(newItem.getPrice() * newItem.getQuantity());
			salesLineItemSet.add(newItem);
		}

		return salesLineItemSet;
	}

	// 工具方法，得到每个商品的平均价
	private ArrayList<LineItemPO> averagePrice(ArrayList<LineItemPO> itemSet) {
		for (LineItemPO item : itemSet) {
			item.setPrice(item.getPrice() / item.getQuantity());
		}

		return itemSet;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<SalesReceiptPO> getSalesByType(SalesReceiptType type, Date startTime, Date endTime) {
		ArrayList<SalesReceiptPO> theEffectiveReceipt = salesDataService.findByReceiptState(ReceiptState.VERIFIED);
		ArrayList<SalesReceiptPO> timeWithinReceipt = salesDataService.findByTime(startTime, endTime);
		ArrayList<SalesReceiptPO> typeCorrectReceipt = salesDataService.findBySalesType(type);

		ArrayList<SalesReceiptPO> resultReceipt = (ArrayList<SalesReceiptPO>) this.fliter_effective(theEffectiveReceipt,
				timeWithinReceipt);
		resultReceipt = (ArrayList<SalesReceiptPO>) this.fliter_effective(resultReceipt, typeCorrectReceipt);

		return resultReceipt;
	}

	// 返回第二个列表元素同时也在第一个列表出现的单据
	private ArrayList<? extends ReceiptPO> fliter_effective(ArrayList<? extends ReceiptPO> effective_list,
			ArrayList<? extends ReceiptPO> find_list) {
		Iterator<? extends ReceiptPO> it = find_list.iterator();// 在需要删除元素的时候不要用迭代for循环，用迭代器安全

		while (it.hasNext()) {
			ReceiptPO find_item = it.next();
			boolean is_effective = false;
			for (ReceiptPO effective_item : effective_list) {
				if (find_item.getId().equals(effective_item.getId())) {
					is_effective = true;
					break;
				}
			}

			if (!is_effective) {
				it.remove();
			}
		}

		return find_list;
	}
}
