package com.CondeNast.TestScenarios.MediaPlanner;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;
import com.aventstack.extentreports.Status;

public class ATLAS_1083_Sprint4_TC_ALL extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	String oppdet;
	String AccountName="John Deo";
	String snames;
	String OppName;
	int rowcount;
	String MQLIName;
	String sfUser_MP = "Shuo Yu";
	
	@Test
	public void  ValidateCloneQLIs() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_MP);
		
		opp.ClickonOpportunities();	
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"6000","31/01/2021","IdentifyingOpportunity","CentrallyBilled");
		OppName = opp.SaveOpportunitiesDetails();
		opp.CreateQuotes();
		rowcount = opp.getQuoteLineItemsCount();
		if(rowcount>0)
		{
			snames = opp.getQuoteLineItemName();
		}
		opp.CreateNewQuoteLineItem();
		opp.FillQuoteLineItemFields("6000","16/11/2020","31/01/2021","Digital");
		opp.SelectAvailableMultipleMarkets("GCP HQ;Australia", "3000;1000");
		opp.RefreshRevenueSection();
		opp.fillRevenueLiesDetails("6000", "10");
		opp.SaveQuoteLineItem();
		rowcount = opp.getQuoteLineItemsCount();
		HashMap<String, String> sdat = opp.getNewQuoteLineItemDetails(rowcount);
		MQLIName = sdat.get("NAME");
		System.out.println("Name : " +MQLIName);
		System.out.println("PLATFORM : " +sdat.get("PLATFORM"));
		reporter.logger.log(Status.INFO, "***************************PARENT QLI*****************");
		reporter.logger.log(Status.INFO, "Name : " +sdat.get("NAME"));
		reporter.logger.log(Status.INFO, "MARKET : " +sdat.get("MARKET"));
		reporter.logger.log(Status.INFO, "PLATFORM : " +sdat.get("PLATFORM"));
		reporter.logger.log(Status.INFO, "NET MEDIA/PRODUCTION : " +sdat.get("NET MEDIA/PRODUCTION"));
		reporter.logger.log(Status.INFO, "TOTAL NET : " +sdat.get("TOTAL NET"));
		reporter.logger.log(Status.INFO, "BUDGET : " +sdat.get("BUDGET"));
		reporter.logger.log(Status.INFO, "UNASSIGNED : " +sdat.get("UNASSIGNED"));
		reporter.logger.log(Status.INFO, "SALES STAGE : " +sdat.get("SALES STAGE"));
		reporter.logger.log(Status.INFO, "***************************END*****************");
		uiDriver.driver.findElement(By.xpath("//td[text()='"+MQLIName+"']")).click();
		Thread.sleep(5000);
		opp.VerifyActionsforMasterQLI(MQLIName);
		rowcount = opp.getQuoteLineItemsCount();
		for(int i=2;i<=rowcount;i++)
		{
			HashMap<String, String> sdat1 = opp.getNewQuoteLineItemDetails(i);
			System.out.println("Name2 : " +sdat1.get("NAME"));
			opp.VerifyActionsforSubQLI(sdat1.get("NAME"));
			System.out.println("MARKET : " +sdat1.get("MARKET"));
			System.out.println("PLATFORM : " +sdat1.get("PLATFORM"));
			reporter.logger.log(Status.INFO, "***************************CHILD QLI*****************");
			reporter.logger.log(Status.INFO, "Name : " +sdat1.get("NAME"));
			reporter.logger.log(Status.INFO, "MARKET : " +sdat1.get("MARKET"));
			reporter.logger.log(Status.INFO, "PLATFORM : " +sdat1.get("PLATFORM"));
			reporter.logger.log(Status.INFO, "NET MEDIA/PRODUCTION : " +sdat1.get("NET MEDIA/PRODUCTION"));
			reporter.logger.log(Status.INFO, "TOTAL NET : " +sdat1.get("TOTAL NET"));
			reporter.logger.log(Status.INFO, "***************************END*****************");
		}
		opp.CopyQuoteLine();
		rowcount = opp.getQuoteLineItemsCount();
		sdat = opp.getNewQuoteLineItemDetails(rowcount);
		MQLIName = sdat.get("NAME");
		System.out.println("Name : " +MQLIName);
		System.out.println("PLATFORM : " +sdat.get("PLATFORM"));
		reporter.logger.log(Status.INFO, "***************************CLONED PARENT QLI*****************");
		reporter.logger.log(Status.INFO, "Name : " +sdat.get("NAME"));
		reporter.logger.log(Status.INFO, "MARKET : " +sdat.get("MARKET"));
		reporter.logger.log(Status.INFO, "PLATFORM : " +sdat.get("PLATFORM"));
		reporter.logger.log(Status.INFO, "NET MEDIA/PRODUCTION : " +sdat.get("NET MEDIA/PRODUCTION"));
		reporter.logger.log(Status.INFO, "TOTAL NET : " +sdat.get("TOTAL NET"));
		reporter.logger.log(Status.INFO, "BUDGET : " +sdat.get("BUDGET"));
		reporter.logger.log(Status.INFO, "UNASSIGNED : " +sdat.get("UNASSIGNED"));
		reporter.logger.log(Status.INFO, "SALES STAGE : " +sdat.get("SALES STAGE"));
		reporter.logger.log(Status.INFO, "***************************END*****************");
		uiDriver.driver.findElement(By.xpath("//td[text()='"+MQLIName+"']")).click();
		Thread.sleep(5000);
		opp.VerifyActionsforMasterQLI(MQLIName);
		rowcount = opp.getQuoteLineItemsCount();
		for(int i=2;i<=rowcount;i++)
		{
			HashMap<String, String> sdat1 = opp.getNewQuoteLineItemDetails(i);
			System.out.println("Name2 : " +sdat1.get("NAME"));
			opp.VerifyActionsforSubQLI(sdat1.get("NAME"));
			System.out.println("MARKET : " +sdat1.get("MARKET"));
			System.out.println("PLATFORM : " +sdat1.get("PLATFORM"));
			reporter.logger.log(Status.INFO, "***************************CLONED CHILD QLI*****************");
			reporter.logger.log(Status.INFO, "Name : " +sdat1.get("NAME"));
			reporter.logger.log(Status.INFO, "MARKET : " +sdat1.get("MARKET"));
			reporter.logger.log(Status.INFO, "PLATFORM : " +sdat1.get("PLATFORM"));
			reporter.logger.log(Status.INFO, "NET MEDIA/PRODUCTION : " +sdat1.get("NET MEDIA/PRODUCTION"));
			reporter.logger.log(Status.INFO, "TOTAL NET : " +sdat1.get("TOTAL NET"));
			reporter.logger.log(Status.INFO, "***************************END*****************");
		}
		
		opp.LogoutofSalesForceUser(sfUser_MP);
		Thread.sleep(5000);
	}
	
	

}
