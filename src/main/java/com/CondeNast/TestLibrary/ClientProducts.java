package com.CondeNast.TestLibrary;

import java.net.URISyntaxException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import com.CondeNast.Core.UtilityFunctions.Mode;
import com.aventstack.extentreports.Status;

public class ClientProducts extends BaseTest
{
  
	public void ClickonClientProducts() throws Exception
	{
		WebElement Opportunities = uiDriver.driver.findElement(By.xpath("//a[@title='Client Products']/span"));
		JavascriptExecutor executor = (JavascriptExecutor)uiDriver.driver;
		executor.executeScript("arguments[0].click();", Opportunities);
		Thread.sleep(5000);
		uiDriver.WaitforElementPresent("CreateNewClientProduct");
	}
	
	
	public void CreateNewClientProduct() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("CreateNewClientProduct");
		Thread.sleep(5000);
		uiDriver.WaitforElementPresent("CreateNewClientProduct");
	}
	

	public void fillDetails(String sBrand) throws URISyntaxException, Exception
	{
		uiDriver.WaitforElementPresent("ClientProductName");
		String randNo = utilFuns.GenerateRandom(4, Mode.ALPHANUMERIC);
		String marName = "Product_"+randNo;
		
		uiDriver.ClickOnButtonorLink("ClientBrand");
		Thread.sleep(10000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+sBrand+"']")).click();
		Thread.sleep(5000);
		
		uiDriver.SetValueForTextBox("ClientProductName", marName);
		
		uiDriver.ClickOnButtonorLink("MarketAccountCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("AUTAutomotive");
		Thread.sleep(10000);
		
		uiDriver.ClickOnButtonorLink("MarketAccountSubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Cars_Asian");
		Thread.sleep(5000);
	}
	

	public String SaveClientProductDetails() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("SaveEditClientProductsDetails");
		Thread.sleep(1000);
		String img1 = uiDriver.CaptureFullScreenShot("ClientProductDetails");
		reporter.addScreenshotToReport(img1, "Client Product Created/Modified Succesfully");
		Thread.sleep(2000);
		String maname = uiDriver.GetTextandStoreinVariable("ClientProduct_Name");
		return maname;
	}
	

	public void EditClientProduct() throws Exception
	{
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("(//button[@name='Edit'])[2]"))).click().build().perform();
		Thread.sleep(5000);
		uiDriver.WaitforElementPresent("SaveEditClientProductsDetails");
	}
	
	
	public void DeleteClientBrand(String sAccount) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+sAccount+"']/../../following-sibling::td/span/div")).click();
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("MarketAccountSubMenuDelete");
		Thread.sleep(5000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(driver.findElement(By.xpath("//button[@title='Delete']"))).click().build().perform();
		Thread.sleep(5000);
		reporter.logger.log(Status.PASS, "Successfully Clicked on Delete");
		String img1 = uiDriver.CaptureFullScreenShot("ClientProduct");
		reporter.addScreenshotToReport(img1, "Client Product Deleted Succesfully");
	}
	
	
	public void SearchandClickonClientProduct(String mAccount) throws FindFailed, Exception
	{
		uiDriver.screen.paste(Pattern.SearchClientProduct, mAccount);
		Thread.sleep(15000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(uiDriver.driver.findElement(By.xpath("//span[@title='"+mAccount+"']"))).click().build().perform();
		Thread.sleep(15000);
	}
	
	
	public void ClickonExistingClientProduct(String mAccount) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+mAccount+"']")).click();
		Thread.sleep(15000);
		uiDriver.WaitforVisiblilityofElement("EditClientProducts");
	}
	
	

	public void VerifyNewButtontoCreateClientProduct()
	{
		if(uiDriver.driver.findElements(By.xpath("//a[@title='New']")).size()>0)
		{
			reporter.logger.log(Status.PASS, "New button to Create Client Product Present");
		}
		else
		{
			reporter.logger.log(Status.FAIL, "New button to Create Client Product is not Present");
		}
	}
	
	
	public void VerifyEditButtontoeditClientProduct()
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
	
	
	public void VerifyDeleteOptionforClientProduct(String sAccount) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+sAccount+"']/../../following-sibling::td/span/div")).click();
		Thread.sleep(9000);
		if(uiDriver.driver.findElements(By.xpath("//button[@title='Delete']")).size()>0)
		{
			reporter.logger.log(Status.FAIL, "Delete option for Client Product Present");
		}
		else
		{
			reporter.logger.log(Status.PASS, "Delete option for Client Product is not Present");
			String img1 = uiDriver.CaptureFullScreenShot("ClientProduct");
			reporter.addScreenshotToReport(img1, "Client Product Delete Option");
			Thread.sleep(5000);
		}
	}
	
	public void CancelClientProductCreation() throws Exception
	{
		uiDriver.ClickOnButtonorLink("CancelClientProductChanges");
		Thread.sleep(5000);
	}
	
	
}

