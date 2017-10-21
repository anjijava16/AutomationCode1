package com.edureka.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Reporter;



public class SpreadsheetOperations {

    public String path;
    public FileInputStream fis;
    public FileOutputStream fileOut;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    private static XSSFCell cell;
    private static PropertyReader propObj;
    
    


    public  SpreadsheetOperations() {
        propObj = new PropertyReader();

        System.setProperty("org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.Jdk14Logger");


    }


    public void getPerformanceData(String extension, String fileName, String sheetName) throws Exception{
        int aa = SpreadsheetOperations.getRowCount("report","report");
        ArrayList<String> label = new ArrayList<String>();
        ArrayList<String> jsRT= new ArrayList<String>();
        ArrayList<String> jsSize= new ArrayList<String>();
        ArrayList<String> jsURL= new ArrayList<String>();

        double expectedPageSize = propObj.readPerformanceData(fileName+extension+"PageSize");
        double expectedResponseTime = propObj.readPerformanceData(fileName+extension+"ResponseTime");
        double expectedNoRequest = propObj.readPerformanceData(fileName+extension+"NoOfRequest");

        extension = "."+extension;
        extension = extension.toLowerCase();
        for (int i=0;i<(aa);i++){
            String data = SpreadsheetOperations.getCellData("report","report", "label", i);
            if (data.contains(extension))
            {
                label.add(data);
                jsRT.add(SpreadsheetOperations.getCellData("report","report", "elapsed", i));
                jsSize.add(SpreadsheetOperations.getCellData("report","report", "bytes", i));

                URL myUrl = new URL(SpreadsheetOperations.getCellData("report","report", "URL", i));
                String domain = myUrl.getProtocol()+"://"+myUrl.getHost();

                jsURL.add(domain);
            }

        }

        for (int j=0;j<label.size();j++){
            setCellData(fileName, sheetName,"Request", (j+2), label.get(j) );
            setCellData(fileName, sheetName,"ResponseTime", (j+2), Double.valueOf(jsRT.get(j)) );
            setCellData(fileName, sheetName,"PageSize", (j+2), Double.valueOf(jsSize.get(j)) );
            setCellData(fileName, sheetName,"URL", (j+2),  jsURL.get(j));
        }
        Double totalTime=0.0;
        Double totalSize=0.0;
        for (int j=0;j<jsRT.size();j++){
            totalTime = totalTime +Double.valueOf(jsRT.get(j));
            totalSize = totalSize +Double.valueOf(jsSize.get(j));
        }
        setCellData(fileName, sheetName,"Request", label.size()+2, "Total "+extension+" request= "+label.size());
        setCellData(fileName, sheetName,"ResponseTime", jsRT.size()+2, "Total Time = "+(totalTime/1000)+" seconds");
        setCellData(fileName, sheetName,"PageSize", jsSize.size()+2, "Total Size = "+(totalSize)/1024+" KB");

        setCellDataWithColor(fileName, sheetName,"PageSize",jsSize.size()+2,  (totalSize)/1024, expectedPageSize);
        setCellDataWithColor(fileName, sheetName,"ResponseTime",jsRT.size()+2, (totalTime/1000), expectedResponseTime);
        setCellDataWithColor(fileName, sheetName,"Request",label.size()+2,  label.size(), expectedNoRequest);


        Set<String> uniqueValues = new HashSet<String>(jsURL);
        ArrayList<String> list = new ArrayList<String>(uniqueValues);

        for (int k=0;k<list.size();k++){
            setCellData(fileName, sheetName,"Request", label.size()+4, "Domain");
            setCellData(fileName, sheetName,"ResponseTime", label.size()+4, "Number of request");

            setCellData(fileName, sheetName,"Request", label.size()+5+k, list.get(k));
            setCellData(fileName, sheetName,"ResponseTime", label.size()+5+k, Collections.frequency(jsURL, list.get(k)));
        }
    }


