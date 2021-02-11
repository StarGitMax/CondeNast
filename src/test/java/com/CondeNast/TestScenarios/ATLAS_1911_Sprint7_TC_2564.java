package com.CondeNast.TestScenarios;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_1911_Sprint7_TC_2564 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	String oppdet;
	String AccountName="John Deo";
	String snames;
	String sname;
	String OppName;
	int rowcount;
	String sfUser_RA = "Eleanor Matthews";
	String sfUser_SA = "Ariana Stachrowski";
	String sfUser_SL = "Antonia Wigan";
	String sfUser_MP = "Shuo Yu";
	
	@Test
	public void VeirfyOpportunityisnotablesavewithoutClietBrand() throws Exception
	{
		sl.NavigateandLogin();
		opp.ClickonOpportunities();	
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"18000","31/01/2021","IdentifyingOpportunity","CentrallyBilled");
		OppName = opp.SaveOpportunitiesDetails();
		
		//Verbal Commitment (75%)
		opp.EditOpprtunity();
		Thread.sleep(5000);
		opp.SelectOpportunityStage2("Verbal Commitment");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(5000);
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		opp.CancelOpportunitiesCreation();
		
		
		// Contracting (90%)
		opp.EditOpprtunity();
		Thread.sleep(5000);
		opp.SelectOpportunityStage2("Contracting");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(5000);
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		opp.CancelOpportunitiesCreation();
				
		// Closed Won (100%)
		opp.EditOpprtunity();
		Thread.sleep(5000);
		opp.SelectOpportunityStage2("Closed Won");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(5000);
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		opp.CancelOpportunitiesCreation();
		
		sl.Logout();
 }
	
	
	//@Test
	public void VeirfyOpportunityisnotablesavewithoutClietBrand2() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_MP);
		
		opp.ClickonOpportunities();	
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"18000","31/01/2021","IdentifyingOpportunity","CentrallyBilled");
		OppName = opp.SaveOpportunitiesDetails();
		
		//Verbal Commitment (75%)
		opp.EditOpprtunity();
		Thread.sleep(5000);
		opp.SelectOpportunityStage2("Verbal Commitment");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(5000);
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		opp.CancelOpportunitiesCreation();
		
		
		// Contracting (90%)
		opp.EditOpprtunity();
		Thread.sleep(5000);
		opp.SelectOpportunityStage2("Contracting");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(5000);
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		opp.CancelOpportunitiesCreation();
				
		// Closed Won (100%)
		opp.EditOpprtunity();
		Thread.sleep(5000);
		opp.SelectOpportunityStage2("Closed Won");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(5000);
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		opp.CancelOpportunitiesCreation();
		
		opp.LogoutofSalesForceUser(sfUser_MP);
		Thread.sleep(5000);
 }
	
	
}
