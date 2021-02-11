package com.CondeNast.TestScenarios.SalesAgent;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;
import com.aventstack.extentreports.Status;

public class ATLAS_1120_Sprint5_TC_1885 extends BaseTest
{

	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	Accounts acc = new Accounts();
	String oppdet;
	String AccountName;
	String snames;
	String sname;
	String OppName;
	int rowcount;
	String sfUser_SA = "Ariana Stachrowski";

	@Test
	public void ValidateRLIforchangeinDates() throws Exception
	{
		
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SA);
		
		AccountName = CreateAccount();
		
		opp.ClickonOpportunities();	
		opp.ClickonCreateNewOpportunities();
		String sval = opp.fillOpportunitiesForm(AccountName,"18000","28/02/2021","IdentifyingOpportunity","NA");
		opp.SaveOpportunitiesDetails();
		opp.CreateQuotes();
		rowcount = opp.getQuoteLineItemsCount();
		if(rowcount>0)
		{
			snames = opp.getQuoteLineItemName();
		}
		
		opp.CreateNewQuoteLineItem();
		opp.FillQuoteLineItemFields("18000","01/01/2021","30/06/2021","Digital");
		opp.SelectAvailableMultipleMarkets("GCP HQ;Australia", "6000;4000");
		opp.RefreshRevenueSection();
		opp.fillRevenueLiesDetails("18000", "10");
		opp.SaveQuoteLineItem();
		sname = getQLIdata();
		
		
		
		opp.EditQuoteLine(sname);
		ValidateRLIfor3quaters("01/12/2020","30/05/2021");
		opp.SaveQuoteLineItem();
		opp.EditQuoteLine(sname);
		ValidateRLIchangequaters("01/12/2020","30/03/2021");
		opp.CloseErrorPopUp();
		opp.CloseErrorPopUp();
		opp.editRevenueLiesDetails("9000;9000");
		opp.SaveQuoteLineItem();
		
