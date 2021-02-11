package com.CondeNast.TestScenarios;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_973_Sprint3_TC_135_136_137_138_139 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	Accounts acc = new Accounts();
	String AccountName;
	String OppName;
	String sname;
	int rowcount;
	String sfUser_SA = "Ariana Stachrowski";
	
	@Test
	public void  ValidateBillingTypeinOpportunity() throws Exception
	{
		sl.NavigateandLogin();
	    opp.LoginAsDifferentUsers(sfUser_SA);
		
		// Create Account
		AccountName = CreateAccount();
		
		opp.ClickonOpportunities();	
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"6000","31/01/2021","IdentifyingOpportunity","CentrallyBilled");
		OppName = opp.SaveOpportunitiesDetails();
		opp.EditOpprtunity();
		String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
		reporter.addScreenshotToReport(img1, "Opportunity Details");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(3000);
		opp.ClickonOpportunities();
		opp.ClickonOpportunitySubMenu(OppName);
		opp.ChangeBillingtype("LocallyBilled");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(20000);
		uiDriver.driver.findElement(By.xpath("//a[@title='"+OppName+"']")).click();
		Thread.sleep(20000);
		opp.EditOpprtunity();
		img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
		reporter.addScreenshotToReport(img1, "Opportunity Details");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(3000);
		
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
		acc.SaveAccountDetails();
		String accName = cusDetails.get("AccountName");
		
		return accName;
	}
}
