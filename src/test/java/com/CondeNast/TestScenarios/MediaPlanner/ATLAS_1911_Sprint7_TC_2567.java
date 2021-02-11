package com.CondeNast.TestScenarios.MediaPlanner;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_1911_Sprint7_TC_2567 extends BaseTest
{

	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	String oppdet;
	String AccountName="John Deo";
	//String AccountName="OnlineTimes";
	String snames;
	String sname;
	String OppName;
	int rowcount;
	String sfUser_RA = "Eleanor Matthews";
	String sfUser_SA = "Ariana Stachrowski";
	String sfUser_SL = "Antonia Wigan";
	String sfUser_MP = "Shuo Yu";
		
		//@Test
		public void VeirfyOpportunityClientBrand() throws Exception
		{
			sl.NavigateandLogin();
			opp.ClickonOpportunities();	
			opp.ClickonCreateNewOpportunities();
			opp.fillOpportunitiesForm(AccountName,"18000","31/01/2021","IdentifyingOpportunity","CentrallyBilled","USPolo","Shirt");
			OppName = opp.SaveOpportunitiesDetails();
			opp.ClickonDetails();
			Actions act =  new Actions(uiDriver.driver);
			act.moveToElement(driver.findElement(By.xpath("//span[text()='Other Information']"))).build().perform();
			Thread.sleep(5000);
			uiDriver.GetTextandStoreinVariable("ClientBrandinDetailsTab");
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(200);
			sl.Logout();
	    }
		
		

	    @Test
		public void VeirfyOpportunityClientBrand2() throws Exception
		{
			sl.NavigateandLogin();
			opp.LoginAsDifferentUsers(sfUser_MP);
			opp.ClickonOpportunities();	
			opp.ClickonCreateNewOpportunities();
			opp.fillOpportunitiesForm(AccountName,"18000","31/01/2021","IdentifyingOpportunity","CentrallyBilled","Tip &Top","Tip & top Matress");
			OppName = opp.SaveOpportunitiesDetails();
			opp.ClickonDetails();
			Actions act =  new Actions(uiDriver.driver);
			act.moveToElement(driver.findElement(By.xpath("//span[text()='Other Information']"))).build().perform();
			Thread.sleep(5000);
			uiDriver.GetTextandStoreinVariable("ClientBrandinDetailsTab");
			String img1 = uiDriver.CaptureFullScreenShot("OpportunitiesDetails");
			reporter.addScreenshotToReport(img1, "Opportunity Created/Modified Succesfully");
			Thread.sleep(200);
			opp.LogoutofSalesForceUser(sfUser_MP);
			Thread.sleep(5000);
	    }
}
