package com.CondeNast.TestScenarios;

import java.util.HashMap;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_665_Sprint7_TC_2557 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Accounts acc = new Accounts();
	Opportunities opp = new Opportunities();
	String AccountName = "MarketAccountSalesagent";
	String sfUser = "Andrew Thornton";
	
	
	@Test
	public void CreateAccountandOpportunity() throws Exception
	{
		
		//Navigate and Login to SalesForce
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser);
		
		// Create Account
		acc.ClickonAccounts();
		acc.VerifyNewButtontoCreateAccount();
		acc.SearchandClickAccount(AccountName);
	
		acc.ClickonEditAccount();
		uiDriver.SetValueForTextBox("AccountNotes", "Test");
		acc.SaveAccountDetails();
		
		acc.ClickonAccounts();
		acc.VerifyDeleteOptionforAccount(AccountName);
		
		sl.Logout();
	}
}
