package com.CondeNast.TestScenarios.SalesAgent;

import java.util.HashMap;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.MarketAccounts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;
import com.aventstack.extentreports.Status;

public class ATLAS_1908_Sprint7_TC_2616_2622_2624 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	MarketAccounts ma = new MarketAccounts();
	Opportunities opp = new Opportunities();
	Accounts acc = new Accounts();
	String AccountName;
	String mAccount;
	
	@Test
	public void ValidateMarketAccounts() throws Exception
	{
		sl.NavigateandLogin();
		
		AccountName = CreateAccount();
		
	     ma.ClickonMarketAccounts();
	     ma.CreateNewMarketAccount();
	     ma.validatefields();
	     ma.fillaccountDetails(AccountName, "GCP HQ", "USD", "Monitored");
	     mAccount = ma.SaveMarketAcountDetails();
	     
	     ma.ClickonDetails();
	     String img1 = uiDriver.CaptureFullScreenShot("Market");
		 reporter.addScreenshotToReport(img1, "Market Account Details");
		 Thread.sleep(5000);
		 
		 if(mAccount.contains(":"+AccountName))
		 {
			 reporter.logger.log(Status.PASS, "Market Account Name Created in 'Market Name:Account Name' format");
		 }
		 else
		 {
			 reporter.logger.log(Status.FAIL, "Market Account Name not Created in 'Market Name:Account Name' format");
		 }
	     
	    sl.Logout();
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
		cusDetails.put("AccountCategory", "Beauty_PersonalCare");
		cusDetails.put("SubCategory", "Luxury_Cosmetics");
		
		acc.ClickonAccounts();
		acc.ClickonCreateNewAccount();
		acc.SelectAccountType(accType);
		acc.ClickNext();
		acc.fillAccountdetails4(cusDetails);
		String accName = acc.SaveAccountDetails();
		
		return accName;
	}
}
