package com.CondeNast.TestScenarios;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;
import com.aventstack.extentreports.Status;

public class ATLAS_762_Sprint3_TC_128_129_133_134 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	Accounts acc = new Accounts();
	String AccountName;
	String OppName;
	String snames;
	String sname;
	int rowcount;
	String sfUser_SA = "Ariana Stachrowski";
	
	@Test
	public void CreateQuoteLineItems() throws Exception
	{
		//Navigate and Login to SalesForce
		sl.NavigateandLogin();
        opp.LoginAsDifferentUsers(sfUser_SA);
		
		// Create Account
		AccountName = CreateAccount();
		
		// Creating Opportunity , Quote and QuoteLine
		opp.ClickonOpportunities();
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"18000","30/04/2021","IdentifyingOpportunity","CentrallyBilled");
		opp.SaveOpportunitiesDetails();
		opp.CreateQuotes();
		opp.CreateQuoteLineItem("2000", "16/01/2021","28/02/2021","Digital");
		opp.CreateQuoteLineItem("3000", "16/01/2021","28/02/2021","Digital");
		opp.getQuoteLinedetails();
		int rowcount = opp.getQuoteLineItemsCount();
		if(rowcount>0)
		{
			snames = opp.getQuoteLineItemName();
		}
		for(int i=1;i<=rowcount;i++)
		{
			HashMap<String, String> sdat1 = opp.getNewQuoteLineItemDetails(i);
			System.out.println("Name2 : " +sdat1.get("NAME"));
			System.out.println("MARKET : " +sdat1.get("MARKET"));
			System.out.println("PLATFORM : " +sdat1.get("PLATFORM"));
			reporter.logger.log(Status.INFO, "***************************QLI*****************");
			reporter.logger.log(Status.INFO, "Name : " +sdat1.get("NAME"));
			reporter.logger.log(Status.INFO, "MARKET : " +sdat1.get("MARKET"));
			reporter.logger.log(Status.INFO, "PLATFORM : " +sdat1.get("PLATFORM"));
			reporter.logger.log(Status.INFO, "NET MEDIA/PRODUCTION : " +sdat1.get("NET MEDIA/PRODUCTION"));
			reporter.logger.log(Status.INFO, "TOTAL NET : " +sdat1.get("TOTAL NET"));
			reporter.logger.log(Status.INFO, "Budget : " +sdat1.get("BUDGET"));
			reporter.logger.log(Status.INFO, "Unassigned : " +sdat1.get("UNASSIGNED"));
			reporter.logger.log(Status.INFO, "***************************END*****************");
		}
		String[] sname = snames.split(";");
		opp.DeleteMasterQLI(sname[1]);
		rowcount = opp.getQuoteLineItemsCount();
		for(int i=1;i<=rowcount;i++)
		{
			HashMap<String, String> sdat1 = opp.getNewQuoteLineItemDetails(i);
			System.out.println("Name2 : " +sdat1.get("NAME"));
			System.out.println("MARKET : " +sdat1.get("MARKET"));
			System.out.println("PLATFORM : " +sdat1.get("PLATFORM"));
			reporter.logger.log(Status.INFO, "***************************CHILD QLI*****************");
			reporter.logger.log(Status.INFO, "Name : " +sdat1.get("NAME"));
			reporter.logger.log(Status.INFO, "MARKET : " +sdat1.get("MARKET"));
			reporter.logger.log(Status.INFO, "PLATFORM : " +sdat1.get("PLATFORM"));
			reporter.logger.log(Status.INFO, "NET MEDIA/PRODUCTIONE : " +sdat1.get("NET MEDIA/PRODUCTION"));
			reporter.logger.log(Status.INFO, "TOTAL NET : " +sdat1.get("TOTAL NET"));
			reporter.logger.log(Status.INFO, "Budget : " +sdat1.get("BUDGET"));
			reporter.logger.log(Status.INFO, "Unassigned : " +sdat1.get("UNASSIGNED"));
			reporter.logger.log(Status.INFO, "***************************END*****************");
		}
		opp.getQuoteLinedetails();
		
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
