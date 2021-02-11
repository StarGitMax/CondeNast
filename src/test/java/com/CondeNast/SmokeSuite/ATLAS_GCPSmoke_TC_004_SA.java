package com.CondeNast.SmokeSuite;

import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.MarketAccounts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_GCPSmoke_TC_004_SA extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	MarketAccounts ma = new MarketAccounts();
	Opportunities opp = new Opportunities();
	String sfUser = "Ariana Stachrowski";
	
	@Test
	public void ValidateMarketAccounts() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser);
	    ma.ClickonMarketAccounts();
	    ma.VerifyNewButtontoCreateMarketAccount();
	    
	    ma.SearchandClickMarketAccount(AccountName_TC2432_2433);
	    ma.EditMarketAccount();
	    uiDriver.GetTextandStoreinVariable("CreditStatusValue");
	    uiDriver.scrolltoViewElement("MarketAccountNotes");
	    uiDriver.SetValueForTextBox("MarketAccountNotes", "BillingDetailsTest");
	    ma.SaveMarketAcountDetails();
	     
	    ma.ClickonMarketAccounts();
	    ma.VerifyDeleteOptionforMarketAccount(AccountName_TC2432_2433);
	    
	    opp.LogoutofSalesForceUser(sfUser);
	}
}
