package com.CondeNast.SmokeSuite;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Contacts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_GCPSmoke_TC_009_MP extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	Contacts cont = new Contacts();
	String sfUser_MP = "Shuo Yu";
	
	@Test
	public void  CreateContact() throws Exception
	{	
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_MP);
		
		cont.ClickonContacts();
		cont.VerifyNewButtontoCreateContact();
		
		cont.SearchandClickContact(ContName_TC2571);
		cont.VerifyEditButtontoeditContact();
		
		cont.ClickonContacts();
		cont.VerifyDeleteOptionforContact(ContName_TC2571);
		
		 opp.LogoutofSalesForceUser(sfUser_MP);
		
	}
} 
