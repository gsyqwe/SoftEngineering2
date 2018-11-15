package export.interpreter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import export.Exportable;
import export.annotation.ExportField;

/**
 * 利用反射機制和ExportField解釋需導出的字段,並取得其值
 * 
 * @author 161250051
 *
 * @param <T>
 */
public class ExportInterpreter<T> implements ExportInterpreterService {
	private Exportable<T>[] exportDatas;
	private Field[] fields;
	private Method[] methods;
	private ExportField[] exportFields;

	/**
	 * 傳入一個導出數據
	 * 
	 * @param exportData
	 */
	public ExportInterpreter(Exportable<T> exportData) {
		checkExportData(exportData); // 檢查前置條件
		this.exportDatas = new Exportable[1];
		this.exportDatas[0] = exportData;
		initMemberData(this.exportDatas[0]);
	}

	/**
	 * 傳入多個導出數據
	 * 
	 * @param exportDatas
	 */
	public ExportInterpreter(Exportable<T>[] exportDatas) {
		checkExportData(exportDatas); // 檢查前置條件
		this.exportDatas = exportDatas;
		initMemberData(this.exportDatas[0]);
	}

	protected void checkExportData(Exportable<T> exportData) {
		if (exportData == null)
			throw new IllegalArgumentException("導出數據為空");
	}

	protected void checkExportData(Exportable<T>[] exportData) {
		System.out.println((null == exportData));
//		for(Exportable ex: exportData){
//			System.out.println(ex);
//		}
		if (exportData == null || exportData.length == 0)
			throw new IllegalArgumentException("導出數據為空");
	}

	/**
	 * // 利用反射機制取得exportable的所有變量和方法
	 * 
	 * @param exportable
	 */
	protected void initMemberData(Exportable<T> exportable) {
		fields = exportable.getClass().getDeclaredFields();
		methods = exportable.getClass().getDeclaredMethods();
		exportFields = new ExportField[fields.length];
		for (int i = 0; i < fields.length; i++) {
			exportFields[i] = fields[i].getAnnotation(ExportField.class);
			// System.err.println(exportFields[i]);
		}
	}

	/**
	 * 利用ExportField解釋並生成導出內容
	 */
	public ExportContent[] getExportContent() {
		Set<ExportContent> exportContents = new HashSet<>();
		for (int i = 0; i < exportFields.length; i++) {
			ExportField exportField = exportFields[i];
			// 因為不是全部Field都導出, 因為exportField可能為空
			if (exportField != null) {
				// 字段名稱
				exportContents.add(new ExportContentImpl(
						new ExportPosition(exportField.nameRow(), exportField.nameCol()), exportField.name()));
				try {
					// 字段數值
					for (int j = 0; j < exportDatas.length; j++) {
						exportContents.add(new ExportContentImpl(
								new ExportPosition(exportField.nameRow() + j + 1, exportField.nameCol()),
								this.findGetterOf(fields[i]).invoke(exportDatas[j]))); // 反射調用Getter方法
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				// if (exportField.isCollection()) {
				// try {
				// Collection<? extends Exportable> collection = (Collection<?
				// extends Exportable>) this
				// .findGetterOf(fields[i]).invoke(exportables);
				// for (Exportable element : collection) {
				// ExportInterpreter<Object> exportInterpreter = new
				// ExportInterpreter<Object>(element);
				// ExportContent[] exportContents2 =
				// exportInterpreter.getExportContent();
				// for (ExportContent ec : exportContents2) {
				// ec.getPostion().setRow(ec.getPostion().getRow() +
				// exportField.valueRow());
				// ec.getPostion().setCol(ec.getPostion().getCol() +
				// exportField.valueCol());
				// exportContents.add(ec);
				// }
				// // output
				// // for(int j=0;j<exportContents2.length;j++){
				// // System.err.println(exportContents2[j]);
				// // }
				// }
				// } catch (IllegalAccessException | IllegalArgumentException |
				// InvocationTargetException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// }
			}
		}
		// exportContents.parallelStream().forEach(s -> System.out.println(s));
		return (ExportContent[]) exportContents.toArray(new ExportContent[0]);

	}

	/**
	 * 利用反射機制調用導出字段的Getter方法
	 * 
	 * @param field
	 * @return
	 */
	protected Method findGetterOf(Field field) {
		for (Method method : methods) {
			if (method.getName()
					.equals("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1))) {
				return method;
			}
		}
		return null;
	}
}
