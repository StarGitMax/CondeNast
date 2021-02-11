package com.CondeNast.TestScenarios;

import org.testng.annotations.Test;

import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_610_Sprint3_TC_99_100  extends BaseTest
{

	SalesforceLogin sl = new SalesforceLogin();
	Opportunities opp = new Opportunities();
	String sfUser_SA = "Ariana Stachrowski";
	
	@Test
	public void VerifyFields_StageandLost() throws Exception
	{
		
		sl.NavigateandLogin();
		 opp.LoginAsDifferentUsers(sfUser_SA);
		 
		opp.ClickonOpportunities();
		opp.ClickonCreateNewOpportunities();
		
		
		uiDriver.ClickOnButtonorLink("Stage");
		Thread.sleep(5000);
		uiDriver.IsElementPresent("ClosedWon");
		uiDriver.ClickOnButtonorLink("ClosedWon");
		Thread.sleep(3000);
		
		uiDriver.ClickOnButtonorLink("Stage");
		Thread.sleep(5000);
		uiDriver.IsElementPresent("ClosedLost");
		uiDriver.ClickOnButtonorLink("ClosedLost");
		Thread.sleep(3000);
		
		
		uiDriver.ClickOnButtonorLink("LossReason");
		Thread.sleep(5000);
		uiDriver.IsElementPresent("BudgetCut");
		uiDriver.ClickOnButtonorLink("BudgetCut");
		Thread.sleep(3000);
		
		uiDriver.ClickOnButtonorLink("LossReason");
		Thread.sleep(5000);
		uiDriver.IsElementPresent("ChangeinStrategy");
		uiDriver.ClickOnButtonorLink("ChangeinStrategy");
		Thread.sleep(3000);
		
		uiDriver.ClickOnButtonorLink("LossReason");
		Thread.sleep(5000);
		uiDriver.IsElementPresent("Price");
		uiDriver.ClickOnButtonorLink("Price");
		Thread.sleep(3000);

		uiDriver.ClickOnButtonorLink("LossReason");
		Thread.sleep(5000);
		uiDriver.IsElementPresent("WentwithAnotherPartner");
		uiDriver.ClickOnButtonorLink("WentwithAnotherPartner");
		Thread.sleep(3000);
		
		uiDriver.ClickOnButtonorLink("LossReason");
		Thread.sleep(5000);
		uiDriver.IsElementPresent("DuplicateRecord");
		uiDriver.ClickOnButtonorLink("DuplicateRecord");
		Thread.sleep(3000);
		
		uiDriver.ClickOnButtonorLink("LossReason");
		Thread.sleep(5000);
		uiDriver.IsElementPresent("MovedtoOtherBrand");
		uiDriver.ClickOnButtonorLink("MovedtoOtherBrand");
		Thread.sleep(3000);
		
		uiDriver.ClickOnButtonorLink("LossReason");
		Thread.sleep(5000);
		uiDriver.IsElementPresent("CustomerNameUpdate");
		uiDriver.ClickOnButtonorLink("CustomerNameUpdate");
		Thread.sleep(3000);
		
		uiDriver.ClickOnButtonorLink("LossReason");
		Thread.sleep(5000);
		uiDriver.IsElementPresent("Upgraded");
		uiDriver.ClickOnButtonorLink("Upgraded");
		Thread.sleep(3000);
		
		uiDriver.ClickOnButtonorLink("LossReason");
		Thread.sleep(5000);
		uiDriver.IsElementPresent("COVID-19");
		uiDriver.ClickOnButtonorLink("COVID-19");
		Thread.sleep(3000);
		
		uiDriver.ClickOnButtonorLink("LossReason");
		Thread.sleep(5000);
		uiDriver.IsElementPresent("Other");
		uiDriver.ClickOnButtonorLink("Other");
		Thread.sleep(3000);
		
		opp.CancelOpportunitiesCreation();
		
		opp.LogoutofSalesForceUser(sfUser_SA);
		Thread.sleep(5000);
		
	}
	
	 
}
