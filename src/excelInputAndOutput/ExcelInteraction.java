package excelInputAndOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelInteraction {
	Workbook workbook;
	FileInputStream stream;
	
	public String getCellData(String filePath,String fileName,String sheetName,int rowno,int colno) throws IOException{
		File file = new File(filePath+"\\"+fileName);
		stream = new FileInputStream(file);
		
		String fileExtension = fileName.substring(fileName.indexOf("."));
		if(fileExtension.equalsIgnoreCase(".xls")){
			workbook = new HSSFWorkbook(stream);
		}else if(fileExtension.equalsIgnoreCase(".xlsx")){
			workbook = new XSSFWorkbook(stream);
		}
		
		Sheet sheet = workbook.getSheet(sheetName);
		
		Row row = sheet.getRow(rowno);
		
		Cell cell = row.getCell(colno);
		
		String data = cell.getStringCellValue().trim();
		
		return data;
		
		
	}
	
	
	

}
