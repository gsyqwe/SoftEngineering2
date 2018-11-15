package commoditybl;



import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import PO.CommodityPO;
import VO.CategoryVO;
import VO.CommodityVO;
import VO.EmailForAlarmVO;
import VO.StockInAndOutVO;
import categorybl.CategoryController;
import enums.ResultMessage;
import service.CommodityblService;



public class CommodityController extends UnicastRemoteObject implements CommodityblService {

	/**
	 * @throws RemoteException 
	 *
	 */
	public static void main(String[] args) throws RemoteException{
		CommodityController c=new CommodityController();
		System.out.println(c.deleteCommodity("COM-20180105-00001"));
	}
	private static final long serialVersionUID = 1L;
	Commodity com;

	public CommodityController() throws RemoteException{

		super();

		com = new Commodity();



	}

	public CommodityController(Commodity com) throws RemoteException{

		this();

		this.com=com;

	}

	@Override
	public ResultMessage addCommodity(CommodityVO vo) throws RemoteException {
		ResultMessage result=com.addCommodity(vo);
		if(result==ResultMessage.SUCCESS)
		{
			CategoryController cate=new CategoryController();
			String[] nameAndID=vo.getCategory().split("/");
			CategoryVO category=cate.findById(nameAndID[1]);
			ArrayList<String> commodity=category.getSubCateOrCom();
			commodity.add(vo.getName()+"/"+com.findByName(vo.getName()).getId());
			category.setSubCateOrCom(commodity);
			ResultMessage modifyResult=cate.modifyCategory(category);
			return modifyResult;
		}
		else 
			return result;

	}
	
	@Override

	public CommodityVO findByName(String name) throws RemoteException {

		return com.findByName(name);

	}

	@Override

	public ResultMessage modifyCommodity(CommodityVO vo) throws RemoteException {

		return com.modifyCommodity(vo);

	}

	@Override

	public ResultMessage deleteCommodity(String id) throws RemoteException {
		CategoryController cate=new CategoryController();
		System.out.println(com.findByID(id).toString());
		String[] nameAndIDf=com.findByID(id).getCategory().split("/");
		CategoryVO vo=cate.findById(nameAndIDf[1]);
		System.out.println(vo.toString());
		ArrayList<String> coms=vo.getSubCateOrCom();
		int index=-1;
		for(int i=0;i<coms.size();i++){
			String[] nameAndID=coms.get(i).split("/");
			if(nameAndID[1].equals(id)){
				index=i;
				break;
			}
		}
		if(index!=-1)
			coms.remove(index);
		
		vo.setSubCateOrCom(coms);
		ResultMessage result=cate.modifyCategory(vo);
		if(result==ResultMessage.SUCCESS){
			return com.deleteCommodity(id);
		}
		return result;
	}

	@Override

	public CommodityVO findByID(String id) throws RemoteException {
		return com.findByID(id);
	}

	@Override

	public ArrayList<CommodityVO> findWithKeyWord(String keyword) throws RemoteException {

		return com.findWithKeyword(keyword);

	}

	@Override

	public ArrayList<CommodityVO> showCommodityList() throws RemoteException {

		return com.showCommodityList();

	}

	@Override

	public ResultMessage stockInAndOut(StockInAndOutVO vo,ArrayList<EmailForAlarmVO> emails) throws RemoteException {

		return com.stockInAndOut(vo,emails);

	}

}