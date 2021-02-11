package com.CondeNast.TestScenarios;

import java.util.HashMap;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Contacts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_1360_Sprint7_2571 extends BaseTest
{

	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	Contacts cont = new Contacts();
	String AccountName="John Deo";
	String ContName = "Jacob Johnson";
	String sfUser_MP = "Shuo Yu";
	
	@Test
	public void  CreateContact() throws Exception
	{
		
		HashMap<String, String> cusDetails = utilFuns.generateCustomerDetails();
		cusDetails.put("accountName", AccountName);
		
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_MP);
		
		cont.ClickonContacts();
		cont.VerifyNewButtontoCreateContact();
		
		cont.SearchandClickContact(ContName);
		cont.VerifyEditButtontoeditContact();
		
		cont.ClickonContacts();
		cont.VerifyDeleteOptionforContact(ContName);
		
		 opp.LogoutofSalesForceUser(sfUser_MP);
		
	}
}
