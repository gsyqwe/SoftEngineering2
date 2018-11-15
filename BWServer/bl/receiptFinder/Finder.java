package receiptFinder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import PO.ReceiptPO;
import VO.ReceiptVO;
import enums.ReceiptState;

/**
 * 
 * @author JiYuzhe
 * Finder类是直接进行查找的类，里面有一个查找方法，返回ReceiptVO供子类实现
 * Finder的所有子类都有一个实现ReceiptDataServiceInfo的类提供数据层的支持
 * 在实现筛选的时候，界面层先判断是否点选了根据单据类型那个选项，如果点选了那个选项，那么根据所填入的字段arrayList里面只装一个dataService
 * 如果没有点选根据单据类型选择，那么arrayList里面装满所有三个dataService
 *
 */
public abstract class Finder implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract ArrayList<ReceiptVO> find(String word);//子类只需要覆盖这个抽象方法即可

	protected ArrayList<ReceiptVO> change_list_to_vo(ArrayList<ReceiptPO> find_list){
		ArrayList<ReceiptVO> result = new ArrayList<>();
		for(ReceiptPO receipt:find_list){
			result.add(receipt.toVO());
		}
		
		return result;
	}
	
	public ArrayList<ReceiptPO> effective_receipts(ArrayList<ReceiptDataServiceInfo> dataService){
		ArrayList<ReceiptPO> effective_receipt_list = new ArrayList<>();
		
		for(ReceiptDataServiceInfo service:dataService){
			//因为process里面要显示的是已经生效的单据，所以所有find都要调用这个方法
			@SuppressWarnings("unchecked")
			ArrayList<ReceiptPO> tem = (ArrayList<ReceiptPO>) service.findByReceiptState(ReceiptState.VERIFIED);
			effective_receipt_list.addAll(tem);
		}
		
		return effective_receipt_list;
	}
	
	//在当前单据中删除能生效的ReceiptPO列表
	public ArrayList<ReceiptPO> fliter_effective(ArrayList<ReceiptPO> effective_list,ArrayList<ReceiptPO> find_list){
		Iterator<ReceiptPO> it = find_list.iterator();//在需要删除元素的时候不要用迭代for循环，用迭代器安全
		
		while(it.hasNext()){
			ReceiptPO find_item = it.next();
			boolean is_effective = false;
			for(ReceiptPO effective_item:effective_list){
				if(find_item.getId().equals(effective_item.getId())){
					is_effective = true;
					break;
				}
			}
			
			if(!is_effective){
				it.remove();
			}
		}
		
		return find_list;
	}
}
