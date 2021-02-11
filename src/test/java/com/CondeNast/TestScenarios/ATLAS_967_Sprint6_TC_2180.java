package com.CondeNast.TestScenarios;

import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.MarketAccounts;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_967_Sprint6_TC_2180 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	MarketAccounts ma = new MarketAccounts();
	String marketaccount="GCP HQ: John Deo";
	
	@Test
	 public void ValidateCreditStatusinMarketAccounts() throws Exception
	 {
		sl.NavigateandLogin();
	     ma.ClickonMarketAccounts();
	     ma.SearchforMarketAccounts(marketaccount);
	     ma.ClickonExistingMarketAccounts(marketaccount);
	     ma.EditMarketAccount();
	     //ma.ChangeCreditStatus("GoodCredit");
	    // ma.SaveMarketAcountDetailswithValidations();
	     ma.fillCategorySubCategoryDetails();
	     ma.SaveMarketAcountDetails();
	     ma.ClickonDetails();
	     uiDriver.GetTextandStoreinVariable("CreditStatusValue");
	     sl.Logout();
	 }
}
