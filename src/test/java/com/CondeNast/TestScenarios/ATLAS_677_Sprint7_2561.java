package com.CondeNast.TestScenarios;

import org.testng.annotations.Test;
import com.CondeNast.TestLibrary.BaseTest;
import com.CondeNast.TestLibrary.ClientBrand;
import com.CondeNast.TestLibrary.ClientProducts;
import com.CondeNast.TestLibrary.Opportunities;
import com.CondeNast.TestLibrary.SalesforceLogin;

public class ATLAS_677_Sprint7_2561 extends BaseTest
{

	SalesforceLogin sl = new SalesforceLogin();
	ClientBrand cb = new ClientBrand();
	ClientProducts cp = new ClientProducts();
	Opportunities opp = new Opportunities();
	String cbrandName;
	String cProductName;

	
	@Test
	public void ValidateClientBrandsandProducts() throws Exception
	{
		sl.NavigateandLogin();
		
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
		cp.DeleteClientBrand(cProductName);
		
		
		cb.ClickonClientBrands();
		cb.DeleteClientBrand(cbrandName);
		
		sl.Logout();
	}
	
}
