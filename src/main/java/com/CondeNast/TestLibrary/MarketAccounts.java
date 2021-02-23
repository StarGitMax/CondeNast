package com.CondeNast.TestLibrary;

import java.net.URISyntaxException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;

import com.CondeNast.Core.UtilityFunctions.Mode;
import com.aventstack.extentreports.Status;

public class MarketAccounts extends BaseTest
{

	
	public void ClickonMarketAccounts() throws Exception
	{
		WebElement Opportunities = uiDriver.driver.findElement(By.xpath("//a[@title='Market Accounts']"));
		JavascriptExecutor executor = (JavascriptExecutor)uiDriver.driver;
		executor.executeScript("arguments[0].click();", Opportunities);
		Thread.sleep(5000);
		uiDriver.WaitforElementPresent("SearchinMarketAccounts");
	}
	
	
	public void CreateNewMarketAccount() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("CreateNewMarketAccount");
		Thread.sleep(5000);
		uiDriver.WaitforElementPresent("MarketAccountName");
	}
	
	
	public void ClickonExistingMarketAccounts(String mAccount) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+mAccount+"']")).click();
		Thread.sleep(15000);
		uiDriver.WaitforVisiblilityofElement("EditMarketAccount");
	}
	
	
	public void ClickonDetails() throws Exception
	{
		//uiDriver.ClickOnButtonorLink("MarketAccountDetails");
		uiDriver.screen.click(Pattern.MarketAccountDetailsTab);
		reporter.logger.log(Status.PASS, "Details Tab is Clicked");
		Thread.sleep(5000);
		uiDriver.WaitforVisiblilityofElement("AddressInformation");
	}
	
	
	public void SearchforMarketAccounts(String mAccount) throws Exception
	{
		uiDriver.SetValueForTextBox("SearchinMarketAccounts", mAccount);
		uiDriver.PerformKeyBoardOperations("ENTER");
		Thread.sleep(5000);
	}
	
	public void VerifyNewButtontoCreateMarketAccount()
	{
		if(uiDriver.driver.findElements(By.xpath("//a[@title='New']")).size()>0)
		{
			reporter.logger.log(Status.FAIL, "New button to Create Market Account Present");
		}
		else
		{
			reporter.logger.log(Status.PASS, "New button to Create Market Account is not Present");
		}
	}
	
	
	public void VerifyEditButtontoeditMarketAccount()
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
	
	
	public void VerifyDeleteOptionforMarketAccount(String sAccount) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+sAccount+"']/../../following-sibling::td/span/div")).click();
		Thread.sleep(9000);
		if(uiDriver.driver.findElements(By.xpath("//button[@title='Delete']")).size()>0)
		{
			reporter.logger.log(Status.FAIL, "Delete option for Market Account Present");
		}
		else
		{
			reporter.logger.log(Status.PASS, "Delete option for Market Account is not Present");
			String img1 = uiDriver.CaptureFullScreenShot("MarketAccount");
			reporter.addScreenshotToReport(img1, "Market Account Delete Option");
		}
	}
	
	public void VerifyMarketAccountStatusField() throws Exception
	{
		if(uiDriver.driver.findElements(By.xpath("//label[text()='Market Account Status']/following-sibling::div/lightning-base-combobox/div/div/input")).size()>0)
		{
			reporter.logger.log(Status.FAIL, "Market Account Status is Editable");
		}
		else
		{
			String img1 = uiDriver.CaptureFullScreenShot("MarketAccountDetails");
			reporter.addScreenshotToReport(img1, "Market Account Status not is Editable");
		}
	}
	
	public void EditMarketAccount2() throws Exception
	{
		Actions act =  new Actions(uiDriver.driver);
		act.moveToElement(driver.findElement(By.xpath("(//button[@name='Edit'])[3]"))).click().build().perform();
		Thread.sleep(5000);
		uiDriver.WaitforVisiblilityofElement("SaveEditMarketAccountDetails");
	}
	
	public void EditMarketAccount() throws Exception
	{
		uiDriver.screen.click(Pattern.MarketAccountEdit);
		Thread.sleep(5000);
		uiDriver.WaitforVisiblilityofElement("SaveEditMarketAccountDetails");
	}
	
	public void CancelEditChanges() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("CancelEditChanges");
		Thread.sleep(1500);
	}
	
	
	public String SaveMarketAcountDetails() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("SaveEditMarketAccountDetails");
		Thread.sleep(15000);
		String img1 = uiDriver.CaptureFullScreenShot("MarketAccountDetails");
		reporter.addScreenshotToReport(img1, "Market Account Created/Modified Succesfully");
		Thread.sleep(20000);
		String maname = uiDriver.GetTextandStoreinVariable("NameMarketAccount");
		return maname;
	}
	
	
	public void SaveMarketAcountDetailswithValidations() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("SaveEditMarketAccountDetails");
		Thread.sleep(5000);
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(1000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
	}
	
	public void ChangeCreditStatus(String creditStatus) throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("CreditStatus");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink(creditStatus);
		Thread.sleep(5000);
	}
	
	
	public void ChangeMarketAccountStatus(String mStatus) throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("MarketAccountStatus");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink(mStatus);
		Thread.sleep(5000);
	}
	
	
	public void EnterBillingDetails(String sData) throws Exception
	{
		uiDriver.SetValueForTextBox("BillingDetails", sData);
	}
	
	
	
	public void fillCategorySubCategoryDetails() throws URISyntaxException, Exception
	{
		uiDriver.ClickOnButtonorLink("MarketAccountCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("AUTAutomotive");
		Thread.sleep(10000);
		
		uiDriver.ClickOnButtonorLink("MarketAccountSubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Cars_Asian");
		Thread.sleep(5000);
	}
	
	
	
	public void fillaccountDetails(String accountName, String smarket, String sCurrency, String creditStatus) throws Exception
	{
		String randNo = utilFuns.GenerateRandom(4, Mode.ALPHANUMERIC);
		String marName = "MAR_"+randNo;
		uiDriver.WaitforElementPresent("MarketAccountName");
		uiDriver.SetValueForTextBox("MarketAccountName", marName);
		
		uiDriver.ClickOnButtonorLink("SearchAccount");
		Thread.sleep(10000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+accountName+"']")).click();
		Thread.sleep(5000);
		
		uiDriver.ClickOnButtonorLink("MarketName");
		Thread.sleep(5000);
		uiDriver.driver.findElement(By.xpath("//span[@title='"+smarket+"']")).click();
		Thread.sleep(5000);
		
		uiDriver.ClickOnButtonorLink("MarketName2");
		Thread.sleep(5000);
		WebElement market = uiDriver.driver.findElement(By.xpath("//*[@data-value='"+smarket+"']/span[2]/span"));
		uiDriver.scrolltoViewInsideElement(market);
		market.click();
		Thread.sleep(5000);
		
		SelectBillingCurrencies(sCurrency);
		
		uiDriver.ClickOnButtonorLink("MarketAccountStatus");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Requested");
		Thread.sleep(5000);
		
		uiDriver.ClickOnButtonorLink("CreditStatus");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink(creditStatus);
		Thread.sleep(5000);
		
		uiDriver.ClickOnButtonorLink("MarketAccountCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("AUTAutomotive");
		Thread.sleep(10000);
		
		uiDriver.scrolltoViewElement("MarketAccountSubCategory");
		uiDriver.ClickOnButtonorLink("MarketAccountSubCategory");
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("Cars_Asian");
		Thread.sleep(5000);
		
	}
	
	
	public void validatefields()
	{

		if(uiDriver.driver.findElements(By.xpath("//label[text()='Market Account Name (Local)']/following-sibling::div/input")).size()>0)
		{
			reporter.logger.log(Status.PASS, "Market Account Name (Local)  is Present");
		}
		else
		{
			reporter.logger.log(Status.FAIL, "Market Account Name (Local) is not Present");
		}
		
		
		if(uiDriver.driver.findElements(By.xpath("//label[text()='Company Identification Number']/following-sibling::div/input")).size()>0)
		{
			reporter.logger.log(Status.PASS, "Company Identification Number is Present");
		}
		else
		{
			reporter.logger.log(Status.FAIL, "Company Identification Number is not Present");
		} 
		

		if(uiDriver.driver.findElements(By.xpath("//span[text()='PO Number Required?']/../following-sibling::div/span/input")).size()>0)
		{
			reporter.logger.log(Status.PASS, "PO Number Required? is Present");
		}
		else
		{
			reporter.logger.log(Status.FAIL, "PO Number Required? is not Present");
		}
		
		if(uiDriver.driver.findElements(By.xpath("//label[text()='External UID']/following-sibling::div/input")).size()>0)
		{
			reporter.logger.log(Status.PASS, "External UID is Present");
		}
		else
		{
			reporter.logger.log(Status.FAIL, "External UID is not Present");
		}
		
		if(uiDriver.driver.findElements(By.xpath("//label[text()='VAT Code']/following-sibling::div/input")).size()>0)
		{
			reporter.logger.log(Status.PASS, "'VAT Code is Present");
		}
		else
		{
			reporter.logger.log(Status.FAIL, "'VAT Code is not Present");
		} 
	}
	
	public void validateBlockOrdersfield()
	{
		if(uiDriver.driver.findElements(By.xpath("//span[text()='Block Orders']/../following-sibling::div/span/input")).size()>0)
		{
			reporter.logger.log(Status.FAIL, "Block Orders Checkbox is Present");
		}
		else
		{
			reporter.logger.log(Status.PASS, "Block Orders Checkbox is not Present");
		}
		
	}
	
	
	
	public void SelectBillingCurrencies(String sCurrency) throws Exception
	{
		List<WebElement> val=uiDriver.driver.findElements(By.xpath("//div[text()='Billing Currencies']/following-sibling::div/div/div[3]/div/ul/li"));
		System.out.println("Total Number of All Element = "  + val.size());
	
		for(int i=1;i<=val.size();i++)
		{
			WebElement linkElement = uiDriver.driver.findElement(By.xpath("//div[text()='Billing Currencies']/following-sibling::div/div/div[3]/div/ul/li["+i+"]/div/span/span"));
        	String sval = linkElement.getText().trim();
        	System.out.println("Element = "  + sval);
            if(sval.equalsIgnoreCase(sCurrency))
			 {
        		System.out.println("sval "+sval);
            	linkElement.click();
            	Thread.sleep(3000);
            	uiDriver.ClickOnButtonorLink("MoveSelectedCurrencyToRight");
            	Thread.sleep(3000);
            	reporter.logger.log(Status.PASS, "Available Market(s) Lisbox Value "+sval+" is Selected");
              }
		 }
	}
	
	public void ValidateMarketAccountStatus() throws Exception
	{
		String sval1 = null;
		List<WebElement> val=uiDriver.driver.findElements(By.xpath("//div[text()='Billing Currencies']/following-sibling::div/div/div[3]/div/ul/li"));
		System.out.println("Total Number of All Element = "  + val.size());
	
		for(int i=1;i<=val.size();i++)
		{
			WebElement linkElement = uiDriver.driver.findElement(By.xpath("//div[text()='Billing Currencies']/following-sibling::div/div/div[3]/div/ul/li["+i+"]/div/span/span"));
        	String sval = linkElement.getText().trim();
        	if(sval1 == null)
        	{
        		sval1 = sval;
        	}
        	else
        	{
        		sval1 = sval1+";"+sval;
        	}
		 }
		
		System.out.println("Element = "  + sval1);
		reporter.logger.log(Status.PASS, "Market Account Status Lisbox Valuea "+sval1);
	}
	
	
	
	public void DeleteMarketAccount(String sAccount) throws Exception
	{
		uiDriver.driver.findElement(By.xpath("//a[@title='"+sAccount+"']/../../following-sibling::td/span/div")).click();
		Thread.sleep(5000);
		uiDriver.ClickOnButtonorLink("MarketAccountSubMenuDelete");
		Thread.sleep(5000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(driver.findElement(By.xpath("//button[@title='Delete']"))).click().build().perform();
		Thread.sleep(25000);
		reporter.logger.log(Status.PASS, "Successfully Clicked on Delete");
		String img1 = uiDriver.CaptureFullScreenShot("Quote");
		reporter.addScreenshotToReport(img1, "Account Deleted Succesfully");
	}
	
	public void SearchandClickMarketAccount(String mAccount) throws FindFailed, Exception
	{
		uiDriver.screen.paste(Pattern.SearchMarketAccounts, mAccount);
		Thread.sleep(15000);
		Actions act1 =  new Actions(uiDriver.driver);
		act1.moveToElement(uiDriver.driver.findElement(By.xpath("//span[@title='"+mAccount+"']"))).click().build().perform();
		Thread.sleep(15000);
	}
	
}