		opp.LogoutofSalesForceUser(sfUser_SA);
		Thread.sleep(5000);
	}
	
	
	public void ValidateRLIfor3quaters(String startDate, String endDate) throws Exception
	{
		Actions act1 = new Actions(uiDriver.driver);
		act1.moveToElement(driver.findElement(By.xpath("//a[text()='Revenue']"))).click().build().perform();
		Thread.sleep(3000);
		uiDriver.ClearTextBox("StartDate");
	    uiDriver.SetValueForTextBox("StartDate",startDate);
	    uiDriver.ClearTextBox("EndDate");
		uiDriver.SetValueForTextBox("EndDate", endDate);
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//button[@title='Save record and close the window']"))).click().build().perform();
		Thread.sleep(5000);
		opp.CloseErrorPopUp();
		opp.CloseErrorPopUp();
		Actions act2 = new Actions(uiDriver.driver);
		act2.moveToElement(driver.findElement(By.xpath("//a[text()='Revenue']"))).click().build().perform();
		Thread.sleep(3000);
		act1.moveToElement(driver.findElement(By.xpath("//a[text()='Revenue']/following-sibling::span/span/button"))).click().build().perform();
		Thread.sleep(10000);
		JavascriptExecutor js = (JavascriptExecutor) uiDriver.driver;
		WebElement Element = uiDriver.driver.findElement(By.xpath("//td[text()='Total']/following-sibling::td[3]"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
		Thread.sleep(5000);
		String img1 = uiDriver.CaptureFullScreenShot("Date");
		reporter.addScreenshotToReport(img1, "ValidateRLI");
		Thread.sleep(10000);
	}
	
	public void ValidateRLIchangequaters(String startDate, String endDate) throws Exception
	{
		Actions act1 = new Actions(uiDriver.driver);
		act1.moveToElement(driver.findElement(By.xpath("//a[text()='Revenue']"))).click().build().perform();
		Thread.sleep(3000);
		uiDriver.ClearTextBox("StartDate");
	    uiDriver.SetValueForTextBox("StartDate",startDate);
	    uiDriver.ClearTextBox("EndDate");
		uiDriver.SetValueForTextBox("EndDate", endDate);
		uiDriver.PerformKeyBoardOperations("TAB");
		Actions act2 = new Actions(uiDriver.driver);
		act2.moveToElement(driver.findElement(By.xpath("//a[text()='Revenue']"))).click().build().perform();
		Thread.sleep(3000);
		act1.moveToElement(driver.findElement(By.xpath("//a[text()='Revenue']/following-sibling::span/span/button"))).click().build().perform();
		Thread.sleep(10000);
		JavascriptExecutor js = (JavascriptExecutor) uiDriver.driver;
		WebElement Element = uiDriver.driver.findElement(By.xpath("//td[text()='Total']/following-sibling::td[3]"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
		Thread.sleep(5000);
		String img1 = uiDriver.CaptureFullScreenShot("Date");
		reporter.addScreenshotToReport(img1, "ValidateRLI");
		Thread.sleep(10000);
	}
	
	
	public String getQLIdata() throws InterruptedException
	{
		rowcount = opp.getQuoteLineItemsCount();
		HashMap<String, String> sdat = opp.getNewQuoteLineItemDetails(rowcount);
		System.out.println("Name : " +sdat.get("NAME"));
		System.out.println("PLATFORM : " +sdat.get("PLATFORM"));
		reporter.logger.log(Status.INFO, "***************************PARENT QLI*****************");
		reporter.logger.log(Status.INFO, "Name : " +sdat.get("NAME"));
		reporter.logger.log(Status.INFO, "MARKET : " +sdat.get("MARKET"));
		reporter.logger.log(Status.INFO, "PLATFORM : " +sdat.get("PLATFORM"));
		reporter.logger.log(Status.INFO, "NET MEDIA/PRODUCTION : " +sdat.get("NET MEDIA/PRODUCTION"));
		reporter.logger.log(Status.INFO, "TOTAL NET : " +sdat.get("TOTAL NET"));
		reporter.logger.log(Status.INFO, "BUDGET : " +sdat.get("BUDGET"));
		reporter.logger.log(Status.INFO, "UNASSIGNED : " +sdat.get("UNASSIGNED"));
		reporter.logger.log(Status.INFO, "SALES STAGE : " +sdat.get("SALES STAGE"));
		reporter.logger.log(Status.INFO, "***************************END*****************");
		uiDriver.driver.findElement(By.xpath("//td[text()='"+sdat.get("NAME")+"']")).click();
		Thread.sleep(5000);
		rowcount = opp.getQuoteLineItemsCount();
		for(int j=2;j<=rowcount;j++)
		{
			HashMap<String, String> sdat1 = opp.getNewQuoteLineItemDetails(j);
			System.out.println("Name2 : " +sdat1.get("NAME"));
			System.out.println("MARKET : " +sdat1.get("MARKET"));
			System.out.println("PLATFORM : " +sdat1.get("PLATFORM"));
			reporter.logger.log(Status.INFO, "***************************CHILD QLI*****************");
			reporter.logger.log(Status.INFO, "Name : " +sdat1.get("NAME"));
			reporter.logger.log(Status.INFO, "MARKET : " +sdat1.get("MARKET"));
			reporter.logger.log(Status.INFO, "PLATFORM : " +sdat1.get("PLATFORM"));
			reporter.logger.log(Status.INFO, "NET MEDIA/PRODUCTION : " +sdat1.get("NET MEDIA/PRODUCTION"));
			reporter.logger.log(Status.INFO, "TOTAL NET : " +sdat1.get("TOTAL NET"));
			reporter.logger.log(Status.INFO, "***************************END*****************");
		}
		
		return sdat.get("NAME");
	}
	
	
	public String CreateAccount() throws Exception
	{
		//Prerequisite Test Data
		String accType = dataDriver.getCellData("AccountDetails", "AccountType", 2);
		String industry = dataDriver.getCellData("AccountDetails", "Industry", 2);
		String billingAddress = dataDriver.getCellData("AccountDetails", "BillingAddress", 2);
		String shippingAddress = dataDriver.getCellData("AccountDetails", "ShippingAddress", 2);
		String accountClass = dataDriver.getCellData("AccountDetails", "AccountClass", 2);
		System.out.println("accType "+accType);
		HashMap<String, String> cusDetails = utilFuns.generateCustomerDetails();
		cusDetails.put("Industry", industry);
		cusDetails.put("BillingAddress", billingAddress);
		cusDetails.put("ShippingAddress", shippingAddress);
		cusDetails.put("AccountClass", accountClass);
		
		acc.ClickonAccounts();
		acc.ClickonCreateNewAccount();
		acc.SelectAccountType(accType);
		acc.ClickNext();
		acc.fillAccountdetails2(cusDetails);
		String accName = acc.SaveAccountDetails();
		
		return accName;
	}
}
