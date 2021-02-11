package com.CondeNast.TestScenarios;

import java.util.HashMap;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_798_Sprint3_TC_148 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	Accounts acc = new Accounts();
	String AccountName;
	String OppName;
	String sname;
	String snames;
	int rowcount;
	String sfUser_SA = "Ariana Stachrowski";

	
	@Test
	public void RLIAmountValidation() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SA);
		
		// Create Account
		AccountName = CreateAccount();
		
		opp.ClickonOpportunities();	
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"18000","30/04/2021","IdentifyingOpportunity","CentrallyBilled");
		opp.SaveOpportunitiesDetails();
		opp.CreateQuotes();
		int rowcount = opp.getQuoteLineItemsCount();
		if(rowcount>0)
		{
			snames = opp.getQuoteLineItemName();
		}
		opp.CreateNewQuoteLineItem();
		opp.FillQuoteLineItemFields("5000","16/01/2021","30/06/2021","Digital");
		opp.SelectAvailableMultipleMarkets("GCP HQ;Australia", "2000;2000");
		opp.RefreshRevenueSection();
		opp.fillRevenueLiesDetails("6000", "10");
		opp.SaveQuoteLineItemToCheckMandatoryFields();
		opp.fillRevenueLiesDetails("5000", "10");
		opp.SaveQuoteLineItem();
		
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
		acc.SaveAccountDetails();
		String accName = cusDetails.get("AccountName");
		
		return accName;
	}
}
