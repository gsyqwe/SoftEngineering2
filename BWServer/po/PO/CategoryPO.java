package PO;

import java.util.ArrayList;
import java.util.List;

import VO.CategoryVO;
import enums.CategoryType;

public class CategoryPO {
	private static List<String> SUB_CATE_OR_COM_1 = new ArrayList<>();
	private static List<String> SUB_CATE_OR_COM_2 = new ArrayList<>();
	private static List<String> SUB_CATE_OR_COM_3 = new ArrayList<>();
	private static List<String> SUB_CATE_OR_COM_4 = new ArrayList<>();
	private static List<String> SUB_CATE_OR_COM_5 = new ArrayList<>();

	static {
		SUB_CATE_OR_COM_1.add("CAT-00010");
		SUB_CATE_OR_COM_2.add("CAT-00009");
		SUB_CATE_OR_COM_2.add("CAT-00008");
		SUB_CATE_OR_COM_2.add("CAT-00007");
		SUB_CATE_OR_COM_3.add("CAT-00006");
		SUB_CATE_OR_COM_3.add("CAT-00005");
		SUB_CATE_OR_COM_4.add("CAT-00004");
		SUB_CATE_OR_COM_4.add("CAT-00003");
		SUB_CATE_OR_COM_4.add("CAT-00002");
		SUB_CATE_OR_COM_5.add(CommodityPO.SAMPLE_2.getId());
		SUB_CATE_OR_COM_5.add(CommodityPO.SAMPLE_1.getId());
	}

	public static CategoryPO SAMPLE_1 = new CategoryPO("大型燈", "CAT-00001", CategoryType.NODE,
			(ArrayList<String>) SUB_CATE_OR_COM_1, "CAT-00002");
	public static CategoryPO SAMPLE_2 = new CategoryPO("大型燈", "CAT-00002", CategoryType.NODE,
			(ArrayList<String>) SUB_CATE_OR_COM_2, "CAT-00002");
	public static CategoryPO SAMPLE_3 = new CategoryPO("大型燈", "CAT-00003", CategoryType.NODE,
			(ArrayList<String>) SUB_CATE_OR_COM_3, "CAT-00002");
	public static CategoryPO SAMPLE_4 = new CategoryPO("大型燈", "CAT-00004", CategoryType.NODE,
			(ArrayList<String>) SUB_CATE_OR_COM_4, "CAT-00002");
	public static CategoryPO SAMPLE_5 = new CategoryPO("大型燈", "CAT-00005", CategoryType.LEAF,
			(ArrayList<String>) SUB_CATE_OR_COM_5, "CAT-00002");

	protected String name; // 名稱
	/*
	 * key for database
	 */
	protected String id;
	protected CategoryType type;
	/*
	 * database mapping
	 */
	protected List<String> subCateOrCom = new ArrayList<>();
	private boolean isDeleted;
	private String father;

	public CategoryPO() {
	}

	public CategoryPO(String name, String id, CategoryType type, ArrayList<String> subCateOrCom) {
		super();
		this.name = name;
		this.id = id;
		this.type = type;
		this.subCateOrCom = subCateOrCom;
	}

	public CategoryPO(String name, String id, CategoryType type, ArrayList<String> subCateOrCom, String father) {
		super();
		this.name = name;
		this.id = id;
		this.type = type;
		this.subCateOrCom = subCateOrCom;
		this.father = father;
	}

	public CategoryType getType() {
		return type;
	}

	public void setType(CategoryType type) {
		this.type = type;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// for database
	public List<String> getSubCateOrCom() {
		return subCateOrCom;
	}

	// for database
	public void setSubCateOrCom(List<String> subCateOrCom) {
		this.subCateOrCom = subCateOrCom;
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

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public CategoryPO(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	@Override
	public String toString() {
		return "CategoryPO [name=" + name + ", id=" + id + ", type=" + type + ", subCateOrCom=" + subCateOrCom + "]";
	}

	public CategoryVO toVO() {
		ArrayList<String> list = new ArrayList<>(subCateOrCom);
		return new CategoryVO(name, id, type, list,father);
	}

}
