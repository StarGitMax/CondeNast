package com.CondeNast.TestScenarios.MediaPlanner;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.CondeNast.Core.WebControls.LIST;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;
import com.aventstack.extentreports.Status;

public class ATLAS_627_Sprint5_TC_1710 extends BaseTest
{

	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	String oppdet;
	String AccountName="John Deo";
	String snames;
	String sname;
	String OppName;
	int rowcount;
	String sfUser_MP = "Shuo Yu";
	
	
	@Test
	public void VeirfyQLIDeleteFunctionlaity() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_MP);
		
		opp.ClickonOpportunities();	
		opp.ClickonCreateNewOpportunities();
		String sval = opp.fillOpportunitiesForm(AccountName,"6000","31/01/2021","IdentifyingOpportunity","CentrallyBilled");
		String[] sval1 = sval.split(";");
		OppName = sval1[0];
		opp.SaveOpportunitiesDetails();
		opp.CreateQuotes();
		rowcount = opp.getQuoteLineItemsCount();
		if(rowcount>0)
		{
			snames = opp.getQuoteLineItemName();
		}
		
		// Verbal Commitment (75%)
		createQuoteLine("Verbal Commitment (75%)");
		sname = getQLIdata();
		opp.EditOpprtunity();
		Thread.sleep(5000);
		opp.SelectOpportunityStage("Verbal Commitment");
		opp.SelectClientBrand("Acer");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(10000);
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//span[text()='Delete']"))).click().build().perform();
	    Thread.sleep(5000);
	    opp.CloseErrorPopUp();
	    
	    opp.EditOpprtunity();
	    Thread.sleep(5000);
		opp.SelectOpportunityStage("Shaping Proposal");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(10000);
		
		//Contracting (90%)
		opp.EditQuoteLine(sname);
		Thread.sleep(10000);
		uiDriver.SetValueForList("QLStage", LIST.ByVisibleText, "Contracting (90%)");
		Thread.sleep(10000);
	    opp.SaveQuoteLineItem();
		sname = getQLIdata();
		opp.EditOpprtunity();
		Thread.sleep(10000);
		opp.SelectOpportunityStage("Contracting");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(10000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(driver.findElement(By.xpath("//span[text()='Delete']"))).click().build().perform();
	    Thread.sleep(5000);
	    opp.CloseErrorPopUp();
		
	    opp.EditOpprtunity();
		Thread.sleep(5000);
		opp.SelectOpportunityStage("Shaping Proposal");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(10000);
		
		//Closed Won (100%)
		opp.EditQuoteLine(sname);
		Thread.sleep(5000);
		uiDriver.SetValueForList("QLStage", LIST.ByVisibleText, "Closed Won (100%)");
		Thread.sleep(10000);
	    opp.SaveQuoteLineItem();
		sname = getQLIdata();
		opp.EditOpprtunity();
		Thread.sleep(5000);
		opp.SelectOpportunityStage("Closed Won");
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(10000);
		Actions act2 =  new Actions(uiDriver.driver);
		act2.moveToElement(driver.findElement(By.xpath("//span[text()='Delete']"))).click().build().perform();
	    Thread.sleep(5000);
	    opp.CloseErrorPopUp();
		
		opp.LogoutofSalesForceUser(sfUser_MP);
		Thread.sleep(5000);
		
	}
	
	
	

	public void createQuoteLine(String stage) throws Exception
	{
		opp.CreateNewQuoteLineItem();
		uiDriver.WaitforElementPresent("Budget");
		Actions act = new Actions(uiDriver.driver);
		act.doubleClick(uiDriver.driver.findElement(By.xpath("//label[text()='Budget']/following-sibling::div/div/input"))).sendKeys(Keys.DELETE).build().perform();;
		Thread.sleep(1000);
		uiDriver.SetValueForTextBox("Budget", "6000");
	    uiDriver.SetValueForTextBox("StartDate","16/11/2020");
		uiDriver.SetValueForTextBox("EndDate", "31/01/2021");
		uiDriver.SetValueForList("QLStage", LIST.ByVisibleText, stage);
		Thread.sleep(10000);
		uiDriver.SetValueForList("Platform", LIST.ByVisibleText, "Digital");
		Thread.sleep(10000);
		uiDriver.SetValueForList("Brand", LIST.ByVisibleText, "Teen Vogue");
		Thread.sleep(10000);
		opp.SelectAvailableMultipleMarkets("GCP HQ;Australia", "4000;2000");
		opp.RefreshRevenueSection();
		opp.fillRevenueLiesDetails("6000", "10");
		opp.SaveQuoteLineItem();
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
		reporter.logger.log(Status.INFO, "NET MEDIA/PRODUCTION : " +sdat.get("NET MEDIA/PRODUCTION"));
		reporter.logger.log(Status.INFO, "TOTAL NET : " +sdat.get("TOTAL NET"));
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
			reporter.logger.log(Status.INFO, "NET MEDIA/PRODUCTION : " +sdat1.get("NET MEDIA/PRODUCTION"));
			reporter.logger.log(Status.INFO, "TOTAL NET : " +sdat1.get("TOTAL NET"));
			reporter.logger.log(Status.INFO, "***************************END*****************");
		}
		
		return sdat.get("NAME");
	}
}
