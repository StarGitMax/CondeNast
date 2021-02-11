package com.CondeNast.Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataDriver
{
	String sPath = System.getProperty("user.dir");
	String instance = System.getProperty("instance");
	
	public String getCellData(String sheetName, String colName, int rowNum) throws IOException, URISyntaxException
    {
           FileInputStream fis = new FileInputStream(new File(this.getClass().getResource("/TestData.xlsx").toURI())); 
           XSSFWorkbook workbook = new XSSFWorkbook(fis);

           try
           {
                  int col_Num = -1;
                  Sheet   sheet = workbook.getSheet(sheetName);
                  Row  row = sheet.getRow(0);
                  for(int i = 0; i < row.getLastCellNum(); i++)
                  {
                        if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                               col_Num = i;
                  }

                  row = sheet.getRow(rowNum - 1);
                  Cell  cell = row.getCell(col_Num);

                  if(cell.getCellTypeEnum() == CellType.STRING)
                        return cell.getStringCellValue();
                  
                  else if(cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA)
                  {
                        String cellValue = String.valueOf(cell.getNumericCellValue());
                        
                        if(HSSFDateUtil.isCellDateFormatted(cell))
                        {
                               DateFormat df = new SimpleDateFormat("dd/MM/yy");
                               Date date = cell.getDateCellValue();
                               cellValue = df.format(date);
                        }
                        return cellValue;
                  }
                  
                  
                  else if(cell.getCellTypeEnum() == CellType.BLANK)
                        return "";
                  
                  else
                        return String.valueOf(cell.getBooleanCellValue());
           }
           catch(Exception e)
           {
                  e.printStackTrace();
                  return "row "+rowNum+" or column "+colName +" does not exist  in Excel";
           }
           
    }
	
	private List<String> readFirstRow(String sheetName)
	{
	    List<String> fieldsArrayList = new ArrayList<String>();

	     try
	     {
	    	    FileInputStream fis = new FileInputStream(new File(this.getClass().getResource("/TestData.xlsx").toURI()));

	            Workbook workBook  = new XSSFWorkbook(fis);

	            Sheet sheet = workBook.getSheet(sheetName);

	            Row firstRow = sheet.getRow(0);

	            int length  = firstRow.getLastCellNum();

	            Cell cell = null;

	            for( int i = 0 ; i < length ; i++ )
                {
                     cell = firstRow.getCell(i);
                     fieldsArrayList.add(cell.toString());
                }
	            
	            workBook.close();

	     }
	     catch(Exception e)
	     {
	         e.printStackTrace();
	     }
	     
	     return fieldsArrayList;
	}
	
	
	public LinkedHashMap<Integer, String> readColumnValues(String sheetName,String columnName)
	{
		LinkedHashMap<Integer,String> colValues = new  LinkedHashMap<Integer,String>();
	    try
	    {
	    	
	    	List<String> fieldsArrayList = readFirstRow(sheetName);
	    	int columnPosition = fieldsArrayList.indexOf(columnName);
	    	FileInputStream fis = new FileInputStream(new File(this.getClass().getResource("/TestData.xlsx").toURI()));
	        Workbook workBook = new XSSFWorkbook(fis);
	        Sheet sheet = workBook.getSheet(sheetName);
	        for ( int i = 0 ; i <= sheet.getLastRowNum() ; i++ )
	        {
	            Row row = sheet.getRow(i);
	            if (i > 0) //skip first row
	            {
	                Cell cell = row.getCell(columnPosition);
	                String columnValue =   cell.toString();
	                colValues.put(i, columnValue);
	            }
	        }
	        
	        workBook.close();

	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	    
	    return colValues;
	}
	
	public ArrayList<HashMap<String, String>> getDatafromExcel(int sheetNo) throws Exception
	{
		 ArrayList<HashMap<String, String>> QuoteParams = new ArrayList<>();
	     HashMap<String, String> datamap = null;
	     FileInputStream fis = null;
	     
	     if(instance.equalsIgnoreCase("sit"))
	     {
	    	 fis = new FileInputStream(new File((sPath+"/DataFiles/TestData.xlsx")));
	     }
	     else  if(instance.equalsIgnoreCase("ust"))
	     {
	    	 fis = new FileInputStream(new File((sPath+"/DataFiles/TestData_uat.xlsx")));
	     }
	    
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
		 XSSFSheet sheet = wb.getSheetAt(sheetNo);
		 wb.close();
		 int lastRowNum = sheet.getLastRowNum() ;
		 int lastCellNum = sheet.getRow(0).getLastCellNum();

		 for (int i = 0; i < lastRowNum; i++) {
		       datamap = new HashMap<>();
		       if(sheet.getRow(0).getCell(0).toString().equalsIgnoreCase("Include") && sheet.getRow(i+1).getCell(0).toString().equalsIgnoreCase("N") )
			    {
			    	continue;
			    }
		       else
		       {
		    	   for (int j = 0; j < lastCellNum; j++) {
						  datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
				 }
		       }
		    QuoteParams.add(datamap);
	       }
		 return QuoteParams; 
	}
	
	
}
