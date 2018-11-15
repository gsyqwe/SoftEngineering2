package rmi;

import java.rmi.Remote;
import service.CategoryblService;

public class CategoryHelper {
	private Remote remote;
	private static CategoryHelper categoryHelper ;
	public static CategoryHelper getInstance(){
		if(categoryHelper==null)
			categoryHelper=new CategoryHelper();
		return categoryHelper;
	}
	public static CategoryHelper getInstance(Remote r){
		if(categoryHelper==null)
			categoryHelper=new CategoryHelper(r);
		return categoryHelper;
	}
	
	private CategoryHelper() {
	}
	private CategoryHelper(Remote r) {
		this.remote=r;
	}

	public CategoryblService getService(){
		return (CategoryblService) remote;
	}
}
