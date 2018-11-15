package financelerui;

import java.rmi.RemoteException;

import rmi.BankAccountHelper;
import VO.LineItemVO;

public class AccountName {
	LineItemVO lineitem;
	String name;
	String reason;
	String price;

	public AccountName(LineItemVO lineitem) {
		this.lineitem=lineitem;
		BankAccountHelper bankHelper = BankAccountHelper.getInstance();
		try {
			name = bankHelper.getService().findByID(lineitem.getId()).getName();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reason=lineitem.getComment();
		price=Double.toString(lineitem.getPrice());
	}
	public String GetName(){
		return name;
	}
	public String GetReason(){
		return reason;
	}
	public String GetPrice(){
		return price;
	}
	public LineItemVO GetLineItem(){
		return lineitem;
	}
}

