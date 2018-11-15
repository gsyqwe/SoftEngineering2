package receiptFinder;

import java.io.Serializable;
import java.util.ArrayList;

import PO.ReceiptPO;
import VO.ReceiptVO;

public class FindByUser extends Finder implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ReceiptDataServiceInfo> dataService;

	public FindByUser() {
		super();
		dataService = new ArrayList<>();
	}

	public FindByUser(ArrayList<ReceiptDataServiceInfo> dataService) {
		this();
		this.dataService = dataService;
	}

	public void setDataService(ArrayList<ReceiptDataServiceInfo> dataService) {
		this.dataService = dataService;
	}

	@Override
	public ArrayList<ReceiptVO> find(String word) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptPO> result = new ArrayList<>();
		ArrayList<ReceiptPO> effective = effective_receipts(dataService);

		for(ReceiptDataServiceInfo info:dataService){
			@SuppressWarnings("unchecked")//因为Java的ArrayList不支持类型的隐式转换，所以要将返回结果强制转换为父类的ReceiptPO，但是应该不影响结果
			ArrayList<ReceiptPO> tem = (ArrayList<ReceiptPO>) info.findByUser(word);
			result.addAll(tem);
		}

		result = fliter_effective(effective,result);
		return change_list_to_vo(result);
	}

}
