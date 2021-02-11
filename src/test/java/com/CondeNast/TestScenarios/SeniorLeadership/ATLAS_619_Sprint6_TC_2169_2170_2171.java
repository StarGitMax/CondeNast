package com.CondeNast.TestScenarios.SeniorLeadership;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.CondeNast.Core.WebControls.LIST;
import com.CondeNast.TestLibrary.Accounts;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;
import com.aventstack.extentreports.Status;

public class ATLAS_619_Sprint6_TC_2169_2170_2171 extends BaseTest
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
	String sfUser_SL = "Antonia Wigan";
	int sAmount;
	int[] sAmount1= new int[2];
	int j=0;
	String MQLIName;
	String oppName;
	HashMap<String, String> cusDetails;
	
	@Test
	public void ValidateForecastCalculation() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SL);
		AccountName = CreateAccount();
		opp.ClickonOpportunities();
		opp.ClickonCreateNewOpportunities();
		opp.fillOpportunitiesForm(AccountName,"20000","31/03/2021","IdentifyingOpportunity","CentrallyBilled");
		OppName = opp.SaveOpportunitiesDetails();
		opp.CreateQuotes();
		rowcount = opp.getQuoteLineItemsCount();
		if(rowcount>0)
		{
			snames = opp.getQuoteLineItemName();
		}
		
		createQuoteLine("Identifying Opportunity (10%)");
		opp.getOpportunitydetails();
		opp.getQuoteLinedetails();
		
		createQuoteLine("Shaping Proposal (50%)");
		opp.getOpportunitydetails();
		opp.getQuoteLinedetails();
		
		String sforecastAmount = uiDriver.driver.findElement(By.xpath("(//p[text()='Forecast Amount'])[2]/following-sibling::div/div/div/span")).getText();
		String f11 = sforecastAmount.replace("$", "").replace(",", "").replace(".00", "").trim();
	    int fc1 = Integer.parseInt(f11);
	    System.out.println("sforecastAmount : " + fc1);
		rowcount = opp.getQuoteLineItemsCount();
		HashMap<Integer, HashMap<String, String>> sdat = opp.getNewQuoteLineItemDetails2(rowcount);
        for(int k=1;k<=sdat.size();k++)
        {
        	MQLIName = sdat.get(k).get("NAME").toString();
    		System.out.println("Name : " +MQLIName);
    		System.out.println("PLATFORM : " +sdat.get(k).get("PLATFORM").toString());
    		reporter.logger.log(Status.INFO, "***************************PARENT QLI*****************");
    		reporter.logger.log(Status.INFO, "Name : " +sdat.get(k).get("NAME"));
    		reporter.logger.log(Status.INFO, "MARKET : " +sdat.get(k).get("MARKET"));
    		reporter.logger.log(Status.INFO, "PLATFORM : " +sdat.get(k).get("PLATFORM"));
    		reporter.logger.log(Status.INFO, "NET MEDIA/PRODUCTION : " +sdat.get(k).get("NET MEDIA/PRODUCTION"));
    		reporter.logger.log(Status.INFO, "TOTAL NET : " +sdat.get(k).get("TOTAL NET"));
    		reporter.logger.log(Status.INFO, "BUDGET : " +sdat.get(k).get("BUDGET"));
    		reporter.logger.log(Status.INFO, "UNASSIGNED : " +sdat.get(k).get("UNASSIGNED"));
    		reporter.logger.log(Status.INFO, "SALES STAGE : " +sdat.get(k).get("SALES STAGE"));
    		reporter.logger.log(Status.INFO, "***************************END*****************");
    	
    		sAmount1[j]=opp.validateForecastAmmount(sdat.get(k).get("BUDGET"),sdat.get(k).get("SALES STAGE"));
    		j = j+1;
        }
          sAmount = sAmount1[0]+sAmount1[1];
          System.out.println("sforecastAmount : " + fc1);
          
          if(fc1==sAmount)
          {
        	  System.out.println("True : ");
        	  reporter.logger.log(Status.PASS, "Master QLI ForecastAmount is equal to sum of Sub QLI's stages budget");
          }
        
        opp.LogoutofSalesForceUser(sfUser_SL);
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
	    uiDriver.SetValueForTextBox("StartDate","16/01/2021");
		uiDriver.SetValueForTextBox("EndDate", "30/04/2021");
		uiDriver.SetValueForList("QLStage", LIST.ByVisibleText, stage);
		Thread.sleep(10000);
		uiDriver.SetValueForList("Platform", LIST.ByVisibleText, "Digital");
		Thread.sleep(10000);
		uiDriver.SetValueForList("Brand", LIST.ByVisibleText, "Teen Vogue");
		Thread.sleep(10000);
		opp.SelectAvailableMultipleMarkets("GCP HQ;Australia", "3000;3000");
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
