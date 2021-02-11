package com.CondeNast.SmokeSuite;

import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.MarketAccounts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_GCPSmoke_TC_004_RA extends BaseTest
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
	    ma.VerifyNewButtontoCreateMarketAccount();
	    
	    ma.SearchandClickMarketAccount(AccountName_TC2436_2437);
	    ma.EditMarketAccount();
	    uiDriver.scrolltoViewElement("MarketAccountNotes");
	    uiDriver.ClearTextBox("MarketAccountNotes");
	    uiDriver.SetValueForTextBox("MarketAccountNotes", "BillingDetailsTest");
	    ma.SaveMarketAcountDetails();
	     
	    ma.ClickonMarketAccounts();
	    ma.VerifyDeleteOptionforMarketAccount(AccountName_TC2436_2437);
	    
	    opp.LogoutofSalesForceUser(sfUser_RA);
	}
}
