package effortLoggerAnalysis;

/*******
Author Manpreet and Garvit
Version 2.0
Date 20/94/2018
*/

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.examples.CreateTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.BorderStyle;

/*

* Here we will learn how to create Excel file and header for the same.

*/

public class ExcelMerging {

              public static final String SAMPLE_XLSX_FILE_PATH[] = 
            	  {""+"Amandeep_SD Faculty Effort Log v2.xlsx", 
            		  "Anil_SD Faculty Effort Log v2.xlsx",
            		  "Anuj_SD Faculty Effort Log v2.xlsx",
            		  "Gunjan_SD Faculty Effort Log v2.xlsx",
            		  "Meghna_SD Faculty Effort Log v2.xlsx",
            		  "Neera_SD Faculty Effort Log v2.xlsx",
            		  "Nikita_SD Faculty Effort Log v2.xlsx",
            		  "Rajeev_SD Faculty Effort Log v2.xlsx",
            		  "Ritu_SD Faculty Effort Log v2.xlsx",
            		  "Rohini_SD Faculty Effort Log v2.xlsx"};

              static XSSFSheet Sheets[] = new XSSFSheet[14] ;
              Collection<File> files;
              static XSSFWorkbook workbook;

              File exactFile;
              {
                  workbook = new XSSFWorkbook();
                  XSSFFont font = workbook.createFont();
                  font.setFontHeightInPoints((short) 50);
                  font.setFontName("Arial");
                  font.setItalic(true);
                  for(int ndx = 0; ndx < SAMPLE_XLSX_FILE_PATH.length ; ndx++ ) {
                           Sheets[ndx] = workbook.createSheet(SAMPLE_XLSX_FILE_PATH[ndx]);
                 }   
              }
              
              public ExcelMerging() {
              }
              
              void createExcelFile(){
                  FileOutputStream fileoutputstream = null;
                  try {                                                            /*change your directory*/
                                fileoutputstream = new FileOutputStream(new File("C:\\Users\\Manpreet Singh Kohli\\Desktop\\Project 3 EffortLogger\\Summery Sheet.xlsx"));
                       
                                
                                for(int ndx= 0; ndx < SAMPLE_XLSX_FILE_PATH.length ; ++ndx)
                                {                                       
                                Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH[ndx]));
                                // Retrieving the number of sheets in the Workbook.//
                                Sheet sheet = workbook.getSheetAt(0);                        
                                DataFormatter dataFormatter = new DataFormatter();
                                int rownum=0;
                                Iterator<Row> rowIterator = sheet.rowIterator();
                               
                                while (rowIterator.hasNext()) {
                                    Row row = rowIterator.next();
                                                                               
                                    Row row1 = Sheets[ndx].createRow(rownum);
                                    // let's iterate over the columns of the current row.   
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    
                                    while (cellIterator.hasNext()) {
                                        Cell cell = cellIterator.next();
                                        
                                        String cellValue = dataFormatter.formatCellValue(cell);
                                        
                                        int mg= 0;
										Cell cell1 = row1.createCell(mg);
                                        cell1.setCellValue(cellValue);
                                        mg++;
                                        
                                    }
                                    
                                   rownum++;
                                }
 
                                }   
                               workbook.write(fileoutputstream);
                                workbook.close();
                                fileoutputstream.flush();
                                fileoutputstream.close();
                  } catch (Exception exception) {
                                exception.printStackTrace();
                  }
     }
             public static void main(String args[])  {
                           ExcelMerging newfile = new ExcelMerging();
                                 newfile.createExcelFile(); 
                                 System.out.println("Directory of the code - C:\\Users\\Manpreet Singh Kohli\\Desktop\\Summery Sheet.xlsx");
              }
              
}

