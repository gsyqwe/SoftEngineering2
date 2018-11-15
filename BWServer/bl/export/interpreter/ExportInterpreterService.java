package export.interpreter;

/**
 * 導出解釋器所提供的服務
 * 
 * @author 161250051
 *
 */
public interface ExportInterpreterService {
	/**
	 * 取得需要導出的內容
	 * 
	 * @return
	 */
	public ExportContent[] getExportContent();
}
