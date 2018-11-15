package enums;

public enum JobType {

	FINANCIAL("财务人员"), MANAGER("总经理"), SALESMAN("销售人员"), INVENTORY("库存管理人员"),ADMINOR("管理员");

	

	private String name;

	

	private JobType(String name){

		this.name = name;

	}



	 public static JobType getEnumByValue(String name){

	        for(JobType a : JobType.values()){

	            if(a.name.equals(name)){

	                return a;   

	            }

	        }

	    return null;

	 }
	 public String toString(){
		 return name;
	 }
	 /*public String toString(){
		 if(name.equals("管理员"))
			 return "管理员";
		 else if(name.equals("c"))
			 return "财务人员";
		 else if(name.equals(INVENTORY))
			 return "库存管理人员";
		 else if(name.equals(MANAGER))
			 return "经理";
		 else 
			 return "销售人员";
	 }*/

};