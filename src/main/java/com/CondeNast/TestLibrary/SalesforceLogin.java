package com.CondeNast.TestLibrary;

import java.net.URISyntaxException;

public class SalesforceLogin extends BaseTest
{
	public void NavigateandLogin() throws Exception
	{
		if(instance.equalsIgnoreCase("uat"))
		{
			uiDriver.navitagetoURL(utilFuns.loadProps().get("UATURL").toString());
		    uiDriver.SetValueForTextBox("UserName",utilFuns.loadProps().get("userName_UAT").toString());
		    uiDriver.SetValueForTextBox("Password",utilFuns.loadProps().get("passsword_UAT").toString());
		}
		else if(instance.equalsIgnoreCase("sit"))
		{
			uiDriver.navitagetoURL(utilFuns.loadProps().get("SITURL").toString());
		    uiDriver.SetValueForTextBox("UserName",utilFuns.loadProps().get("userName_SIT").toString());
		    uiDriver.SetValueForTextBox("Password",utilFuns.loadProps().get("passsword_SIT").toString());
		}
	    uiDriver.ClickOnButtonorLink("Login"); 
	    uiDriver.WaitforElementPresent("Accounts");
	    //Thread.sleep(10000);
	}
	
	
	public void Logout() throws URISyntaxException, Exception
	{
		uiDriver.hoverWebelementandClick("User");
		uiDriver.WaitforElementPresent("LogOut");
		uiDriver.ClickOnButtonorLink("LogOut"); 
		Thread.sleep(15000);
	}
}
