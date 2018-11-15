package emaildataservice;

import java.util.ArrayList;
import java.util.Date;

import PO.EmailPO;
import enums.ResultMessage;

/**
 *
 * @author 82646 因为email没有id,所以用email的receiverID + date来唯一标识email(理论上这样不能唯一确定一个email，但是在咱们的系统中这个就算可以了)
 * 增加一个更新email的方法，把未读改成已读
 */
public interface EmailDataService {

	public ResultMessage insertEmail(EmailPO email);

	public ArrayList<EmailPO> getEmailByUser(String receiverID);

	public ResultMessage deleteEmail(String receiverID, Date date);

	public ResultMessage updateEmail(String receiverID, Date date, EmailPO email);

}
