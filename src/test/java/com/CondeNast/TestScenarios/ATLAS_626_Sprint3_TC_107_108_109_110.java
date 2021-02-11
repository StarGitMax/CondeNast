package com.CondeNast.TestScenarios;

import java.util.HashMap;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;
import com.aventstack.extentreports.Status;

public class ATLAS_626_Sprint3_TC_107_108_109_110 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	Accounts acc = new Accounts();
	String AccountName;
	String amount = "5000";
	String sfUser_SA = "Ariana Stachrowski";
	
	@Test
	public void CreateQuoteagainstOpportunity() throws Exception
	{
		sl.NavigateandLogin();
        opp.LoginAsDifferentUsers(sfUser_SA);
		
		// Create Account
		AccountName = CreateAccount();
		
		opp.ClickonOpportunities();
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,amount,"18/11/2020");
		opp.SaveOpportunitiesDetails();
		opp.CreateQuotes();
		
		//Validating Amount, Budget and UnAllocated Budget.
		String QuotesAmount = uiDriver.GetTextandStoreinVariable("QuotesAmount");
		String QuotesBudget = uiDriver.ValuebyGetAttributeandStoreinVariable("QuotesBudget");
		String QuotesUnallocatedBudget = uiDriver.GetTextandStoreinVariable("QuotesUnallocatedBudget");
		QuotesAmount = QuotesAmount.replace("USD ", "").trim();
		QuotesBudget = QuotesBudget.replace("$", "").trim();
		QuotesUnallocatedBudget = QuotesUnallocatedBudget.replace("$", "").trim();
		if(QuotesAmount.contains(QuotesBudget) && QuotesAmount.contains(QuotesUnallocatedBudget))
		{
			reporter.logger.log(Status.PASS, "Opportunity Amount, Budget and UnAllocatedBudget in Quotes have Same values");
		}
		else
		{
			reporter.logger.log(Status.FAIL, "Opportunity Amount, Budget and UnAllocatedBudget in Quotes doesnot have Same values");
		}
		
		
		//Quote Name Validation
	    opp.EditQuote();
		
		uiDriver.ClickOnButtonorLink("EditQuoteName");
		Thread.sleep(5000);
		
		uiDriver.PerformKeyBoardOperations("CLEAR");
		opp.SaveQuote();
		
		boolean svalue = uiDriver.IsElementPresent("ErrorPopUp");
		if(svalue)
		{
			String stext = uiDriver.GetTextandStoreinVariable("ErrorMessage2");
			if(stext.equalsIgnoreCase("Save Failed: Please review the validation errors below"))
			{
				reporter.logger.log(Status.INFO, stext);
				uiDriver.ClickOnButtonorLink("CloseErrorPopUP");
				Thread.sleep(5000);
				stext = uiDriver.GetTextandStoreinVariable("QuoteNameValidation");
				reporter.logger.log(Status.INFO, stext);
				opp.CancelEditQuote();
			}
		
		}
		opp.ClickonOpportunities();
		
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
