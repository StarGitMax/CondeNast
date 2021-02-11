package com.CondeNast.TestScenarios;

import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.MarketAccounts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_675_Sprint7_TC_2439 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	MarketAccounts ma = new MarketAccounts();
	Opportunities opp = new Opportunities();
	String AccountName="GCP HQ: John Deo";
	String mAccount;
	String sfUser = "Shuo Yu";
	

	@Test
	public void ValidateMarketAccounts() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser);
	    ma.ClickonMarketAccounts();
	    ma.VerifyNewButtontoCreateMarketAccount();
	    
	    ma.SearchandClickMarketAccount(AccountName);
	    ma.VerifyEditButtontoeditMarketAccount();
	     
	    ma.ClickonMarketAccounts();
	    ma.VerifyDeleteOptionforMarketAccount(AccountName);
	    
	    opp.LogoutofSalesForceUser(sfUser);
	}
}
