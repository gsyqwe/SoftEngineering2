package emailbl;

import VO.EmailVO;
import emaildata.EmailDataUseDataBase;
import emaildataservice.EmailDataService;
import enums.ResultMessage;

/**
 *
 * @author JiYuzhe
 * 这个类是供系统自己发送消息使用，只是在server里面供其他类调用
 *
 */
public class EmailForServer {

	EmailDataService dataService = new EmailDataUseDataBase();

	public ResultMessage saveEmail(EmailVO email){
		return dataService.insertEmail(email.toPO());
	}

}
