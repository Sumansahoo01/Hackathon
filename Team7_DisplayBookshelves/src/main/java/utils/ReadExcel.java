package utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public static String read(int a,int b) throws Exception
	{
	//Getting the relative path for excel from source folder
	String filepath=System.getProperty("user.dir")+"\\src\\test\\resources\\Input.xlsx";
	//Create an object of FileInputStream class to read excel file
	FileInputStream file=new FileInputStream(new File(filepath));
	//creating workbook instance that refers to .xlsx file
	XSSFWorkbook wb=new XSSFWorkbook(file);
	//creating sheet object using the sheet value
	XSSFSheet ws=wb.getSheet("Sheet1");
	//Getting data from excel sheet at row a and column b
	String testData=String.valueOf(ws.getRow(a).getCell(b));
	wb.close();
	return testData;
	}

}
 