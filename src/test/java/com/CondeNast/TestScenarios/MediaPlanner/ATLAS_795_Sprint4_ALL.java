package com.CondeNast.TestScenarios.MediaPlanner;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;
import com.aventstack.extentreports.Status;

public class ATLAS_795_Sprint4_ALL extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	String oppdet;
	String AccountName="John Deo";
	String snames;
	String OppName;
	String sfUser_MP = "Shuo Yu";

	
	@Test
	public void  ValidationforCreateQLIs() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_MP);
		
		opp.ClickonOpportunities();	
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"6000","31/01/2021","IdentifyingOpportunity","CentrallyBilled");
		OppName = opp.SaveOpportunitiesDetails();
		opp.CreateQuotes();
		int rowcount = opp.getQuoteLineItemsCount();
		if(rowcount>0)
		{
			snames = opp.getQuoteLineItemName();
		}
		opp.CreateNewQuoteLineItem();
		opp.QuoteLineItemFieldsValidations("6000","16/11/2020","31/01/2021","Digital","GCP HQ;Australia", "4000;3000","GCP HQ;Australia", "3000;1000");
		opp.SaveQuoteLineItemToCheckMandatoryFields();
		opp.RefreshRevenueSection();
		opp.editRevenueLiesDetails("4000;3000");
		opp.SaveQuoteLineItemToCheckMandatoryFields();
		opp.editRevenueLiesDetails("3000;3000");
		opp.SaveQuoteLineItem();
		rowcount = opp.getQuoteLineItemsCount();
		HashMap<String, String> sdat = opp.getNewQuoteLineItemDetails(rowcount);
		System.out.println("Name : " +sdat.get("NAME"));
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
		uiDriver.driver.findElement(By.xpath("//td[text()='"+sdat.get("NAME")+"']")).click();
		Thread.sleep(5000);
		rowcount = opp.getQuoteLineItemsCount();
		opp.VerifyActionsforMasterQLI(sdat.get("NAME"));
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
		opp.EditQuoteLine(sdat.get("NAME"));
		opp.fillQuoteLiesDetails("GCP HQ", "3000");
		opp.fillQuoteLiesDetails("Australia", "4000");
		opp.SaveQuoteLineItemToCheckMandatoryFields();
		opp.fillQuoteLiesDetails("GCP HQ", "1000");
		opp.fillQuoteLiesDetails("Australia", "3000");
		opp.SaveQuoteLineItem();
		
		opp.LogoutofSalesForceUser(sfUser_MP);
		Thread.sleep(5000);
	}
	
}
