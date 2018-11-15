package export.interpreter;

import java.util.ArrayList;
import java.util.Date;

import export.Exportable;
import export.annotation.ExportField;

public class Tester implements Exportable<Tester> {

	@ExportField(name = "字符串", nameRow = 0, nameCol = 0)
	private String string;

	@ExportField(name = "整數", nameRow = 0, nameCol = 1)
	private int intt;

	@ExportField(name = "日期", nameRow = 0, nameCol = 2)
	private Date date;

	@ExportField(name = "列表", nameRow = 0, nameCol = 3)
	private ArrayList<InsideClass> list;

	public ArrayList<InsideClass> getList() {
		return list;
	}

	public void setList(ArrayList<InsideClass> list) {
		this.list = list;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public int getIntt() {
		return intt;
	}

	public void setIntt(int intt) {
		this.intt = intt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	static public class InsideClass implements Exportable<InsideClass> {
		@ExportField(name = "內部類字符串", nameRow = 0, nameCol = 0)
		private String string;

		@ExportField(name = "內部類日期", nameRow = 0, nameCol = 1)
		private Date date;

		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

	}

}
