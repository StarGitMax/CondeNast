package com.CondeNast.TestScenarios.RevenueAnalyst;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_1911_Sprint7_TC_2565 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	Accounts acc = new Accounts();
	String oppdet;
	String AccountName;
	String snames;
	String sname;
	String OppName;
	int rowcount;
	String sfUser_RA = "Eleanor Matthews";
	HashMap<String, String> cusDetails;
	
		
		@Test
		public void VeirfyOpportunityClientBrand() throws Exception
		{
			sl.NavigateandLogin();
			opp.LoginAsDifferentUsers(sfUser_RA);
			AccountName = CreateAccount();
			opp.ClickonOpportunities();	
			opp.ClickonCreateNewOpportunities();
			opp.fillOpportunitiesForm(AccountName,"18000","31/03/2021","IdentifyingOpportunity","CentrallyBilled","Acer");
			OppName = opp.SaveOpportunitiesDetails();
			opp.ClickonDetails2();
			uiDriver.GetTextandStoreinVariable("ClientBrandinDetailsTab");
			Actions act =  new Actions(uiDriver.driver);
			act.moveToElement(driver.findElement(By.xpath("//span[text()='Other Information']"))).build().perform();
			Thread.sleep(5000);
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(200);
			
			opp.LogoutofSalesForceUser(sfUser_RA);
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
