package VO;

import java.io.Serializable;
import java.util.ArrayList;

import PO.CategoryPO;
import enums.CategoryType;

public class CategoryVO implements Serializable {// 鍟嗗搧鍒嗙被
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name; // 鍚嶇ū
	protected String id;
	protected CategoryType type;
	protected ArrayList<String> subCateOrCom = new ArrayList<>();
	protected String father;
	
	public CategoryVO(String name, String id, CategoryType type,
			ArrayList<String> subCateOrCom,String father) {
		super();
		this.name = name;
		this.id = id;
		this.type = type;
		this.subCateOrCom = subCateOrCom;
		this.father=father;
	}
	public CategoryVO() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CategoryType getType() {
		return type;
	}
	public void setType(CategoryType type) {
		this.type = type;
	}
	public ArrayList<String> getSubCateOrCom() {
		return subCateOrCom;
	}
	public void setSubCateOrCom(ArrayList<String> subCateOrCom) {
		this.subCateOrCom = subCateOrCom;
	}
	
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public CategoryPO toPO(){
		CategoryPO po=new CategoryPO(name, id, type, subCateOrCom,father);
		return po;
	}

}
