package com.CondeNast.TestScenarios;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;
import com.aventstack.extentreports.Status;

public class ATLAS_758_Sprint3_TC_111_112_new extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	String oppName = "AUT-GCP HQ: John Deo-2021Q1-CMP_0710-000000708";
	int rowcount;
	String MQLIName;
	int sAmount;
	int[] sAmount1= new int[2];
	int j=0;
	
	
	@Test
	public void ValidatePlatformFields1() throws Exception
	{
		sl.NavigateandLogin();
		opp.ClickonOpportunities();
		opp.ClickonExistingOpportunities(oppName);
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
    		reporter.logger.log(Status.INFO, "NET PRICE : " +sdat.get(k).get("NET MEDIA/PRODUCTION"));
    		reporter.logger.log(Status.INFO, "NET NET PRICE : " +sdat.get(k).get("TOTAL NET"));
    		reporter.logger.log(Status.INFO, "BUDGET : " +sdat.get(k).get("BUDGET"));
    		reporter.logger.log(Status.INFO, "UNASSIGNED : " +sdat.get(k).get("UNASSIGNED"));
    		reporter.logger.log(Status.INFO, "SALES STAGE : " +sdat.get(k).get("SALES STAGE"));
    		reporter.logger.log(Status.INFO, "***************************END*****************");
    	
    		sAmount1[j]=validateForecastAmmount(sdat.get(k).get("BUDGET"),sdat.get(k).get("SALES STAGE"));
    		j = j+1;
        }
          sAmount = sAmount1[0]+sAmount1[1];
          System.out.println("sforecastAmount : " + fc1);
          
          if(fc1==sAmount)
          {
        	  System.out.println("True : ");
        	  reporter.logger.log(Status.PASS, "Master QLI ForecastAmount is equal to sum of Sub QLI's stages budget");
          }
        
       
		/*
		 * opp.SelectSearchOption("SearchPlatform", "Business Services");
		 * opp.ClearFilters();
		 * 
		 * opp.SelectSearchOption("SearchPlatform","Digital"); opp.ClearFilters();
		 * 
		 * opp.SelectSearchOption("SearchPlatform","Video"); opp.ClearFilters();
		 */
	    sl.Logout();
	   
	}
	
	
	public int validateForecastAmmount(String amt, String stg)
	{
		String b1 = amt;
		int st1 = 0;
		
		
		if(stg.equalsIgnoreCase("Identifying Opportunity (10%)"))
		{
			st1 = 10;
		}
		else if(stg.equalsIgnoreCase("Shaping Proposal (50%)"))
		{
			st1 = 50;
		}
		else if(stg.equalsIgnoreCase("Verbal Commitment (75%)"))
		{
			st1 = 75;
		}
		else if(stg.equalsIgnoreCase("Contracting (90%)"))
		{
			st1 = 90;
		}
		else if(stg.equalsIgnoreCase("Closed Won (100%)"))
		{
			st1 = 100;
		}
		
		
		String b11 = b1.replace("US$", "").replace(",", "").replace(".00", "");
		System.out.println("Total Aount : " + b11);
		
		
		float fb1 = Float.parseFloat(b11);
		
        float per = (fb1/100)*st1;
        System.out.println("Percentage ::"+ per);
        
        int amt1 = Integer.parseInt(String.valueOf(per).replace(".0", ""));
       

        return amt1;
	}
	

	//@Test
	public void ValidatePlatformFields() throws Exception
	{
		sl.NavigateandLogin();
		opp.ClickonOpportunities();
		opp.ClickonExistingOpportunities(oppName);
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
    		reporter.logger.log(Status.INFO, "NET PRICE : " +sdat.get(k).get("NET MEDIA"));
    		reporter.logger.log(Status.INFO, "NET NET PRICE : " +sdat.get(k).get("TOTAL NET"));
    		reporter.logger.log(Status.INFO, "BUDGET : " +sdat.get(k).get("BUDGET"));
    		reporter.logger.log(Status.INFO, "UNASSIGNED : " +sdat.get(k).get("UNASSIGNED"));
    		reporter.logger.log(Status.INFO, "SALES STAGE : " +sdat.get(k).get("SALES STAGE"));
    		reporter.logger.log(Status.INFO, "***************************END*****************");
        }
		
		//opp.VerifyActionsforMasterQLI(MQLIName);
		//opp.CopyQuoteLine();
		
		//getSubQLIDetails(MQLIName);
        
     // Shaping Proposal (50%)
     		opp.EditOpprtunity();
     		Thread.sleep(10000);
     		opp.SelectOpportunityStage("Shaping Proposal");
     		opp.SaveOpportunitiesDetails();
     		
	   
	}
	

	public void getSubQLIDetails(String name) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']")).click();
		Thread.sleep(5000);
		rowcount = opp.getQuoteLineItemsCount();
		for(int i=2;i<=rowcount;i++)
		{
			HashMap<String, String> sdat1 = opp.getNewQuoteLineItemDetails(i);
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
	}
	
	

}
