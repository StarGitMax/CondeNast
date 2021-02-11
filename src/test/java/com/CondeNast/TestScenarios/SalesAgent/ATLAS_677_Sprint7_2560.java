package com.CondeNast.TestScenarios.SalesAgent;

import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.ClientBrand;
import com.CondeNast.TestLibrary.ClientProducts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_677_Sprint7_2560 extends BaseTest
{
	SalesforceLogin sl = new SalesforceLogin();
	ClientBrand cb = new ClientBrand();
	ClientProducts cp = new ClientProducts();
	Opportunities opp = new Opportunities();
	String cbrandName;
	String cProductName;
	String sfUser_SA = "Ariana Stachrowski";
	
	@Test
	public void ValidateClientBrands() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SA);
		
		cb.ClickonClientBrands();
		cb.CreateNewClientBrand();
		cb.filldetails();
		cbrandName = cb.SaveClientBrandDetails();
		
		cb.ClickonClientBrands();
		cb.VerifyDeleteOptionforClientBrand(cbrandName);
		
		opp.LogoutofSalesForceUser(sfUser_SA);
	}
	
	@Test
	public void ValidateClientProducts() throws Exception
	{
		sl.NavigateandLogin();
		opp.LoginAsDifferentUsers(sfUser_SA);
		
		cp.ClickonClientProducts();
		cp.CreateNewClientProduct();
		cp.fillDetails(cbrandName_TC2560);
		cProductName = cp.SaveClientProductDetails();
	
		cp.ClickonClientProducts();
		cp.VerifyDeleteOptionforClientProduct(cProductName);
		
		opp.LogoutofSalesForceUser(sfUser_SA);
	}
}
