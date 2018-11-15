package categorydataService;

import PO.CategoryPO;
import enums.ResultMessage;

/**
 * @version 2017/12/04
 * @author 161250051
 *
 */
public interface CategorydataService {

	/**
	 * 在持久化文件中添加一个CategoryOfLeafPO
	 * 
	 * @param po要增加的CategoryOfLeafPO
	 * @return 返回ResultMessage说明结果,详见enums.ResultMessage相应部份
	 */
	public ResultMessage insert(CategoryPO po);
	
	public CategoryPO findByID(String ID);
	
	public CategoryPO findByName(String name);

	/**
	 * 删除指定id的CategoryOfNodePO/CategoryOfLeafPO, 注意两者的id格式有区别
	 * 
	 * @param id
	 * @return 返回ResultMessage说明结果,详见enums.ResultMessage相应部份
	 */
	public ResultMessage delete(String id);

	/**
	 * 更新指定id的数据, 采用覆盖的方法
	 * 
	 * @param targetID指定要更新PO的id
	 * @param replacement用来覆盖的PO
	 * @return 返回ResultMessage说明结果,详见enums.ResultMessage相应部份
	 */
	public ResultMessage update(String targetID, CategoryPO replacement);

	/*
	 * 返回当前存在的分类的最大编号
	 */
	public String getMaxID();
}

