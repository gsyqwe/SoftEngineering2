package export.interpreter;

/**
 * 導出的內容, 具有內容和位置
 * 
 * @author 161250051
 *
 */
public interface ExportContent {
	/**
	 * 取得在導出文件中的位置
	 * 
	 * @return
	 */
	public ExportPosition getPostion();

	/**
	 * 取得在導出至文件的內容
	 * 
	 * @return
	 */
	public Object getContent();
}
