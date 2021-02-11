package com.CondeNast.TestScenarios;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.MarketAccounts;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_675_Sprint7_TC_2440 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	MarketAccounts ma = new MarketAccounts();
	Accounts acc = new Accounts();
	String AccountName;
	String mAccount;
	
	@Test
	public void ValidateMarketAccounts() throws Exception
	{
		 sl.NavigateandLogin();
		 AccountName = CreateAccount();
	     ma.ClickonMarketAccounts();
	     ma.CreateNewMarketAccount();
	     ma.fillaccountDetails(AccountName, "GCP HQ", "USD", "Monitored");
	     mAccount = ma.SaveMarketAcountDetails();
	     ma.EditMarketAccount();
	     ma.ChangeCreditStatus("GoodCredit");
	     ma.SaveMarketAcountDetails();
	     
	     ma.ClickonMarketAccounts();
	     ma.DeleteMarketAccount(mAccount);
	     sl.Logout();
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
		HashMap<String, String> cusDetails = utilFuns.generateCustomerDetails();
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
