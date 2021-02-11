package com.CondeNast.SmokeSuite;

import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_GCPSmoke_TC_027_CC extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	Accounts acc = new Accounts();
	String OppName = "AUT-GCP HQ: Aracely Wehner-2021Q1-CAMP_ihhD-000001645";
	String sfUser_CC = "Beth Langford";
	
	@Test
	public void VerifyOpportunityfunctionality() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_CC);
		
		opp.ClickonOpportunities();
		
		opp.SearhandClickonOpportunity(OppName);
		
	    opp.VerifyEditButtontoeditOpportunity();
	    
	    opp.ClickonOpportunities();
	    
	    opp.VerifyDeleteOptionforOpportunity(OppName);
		
		opp.LogoutofSalesForceUser(sfUser_CC);
		Thread.sleep(5000);
	}
	
}
