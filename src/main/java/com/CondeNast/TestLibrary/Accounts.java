package com.CondeNast.TestLibrary;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;

import com.aventstack.extentreports.Status;

public class Accounts extends BaseTest
{
       
	public void ClickonAccounts() throws Exception
	{
		WebElement accounts = uiDriver.driver.findElement(By.xpath("//a[@title='Accounts']/span"));
		JavascriptExecutor executor = (JavascriptExecutor)uiDriver.driver;
		executor.executeScript("arguments[0].click();", accounts);
		Thread.sleep(10000);
	}
	
	
	public void ClickonCreateNewAccount() throws Exception
	{
		uiDriver.ClickOnButtonorLink("CreateNewAccount"); 
		uiDriver.WaitforElementPresent("NewAccount_Next");
		Thread.sleep(5000);
	}
	
	
	public void ClickonEditAccount() throws Exception
	{
		uiDriver.ClickOnButtonorLink("EditAccount");
		Thread.sleep(5000);
		uiDriver.WaitforElementPresent("AccountNotes");
	}
	
	
	public void SelectAccountType(String accountType) throws AWTException, Exception
	{
		try {
			uiDriver.ClickOnButtonorLink(accountType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			uiDriver.screen.click(Pattern.AccountClient);
		}
		Thread.sleep(1000);
	}
	
	
	public void ClickNext() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("NewAccount_Next");
		Thread.sleep(1000);
		uiDriver.WaitforVisiblilityofElement("AccountName");
	}
	
	public void CancelAccountCreation() throws Exception
	{
		uiDriver.ClickOnButtonorLink("CancelEditChanges");
		Thread.sleep(5000);
	}
	
