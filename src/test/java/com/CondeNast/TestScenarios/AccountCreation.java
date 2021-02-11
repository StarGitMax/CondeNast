package com.CondeNast.TestScenarios;

import java.util.HashMap;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class AccountCreation extends BaseTest
{

	SalesforceLogin sl = new SalesforceLogin();
	Accounts acc = new Accounts();
	
	@Test
	public void CreateAccount() throws Exception
	{
		String accType = dataDriver.getCellData("AccountDetails", "AccountType", 2);
		String industry = dataDriver.getCellData("AccountDetails", "Industry", 2);
		String billingAddress = dataDriver.getCellData("AccountDetails", "BillingAddress", 2);
		String shippingAddress = dataDriver.getCellData("AccountDetails", "ShippingAddress", 2);
		
		System.out.println("accType "+accType);
		
		HashMap<String, String> cusDetails = utilFuns.generateCustomerDetails();
		cusDetails.put("Industry", industry);
		cusDetails.put("BillingAddress", billingAddress);
		cusDetails.put("ShippingAddress", shippingAddress);
		sl.NavigateandLogin();
		acc.ClickonAccounts();
		acc.ClickonCreateNewAccount();
		acc.SelectAccountType(accType);
		acc.ClickNext();
		acc.fillAccountdetails(cusDetails);
		acc.SaveAccountDetails();
		sl.Logout();
	}
	
}
