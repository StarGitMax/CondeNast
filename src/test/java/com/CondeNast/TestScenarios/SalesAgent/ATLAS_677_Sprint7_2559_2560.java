package com.CondeNast.TestScenarios.SalesAgent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.ClientBrand;
import com.CondeNast.TestLibrary.ClientProducts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_677_Sprint7_2559_2560 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	ClientBrand cb = new ClientBrand();
	ClientProducts cp = new ClientProducts();
	Opportunities opp = new Opportunities();
	String sfUser_SA = "Ariana Stachrowski";
	

	@Test
	public void ValidateClientBrandsandProducts2() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SA);
		
		WebElement Opportunities = uiDriver.driver.findElement(By.xpath("//a[@title='Client Brands']/span"));
		JavascriptExecutor executor = (JavascriptExecutor)uiDriver.driver;
		executor.executeScript("arguments[0].click();", Opportunities);
		Thread.sleep(5000);
		cb.VerifyNewButtontoCreateClientBrand();
		
		cb.SearchandClickonClientBrand(cbrandName_TC2559_2560);
		cb.VerifyEditButtontoeditClientBrand();
		
		Opportunities = uiDriver.driver.findElement(By.xpath("//a[@title='Client Brands']/span"));
		executor.executeScript("arguments[0].click();", Opportunities);
		Thread.sleep(5000);
	    cb.VerifyDeleteOptionforClientBrand(cbrandName_TC2559_2560);
		
	    Opportunities = uiDriver.driver.findElement(By.xpath("//a[@title='Client Products']/span"));
		executor.executeScript("arguments[0].click();", Opportunities);
		Thread.sleep(5000);
		cp.VerifyNewButtontoCreateClientProduct();
		
		cp.SearchandClickonClientProduct(cProductName_TC2559_2560);
		cp.VerifyEditButtontoeditClientProduct();
		
		Opportunities = uiDriver.driver.findElement(By.xpath("//a[@title='Client Products']/span"));
		executor.executeScript("arguments[0].click();", Opportunities);
		Thread.sleep(5000);
		cp.VerifyDeleteOptionforClientProduct(cProductName_TC2559_2560);
		
		opp.LogoutofSalesForceUser(sfUser_SA);
		Thread.sleep(5000);
	}
	
	
}
