package com.CondeNast.TestScenarios.SalesAgent;


import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.MarketAccounts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_686_Sprint5_TC_1752 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	MarketAccounts ma = new MarketAccounts();
	Opportunities opp = new Opportunities();
	String mAccount;
	String sfUser_SA = "Ariana Stachrowski";
	

	@Test
	public void ValidateMarketAccounts() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SA);
	    ma.ClickonMarketAccounts();
	    ma.VerifyNewButtontoCreateMarketAccount();
	    
	    ma.SearchandClickMarketAccount(AccountName_TC1752);
	    ma.EditMarketAccount();
	    ma.VerifyMarketAccountStatusField();
		ma.CancelEditChanges();
	    opp.LogoutofSalesForceUser(sfUser_SA);
	}
}
