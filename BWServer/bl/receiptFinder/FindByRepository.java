package receiptFinder;

import java.io.Serializable;
import java.util.ArrayList;

import PO.ReceiptPO;
import VO.ReceiptVO;

public class FindByRepository extends Finder implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ReceiptDataServiceInfo> dataService;

	public FindByRepository() {
		super();
		dataService = new ArrayList<>();
	}


	public FindByRepository(ArrayList<ReceiptDataServiceInfo> dataService) {
		this();
		this.dataService = dataService;
	}

	public void setDataService(ArrayList<ReceiptDataServiceInfo> dataService){
		this.dataService = dataService;
	}

	@Override
	public ArrayList<ReceiptVO> find(String word) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptPO> receipt_list = new ArrayList<>();
		ArrayList<ReceiptPO> effective = effective_receipts(dataService);
		for(ReceiptDataServiceInfo info:dataService){
			@SuppressWarnings("unchecked")
			ArrayList<ReceiptPO> tem = (ArrayList<ReceiptPO>) info.findByRepository(word);
			receipt_list.addAll(tem);
		}

		receipt_list = fliter_effective(effective,receipt_list);
		return change_list_to_vo(receipt_list);
	}

}
