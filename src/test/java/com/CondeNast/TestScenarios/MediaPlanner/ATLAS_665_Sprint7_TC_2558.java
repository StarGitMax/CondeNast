package com.CondeNast.TestScenarios.MediaPlanner;

import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_665_Sprint7_TC_2558 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Accounts acc = new Accounts();
	Opportunities opp = new Opportunities();
	String sfUser = "Shuo Yu";
	
	
	@Test
	public void CreateAccountandOpportunity() throws Exception
	{
		
		//Navigate and Login to SalesForce
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser);
		
		// Create Account
		acc.ClickonAccounts();
		acc.VerifyNewButtontoCreateAccount();
		acc.SearchandClickAccount(Account_TC2558);
		acc.VerifyEditButtontoeditAccount();
		
		acc.ClickonAccounts();
		acc.VerifyDeleteOptionforAccount(Account_TC2558);
		
		opp.LogoutofSalesForceUser(sfUser);
		Thread.sleep(2500);
	}
}
