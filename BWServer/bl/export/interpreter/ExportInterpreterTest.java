package export.interpreter;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import export.implement.Export;
import export.interpreter.Tester.InsideClass;

public class ExportInterpreterTest {

	@Test
	public void test() {
		Tester test = new Tester();
		test.setDate(new Date());
		test.setIntt(897);
		test.setString("fuck");
		InsideClass insideClass1 = new InsideClass();
		insideClass1.setDate(new Date(8976589));
		insideClass1.setString("you");
		InsideClass insideClass2 = new InsideClass();
		insideClass2.setDate(new Date(8976589));
		insideClass2.setString("you");
		InsideClass insideClass3 = new InsideClass();
		insideClass3.setDate(new Date(8976589));
		insideClass3.setString("you");
		ArrayList<InsideClass> list = new ArrayList<>();
		list.add(insideClass1);
		list.add(insideClass2);
		list.add(insideClass3);
		test.setList(list);
		ExportInterpreter<Tester> interpreter = new ExportInterpreter<>(test);
		ExportContent[] exportContents = interpreter.getExportContent();
		Export export = new Export(exportContents, "fuck_.xlsx");
		export.export();
		for (int i = 0; i < exportContents.length; i++) {
			System.err.println(exportContents[i]);
		}
	}

}
