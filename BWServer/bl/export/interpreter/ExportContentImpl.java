package export.interpreter;

/**
 * 具體的導出內容類
 * 
 * @author 161250051
 *
 */
public class ExportContentImpl implements ExportContent {
	private ExportPosition position;
	private Object content;

	public ExportContentImpl(ExportPosition position, Object content) {
		this.position = position;
		this.content = content;
	}

	@Override
	public Object getContent() {
		return this.content;
	}

	@Override
	public ExportPosition getPostion() {
		return this.position;
	}

	@Override
	public String toString() {
		return "ExportContentImpl [position=" + position + ", content=" + content + "]";
	}

}
