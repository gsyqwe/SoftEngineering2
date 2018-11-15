package VO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailForAuditVO extends EmailVO{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public EmailForAuditVO(String receiverID,String senderID,Date date){
		super(receiverID,senderID,date);
	}

	public String getContent(String receiptID,boolean pass){
		String note;
		if(pass){
			note = "has passed audit.";
		}
		else{
			note = "hasn't passed audit.";
		}
		return "You receipt " + receiptID + " " + note;
	}

	public static String setTitleByPass(boolean pass){
		if(pass){
			return "Auto-generate Your receipt has been passed";
		}
		else{
			return "Auto-generate Your receipt has not been passed";
		}
	}

	public static EmailForAuditVO generateEmail(String senderID,String receiverID,Date date){
		EmailForAuditVO email = new EmailForAuditVO(receiverID,senderID,date);
		return email;
	}

	public static EmailForAuditVO generateEmail(String senderID,String receiverID,Date date,String receiptID,boolean pass){
		EmailForAuditVO email = EmailForAuditVO.generateEmail(senderID, receiverID, date);
		String content = email.getContent(receiptID, pass);
		List<String> contents = new ArrayList<>();
		contents.add(content);
		email.setContent(contents);
		email.setTitle(setTitleByPass(pass));
		return email;
	}

}
