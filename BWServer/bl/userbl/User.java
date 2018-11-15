package userbl;

import java.util.ArrayList;
import PO.UserPO;
import VO.UserVO;
import enums.JobType;
import enums.ResultMessage;
import userdata.UserDataUseDataBase;
import userdataService.UserDataService;

public class User {
	UserDataService userDataService;
	/*public static void main(String[] arg){
		User u=new User();
		ArrayList<UserVO> po=u.getAllList();
		for(int i=0;i<po.size();i++){
			System.out.println(po.get(i).getName());
		}
		UserVO vo=new UserVO("","112233",JobType.getEnumByValue("管理员"),"许墨");
		System.out.println(u.addUser(vo));
		//System.out.println(u.deleteUser("MAN-004"));
	}*/
	public User(){
		userDataService=new UserDataUseDataBase();
	}
	 public ResultMessage Log(String ID,String Password){
		 UserPO po=userDataService.findByID(ID);
		 if(po==null)
			 return ResultMessage.LOGIN_FAIL_USER_NOT_FOUND;
		 else if(po.getPassword().equals(Password))
			 return ResultMessage.SUCCESS;
		 else
			 return ResultMessage.LOGIN_FAIL_WRONG_PASSWORD;
	 }
     public ArrayList<UserVO> getAllList(){
    	 ArrayList<UserPO> users=userDataService.getAllList();
    	 ArrayList<UserVO> vo=new ArrayList<UserVO>();
    	 for(UserPO temp:users)
    		 vo.add(new UserVO(temp.getId(),temp.getPassword(), temp.getJob(),temp.getName()));
    	 return vo;
     }
     public ResultMessage addUser(UserVO user){
    	String  id=userDataService.getIDSuffix(user.getJob());
    	if(user.getJob()==JobType.MANAGER)
    		user.setId("MAN-"+id);
    	else if(user.getJob()==JobType.FINANCIAL)
    		user.setId("FIN-"+id);
    	else if(user.getJob()==JobType.INVENTORY)
    		user.setId("INT-"+id);
    	else if(user.getJob()==JobType.SALESMAN)
    		user.setId("SAL-"+id);
    	else
    		user.setId("AMD-"+id);
    	return userDataService.insert(user.toPO());
     }
     public ResultMessage deleteUser(String ID){
    	 return userDataService.delete(ID);
     }
     public ResultMessage modifyUser(UserVO user){
    	 return userDataService.update(user.getId(),user.toPO());
     }
     public UserVO findByID(String ID){
    	 return userDataService.findByID(ID).toVO();
     }
     public String getMaxId(JobType job){
    	 return userDataService.getIDSuffix(job);
     }
}
