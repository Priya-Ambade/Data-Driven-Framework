package com.datadrivenframework.excelReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excel_reader {

    public String[][] getExcelData(String excellocation, String sheetName) {
        try {
            String dataSets[][] = null;
            FileInputStream file = new FileInputStream(new File(excellocation));

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(sheetName);
            // count number of active rows
            int totalRow = sheet.getLastRowNum();
            // count number of active columns in row
            int totalColumn = sheet.getRow(0).getLastCellNum();
            // Create array of rows and column
            dataSets = new String[totalRow][totalColumn];
            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int i = 0;
            while (rowIterator.hasNext()) {
                System.out.println(i);

                Row row = rowIterator.next();
                // For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                int j = 0;
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    if (cell.getStringCellValue().contains("User Name")) {
                        break;
                    }

                    // Check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            dataSets[i - 1][j++] = cell.getStringCellValue();
                            System.out.println(cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
                            dataSets[i - 1][j++] = cell.getStringCellValue();
                            System.out.println(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            dataSets[i - 1][j++] = cell.getStringCellValue();
                            System.out.println(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            dataSets[i - 1][j++] = cell.getStringCellValue();
                            System.out.println(cell.getStringCellValue());
                            break;
                    }

                }

                System.out.println("");
                i++;
            }
            file.close();
            return dataSets;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateResult(String excellocation, String sheetName, String testCaseName, String testStatus) throws IOException {

        try {
            FileInputStream file = new FileInputStream(new File(excellocation));

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(sheetName);
            // count number of active tows
            int totalRow = sheet.getLastRowNum() + 1;
            // count number of active columns in row
            for (int i = 1; i < totalRow; i++) {
                XSSFRow r = sheet.getRow(i);
                String ce = r.getCell(1).getStringCellValue();
                if (ce.contains(testCaseName)) {
                    r.createCell(2).setCellValue(testStatus);
                    file.close();
                    System.out.println("resule updated");
                    FileOutputStream outFile = new FileOutputStream(new File(excellocation));
                    workbook.write(outFile);
                    outFile.close();
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<String> getExcelDataBasedOnStartingPoint(String excellocation, String sheetName, String testName) {
        try {
            String dataSets[][] = null;
            FileInputStream file = new FileInputStream(new File(excellocation));

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(sheetName);
            // count number of active rows
            int totalRow = sheet.getLastRowNum();
            int totalColumn = 0;
            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int i = 0;
            int count = 1;
            while (rowIterator.hasNext() && count == 1 || count == 2) {
                // System.out.println(i);

                Row row = rowIterator.next();
                // For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                int j = 0;
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    if (cell.getStringCellValue().contains(testName + "end")) {
                        count = 0;
                        break;
                    }

                    // System.out.println(sheetName+"Start");
                    if (cell.getStringCellValue().contains(testName + "start")) {
                        // count number of active columns in row
                        totalColumn = row.getPhysicalNumberOfCells() - 1;
                        // Create array of rows and column
                        dataSets = new String[totalRow][totalColumn];
                    }
                    // System.out.println(sheetName+"Start");
                    if (cell.getStringCellValue().contains(testName + "start") || count == 2) {
                        System.out.println(sheetName + "start");
                        count = 2;
                        // Check the cell type and format accordingly

                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                dataSets[i - 1][j++] = cell.getStringCellValue();
                                System.out.println(cell.getNumericCellValue());
                                break;
                            case Cell.CELL_TYPE_STRING:
                                if (!cell.getStringCellValue().contains(testName + "start")) {
                                    dataSets[i - 1][j++] = cell.getStringCellValue();
                                    System.out.println(cell.getStringCellValue());
                                }
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                dataSets[i - 1][j++] = cell.getStringCellValue();
                                System.out.println(cell.getStringCellValue());
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                dataSets[i - 1][j++] = cell.getStringCellValue();
                                System.out.println(cell.getStringCellValue());
                                break;
                        }

                    }
                }

                System.out.println("");
                i++;
            }
            file.close();

//            return (List<String>) parseData(dataSets, totalColumn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object[][] parseData(Object[][] data, int colSize) {
        // Creating array list to store data;
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

        // This array list will store one Array index data, every array index
        // has three sets of data
        ArrayList<String> list1;

        System.out.println(data.length);

        // running for loop on array size
        for (int i = 0; i < data.length; i++) {
            // creates a list to store the elements != null

            System.out.println(data[i].length);

            list1 = new ArrayList<String>();
            // this for loop will run on array index, since each array index has
            // three sets of data
            for (int j = 0; j < data[i].length; j++) {
                // this if will check null
                if (data[i][j] != null) {
                    list1.add((String) data[i][j]);
                }
            }
            // once all one array index data is entered in arrayList , then
            // putting this object in parent arrayList
            if (list1.size() > 0) {
                list.add(list1);
            }
        }
        // convert array List Data into 2D Array
        Object[][] arr2d = new Object[list.size()][colSize];
        // run loop on array list data
        for (int i = 0; i < list.size(); i++) {
            // every array list index has arryList inside
            ArrayList<String> t = list.get(i);
            // run loop on inner array List
            for (int j = 0; j < t.size(); j++) {
                arr2d[i][j] = t.get(j);
            }
        }
        System.out.println(list);
        System.out.println(arr2d);
        return arr2d;
    }


    public static List<String> eData;
    public static void getData(String expectedMethodName) {
        eData = new ArrayList<String>();
        File f = new File("D:/Automation Related/Sample Framework/DataDrivenFramework/src/main/java/com/datadrivenframework/data/Testdata.xlsx");
        try {
            FileInputStream fis = new FileInputStream(f);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet ws = wb.getSheet("Data");
            int lastRow = ws.getLastRowNum();
            for(int i=1; i<=lastRow; i++){
                XSSFRow row = ws.getRow(i);
                XSSFCell cell = row.getCell(0);
                String actualMethodName = cell.getStringCellValue();
                if(actualMethodName.equalsIgnoreCase(expectedMethodName)){
                    int colCount = row.getLastCellNum();
                    for(int j=1; j<colCount; j++){
                        cell = row.getCell(j);
                        eData.add(cell.getStringCellValue());
                    }
                    break;
                }

            }
            fis.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




}
