package categorybl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import VO.CategoryVO;
import enums.ResultMessage;
import service.CategoryblService;

public class CategoryController extends UnicastRemoteObject implements CategoryblService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Category cate;
	public CategoryController() throws RemoteException{

		super();

		cate = new Category();

		

	}

	

	public CategoryController(Category cate) throws RemoteException{

		this();

		this.cate=cate;

	}

	@Override

	public ResultMessage addCategory(CategoryVO vo) throws RemoteException {

		return cate.addCategory(vo);

	}



	@Override

	public ResultMessage modifyCategory(CategoryVO vo) throws RemoteException {

		return cate.modifyCategory(vo);

	}



	@Override

	public ResultMessage deleteCategory(String id) throws RemoteException {

		return cate.deleteCategory(id);

	}



	@Override

	public ArrayList<String> getSubCategoryList(String categoryID) throws RemoteException {
		return cate.getSubCategoryList(categoryID);

	}
	public ArrayList<String> getCommodityList(String categoryID)throws RemoteException{
		return cate.getCommodityList(categoryID);
	}



	@Override
	public CategoryVO findById(String id) throws RemoteException {
		return cate.findById(id);
	}



	@Override
	public CategoryVO findByName(String name) throws RemoteException {
		return cate.findByName(name);
	}
}