package com.CondeNast.TestScenarios.SalesAgent;

import java.util.HashMap;

import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.MarketAccounts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_1908_Sprint7_TC_2621 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	MarketAccounts ma = new MarketAccounts();
	Opportunities opp = new Opportunities();
	Accounts acc = new Accounts();
	String AccountName;
	String mAccount;
	
	@Test
	public void ValidateMarketAccounts() throws Exception
	{
		sl.NavigateandLogin();
	    ma.ClickonMarketAccounts();
	    ma.CreateNewMarketAccount();
	    ma.validateBlockOrdersfield();
	    ma.CancelEditChanges();
        sl.Logout();
	}
	
}
