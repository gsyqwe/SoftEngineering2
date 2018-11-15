package enums;

public enum MemberVipLevel {
	ORDINARY("Ordinary"), BRONZE("Bronze"), SLIVER("Sliver"), GOLD("Gold"), DIAMOND("Diamond");
	
	private String name;
	
	private MemberVipLevel(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	public static MemberVipLevel getEnumByValue(String name){
        for(MemberVipLevel a :MemberVipLevel.values()){
            if(a.name.equals(name)){
                return a;   
            }
        }
    return null;
 }
}
