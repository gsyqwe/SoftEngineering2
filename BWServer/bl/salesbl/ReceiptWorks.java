package salesbl;

import enums.ResultMessage;
import PO.ReceiptPO;
import VO.ReceiptVO;


public interface ReceiptWorks {
	
	public String addReceipt();//鏂板缓鍗曟嵁锛岃繑鍥炲崟鎹殑缂栧彿
	
	public ReceiptVO prepareToShow();//鏄剧ず鍗曟嵁锛岃繑鍥濺eceiptVO渚涚晫闈㈡樉绀�
	
	public ResultMessage passAudit();//鍦ㄩ�氳繃瀹℃牳鍚庤皟鐢ㄨ繖涓柟娉曪紝鏇存敼鍗曟嵁鐨勭姸鎬佸拰淇敼绯荤粺鏁版嵁锛屽叾涓慨鏀圭郴缁熸暟鎹彲浠ラ�氳繃璋冪敤changeData鏂规硶瀹炵幇
	
	public ResultMessage changeData();//璐熻矗瀹炵幇淇敼绯荤粺鐨勬暟鎹�
	
	public ResultMessage failPassing();//鍦ㄥ鏍镐笉閫氳繃鏃惰皟鐢ㄧ殑鏂规硶
	
	public void endSale();//鍙互鍦ㄦ病鏈夋柊寤哄畬鎴愮偣鍑诲彇娑堢殑鏃跺�欒Е鍙戯紝杩欐椂鍊欏彲浠ヨ�冭檻涓�涓檮鍔犲姛鑳斤紝姣斿鎶婃鍗曟嵁鏀惧叆鑽夌绠憋紝浠raft鐘舵�佹爣璇�
	
	public void setReceiptPO(ReceiptPO receipt);//鏄痵et鎴愬憳鍙橀噺鐨剆et鏂规硶锛屽彲浠ュ湪淇敼鍗曟嵁鍐呭鐨勬椂鍊欒皟鐢�
	
	public ReceiptPO getReceiptPO();//get鏂规硶锛屽緱鍒扮幇鍦ㄧ殑PO瀵硅薄锛岀劧鍚庡彲浠ュ垎鍒褰撳墠po鐨勫悇椤瑰睘鎬ц繘琛屾搷浣滃拰淇敼
	
	public ResultMessage save();//瀵瑰崟鎹繘琛屾寔涔呭寲淇濆瓨锛屽彲浠ュ垎涓哄绠�鐭殑鍗曟嵁灞炴�х殑淇濆瓨鍜屽鍗曟嵁鎵�鏈夊唴瀹圭殑淇濆瓨锛屽彲浠ヨ皟鐢ㄥ悇涓崟鎹甦ata灞傜殑鏂规硶
	
	public ResultMessage delete();//瀵规寔涔呭寲淇濆瓨鐨勫崟鎹殑鍒犻櫎宸ヤ綔锛屽彲浠ヨ皟鐢ㄥ悇涓崟鎹甦ata灞傜殑鏂规硶

}
