package com.edureka.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataReader {

	HashMap<String,String> ExcelColumn;
	XSSFWorkbook wb;
public static void main(String[] args) throws IOException
{	
    
   ExcelDataReader d= new ExcelDataReader("C:\\Users\\dhruvt\\Desktop\\Edureka_TestData.xlsx");
   d.getColumnData("Registration", "Test_036");
   HashMap<String, String> data = d.getColumnData("Registration", "Test_036");
   System.out.println(data.get("Username"));
}
	
    public ExcelDataReader(String excelpath)
    {
	  try 
		{
			File src = new File(excelpath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} 
		catch (Exception e) 
		{
		  System.out.println(e.getMessage());  
		} 
	 }
    public String getData(String Sheetname,int row ,int col)
    {
	  XSSFSheet sheet = wb.getSheet(Sheetname);
	  String data = sheet.getRow(row).getCell(col).getStringCellValue();
	  return data;
     }
    public double getNumericData(int SheetNumber,int row, int col){
    	XSSFSheet sheet = wb.getSheetAt(SheetNumber);
    	double a = sheet.getRow(row).getCell(col).getNumericCellValue();
    	return a;
    }
    public int getRowCount(int SheetNumber){
    	XSSFSheet sheet = wb.getSheetAt(SheetNumber);
    	int count = sheet.getLastRowNum();
    	count = count+1;
    	return count;
    }
    
    public HashMap<String,String> getColumnData(String sheetName,String RowName){
    	
    	int Col = getColNumber(sheetName, RowName);
    	XSSFSheet sheet = wb.getSheet(sheetName);
    	int LastRowNumber = sheet.getLastRowNum();
        ExcelColumn = new HashMap<String, String>(); 
    	 for(int a = 0;a<LastRowNumber;a++)
    	 {
    		 String KEY = sheet.getRow(a).getCell(0).getStringCellValue();
    		 System.out.println(KEY);
             String VALUE = sheet.getRow(a).getCell(Col).getStringCellValue();
             System.out.println(VALUE);
    		 ExcelColumn.put(KEY, VALUE);
    	 }
    	
    	return ExcelColumn;
    } 
    private int getColNumber(String sheetname,String RowName){
    	int a = -1;
    	int row = wb.getSheet(sheetname).getLastRowNum();
        int i = wb.getSheet(sheetname).getPhysicalNumberOfRows();
        String[] d1 = new String[row];
        for (int j = 0;j<i-1;j++)
        {
        	d1[j] = wb.getSheet(sheetname).getRow(0).getCell(j).getStringCellValue();
        	String word = d1[j];
        	if (RowName.equalsIgnoreCase(word)){
        		System.out.println("+++++++++++True++++++++++++: "+j);
                 a = j;
         }
        }
       return a;
    }
    
}

