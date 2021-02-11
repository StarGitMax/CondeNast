package com.CondeNast.TestScenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.CondeNast.Core.WebControls.LIST;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class OpportunitiesCreation extends BaseTest
{

	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	String AccountName="111 SKIN LTD";
	
	@Test
	public void CreateOpportunities() throws Exception
	{
		sl.NavigateandLogin();
		opp.ClickonOpportunities();
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"3000","30/06/2021","IdentifyingOpportunity","CentrallyBilled");
		String OppName = opp.SaveOpportunitiesDetails();
		opp.CreateQuotes();
		opp.CreateNewQuoteLineItem();
		uiDriver.WaitforElementPresent("Budget");
		Actions act = new Actions(uiDriver.driver);
		act.doubleClick(uiDriver.driver.findElement(By.xpath("//label[text()='Budget']/following-sibling::div/div/input"))).sendKeys(Keys.DELETE).build().perform();;
		Thread.sleep(1000);
		uiDriver.SetValueForTextBox("Budget", "3000");
	    uiDriver.SetValueForTextBox("StartDate","01/01/2021");
		uiDriver.SetValueForTextBox("EndDate", "31/03/2021");
		uiDriver.SetValueForList("QLStage", LIST.ByVisibleText, "Identifying Opportunity (10%)");
		Thread.sleep(10000);
		uiDriver.SetValueForList("Platform", LIST.ByVisibleText, "Digital");
		Thread.sleep(5000);
		uiDriver.SetValueForList("Brand", LIST.ByVisibleText, "Teen Vogue");
		Thread.sleep(5000);
		opp.SelectAvailableMultipleMarkets("GCP HQ;Australia", "1500;1500");
		opp.RefreshRevenueSection();
		opp.fillRevenueLiesDetails("3000", "10");
		opp.SaveQuoteLineItem();
		sl.Logout();
	}
	
	
	
}
