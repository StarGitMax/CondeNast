package com.CondeNast.Core;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.Screen;
import org.testng.Assert;
import com.aventstack.extentreports.Status;

public class WebControls extends WebDriverClass
{
	String[] temp;
	ObjectRepository obj = new ObjectRepository();
	public  enum LIST {ByValue,ByIndex,ByVisibleText};
	String  browser = System.getProperty("browser");
	public static Screen screen;
	public static Robot r;
	
	static
	{
		InitializeScreenInstance scr = InitializeScreenInstance.getInstanceOfScreenClass();
		screen = scr.getScreen();
	}
	
	public void navitagetoURL(String URL)
	{
		driver.get(URL);
		ReporterClass.logger.log(Status.PASS, "Successfully Navigated to  URL " + URL);

	}

	private WebElement FindElement(String locator, String property)
	{
		By byElement = null;
		WebElement webelement = null;
		
		switch (locator)
		{

			case "id":
			{
				byElement = By.id(property);
				break;
			}

			case "name": 
			{
				byElement = By.name(property);
				break;
			}

			case "xpath":
			{
				byElement = By.xpath(property);
				break;
			}
			case "classname": 
			{
				byElement = By.className(property);
				break;
			}
			case "linktext": 
			{
				byElement = By.linkText(property);
				break;
			}
			case "tagname": 
			{
				byElement = By.tagName(property);
				break;
			}
			case "paritallinktext": 
			{
				byElement = By.partialLinkText(property);
				break;
			}
			case "cssSelector": 
			{
				byElement = By.cssSelector(property);
				break;
			}
			default: 
			{
				break;
			}

		}
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		webelement = driver.findElement(byElement);
			
		return webelement;
	}
	
	 private By FindByElement(String locator, String property)
		{
			By byElement = null;
			
			switch (locator)
			{

				case "id":
				{
					byElement = By.id(property);
					break;
				}

				case "name": 
				{
					byElement = By.name(property);
					break;
				}

				case "xpath":
				{
					byElement = By.xpath(property);
					break;
				}
				case "classname": 
				{
					byElement = By.className(property);
					break;
				}
				case "linktext": 
				{
					byElement = By.linkText(property);
					break;
				}
				case "tagname": 
				{
					byElement = By.tagName(property);
					break;
				}
				case "paritallinktext": 
				{
					byElement = By.partialLinkText(property);
					break;
				}
				case "cssSelector": 
				{
					byElement = By.cssSelector(property);
					break;
				}
				default: 
				{
					break;
				}

			}
				
			return byElement;
		}
	
	
	private WebElement getWebElement(String property) throws IOException, URISyntaxException
	{
		 String str = obj.getObjectProperties(property);
		 temp = str.split(";");
		 WebElement webelement = FindElement(temp[0], temp[1]);
		 Assert.assertNotNull(webelement);
		 return webelement;
	}

	
	public void SetValueForTextBox(String sElement,String sData) throws Exception
	{
		 WebElement element = getWebElement(sElement);
		 element.sendKeys(sData);
		 ReporterClass.logger.log(Status.PASS, "Successfully entered value " + sData + " for TextBox "+sElement);
		 Thread.sleep(3000);
	}
	

	public void ClearTextBox(String sElement) throws Exception
	{
		 WebElement element = getWebElement(sElement);
		 element.clear();
		 ReporterClass.logger.log(Status.PASS, "Successfully Cleared value for TextBox "+sElement);
		 Thread.sleep(5000);
	}
	
