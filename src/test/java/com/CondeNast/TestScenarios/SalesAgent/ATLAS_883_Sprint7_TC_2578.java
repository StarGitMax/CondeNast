package com.CondeNast.TestScenarios.SalesAgent;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Contacts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_883_Sprint7_TC_2578 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Contacts cont = new Contacts();
	Opportunities opp = new Opportunities();
	Accounts acc = new Accounts();
	String AccountName;
	String cAccountName;
	String ContName;
	String sfUser_SA = "Ariana Stachrowski";
	HashMap<String, String> cusDetails;
	
	@Test
	public void  CreateContact() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SA);
		
		AccountName = CreateAccount();
		cAccountName = CreateAccount();
		
		cusDetails.put("AccountName", AccountName);
		cusDetails.put("accountName", cAccountName);
		
		cont.ClickonContacts();
		cont.ClickonCreateNewContact();
		cont.fillContactdetails(cusDetails);
		cont.SaveContactDetails();
		
		cont.ClickonRelatedTab();
		cont.ClickonAddRelationship();
		uiDriver.ClickOnButtonorLink("Account");
		Thread.sleep(10000);
		uiDriver.driver.findElement(By.xpath("//div[@title='"+AccountName+"']")).click();
		Thread.sleep(5000);
		cont.SelectRole("Billing");
		cont.SaveAccountContactRelationship();

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
		uiDriver.ClickOnButtonorLink("SaveAccountDetails");
		Thread.sleep(15000);
		String img1 = uiDriver.CaptureFullScreenShot("AccountPage");
		reporter.addScreenshotToReport(img1, "Account Created.");
		Thread.sleep(5000);
		String accName = cusDetails.get("AccountName");
		
		return accName;
	}
	
	
}
