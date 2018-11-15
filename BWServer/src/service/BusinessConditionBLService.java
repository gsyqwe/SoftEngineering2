package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import VO.BusinessConditionVO;

/*                                                          
* Module Comments:  �w�q��{�d�ݸg�籡�p��ɭ��һݪ��ѱ��f                  
* Author:	161250051/Lai Kin Meng            
* Create Date�G  2017-10-25 
* Modified By�G   161250051/Lai Kin Meng                                         
* Modified Date:  2017-11-06                                
* Why & What is modified�Gadd comments                     
*/ 
public interface BusinessConditionBLService extends Remote{
	
	/**
	 * BusinessCondition只有VO没有PO，先将所有符合条件的销售单，库存单找出来，然后分条计算，最后打包将所有数据传给界面层，只有一个方法
	 */
	public BusinessConditionVO getBusinessConditionList(Date startTime,Date endTiome) throws RemoteException;
}
