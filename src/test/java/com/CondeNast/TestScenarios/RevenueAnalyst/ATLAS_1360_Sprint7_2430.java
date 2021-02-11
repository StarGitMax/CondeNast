package com.CondeNast.TestScenarios.RevenueAnalyst;

import java.util.HashMap;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Contacts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_1360_Sprint7_2430 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Contacts cont = new Contacts();
	Opportunities opp = new Opportunities();
	String sfUser = "Eleanor Matthews";
	
	@Test
	public void  CreateContact() throws Exception
	{
		
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser);
		cont.ClickonContacts();
		
		cont.SearchandClickContact(ContName_TC2430);
		cont.EditContactDetails();
		
		uiDriver.ClearTextBox("Email");
		uiDriver.SetValueForTextBox("Email","agripina.gutmann@xyz.com");
		Thread.sleep(500);
	
		cont.SaveContactDetails();
		opp.LogoutofSalesForceUser(sfUser);
		
	}
}
