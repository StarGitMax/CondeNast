package com.CondeNast.TestScenarios.RevenueAnalyst;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;
import com.aventstack.extentreports.Status;

public class ATLAS_663_Sprint7_TC_2687 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Accounts acc = new Accounts();
	Opportunities opp = new Opportunities();
	String AccountName;
	String sfUser_RA = "Eleanor Matthews";
	
	
	@Test
	public void ValidateAgencyClassificationField2() throws Exception
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
		AccountName = cusDetails.get("AccountName");
		
		//Navigate and Login to SalesForce
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_RA);
		
		// Create Account
		acc.ClickonAccounts();
		acc.ClickonCreateNewAccount();
		acc.SelectAccountType(accType);
		acc.ClickNext();
		Thread.sleep(5000);
		if(uiDriver.driver.findElements(By.xpath("//label[text()='Agency Classification']/following-sibling::div/lightning-base-combobox/div/div/input")).size()>0)
		{
			reporter.logger.log(Status.INFO, "Agency Classification  Present");
		}
		else
	    {
		    reporter.logger.log(Status.PASS, "Agency Classification is not Present");
	    }
		acc.CancelAccountCreation();
	
		opp.LogoutofSalesForceUser(sfUser_RA);
		Thread.sleep(5000);
	}
}
