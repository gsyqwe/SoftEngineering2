package export.interpreter;

/**
 * 用作表示在導出文件中的位置
 * 
 * @author 161250051
 *
 */
public class ExportPosition {
	/*
	 * 頁數
	 */
	private int sheet;
	/*
	 * 行數
	 */
	private int row;
	/*
	 * 列數
	 */
	private int col;

	public ExportPosition() {
	}

	public ExportPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public int getSheet() {
		return sheet;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setSheet(int sheet) {
		this.sheet = sheet;
	}

	@Override
	public String toString() {
		return "ExportPosition [sheet=" + sheet + ", row=" + row + ", col=" + col + "]";
	}

}
