package com.CondeNast.TestScenarios.MediaPlanner;

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
	String cbrandName;
	String cProductName;
	String cbrandName2 = "Acer";
	String sfUser_RA = "Eleanor Matthews";
	String sfUser_SA = "Ariana Stachrowski";
	String sfUser_SL = "Antonia Wigan";
	String sfUser_MP = "Shuo Yu";
	

	//@Test
	public void ValidateClientBrandsandProducts() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_RA);
		
		cb.ClickonClientBrands();
		cb.CreateNewClientBrand();
		cb.filldetails();
		cbrandName = cb.SaveClientBrandDetails();
		
		cb.EditClientBrand();
		uiDriver.SetValueForTextBox("BrandDescription", "test");
		cb.SaveClientBrandDetails();
		
		cp.ClickonClientProducts();
		cp.CreateNewClientProduct();
		cp.fillDetails(cbrandName);
		cProductName = cp.SaveClientProductDetails();
		
		cp.EditClientProduct();
		uiDriver.SetValueForTextBox("ProductDescription", "test");
		cp.SaveClientProductDetails();
		
		cp.ClickonClientProducts();
		cp.VerifyDeleteOptionforClientProduct(cProductName);
		
		
		cb.ClickonClientBrands();
		cb.VerifyDeleteOptionforClientBrand(cbrandName);
		
		opp.LogoutofSalesForceUser(sfUser_RA);
		Thread.sleep(5000);
	}
	
	
	@Test
	public void ValidateClientBrandsandProducts2() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_MP);
		
		WebElement Opportunities = uiDriver.driver.findElement(By.xpath("//a[@title='Client Brands']/span"));
		JavascriptExecutor executor = (JavascriptExecutor)uiDriver.driver;
		executor.executeScript("arguments[0].click();", Opportunities);
		Thread.sleep(5000);
		cb.VerifyNewButtontoCreateClientBrand();
		
		cb.SearchandClickonClientBrand("Acer");
		cb.VerifyEditButtontoeditClientBrand();
		
		Opportunities = uiDriver.driver.findElement(By.xpath("//a[@title='Client Brands']/span"));
		executor.executeScript("arguments[0].click();", Opportunities);
		Thread.sleep(5000);
	    cb.VerifyDeleteOptionforClientBrand("Acer");
		
	    Opportunities = uiDriver.driver.findElement(By.xpath("//a[@title='Client Products']/span"));
		executor.executeScript("arguments[0].click();", Opportunities);
		Thread.sleep(5000);
		cp.VerifyNewButtontoCreateClientProduct();
		
		cp.SearchandClickonClientProduct("Tip & top Matress");
		cp.VerifyEditButtontoeditClientProduct();
		
		Opportunities = uiDriver.driver.findElement(By.xpath("//a[@title='Client Products']/span"));
		executor.executeScript("arguments[0].click();", Opportunities);
		Thread.sleep(5000);
		cp.VerifyDeleteOptionforClientProduct("Tip & top Matress");
		
		opp.LogoutofSalesForceUser(sfUser_MP);
		Thread.sleep(5000);
	}
	
	
}
