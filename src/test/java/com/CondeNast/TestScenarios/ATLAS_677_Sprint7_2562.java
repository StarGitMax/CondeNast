package com.CondeNast.TestScenarios;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.ClientBrand;
import com.CondeNast.TestLibrary.ClientProducts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_677_Sprint7_2562 extends BaseTest
{

	SalesforceLogin sl = new SalesforceLogin();
	ClientBrand cb = new ClientBrand();
	ClientProducts cp = new ClientProducts();
	Opportunities opp = new Opportunities();
	String cProductName = "Benz";
	String cbrandName = "Acer";
	String sfUser_RA = "Eleanor Matthews";
	String sfUser_SA = "Ariana Stachrowski";
	String sfUser_SL = "Antonia Wigan";
	
	//@Test
	public void ValidateClientBrands() throws Exception
	{
		sl.NavigateandLogin();
		
		cb.ClickonClientBrands();
		cb.CreateNewClientBrand();
		uiDriver.WaitforElementPresent("BrandName");
		uiDriver.SetValueForTextBox("BrandName", cbrandName);
		uiDriver.ClickOnButtonorLink("BrandDescription");
		Thread.sleep(2000);
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		
		uiDriver.ClickOnButtonorLink("CancelClientBrandChanges");
		Thread.sleep(5000);
		
		cp.ClickonClientProducts();
		cp.CreateNewClientProduct();
		
		uiDriver.ClickOnButtonorLink("ClientBrand");
		Thread.sleep(10000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+cbrandName+"']")).click();
		Thread.sleep(5000);
		
		uiDriver.SetValueForTextBox("ClientProductName", cProductName);
		
		uiDriver.ClickOnButtonorLink("MarketAccountCategory");
		Thread.sleep(5000);
		
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		
		uiDriver.ClickOnButtonorLink("CancelClientProductChanges");
		Thread.sleep(5000);
		
		sl.Logout();
	}
	
	
	@Test
	public void ValidateClientBrands2() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SL);
		
		cb.ClickonClientBrands();
		cb.CreateNewClientBrand();
		uiDriver.WaitforElementPresent("BrandName");
		uiDriver.SetValueForTextBox("BrandName", cbrandName);
		uiDriver.ClickOnButtonorLink("BrandDescription");
		Thread.sleep(2000);
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		
		uiDriver.ClickOnButtonorLink("CancelClientBrandChanges");
		Thread.sleep(5000);
		
		cp.ClickonClientProducts();
		cp.CreateNewClientProduct();
		
		uiDriver.ClickOnButtonorLink("ClientBrand");
		Thread.sleep(10000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+cbrandName+"']")).click();
		Thread.sleep(5000);
		
		uiDriver.SetValueForTextBox("ClientProductName", cProductName);
		
		uiDriver.ClickOnButtonorLink("MarketAccountCategory");
		Thread.sleep(5000);
		
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		
		uiDriver.ClickOnButtonorLink("CancelClientProductChanges");
		Thread.sleep(5000);
		
		opp.LogoutofSalesForceUser(sfUser_SL);
		Thread.sleep(5000);
	}
	
}
