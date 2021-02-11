package com.CondeNast.TestScenarios.RevenueAnalyst;

import java.util.HashMap;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Contacts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_1360_Sprint7_2572 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Contacts cont = new Contacts();
	Opportunities opp = new Opportunities();
	String ContName;
	String sfUser_RA = "Eleanor Matthews";
	
	@Test
	public void  CreateBillingContact() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_RA);
		cont.ClickonContacts();
		cont.ClickonCreateNewContact();
		cont.validatefields();
		cont.CancelContactCreation();
		opp.LogoutofSalesForceUser(sfUser_RA);
		Thread.sleep(5000);
		
	}
	 
}