	public String SaveAccountDetails() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("SaveAccountDetails");
		Thread.sleep(15000);
		String img1 = uiDriver.CaptureFullScreenShot("AccountPage");
		reporter.addScreenshotToReport(img1, "Account Created.");
		Thread.sleep(5000);
		String accname = uiDriver.GetTextandStoreinVariable("Account_Name");
		return accname;
	}
	
	public void SaveandNew() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("SaveandNew");
		uiDriver.WaitforElementPresent("NewAccount_Next");
	}
	
	public void fillAccountdetails(HashMap<String,String> cusDetails) throws IOException, URISyntaxException, Exception
	{

		 uiDriver.SetValueForTextBox("AccountName",cusDetails.get("AccountName"));
		 
		 uiDriver.SetValueForTextBox("CompanyRegistrationNumber",cusDetails.get("RegistrationNumber"));
		 
		 WebElement BillingAddress = uiDriver.driver.findElement(By.xpath("//legend[text()='Billing Address']/following-sibling::div/div/div/lightning-lookup-address/div/lightning-base-combobox/div/div/input"));
		 BillingAddress.sendKeys(cusDetails.get("BillingAddress"));
		 Thread.sleep(5000);
		 BillingAddress.sendKeys(Keys.ENTER);
		 Thread.sleep(5000);
		 
		 uiDriver.ClickOnButtonorLink("AccountClass");
		 Thread.sleep(500);
		 uiDriver.ClickOnButtonorLink(cusDetails.get("AccountClass"));
		 Thread.sleep(500);
		 
		uiDriver.scrolltoViewElement("AccountCategory");
	    uiDriver.ClickOnButtonorLink("AccountCategory");
	    Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("AUTAutomotive");
		Thread.sleep(5000);
			
		uiDriver.ClickOnButtonorLink("SubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Cars_American");
		Thread.sleep(5000);
		 
	}
	
	public void validateFields()
	{
		
			if(uiDriver.driver.findElements(By.xpath("//span[text()='Shipping Address']/../following-sibling::div/div/button/span")).size()>0)
			{
				reporter.logger.log(Status.INFO, "Shipping Address  Present");
			}
			else
			{
			   reporter.logger.log(Status.PASS, "Shipping Address is not Present");
		    }
			
			
			if(uiDriver.driver.findElements(By.xpath("//label[text()='Account Class']/following-sibling::div/lightning-base-combobox/div/div/input")).size()>0)
			{
				reporter.logger.log(Status.PASS, "Account Class is  Present");
			}
			else
			{
			   reporter.logger.log(Status.FAIL, "Account Class is not Present");
		    }
			
			if(uiDriver.driver.findElements(By.xpath("//label[text()='Category']/following-sibling::div/lightning-base-combobox/div/div/input")).size()>0)
			{
				reporter.logger.log(Status.PASS, "Category is  Present");
			}
			else
			{
			   reporter.logger.log(Status.FAIL, "Category is not Present");
		    }
			

			if(uiDriver.driver.findElements(By.xpath("//label[text()='Sub-Category']/following-sibling::div/lightning-base-combobox/div/div/input")).size()>0)
			{
				reporter.logger.log(Status.PASS, "Sub-Category is  Present");
			}
			else
			{
			   reporter.logger.log(Status.FAIL, "Sub-Category is not Present");
		    }
			
			if(uiDriver.driver.findElements(By.xpath("//label[text()='Notes']/following-sibling::div/textarea")).size()>0)
			{
				reporter.logger.log(Status.PASS, "Notes is  Present");
			}
			else
			{
			   reporter.logger.log(Status.FAIL, "Notes is not Present");
		    }
			

			if(uiDriver.driver.findElements(By.xpath("//label[text()='Company Identification Number']/following-sibling::div/input")).size()>0)
			{
				reporter.logger.log(Status.PASS, "Company Identification Number is  Present");
			}
			else
			{
			   reporter.logger.log(Status.FAIL, "Company Identification Number is not Present");
		    }
	
	}
	
	public void fillAccountdetails2(HashMap<String,String> cusDetails) throws IOException, URISyntaxException, Exception
	{

		 Thread.sleep(9000);
		 uiDriver.WaitforVisiblilityofElement("AccountName");
		 uiDriver.SetValueForTextBox("AccountName",cusDetails.get("AccountName"));
		 
		 WebElement BillingAddress = uiDriver.driver.findElement(By.xpath("//legend[text()='Billing Address']/following-sibling::div/div/div/lightning-lookup-address/div/lightning-base-combobox/div/div/input"));
		 BillingAddress.sendKeys(cusDetails.get("BillingAddress"));
		 Thread.sleep(5000);
		 BillingAddress.sendKeys(Keys.ENTER);
		 Thread.sleep(5000);
		 
		 uiDriver.scrolltoViewElement("AccountClass");
		 uiDriver.ClickOnButtonorLink("AccountClass");
		 Thread.sleep(500);
		 uiDriver.ClickOnButtonorLink(cusDetails.get("AccountClass"));
		 Thread.sleep(500);
		 
		uiDriver.ClickOnButtonorLink("AccountCategory");
	    Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("AUTAutomotive");
		Thread.sleep(5000);
			
		uiDriver.ClickOnButtonorLink("SubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Cars_American");
		Thread.sleep(5000);
		 
	}
	
	public void fillAccountdetails4(HashMap<String,String> cusDetails) throws IOException, URISyntaxException, Exception
	{

		 Thread.sleep(9000);
		 uiDriver.WaitforVisiblilityofElement("AccountName");
		 uiDriver.SetValueForTextBox("AccountName",cusDetails.get("AccountName"));
		 
		 WebElement BillingAddress = uiDriver.driver.findElement(By.xpath("//legend[text()='Billing Address']/following-sibling::div/div/div/lightning-lookup-address/div/lightning-base-combobox/div/div/input"));
		 BillingAddress.sendKeys(cusDetails.get("BillingAddress"));
		 Thread.sleep(5000);
		 BillingAddress.sendKeys(Keys.ENTER);
		 Thread.sleep(5000);
		 
		 uiDriver.scrolltoViewElement("AccountClass");
		 uiDriver.ClickOnButtonorLink("AccountClass");
		 Thread.sleep(500);
		 uiDriver.ClickOnButtonorLink(cusDetails.get("AccountClass"));
		 Thread.sleep(500);
		 
		uiDriver.ClickOnButtonorLink("AccountCategory");
	    Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink(cusDetails.get("AccountCategory"));
		Thread.sleep(5000);
			
		uiDriver.ClickOnButtonorLink("SubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink(cusDetails.get("SubCategory"));
		Thread.sleep(5000);
		 
	}
	
	public void fillAccountdetails3(HashMap<String,String> cusDetails) throws IOException, URISyntaxException, Exception
	{

		 uiDriver.SetValueForTextBox("AccountName",cusDetails.get("AccountName"));
		 
		 uiDriver.ClickOnButtonorLink("ParentAccount");
		 Thread.sleep(500);
		
		 WebElement BillingAddress = uiDriver.driver.findElement(By.xpath("//legend[text()='Billing Address']/following-sibling::div/div/div/lightning-lookup-address/div/lightning-base-combobox/div/div/input"));
		 BillingAddress.sendKeys(cusDetails.get("BillingAddress"));
		 Thread.sleep(5000);
		 BillingAddress.sendKeys(Keys.ENTER);
		 Thread.sleep(5000);
		 
		 uiDriver.scrolltoViewElement("AccountClass");
		 uiDriver.ClickOnButtonorLink("AccountClass");
		 Thread.sleep(500);
		 uiDriver.ClickOnButtonorLink(cusDetails.get("AccountClass"));
		 Thread.sleep(500);
		 
		 uiDriver.ClickOnButtonorLink("AgencyClassification");
		 Thread.sleep(500);
		 uiDriver.ClickOnButtonorLink(cusDetails.get("AgencyClassification"));
		 Thread.sleep(500);
		 
		uiDriver.ClickOnButtonorLink("AccountCategory");
	    Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("AUTAutomotive");
		Thread.sleep(5000);
			
		uiDriver.ClickOnButtonorLink("SubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Cars_American");
		Thread.sleep(5000);
		 
	}
	
	
	public void DeleteAccount(String sAccount) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+sAccount+"']/../../following-sibling::td/span/div")).click();
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("MarketAccountSubMenuDelete");
		Thread.sleep(5000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(driver.findElement(By.xpath("//button[@title='Delete']"))).click().build().perform();
		Thread.sleep(25000);
		reporter.logger.log(Status.PASS, "Successfully Clicked on Delet");
		String img1 = uiDriver.CaptureFullScreenShot("Quote");
		reporter.addScreenshotToReport(img1, "Account Deleted Succesfully");
	}
	
	public void VerifyNewButtontoCreateAccount()
	{
		
		try {
			if(uiDriver.driver.findElements(By.xpath("//a[@title='New']")).size()>0)
			{
				reporter.logger.log(Status.FAIL, "New button to Create Account Present");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reporter.logger.log(Status.PASS, "New button to Create Account is not Present");
		}
		
	}
	
	
	public void VerifyEditButtontoeditAccount()
	{
		if(uiDriver.driver.findElements(By.xpath("//button[@name='Edit']")).size()>0)
		{
			reporter.logger.log(Status.FAIL, "Edit button is Present");
		}
		else
	    {
		   reporter.logger.log(Status.PASS, "Edit button is not Present");
	    }
	}
	
	
	public void VerifyDeleteOptionforAccount(String sAccount) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+sAccount+"']/../../following-sibling::td/span/div")).click();
		Thread.sleep(9000);
		
		if(uiDriver.driver.findElements(By.xpath("//button[@title='Delete']")).size()>0)
		{
			reporter.logger.log(Status.FAIL, "Delete option for Account Present");
		}
		else
	   {
			// TODO Auto-generated catch block
			reporter.logger.log(Status.PASS, "Delete option for Account is not Present");
			String img1 = uiDriver.CaptureFullScreenShot("MarketAccount");
			reporter.addScreenshotToReport(img1, "Market Account Delete Option");
	  }
	}
	
	
	public void SearchandClickAccount(String mAccount) throws FindFailed, Exception
	{
		uiDriver.screen.paste(Pattern.SearchAccounts, mAccount);
		Thread.sleep(15000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(uiDriver.driver.findElement(By.xpath("//span[@title='"+mAccount+"']"))).click().build().perform();
		Thread.sleep(15000);
	}
	
	
	public void ClickonRelatedTab() throws URISyntaxException, Exception
	{
		uiDriver.screen.click(Pattern.RelatedTab);
		Thread.sleep(10000);
		uiDriver.WaitforElementPresent("AddRelationship");
	}
	
	
	public void ClickonAddRelationship() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("AddRelationship");
		Thread.sleep(5000);
		uiDriver.WaitforElementPresent("Roles");
	}
	
	public void SelectRole(String sRole) throws Exception
	{
		List<WebElement> val=uiDriver.driver.findElements(By.xpath("//div[text()='Roles']/following-sibling::div/div/div[3]/div/ul/li"));
		System.out.println("Total Number of All Element = "  + val.size());
	
		for(int i=1;i<=val.size();i++)
		{
			WebElement linkElement = uiDriver.driver.findElement(By.xpath("//div[text()='Roles']/following-sibling::div/div/div[3]/div/ul/li["+i+"]/div/span/span"));
        	String sval = linkElement.getText().trim();
        	System.out.println("Element = "  + sval);
            if(sval.equalsIgnoreCase(sRole))
			 {
        		System.out.println("sval "+sval);
            	linkElement.click();
            	Thread.sleep(3000);
            	uiDriver.ClickOnButtonorLink("MoveSelectedCurrencyToRight");
            	Thread.sleep(3000);
            	reporter.logger.log(Status.PASS, "Available Market(s) Lisbox Value "+sval+" is Selected");
            	break;
              }
		 }
	}
	
	
	public void SaveAccountContactRelationship() throws Exception
	{
		WebElement accounts = uiDriver.driver.findElement(By.xpath("//button[@title='Save']"));
		JavascriptExecutor executor = (JavascriptExecutor)uiDriver.driver;
		executor.executeScript("arguments[0].click();", accounts);
		Thread.sleep(10000);
		String img1 = uiDriver.CaptureFullScreenShot("AccountPage");
		reporter.addScreenshotToReport(img1, "Account Created.");
		Thread.sleep(10000);
	}
	
	
	public void fillandsaveContactdetails(HashMap<String,String> cusDetails) throws IOException, URISyntaxException, Exception
	{
		
		uiDriver.ClickOnButtonorLink("Accounts_Salutation");
		 Thread.sleep(600);
		 uiDriver.driver.findElement(By.xpath("//a[@title='"+cusDetails.get("Title")+"']")).click();
		 Thread.sleep(500);

		 uiDriver.SetValueForTextBox("Accounts_FirstName",cusDetails.get("ForeName"));
		 
		 uiDriver.SetValueForTextBox("Accounts_LastName",cusDetails.get("SurName"));
		 
		 
		 uiDriver.driver.findElement(By.xpath("(//input[@title='Search Accounts'])[2]")).click();
		 Thread.sleep(10000);
		 uiDriver.driver.findElement(By.xpath("//div[@title='"+cusDetails.get("AccountName")+"']")).click();
		 Thread.sleep(5000);
		 
		 uiDriver.SetValueForTextBox("Accounts_Email",cusDetails.get("Email"));
		 
		 uiDriver.ClickOnButtonorLink("Accounts_Save");
	     Thread.sleep(15000);
	}
	
	
	
}
