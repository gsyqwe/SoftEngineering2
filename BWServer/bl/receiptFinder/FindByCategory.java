package receiptFinder;

import java.io.Serializable;
import java.util.ArrayList;

import PO.ReceiptPO;
import VO.ReceiptVO;

public class FindByCategory extends Finder implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ReceiptDataServiceInfo> dataService;

	public FindByCategory(ArrayList<ReceiptDataServiceInfo> dataService) {
		this();
		this.dataService = dataService;
	}


	public FindByCategory() {
		super();
		dataService = new ArrayList<>();
	}


	@Override
	public ArrayList<ReceiptVO> find(String word) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptPO> effectiveReceipt = effective_receipts(dataService);
		ArrayList<ReceiptPO> result = new ArrayList<>();
		for(ReceiptDataServiceInfo info:dataService){
			@SuppressWarnings("unchecked")
			ArrayList<ReceiptPO> tem = (ArrayList<ReceiptPO>) info.findByRepository(word);
			result.addAll(tem);
		}

		result = fliter_effective(effectiveReceipt,result);
		return change_list_to_vo(result);
	}

}
