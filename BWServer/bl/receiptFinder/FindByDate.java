package receiptFinder;

import java.io.Serializable;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import PO.ReceiptPO;
import VO.ReceiptVO;

public class FindByDate extends Finder implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ReceiptDataServiceInfo> dataService;

	public FindByDate() {
		super();
		dataService = new ArrayList<>();
	}

	public FindByDate(ArrayList<ReceiptDataServiceInfo> dataService) {
		this();
		this.dataService = dataService;
	}

	public void setDataService(ArrayList<ReceiptDataServiceInfo> dataService){
		this.dataService = dataService;
	}

	@Override
	public ArrayList<ReceiptVO> find(String word) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptPO> result_list = new ArrayList<>();
		ArrayList<ReceiptPO> effective_receipt = effective_receipts(dataService);
		//对传入的string进行解析，解析成开始时间和结束时间，然后调用方法
		String [] start_and_end = word.split("->");
		Date start_date = strToDate(start_and_end[0]);
		Date end_date = strToDate(start_and_end[1]);

		if(start_date == null || end_date == null){
			return new ArrayList<ReceiptVO>();
		}

		for(ReceiptDataServiceInfo info:dataService){
			@SuppressWarnings("unchecked")
			ArrayList<ReceiptPO> tem = (ArrayList<ReceiptPO>) info.findByDate(start_date, end_date);
			result_list.addAll(tem);
		}

		result_list = fliter_effective(effective_receipt,result_list);//筛选结果中有效的单据
		return change_list_to_vo(result_list);
	}

	private static Date strToDate(String time){//将字符串转化为Date
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = date.parse(time, pos);
		return strtodate;
	}

}
