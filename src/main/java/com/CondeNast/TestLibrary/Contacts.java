package com.CondeNast.TestLibrary;

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

public class Contacts extends BaseTest
{

	public void ClickonContacts() throws Exception
	{
		WebElement accounts = uiDriver.driver.findElement(By.xpath("//a[@title='Contacts']/span"));
		JavascriptExecutor executor = (JavascriptExecutor)uiDriver.driver;
		executor.executeScript("arguments[0].click();", accounts);
		Thread.sleep(10000);
	}
	
	
	
	public void ClickonCreateNewContact() throws Exception
	{
		uiDriver.ClickOnButtonorLink("CreateNewContact"); 
		//uiDriver.WaitforElementPresent("NewAccount_Next");
		Thread.sleep(10000);
	}
	
	
	public void fillContactdetails(HashMap<String,String> cusDetails) throws IOException, URISyntaxException, Exception
	{
		
		uiDriver.ClickOnButtonorLink("Salutation");
		 Thread.sleep(500);
		 uiDriver.ClickOnButtonorLink(cusDetails.get("Title"));
		 Thread.sleep(500);

		 uiDriver.SetValueForTextBox("FirstName",cusDetails.get("ForeName"));
		 
		 uiDriver.SetValueForTextBox("LastName",cusDetails.get("SurName"));
		 
		 
		 uiDriver.ClickOnButtonorLink("SearchAccountName");
		 Thread.sleep(10000);
		 uiDriver.driver.findElement(By.xpath("//span[@title='"+cusDetails.get("accountName")+"']")).click();
		Thread.sleep(5000);
		 
		 uiDriver.SetValueForTextBox("Email",cusDetails.get("Email"));
	}
	
