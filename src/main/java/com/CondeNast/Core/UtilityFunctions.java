package com.CondeNast.Core;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.github.javafaker.Faker;
import com.lindar.postcodes.io.client.PostcodesClient;
import com.lindar.postcodes.io.client.vo.PostcodeVO;
import com.lindar.postcodes.io.client.vo.Response;

public class UtilityFunctions 
{
	public  enum Mode {ALPHA, ALPHANUMERIC, NUMERIC}
	
	
	public String GenerateRandomNumber()
	{
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss") ;
		String ramnumber = dateFormat.format(date);
		System.out.println("GenerateRandomNumber "+ramnumber);
		return ramnumber;

	}
	
	public String GenerateRandom(int length,Mode mode)
    {
          StringBuffer buffer = new StringBuffer();
          String characters = "";

          switch(mode)
          {
          
          case ALPHA:
                 characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                 break;
          
          case ALPHANUMERIC:
                 characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                 break;
    
          case NUMERIC:
                 characters = "1234567890";
              break;
          }
          
          int charactersLength = characters.length();

          for (int i = 0; i < length; i++) 
          {
                 double index = Math.random() * charactersLength;
                 buffer.append(characters.charAt((int) index));
          }
          return buffer.toString();
          
    }
	
	
	public String getValuesFromPropertiesFile(String propertiesFileName,String key)
	{

		try
		{
			File file = new File(this.getClass().getResource("/"+propertiesFileName+".properties").toURI());
			FileInputStream fis = new FileInputStream(new File(file.toString()));
			Properties prop=new Properties();
			prop.load(fis);
			return prop.get(key).toString();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Properties loadProps() throws IOException, URISyntaxException
	{
		
		FileInputStream fis = new FileInputStream(new File(this.getClass().getResource("/config.properties").toURI()));
		Properties config = new Properties();
		config.load(fis);
		return config;
	}
	
	
	public HashMap<String, String> generateCustomerDetails()
	{
		String DOB = randomDOB();
        HashMap<String,String> cusDetails = new HashMap<String,String>();
		Faker faker = new Faker();
		String title = faker.name().prefix();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String AccountName = firstName+" "+lastName;
		String Company = faker.company().name();
		String email = firstName+"."+lastName+"@xyz.com";
		while(firstName.contains("'") || lastName.contains("'"))
		{
			faker = new Faker();
			title = faker.name().prefix();
			firstName = faker.name().firstName();
			lastName = faker.name().lastName();
		    AccountName = firstName+" "+lastName;
			Company = faker.company().name();
			email = firstName+"."+lastName+"@xyz.com";
		}
		String regNumber = "6"+String.valueOf(faker.number().randomNumber(5, true));
		String phoneNumber = faker.phoneNumber().cellPhone();
		
		System.out.println("title " +title);
		 if(title.equalsIgnoreCase("Dr."))
		 {
			 cusDetails.put("Title", "Dr.");
			 cusDetails.put("Gender", "Male");
			 cusDetails.put("MaritalStatus", "Married");
		 }
		 if(title.equalsIgnoreCase("Mr."))
		 {
			 cusDetails.put("Title", "Mr.");
			 cusDetails.put("Gender", "Male");
			 cusDetails.put("MaritalStatus", "Single");
		 }
		 if(title.equalsIgnoreCase("Miss."))
		 {
			 cusDetails.put("Title", "Ms.");
			 cusDetails.put("Gender", "Female");
			 cusDetails.put("MaritalStatus", "Single");
		 }
		 if(title.equalsIgnoreCase("Miss"))
		 {
			 cusDetails.put("Title", "Ms.");
			 cusDetails.put("Gender", "Female");
			 cusDetails.put("MaritalStatus", "Single");
		 }
		 if(title.equalsIgnoreCase("Ms."))
		 {
			 cusDetails.put("Title", "Ms.");
			 cusDetails.put("Gender", "Female");
			 cusDetails.put("MaritalStatus", "Single");
		 }
		 if(title.equalsIgnoreCase("Mrs."))
		 {
			 cusDetails.put("Title", "Mrs.");
			 cusDetails.put("Gender", "Female");
			 cusDetails.put("MaritalStatus", "Married");
		 }
		cusDetails.put("ForeName", firstName);
		cusDetails.put("SurName", lastName);
		cusDetails.put("AccountName", AccountName);
		cusDetails.put("DOB", DOB);
		cusDetails.put("MobileNumber", phoneNumber);
		cusDetails.put("CompanyName", Company);
		cusDetails.put("RegistrationNumber", regNumber);
		cusDetails.put("Email", email);
		return cusDetails;
	}
	
	public String randomDOB() 
	{

	    int yyyy = random(1975, 2013);
	    int mm = random(1, 12);
	    int dd = 0; // will set it later depending on year and month

	    switch(mm) {
	      case 2:
	        if (isLeapYear(yyyy)) {
	          dd = random(1, 29);
	        } else {
	          dd = random(1, 28);
	        }
	        break;

	      case 1:
	      case 3:
	      case 5:
	      case 7:
	      case 8:
	      case 10:
	      case 12:
	        dd = random(1, 31);
	        break;

	      default:
	        dd = random(1, 30);
	      break;
	    }

	    String year = Integer.toString(yyyy);
	    String month = Integer.toString(mm);
	    String day = Integer.toString(dd);

	    if (mm < 10) {
	        month = "0" + mm;
	    }

	    if (dd < 10) {
	        day = "0" + dd;
	    }

	    return day + '/' + month + '/' + year;
	  }

	  private int random(int lowerBound, int upperBound) {
	    return (lowerBound + (int) Math.round(Math.random()
	            * (upperBound - lowerBound)));
	  }

	  private boolean isLeapYear(int year) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.YEAR, year);
	    int noOfDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);

