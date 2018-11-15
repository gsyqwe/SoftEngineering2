package salesdetailbl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import PO.CommodityPO;
import PO.LineItemPO;
import PO.SalesReceiptPO;
import VO.LineItemVO;
import VO.ReceiptVO;
import VO.SalesDetailVO;
import businessprocessbl.BusinessProcessController;
import commoditydata.CommodityDataUseDataBase;
import commoditydataService.CommodityDataService;
import enums.ReceiptState;
import enums.SalesReceiptType;
import receiptFinder.SalesFinder;
import service.SalesDetailBLService;

/**
 *
 * @author 82646 提供了按照各项属性排序的方法,目前只增加了升序的版本，但是时间是按照降序排序
 *
 */
public class SalesDetailController extends UnicastRemoteObject implements
		Serializable, SalesDetailBLService {

	private static final long serialVersionUID = 1L;

	public SalesDetailController() throws RemoteException {
		super();
		businessProcess = new BusinessProcessController();
		salesFinder = new SalesFinder();
		// TODO Auto-generated constructor stub
	}

	BusinessProcessController businessProcess;
	CommodityDataService commodityService = new CommodityDataUseDataBase();
	SalesFinder salesFinder = new SalesFinder();

	@Override
	public ArrayList<SalesDetailVO> getEffectiveLineItemWithoutSpecificName(
			ArrayList<ReceiptVO> receipts) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptPO> effectiveSalesReceipt = salesFinder
				.findBySalesReceiptType(SalesReceiptType.SALES);
		receipts = fliterSalesReceipt(receipts, effectiveSalesReceipt);// 从查找结果中找到sales

		ArrayList<LineItemVO> lineItemList = new ArrayList<>();
		ArrayList<SalesDetailVO> salesDetailList = new ArrayList<>();
		for (ReceiptVO receipt : receipts) {
			lineItemList = receipt.getLineItem();
			for (LineItemVO item : lineItemList) {
				CommodityPO com = commodityService.findByID(item.getId());
				SalesDetailVO detail = new SalesDetailVO(receipt.getDate(),
						com.getName(), com.getVersion(), item.getQuantity(),
						item.getPrice(), item.getPrice() * item.getQuantity());
				salesDetailList.add(detail);
			}
		}
		return salesDetailList;
	}

	// 这时候商品名不作为一个finder，有没有商品名决定调用什么方法而已
	@Override
	public ArrayList<SalesDetailVO> getEffectiveLineItemWithCommodityName(
			String commodityName, ArrayList<ReceiptVO> findReceiptList)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptPO> effectiveSalesReceipt = salesFinder
				.findBySalesReceiptType(SalesReceiptType.SALES);
		findReceiptList = fliterSalesReceipt(findReceiptList,
				effectiveSalesReceipt);// 从查找结果中找到sales

		ArrayList<CommodityPO> namedCommodityList = commodityService
				.findWithKeyword(commodityName);// 根据输入的商品名称找到的商品
		ArrayList<SalesDetailVO> detailList = new ArrayList<>();

		ArrayList<LineItemVO> lineItemList = new ArrayList<>();

		for (ReceiptVO receipt : findReceiptList) {
			lineItemList = receipt.getLineItem();
			for (LineItemVO theItem : lineItemList) {
				for (CommodityPO commodity : namedCommodityList) {
					if (commodity.getId().equals(theItem.getId())) {
						SalesDetailVO salesDetail = new SalesDetailVO(
								receipt.getDate(), commodity.getName(),
								commodity.getVersion(), theItem.getQuantity(),
								theItem.getPrice(), theItem.getPrice()
										* theItem.getQuantity());
						detailList.add(salesDetail);
						break;
					}
				}
			}
		}

		Collections.sort(detailList);

		return detailList;
	}

	@Override
	public ArrayList<SalesDetailVO> sortByDate(
			ArrayList<SalesDetailVO> detailList) throws RemoteException {
		Collections.sort(detailList, new Comparator<SalesDetailVO>() {
			@Override
			public int compare(SalesDetailVO fruit1, SalesDetailVO fruit2) {

				if (fruit1.getDate().compareTo(fruit2.getDate()) != 0) {
					return fruit2.getDate().compareTo(fruit1.getDate());
				}

				return fruit1.getCommodityName().compareTo(
						fruit2.getCommodityName());
			}
		});

		return detailList;
	}

	@Override
	public ArrayList<SalesDetailVO> sortByCommodityName(
			ArrayList<SalesDetailVO> detailList) throws RemoteException {
		Collections.sort(detailList);

		return detailList;
	}

	@Override
	public ArrayList<SalesDetailVO> sortByAmount(
			ArrayList<SalesDetailVO> detailList) throws RemoteException {
		Collections.sort(detailList, new Comparator<SalesDetailVO>() {
			@Override
			public int compare(SalesDetailVO fruit1, SalesDetailVO fruit2) {

				if (Double.compare(fruit1.getTotalPrice(),
						fruit2.getTotalPrice()) != 0) {
					return Double.compare(fruit1.getTotalPrice(),
							fruit2.getTotalPrice());
				}

				return fruit1.getCommodityName().compareTo(
						fruit2.getCommodityName());
			}
		});

		return detailList;
	}

	@Override
	public ArrayList<SalesDetailVO> sortByQuantity(
			ArrayList<SalesDetailVO> detailList) throws RemoteException {
		Collections.sort(detailList, new Comparator<SalesDetailVO>() {
			@Override
			public int compare(SalesDetailVO fruit1, SalesDetailVO fruit2) {

				if (Integer.compare(fruit1.getQuantity(), fruit2.getQuantity()) != 0) {
					return Integer.compare(fruit1.getQuantity(),
							fruit2.getQuantity());
				}

				return fruit1.getCommodityName().compareTo(
						fruit2.getCommodityName());
			}
		});

		return detailList;
	}

	@Override
	public ArrayList<SalesDetailVO> sortByPriceEach(
			ArrayList<SalesDetailVO> detailList) throws RemoteException {
		Collections.sort(detailList, new Comparator<SalesDetailVO>() {
			@Override
			public int compare(SalesDetailVO fruit1, SalesDetailVO fruit2) {

				if (Double.compare(fruit1.getPriceForEach(),
						fruit2.getPriceForEach()) != 0) {
					return Double.compare(fruit1.getPriceForEach(),
							fruit2.getPriceForEach());
				}

				return fruit1.getCommodityName().compareTo(
						fruit2.getCommodityName());
			}
		});

		return detailList;
	}

	@Override
	public ArrayList<SalesDetailVO> getInitLineItem() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptPO> effectiveSalesReceipt = salesFinder
				.findBySalesReceiptType(SalesReceiptType.SALES);
		ArrayList<SalesReceiptPO> verifiedSalesReceipt = salesFinder
				.findByReceiptState(ReceiptState.VERIFIED);
		ArrayList<SalesReceiptPO> finalReceipt = new ArrayList<>();

		for (SalesReceiptPO salesReceipt : effectiveSalesReceipt) {
			for (SalesReceiptPO verifiedReceipt : verifiedSalesReceipt) {
				if (salesReceipt.getId().equals(verifiedReceipt.getId())) {
					finalReceipt.add(salesReceipt);
					break;
				}
			}
		}

		ArrayList<LineItemPO> lineItemList = new ArrayList<>();
		ArrayList<SalesDetailVO> salesDetailList = new ArrayList<>();
		for (SalesReceiptPO receipts : finalReceipt) {
			lineItemList = receipts.getLineItemAsList();
			for (LineItemPO item : lineItemList) {
				CommodityPO com = commodityService.findByID(item.getId());
				SalesDetailVO detail = new SalesDetailVO(receipts.getDate(),
						com.getName(), com.getVersion(), item.getQuantity(),
						item.getPrice(), item.getPrice() * item.getQuantity());
				salesDetailList.add(detail);
			}
		}

		Collections.sort(salesDetailList);// 按照默认排序进行排序

		return salesDetailList;

	}

	private ArrayList<ReceiptVO> fliterSalesReceipt(
			ArrayList<ReceiptVO> receipts,
			ArrayList<SalesReceiptPO> salesReceipts) {
		ArrayList<ReceiptVO> resultList = new ArrayList<>();

		for (ReceiptVO receipt : receipts) {
			for (SalesReceiptPO sales : salesReceipts) {
				if (receipt.getId().equals(sales.getId())) {
					resultList.add(receipt);
					break;
				}
			}
		}

		return resultList;
	}

}