	public void fillContactdetails2(HashMap<String,String> cusDetails) throws IOException, URISyntaxException, Exception
	{
		
		uiDriver.ClickOnButtonorLink("Salutation");
		 Thread.sleep(500);
		 uiDriver.ClickOnButtonorLink(cusDetails.get("Title"));
		 Thread.sleep(500);

		 uiDriver.SetValueForTextBox("FirstName",cusDetails.get("ForeName"));
		 
		 uiDriver.SetValueForTextBox("LastName",cusDetails.get("SurName"));
		 
		 
		 uiDriver.ClickOnButtonorLink("SearchAccountName");
		 Thread.sleep(5000);
		 uiDriver.driver.findElement(By.xpath("//span[@title='"+cusDetails.get("accountName")+"']")).click();
		Thread.sleep(5000);
		 
		 uiDriver.SetValueForTextBox("Email",cusDetails.get("Email"));
		 
		 uiDriver.scrolltoViewElement("InvoiceContact");
		 uiDriver.ClickOnButtonorLink("InvoiceContact");
		 Thread.sleep(500);
	}
	
	
	public String SaveContactDetails() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("SaveContactDetails");
		Thread.sleep(15000);
		String img1 = uiDriver.CaptureFullScreenShot("ContactPage");
		reporter.addScreenshotToReport(img1, "Contact Created.");
		Thread.sleep(5000);
		String maname = uiDriver.GetTextandStoreinVariable("ContactName");
		return maname;
	}
	
	public void CancelContactCreation() throws Exception
	{
		uiDriver.ClickOnButtonorLink("CancelContactChanges");
		Thread.sleep(5000);
	}
	
	
	public void EditContactDetails() throws Exception
	{
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//button[@name='Edit']"))).click().build().perform();
		Thread.sleep(5000);
		uiDriver.WaitforElementPresent("SaveContactDetails");
	}
	
	public void DeleteContactDetails() throws Exception
	{
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//button[@name='Delete']"))).click().build().perform();
		Thread.sleep(5000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(driver.findElement(By.xpath("//button[@title='Delete']"))).click().build().perform();
		Thread.sleep(15000);
		reporter.logger.log(Status.PASS, "Successfully Clicked on Delete");
		String img1 = uiDriver.CaptureFullScreenShot("Quote");
		reporter.addScreenshotToReport(img1, "Contact Deleted Succesfully");
	}
	
	public void SearchandClickContact(String mcontact) throws FindFailed, Exception
	{
		uiDriver.screen.paste(Pattern.SearchContacts, mcontact);
		Thread.sleep(15000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(uiDriver.driver.findElement(By.xpath("//span[@title='"+mcontact+"']"))).click().build().perform();
		Thread.sleep(15000);
	}
	
	public void VerifyNewButtontoCreateContact()
	{
		if(uiDriver.driver.findElements(By.xpath("//a[@title='New']")).size()>0)
		{
			reporter.logger.log(Status.FAIL, "New button to Create Contact Present");
		}
		else
		{
			reporter.logger.log(Status.PASS, "New button to Create Contact is not Present");
		}
	}
	
	
	public void VerifyEditButtontoeditContact()
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
	
	
	public void VerifyDeleteOptionforContact(String scontact) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+scontact+"']/../../following-sibling::td/span/div")).click();
		Thread.sleep(9000);
		if(uiDriver.driver.findElements(By.xpath("//button[@title='Delete']")).size()>0)
		{
			reporter.logger.log(Status.FAIL, "Delete option for Contact Present");
		}
		else
		{
			reporter.logger.log(Status.PASS, "Delete option for Contact is not Present");
			String img1 = uiDriver.CaptureFullScreenShot("MarketAccount");
			reporter.addScreenshotToReport(img1, "Market Account Delete Option");
			Thread.sleep(5000);
		}
	}
	
	
	public void ClickonRelatedTab() throws URISyntaxException, Exception
	{
		uiDriver.screen.click(Pattern.RelatedTab);
		Thread.sleep(10000);
		uiDriver.WaitforVisiblilityofElement("AddRelationship");
	}
	
	
	public void ClickonAddRelationship() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("AddRelationship");
		Thread.sleep(5000);
		uiDriver.WaitforVisiblilityofElement("Roles");
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
		WebElement accounts = uiDriver.driver.findElement(By.xpath("(//span[text()='Save'])[3]"));
		JavascriptExecutor executor = (JavascriptExecutor)uiDriver.driver;
		executor.executeScript("arguments[0].click();", accounts);
		Thread.sleep(10000);
	}
	
	
	public void validatefields() throws URISyntaxException, Exception
	{

		if(uiDriver.driver.findElements(By.xpath("//span[text()='Do Not Contact']/../following-sibling::div/span/input")).size()>0)
		{
			reporter.logger.log(Status.PASS, "Do Not Contact  is Present");
		}
		else
		{
			reporter.logger.log(Status.FAIL, "Do Not Contact is not Present");
		}
		
		
		if(uiDriver.driver.findElements(By.xpath("//label[text()='Assistant Email']/following-sibling::div/input")).size()>0)
		{
			reporter.logger.log(Status.PASS, "Assistant Email is Present");
		}
		else
		{
			reporter.logger.log(Status.FAIL, "Assistant Email is not Present");
		}
		
		uiDriver.scrolltoViewElement("Source");
		uiDriver.ClickOnButtonorLink("Source");
		Thread.sleep(10000);
		String sval1 = null;
		List<WebElement> val=uiDriver.driver.findElements(By.xpath("//label[text()='Source']/following-sibling::div/lightning-base-combobox/div/div[2]/lightning-base-combobox-item"));
		System.out.println("Total Number of All Element = "  + val.size());
	
		for(int i=2;i<=val.size();i++)
		{
			WebElement linkElement = uiDriver.driver.findElement(By.xpath("//label[text()='Source']/following-sibling::div/lightning-base-combobox/div/div[2]/lightning-base-combobox-item["+i+"]/span[2]/span"));
        	String sval = linkElement.getText().trim();
        	if(sval1 == null)
        	{
        		sval1 = sval;
        	}
        	else
        	{
        		sval1 = sval1+";"+sval;
        	}
		 }
		
		System.out.println("Element = "  + sval1);
		reporter.logger.log(Status.PASS, "Source Lisbox Values "+sval1);
		

	}
	
	
	
}
