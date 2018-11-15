package categorybl;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import PO.CategoryPO;
import VO.CategoryVO;
import categorydata.CategoryDataUseDataBase;
import categorydataService.CategorydataService;
import enums.CategoryType;
import enums.ResultMessage;

public class Category{
	public static void main(String[] arg) throws RemoteException{
		CategorydataService c=new CategoryDataUseDataBase();
		CategoryPO cate=new CategoryPO();
		cate.setFather(null);
		cate.setId("CAT-00000");
		cate.setName("分类");
		cate.setType(CategoryType.NODE);
		c.insert(cate);
		//Category cate=new Category();
		//ArrayList<String> m=c.getSubCategoryList("CAT-00001");
		//for(int i=0;i<m.size();i++){
			//System.out.println(m.get(i));
		//}
		//ArrayList<String> sub=new ArrayList<String>();
		//sub.add("许墨/COM-00001");
		//CategoryVO vo=new CategoryVO("总分类","CAT-00000001",CategoryType.NODE,null,null);
		//System.out.println(c.insert(vo.toPO()));
		//ArrayList<String> x=cate.getCommodityList("CAT-00000008");
		//for(String temp:x){
		//	System.out.println(temp);
		//}
		//System.out.println(cate.getCommodityList("CAT-00000008"));*/
		//Category cate=new Category();
		//CategoryVO vo=new CategoryVO();
		//vo.setName("总分类");
		//vo.setId("CAT-00001");
		//vo.setType(CategoryType.NODE);
		//CategoryVO vo=new CategoryVO();
		//vo.setFather("总分类/CAT-00001");
		//vo.setName("TEST3");
		//vo.setType(CategoryType.NODE);
		//System.out.println(cate.addCategory(vo));
		//System.out.println(cate.deleteCategory("CAT-00015"));
	}

	CategorydataService categoryDataService = new CategoryDataUseDataBase();

	public Category(){

		categoryDataService=new CategoryDataUseDataBase();

	}

	public ResultMessage addCategory(CategoryVO vo) throws RemoteException {		
		if(categoryDataService.findByName(vo.getName())!=null){
			return ResultMessage.CATEGORY_INSERT_FIAL_NAME_ALREADY_EXIST;
			}
		String[] fatherNameAndID=vo.getFather().split("/");
		String id = "CAT-"+categoryDataService.getMaxID();//得到目前最大的分类编号
		vo.setId(id);
		vo.setFather(fatherNameAndID[1]);
		ResultMessage result=categoryDataService.insert(vo.toPO());
		if(result==ResultMessage.SUCCESS){
			CategoryVO father=categoryDataService.findByID(fatherNameAndID[1]).toVO();
			ArrayList<String> subCate=father.getSubCateOrCom();
			subCate.add(vo.getName()+"/"+categoryDataService.findByName(vo.getName()).getId());
			father.setSubCateOrCom(subCate);
			ResultMessage updateResult=categoryDataService.update(father.getId(), father.toPO());
			return updateResult;
		}
		return result;
		

	}
	public CategoryVO findById(String id){
		CategoryPO po=categoryDataService.findByID(id);
		if(po!=null)
			return po.toVO();
		else
			return null;
	}
	public CategoryVO findByName(String name){
		CategoryPO po=categoryDataService.findByName(name);
		if(po!=null)
			return po.toVO();
		else
			return null;
	}
	public ResultMessage modifyCategory(CategoryVO vo) throws RemoteException {
		return categoryDataService.update(vo.getId(),vo.toPO());

	}

	public ResultMessage deleteCategory(String id) throws RemoteException {
		CategoryVO vo=categoryDataService.findByID(id).toVO();
		if(vo.getSubCateOrCom().size()>0){
			return ResultMessage.CATEGORY_DELETE_FAIL;}
		String[] fatherNameAndID=vo.getFather().split("/");
		CategoryVO father=categoryDataService.findByID(fatherNameAndID[1]).toVO();
		ArrayList<String> subCate=father.getSubCateOrCom();
		int index=-1;
		for(int i=0;i<subCate.size();i++){
			String temp=subCate.get(i);
			String[] nameAndID=temp.split("/");
			if(id.equals(nameAndID[1])){
				index=i;
				break;
			}
		}
		if(index!=-1)
			subCate.remove(index);
		father.setSubCateOrCom(subCate);
		ResultMessage result=categoryDataService.update(father.getId(), father.toPO());
		if(result!=ResultMessage.SUCCESS)
			return result;
		else{
			return categoryDataService.delete(id);
		}
		

	}

	public ArrayList<String> getSubCategoryList(String categoryID) throws RemoteException {
		CategoryPO po=categoryDataService.findByID(categoryID);
		if(po.getType()==CategoryType.LEAF)
			return null;
		else return
				(ArrayList<String>)po.getSubCateOrCom().parallelStream().collect(Collectors.toList());

	}
	public ArrayList<String> getCommodityList(String categoryID){
		CategoryPO po=categoryDataService.findByID(categoryID);
		if(po.getType()==CategoryType.NODE)
			return null;
		else return
				(ArrayList<String>)po.getSubCateOrCom().parallelStream().collect(Collectors.toList());

	}

}