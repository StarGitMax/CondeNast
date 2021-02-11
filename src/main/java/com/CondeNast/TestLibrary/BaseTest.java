package com.CondeNast.TestLibrary;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.CondeNast.Core.ImagePatterns;
import com.CondeNast.Core.ReporterClass;
import com.CondeNast.Core.TestDataDriver;
import com.CondeNast.Core.UtilityFunctions;
import com.CondeNast.Core.WebControls;


public class BaseTest extends ReporterClass
{
    String Browser;
    public static String instance;
	public static WebControls uiDriver;
	public static ReporterClass reporter;
	public static UtilityFunctions utilFuns;
	public static TestDataDriver dataDriver;
	public static ImagePatterns Pattern;
	public static String AccountName_TC1752;
	public static String cProductName_TC2562;
	public static String cbrandName_TC2562;
	public static String cbrandName_TC2560;
	public static String cProductName_TC2559_2560;
	public static String cbrandName_TC2559_2560;
	public static String AccountName_TC2434_2435;
	public static String cProductName_TC2567;
	public static String cbrandName_TC2567;
	public static String cbrandName_TC2565;
	public static String AccountName_TC2432_2433;
	public static String ContName_TC2430;
	public static String AccountName_TC2436_2437;
	public static String AccountName_TC1751_1753;
	public static String cbrandName_TC1810;
	public static String Account_TC2558;
	public static String mAccount_TC2439;
	public static String ContName_TC2571;
    static
	 {
		  uiDriver = new WebControls();
		  reporter = new ReporterClass();
		  utilFuns = new UtilityFunctions();
		  dataDriver = new TestDataDriver();
		  Pattern = new ImagePatterns();
	 }	
    
   @BeforeSuite
   public void beforeSuite() throws Exception
    {
	   ReportConfiguration();
    }
   
   
   @BeforeTest
   public void beforeTest() throws MalformedURLException
   {
	  
   }
   
    	
    @BeforeMethod
    public void getTestMethodName(Method method) throws IOException, URISyntaxException
	{
      Browser = System.getProperty("browser");
      instance = System.getProperty("instance");
      if(instance.equalsIgnoreCase("uat"))
	  {
    	  AccountName_TC1752 = utilFuns.loadProps().get("AccountName_UAT_TC1752").toString();
    	  cProductName_TC2562 = utilFuns.loadProps().get("cProductName_UAT_TC2562").toString();
          cbrandName_TC2562 = utilFuns.loadProps().get("cbrandName_UAT_TC2562").toString();
          cbrandName_TC2560  = utilFuns.loadProps().get("cbrandName_UAT_TC2560").toString();
          cProductName_TC2559_2560 = utilFuns.loadProps().get("cProductName_UAT_TC2559_2560").toString();
          cbrandName_TC2559_2560 = utilFuns.loadProps().get("cbrandName_UAT_TC2559_2560").toString();
          AccountName_TC2434_2435 = utilFuns.loadProps().get("AccountName_UAT_TC2434_2435").toString();
          cProductName_TC2567 = utilFuns.loadProps().get("cProductName_UAT_TC2567").toString();
          cbrandName_TC2567 = utilFuns.loadProps().get("cbrandName_UAT_TC2567").toString();
          cbrandName_TC2565 = utilFuns.loadProps().get("cbrandName_UAT_TC2565").toString();
          AccountName_TC2432_2433 = utilFuns.loadProps().get("AccountName_UAT_TC2432_2433").toString();
          ContName_TC2430 = utilFuns.loadProps().get("ContName_UAT_TC2430").toString();
          AccountName_TC2436_2437 = utilFuns.loadProps().get("AccountName_UAT_TC2436_2437").toString();
          AccountName_TC1751_1753 = utilFuns.loadProps().get("AccountName_UAT_TC1751_1753").toString();
          cbrandName_TC1810 = utilFuns.loadProps().get("cbrandName_UAT_TC1810").toString();
          Account_TC2558 = utilFuns.loadProps().get("Account_UAT_TC2558").toString();
          mAccount_TC2439 = utilFuns.loadProps().get("mAccount_UAT_TC2439").toString();
          ContName_TC2571 = utilFuns.loadProps().get("ContName_UAT_TC2571").toString();
	  }
      else if(instance.equalsIgnoreCase("sit"))
      {
    	  AccountName_TC1752 = utilFuns.loadProps().get("AccountName_SIT_TC1752").toString();
    	  cProductName_TC2562 = utilFuns.loadProps().get("cProductName_SIT_TC2562").toString();
    	  cbrandName_TC2562 = utilFuns.loadProps().get("cbrandName_SIT_TC2562").toString();
    	  cbrandName_TC2560  = utilFuns.loadProps().get("cbrandName_SIT_TC2560").toString();
    	  cProductName_TC2559_2560 = utilFuns.loadProps().get("cProductName_SIT_TC2559_2560").toString();
          cbrandName_TC2559_2560 = utilFuns.loadProps().get("cbrandName_SIT_TC2559_2560").toString();
          AccountName_TC2434_2435 = utilFuns.loadProps().get("AccountName_SIT_TC2434_2435").toString();
          cProductName_TC2567 = utilFuns.loadProps().get("cProductName_SIT_TC2567").toString();
          cbrandName_TC2567 = utilFuns.loadProps().get("cbrandName_SIT_TC2567").toString();
          cbrandName_TC2565 = utilFuns.loadProps().get("cbrandName_SIT_TC2565").toString();
          AccountName_TC2432_2433 = utilFuns.loadProps().get("AccountName_SIT_TC2432_2433").toString();
          ContName_TC2430 = utilFuns.loadProps().get("ContName_SIT_TC2430").toString();
          AccountName_TC2436_2437 = utilFuns.loadProps().get("AccountName_SIT_TC2436_2437").toString();
          AccountName_TC1751_1753 = utilFuns.loadProps().get("AccountName_SIT_TC1751_1753").toString();
          cbrandName_TC1810 = utilFuns.loadProps().get("cbrandName_SIT_TC1810").toString();
          Account_TC2558 = utilFuns.loadProps().get("Account_SIT_TC2558").toString();
          mAccount_TC2439 = utilFuns.loadProps().get("mAccount_SIT_TC2439").toString();
          ContName_TC2571 = utilFuns.loadProps().get("ContName_SIT_TC2571").toString();
      }
      
  	  initilize(Browser);
      StartReport(method);
	}
    
    
    @AfterMethod
    public void getStatusofTests(ITestResult result) throws Exception
    {
    	getResult(result);
    	endTest();
    }
   
    
    @AfterTest
    public void afterTest()
    {
    	
    }
    
}
