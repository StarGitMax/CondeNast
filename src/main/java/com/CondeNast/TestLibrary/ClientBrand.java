package com.CondeNast.TestLibrary;

import java.net.URISyntaxException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import com.CondeNast.Core.UtilityFunctions.Mode;
import com.aventstack.extentreports.Status;

public class ClientBrand extends BaseTest
{
  
	public void ClickonClientBrands() throws Exception
	{
		WebElement Opportunities = uiDriver.driver.findElement(By.xpath("//a[@title='Client Brands']/span"));
		JavascriptExecutor executor = (JavascriptExecutor)uiDriver.driver;
		executor.executeScript("arguments[0].click();", Opportunities);
		Thread.sleep(5000);
		uiDriver.WaitforElementPresent("CreateNewClientBrand");
	}
	
	
	public void CreateNewClientBrand() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("CreateNewClientBrand");
		Thread.sleep(5000);
		uiDriver.WaitforElementPresent("BrandName");
	}
	
	
	public void filldetails() throws Exception
	{
		String randNo = utilFuns.GenerateRandom(4, Mode.ALPHANUMERIC);
		String marName = "Brand_"+randNo;
		
		uiDriver.WaitforElementPresent("BrandName");
		uiDriver.SetValueForTextBox("BrandName", marName);
		
		uiDriver.ClickOnButtonorLink("MarketAccountCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("AUTAutomotive");
		Thread.sleep(5000);
		
		uiDriver.ClickOnButtonorLink("MarketAccountSubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Cars_Asian");
		Thread.sleep(5000);

	}
	
	
	public String SaveClientBrandDetails() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("SaveEditClientBrandDetails");
		Thread.sleep(10000);
		String img1 = uiDriver.CaptureFullScreenShot("ClientBrandDetails");
		reporter.addScreenshotToReport(img1, "Client Brand Created/Modified Succesfully");
		uiDriver.WaitforVisiblilityofElement("EditClientBrands");
		String maname = uiDriver.GetTextandStoreinVariable("ClientBrandName");
		return maname;
	}
	
	public void EditClientBrand() throws Exception
	{
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//button[@name='Edit']"))).click().build().perform();
		Thread.sleep(5000);
		uiDriver.WaitforElementPresent("SaveEditClientBrandDetails");
	}
	
	
	public void DeleteClientBrand(String sAccount) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+sAccount+"']/../../following-sibling::td/span/div")).click();
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("MarketAccountSubMenuDelete");
		Thread.sleep(5000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(driver.findElement(By.xpath("//button[@title='Delete']"))).click().build().perform();
		Thread.sleep(2000);
		reporter.logger.log(Status.PASS, "Successfully Clicked on Delete");
		String img1 = uiDriver.CaptureFullScreenShot("ClientBrand");
		reporter.addScreenshotToReport(img1, "Client Brand Deleted Succesfully");
		Thread.sleep(5000);
	}
	
	
	public void SearchandClickonClientBrand(String mAccount) throws FindFailed, Exception
	{
		uiDriver.screen.paste(Pattern.SearchClientBrand, mAccount);
		Thread.sleep(10000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(uiDriver.driver.findElement(By.xpath("//span[@title='"+mAccount+"']"))).click().build().perform();
		Thread.sleep(1000);
	}
	
	public void ClickonExistingClientBrand(String mAccount) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+mAccount+"']")).click();
		Thread.sleep(15000);
		uiDriver.WaitforElementPresent("EditMarketAccount");
	}
	
	
	public void VerifyNewButtontoCreateClientBrand()
	{
		if(uiDriver.driver.findElements(By.xpath("//a[@title='New']")).size()>0)
		{
			reporter.logger.log(Status.PASS, "New button to Create Client Brand is not Present");
		}
		else
		{
			reporter.logger.log(Status.FAIL, "New button to Create Client Brand Present");
			
		}
	}
	
	
	public void VerifyEditButtontoeditClientBrand()
	{
		if(uiDriver.driver.findElements(By.xpath("//button[@name='Edit']")).size()>0)
		{
			reporter.logger.log(Status.PASS, "Edit button is Present");
		}
		else
		{
			reporter.logger.log(Status.FAIL, "Edit button is not Present");
		}
	}
	
	
	public void VerifyDeleteOptionforClientBrand(String sAccount) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+sAccount+"']/../../following-sibling::td/span/div")).click();
		Thread.sleep(9000);
		if(uiDriver.driver.findElements(By.xpath("//button[@title='Delete']")).size()>0)
		{
			reporter.logger.log(Status.FAIL, "Delete option for Client Brand Present");
		}
		else
		{
			reporter.logger.log(Status.PASS, "Delete option for Client Brand is not Present");
			String img1 = uiDriver.CaptureFullScreenShot("ClientBrand");
			reporter.addScreenshotToReport(img1, "Client Brand Delete Option");
			Thread.sleep(5000);
		}
	}
	
	
	public void CancelClientBrandCreation() throws Exception
	{
		uiDriver.ClickOnButtonorLink("CancelClientBrandChanges");
		Thread.sleep(5000);
	}
	
	
	
}
