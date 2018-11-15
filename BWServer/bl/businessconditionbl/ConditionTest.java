package businessconditionbl;

import java.rmi.RemoteException;
import java.util.Date;

import VO.BusinessConditionVO;

public class ConditionTest {

	public static void main(String [] args){
		ConditionTest test = new ConditionTest();
		test.testCondition();
	}

	public void testCondition(){
		try {
			BusinessConditionController controller = new BusinessConditionController();
			@SuppressWarnings("deprecation")
			Date endDate = new Date(118,5,1,1,1,1);
			@SuppressWarnings("deprecation")
			Date beginDate = new Date(117,1,1,1,1,1);
			BusinessConditionVO condition = controller.getBusinessConditionList(beginDate, endDate);
			System.out.println(condition);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
