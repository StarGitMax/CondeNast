package com.CondeNast.TestScenarios.SalesAgent;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.CondeNast.Core.WebControls.LIST;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;
import com.aventstack.extentreports.Status;

public class ATLAS_795_Sprint4_1401 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	Accounts acc = new Accounts();
	String oppdet;
	String AccountName;
	String snames;
	String OppName;
	String sfUser_SA = "Ariana Stachrowski";
	
	
	@Test
	public void  ValidateQLIs() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SA);
		AccountName = CreateAccount();
		opp.ClickonOpportunities();	
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"18000","27/02/2021","IdentifyingOpportunity","CentrallyBilled");
		OppName = opp.SaveOpportunitiesDetails();
		opp.CreateQuotes();
		int rowcount = opp.getQuoteLineItemsCount();
		if(rowcount>0)
		{
			snames = opp.getQuoteLineItemName();
		}
		
		
		// SubQuoteLine with Verbal Commitment (75%)
		opp.CreateNewQuoteLineItem();
		uiDriver.WaitforElementPresent("Budget");
		Actions act = new Actions(uiDriver.driver);
		act.doubleClick(uiDriver.driver.findElement(By.xpath("//label[text()='Budget']/following-sibling::div/div/input"))).sendKeys(Keys.DELETE).build().perform();;
		Thread.sleep(1000);
		uiDriver.SetValueForTextBox("Budget", "6000");
	    uiDriver.SetValueForTextBox("StartDate","16/01/2021");
		uiDriver.SetValueForTextBox("EndDate", "30/06/2021");
		uiDriver.SetValueForList("QLStage", LIST.ByVisibleText, "Verbal Commitment (75%)");
		Thread.sleep(10000);
		uiDriver.SetValueForList("Platform", LIST.ByVisibleText, "Digital");
		Thread.sleep(10000);
		uiDriver.SetValueForList("Brand", LIST.ByVisibleText, "Teen Vogue");
		Thread.sleep(10000);
		opp.SelectAvailableMultipleMarkets("GCP HQ;Australia", "3000;3000");
		opp.RefreshRevenueSection();
		opp.fillRevenueLiesDetails("6000", "10");
		opp.SaveQuoteLineItem();
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
		for(int i=2;i<=rowcount;i++)
		{
			HashMap<String, String> sdat1 = opp.getNewQuoteLineItemDetails(i);
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
		opp.EditQuoteLine(sdat.get("NAME"));
		String img = uiDriver.CaptureFullScreenShot("QLI");
		reporter.addScreenshotToReport(img, "SubQLI Non Editable");
		opp.CloseQuoteLineItem();
		
		
		
		// SubQuoteLine with Contracting (90%)
			opp.CreateNewQuoteLineItem();
			uiDriver.WaitforElementPresent("Budget");
			act = new Actions(uiDriver.driver);
			act.doubleClick(uiDriver.driver.findElement(By.xpath("//label[text()='Budget']/following-sibling::div/div/input"))).sendKeys(Keys.DELETE).build().perform();;
			Thread.sleep(1000);
			uiDriver.SetValueForTextBox("Budget", "6000");
		    uiDriver.SetValueForTextBox("StartDate","16/11/2020");
			uiDriver.SetValueForTextBox("EndDate", "31/01/2021");
			uiDriver.SetValueForList("QLStage", LIST.ByVisibleText, "Contracting (90%)");
			Thread.sleep(10000);
			uiDriver.SetValueForList("Platform", LIST.ByVisibleText, "Digital");
			Thread.sleep(10000);
			uiDriver.SetValueForList("Brand", LIST.ByVisibleText, "Teen Vogue");
			Thread.sleep(10000);
			opp.SelectAvailableMultipleMarkets("GCP HQ;Australia", "3000;3000");
			opp.RefreshRevenueSection();
			opp.fillRevenueLiesDetails("6000", "10");
			opp.SaveQuoteLineItem();
			rowcount = opp.getQuoteLineItemsCount();
			sdat = opp.getNewQuoteLineItemDetails(rowcount);
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
			for(int i=2;i<=rowcount;i++)
			{
				HashMap<String, String> sdat1 = opp.getNewQuoteLineItemDetails(i);
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
			opp.EditQuoteLine(sdat.get("NAME"));
			img = uiDriver.CaptureFullScreenShot("QLI");
			reporter.addScreenshotToReport(img, "SubQLI Non Editable");
			opp.CloseQuoteLineItem();
			
			
			// SubQuoteLine with Closed Won (100%)
				opp.CreateNewQuoteLineItem();
				uiDriver.WaitforElementPresent("Budget");
				act = new Actions(uiDriver.driver);
				act.doubleClick(uiDriver.driver.findElement(By.xpath("//label[text()='Budget']/following-sibling::div/div/input"))).sendKeys(Keys.DELETE).build().perform();;
				Thread.sleep(1000);
				uiDriver.SetValueForTextBox("Budget", "6000");
			    uiDriver.SetValueForTextBox("StartDate","16/11/2020");
				uiDriver.SetValueForTextBox("EndDate", "31/01/2021");
				uiDriver.SetValueForList("QLStage", LIST.ByVisibleText, "Closed Won (100%)");
				Thread.sleep(10000);
				uiDriver.SetValueForList("Platform", LIST.ByVisibleText, "Digital");
				Thread.sleep(10000);
				uiDriver.SetValueForList("Brand", LIST.ByVisibleText, "Teen Vogue");
				Thread.sleep(10000);
				opp.SelectAvailableMultipleMarkets("GCP HQ;Australia", "3000;3000");
				opp.RefreshRevenueSection();
				opp.fillRevenueLiesDetails("6000", "10");
				opp.SaveQuoteLineItem();
				rowcount = opp.getQuoteLineItemsCount();
				sdat = opp.getNewQuoteLineItemDetails(rowcount);
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
				for(int i=2;i<=rowcount;i++)
				{
					HashMap<String, String> sdat1 = opp.getNewQuoteLineItemDetails(i);
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
				opp.EditQuoteLine(sdat.get("NAME"));
				img = uiDriver.CaptureFullScreenShot("QLI");
				reporter.addScreenshotToReport(img, "SubQLI Non Editable");
				opp.CloseQuoteLineItem();
	
		
				opp.LogoutofSalesForceUser(sfUser_SA);
				Thread.sleep(5000);
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
