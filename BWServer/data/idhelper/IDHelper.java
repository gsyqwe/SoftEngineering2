package idhelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IDHelper {
	
	public static String toKBitString(long id, int k) {
		String kBitSuffix = String.valueOf(id);
		if (kBitSuffix.length() > k)
			throw new IllegalArgumentException();
		while (kBitSuffix.length() < k) {
			kBitSuffix = "0" + kBitSuffix;
		}
		return kBitSuffix;
	}
	
	public static String getIDInfixOf(Date date){
		//System.out.println((new SimpleDateFormat("yyyyMMdd")).format(date));  
		return (new SimpleDateFormat("yyyyMMdd")).format(date);
	}
}
