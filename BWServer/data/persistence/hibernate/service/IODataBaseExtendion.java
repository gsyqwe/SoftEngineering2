package persistence.hibernate.service;

import persistence.txt.service.IOStrategy;

/**
 * 定義數據庫操作的接口
 * 
 * @version 2017/12/15
 * @author Lai Kin Meng
 */
public interface IODataBaseExtendion<T> extends IOStrategy<T> {
	/**
	 * 設定查詢語句
	 * 
	 * @param command
	 */
	public void setSearChCommand(String command);

	/**
	 * 在數據庫刪除一個持久化對像
	 * 
	 * @param object被刪除的對象
	 * @return
	 */
	public Boolean deleteOne(T object);

	/**
	 * 設定查詢語句裡的參數
	 * 
	 * @param parameter設置的參數
	 */
	public void setParameter(String parameter);
}