	public void setFocus(String sElement) throws URISyntaxException, Exception
	{
		WebElement element = getWebElement(sElement);
		element.click();
	}
	
	
	public void setAttributeValue(String sElement, String sAttribute, String sData) throws Exception, URISyntaxException
	{
		WebElement element = getWebElement(sElement);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].removeAttribute('readonly')", element);
	    js.executeScript("arguments[0].setAttribute('"+sAttribute+"','"+sData+"')",element);
	    ReporterClass.logger.log(Status.PASS, "Successfully entered value " + sData + " for TextBox "+sElement);
		Thread.sleep(3000);
	}
	
	public void scrolltoViewElement(WebElement Element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}
	
	public void scrolltoViewInsideElement(WebElement Element) throws Exception
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", Element);
		Thread.sleep(2000);
	}
	
	
	public void scrolltoViewElement(String sElement) throws Exception, URISyntaxException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = getWebElement(sElement);
		js.executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(3000);
	}
	
	
	public String AlertText(String sElement) throws URISyntaxException, Exception
	{ 	 
	    String almessage = null;
	    try
	    {
	    	WebElement element = getWebElement(sElement);
	        almessage = element.getText();
	        ReporterClass.logger.log(Status.PASS,"message is "+almessage);
	    } 
	    catch (NoAlertPresentException n) 
	    {
	       // System.out.println(n);
	    }
		return almessage;
	}
	
	
	
	public void ClickOnButtonorLink(String sElement) throws URISyntaxException, Exception 
	{	
		WebElement element = getWebElement(sElement);
		element.click();
		ReporterClass.logger.log(Status.PASS, "Successfully Clicked on " + sElement + " Button\\Link");
	}
	
	public int Count(String sElement) throws Exception
	{

	// count occurrences of the string
	int count = 0;

	try {
	// search for the String within the text
	String str = obj.getObjectProperties(sElement);
	temp = str.split(";");
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	List<WebElement> body = driver.findElements(By.xpath(temp[1]));
	count = body.size();

	}
	catch (Exception e) {
	// TODO Auto-generated catch block
	}

	return count;
	}
	
	public void SetValueForCheckBox(String sElement) throws URISyntaxException, Exception
	{
		boolean checkstatus;
		WebElement element = getWebElement(sElement);
		checkstatus = element.isSelected();
		if (checkstatus == true)
		{
			ReporterClass.logger.log(Status.PASS, sElement + " CheckBox is already checked");
		}
		else
		{
			element.click();
			ReporterClass.logger.log(Status.PASS, "Successfully Click on " + sElement + " CheckBox");
		}
	}
	
	public void SetValueForRadio(String sElement) throws URISyntaxException, Exception
	{
		boolean checkstatus;
		WebElement element = getWebElement(sElement);
		checkstatus = element.isSelected();
		if (checkstatus == true)
		{
			ReporterClass.logger.log(Status.PASS, sElement + " RadioButton is already checked");
		}
		else
		{
			element.click();
			ReporterClass.logger.log(Status.PASS, "Successfully Click on " + sElement + "  Radio Button");
		}
	}
	
	public void SetValueForList(String sElement,LIST list,String sData) throws Exception
	{
		
		WebElement element = getWebElement(sElement);
		switch(list)
		{
		  
			case ByValue:
			{
				Select list1 = new Select(element);
				list1.selectByValue(sData);
				ReporterClass.logger.log(Status.PASS, "Successfully Selected Value From " + sElement + " List Box "+ "with value "+sData);
				break;
			}
				
			case ByIndex:
			{
				int sdat = Integer.parseInt(sData);
				Select list2 = new Select(element);
				list2.selectByIndex(sdat);
				ReporterClass.logger.log(Status.PASS, "Successfully Selected Value From " + sElement + " List Box "+ "with value "+sData);
				break;
			}
			
			case ByVisibleText:
			{
				Select list3 = new Select(element);
				list3.selectByVisibleText(sData);
				ReporterClass.logger.log(Status.PASS, "Successfully Selected Value From " + sElement + " List Box "+ "with value "+sData);
				break;
			}
			
			default:
			{
				throw new Exception();
			}
		}
	}
	
	
	
	public boolean IsElementDisplayed(String sElement)  throws Exception
	{
		boolean svalue = false;
		
		try 
		{
			String str = obj.getObjectProperties(sElement);
			temp = str.split(";");
			WebElement webelement = FindElement(temp[0], temp[1]);
			svalue = webelement.isDisplayed();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return svalue;
	}
	
	public boolean IsElementEnabled(String sElement)  throws Exception
	{
		boolean svalue = false;
		
		try
		{
			String str = obj.getObjectProperties(sElement);
			temp = str.split(";");
			WebElement webelement = FindElement(temp[0], temp[1]);
			svalue = webelement.isEnabled();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return svalue;
	}
	
	
	public boolean IsElementPresent(String sElement)
	{
		String str = null;
		boolean svalue = false;
		
		try {
			str = obj.getObjectProperties(sElement);
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp = str.split(";");
		WebElement webelement = FindElement(temp[0], temp[1]);
		if(webelement!=null)
		{
			//highLight(webelement,driver);
			svalue = true;
			ReporterClass.logger.log(Status.PASS, sElement + " is Present");
		}
		else
		{
			ReporterClass.logger.log(Status.FAIL, sElement + " is not Present");
		}
		return svalue;
	}
	
	public String GetTextandStoreinVariable(String sElement) throws URISyntaxException, Exception
	{
		String appvalue = null;
		WebElement element = getWebElement(sElement);
		appvalue = element.getText();
		ReporterClass.logger.log(Status.PASS, "Successfully fetched Value From " + sElement + " is "+appvalue);
		return appvalue;
	}
	
	public String ValuebyGetAttributeandStoreinVariable(String sElement) throws URISyntaxException, Exception
	{
		String appvalue = null;
		WebElement element = getWebElement(sElement);
		appvalue = element.getAttribute("value");
		ReporterClass.logger.log(Status.PASS, "Successfully fetched Value From " + sElement + " is "+appvalue);
		return appvalue;
	}
	
	public String GetAttributeValueandStoreinVariable(String sElement,String sAttibute) throws URISyntaxException, Exception
	{
		String appvalue = null;
		WebElement element = getWebElement(sElement);
		appvalue = element.getAttribute(sAttibute);
		ReporterClass.logger.log(Status.PASS, "Successfully fetched Value From " + sElement + " is "+appvalue);
		return appvalue;
	}
	
	public String checkAlert_Accept() 
	{
		 String almessage = null;
	    try
	    {
	        Alert alert = driver.switchTo().alert();
	        almessage = alert.getText();
	        alert.accept();
	        ReporterClass.logger.log(Status.PASS, " Alert is Present and Text is " + almessage);
	    } 
	    catch (NoAlertPresentException n) 
	    {
	       // System.out.println(n);
	    }
		return almessage;
	}
	
	public String checkAlert_Dismiss() 
	{
		 String almessage = null;
	    try
	    {
	        Alert alert = driver.switchTo().alert();
	        almessage = alert.getText();
	        alert.dismiss();
	        ReporterClass.logger.log(Status.PASS, " Alert is Present and Text is " + almessage);
	    } 
	    catch (NoAlertPresentException n) 
	    {
	       // System.out.println(n);
	    }
		return almessage;
	}
	
	
	
	public  void switchToParentWindow()
	{
		driver.switchTo().defaultContent();
	}
	
	
	public void pressKeyEnter(String sElement) throws IOException, URISyntaxException, InterruptedException 
	{
		WebElement element = getWebElement(sElement);
		element.sendKeys(Keys.ENTER);
	}
	
	public void pressKeyDelete(String sElement) throws IOException, URISyntaxException, InterruptedException 
	{
		WebElement element = getWebElement(sElement);
		element.sendKeys(Keys.DELETE);
	}
	
	
	public void hoverWebelementandClick(String sElement)throws InterruptedException, Exception, URISyntaxException 
	{
		WebElement element = getWebElement(sElement);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().build().perform();
		Thread.sleep(5000);
	}
    
	public void hoverWebelementandDoubleClick(String sElement)throws InterruptedException, Exception, URISyntaxException 
	{
		WebElement element = getWebElement(sElement);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).doubleClick().build().perform();
		Thread.sleep(5000);
	}
    
	public String getToolTip(String sElement) throws Exception, URISyntaxException
	{
		WebElement element = getWebElement(sElement);
		String tooltip = element.getAttribute("title");
		System.out.println("Tool text : " + tooltip);
		return tooltip;
	}
	
	public void openNewTab()
	{
		((JavascriptExecutor)driver).executeScript("window.open()");
		 ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		 driver.switchTo().window(tabs.get(1));
	}
	
	
	  public String checkAlert(String sElement) throws URISyntaxException, Exception
			{
				 String almessage = null;
			    try
			    {
			    	WebElement element = getWebElement(sElement);
			       // Alert alert = driver.switchTo().alert();
			        almessage = element.getText();	
			        boolean error = almessage.contains("Error");
			        boolean success = almessage.contains("Success");
			        if(error==true)
			        {
			        	temp=almessage.split("Error");
			        	ReporterClass.logger.log(Status.PASS, " Error occured, message is " + temp[1]);
			        }
			        else
			        {
			        	System.out.println("no error");
			            if(success==true)
			            	{
			                	temp=almessage.split("Success");
			                	ReporterClass.logger.log(Status.PASS, " Success, message is " + temp[1]);
			                }
			            else
			            	System.out.println("no success");
			        }
			    } 
			    catch (NoAlertPresentException n) 
			    {
			       // System.out.println(n);
			    }
				return almessage;
			}
	  
	  public String GetOption(String sElement) throws Exception
		{
			//Assume driver initialized properly some where else
			  WebElement element = getWebElement(sElement);
			  Select sel = new Select(element);
			  String option = sel.getFirstSelectedOption().getText();
			  		  
			  
			 // String option = element.getAttribute("text");
			  return option;

		}
	  
	  
	  public boolean DynamicIsElementDisplayed(String sElement, String pos)  throws Exception
		{
			boolean svalue = false;
			
			try 
			{
				String str = obj.getObjectProperties(sElement);
				temp = str.split(";");
				WebElement webelement = FindElement(temp[0], temp[1]+pos);
				svalue = webelement.isDisplayed();
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
			}
			return svalue;
		}
	  
	  public boolean DynamicIsElementEnabled(String sElement, String pos)  throws Exception
		{
			boolean svalue = false;
			
			try
			{
				String str = obj.getObjectProperties(sElement);
				temp = str.split(";");
				WebElement webelement = FindElement(temp[0], temp[1]+pos);
				svalue = webelement.isEnabled();
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
			return svalue;
		}
	  
	  public String DynamicGetTextandStoreinVariable(String sElement, String pos) throws URISyntaxException, Exception
		{
			
			String appvalue = null;
			WebElement element = DynamicgetWebElement(sElement,pos);
			appvalue = element.getText();
			ReporterClass.logger.log(Status.PASS, "Successfully fetched Value From " + sElement + " is "+appvalue);
			return appvalue;
		}
		
	  
	  public void DynamicClickOnButtonorLink(String sElement, String pos) throws URISyntaxException, Exception
		{
			WebElement element = DynamicgetWebElement(sElement,pos);
			element.click();
			ReporterClass.logger.log(Status.PASS, "Successfully Clicked on " + sElement + " Button\\Link");
		}
	  
	  
	  private WebElement DynamicgetWebElement(String property, String pos) throws IOException, URISyntaxException
		{		 
			String str = obj.getObjectProperties(property);
			 temp = str.split(";");
			 WebElement webelement = FindElement(temp[0], temp[1]+pos);
			 Assert.assertNotNull(webelement);
			 return webelement;
		}
	  
	  
	  public void RemoveValueForCheckBox(String sElement) throws URISyntaxException, Exception
		{
			boolean checkstatus;
			WebElement element = getWebElement(sElement);
			checkstatus = element.isSelected();
			if (checkstatus == true)
			{
				element.click();
				ReporterClass.logger.log(Status.PASS, "Successfully Unchecked on " + sElement + " CheckBox");
				
			}
			/*else
			{
				ReporterClass.logger.log(Status.PASS, sElement + " CheckBox is already unchecked");
			}*/
		}
	  
	  
	  public String DoubleClickText(String sElement) throws URISyntaxException, Exception
      {
             WebElement element = getWebElement(sElement);
             Actions builder = new Actions(driver);
             Action seriesOfActions = builder
                                      .doubleClick(element)                                                                                                             
                                      .keyDown(Keys.CONTROL)           
                                      .sendKeys("c")
                                      .keyUp(Keys.CONTROL)                                                                                 
                                      .build();                  
             seriesOfActions.perform();
             
             Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
             Transferable contents = clipboard.getContents(null);
             String text = (String) contents.getTransferData(DataFlavor.stringFlavor);
             ReporterClass.logger.log(Status.PASS, "Successfully fetched Value From " + sElement + " is "+text);
             return text;
             
      }
	  

	  public void WaitforElementPresent(String sElement) throws IOException, URISyntaxException
	  {
		  String str = obj.getObjectProperties(sElement);
		  temp = str.split(";");
		  WebElement Element = FindElement(temp[0], temp[1]);
		  customWait.until(ExpectedConditions.visibilityOf(Element));
	  }
	  
	  public void WaitforVisiblilityofElement(String sElement) throws IOException, URISyntaxException
	  {
		  String str = obj.getObjectProperties(sElement);
		  temp = str.split(";");
		  WebElement Element = FindElement(temp[0], temp[1]);	  
		  customWait.until(ExpectedConditions.visibilityOf(Element));
	  }
	  
	  public void WaitforElementToBeClickable(String sElement) throws IOException, URISyntaxException
	  {
		  String str = obj.getObjectProperties(sElement);
		  temp = str.split(";");
		  WebElement Element = FindElement(temp[0], temp[1]);
		  customWait.until(ExpectedConditions.elementToBeClickable(Element));
	  }
	  
	  
	  public void WaitforElementToBeSelected(String sElement) throws IOException, URISyntaxException
	  {
		  String str = obj.getObjectProperties(sElement);
		  temp = str.split(";");
		  WebElement Element = FindElement(temp[0], temp[1]);	  
		 customWait.until(ExpectedConditions.elementToBeSelected(Element));
	  }
	  
	  
	  
	  public void WaitforFrameToBeAvailableandSwitchToIt(String sElement) throws IOException, URISyntaxException
	  {
		  String str = obj.getObjectProperties(sElement);
		  temp = str.split(";");
		  WebElement Element = FindElement(temp[0], temp[1]);	  
          customWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Element)); 
	  }
	  
	  public void WaitforInvisiblityofElement(String sElement) throws IOException, URISyntaxException
	  {
		  String str = obj.getObjectProperties(sElement);
		  temp = str.split(";");
		  WebElement Element = FindElement(temp[0], temp[1]);	  
          customWait.until(ExpectedConditions.invisibilityOf(Element));
	  }
	  
	  
	  public void WaitfortextToBePresentInElement(String sElement) throws IOException, URISyntaxException
	  {
		  String str = obj.getObjectProperties(sElement);
		  temp = str.split(";");
		  WebElement Element = FindElement(temp[0], temp[1]);	  
		  customWait.until(ExpectedConditions.textToBePresentInElement(Element, ""));
	  }
	  
	 public void PerformKeyBoardOperations(String keyEvents) throws AWTException, Exception
     {
		 r = new Robot();
    	 if(keyEvents.equalsIgnoreCase("TAB"))
    	 {
    		 r.keyPress(KeyEvent.VK_TAB);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_TAB);
    	 }
    	 if(keyEvents.equalsIgnoreCase("SPACE"))
    	 {
    		 r.keyPress(KeyEvent.VK_SPACE);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_SPACE);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("ENTER"))
    	 {
    		 r.keyPress(KeyEvent.VK_ENTER);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_ENTER);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("BACKSPACE"))
    	 {
    		 r.keyPress(KeyEvent.VK_BACK_SPACE);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_BACK_SPACE);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("DELETE"))
    	 {
    		 r.keyPress(KeyEvent.VK_DELETE);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_DELETE);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("ESCAPE"))
    	 {
    		 r.keyPress(KeyEvent.VK_ESCAPE);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_ESCAPE);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("DOWN"))
    	 {
    		 r.keyPress(KeyEvent.VK_DOWN);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_DOWN);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("UP"))
    	 {
    		 r.keyPress(KeyEvent.VK_UP);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_UP);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("CTRL+ENTER"))
    	 {
    		 r.keyPress(KeyEvent.VK_CONTROL);
    		 r.keyPress(KeyEvent.VK_ENTER);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_ENTER);
    		 r.keyRelease(KeyEvent.VK_CONTROL);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("CTRL+INSERT"))
    	 {
    		 r.keyPress(KeyEvent.VK_CONTROL);
    		 r.keyPress(KeyEvent.VK_INSERT);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_INSERT);
    		 r.keyRelease(KeyEvent.VK_CONTROL);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("COPY"))
    	 {
    		 r.keyPress(KeyEvent.VK_CONTROL);
    		 r.keyPress(KeyEvent.VK_C);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_C);
    		 r.keyRelease(KeyEvent.VK_CONTROL);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("PASTE"))
    	 {
    		 r.keyPress(KeyEvent.VK_CONTROL);
    		 r.keyPress(KeyEvent.VK_V);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_V);
    		 r.keyRelease(KeyEvent.VK_CONTROL);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("SELECTALL"))
    	 {
    		 r.keyPress(KeyEvent.VK_CONTROL);
    		 r.keyPress(KeyEvent.VK_A);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_A);
    		 r.keyRelease(KeyEvent.VK_CONTROL);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("CLEAR"))
    	 {
    		 r.keyPress(KeyEvent.VK_CONTROL);
    		 r.keyPress(KeyEvent.VK_A);
    		 Thread.sleep(200);
    		 r.keyPress(KeyEvent.VK_DELETE);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_DELETE);
    		 r.keyRelease(KeyEvent.VK_A);
    		 r.keyRelease(KeyEvent.VK_CONTROL);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("F7"))
    	 {
    		 r.keyPress(KeyEvent.VK_F7);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_F7);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("ALT+F4"))
    	 {
    		 r.keyPress(KeyEvent.VK_ALT);
    		 r.keyPress(KeyEvent.VK_F4);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_F4);
    		 r.keyRelease(KeyEvent.VK_ALT);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("CTRL+F4"))
    	 {
    		 r.keyPress(KeyEvent.VK_CONTROL);
    		 r.keyPress(KeyEvent.VK_F4);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_F4);
    		 r.keyRelease(KeyEvent.VK_CONTROL);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("CTRL+W"))
    	 {
    		 r.keyPress(KeyEvent.VK_CONTROL);
    		 r.keyPress(KeyEvent.VK_W);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_W);
    		 r.keyRelease(KeyEvent.VK_CONTROL);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("CTRL+F"))
    	 {
    		 r.keyPress(KeyEvent.VK_CONTROL);
    		 r.keyPress(KeyEvent.VK_F);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_F);
    		 r.keyRelease(KeyEvent.VK_CONTROL);
    	 }
    	 else if(keyEvents.equalsIgnoreCase("CTRL+Q"))
    	 {
    		 r.keyPress(KeyEvent.VK_CONTROL);
    		 r.keyPress(KeyEvent.VK_Q);
    		 Thread.sleep(200);
    		 r.keyRelease(KeyEvent.VK_Q);
    		 r.keyRelease(KeyEvent.VK_CONTROL);
    	 }
    	 Thread.sleep(3000);
     }
	 
	 
	 public String CaptureFullScreenShot(String imagename)
     {
    	 SimpleDateFormat sdf2 = new SimpleDateFormat("yyMMMmmss");
         sdf2.setTimeZone(TimeZone.getTimeZone("PST"));
         String datetime = sdf2.format(new Date());
         System.out.println("timeStamp: " + datetime);
         String imageName = imagename+"_"+datetime;
         File file = new File(screenshotsPath);
    	 if (!file.exists())
    	 {
    		 file.mkdir();
    	 }
    	 screen.capture().save(screenshotsPath, imageName);
		return imageName+".png";
     }
	 
	 
	 
	 public void switchtoParentWindow(String nameOrHandle) throws Exception
	 {
		 driver.switchTo().window(nameOrHandle);
		 Thread.sleep(5000);
	 }
	 
	 public void switchtoNewWindow(String nameOrHandle) throws Exception
	 { 
	    // To handle all new opened window
	    Set<String> s1 = driver.getWindowHandles();
	    System.out.println("Child window handle is" + s1);
	    Iterator<String> i1 = s1.iterator();

	    // Here we will check if child window has other child windows and when child window
	    //is the main window it will come out of loop.
	      while (i1.hasNext())
	      {
	          String ChildWindow = i1.next();
	          if (!nameOrHandle.equalsIgnoreCase(ChildWindow))
	          {
	        	  ChildWindowHandle = ChildWindow;
	              driver.switchTo().window(ChildWindow);
	              Thread.sleep(5000);
	              System.out.println("Switched to Child window");
	           }
	       }
	 }
	 
	 
	 public void closeChildWindow(String nameOrHandle) throws Exception
	 {
		// To handle all new opened window
		    Set<String> s1 = driver.getWindowHandles();
		    System.out.println("Child window handle is" + s1);
		    Iterator<String> i1 = s1.iterator();

		    // Here we will check if child window has other child windows and when child window
		    //is the main window it will come out of loop.
		      while (i1.hasNext())
		      {
		          String ChildWindow = i1.next();
		          if (!nameOrHandle.equalsIgnoreCase(ChildWindow))
		          {
		        	  driver.close();
		        	  Thread.sleep(5000);
		     		 System.out.println("Child window closed");
		           }
		       }
		
	 }
	 
	 public void switchtoFrameByIndex(int i) throws InterruptedException
	 {
		  driver.switchTo().frame(i);
		  Thread.sleep(5000);
	 }
	 
	 public void switchbacktoPage() throws Exception
	 {
		 driver.switchTo().defaultContent();
		 Thread.sleep(5000);
	 }

}
