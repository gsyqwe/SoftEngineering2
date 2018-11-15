package userdataService;

import java.util.ArrayList;

import PO.UserPO;
import enums.JobType;
import enums.ResultMessage;

/**
 *
 * @version 2017/12/29
 * @since 2017/11/10
 * @author 161250032
 *
 */
public interface UserDataService {

	/**
	 * 在持久化文件裡刪除編號為id的持久化對象 若沒有匹配id,返回ResultMessage.OPERATE_FAIL_ID_NOT_FOUND
	 *
	 * @param id
	 * @return
	 */
	public ResultMessage delete(String id);

	/**
	 * 在持久化文件裡查找並返回編號為targetID的持久化對象 若沒有匹配,返回null
	 *
	 * @param tartgetID
	 * @return
	 */
	public UserPO findByID(String tartgetID);

	/**
	 * 取得持久化文件裡所有的持久化對象 若持久化文件裡沒有持久化對像,則返回null;
	 *
	 * @return
	 */
	public ArrayList<UserPO> getAllList();

	/**
	 * 在持久化文件裡追加一個持久化對象 若編號重覆,返回ResultMessage.OPERATE_FAIL_ID_NOT_FOUND
	 *
	 * @param po
	 * @return
	 */
	public ResultMessage insert(UserPO po);

	/**
	 * 在持久化文件裡更新編號為targetId,並用replacement覆蓋舊有數據 *
	 * 若沒有匹配targetId,返回ResultMessage.OPERATE_FAIL_ID_NOT_FOUND
	 *
	 * @param targetId
	 * @param replacement
	 * @return
	 */
	public ResultMessage update(String targetId, UserPO replacement);

	/**
	 * 查看持久化文件裡職位為jobType(詳見enums.JobType)的總數
	 *
	 * @param jobType
	 * @return
	 */
	public String getIDSuffix(JobType jobType);

	public ArrayList<UserPO> findByUserType(JobType type);

}
