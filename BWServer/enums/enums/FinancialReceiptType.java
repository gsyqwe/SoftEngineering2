package enums;

/**                                                          
* Module Comments:  		财务类单据的类型         
* Author:					161250051/Lai Kin Meng            
* Create Date： 			 	2017-10-25 
* Modified By：  				161250051/Lai Kin Meng                                         
* Modified Date:            2017-11-06                    
* Why & What is modified：    added comments; added a "_" between two words 
* 
* Last Modified by:JiYuzhe 在枚举类里面添加toString方法方便在界面层显示，而且是私有的构造函数，不会影响已有的其他类
*/
public enum FinancialReceiptType {
	BILL("Bill"), // 付款单
	DEBIT_NOTE("Debit_note"), // 收款单
	CASH_CLAIM("Cash_claim"); // 现金费用单
	
	private String name;
	
	private FinancialReceiptType(String name){//一定要声明为私有的构造函数
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
