package categorybl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import PO.CategoryPO;
import VO.CategoryVO;
import categorydata.CategoryDataUseDataBase;
import categorydataService.CategorydataService;
import enums.CategoryType;
import enums.ResultMessage;

public class CategoryControllerTest {
	private static CategoryController cate;
	@Before
	public void setUp() throws Exception {
		cate=new CategoryController();
	}

	@Test
	public void testAddCategory() throws RemoteException {
		CategoryVO vo=new CategoryVO();
		vo.setFather("分类/CAT-00000");
		vo.setName("李泽言分类");
		vo.setType(CategoryType.LEAF);
		//ArrayList<String> com=new ArrayList<String>();
		//com.add("许墨/COM-20180105-00001");
		//vo.setSubCateOrCom(com);
		assertEquals(ResultMessage.SUCCESS,cate.addCategory(vo));
		
	}
	
	@Test
	public void testAddCategory1() throws RemoteException {
		CategoryVO vo=new CategoryVO();
		vo.setFather("分类/CAT-00000");
		vo.setName("许墨分类");
		vo.setId("");
		vo.setType(CategoryType.NODE);
		//ArrayList<String> com=new ArrayList<String>();
		//com.add("许墨");
		//vo.setSubCateOrCom(com);
		assertEquals(ResultMessage.SUCCESS,cate.addCategory(vo));
		
	}
	
	@Test
	public void testAddCategory2() throws RemoteException {
		CategoryVO vo=new CategoryVO();
		vo.setFather("许墨分类/CAT-00008");
		vo.setName("许墨分类的分类");
		vo.setId("");
		vo.setType(CategoryType.NODE);
		//ArrayList<String> com=new ArrayList<String>();
		//com.add("许墨");
		//vo.setSubCateOrCom(com);
		assertEquals(ResultMessage.SUCCESS,cate.addCategory(vo));
		
	}

	@Test
	public void testModifyCategory() throws RemoteException {
		CategoryVO vo=new CategoryVO();
		vo.setId("CAT-00009");
		vo.setName("许墨分类类");
		vo.setType(CategoryType.NODE);
		vo.setFather("许墨分类/CAT-00008");
		//ArrayList<String> com=new ArrayList<String>();
		//com.add("COM-00001/李泽言");
		//vo.setSubCateOrCom(com);
		assertEquals(ResultMessage.SUCCESS,cate.modifyCategory(vo));
	}
	
	@Test
	public void testModifyCategory1() throws RemoteException {
		CategoryVO vo=new CategoryVO();
		vo.setId("CAT-00000007");
		vo.setName("野男人们");
		vo.setType(CategoryType.LEAF);
		vo.setFather("总分类/CATE");
		ArrayList<String> com=null;
		vo.setSubCateOrCom(null);
		assertEquals(ResultMessage.SUCCESS,cate.modifyCategory(vo));
	}

	@Test
	public void testDeleteCategory() throws RemoteException {
		String id="CAT-00008";
		assertEquals(ResultMessage.SUCCESS,cate.deleteCategory(id));
		//assertEquals(ResultMessage.SUCCESS,cate.deleteCategory("CAT-00000008"));
		//assertEquals(ResultMessage.SUCCESS,cate.deleteCategory("CAT-00000009"));
	}

	@Test
	public void testGetSubCategoryList() throws RemoteException {
		String id="CAT-00000006";
		ArrayList<String> sub=cate.getSubCategoryList(id);
		if(sub==null){
			System.out.println("为空或叶节点");
		}
		else
			for(String temp:sub){
				System.out.println(sub);
			}
	}

	@Test
	public void testGetCommodityList() throws RemoteException {
		String id="CAT-00000006";
		ArrayList<String> sub=cate.getCommodityList(id);
		if(sub==null){
			System.out.println("为空或分支节点");
		}
		else
		for(String temp:sub){
			System.out.println(sub);}
		}

	@Test
	public void testFindById() throws RemoteException {
		String id="5";
		CategoryVO vo=cate.findById(id);
		System.out.println(vo.getId());
		System.out.println(vo.getName());
		System.out.println(vo.getType());	
		
	}

}
