package com.CondeNast.TestScenarios;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.Contacts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_1360_Sprint7_2431 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	Contacts cont = new Contacts();
	Opportunities opp = new Opportunities();
	String ContName = "John Clan";
	String sfUser = "Ariana Stachrowski";
	
	@Test
	public void  CreateContact() throws Exception
	{
		
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser);
		cont.ClickonContacts();
		
		cont.SearchandClickContact(ContName);
		cont.EditContactDetails();
		
		uiDriver.scrolltoViewElement("InvoiceContact");
		uiDriver.ClickOnButtonorLink("InvoiceContact");
		Thread.sleep(500);
		cont.SaveContactDetails();
		if(uiDriver.driver.findElements(By.xpath("//button[@id='window']")).size()>0)
		{
			String img1 = uiDriver.CaptureFullScreenShot("ContactDetails");
			reporter.addScreenshotToReport(img1, "Invoice Contact");
			Thread.sleep(15000);
			uiDriver.ClickOnButtonorLink("CloseErrorDialog");
			Thread.sleep(2000);
		}
		
        cont.CancelContactCreation();
		
		opp.LogoutofSalesForceUser(sfUser);
		
	}
}
