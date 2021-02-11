package com.CondeNast.TestScenarios;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;
import com.aventstack.extentreports.Status;

public class ATLAS_1124_Sprint5_TC_1686 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	String oppdet;
	String AccountName="111 SKIN LTD";
	String snames;
	String sname;
	String OppName;
	int rowcount;
	String sfuser = "Akhil Manne";
	

	@Test
	public void ValidateFilterSearchNonGCPUser() throws Exception
	{
		
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfuser);
		
		opp.ClickonOpportunities();	
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"18000","31/01/2021","IdentifyingOpportunity");
		String sval =opp.SaveOpportunitiesDetails();
		opp.CreateQuotes();
		rowcount = opp.getQuoteLineItemsCount();
		if(rowcount>0)
		{
			snames = opp.getQuoteLineItemName();
		}
		
		opp.CreateNewQuoteLineItem();
		opp.FillQuoteLineItemFields("6000","16/11/2020","31/01/2021","Digital");
		opp.SelectAvailableMultipleMarkets("GCP HQ;Australia", "2000;4000");
		opp.RefreshRevenueSection();
		opp.fillRevenueLiesDetails("6000", "10");
		opp.SaveQuoteLineItem();
		
		opp.CreateNewQuoteLineItem();
		opp.FillQuoteLineItemFields("6000","01/01/2021","31/05/2021","Business Services");
		opp.SelectAvailableMultipleMarkets("Singapore;South Korea", "1000;4000");
		opp.RefreshRevenueSection();
		opp.fillRevenueLiesDetails("6000", "10");
		opp.SaveQuoteLineItem();
		
		opp.CreateNewQuoteLineItem();
		opp.FillQuoteLineItemFields("6000","01/01/2021","31/07/2021","Video");
		opp.SelectAvailableMultipleMarkets("Hungary;CN US", "2000;2000");
		opp.RefreshRevenueSection();
		opp.fillRevenueLiesDetailswithpercenage("50");
		opp.SaveQuoteLineItem();
		Thread.sleep(20000);
		
        opp.SelectSearchOption("SearchPlatform", "Business Services");
        sname = getQLIdata();
        opp.ClearFilters();
        
        opp.SelectSearchOption("SearchPlatform","Digital");
        sname = getQLIdata();
        opp.ClearFilters();
        
        opp.SelectSearchOption("SearchPlatform","Video");
        sname = getQLIdata();
        opp.ClearFilters();
        
		
        opp.LogoutofSalesForceUser(sfuser);
      	Thread.sleep(5000);
	}
	
	
	
	
	public String getQLIdata() throws InterruptedException
	{
		rowcount = opp.getQuoteLineItemsCount();
		HashMap<String, String> sdat = opp.getNewQuoteLineItemDetails(rowcount);
		System.out.println("Name : " +sdat.get("NAME"));
		System.out.println("PLATFORM : " +sdat.get("PLATFORM"));
		reporter.logger.log(Status.INFO, "***************************PARENT QLI*****************");
		reporter.logger.log(Status.INFO, "Name : " +sdat.get("NAME"));
		reporter.logger.log(Status.INFO, "MARKET : " +sdat.get("MARKET"));
		reporter.logger.log(Status.INFO, "PLATFORM : " +sdat.get("PLATFORM"));
		reporter.logger.log(Status.INFO, "NET PRICE : " +sdat.get("NET MEDIA"));
		reporter.logger.log(Status.INFO, "NET NET PRICE : " +sdat.get("TOTAL NET"));
		reporter.logger.log(Status.INFO, "BUDGET : " +sdat.get("BUDGET"));
		reporter.logger.log(Status.INFO, "UNASSIGNED : " +sdat.get("UNASSIGNED"));
		reporter.logger.log(Status.INFO, "SALES STAGE : " +sdat.get("SALES STAGE"));
		reporter.logger.log(Status.INFO, "***************************END*****************");
		uiDriver.driver.findElement(By.xpath("//td[text()='"+sdat.get("NAME")+"']")).click();
		Thread.sleep(5000);
		rowcount = opp.getQuoteLineItemsCount();
		for(int j=2;j<=rowcount;j++)
		{
			HashMap<String, String> sdat1 = opp.getNewQuoteLineItemDetails(j);
			System.out.println("Name2 : " +sdat1.get("NAME"));
			System.out.println("MARKET : " +sdat1.get("MARKET"));
			System.out.println("PLATFORM : " +sdat1.get("PLATFORM"));
			reporter.logger.log(Status.INFO, "***************************CHILD QLI*****************");
			reporter.logger.log(Status.INFO, "Name : " +sdat1.get("NAME"));
			reporter.logger.log(Status.INFO, "MARKET : " +sdat1.get("MARKET"));
			reporter.logger.log(Status.INFO, "PLATFORM : " +sdat1.get("PLATFORM"));
			reporter.logger.log(Status.INFO, "NET PRICE : " +sdat1.get("NET MEDIA"));
			reporter.logger.log(Status.INFO, "NET NET PRICE : " +sdat1.get("TOTAL NET"));
			reporter.logger.log(Status.INFO, "***************************END*****************");
		}
		
		return sdat.get("NAME");
	}
}
