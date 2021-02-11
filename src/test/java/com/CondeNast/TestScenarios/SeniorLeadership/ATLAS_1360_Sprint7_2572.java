package com.CondeNast.TestScenarios.SeniorLeadership;

import java.util.HashMap;
import org.testng.annotations.Test;

import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Contacts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_1360_Sprint7_2572 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Contacts cont = new Contacts();
	Accounts acc = new Accounts();
	Opportunities opp = new Opportunities();
	String ContName;;
	String sfUser_SL = "Antonia Wigan";
	
	
	@Test
	public void  CreateBillingContact() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SL);
		cont.ClickonContacts();
		cont.ClickonCreateNewContact();
		cont.validatefields();
		cont.CancelContactCreation();
		opp.LogoutofSalesForceUser(sfUser_SL);
		Thread.sleep(5000);
		
	}
	
}