	    if (noOfDays > 365) {
	        return true;
	    }

	    return false;
	  }
	  
	  public String generateZipCode()
	  {
	     PostcodesClient pc = new PostcodesClient();
  	     Response<PostcodeVO> sval = pc.randomPostcode();
  	     System.out.println(sval);
  	     String[] t1 = sval.toString().split("postcode\\=");
  	     String[] t2 = t1[1].split(",");
  	     String pincode = t2[0];
  	     System.out.println(pincode); 
		return pincode;
	  }
	  
	  
	public void CopytoClipBoard(String theString)
	{
		StringSelection selection = new StringSelection(theString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}
		
	public String GetDatafromClipBoard() throws UnsupportedFlavorException, IOException
	{
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String svalue = (String) clipboard.getData(DataFlavor.stringFlavor);
		return svalue;
	}
	

	public Map<String,Map<String, String>> setMapData() throws URISyntaxException, IOException 
	{
		 Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String,String>>();
		 Map<String, String> dataMap = new HashMap<String, String>();
		 
		 FileInputStream file = new FileInputStream(new File(this.getClass().getResource("/objectrepository.xlsx").toURI()));
		 Workbook workbook = new XSSFWorkbook(file);
		 Sheet sheet = workbook.getSheetAt(0);
		  
		  int lastRow = sheet.getLastRowNum();
		 		  
		  //Looping over entire row
		  for(int i=1; i<=lastRow; i++)
		  {
		  
			  Row row = sheet.getRow(i);
			  
			  //1st Cell as Value
			  Cell valueCell = row.getCell(1);
			  
			  //0th Cell as Key
			  Cell keyCell = row.getCell(0);
			  
			  String value = valueCell.getStringCellValue().trim();
			  String key = keyCell.getStringCellValue().trim();
			  
			  //Putting key & value in dataMap
			  dataMap.put(key, value);
			  
			  //Putting dataMap to excelFileMap
			  excelFileMap.put("DataSheet", dataMap);
	    }
		  
		  workbook.close();
		  
		// TODO Auto-generated method stub
			return excelFileMap;
   }
	
	
	public String calculatePercentage(String amount1 , String percentage1)
	{
		Integer amount = Integer.valueOf(amount1);
		Integer percentage = Integer.valueOf(percentage1);
		int sval = amount * percentage / 100;
		String samount = String.valueOf(sval);
		System.out.println("amount "+samount);
		return samount;
	}
	
	
	
	public String splitAmountBasedonQuater(String amount1, int tolquater)
	{
		Integer amount = Integer.valueOf(amount1);
		int sval = amount / tolquater;
		String samount = String.valueOf(sval);
		System.out.println("amount "+samount);
		return samount;
		
	}
	
	
	

}
