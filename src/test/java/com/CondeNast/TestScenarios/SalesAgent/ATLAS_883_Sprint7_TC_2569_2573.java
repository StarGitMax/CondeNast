package com.CondeNast.TestScenarios.SalesAgent;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Contacts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_883_Sprint7_TC_2569_2573 extends BaseTest
{

	SalesforceLogin sl = new SalesforceLogin();
	Accounts acc = new Accounts();
	Opportunities opp = new Opportunities();
	Contacts cont = new Contacts();
	String AccountName;
	String cAccountName;
	String contactName;
	String sfUser_SA = "Ariana Stachrowski";
	
	@Test
	public void CreateAccountRelationship() throws Exception
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
		
		//Navigate and Login to SalesForce
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SA);
		
		// Create Account
		cAccountName = CreateAccount();
		cusDetails.put("accountName", cAccountName);
		
		cont.ClickonContacts();
		cont.ClickonCreateNewContact();
		cont.fillContactdetails(cusDetails);
		contactName = cont.SaveContactDetails();
		String[] cName = contactName.split("\\.");
		
		
		// Create Account with RelationShip
	   AccountName = CreateAccount();
	   cusDetails.put("AccountName", AccountName);
		
		acc.ClickonRelatedTab();
		acc.ClickonAddRelationship();
		uiDriver.ClickOnButtonorLink("Contact");
		Thread.sleep(10000);
		uiDriver.driver.findElement(By.xpath("//div[@title='"+cName[1].trim()+"']")).click();
		Thread.sleep(5000);
		acc.SelectRole("Billing");
        acc.SaveAccountContactRelationship();
		
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
