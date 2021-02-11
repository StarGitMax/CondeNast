package com.CondeNast.TestScenarios.RevenueAnalyst;

import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.MarketAccounts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_686_Sprint5_TC_1751_1753 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	MarketAccounts ma = new MarketAccounts();
	Opportunities opp = new Opportunities();
	String mAccount;
	String sfUser_RA = "Eleanor Matthews";
	
	
	@Test
	public void ValidateMarketAccounts() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_RA);
	    ma.ClickonMarketAccounts();
	    
	    ma.SearchandClickMarketAccount(AccountName_TC1751_1753);
	    ma.EditMarketAccount();
	    ma.ChangeMarketAccountStatus("Approved");
	    ma.SaveMarketAcountDetails();
	     
	    opp.LogoutofSalesForceUser(sfUser_RA);
	    Thread.sleep(5000);
	}
}
