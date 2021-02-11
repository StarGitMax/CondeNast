package com.CondeNast.Core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverClass 
{
    public static WebDriver driver;
    public static WebDriverWait customWait;
    public static String MainWindowHandle;
    public static String ChildWindowHandle;
    public static String sPath = System.getProperty("user.dir");
	public static String username = System.getProperty("user.name");
	public static String screenshotsPath = sPath+"/target/HtmlReports/Screenshots/";
  
	public void initilize(String browserName) throws MalformedURLException
	{
		
		//Initialize webDriver
		if(driver == null)
		{
			switch(browserName) 
			{
			   case "chrome" :
			   case "CHROME" :
			   {
				   WebDriverManager.chromedriver().setup();
				   driver=new ChromeDriver();
				   break;
			   }
			   
			   case "firefox" :
			   case "FIREFOX" :
			   {
				   WebDriverManager.firefoxdriver().setup();
				   driver=new FirefoxDriver();
				   break;
			   }
			   
			   case "edge" :
			   case "EDGE" :
			   {
				   WebDriverManager.edgedriver().setup();
				   driver=new EdgeDriver();
				   break;
			   }
			
			   case "ie" :
			   case "IE" :
			   {
				   WebDriverManager.iedriver().setup();
				   driver=new InternetExplorerDriver();
				   DesiredCapabilities cap = new DesiredCapabilities();
				   cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				   break;
			   }
			   
			   case "safari" :
			   case "SAFARI" :
			   {
				   driver= new SafariDriver();
				   break;
			   }
			   default: 
			   {
					break;
			   }
				   
			}
			
		}
		//Perform Basic Operations
		//driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(150, TimeUnit.SECONDS);
		customWait = new WebDriverWait(driver,90);
		MainWindowHandle = driver.getWindowHandle();
		System.out.println("Main window handle is " + MainWindowHandle);
	}
	
	  public void endTest()
	    {
	    	driver.close();
		    driver.quit();
		    driver = null;
		    try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
	    }
}
