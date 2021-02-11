package com.CondeNast.SmokeSuite;

import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_GCPSmoke_TC_027_FU extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	Accounts acc = new Accounts();
	String OppName = "AUT-GCP HQ: 10a_TestAccount_08022021_01-2021Q1-CIn-000002564";
	String sfUser_FU = "Andrew Thornton";
	
	@Test
	public void VerifyOpportunityfunctionality() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_FU);
		
		opp.ClickonOpportunities();
		
		opp.SearhandClickonOpportunity(OppName);
		
	    opp.VerifyEditButtontoeditOpportunity();
	    
	    opp.ClickonOpportunities();
	    
	    opp.VerifyDeleteOptionforOpportunity(OppName);
		
		opp.LogoutofSalesForceUser(sfUser_FU);
		Thread.sleep(5000);
	}
	
	
}
