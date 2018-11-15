package export.implement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import export.interpreter.ExportContent;

/**
 * 将ExportContent按照其Position导出至位于filePath的Excel文件
 * 
 * @since 2017/12/25
 * @version 2017/12/27
 * @author 161250051
 *
 */
public class Export {
	// public static void main(String[] args) {
	// BusinessConditionVO vo = new BusinessConditionVO();
	// vo.setBuySaleDifferenceIncome(100);
	// vo.setCommidityCostChangeIncome(120);
	// vo.setCommidityGiftCost(786);
	// vo.setCommidityLostCost(8976);
	// vo.setCommidityOverFlowIncome(987);
	// vo.setIncomeAfterdiscount(687);
	// vo.setVoucherActualDifferenceIncome(7689);
	// vo.setSumOfDiscount(123);
	// vo.setSumOfCost(8712);
	// vo.setSaleIncome(14323);
	// vo.setSaleCost(321456);
	// ExportInterpreter<BusinessConditionVO> interpreter = new
	// ExportInterpreter<>(vo);
	// Export export = new Export(interpreter.getExportContent(),
	// "exportTest.xlsx");
	// export.export();
	//
	// }

	private ExportContent[] content;
	private String filePath;

	public Export(ExportContent[] content, String filePath) {
		this.content = content;
		this.filePath = filePath;
	}

	public Boolean export() {
		// 创建Excel工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 创建一个工作表sheet
		Sheet sheet = workbook.createSheet();
		for (ExportContent ec : content) {
			// 取得对应行数的行
			Row row = sheet.getRow(ec.getPostion().getRow());
			if (row == null) {
				// 创建一行
				row = sheet.createRow(ec.getPostion().getRow());
			}
			// 插入数据
			Cell cell = row.createCell(ec.getPostion().getCol());
			cell.setCellValue(String.valueOf(ec.getContent()));
		}
		File file = new File(filePath);
		try {
			file.createNewFile();
			// 将Excel内容存盘
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
			workbook.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

}
