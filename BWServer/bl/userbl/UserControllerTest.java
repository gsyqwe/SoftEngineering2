package userbl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import VO.UserVO;
import enums.JobType;
import enums.ResultMessage;

public class UserControllerTest {
	public static UserController user;
	@Before
	public void setUp() throws Exception {
		user=new UserController();
	}

	@Test
	public void testLog() throws RemoteException {
		String id="00000";
		String password="112233";
		assertEquals(ResultMessage.SUCCESS,user.Log(id, password));
	}

	@Test
	public void testAddUser() throws RemoteException {
		UserVO vo=new UserVO();
		vo.setId("");
		vo.setJob(JobType.MANAGER);
		vo.setName("李泽言");
		vo.setPassword("112233");
		assertEquals(ResultMessage.SUCCESS,user.addUser(vo));
	}

	@Test
	public void testDeleteUser() throws RemoteException {
		assertEquals(ResultMessage.SUCCESS,user.deleteUser("MAN-00001"));
	}

	@Test
	public void testModifyUser() throws RemoteException {
		UserVO vo=new UserVO();
		vo.setId("MAN-00001");
		vo.setJob(JobType.MANAGER);
		vo.setName("李泽言");
		vo.setPassword("111111");
		assertEquals(ResultMessage.SUCCESS,user.modifyUser(vo));
	}

}
