package com.CondeNast.TestScenarios;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.github.javafaker.Faker;
import com.lindar.postcodes.io.client.PostcodesClient;
import com.lindar.postcodes.io.client.vo.PostcodeVO;
import com.lindar.postcodes.io.client.vo.Response;

public class test {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		/*
		 * Faker ukFaker = new Faker(new Locale("en-GB"));
		 * 
		 * System.out.println(String.format("British postcode: %s",
		 * ukFaker.address().zipCode()));
		 * 
		 * Pattern ukPattern = Pattern.compile(
		 * "([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|" +
		 * "(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y]"
		 * + "[0-9]?[A-Za-z]))))\\s?[0-9][A-Za-z]{2})"); Matcher ukMatcher =
		 * ukPattern.matcher(ukFaker.address().zipCode());
		 * //System.out.println(ukMatcher.find());
		 * 
		 * PostcodesClient pc = new PostcodesClient(); Response<PostcodeVO> sval =
		 * pc.randomPostcode(); String[] t1 = sval.toString().split("postcode\\=");
		 * String[] t2 = t1[1].split(","); System.out.println(t2[0]);
		 */
		
		
		/*
		 * PostcodesClient pc = new PostcodesClient(); Response<PostcodeVO> sval =
		 * pc.randomPostcode(); String[] t1 = sval.toString().split("postcode\\=");
		 * String[] t2 = t1[1].split(","); System.out.println(t2[0]);
		 * 
		 * test t = new test(); try { String sval1 = t.getCellData("AccountDetails",
		 * "AccountType", 2); System.out.println(sval1); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (URISyntaxException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		
		int i = 5000 * 40 / 100;
		System.out.println("amount "+i);
		
		String contactName  = "Ms. Zana Jacobson";
		String[] cName = contactName.split("\\.");
		System.out.println("amount "+cName[1]);
		
	}
	
	
	
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

}
