package com.CondeNast.TestScenarios.SalesAgent;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_1911_Sprint7_TC_2564 extends BaseTest
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
	public void VeirfyOpportunityisnotablesavewithoutClietBrand() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SA);
		AccountName = CreateAccount();
		opp.ClickonOpportunities();	
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"18000","28/02/2021","IdentifyingOpportunity","CentrallyBilled");
		OppName = opp.SaveOpportunitiesDetails();
		
		//Verbal Commitment (75%)
		opp.EditOpprtunity();
		Thread.sleep(5000);
		opp.SelectOpportunityStage2("Verbal Commitment");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(5000);
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		opp.CancelOpportunitiesCreation();
		
		
		// Contracting (90%)
		opp.EditOpprtunity();
		Thread.sleep(5000);
		opp.SelectOpportunityStage2("Contracting");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(5000);
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		opp.CancelOpportunitiesCreation();
				
		// Closed Won (100%)
		opp.EditOpprtunity();
		Thread.sleep(5000);
		opp.SelectOpportunityStage2("Closed Won");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(5000);
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		opp.CancelOpportunitiesCreation();
		
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
