package com.CondeNast.TestScenarios;

import java.util.HashMap;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class DemoforAccountandOpportunity  extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Accounts acc = new Accounts();
	Opportunities opp = new Opportunities();
	String AccountName;
	
	@Test
	public void CreateAccountandOpportunity() throws Exception
	{
		//Prerequisite Test Data
		String accType = dataDriver.getCellData("AccountDetails", "AccountType", 2);
		String industry = dataDriver.getCellData("AccountDetails", "Industry", 2);
		String billingAddress = dataDriver.getCellData("AccountDetails", "BillingAddress", 2);
		String shippingAddress = dataDriver.getCellData("AccountDetails", "ShippingAddress", 2);
		System.out.println("accType "+accType);
		HashMap<String, String> cusDetails = utilFuns.generateCustomerDetails();
		cusDetails.put("Industry", industry);
		cusDetails.put("BillingAddress", billingAddress);
		cusDetails.put("ShippingAddress", shippingAddress);
		AccountName = cusDetails.get("AccountName");
		
		//Navigate and Login to SalesForce
		sl.NavigateandLogin();
		
		
		// Create Account
		acc.ClickonAccounts();
		acc.ClickonCreateNewAccount();
		acc.SelectAccountType(accType);
		acc.ClickNext();
		acc.fillAccountdetails(cusDetails);
		acc.SaveAccountDetails();
		
		// Creating Opportunity , Quote and QuoteLine
		opp.ClickonOpportunities();
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"5000","18/12/2020");
		opp.SaveOpportunitiesDetails();
		opp.CreateQuotes();
		opp.CreateQuoteLineItem();
		sl.Logout();
	}
}
