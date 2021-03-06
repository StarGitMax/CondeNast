package com.CondeNast.SmokeSuite;

import java.util.HashMap;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Contacts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_GCPSmoke_TC_007_SA extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Contacts cont = new Contacts();
	Accounts acc = new Accounts();
	Opportunities opp = new Opportunities();
	String AccountName;
	String ContName;
	String sfUser_SA = "Ariana Stachrowski";
	HashMap<String, String> cusDetails;
	
	
	@Test
	public void  CreateContact() throws Exception
	{
		
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SA);
		
		AccountName = CreateAccount();
		cusDetails.put("accountName", AccountName);
		
		cont.ClickonContacts();
		cont.ClickonCreateNewContact();
		cont.fillContactdetails(cusDetails);
		cont.SaveContactDetails();
		
		cont.EditContactDetails();
		uiDriver.SetValueForTextBox("Department", "IT");
		cont.SaveContactDetails();
		
		cont.DeleteContactDetails();
		
		opp.LogoutofSalesForceUser(sfUser_SA);
		Thread.sleep(5000);
		
	}
	
	
	public String CreateAccount() throws Exception
	{
		//Prerequisite Test Data
		String accType = dataDriver.getCellData("AccountDetails", "AccountType", 2);
		String industry = dataDriver.getCellData("AccountDetails", "Industry", 2);
		String billingAddress = dataDriver.getCellData("AccountDetails", "BillingAddress", 2);
		String shippingAddress = dataDriver.getCellData("AccountDetails", "ShippingAddress", 2);
		String accountClass = dataDriver.getCellData("AccountDetails", "AccountClass", 2);
		System.out.println("accType "+accType);
		cusDetails = utilFuns.generateCustomerDetails();
		cusDetails.put("Industry", industry);
		cusDetails.put("BillingAddress", billingAddress);
		cusDetails.put("ShippingAddress", shippingAddress);
		cusDetails.put("AccountClass", accountClass);
		
		acc.ClickonAccounts();
		acc.ClickonCreateNewAccount();
		acc.SelectAccountType(accType);
		acc.ClickNext();
		acc.fillAccountdetails2(cusDetails);
		String accName = acc.SaveAccountDetails();
		
		return accName;
	}
}
