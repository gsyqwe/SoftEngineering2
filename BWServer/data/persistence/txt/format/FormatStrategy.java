package persistence.txt.format;

/**
 * 使用特定格式解析和反解析對象屬性
 * 
 * @version 2017/12/5 updated
 * @since 2017/11/25
 * @author 161250051
 *
 */
public interface FormatStrategy {
	/**
	 * 將一個對象的屬性按一定格式轉換成字符串
	 * 
	 * @param object要轉換成字符串的對象
	 * @return object的屬性字符串
	 */
	public String format(Object object);

	/**
	 * 將一個字符串按一定格式轉換成對象
	 * 
	 * @param string要轉換成對象的字符串
	 * @param clazz要轉換成的類
	 * @return clazz的實例具有string所包括的屬性和值
	 */
	public Object unFormat(String string, Class<?> clazz);
}
