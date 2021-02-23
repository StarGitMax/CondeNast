package com.CondeNast.TestLibrary;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.FindFailed;
import com.CondeNast.Core.ReporterClass;
import com.CondeNast.Core.UtilityFunctions.Mode;
import com.CondeNast.Core.WebControls.LIST;
import com.aventstack.extentreports.Status;

public class Opportunities extends BaseTest
{
    
	public void ClickonOpportunities() throws Exception
	{
		WebElement Opportunities = uiDriver.driver.findElement(By.xpath("//a[@title='Opportunities']/span"));
		JavascriptExecutor executor = (JavascriptExecutor)uiDriver.driver;
		executor.executeScript("arguments[0].click();", Opportunities);
		Thread.sleep(20000);
		reporter.logger.log(Status.PASS, "Successfully Clicked on Opportunity Link ");
	}
	
	
	public void ClickonCreateNewOpportunities() throws Exception
	{
		uiDriver.ClickOnButtonorLink("CreateNewOpportunities"); 
		//uiDriver.WaitforElementPresent("NewAccount_Next");
		Thread.sleep(10000);
	}
	
	public void ClickonExistingOpportunities(String oppName) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+oppName+"']")).click();
		Thread.sleep(25000);
	}
	
	public void SearhforOpportunity(String oppName) throws FindFailed, Exception
	{
		uiDriver.screen.paste(Pattern.SearchOpportunities, oppName);
		Thread.sleep(5000);
		uiDriver.PerformKeyBoardOperations("ENTER");
	}
	
	public void SearhandClickonOpportunity(String oppName) throws FindFailed, Exception
	{
		uiDriver.screen.paste(Pattern.SearchOpportunities, oppName);
		Thread.sleep(10000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(uiDriver.driver.findElement(By.xpath("//span[@title='"+oppName+"']"))).click().build().perform();
		Thread.sleep(10000);
	}
	
	public void ClickonOpportunitySubMenu(String oppName) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+oppName+"']/../.././following-sibling::td[6]/span/div/a")).click();
		Thread.sleep(25000);
		uiDriver.WaitforVisiblilityofElement("OpportunitySubMenuEdit");
		uiDriver.ClickOnButtonorLink("OpportunitySubMenuEdit"); 
		Thread.sleep(5000);
		
	}
	
	public void VerifyDeleteOptionforOpportunity(String oppName) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+oppName+"']/../../following-sibling::td[6]/span/div")).click();
		Thread.sleep(9000);
		if(uiDriver.driver.findElements(By.xpath("//a[@title='Delete']")).size()>0)
		{
			reporter.logger.log(Status.FAIL, "Delete option for Opportunity Present");
		}
		else
		{
			reporter.logger.log(Status.PASS, "Delete option for Opportunity is not Present");
			String img1 = uiDriver.CaptureFullScreenShot("Opportunity");
			reporter.addScreenshotToReport(img1, "Opportunity Delete Option");
			Thread.sleep(5000);
		}
	}
	

	public void DeleteOpportunity(String oppName) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+oppName+"']/../../following-sibling::td[6]/span/div")).click();
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("MarketAccountSubMenuDelete");
		Thread.sleep(5000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(driver.findElement(By.xpath("//button[@title='Delete']"))).click().build().perform();
		Thread.sleep(2000);
		reporter.logger.log(Status.PASS, "Successfully Clicked on Delete");
		String img1 = uiDriver.CaptureFullScreenShot("Opportunity");
		reporter.addScreenshotToReport(img1, "Opportunity Deleted Succesfully");
	}
	
	public void VerifyEditButtontoeditOpportunity()
	{
		if(uiDriver.driver.findElements(By.xpath("//button[@name='Edit']")).size()>0)
		{
			reporter.logger.log(Status.FAIL, "Edit button is Present");
		}
		else
	    {
		   reporter.logger.log(Status.PASS, "Edit button is not Present");
	    }
	}
	
	
	public void getQuoteLinedetails()
	{
		String sprobability = uiDriver.driver.findElement(By.xpath("//p[text()='Probability']/following-sibling::p/span")).getText();
		reporter.logger.log(Status.PASS, "Master QLI Probability "+sprobability);
		
		String sAmount = uiDriver.driver.findElement(By.xpath("(//p[text()='Amount'])[2]/following-sibling::div/div/div/span")).getText();
		reporter.logger.log(Status.PASS, "Master QLI Amount "+sAmount);
		
		String sforecastAmount = uiDriver.driver.findElement(By.xpath("(//p[text()='Forecast Amount'])[2]/following-sibling::div/div/div/span")).getText();
		reporter.logger.log(Status.PASS, "Master QLI ForecastAmount "+sforecastAmount);
		
		String sBudget = uiDriver.driver.findElement(By.xpath("//p[text()='Budget']/following-sibling::div/div//div/div/div/div/input")).getAttribute("value");
		reporter.logger.log(Status.PASS, "Master QLI Budget "+sBudget);
		
		String sUnallocatedBudget = uiDriver.driver.findElement(By.xpath("//p[text()='Unallocated Budget']/following-sibling::div/div/div/span")).getText();
		reporter.logger.log(Status.PASS, "Master QLI UnallocatedBudget "+sUnallocatedBudget);
	}
	
	public void getOpportunitydetails()
	{
		String sAmount = uiDriver.driver.findElement(By.xpath("(//p[text()='Amount'])[1]/following-sibling::p/slot/lightning-formatted-text")).getText();
		reporter.logger.log(Status.PASS, "Amount "+sAmount);
		
		String sforecastAmount = uiDriver.driver.findElement(By.xpath("(//p[text()='Forecast Amount'])[1]/following-sibling::p/slot/records-formula-output/slot/lightning-formatted-text")).getText();
		reporter.logger.log(Status.PASS, "Forecast Amount "+sforecastAmount);
	}
	
	
	public int getQuoteLineItemsCount() 
	{
		//No. of Columns
		int rowcount = 0;
        List  col = uiDriver.driver.findElements(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/thead/tr/th"));
        
        //No.of rows
        String sval = uiDriver.driver.findElement(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/tbody/tr/td")).getText();
        if(!sval.equalsIgnoreCase("No Results Found"))
        {
        	 List  rows = uiDriver.driver.findElements(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/tbody/tr"));
             rowcount = rows.size();
        }
        System.out.println("Total No of rows are : " + rowcount);
       
		return rowcount;
	}
	
	public HashMap<String, String> getNewQuoteLineItemDetails(int rownum)
	{
		HashMap<String, String> sData = new HashMap<String,String>();
		
		List  col = uiDriver.driver.findElements(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/thead/tr/th"));
        
        System.out.println("row "+rownum);
		 for(int j=1;j<=col.size();j++)
        {
              String sColValue = uiDriver.driver.findElement(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/thead/tr/th["+j+"]")).getText();
              String sColVal1 = uiDriver.driver.findElement(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/tbody/tr["+rownum+"]/td["+j+"]")).getText();
             // System.out.println("col "+ uiDriver.driver.findElement(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/thead/tr/th["+j+"]")));
              //System.out.println("colval "+uiDriver.driver.findElement(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/tbody/tr["+rownum+"]/td["+j+"]")));
             // System.out.println("sColValue "+sColValue);
             // System.out.println("sColVal1 "+sColVal1);
              sData.put(sColValue, sColVal1);
        }
        
        return sData;
	}
	
	public HashMap<Integer, HashMap<String, String>> getNewQuoteLineItemDetails2(int rownum)
	{
		HashMap<Integer, HashMap<String, String>> outerMap = new HashMap<Integer, HashMap<String,String>>();
	    HashMap<String, String> innerMap = new HashMap<String, String>();
		
		List  col = uiDriver.driver.findElements(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/thead/tr/th"));
        
        System.out.println("row "+rownum);
        for(int i=1;i<=rownum;i++)
        {
        	 for(int j=1;j<=col.size();j++)
             {
                   String sColValue = uiDriver.driver.findElement(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/thead/tr/th["+j+"]")).getText();
                   String sColVal1 = uiDriver.driver.findElement(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/tbody/tr["+i+"]/td["+j+"]")).getText();
                  // System.out.println("col "+ uiDriver.driver.findElement(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/thead/tr/th["+j+"]")));
                   //System.out.println("colval "+uiDriver.driver.findElement(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/tbody/tr["+rownum+"]/td["+j+"]")));
                  //System.out.println("sColValue "+sColValue);
                  System.out.println("sColVal1 "+sColValue+" : "+sColVal1);
                  innerMap.put(sColValue, sColVal1);
             }
        	 
        	 outerMap.put(i, innerMap);
        	 innerMap = new HashMap<String, String>();
        }
		
        
        return outerMap;
	}
	
	public String getQuoteLineItemName() throws Exception
	{
		//No. of Columns
		String name = null;
        List  col = uiDriver.driver.findElements(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/thead/tr/th"));
        System.out.println("Total No of columns are : " +col.size());
        
        //No.of rows
        List  rows = uiDriver.driver.findElements(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/tbody/tr"));
        System.out.println("Total No of rows are : " + rows.size());
       
		for(int i = 1;i<=rows.size();i++)
        {
			 for(int j=1;j<=col.size();j++)
             {
	               	String sColValue = uiDriver.driver.findElement(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/thead/tr/th["+(j)+"]")).getText();
	               	
	                String sColVal1 = 	uiDriver.driver.findElement(By.xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/div/c-bgm-oli-paginator/c-bgm-paginator/article/div[2]/slot/c-bgm-oli-paginator-table/div/table/tbody/tr["+(i)+"]/td["+(j)+"]")).getText();
	                
	                if(sColValue.equalsIgnoreCase("NAME"))
	                {
	                	if(name==null)
	                	{
	                		name = sColVal1;
	                	}
	                	else
	                	{
	                		name = name+";"+sColVal1;
	                	}
	                }
             }
			 
        }
		
		 System.out.println("sColValues "+name);
		 
		return name;
	}
	
	
	public String fillOpportunitiesForm(String accountName, String amount, String closeDate) throws Exception
	{
		String randNo = utilFuns.GenerateRandom(4, Mode.ALPHANUMERIC);
		String oppName = "OPP_"+randNo;
		String camp = "CAMP_"+randNo;
		uiDriver.SetValueForTextBox("OpportunityName", oppName);
		uiDriver.SetValueForTextBox("CampaignInitiative", camp);
		uiDriver.ClickOnButtonorLink("SearchAccountName");
		Thread.sleep(5000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+accountName+"']")).click();
		Thread.sleep(5000);
		//uiDriver.ClickOnButtonorLink("OpportunityCurrency");
		//Thread.sleep(5000);
		//uiDriver.ClickOnButtonorLink("INR");
		//Thread.sleep(5000);
		uiDriver.SetValueForTextBox("Amount", amount);
		uiDriver.SetValueForTextBox("CampaignStartDate", "11/12/2020");
		uiDriver.SetValueForTextBox("CloseDate", closeDate);
		
		
		uiDriver.ClickOnButtonorLink("Stage");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("IdentifyingOpportunity");
		Thread.sleep(5000);
		String probability = uiDriver.ValuebyGetAttributeandStoreinVariable("Probability");
		
		uiDriver.scrolltoViewElement("Category");
		uiDriver.ClickOnButtonorLink("Category");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("AUTAutomotive");
		Thread.sleep(5000);
		
		uiDriver.ClickOnButtonorLink("SubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Cars_American");
		Thread.sleep(5000);
		
		return oppName+";"+probability;
		
	}
	public String fillOpportunitiesForm(String accountName, String amount, String closeDate, String stage) throws Exception
	{
		String randNo = utilFuns.GenerateRandom(4, Mode.ALPHANUMERIC);
		String oppName = "OPP_"+randNo;
		uiDriver.SetValueForTextBox("OpportunityName", oppName);
		
		uiDriver.ClickOnButtonorLink("SearchAccountName");
		Thread.sleep(5000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+accountName+"']")).click();
		Thread.sleep(5000);
		
		uiDriver.ClickOnButtonorLink("OpportunityCurrency");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("INR");
		Thread.sleep(5000);
		
		
		uiDriver.SetValueForTextBox("Amount", amount);
		uiDriver.SetValueForTextBox("CloseDate", closeDate);
		
		uiDriver.ClickOnButtonorLink("Stage");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink(stage);
		Thread.sleep(5000);
		String probability = uiDriver.ValuebyGetAttributeandStoreinVariable("Probability");
		
		uiDriver.scrolltoViewElement("Category");
		uiDriver.ClickOnButtonorLink("Category");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("AUTAutomotive");
		Thread.sleep(5000);
		
		uiDriver.ClickOnButtonorLink("SubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Cars_American");
		Thread.sleep(5000);
		
		return oppName+";"+probability;
		
	}
	
	public String fillOpportunitiesForm(String accountName, String amount, String closeDate, String stage, String billingType) throws Exception
	{
		String randNo = utilFuns.GenerateRandom(4, Mode.ALPHANUMERIC);
		String oppName = "OPP_"+randNo;
		String camp = "CAMP_"+randNo;;
		uiDriver.SetValueForTextBox("OpportunityName", oppName);
		uiDriver.SetValueForTextBox("CampaignInitiative", camp);
		uiDriver.ClickOnButtonorLink("SearchAccountName");
		Thread.sleep(5000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+accountName+"']")).click();
		Thread.sleep(5000);
		
		//uiDriver.ClickOnButtonorLink("OpportunityCurrency");
		//Thread.sleep(5000);
		//uiDriver.ClickOnButtonorLink("INR");
		//Thread.sleep(5000);
		
		uiDriver.SetValueForTextBox("Amount", amount);
		uiDriver.SetValueForTextBox("CampaignStartDate", "11/02/2021");
		uiDriver.SetValueForTextBox("CloseDate", closeDate);
		
		uiDriver.ClickOnButtonorLink("Stage");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink(stage);
		Thread.sleep(5000);
		String probability = uiDriver.ValuebyGetAttributeandStoreinVariable("Probability");
		
		if(!billingType.equalsIgnoreCase("NA"))
		{
			uiDriver.scrolltoViewElement("BillingType");
			uiDriver.ClickOnButtonorLink("BillingType");
			Thread.sleep(5000);
			uiDriver.ClickOnButtonorLink(billingType);
			Thread.sleep(5000);
		}
		
		uiDriver.scrolltoViewElement("Category");
		uiDriver.ClickOnButtonorLink("Category");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("AUTAutomotive");
		Thread.sleep(5000);
		
		uiDriver.ClickOnButtonorLink("SubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Cars_American");
		Thread.sleep(5000);
		
		return oppName+";"+probability;
		
	}
	
	public String fillOpportunitiesForm(String accountName, String amount, String closeDate, String stage, String billingType,String clientBrand) throws Exception
	{
		String randNo = utilFuns.GenerateRandom(4, Mode.ALPHANUMERIC);
		String oppName = "OPP_"+randNo;
		String camp = "CAMP_"+randNo;;
		uiDriver.SetValueForTextBox("OpportunityName", oppName);
		uiDriver.SetValueForTextBox("CampaignInitiative", camp);
		uiDriver.ClickOnButtonorLink("SearchAccountName");
		Thread.sleep(5000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+accountName+"']")).click();
		Thread.sleep(5000);
		
		//uiDriver.ClickOnButtonorLink("OpportunityCurrency");
		//Thread.sleep(5000);
		//uiDriver.ClickOnButtonorLink("INR");
		//Thread.sleep(5000);
		
		uiDriver.SetValueForTextBox("Amount", amount);
		uiDriver.SetValueForTextBox("CampaignStartDate", "11/12/2020");
		uiDriver.SetValueForTextBox("CloseDate", closeDate);
		
		uiDriver.ClickOnButtonorLink("Stage");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink(stage);
		Thread.sleep(5000);
		String probability = uiDriver.ValuebyGetAttributeandStoreinVariable("Probability");
		
		if(!billingType.equalsIgnoreCase("NA"))
		{
			uiDriver.scrolltoViewElement("BillingType");
			uiDriver.ClickOnButtonorLink("BillingType");
			Thread.sleep(5000);
			uiDriver.ClickOnButtonorLink(billingType);
			Thread.sleep(5000);
		}
		
		uiDriver.scrolltoViewElement("ClientBrand");
		uiDriver.ClickOnButtonorLink("ClientBrand");
		Thread.sleep(10000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+clientBrand+"']")).click();
		Thread.sleep(5000);
		
		uiDriver.scrolltoViewElement("Category");
		uiDriver.ClickOnButtonorLink("Category");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("AUTAutomotive");
		Thread.sleep(5000);
		
		uiDriver.ClickOnButtonorLink("SubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Cars_American");
		Thread.sleep(5000);
		
		return oppName+";"+probability;
		
	}
	
	public String fillOpportunitiesForm(String accountName, String amount, String closeDate, String stage, String billingType,String clientBrand,String clientProduct) throws Exception
	{
		String randNo = utilFuns.GenerateRandom(4, Mode.ALPHANUMERIC);
		String oppName = "OPP_"+randNo;
		String camp = "CAMP_"+randNo;;
		uiDriver.SetValueForTextBox("OpportunityName", oppName);
		uiDriver.SetValueForTextBox("CampaignInitiative", camp);
		uiDriver.ClickOnButtonorLink("SearchAccountName");
		Thread.sleep(5000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+accountName+"']")).click();
		Thread.sleep(5000);
		
		//uiDriver.ClickOnButtonorLink("OpportunityCurrency");
		//Thread.sleep(5000);
		//uiDriver.ClickOnButtonorLink("INR");
		//Thread.sleep(5000);
		
		uiDriver.SetValueForTextBox("Amount", amount);
		uiDriver.SetValueForTextBox("CampaignStartDate", "11/12/2020");
		uiDriver.SetValueForTextBox("CloseDate", closeDate);
		
		uiDriver.ClickOnButtonorLink("Stage");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink(stage);
		Thread.sleep(5000);
		String probability = uiDriver.ValuebyGetAttributeandStoreinVariable("Probability");
		
		if(!billingType.equalsIgnoreCase("NA"))
		{
			uiDriver.scrolltoViewElement("BillingType");
			uiDriver.ClickOnButtonorLink("BillingType");
			Thread.sleep(5000);
			uiDriver.ClickOnButtonorLink(billingType);
			Thread.sleep(5000);
		}
		
		uiDriver.scrolltoViewElement("ClientBrand");
		uiDriver.ClickOnButtonorLink("ClientBrand");
		Thread.sleep(10000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+clientBrand+"']")).click();
		Thread.sleep(5000);
		
		uiDriver.ClickOnButtonorLink("ClientProduct");
		Thread.sleep(10000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+clientProduct+"']")).click();
		Thread.sleep(5000);
		
		
		
		uiDriver.scrolltoViewElement("Category");
		uiDriver.ClickOnButtonorLink("Category");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("AUTAutomotive");
		Thread.sleep(5000);
		
		uiDriver.ClickOnButtonorLink("SubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Cars_American");
		Thread.sleep(5000);
		
		return oppName+";"+probability;
		
	}
	
	public void ChangeBillingtype(String billingType) throws URISyntaxException, Exception
	{
		uiDriver.scrolltoViewElement("BillingType");
		uiDriver.ClickOnButtonorLink("BillingType");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink(billingType);
		Thread.sleep(5000);
	}
	
	public String SelectStage() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("Stage");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("IdentifyingOpportunity");
		Thread.sleep(5000);
		String probability = uiDriver.GetTextandStoreinVariable("Probability");
		
		return probability;
	}
	
	public void EditOpprtunity() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("EditOpportunity");
		Thread.sleep(5000);
	}
	
	
	public void CancelOpportunitiesCreation() throws Exception
	{
		uiDriver.ClickOnButtonorLink("CancelOpportunityChanges");
		Thread.sleep(5000);
	}
	
	public String SaveOpportunitiesDetails() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("SaveOpportunitiesDetails");
		Thread.sleep(20000);
		uiDriver.WaitforVisiblilityofElement("Opportunity");
		String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
		reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
		Thread.sleep(2000);
		String oppname = uiDriver.GetTextandStoreinVariable("Opportunity");
		return oppname;
	}
	
	public void SaveQuoteLineItem() throws Exception
	{
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//button[@title='Save record and close the window']"))).click().build().perform();
		Thread.sleep(5000);
		String img1 = uiDriver.CaptureFullScreenShot("QuoteLineDetailsDetails");
		reporter.addScreenshotToReport(img1, "Quote Line Item Created/Modified Succesfully");
		Thread.sleep(2000);
		uiDriver.WaitforVisiblilityofElement("CreateNewineItem");
	}
	
	public void SaveQuoteLineItemToCheckMandatoryFields() throws Exception
	{
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//button[@title='Save record and close the window']"))).click().build().perform();
		Thread.sleep(5000);
		CloseErrorPopUp();
		CloseErrorPopUp();
		CloseErrorPopUp();
	}
	
	public void CloseErrorPopUp() throws Exception
	{
		String smessage;
		try {
			if(uiDriver.driver.findElements(By.xpath("(//div[text()='Error'])[1]")).size()>0)
			{
				String img1 = uiDriver.CaptureFullScreenShot("ErrorDetails");
				reporter.addScreenshotToReport(img1, "Error Pop Details");
				smessage = uiDriver.driver.findElement(By.xpath("(//div[text()='Error'])[1]/following-sibling::span")).getText();
				ReporterClass.logger.log(Status.PASS, smessage);
				uiDriver.driver.findElement(By.xpath("(//button[@title='Close'])[1]")).click();
				Thread.sleep(5000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Thread.sleep(1000);
		}
	}
	
	public void fillQuoteLiesDetailsAfterValidation(String market, String netprice) throws Exception
	{
		
        //No. of Columns
          List  col = uiDriver.driver.findElements(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/thead/tr/th"));
          System.out.println("Total No of columns are : " +col.size());
          //No.of rows
          List  rows = uiDriver.driver.findElements(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr/td[1]"));
          System.out.println("Total No of rows are : " + rows.size());
         
  		for(int i = 1;i<=rows.size();i++)
          {
  			 for(int j=1;j<=col.size();j++)
               {
	               	String sColValue = uiDriver.driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/thead/tr/th["+(j)+"]")).getText();
	               	System.out.println("sColValue "+sColValue);
	               	String smark = driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr["+(i)+"]/td["+(j)+"]")).getText();
               	    System.out.println("smark "+smark);
               		if(smark.equalsIgnoreCase(market))
               		{
               			Actions act1 = new Actions(uiDriver.driver);
	              		act1.moveToElement(driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr["+(i)+"]/td[3]"))).doubleClick().sendKeys(netprice).build().perform();
	              	    Thread.sleep(3000);
	              	    ReporterClass.logger.log(Status.PASS, "Successfully entered value Net Price" + netprice + " for "+smark);
               		}
               }
  			
          }
	}
	
	
	public void SaveandNew() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("SaveandNew");
		uiDriver.WaitforElementPresent("NewAccount_Next");
	}
	
	
	public void CreateQuotes() throws URISyntaxException, Exception
	{
		/*
		 * try { uiDriver.ClickOnButtonorLink("CreateQuotes"); } catch (Exception e) {
		 * JavascriptExecutor executor = (JavascriptExecutor) driver;
		 * executor.executeScript("arguments[0].click();", driver.findElement(By.
		 * xpath("//h1[@title='Opportunity quotes']/../../../following-sibling::div[2]/ul/li"
		 * )));
		 * 
		 * }
		 */
		uiDriver.screen.wait(Pattern.CreateQuotes, 60);
		uiDriver.screen.click(Pattern.CreateQuotes);
		uiDriver.WaitforVisiblilityofElement("CreateNewineItem");
		Thread.sleep(15000);
		reporter.logger.log(Status.PASS, "Successfully Clicked on Quote");
		Thread.sleep(3000);
		String img1 = uiDriver.CaptureFullScreenShot("Quote");
		reporter.addScreenshotToReport(img1, "Quote Created Succesfully");
		
	}
	
	
	public void DeleteQuotes() throws URISyntaxException, Exception
	{
	
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//span[text()='Delete']"))).click().build().perform();
	    Thread.sleep(5000);
	    Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(driver.findElement(By.xpath("//button[text()='Yes']"))).click().build().perform();
		Thread.sleep(20000);
		reporter.logger.log(Status.PASS, "Successfully Clicked on Quote");
		String img1 = uiDriver.CaptureFullScreenShot("Quote");
		reporter.addScreenshotToReport(img1, "Quote Created Succesfully");
	}
	
	public void CreateNewQuoteLineItem() throws Exception
	{
       JavascriptExecutor executor = (JavascriptExecutor) driver;
		
		try {
			//uiDriver.ClickOnButtonorLink("CreateNewineItem");
			Actions act =  new Actions(uiDriver.driver);
			act.moveToElement(driver.findElement(By.xpath("//button[@title='Create new line item']"))).click().build().perform();
		    Thread.sleep(5000);
		  } catch (Exception e) {
		     executor = (JavascriptExecutor) driver;
		     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Create new line item']")));
		     reporter.logger.log(Status.PASS, "Successfully Clicked on Create new line item for Quote");
		     
		  }
		Thread.sleep(10000);
	}
	
	public void EditQuoteLine(String name) throws Exception
	{
		try {
			uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']/preceding-sibling::td[1]/c-bgm-qli-actions/lightning-button-icon[3]/button")).click();
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Actions act =  new Actions(uiDriver.driver);
			act.moveToElement(uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']/preceding-sibling::td[1]/c-bgm-qli-actions/lightning-button-icon[3]/button"))).click().build().perform();
			Thread.sleep(10000);
		}
	}
	
	public void VerifyActionsforMasterQLI(String name) throws Exception
	{
		Thread.sleep(5000);
		
		if(uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']/preceding-sibling::td[1]/c-bgm-qli-actions/lightning-button-icon[1]/button")).isDisplayed())
		{		
			ReporterClass.logger.log(Status.PASS, "Copy option is Present for MasterQLI "+name);
		}
		else
		{
			ReporterClass.logger.log(Status.FAIL, "Copy Option is not Present for MasterQLI "+name);
		}
		
		if(uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']/preceding-sibling::td[1]/c-bgm-qli-actions/lightning-button-icon[2]/button")).isDisplayed())
		{		
			ReporterClass.logger.log(Status.PASS, "Information option is Present for MasterQLI "+name);
		}
		else
		{
			ReporterClass.logger.log(Status.FAIL, "Information Option is not Present for MasterQLI "+name);
		}
		
		if(uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']/preceding-sibling::td[1]/c-bgm-qli-actions/lightning-button-icon[3]/button")).isDisplayed())
		{		
			ReporterClass.logger.log(Status.PASS, "Edit option is Present for MasterQLI "+name);
		}
		else
		{
			ReporterClass.logger.log(Status.FAIL, "Edit Option is not Present for MasterQLI "+name);
		}
		
		if(uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']/preceding-sibling::td[1]/c-bgm-qli-actions/lightning-button-icon[4]/button")).isDisplayed())
		{		
			ReporterClass.logger.log(Status.PASS, "Delete option is Present for MasterQLI "+name);
		}
		else
		{
			ReporterClass.logger.log(Status.FAIL, "Delete Option is not Present for MasterQLI "+name);
		}
	
	}
	
	
	public void VerifyActionsforSubQLI(String name) throws Exception
	{
		Thread.sleep(5000);
		
		if(uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']/preceding-sibling::td[1]/c-bgm-qli-actions/lightning-button-icon[1]/button")).isDisplayed())
		{		
			ReporterClass.logger.log(Status.PASS, "Copy option is Present for SubQLI "+name);
		}
		else
		{
			ReporterClass.logger.log(Status.INFO, "Copy Option is not Present for SubQLI "+name);
		}
		
		
		try {
			uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']/preceding-sibling::td[1]/c-bgm-qli-actions/lightning-button-icon[2]/button")).isDisplayed();
			ReporterClass.logger.log(Status.FAIL, "Information option is Present for SubQLI "+name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReporterClass.logger.log(Status.INFO, "Information Option is not Present for SubQLI "+name);
		}
		
		
		try {
			uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']/preceding-sibling::td[1]/c-bgm-qli-actions/lightning-button-icon[3]/button")).isDisplayed();
			ReporterClass.logger.log(Status.FAIL, "Edit option is Present for SubQLI "+name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReporterClass.logger.log(Status.INFO, "Edit Option is not Present for SubQLI "+name);
		}
		

		try {
			uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']/preceding-sibling::td[1]/c-bgm-qli-actions/lightning-button-icon[4]/button")).isDisplayed();
			ReporterClass.logger.log(Status.FAIL, "Delete option is Present for SubQLI "+name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReporterClass.logger.log(Status.INFO, "Delete Option is not Present for SubQLI "+name);
		}
	
	}
	
	public void DeleteSubQLI(String name) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']/preceding-sibling::td[1]/c-bgm-qli-actions/lightning-button-icon/button")).click();
		Thread.sleep(5000);
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//button[text()='Yes']"))).click().build().perform();
		Thread.sleep(20000);
		uiDriver.WaitforElementPresent("CreateNewineItem");
		reporter.logger.log(Status.PASS, "Successfully Deleted QLI "+name);
	}
	
	public void DeleteMasterQLI(String name) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//td[text()='"+name+"']/preceding-sibling::td[1]/c-bgm-qli-actions/lightning-button-icon[4]/button")).click();
		Thread.sleep(5000);
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//button[text()='Yes']"))).click().build().perform();
		Thread.sleep(20000);
		uiDriver.WaitforElementPresent("CreateNewineItem");
		reporter.logger.log(Status.PASS, "Successfully Deleted QLI "+name);
	}
	
	
	
	public void CopyQuoteLine() throws Exception
	{
		uiDriver.screen.dragDrop(Pattern.CopyQuote, Pattern.Quotes);
		Thread.sleep(15000);
		String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
		reporter.addScreenshotToReport(img1, "Opportunity Cloned Succesfully");
		Thread.sleep(2000);
	}
	
	
	public void ChaangeNetPrice()
	{
		///
	}
	
	public void CreateQuoteLineItem() throws URISyntaxException, Exception
	{
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		
		try {
			uiDriver.ClickOnButtonorLink("CreateNewineItem");
		  } catch (Exception e) {
		     executor = (JavascriptExecutor) driver;
		     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Create new line item']")));
		     reporter.logger.log(Status.PASS, "Successfully Clicked on Create new line item for Quote");
		     
		  }
		Thread.sleep(10000);
		uiDriver.WaitforElementPresent("Budget");
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//label[text()='Budget']/following-sibling::div/div/input"))).click().build().perform();
	    Thread.sleep(3000);
		uiDriver.PerformKeyBoardOperations("DELETE");
		uiDriver.SetValueForTextBox("Budget", "5000");
		uiDriver.SetValueForTextBox("StartDate", "01/01/2021");
		uiDriver.SetValueForTextBox("EndDate", "31/03/2021");
		uiDriver.SetValueForList("Platform", LIST.ByVisibleText, "Digital");
		Thread.sleep(8000);
		try {
			uiDriver.ClickOnButtonorLink("SaveandClose");
		  } catch (Exception e) {
		     executor = (JavascriptExecutor) driver;
		     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save and Close']")));
		     reporter.logger.log(Status.PASS, "Successfully Clicked on Save and Close line item for Quote");
		  }
		Thread.sleep(10000);
		String img1 = uiDriver.CaptureFullScreenShot("QUiteLine");
		reporter.addScreenshotToReport(img1, "QUiteLine Created Succesfully");
	}
	
	public void CreateQuoteLineItem(String budget,String startdate, String endDate,String platform) throws URISyntaxException, Exception
	{
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		
		try {
			uiDriver.ClickOnButtonorLink("CreateNewineItem");
		  } catch (Exception e) {
		     executor = (JavascriptExecutor) driver;
		     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Create new line item']")));
		     reporter.logger.log(Status.PASS, "Successfully Clicked on Create new line item for Quote");
		     
		  }
		Thread.sleep(10000);
		uiDriver.WaitforElementPresent("Budget");
		Actions act = new Actions(uiDriver.driver);
		act.doubleClick(uiDriver.driver.findElement(By.xpath("//label[text()='Budget']/following-sibling::div/div/input"))).sendKeys(Keys.DELETE).build().perform();;
		Thread.sleep(1000);
		uiDriver.SetValueForTextBox("Budget", budget);
		uiDriver.SetValueForTextBox("StartDate", startdate);
		uiDriver.SetValueForTextBox("EndDate", endDate);
		uiDriver.SetValueForList("Platform", LIST.ByVisibleText, platform);
		Thread.sleep(10000);
		RefreshRevenueSection();
		fillRevenueLiesDetails(budget);
		try {
			uiDriver.ClickOnButtonorLink("SaveandClose");
		  } catch (Exception e) {
		     executor = (JavascriptExecutor) driver;
		     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save and Close']")));
		     reporter.logger.log(Status.PASS, "Successfully Clicked on Save and Close line item for Quote");
		  }
		Thread.sleep(10000);
		String img1 = uiDriver.CaptureFullScreenShot("QUiteLine");
		reporter.addScreenshotToReport(img1, "QUiteLine Created Succesfully");
	}
	
	
	
	public void FillQuoteLineItemFields(String budget, String startdate, String endDate, String platform) throws URISyntaxException, Exception
	{
		/*
		 * JavascriptExecutor executor = (JavascriptExecutor) driver; try {
		 * uiDriver.ClickOnButtonorLink("CreateNewineItem"); } catch (Exception e) {
		 * executor.executeScript("arguments[0].click();",
		 * driver.findElement(By.xpath("//button[@title='Create new line item']")));
		 * reporter.logger.log(Status.PASS,
		 * "Successfully Clicked on Create new line item for Quote");
		 * 
		 * }
		 */
		
		uiDriver.WaitforElementPresent("Budget");
		Actions act = new Actions(uiDriver.driver);
		act.doubleClick(uiDriver.driver.findElement(By.xpath("//label[text()='Budget']/following-sibling::div/div/input"))).sendKeys(Keys.DELETE).build().perform();;
		//uiDriver.driver.findElement(By.xpath("//label[text()='Budget']/following-sibling::div/div/input")).sendKeys(Keys.DELETE);
		Thread.sleep(1000);
		uiDriver.SetValueForTextBox("Budget", budget);
	    uiDriver.SetValueForTextBox("StartDate",startdate );
		uiDriver.SetValueForTextBox("EndDate", endDate);
		uiDriver.SetValueForList("Platform", LIST.ByVisibleText, platform);
		Thread.sleep(10000);
	}
	
	
	public void SelectAvailableMarkets(String market, String netprice, String amount) throws Exception
	{
		List<WebElement> val=uiDriver.driver.findElements(By.xpath("//span[text()='Available Market(s)']/following-sibling::div/ul/li"));
		System.out.println("Total Number of All Element = "  + val.size());
	
		for(int i=1;i<val.size();i++)
		{
			WebElement linkElement = uiDriver.driver.findElement(By.xpath("//span[text()='Available Market(s)']/following-sibling::div/ul/li["+i+"]/div/span/span"));
        	String sval = linkElement.getText().trim();
            if(sval.equalsIgnoreCase(market))
			 {
        		System.out.println("sval "+sval);
            	linkElement.click();
            	Thread.sleep(3000);
            	uiDriver.ClickOnButtonorLink("MoveselectiontoSelectedMarkets");
            	Thread.sleep(3000);
            	reporter.logger.log(Status.PASS, "Available Market(s) Lisbox Value "+sval+" is Selected");
            	fillQuoteLiesDetails(netprice);
                RefreshRevenueSection();
                fillRevenueLiesDetails(amount);	
              }
		 }
	}
	
	public void SelectAvailableMultipleMarkets(String markets, String netprices) throws Exception
	{
		List<WebElement> val=uiDriver.driver.findElements(By.xpath("//span[text()='Available Market(s)']/following-sibling::div/ul/li"));
		System.out.println("Total Number of All Element = "  + val.size());
		
		String[] market = markets.split(";");
		String[] netprice = netprices.split(";");
		
		for(int j=0;j<market.length;j++)
		{
			for(int i=1;i<=val.size();i++)
			{
				WebElement linkElement = uiDriver.driver.findElement(By.xpath("//span[text()='Available Market(s)']/following-sibling::div/ul/li["+i+"]/div/span/span"));
	        	String sval = linkElement.getText().trim();
	            if(sval.equalsIgnoreCase(market[j]))
				 {
	        		System.out.println("sval "+sval);
	            	linkElement.click();
	            	Thread.sleep(3000);
	            	uiDriver.ClickOnButtonorLink("MoveselectiontoSelectedMarkets");
	            	Thread.sleep(5000);
	            	reporter.logger.log(Status.PASS, "Available Market(s) Lisbox Value "+sval+" is Selected");
	            	break;
	              }
	            
			 }
			fillQuoteLiesDetails(market[j],netprice[j]);
		}

	}
	
	
	public void fillQuoteLiesDetails(String market, String netprice) throws Exception
	{
		
	  //uiDriver.driver.findElement(By.xpath("//a[text()='QUOTE LINES']")).click();
  	  Thread.sleep(10000);
  	  
        //No. of Columns
          List  col = uiDriver.driver.findElements(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/thead/tr/th"));
          System.out.println("Total No of columns are : " +col.size());
          //No.of rows
          List  rows = uiDriver.driver.findElements(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr/td[1]"));
          System.out.println("Total No of rows are : " + rows.size());
         
  		for(int i = 1;i<=rows.size();i++)
          {
  			 for(int j=1;j<=col.size();j++)
               {
	               	String sColValue = uiDriver.driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/thead/tr/th["+(j)+"]")).getText();
	               	System.out.println("sColValue "+sColValue);
	               	String smark = driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr["+(i)+"]/td["+(j)+"]")).getText();
               	    System.out.println("smark "+smark);
               		if(smark.equalsIgnoreCase(market))
               		{
               			Actions act1 = new Actions(uiDriver.driver);
	              		act1.moveToElement(driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr["+(i)+"]/td[3]"))).doubleClick().sendKeys(netprice).build().perform();
	              	    Thread.sleep(3000);
	              	    ReporterClass.logger.log(Status.PASS, "Successfully entered value Net Price" + netprice + " for "+smark);
               		}
               }
  			
          }
	}
	

	
	public void AddCostLinewithAmount(String market,String costType,String costReason,String amount) throws Exception
	{
		
	  //uiDriver.driver.findElement(By.xpath("//a[text()='QUOTE LINES']")).click();
  	  Thread.sleep(10000);
  	  
        //No. of Columns
          List  col = uiDriver.driver.findElements(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/thead/tr/th"));
          System.out.println("Total No of columns are : " +col.size());
          //No.of rows
          List  rows = uiDriver.driver.findElements(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr/td[1]"));
          System.out.println("Total No of rows are : " + rows.size());
         
  		for(int i = 1;i<=rows.size();i++)
          {
  			 for(int j=1;j<=col.size();j++)
               {
	               	String sColValue = uiDriver.driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/thead/tr/th["+(j)+"]")).getText();
	               	System.out.println("sColValue "+sColValue);
	               	String smark = driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr["+(i)+"]/td["+(j)+"]")).getText();
               	    System.out.println("smark "+smark);
               		if(smark.equalsIgnoreCase(market))
               		{
               			
	              	    Actions act1 = new Actions(uiDriver.driver);
	              		act1.moveToElement(driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr["+(i)+"]/td[1]/button[2]"))).click().build().perform();
	              	    Thread.sleep(5000);
	              	    
		              	uiDriver.ClickOnButtonorLink("CostType");
		          		Thread.sleep(5000);
		          		WebElement filteroption = uiDriver.driver.findElement(By.xpath("//span[@title='"+costType+"']"));
		          		Actions act =  new Actions(uiDriver.driver);
		          	    act.moveToElement(filteroption).click().build().perform();
		          		Thread.sleep(5000);
		          		ReporterClass.logger.log(Status.PASS, "Successfully Selected value " + costType + " for costType Filter ");
		          		Thread.sleep(3000);
		          		uiDriver.SetValueForTextBox("CostReason", costReason);
		          		WebElement amountbased = uiDriver.driver.findElement(By.xpath("//div[@data-id='inputAddCost']/div[5]/lightning-input/div/span/label/span[1]"));
		          		Actions act2 = new Actions(uiDriver.driver);
			          	act2.moveToElement(amountbased).click().build().perform();
			          	Thread.sleep(3000);
			          	Actions act3 = new Actions(uiDriver.driver);
		          		act3.doubleClick(uiDriver.driver.findElement(By.xpath("//div[@data-id='inputAddCost']/div[4]/input"))).sendKeys(Keys.BACK_SPACE).build().perform();
		          		Thread.sleep(1000);
		          		uiDriver.SetValueForTextBox("CostLineAmount", amount);
		          		uiDriver.ClickOnButtonorLink("AddCostLine");
		          		Thread.sleep(3000);
		          		Actions act4 = new Actions(uiDriver.driver);
		          		act4.moveToElement(uiDriver.driver.findElement(By.xpath("(//button[@title='Close window'])[1]"))).click().build().perform();
		          		Thread.sleep(5000);
               		}
               }
  			
          }
	}
	
	

	public void AddCostLinewithPercentage(String market,String costType,String costReason,String percentage) throws Exception
	{
		
	  //uiDriver.driver.findElement(By.xpath("//a[text()='QUOTE LINES']")).click();
  	  Thread.sleep(10000);
  	  
        //No. of Columns
          List  col = uiDriver.driver.findElements(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/thead/tr/th"));
          System.out.println("Total No of columns are : " +col.size());
          //No.of rows
          List  rows = uiDriver.driver.findElements(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr/td[1]"));
          System.out.println("Total No of rows are : " + rows.size());
         
  		for(int i = 1;i<=rows.size();i++)
          {
  			 for(int j=1;j<=col.size();j++)
               {
	               	String sColValue = uiDriver.driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/thead/tr/th["+(j)+"]")).getText();
	               	System.out.println("sColValue "+sColValue);
	               	String smark = driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr["+(i)+"]/td["+(j)+"]")).getText();
               	    System.out.println("smark "+smark);
               		if(smark.equalsIgnoreCase(market))
               		{
               			
	              	    Actions act1 = new Actions(uiDriver.driver);
	              		act1.moveToElement(driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr["+(i)+"]/td[1]/buttton[2]"))).click().build().perform();
	              	    Thread.sleep(5000);
	              	    
		              	uiDriver.ClickOnButtonorLink("CostType");
		          		Thread.sleep(5000);
		          		WebElement filteroption = uiDriver.driver.findElement(By.xpath("//span[@title='"+costType+"']"));
		          		Actions act =  new Actions(uiDriver.driver);
		          	    act.moveToElement(filteroption).click().build().perform();
		          		Thread.sleep(5000);
		          		ReporterClass.logger.log(Status.PASS, "Successfully Selected value " + costType + " for costType Filter ");
		          		Thread.sleep(3000);
		          		WebElement amountbased = uiDriver.driver.findElement(By.xpath("//div[@data-id='inputAddCost']/div[5]/lightning-input/div/span/label/span[1]"));
		          		Actions act2 = new Actions(uiDriver.driver);
			          	act2.moveToElement(amountbased).click().build().perform();
			          	Thread.sleep(3000);
			          	Actions act3 = new Actions(uiDriver.driver);
		          		act3.doubleClick(uiDriver.driver.findElement(By.xpath("//div[@data-id='inputAddCost']/div[3]/input"))).sendKeys(Keys.BACK_SPACE).build().perform();
		          		Thread.sleep(1000);
		          		uiDriver.SetValueForTextBox("CostLinePercentage", percentage);
		          		uiDriver.ClickOnButtonorLink("AddCostLine");
		          		Thread.sleep(3000);
		          		Actions act4 = new Actions(uiDriver.driver);
		          		act4.moveToElement(uiDriver.driver.findElement(By.xpath("(//button[@title='Close window'])[1]"))).click().build().perform();
		          		Thread.sleep(5000);
               		}
               }
  			
          }
	}
	


	
	public void fillQuoteLiesDetails(String netprice) throws Exception
	{
		
	  //uiDriver.driver.findElement(By.xpath("//a[text()='QUOTE LINES']")).click();
  	  Thread.sleep(10000);
  	  
        //No. of Columns
          List  col = uiDriver.driver.findElements(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/thead/tr/th"));
          System.out.println("Total No of columns are : " +col.size());
          //No.of rows
          List  rows = uiDriver.driver.findElements(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr/td[1]"));
          System.out.println("Total No of rows are : " + rows.size());
         
  		for(int i = 1;i<=rows.size();i++)
          {
  			 for(int j=1;j<=col.size();j++)
               {
	               	String sColValue = uiDriver.driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/thead/tr/th["+(j)+"]")).getText();
	               	System.out.println("sColValue "+sColValue);
	               	
	               	if(sColValue.equalsIgnoreCase("Net Price"))
	              	{
	               		Actions act1 = new Actions(uiDriver.driver);
	              		act1.moveToElement(driver.findElement(By.xpath("(//a[text()='QUOTE LINES']/../../../following-sibling::div)[1]/table/tbody/tr["+(i)+"]/td["+(j)+"]"))).doubleClick().sendKeys(netprice).build().perform();
	              	    Thread.sleep(3000);
	              	    ReporterClass.logger.log(Status.PASS, "Successfully entered value " + netprice + " for TextBox Net Price");
	              	}
               }
  			
          }
	}
	
	public void fillRevenueLiesDetails(String amount, String percentage) throws Exception
	{
		//utilFuns.calculatePercentage(amount, percentage);
        //No. of Columns
          List  col = uiDriver.driver.findElements(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[1]/table/thead/tr/th"));
          System.out.println("Total No of columns are : " +col.size());
          //No.of rows
          List  rows = uiDriver.driver.findElements(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[1]/table/tbody/tr/td[1]"));
          System.out.println("Total No of rows are : " + rows.size());
          
          amount = utilFuns.splitAmountBasedonQuater(amount, rows.size());
         
  		for(int i=1;i<=rows.size();i++)
          {
  			 for(int j=1;j<=col.size();j++)
               {
	               	String sColValue = uiDriver.driver.findElement(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[2]/table/thead/tr/th["+(j)+"]")).getText();
	               	System.out.println("sColValue "+sColValue);
	               	 	
	               	if(sColValue.equalsIgnoreCase("Amount"))
	              	{
	               		WebElement Element = driver.findElement(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[2]/table/tbody/tr["+(i)+"]/td["+(j)+"]"));
	               	    uiDriver.scrolltoViewElement(Element);
	               	    Thread.sleep(2000);
	               		Actions act1 = new Actions(uiDriver.driver);
	              		act1.moveToElement(driver.findElement(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[2]/table/tbody/tr["+(i)+"]/td["+(j)+"]"))).doubleClick().sendKeys(amount).build().perform();
	              	    Thread.sleep(3000);
	              	    ReporterClass.logger.log(Status.PASS, "Successfully entered value " + amount + " for TextBox Amount");
	              	}
               }
  			
          }
	}
	
	public void fillRevenueLiesDetailswithpercenage(String percentage) throws Exception
	{
		//utilFuns.calculatePercentage(amount, percentage);
        //No. of Columns
          List  col = uiDriver.driver.findElements(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[1]/table/thead/tr/th"));
          System.out.println("Total No of columns are : " +col.size());
          //No.of rows
          List  rows = uiDriver.driver.findElements(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[1]/table/tbody/tr/td[1]"));
          System.out.println("Total No of rows are : " + rows.size());
         
  		for(int i=1;i<=rows.size();i++)
          {
  			 for(int j=1;j<=col.size();j++)
               {
	               	String sColValue = uiDriver.driver.findElement(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[2]/table/thead/tr/th["+(j)+"]")).getText();
	               	System.out.println("sColValue "+sColValue);
	               	 	
	               	if(sColValue.equalsIgnoreCase("% Split"))
	              	{
	               		WebElement Element = driver.findElement(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[2]/table/tbody/tr["+(i)+"]/td["+(j)+"]"));
	               	    uiDriver.scrolltoViewElement(Element);
	               	    Thread.sleep(2000);
	               		Actions act1 = new Actions(uiDriver.driver);
	              		act1.moveToElement(driver.findElement(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[2]/table/tbody/tr["+(i)+"]/td["+(j)+"]"))).doubleClick().sendKeys(percentage).build().perform();
	              	    Thread.sleep(3000);
	              	    ReporterClass.logger.log(Status.PASS, "Successfully entered value " + percentage + " for TextBox Amount");
	              	}
               }
  			
          }
	}
	
	
	public void fillRevenueLiesDetails(String amount) throws Exception
	{
		Thread.sleep(5000);
        //No. of Columns
          List  col = uiDriver.driver.findElements(By.xpath("//a[text()='Revenue']/../../../following-sibling::div[2]/table/thead/tr/th"));
          System.out.println("Total No of columns are : " +col.size());
          //No.of rows
          List  rows = uiDriver.driver.findElements(By.xpath("//a[text()='Revenue']/../../../following-sibling::div[2]/table/tbody/tr[1]"));
          System.out.println("Total No of rows are : " + rows.size());
         
		 for(int j=1;j<=col.size();j++)
	       {
	           	String sColValue = uiDriver.driver.findElement(By.xpath("//a[text()='Revenue']/../../../following-sibling::div[2]/table/thead/tr/th["+(j)+"]")).getText();
	           	System.out.println("sColValue "+sColValue);
	           	 	
	           	if(sColValue.equalsIgnoreCase("Amount"))
	           	{
	           		Actions act1 = new Actions(uiDriver.driver);
	          		act1.moveToElement(driver.findElement(By.xpath("//a[text()='Revenue']/../../../following-sibling::div/table/tbody/tr[1]/td["+(j)+"]/div/div/div/input"))).doubleClick().sendKeys(amount).build().perform();
	          	    Thread.sleep(3000);
	          	    uiDriver.PerformKeyBoardOperations("TAB");
	          	    ReporterClass.logger.log(Status.PASS, "Successfully entered value " + amount + " for TextBox Amount");
	          	}
          }
	}
	
	
	public void editRevenueLiesDetails(String amount) throws Exception
	{
		Thread.sleep(5000);
		
		String[] samount = amount.split(";");
		int k=0;
        //No. of Columns
          List  col = uiDriver.driver.findElements(By.xpath("//a[text()='Revenue']/../../../following-sibling::div[2]/table/thead/tr/th"));
          System.out.println("Total No of columns are : " +col.size());
          //No.of rows
          List  rows = uiDriver.driver.findElements(By.xpath("//a[text()='Revenue']/../../../following-sibling::div[2]/table/tbody/tr"));
          System.out.println("Total No of rows are : " + rows.size());
          
          for(int i=1;i<rows.size();i++)
          {
        	  for(int j=1;j<=col.size();j++)
   	       {
   	           	String sColValue = uiDriver.driver.findElement(By.xpath("//a[text()='Revenue']/../../../following-sibling::div[2]/table/thead/tr/th["+(j)+"]")).getText();
   	           	System.out.println("sColValue "+sColValue);
   	           	 	
   	           	if(sColValue.equalsIgnoreCase("Amount"))
   	           	{
   	           		Actions act1 = new Actions(uiDriver.driver);
   	          		act1.moveToElement(driver.findElement(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div/table/tbody/tr["+(i)+"]/td["+(j)+"]/div/div/div/input)[2]"))).doubleClick().sendKeys(samount[k]).build().perform();
   	          	    Thread.sleep(3000);
   	          	    uiDriver.PerformKeyBoardOperations("TAB");
   	          	    ReporterClass.logger.log(Status.PASS, "Successfully entered value " + amount + " for TextBox Amount");
   	          	}
             }
        	  
        	  k = k+1;
          }
	}
	
	
	public void getRevenueLiesDetails() throws Exception
	{
		//utilFuns.calculatePercentage(amount, percentage);
        //No. of Columns
          List  col = uiDriver.driver.findElements(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[1]/table/thead/tr/th"));
          System.out.println("Total No of columns are : " +col.size());
          //No.of rows
          List  rows = uiDriver.driver.findElements(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[1]/table/tbody/tr/td[1]"));
          System.out.println("Total No of rows are : " + rows.size());
         
  		for(int i=1;i<=rows.size();i++)
          {
  			 for(int j=1;j<=col.size();j++)
               {
	               	String sColValue = uiDriver.driver.findElement(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[2]/table/thead/tr/th["+(j)+"]")).getText();
	               	if(sColValue.equalsIgnoreCase("Amount") || sColValue.equalsIgnoreCase("% SPLIT"))
		           	{
	               	 	String sRowValue = driver.findElement(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[2]/table/tbody/tr["+(i)+"]/td["+(j)+"]/div/div/div/input")).getAttribute("value");
		               	System.out.println("Value "+sColValue+" : "+sRowValue);
		           	}
	               	else
	               	{
	               	 	String sRowValue = driver.findElement(By.xpath("(//a[text()='Revenue']/../../../following-sibling::div)[2]/table/tbody/tr["+(i)+"]/td["+(j)+"]")).getText();
		               	System.out.println("Value "+sColValue+" : "+sRowValue);
	               	}
  			
                }
	       }
  		
	}
	
	
	public void clickQuoteLines() throws Exception
	{
		uiDriver.ClickOnButtonorLink("Revenue");
		Thread.sleep(3000);
	}
	
	
	public void RefreshRevenueSection() throws Exception
	{
		Actions act1 = new Actions(uiDriver.driver);
		act1.moveToElement(driver.findElement(By.xpath("//a[text()='Revenue']"))).click().build().perform();
		Thread.sleep(3000);
		act1.moveToElement(driver.findElement(By.xpath("//a[text()='Revenue']/following-sibling::span/span/button"))).click().build().perform();
		Thread.sleep(10000);
	}
	
	public void QuoteLinesSection() throws Exception
	{
		Actions act1 = new Actions(uiDriver.driver);
		act1.moveToElement(driver.findElement(By.xpath("//a[text()='QUOTE LINES']"))).click().build().perform();
		Thread.sleep(10000);
	}
	
	public void CloseQuoteLineItem() throws Exception
	{
		uiDriver.WaitforElementPresent("CloseQuoteLineItem");
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//button[@title='Close window']"))).click().build().perform();
	    Thread.sleep(5000);
	}
	
	
	public void EditQuote() throws Exception
	{
		Thread.sleep(5000);
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//button[@title='Edit quote']"))).click().build().perform();
	    Thread.sleep(5000);
	}
	
	public void SaveQuote() throws Exception
	{
		uiDriver.WaitforElementPresent("SaveQuote");
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("(//span[text()='Save'])[2]"))).click().build().perform();
	    Thread.sleep(5000);
	    String img1 = uiDriver.CaptureFullScreenShot("QuoteDetails");
		reporter.addScreenshotToReport(img1, "Quote Created/Modified Succesfully");
		Thread.sleep(10000);
	}
	
	public void CancelEditQuote() throws Exception
	{
		uiDriver.WaitforElementPresent("CancelQuoteChanges");
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("//button[@title='Cancel and lose all changes']"))).click().build().perform();
	    Thread.sleep(5000);
	}
	
	
	public void getValuesfromPlatformListBox() throws Exception
	{
		  String ListValues = null;
		  WebElement val=driver.findElement(By.xpath("//label[text()='Platform']/following-sibling::div/div/div/div/select"));
		  Select s=new Select(val);
		  List<WebElement> allvals=s.getOptions();
		 
		  for(int i=0;i<allvals.size();i++)
		  {
			   Thread.sleep(1000);
			   System.out.println("List: "+ allvals.get(i).getAttribute("value"));
			   if(ListValues==null)
			   {
				   ListValues = allvals.get(i).getAttribute("value");
			   }
			   else
			   {
				   ListValues = ListValues+";"+allvals.get(i).getAttribute("value");
			   }
		  }
		  
		  reporter.logger.log(Status.PASS, "Platform Lisbox Values "+ListValues); 
	}
	
	
	public void validateValuesinBrand() throws Exception
	{
		String ListValues = null;
		WebElement val=uiDriver.driver.findElement(By.xpath("//label[text()='Platform']/following-sibling::div/div/div/div/select"));
		WebElement val2=uiDriver.driver.findElement(By.xpath("//label[text()='Brand']/following-sibling::div/div/div/div/select"));
		Select s=new Select(val);
		List<WebElement> allvals=s.getOptions();
		
		Select s1=new Select(val2);
	    
		  for(int i=1;i<allvals.size();i++)
		  {
			   Thread.sleep(1000);
			   System.out.println("Platform: "+ allvals.get(i).getAttribute("value"));
			   s.selectByIndex(i);
			   Thread.sleep(10000);
			   List<WebElement> allvals1=s1.getOptions();
			   for(int j=1;j<allvals1.size();j++)
			   {
				   System.out.println("brand: "+ allvals1.get(j).getAttribute("value"));
				   if(ListValues==null)
				   {
					   ListValues = allvals1.get(j).getAttribute("value");
				   }
				   else
				   {
					   ListValues = ListValues+";"+allvals1.get(j).getAttribute("value");
				   } 
			   }
			  
			   reporter.logger.log(Status.PASS, "Platform Lisbox Value: "+allvals.get(i).getAttribute("value")+" Brand Lisbox Values "+ListValues);
			   ListValues = null;
		  }
		  
		 
	}
	
	
	public void ValidateAvaliableMarkets()
	{
		String ListValues = null;
		List<WebElement> val=uiDriver.driver.findElements(By.xpath("//span[text()='Available Market(s)']/following-sibling::div/ul/li"));
		System.out.println("Total Number of All Element on webpage = "  + val.size());
	
		for(int i=1;i<val.size();i++)
		{
			WebElement linkElement = uiDriver.driver.findElement(By.xpath("//span[text()='Available Market(s)']/following-sibling::div/ul/li[" + i + "]/div/span/span"));
            System.out.println(linkElement.getText());
            if(ListValues==null)
			 {
				ListValues = linkElement.getText();
			 }
			 else
			 {
				ListValues = ListValues+";"+linkElement.getText();
			 } 
		}
		
		reporter.logger.log(Status.PASS, "Available Market(s) Lisbox Values "+ListValues);
	}
	
	
	public void QuoteLineItemFieldsValidations(String budget, String startdate, String endDate, String platform, String markets, String netprices, String markets1, String netprices1) throws URISyntaxException, Exception
	{
		uiDriver.WaitforVisiblilityofElement("Budget");
		
		SaveQuoteLineItemToCheckMandatoryFields();
		Actions act = new Actions(uiDriver.driver);
		act.doubleClick(uiDriver.driver.findElement(By.xpath("//label[text()='Budget']/following-sibling::div/div/input"))).sendKeys(Keys.DELETE).build().perform();;
		//uiDriver.driver.findElement(By.xpath("//label[text()='Budget']/following-sibling::div/div/input")).sendKeys(Keys.DELETE);
		Thread.sleep(1000);
		uiDriver.SetValueForTextBox("Budget", budget);
		
		SaveQuoteLineItemToCheckMandatoryFields();
	    uiDriver.SetValueForTextBox("StartDate",startdate );
	    
	    SaveQuoteLineItemToCheckMandatoryFields();
		uiDriver.SetValueForTextBox("EndDate", endDate);
		
		SaveQuoteLineItemToCheckMandatoryFields();
		uiDriver.SetValueForList("Platform", LIST.ByVisibleText, platform);
		Thread.sleep(10000);
		
		
		List<WebElement> val=uiDriver.driver.findElements(By.xpath("//span[text()='Available Market(s)']/following-sibling::div/ul/li"));
		System.out.println("Total Number of All Element on webpage = "  + val.size());
		
		String[] market = markets.split(";");
		String[] netprice = netprices.split(";");
		
		for(int j=0;j<market.length;j++)
		{
			for(int i=1;i<=val.size();i++)
			{
				WebElement linkElement = uiDriver.driver.findElement(By.xpath("//span[text()='Available Market(s)']/following-sibling::div/ul/li["+i+"]/div/span/span"));
	        	String sval = linkElement.getText().trim();
	            if(sval.equalsIgnoreCase(market[j]))
				 {
	        		System.out.println("sval "+sval);
	            	linkElement.click();
	            	Thread.sleep(3000);
	            	uiDriver.ClickOnButtonorLink("MoveselectiontoSelectedMarkets");
	            	Thread.sleep(5000);
	            	reporter.logger.log(Status.PASS, "Available Market(s) Lisbox Value "+sval+" is Selected");
	            	break;
	              }
	            
			 }
			fillQuoteLiesDetails(market[j],netprice[j]);
		}
		
		SaveQuoteLineItemToCheckMandatoryFields();
		
		 market = markets1.split(";");
		 netprice = netprices1.split(";");
		 
		for(int j=0;j<market.length;j++)
		{
			fillQuoteLiesDetails(market[j],netprice[j]);
		}
			
	}
	
	public String getValuesfromStageListBox() throws Exception
	{
		  String ListValues = null;
		  WebElement val=driver.findElement(By.xpath("//span[text()='Stage']/../following-sibling::div/div/select"));
		  Select s=new Select(val);
		  List<WebElement> allvals=s.getOptions();
		 
		  for(int i=0;i<allvals.size();i++)
		  {
			   Thread.sleep(1000);
			   System.out.println("List: "+ allvals.get(i).getText());
			   if(ListValues==null)
			   {
				   ListValues = allvals.get(i).getText();
			   }
			   else
			   {
				   ListValues = ListValues+";"+allvals.get(i).getText();
			   }
		  }
		  
		  reporter.logger.log(Status.PASS, "Platform Lisbox Values "+ListValues);
		  return ListValues;
	}
	
	public void selectQLIStage(String stage) throws Exception
	{
		uiDriver.SetValueForList("QLStage", LIST.ByVisibleText, stage);
		Thread.sleep(10000);
	}
	
	
	public String SelectOpportunityStage(String stage) throws Exception
	{
		Thread.sleep(10000);
		if(uiDriver.driver.findElements(By.xpath("(//label[text()='Stage'])[2]/following-sibling::div/lightning-base-combobox/div/div/input")).size()>0)
		{
			uiDriver.ClickOnButtonorLink("EditStage");
		}
		else
		{
			uiDriver.driver.findElement(By.xpath("//label[text()='Stage']/following-sibling::div/lightning-base-combobox/div/div/input")).click();
		}
		
		Thread.sleep(10000);
		if(stage.equalsIgnoreCase("Identifying Opportunity"))
		{
			uiDriver.screen.click(Pattern.IdentifyingOpportunity);
		}
		else if(stage.equalsIgnoreCase("Shaping Proposal"))
		{
			uiDriver.screen.click(Pattern.ShapingProposal);
		}
		else if(stage.equalsIgnoreCase("Verbal Commitment"))
		{
			uiDriver.screen.click(Pattern.VerbalCommitment);
		}
		else if(stage.equalsIgnoreCase("Contracting"))
		{
			uiDriver.screen.click(Pattern.Contracting);
		}
		else if(stage.equalsIgnoreCase("Closed Won"))
		{
			uiDriver.screen.click(Pattern.ClosedWon);
		}
		else if(stage.equalsIgnoreCase("Closed Lost"))
		{
			uiDriver.screen.click(Pattern.ClosedLost);
			Thread.sleep(3000);
			uiDriver.ClickOnButtonorLink("LossReason");
			Thread.sleep(5000);
			uiDriver.ClickOnButtonorLink("BudgetCut");
			Thread.sleep(5000);
			uiDriver.SetValueForTextBox("Reason", "Budget Cut");
			
		}
		Thread.sleep(5000);
		String probability = uiDriver.ValuebyGetAttributeandStoreinVariable("Probability");
		return probability;
	}
	
	public String SelectOpportunityStage2(String stage) throws Exception
	{
		
		uiDriver.ClickOnButtonorLink("EditStage2");
		Thread.sleep(10000);
		if(stage.equalsIgnoreCase("Identifying Opportunity"))
		{
			uiDriver.screen.click(Pattern.IdentifyingOpportunity);
		}
		else if(stage.equalsIgnoreCase("Shaping Proposal"))
		{
			uiDriver.screen.click(Pattern.ShapingProposal);
		}
		else if(stage.equalsIgnoreCase("Verbal Commitment"))
		{
			uiDriver.screen.click(Pattern.VerbalCommitment);
		}
		else if(stage.equalsIgnoreCase("Contracting"))
		{
			uiDriver.screen.click(Pattern.Contracting);
		}
		else if(stage.equalsIgnoreCase("Closed Won"))
		{
			uiDriver.screen.click(Pattern.ClosedWon);
		}
		else if(stage.equalsIgnoreCase("Closed Lost"))
		{
			uiDriver.screen.click(Pattern.ClosedLost);
			Thread.sleep(3000);
			uiDriver.ClickOnButtonorLink("LossReason");
			Thread.sleep(5000);
			uiDriver.ClickOnButtonorLink("BudgetCut");
			Thread.sleep(5000);
			uiDriver.SetValueForTextBox("Reason", "Budget Cut");
			
		}
		Thread.sleep(5000);
		String probability = uiDriver.ValuebyGetAttributeandStoreinVariable("Probability");
		return probability;
	}
	
	public String SelectOpportunityStage3(String stage) throws Exception
	{
		Thread.sleep(10000);
		if(uiDriver.driver.findElements(By.xpath("(//label[text()='Stage'])[2]/following-sibling::div/lightning-base-combobox/div/div/input")).size()>0)
		{
			uiDriver.ClickOnButtonorLink("EditStage");
		}
		else
		{
			uiDriver.driver.findElement(By.xpath("//label[text()='Stage']/following-sibling::div/lightning-base-combobox/div/div/input")).click();
		}
		
		Thread.sleep(10000);
		if(stage.equalsIgnoreCase("Identifying Opportunity"))
		{
			uiDriver.driver.findElement(By.xpath("//*[@data-value='"+stage+"']/span[2]/span")).click();
		}
		else if(stage.equalsIgnoreCase("Shaping Proposal"))
		{
			uiDriver.driver.findElement(By.xpath("//*[@data-value='"+stage+"']/span[2]/span")).click();
		}
		else if(stage.equalsIgnoreCase("Verbal Commitment"))
		{
			uiDriver.driver.findElement(By.xpath("//*[@data-value='"+stage+"']/span[2]/span")).click();
		}
		else if(stage.equalsIgnoreCase("Contracting"))
		{
			uiDriver.driver.findElement(By.xpath("//*[@data-value='"+stage+"']/span[2]/span")).click();
		}
		else if(stage.equalsIgnoreCase("Closed Won"))
		{
			uiDriver.driver.findElement(By.xpath("//*[@data-value='"+stage+"']/span[2]/span")).click();
		}
		else if(stage.equalsIgnoreCase("Closed Lost"))
		{
			uiDriver.driver.findElement(By.xpath("//*[@data-value='"+stage+"']/span[2]/span")).click();
			Thread.sleep(3000);
			uiDriver.ClickOnButtonorLink("LossReason");
			Thread.sleep(5000);
			uiDriver.ClickOnButtonorLink("BudgetCut");
			Thread.sleep(5000);
			uiDriver.SetValueForTextBox("Reason", "Budget Cut");
			
		}
		Thread.sleep(5000);
		String probability = uiDriver.ValuebyGetAttributeandStoreinVariable("Probability");
		return probability;
	}
	
	public void SelectSearchOption(String SearchFilter, String sData) throws Exception
	{
		uiDriver.ClickOnButtonorLink(SearchFilter);
		Thread.sleep(5000);
		WebElement filteroption = uiDriver.driver.findElement(By.xpath("//span[@title='"+sData+"']"));
		Actions act =  new Actions(uiDriver.driver);
	    act.moveToElement(filteroption).click().build().perform();
		Thread.sleep(5000);
		ReporterClass.logger.log(Status.PASS, "Successfully Selected value " + sData + " for "+SearchFilter+" Seacrh Option ");
		Thread.sleep(3000);
		uiDriver.ClickOnButtonorLink("FilterSearch");
		Thread.sleep(10000);
	}
	
	
	public void ClearFilters() throws Exception
	{
		uiDriver.ClickOnButtonorLink("ClearFilter");
		Thread.sleep(10000);
	}
	
	
	public void MediaBrief() throws Exception
	{
		WebElement medibreif = uiDriver.driver.findElement(By.xpath("//a[text()='Media Brief']"));
		Actions act =  new Actions(uiDriver.driver);
	    act.moveToElement(medibreif).click().build().perform();
		Thread.sleep(5000);
		uiDriver.SetValueForTextBox("LocalMarketBriefingInformation", "Local Market Briefing Information");
	}
	
	
	public void TimeLine() throws Exception
	{
		WebElement timeline = uiDriver.driver.findElement(By.xpath("(//button[@title='Timeline'])[1]"));
		Actions act =  new Actions(uiDriver.driver);
	    act.moveToElement(timeline).click().build().perform();
		Thread.sleep(5000);
		String img1 = uiDriver.CaptureFullScreenShot("TimeLine");
		reporter.addScreenshotToReport(img1, "TimeLine");
		Thread.sleep(20000);
		WebElement medibreif = uiDriver.driver.findElement(By.xpath("(//span[text()='Close'])[3]"));
		Actions act1 =  new Actions(uiDriver.driver);
	    act1.moveToElement(medibreif).click().build().perform();
		Thread.sleep(10000);
	}
	

	public void clickonSalesForceUser(String sfuser) throws IOException, URISyntaxException, Exception
	{
		uiDriver.driver.findElement(By.xpath("//div[text()='"+sfuser+"']/../../..")).click();
		//uiDriver.WaitforElementPresent("LoginSalesForceUser");
		Thread.sleep(5000);
	}
	
	public void LogoutofSalesForceUser(String sfuser) throws IOException, URISyntaxException, Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[text()='Log out as "+sfuser+"']")).click();
		Thread.sleep(5000);
		uiDriver.WaitforVisiblilityofElement("SearchSetup");
	}
	
	public void LoginAsDifferentUsers(String sfUser) throws Exception
	{
        Thread.sleep(10000);
		uiDriver.ClickOnButtonorLink("SetupIcon");
		Thread.sleep(2000);
		uiDriver.screen.click(Pattern.SetUp);
		Thread.sleep(5000);
		uiDriver.switchtoNewWindow(MainWindowHandle);
		Thread.sleep(4000);
		uiDriver.ClickOnButtonorLink("SearchSetup");
		uiDriver.WaitforElementPresent("SearchSetup");
		uiDriver.SetValueForTextBox("SearchSetup", sfUser);
		Thread.sleep(10000);
		clickonSalesForceUser(sfUser);
		Thread.sleep(10000);
		uiDriver.switchtoFrameByIndex(0);
		uiDriver.ClickOnButtonorLink("LoginSalesForceUser");
		Thread.sleep(10000);
		uiDriver.switchbacktoPage();
	}
	
	public void LoginAsDifferentUsers2(String sfUser) throws Exception
	{
		Thread.sleep(4000);
		uiDriver.ClickOnButtonorLink("SearchSetup");
		uiDriver.WaitforElementPresent("SearchSetup");
		uiDriver.SetValueForTextBox("SearchSetup", sfUser);
		Thread.sleep(10000);
		clickonSalesForceUser(sfUser);
		Thread.sleep(10000);
		uiDriver.switchtoFrameByIndex(0);
		uiDriver.ClickOnButtonorLink("LoginSalesForceUser");
		Thread.sleep(10000);
		uiDriver.switchbacktoPage();
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
	
	
	public void SelectClientBrand(String cBrand) throws Exception
	{
		uiDriver.scrolltoViewElement("ClientBrand");
		uiDriver.ClickOnButtonorLink("ClientBrand");
		Thread.sleep(10000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+cBrand+"']")).click();
		Thread.sleep(5000);
	}
	
	public void SelectBillingType(String sbillingType) throws URISyntaxException, Exception
	{
		uiDriver.scrolltoViewElement("BillingType");
		uiDriver.ClickOnButtonorLink("BillingType");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink(sbillingType);
		Thread.sleep(5000);
	}
	
	public void ClickonDetails() throws Exception
	{
		Thread.sleep(9000);
		try {
			uiDriver.ClickOnButtonorLink("OpprtunityDetailsTab");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Actions act =  new Actions(uiDriver.driver);
			act.moveToElement(uiDriver.driver.findElement(By.xpath("//a[@id='detailTab__item']"))).click().build().perform();
			Thread.sleep(5000);
		}
		uiDriver.WaitforVisiblilityofElement("BrandsandProducts");
	}
	
	public void ClickonDetails2() throws Exception
	{
		Thread.sleep(15000);
		uiDriver.screen.click(Pattern.OpportunityDetailsTab);
		Thread.sleep(5000);
		uiDriver.WaitforVisiblilityofElement("BrandsandProducts");
	}
	
	public void ClickonPipelineDeliveryTab() throws Exception
	{
		uiDriver.screen.click(Pattern.PipelineDelivery);
		Thread.sleep(9000);
	}
	
	
	
	
	
}
