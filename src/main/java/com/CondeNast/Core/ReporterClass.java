package com.CondeNast.Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReporterClass extends WebDriverClass
{
	 public ExtentHtmlReporter htmlReporter;
	 public static ExtentReports report; 
	 public static ExtentTest logger;
	 public String directory;
	 public static String methodName;
	 UtilityFunctions uFunctions = new UtilityFunctions();
	 static Map<String, String> objectRepositoryData;
     
    public void ReportConfiguration() throws Exception
    {
    	String instance = System.getProperty("instance");
    	String browser = System.getProperty("browser");
    	File currentDirectory = new File(new File(".").getAbsolutePath());
    	String ramdom = uFunctions.GenerateRandomNumber();
    	
    	directory = currentDirectory.getCanonicalPath()+"\\target\\HtmlReports";
		System.out.println("Report File Path "+directory);
		File dir = new File(directory );
	    if (!dir.exists())
	    {
	    	dir.mkdirs();
	    }
	    	
		File f = new File(currentDirectory.getCanonicalPath()+"\\target\\HtmlReports\\AtlasGCP.html");
		File f2 = new File(currentDirectory.getCanonicalPath()+"\\target\\HtmlReports\\AtlasGCP"+ramdom+".html");
		if(f.exists())
		{
			System.out.println("File existed");
			f.renameTo(f2);
		}
		htmlReporter = new ExtentHtmlReporter(currentDirectory.getCanonicalPath()+"\\target\\HtmlReports\\AtlasGCP.html");
		
		report =  new ExtentReports();
        report.attachReporter(htmlReporter);
		report.setSystemInfo("Host Name", "Local Machine");
		report.setSystemInfo("Environment", instance);
		report.setSystemInfo("Instance", browser);
		report.setSystemInfo("User Name", "Kalyan");
        //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
        //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
		 htmlReporter.loadXMLConfig(new File(this.getClass().getResource("/extent-config.xml").toURI()),true);
		 objectRepositoryData = uFunctions.setMapData().get("DataSheet");
    }
    
    

    public void StartReport(Method method)
    {
		try {
			logger = report.createTest((this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName());
			methodName = method.getName();
			System.out.println("Method Name "+ methodName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
    public void getResult(ITestResult result) throws Exception
    {
        if (result.getStatus() == ITestResult.FAILURE) 
        {
        	String scrennShot = getBase64Screenshot();
        	logger.log(Status.FAIL,result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromBase64String(scrennShot).build());
        } 
        else if (result.getStatus() == ITestResult.SKIP)
        {
        	logger.log(Status.SKIP, "Test skipped " + result.getThrowable());
        } 
        else
        {
        	logger.log(Status.PASS, "Test passed");
        }
        report.flush();
    }
   
    
	 public void addScreenshotToReport(String finalDestination, String description) throws IOException
	 {
		 String path = screenshotsPath+finalDestination;
		 System.out.println("scrennShot path: " + path);
		 logger.log(Status.INFO,description,MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	 }
	 

	 public void CaptureandAddScreenshotToReport(String filename, String description) throws IOException
	 {
		 String scrennShot = CaptureScreenShot(filename);
		 logger.log(Status.FAIL,description,MediaEntityBuilder.createScreenCaptureFromBase64String(scrennShot).build());
	 }
	 
	 
	 
	private String CaptureScreenShot(String name) throws IOException
    {
    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyMMMmmss");
        sdf2.setTimeZone(TimeZone.getTimeZone("PST"));
        String datetime = sdf2.format(new Date());
        System.out.println("timeStamp: " + datetime);
        String encodedBase64 = null;
        FileInputStream fileInputStream = null;
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File file = new File(screenshotsPath);
	   	 if (!file.exists())
	   	 {
	   		 file.mkdir();
	   	 }
        String destination =screenshotsPath+name+datetime+".jpg";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);

        try {
            fileInputStream =new FileInputStream(finalDestination);
            byte[] bytes =new byte[(int)finalDestination.length()];
            fileInputStream.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return encodedBase64;
    }
	
	
	
	private String getBase64Screenshot() throws IOException
    {
    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyMMMmmss");
        sdf2.setTimeZone(TimeZone.getTimeZone("GMT"));
        String datetime = sdf2.format(new Date());
        System.out.println("timeStamp: " + datetime);
        String encodedBase64 = null;
        FileInputStream fileInputStream = null;
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File file = new File(screenshotsPath);
	   	 if (!file.exists())
	   	 {
	   		 file.mkdir();
	   	 }
        String destination =screenshotsPath+methodName+datetime+".jpg";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);

        try {
            fileInputStream =new FileInputStream(finalDestination);
            byte[] bytes =new byte[(int)finalDestination.length()];
            fileInputStream.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return encodedBase64;
    }
	    
	
    

}