    public void getPerformanceDataOfMainRequest(String fileName, String sheetName) throws Exception {

        int aa = SpreadsheetOperations.getRowCount("report","report");
        ArrayList<String> label = new ArrayList<String>();
        ArrayList<String> jsRT= new ArrayList<String>();
        ArrayList<String> jsSize= new ArrayList<String>();
        ArrayList<String> jsURL= new ArrayList<String>();
        String pattern= "^[a-zA-Z]*$";
        for (int i=0;i<(aa);i++){
            String data = SpreadsheetOperations.getCellData("report","report", "label", i);
            if((!(data.endsWith(".png"))&&!(data.endsWith(".gif"))&&!(data.endsWith(".js"))&&!(data.endsWith(".css"))&&!(data.endsWith(".ico"))&&!(data.endsWith(".jpg"))&&!(data.endsWith(".mp4")))&&(!data.matches(pattern))&&!(data.endsWith(".eot")))
            {
                label.add(data);	
                jsRT.add(SpreadsheetOperations.getCellData("report","report", "elapsed", i));
                jsSize.add(SpreadsheetOperations.getCellData("report","report", "bytes", i));
                URL myUrl = new URL(SpreadsheetOperations.getCellData("report","report", "URL", i));
                String domain = myUrl.getProtocol()+"://"+myUrl.getHost();
                jsURL.add(domain);
            }
        }

        for (int j=0;j<label.size();j++){
            setCellData(fileName, sheetName,"Request", (j+2), label.get(j) );
            setCellData(fileName, sheetName,"ResponseTime", (j+2), Double.valueOf(jsRT.get(j)) );
            setCellData(fileName, sheetName,"PageSize", (j+2), Double.valueOf(jsSize.get(j)) );
            URL myUrl = null;
            try {
                myUrl = new URL(jsURL.get(j));
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            setCellData(fileName, sheetName,"URL", (j+2), myUrl.getProtocol()+"://"+myUrl.getHost() );

        }

       Set<String> uniqueGas = new HashSet<String>(jsURL);
       System.out.println(uniqueGas);

        Double totalTime=0.0;
        Double totalSize=0.0;
        for (int j=0;j<jsRT.size();j++){

            totalTime = totalTime +Double.valueOf(jsRT.get(j));
            totalSize = totalSize +Double.valueOf(jsSize.get(j));


        }
        setCellData(fileName, sheetName,"Request", label.size()+2, "Total  request= "+label.size());
        setCellData(fileName, sheetName,"ResponseTime", jsRT.size()+2, "Total Time = "+(totalTime/1000)+" seconds");
        setCellData(fileName, sheetName,"PageSize", jsSize.size()+2, "Total Size = "+(totalSize)/1024+" KB");

        double expectedPageSize = propObj.readPerformanceData(fileName+"HTTP-HTTPS"+"PageSize");
        double expectedResponseTime = propObj.readPerformanceData(fileName+"HTTP-HTTPS"+"ResponseTime");
        double expectedNoRequest = propObj.readPerformanceData(fileName+"HTTP-HTTPS"+"NoOfRequest");

        setCellDataWithColor(fileName, sheetName,"PageSize",jsSize.size()+2,  (totalSize)/1024, expectedPageSize);
        setCellDataWithColor(fileName, sheetName,"ResponseTime",jsRT.size()+2, (totalTime/1000), expectedResponseTime);
        setCellDataWithColor(fileName, sheetName,"Request",label.size()+2,  label.size(), expectedNoRequest );

        Set<String> uniqueValues = new HashSet<String>(jsURL);
        ArrayList<String> list = new ArrayList<String>(uniqueValues);

        for (int k=0;k<list.size();k++){
            setCellData(fileName, sheetName,"Request", label.size()+4, "Domain");
            setCellData(fileName, sheetName,"ResponseTime", label.size()+4, "Number of request");

            setCellData(fileName, sheetName,"Request", label.size()+5+k, list.get(k));
            setCellData(fileName, sheetName,"ResponseTime", label.size()+5+k, Collections.frequency(jsURL, list.get(k)));
        }
    }

    /******************************************************************/
    // ************To get the row count in a sheet*******************//
    /**
     * @throws Exception ****************************************************************/
    public static int getRowCount(String fileName, String sheetName) throws Exception {
        PropertyReader propObj = new PropertyReader();
        fileName = propObj.getFilePath()+"//JMX//"+fileName+".xlsx";
        FileInputStream fis = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheetAt(index);
            int number = sheet.getLastRowNum() + 1;
            return number;
        }

    }

