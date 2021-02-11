package com.CondeNast.TestScenarios.SalesAgent;

import java.util.HashMap;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_665_Sprint7_TC_2552 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Accounts acc = new Accounts();
	Opportunities opp = new Opportunities();
	String AccountName;
	String sfUser = "Ariana Stachrowski";
	
	@Test
	public void CreateAccountandOpportunity() throws Exception
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
		AccountName = cusDetails.get("AccountName");
		
		//Navigate and Login to SalesForce
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser);
		
		// Create Account
		acc.ClickonAccounts();
		acc.ClickonCreateNewAccount();
		acc.ClickNext();
		acc.fillAccountdetails2(cusDetails);
		acc.SaveAccountDetails();
		
		acc.VerifyEditButtontoeditAccount();
		
		acc.ClickonAccounts();
		acc.VerifyDeleteOptionforAccount(AccountName);
		
		 opp.LogoutofSalesForceUser(sfUser);
	}
}
