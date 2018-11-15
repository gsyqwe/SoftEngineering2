package persistence.txt.format;

import com.google.gson.Gson;

public class JsonFormat implements FormatStrategy {

	@Override
	public String format(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

	@Override
	public Object unFormat(String string, Class<?> clazz) {
		Gson gson = new Gson();
		return gson.fromJson(string, clazz);
	}

}