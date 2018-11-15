package enums;

import java.io.Serializable;

public enum level implements Serializable{

	ordinary("Ordinary"), Bronze("Bronze"), Sliver("Sliver"), gold("Gold"), Diamond("Diamond");
	private String name;

	private level(String name){
		this.name = name;
	}

	 public static level getEnumByValue(String name){
	        for(level a : level.values()){
	            if(a.name.equals(name)){
	                return a;
	            }
	        }
	    return null;
	 }
}
