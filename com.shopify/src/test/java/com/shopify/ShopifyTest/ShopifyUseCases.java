package com.shopify.ShopifyTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.Shopify.CommonUtils.ShopifyTestBase;
import com.Shopify.Pages.LoginPage;
import com.Shopify.Pages.Logout;
import jxl.read.biff.BiffException;


public class ShopifyUseCases extends ShopifyTestBase {
	LoginPage loginPage;
	Logout logoutPage;
	@Test(dataProvider= "flipcart")
	
	 public void Testcase(String TestCaseName,String UserName,String Password, String Browser,String Url,String Results) throws Exception{
		fn_LaunchBrowser(TestCaseName,Browser,Url);
		loginPage =new LoginPage(); 
		loginPage.flipLogin(UserName, Password, TestCaseName);
		logoutPage = new Logout();
		logoutPage.flipLogout(TestCaseName);
	}
	
	@AfterMethod 
	public void quit() throws InterruptedException{
		driver.close();
		// driver.quit();
	}
	
	@DataProvider(name="flipcart")
    public static Object[][] loginData() throws BiffException{
     
     Object[][] arrayObject=getExcelData("FlipExcel", "FlipSheet");
     return arrayObject;
     }	

}