    /******************************************************************/
    // *************** To read the data from a cell ********************//
    /******************************************************************/
    public static String getCellData(String fileName, String sheetName, String colName, int rowNum) {
        try {
            fileName = propObj.getFilePath()+"//JMX//"+fileName+".xlsx";
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num = -1;
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                // System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim()
                        .equals(colName.trim()))
                    col_Num = i;
            }
            if (col_Num == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(col_Num);

            if (cell == null)
                return "";
            // System.out.println(cell.getCellType());
            if (cell.getCellType() == Cell.CELL_TYPE_STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
                    || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (DateUtil.isCellDateFormatted(cell)) {
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(DateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR)))
                            .substring(2);
                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/"
                            + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
                }
                return cellText;
            } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colName
                    + " does not exist in xls";
        }
    }

    public void csvToExcel(String fileName) throws IOException{
        try {
            String path = propObj.getFilePath();
            String csvFileAddress =path + "//JMX//"+fileName+".csv"; //csv file address

            String xlsxFileAddress = path + "//JMX//"+fileName+".xlsx"; //xlsx file address
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("report");
            String currentLine=null;
            int RowNum=-1;
            BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
            while ((currentLine = br.readLine()) != null) {
                String str[] = currentLine.split(",");
                RowNum++;
                XSSFRow currentRow=sheet.createRow(RowNum);
                for(int i=0;i<str.length;i++){
                    currentRow.createCell(i).setCellValue(str[i]);
                }
            }

            FileOutputStream fileOutputStream =  new FileOutputStream(xlsxFileAddress);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
            br.close();
            File file = new File(csvFileAddress);
            file.delete();
            if(file.delete()){
                System.out.println(file.getName() + " is deleted!");
            }else{
                System.out.println("Delete operation is failed.");
            }
        } 
        catch (Exception ex) {
            System.out.println(ex.getMessage()+"Exception in try");
        }
    }

    public void addLog(String message) {
        Reporter.log(message + "<br>");
    }


    /******************************************************************/
    // ***************** To write data to a cell **************/
    /******************************************************************/

    public boolean setCellData(String fileName, String sheetName, String colName, int rowNum,
            String data) {
        try {
            fileName = propObj.getFilePath()+"//JMX//"+fileName+".xlsx";
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;
            XSSFSheet sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName))
                    colNum = i;
            }
            if (colNum == -1)
                return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);
            cell.setCellValue(data);

            fileOut = new FileOutputStream(fileName);

            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean setCellData(String fileName, String sheetName, String colName, int rowNum,
            double data) {
        try {
            fileName = propObj.getFilePath()+"//JMX//"+fileName+".xlsx";
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;
            XSSFSheet sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName))
                    colNum = i;
            }
            if (colNum == -1)
                return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);
            cell.setCellValue(data);
            fileOut = new FileOutputStream(fileName);

            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /******************************************************************/
    // ***************** To write data to a cell **************/
    /******************************************************************/

    public boolean setCellData(String sheetName, String colName, int rowNum,
            String data, String url) {
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim()
                        .equalsIgnoreCase(colName))
                    colNum = i;
            }

            if (colNum == -1)
                return false;
            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            cell.setCellValue(data);
            XSSFCreationHelper createHelper = workbook.getCreationHelper();

            // cell style for hyperlinks
            // by default hypelrinks are blue and underlined
            CellStyle hlink_style = workbook.createCellStyle();
            XSSFFont hlink_font = workbook.createFont();
            hlink_font.setUnderline(Font.U_SINGLE);
            hlink_font.setColor(IndexedColors.BLUE.getIndex());
            hlink_style.setFont(hlink_font);



            XSSFHyperlink link = createHelper
                    .createHyperlink(Hyperlink.LINK_FILE);
            link.setAddress(url);
            cell.setHyperlink(link);
            cell.setCellStyle(hlink_style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /******************************************************************/
    // ***************** To create a new excel file **************/
    /******************************************************************/
    public void createExcel(String fileName, String sheetName) throws IOException {
        Workbook wb = null;
        Sheet sheet =null;
        wb = new XSSFWorkbook();
        sheet = wb.createSheet(sheetName);
        System.out.println(sheet);
        String excelFileName = propObj.getFilePath()+"//JMX//"+fileName+".xls";
        if (wb instanceof XSSFWorkbook)
            excelFileName += "x";

        FileOutputStream fos = new FileOutputStream(excelFileName);
        wb.write(fos);
        fos.flush();
        fos.close();
    }

    /******************************************************************/
    // ***************** To creat a new sheet in excel file **************/
    /******************************************************************/
    public boolean setCellDataWithColor(String fileName, String sheetName, String colName, int rowNum,
            double data, double expectedData) {
        try {
            fileName = propObj.getFilePath()+"//JMX//"+fileName+".xlsx";
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;
            XSSFSheet sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName))
                    colNum = i;
            }
            if (colNum == -1)
                return false;
            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);
            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);
            XSSFCellStyle style = workbook.createCellStyle();
            if(data>expectedData){
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
                style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                cell.setCellStyle(style);
            }
            else if (data==expectedData||data<expectedData){
                style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
                style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                cell.setCellStyle(style);
            }
            fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /******************************************************************/
    // ******************To add a new column to the sheet*************//
    /******************************************************************/

    public boolean addColumn(String fileName, String sheetName, String colName) {
        try {
            fileName = propObj.getFilePath()+"//JMX//"+fileName+".xlsx";
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            Sheet sheet = workbook.getSheet(sheetName);

            row = (XSSFRow) sheet.getRow(0);
            if (row == null)
                row = (XSSFRow) sheet.createRow(0);

            if (row.getLastCellNum() == -1)
                cell = row.createCell(0);
            else
                cell = row.createCell(row.getLastCellNum());

            cell.setCellValue(colName);
            cell.setCellStyle(style);

            fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    /******************************************************************/
    // ***************** To creat a new sheet in excel file ***********/
    /*************************************************/
    public boolean addSheet(String fileName,String sheetname) throws Exception {

        FileOutputStream fileOut;
        fileName = propObj.getFilePath()+"//JMX//"+fileName+".xlsx";
        FileInputStream fis = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        try {
            workbook.createSheet(sheetname);
            fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public void addData(String fileName, String sheetName, ArrayList<String> list1,ArrayList<String> list2, ArrayList<String> list3, ArrayList<String> list4) throws IOException{
        fileName = propObj.getFilePath()+"\\VerifySignUpFromNavigationHeader.xlsx";
        FileInputStream fis = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        // writing data into XLSX file

        Map<String, Object[]> newData = new HashMap<String, Object[]>();
        newData.put("1", new Object[] { list1.get(0), list2.get(0), list3.get(0),
                list4.get(0) });
        Set<String> newRows = newData.keySet();
        int rownum = sheet.getLastRowNum();

        for (String key : newRows) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = newData.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof Date) {
                    cell.setCellValue((Date) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                }
            }
        }

        // open an OutputStream to save written data into Excel file
        FileOutputStream os = new FileOutputStream(fileName);
        System.out.println("Writing on Excel file Finished ...");

        // Close workbook, OutputStream and Excel file to prevent leak

        os.close();
        fis.close();
    }

    /******************************************************************/
    // ***************find whether sheet exists*************//
    /******************************************************************/

    public boolean doesSheetExist(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            index = workbook.getSheetIndex(sheetName.toUpperCase());
            if (index == -1)
                return false;
            else
                return true;
        } else
            return true;
    }

    /******************************************************************/
    // ****************get column count in a sheet*****************//
    /******************************************************************/

    public int getColumnCount(String sheetName) {
        if (!doesSheetExist(sheetName))
            return -1;

        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);

        if (row == null)
            return -1;

        return row.getLastCellNum();

    }



    public void writeData(String sheetname, int rowNumber, String status)
            throws Exception {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        // Get the first sheet.
        sheet = workbook.getSheet(sheetname);

        // Set value of the first cell.
        row = sheet.getRow(rowNumber);
        Cell cell = row.getCell(7);
        cell.setCellValue(status);

        // Write newly modified workbook to a file.
        fileOut = new FileOutputStream(path);
        workbook.write(fileOut);
        fileOut.close();
    }

    public void writeCellData(String sheetname, int rowNumber, String status)
            throws Exception {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        // Get the first sheet.
        sheet = workbook.getSheet(sheetname);

        // Set value of the first cell.
        row = sheet.getRow(rowNumber);
        Cell cell = row.getCell(0);
        cell.setCellValue(status);

        // Write newly modified workbook to a file.
        fileOut = new FileOutputStream(path);
        workbook.write(fileOut);
        fileOut.close();
    }

    public void createStructure(String fileName) throws Exception{
        csvToExcel("report");
        createExcel(fileName,"JS");
        addSheet(fileName,"css");
        addSheet(fileName,"png");
        addSheet(fileName,"gif");
        addSheet(fileName,"mp4");
        addSheet(fileName,"ico");
        addSheet(fileName,"eot");
        addSheet(fileName,"HTTP-HTTPS");
        addColumn(fileName, "JS", "Request");
        addColumn(fileName, "JS", "ResponseTime");
        addColumn(fileName, "JS", "PageSize");
        addColumn(fileName, "JS", "URL");

        addColumn(fileName, "css", "Request");
        addColumn(fileName, "css", "ResponseTime");
        addColumn(fileName, "css", "PageSize");
        addColumn(fileName, "css", "URL");

        addColumn(fileName, "png", "Request");
        addColumn(fileName, "png", "ResponseTime");
        addColumn(fileName, "png", "PageSize");
        addColumn(fileName, "png", "URL");

        addColumn(fileName, "gif", "Request");
        addColumn(fileName, "gif", "ResponseTime");
        addColumn(fileName, "gif", "PageSize");
        addColumn(fileName, "gif", "URL");

        addColumn(fileName, "mp4", "Request");
        addColumn(fileName, "mp4", "ResponseTime");
        addColumn(fileName, "mp4", "PageSize");
        addColumn(fileName, "mp4", "URL");

        addColumn(fileName, "eot", "Request");
        addColumn(fileName, "eot", "ResponseTime");
        addColumn(fileName, "eot", "PageSize");
        addColumn(fileName, "eot", "URL");

        addColumn(fileName, "HTTP-HTTPS", "Request");
        addColumn(fileName, "HTTP-HTTPS", "ResponseTime");
        addColumn(fileName, "HTTP-HTTPS", "PageSize");
        addColumn(fileName, "HTTP-HTTPS", "URL");


        addColumn(fileName, "ico", "Request");
        addColumn(fileName, "ico", "ResponseTime");
        addColumn(fileName, "ico", "PageSize");
        addColumn(fileName, "ico", "URL");
    }

    public void getPagePerformance(String fileName) throws Exception{
        createStructure(fileName);

        getPerformanceData("JS",fileName, "JS");
        getPerformanceData("CSS",fileName, "CSS");
        getPerformanceData("PNG",fileName, "PNG");
        getPerformanceData("GIF",fileName, "GIF");
        getPerformanceData("MP4",fileName, "MP4");
        getPerformanceData("ICO",fileName, "ICO");
        getPerformanceData("EOT",fileName, "EOT");
        getPerformanceDataOfMainRequest(fileName, "HTTP-HTTPS");
    }
	public String getExcelData(String testCaseName,String sheetName,String colVal){
		try{
		String value="";
		Workbook workbook;
		Row row;
		Row row1;
		String currentDir = System.getProperty("user.dir");
		String excelFilePath=currentDir+"\\TestData\\DataBaseVerification.xlsx";
		System.out.println("Excel File Path :"+excelFilePath);
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		workbook=new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		int RowNo=getRowNo(sheet, testCaseName);
		System.out.println("Row Number"+RowNo);
		row = sheet.getRow(RowNo);
		System.out.println("Last Cell Number"+row.getLastCellNum());
		row1 = sheet.getRow(0);
		for (int colIndex = 0; colIndex <= row.getLastCellNum(); colIndex++) {
			Cell cell = row1.getCell(colIndex);
			String cellValueMaybeNull = cell.getStringCellValue();
			if (cellValueMaybeNull.trim().equals(colVal.trim())) {
				System.out.println("I am in If");
				cell = row.getCell(colIndex);
				System.out.println(cell.getCellType());
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value=cell.getStringCellValue();
					break;

				case Cell.CELL_TYPE_NUMERIC:
					value=String.format("%.0f", cell.getNumericCellValue());
					break;
				}
				break;
			}
		}
		inputStream.close();
		System.out.println("Value is :"+value);
		return value;
		}
		catch(Exception ex){
			//ex.printStackTrace();
			return "";
	}

	}
	public int getRowNo(Sheet sheetName,String testCaseName) throws IOException{
		int rowIndex;
		for (rowIndex = 0; rowIndex <= sheetName.getLastRowNum(); rowIndex++) {
			Row row = sheetName.getRow(rowIndex);	    
			Cell cell = row.getCell(0);
			String value = cell.getStringCellValue();
			if (value.contains(testCaseName)) {
				break;

			}
		}
		return rowIndex;
	}
}
