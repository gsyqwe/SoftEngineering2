package bankaccountdataService;

import java.util.ArrayList;
import java.util.Date;

import PO.BankAccountPO;
import enums.ResultMessage;

/**
 * @version 2017/12/04
 * @author 161250051
 */
public interface BankAccountDataService {

	/**
	 * 按id删除BankAccountPO,并在持久化文件删除,id的格式要求查看BankAccountPO里的注释
	 * 
	 * @param targetID
	 * @return 返回ResultMessage说明结果,详见enums.ResultMessage相应部份
	 */
	public ResultMessage delete(String targetID);

	/**
	 * 根据CardNumber(银行卡号)查找BankAccountPO,银行卡号的格式要求查看BankAccountPO里的注释
	 * 若沒有匹配,返回null,多於一個匹配,返回第一個
	 * 
	 * @param targetCardNumber
	 * @return
	 */
	public BankAccountPO findByCardNumber(String targetCardNumber);

	/**
	 * 根据(银行卡号)查找BankAccountPO,银行卡号的格式要求查看BankAccountPO里的注释
	 * 若沒有匹配,返回null,多於一個匹配,返回第一個
	 * 
	 * @param targetID
	 * @return
	 */
	public BankAccountPO findByID(String targetID);

	/**
	 * 取得持久化文件中所有的BankAccountPO
	 * 
	 * @return 若持久化文件中BankAccountPO为空,返回null
	 */
	public ArrayList<BankAccountPO> getAllList();

	/**
	 * 返回账户id的后缀, id格式详见BankAccountPO的注释
	 * 
	 * @param date生成id的日期
	 * @return 返回最多五位数字代表date这天总共生成了多少张单据(不包括目前这张)
	 * @throws IllegalArgumentException若已超过每天最大生成单据限制,则抛出IllegalArgumentException
	 */
	public String getIDSuffix(Date date) throws IllegalArgumentException;

	/**
	 * 插入BankAccountPO, 并在持久化文件保存, id不能重复
	 * 
	 * @param po
	 * @return 返回ResultMessage说明结果,详见enums.ResultMessage相应部份
	 */
	public ResultMessage insert(BankAccountPO po);

	/**
	 * 更新指定id的BankAccountPO的数据,采用覆盖的方法
	 * 
	 * @param targetId指定的BankAccountPO的id
	 * @param replacement用来覆盖的数据
	 * @return 返回ResultMessage说明结果,详见enums.ResultMessage相应部份
	 */
	public ResultMessage update(String targetId, BankAccountPO replacement);

	/**
	 * 按照賬戶名稱查找銀行賬戶, 若沒有匹配,返回null,若多於一個匹配,返回第一個
	 * 
	 * @param name
	 * @return
	 */
	public BankAccountPO findByName(String name);
}
