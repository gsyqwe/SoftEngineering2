package receiptFinder;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import PO.ReceiptPO;
import VO.ReceiptVO;
import enums.ReceiptState;
import service.FindReceiptMethodService;

public class FindReceipt extends UnicastRemoteObject implements Serializable,
		FindReceiptMethodService {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ReceiptDataServiceInfo> dataService;

	public FindReceipt() throws RemoteException {
		super();
		dataService = new ArrayList<>();
		dataService.add(new FinancialFinder());
		dataService.add(new InventoryFinder());
		dataService.add(new SalesFinder());
	}

	@Override
	public ArrayList<ReceiptVO> findByCategory(String word) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptPO> effectiveReceipt = effective_receipts(dataService);
		ArrayList<ReceiptPO> result = new ArrayList<>();
		for (ReceiptDataServiceInfo info : dataService) {
			@SuppressWarnings("unchecked")
			ArrayList<ReceiptPO> tem = (ArrayList<ReceiptPO>) info
					.findByRepository(word);
			result.addAll(tem);
		}

		result = fliter_effective(effectiveReceipt, result);
		ArrayList<ReceiptVO> the = change_list_to_vo(result);
		return the;
	}

	public ArrayList<ReceiptVO> findByRepository(String word) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptDataServiceInfo> newData = dataService;
		newData.remove(1);
		ArrayList<ReceiptPO> receipt_list = new ArrayList<>();
		ArrayList<ReceiptPO> effective = effective_receipts(newData);
		for (ReceiptDataServiceInfo info : dataService) {
			@SuppressWarnings("unchecked")
			ArrayList<ReceiptPO> tem = (ArrayList<ReceiptPO>) info
					.findByRepository(word);
			receipt_list.addAll(tem);
		}

		receipt_list = fliter_effective(effective, receipt_list);
		ArrayList<ReceiptVO> the = change_list_to_vo(receipt_list);
		return the;
	}

	@Override
	public ArrayList<ReceiptVO> findByDate(String word) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptPO> result_list = new ArrayList<>();
		ArrayList<ReceiptPO> effective_receipt = effective_receipts(dataService);
		// 对传入的string进行解析，解析成开始时间和结束时间，然后调用方法
		String[] start_and_end = word.split("->");
		Date start_date = strToDate(start_and_end[0]);
		Date end_date = strToDate(start_and_end[1]);

		if (start_date == null || end_date == null) {
			return new ArrayList<ReceiptVO>();
		}

		for (ReceiptDataServiceInfo info : dataService) {
			@SuppressWarnings("unchecked")
			ArrayList<ReceiptPO> tem = (ArrayList<ReceiptPO>) info.findByDate(
					start_date, end_date);
			result_list.addAll(tem);
		}

		result_list = fliter_effective(effective_receipt, result_list);// 筛选结果中有效的单据
		ArrayList<ReceiptVO> the = change_list_to_vo(result_list);
		return the;
	}

	@Override
	public ArrayList<ReceiptVO> findByMember(String word) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptDataServiceInfo> newData = dataService;
		newData.remove(1);
		ArrayList<ReceiptPO> effective_receipt = effective_receipts(newData);
		ArrayList<ReceiptPO> result = new ArrayList<>();
		for (ReceiptDataServiceInfo type : dataService) {
			@SuppressWarnings("unchecked")
			ArrayList<ReceiptPO> tem = (ArrayList<ReceiptPO>) type
					.findByMember(word);
			result.addAll(tem);
		}

		result = fliter_effective(effective_receipt, result);
		ArrayList<ReceiptVO> the = change_list_to_vo(result);
		return the;
	}

	@Override
	public ArrayList<ReceiptVO> findByUser(String word) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptPO> result = new ArrayList<>();
		ArrayList<ReceiptPO> effective = effective_receipts(dataService);

		for (ReceiptDataServiceInfo info : dataService) {
			@SuppressWarnings("unchecked")
			// 因为Java的ArrayList不支持类型的隐式转换，所以要将返回结果强制转换为父类的ReceiptPO，但是应该不影响结果
			ArrayList<ReceiptPO> tem = (ArrayList<ReceiptPO>) info
					.findByUser(word);
			result.addAll(tem);
		}

		result = fliter_effective(effective, result);
		ArrayList<ReceiptVO> the = change_list_to_vo(result);
		return the;
	}

	protected ArrayList<ReceiptVO> change_list_to_vo(
			ArrayList<ReceiptPO> find_list) {
		ArrayList<ReceiptVO> result = new ArrayList<>();
		for (ReceiptPO receipt : find_list) {
			result.add(receipt.toVO());
		}

		return result;
	}

	public ArrayList<ReceiptPO> effective_receipts(
			ArrayList<ReceiptDataServiceInfo> dataService) {
		ArrayList<ReceiptPO> effective_receipt_list = new ArrayList<>();

		for (ReceiptDataServiceInfo service : dataService) {
			// 因为process里面要显示的是已经生效的单据，所以所有find都要调用这个方法
			@SuppressWarnings("unchecked")
			ArrayList<ReceiptPO> tem = (ArrayList<ReceiptPO>) service
					.findByReceiptState(ReceiptState.VERIFIED);
			effective_receipt_list.addAll(tem);
		}

		return effective_receipt_list;
	}

	// 在当前单据中删除能生效的ReceiptPO列表
	public ArrayList<ReceiptPO> fliter_effective(
			ArrayList<ReceiptPO> effective_list, ArrayList<ReceiptPO> find_list) {
		Iterator<ReceiptPO> it = find_list.iterator();// 在需要删除元素的时候不要用迭代for循环，用迭代器安全

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

	private static Date strToDate(String time) {// 将字符串转化为Date
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = date.parse(time, pos);
		return strtodate;
	}
}
