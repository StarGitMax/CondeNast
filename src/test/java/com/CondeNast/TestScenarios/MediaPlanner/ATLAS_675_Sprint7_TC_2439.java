package com.CondeNast.TestScenarios.MediaPlanner;

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
	String mAccount;
	String sfUser = "Shuo Yu";
	

	@Test
	public void ValidateMarketAccounts() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser);
	    ma.ClickonMarketAccounts();
	    ma.VerifyNewButtontoCreateMarketAccount();
	    
	    ma.SearchandClickMarketAccount(mAccount_TC2439);
	    ma.VerifyEditButtontoeditMarketAccount();
	     
	    ma.ClickonMarketAccounts();
	    ma.VerifyDeleteOptionforMarketAccount(mAccount_TC2439);
	    
	    opp.LogoutofSalesForceUser(sfUser);
	}
}
