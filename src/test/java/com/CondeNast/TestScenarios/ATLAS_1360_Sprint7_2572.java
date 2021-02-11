package com.CondeNast.TestScenarios;

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
	String AccountName="John Deo";
	String ContName;
	String sfUser_RA = "Eleanor Matthews";
	String sfUser_SA = "Ariana Stachrowski";
	String sfUser_SL = "Antonia Wigan";
	String sfUser_FU = "Andrew Thornton";
	
	//@Test
	public void  CreateBillingContact() throws Exception
	{
		
		HashMap<String, String> cusDetails = utilFuns.generateCustomerDetails();
		cusDetails.put("accountName", AccountName);
		
		sl.NavigateandLogin();
		cont.ClickonContacts();
		cont.ClickonCreateNewContact();
		cont.validatefields();
		cont.CancelContactCreation();
		sl.Logout();
		
	}
	
	@Test
	public void  CreateBillingContact2() throws Exception
	{
		
		HashMap<String, String> cusDetails = utilFuns.generateCustomerDetails();
		cusDetails.put("accountName", AccountName);
		
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_FU);
		cont.ClickonContacts();
		cont.ClickonCreateNewContact();
		cont.validatefields();
		cont.CancelContactCreation();
		opp.LogoutofSalesForceUser(sfUser_FU);
		Thread.sleep(5000);
		
	}
	 
}
