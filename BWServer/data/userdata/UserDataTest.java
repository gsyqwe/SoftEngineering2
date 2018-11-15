package userdata;

import static org.junit.Assert.*;

import org.junit.Test;

import PO.UserPO;
import enums.JobType;
import enums.ResultMessage;
import persistence.txt.implement.IOStrategyUseTxt;
import persistence.txt.service.IOStrategy;

public class UserDataTest {
	IOStrategy<UserPO> ioStrategy = new IOStrategyUseTxt<>("User_Test");
	UserDataUseTxt userData = new UserDataUseTxt();

	@Test
	public void testInsertDelete1() {
		assertEquals(ResultMessage.USER_INSERT_SUCCESS, userData.insert(UserPO.SAMPLE_1));
		assertEquals(ResultMessage.USER_INSERT_SUCCESS, userData.insert(UserPO.SAMPLE_2));
		assertEquals(ResultMessage.USER_INSERT_SUCCESS, userData.insert(UserPO.SAMPLE_3));
		assertEquals(ResultMessage.USER_INSERT_SUCCESS, userData.insert(UserPO.SAMPLE_4));

		assertEquals(ResultMessage.USER_DELETE_SUCCESS, userData.delete(UserPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.USER_DELETE_SUCCESS, userData.delete(UserPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.USER_DELETE_SUCCESS, userData.delete(UserPO.SAMPLE_3.getId()));
		assertEquals(ResultMessage.USER_DELETE_SUCCESS, userData.delete(UserPO.SAMPLE_4.getId()));
	}

	@Test
	public void testInsertDelete2() {
		assertEquals(ResultMessage.USER_INSERT_SUCCESS, userData.insert(UserPO.SAMPLE_1));
		assertEquals(ResultMessage.USER_INSERT_SUCCESS, userData.insert(UserPO.SAMPLE_2));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED, userData.insert(UserPO.SAMPLE_1));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_REPEATED, userData.insert(UserPO.SAMPLE_2));

		assertEquals(ResultMessage.USER_DELETE_SUCCESS, userData.delete(UserPO.SAMPLE_1.getId()));
		assertEquals(ResultMessage.USER_DELETE_SUCCESS, userData.delete(UserPO.SAMPLE_2.getId()));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, userData.delete(UserPO.SAMPLE_3.getId()));
		assertEquals(ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND, userData.delete(UserPO.SAMPLE_4.getId()));
	}

	@Test
	public void testUpdate() {
		UserPO update1 = new UserPO("MAN-001", "9999999", JobType.SALESMAN, "賴健明");
		UserPO update2 = new UserPO("MAN-003", "abcdefg", JobType.MANAGER, "袁玉婷");

		assertEquals(ResultMessage.USER_INSERT_SUCCESS, userData.insert(UserPO.SAMPLE_1));
		assertEquals(ResultMessage.USER_INSERT_SUCCESS, userData.insert(UserPO.SAMPLE_3));

//		assertEquals(ResultMessage.USER_UPDATE_SUCCESS, userData.update(UserPO.SAMPLE_1.getId(), update1));
//		assertEquals(ResultMessage.USER_UPDATE_SUCCESS, userData.update(UserPO.SAMPLE_3.getId(), update2));

		assertEquals(ResultMessage.USER_DELETE_SUCCESS, userData.delete(update1.getId()));
		assertEquals(ResultMessage.USER_DELETE_SUCCESS, userData.delete(update2.getId()));

	}

	@Test
	public void testGetAllList() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIDSuffix() {
		fail("Not yet implemented");
	}

}
