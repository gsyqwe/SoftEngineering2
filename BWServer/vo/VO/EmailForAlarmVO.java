package VO;

import java.util.ArrayList;
import java.util.Date;

public class EmailForAlarmVO extends EmailVO{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public EmailForAlarmVO(String receiver, String senderID, Date date) {
		// TODO Auto-generated constructor stub
		super(receiver,senderID,date);
	}

	public static ArrayList<EmailForAlarmVO> generateEmail(String senderID,ArrayList<String> receiverID,Date date){
		ArrayList<EmailForAlarmVO> emailList = new ArrayList<>();

		for(String receiver:receiverID){
			EmailForAlarmVO email = new EmailForAlarmVO(receiver,senderID,date);
			emailList.add(email);
		}

		return emailList;
	}

	public String getTitleByAlarm(){
		return "Auto-Generate:Commodity alarm";
	}

	public String getTitleByAlarm(int a){
		return "Auto-Generate:Commodity alarm please buy some stock";
	}

	public static ArrayList<EmailForAlarmVO> AddEmailItem(CommodityVO commodity,ArrayList<EmailForAlarmVO> emails){//给一个emailList添加一项内容
		for(EmailForAlarmVO email:emails){
			String content = email.getContent(commodity);
			email.setTitle(email.getTitleByAlarm());
			email.addContentItem(content);
		}

		return emails;
	}

	public static ArrayList<EmailForAlarmVO> AddEmailItem(CommodityVO commodity,ArrayList<EmailForAlarmVO> emails,int a){//给一个emailList添加一项内容
		for(EmailForAlarmVO email:emails){
			String content = email.getContent(commodity,a);
			email.setTitle(email.getTitleByAlarm(a));
			email.addContentItem(content);
		}

		return emails;
	}

	public String getContent(CommodityVO commodity){//得到一行的内容
		return commodity.getName() + "'s quantity in repository is below the alarm rate. Now quantity is " + commodity.getQuantity() +
				" and alarm quantity is " + commodity.getAlertQuantity();
	}

	public String getContent(CommodityVO commodity,int a){//传入一个多余的int参数，这个是发送给进货销售人员的
		return "Please purchase the commodity:" + commodity.getName() + "immediately because the commodity is below the alarm quantity.";
	}

}
