package com.framaework.commonutilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.testng.Reporter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelReaderExpected {

	static Workbook workbook;
	static HashMap<String, String> hm_expectedMessage;
	static Sheet sheet;
	
	public static void connectExcel() throws BiffException{
		try {
			workbook = Workbook.getWorkbook(new File("data_source/Validation.xls"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log("Fail: Cannot connect to expected message excel file");
		}
	}
	
	public static Sheet getSheet(String sheetName) throws IOException{
		sheet = workbook.getSheet(sheetName);
		return sheet;	
		
	}
	
	public static void putHM_ExpectedDataObject(String sheetName) throws BiffException{
		int rows = 0;
		String key = null;
		String value = null;
		hm_expectedMessage = new HashMap<String, String>();
		sheet = workbook.getSheet(sheetName);
		rows = sheet.getRows();
		System.out.println("no of rows:  "+ rows);
		for(int i=0;i<=rows-1;i++){
			key = sheet.getCell(0,i).getContents();
			value = sheet.getCell(1,i).getContents();
			hm_expectedMessage.put(key, value);
		}
	}
	
	public static String get_ExpectedMessage(String sheetName, String key) throws BiffException{
		putHM_ExpectedDataObject(sheetName);
		return hm_expectedMessage.get(key).toString();
		
	}

	public String[][] getExcelData(String fileName, String sheetName) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);
			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();
			
			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
			
			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	
}
