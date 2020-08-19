package com.shopify.ShopifyTest;

import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Shopify.CommonUtils.ObjectRepo;
import com.Shopify.CommonUtils.ShopifyTestBase;
import com.Shopify.Pages.LoginPage;
import com.Shopify.Pages.Logout;
import jxl.read.biff.BiffException;
import com.Shopify.CommonUtils.ObjectRepo;



public class ShopifyUseCases extends ShopifyTestBase {
	LoginPage loginPage;
	Logout logoutPage;

	@Test // (dataProvider= "shopify")
	
	 public void Testcase() throws Exception{
		Properties Obj = ObjectRepo.getInstance();
	//	driver.get(Obj.getProperty("base_url"));
		System.out.println(Obj.getProperty("base_url"));
		String browser = Obj.getProperty("browser");
		String url = Obj.getProperty("base_url");
		System.out.println(browser+ "   "+ url);
		fn_LaunchBrowser("TC01",browser,url);
		loginPage =new LoginPage(); 
		loginPage.flipLogin();
	//	logoutPage = new Logout();
	//	logoutPage.flipLogout(TestCaseName);
	}
	
	@AfterMethod 
	public void quit() throws InterruptedException{
	//	driver.close();
		 driver.quit();
	}
	
//	@DataProvider(name="shopify")
//    public static Object[][] loginData() throws BiffException{
//     
//     Object[][] arrayObject=getExcelData("shopify", "shopify");
//     return arrayObject;
//     }	

}
