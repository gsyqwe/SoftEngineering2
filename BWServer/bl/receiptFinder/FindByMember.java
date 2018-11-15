package receiptFinder;

import java.io.Serializable;
import java.util.ArrayList;

import PO.ReceiptPO;
import VO.ReceiptVO;

public class FindByMember extends Finder implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ReceiptDataServiceInfo> dataService;

	public FindByMember() {
		super();
		dataService = new ArrayList<>();
	}

	public FindByMember(ArrayList<ReceiptDataServiceInfo> dataService) {
		super();
		this.dataService = dataService;
	}

	public void setDataService(ArrayList<ReceiptDataServiceInfo> dataService) {
		this.dataService = dataService;
	}

	@Override
	public ArrayList<ReceiptVO> find(String word) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptPO> effective_receipt = effective_receipts(dataService);
		ArrayList<ReceiptPO> result = new ArrayList<>();
		for(ReceiptDataServiceInfo type:dataService){
			@SuppressWarnings("unchecked")
			ArrayList<ReceiptPO> tem = (ArrayList<ReceiptPO>) type.findByMember(word);
			result.addAll(tem);
		}

		result = fliter_effective(effective_receipt,result);
		return change_list_to_vo(result);
	}

}
