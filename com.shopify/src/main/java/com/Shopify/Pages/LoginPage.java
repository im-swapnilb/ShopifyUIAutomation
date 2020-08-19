package com.Shopify.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Shopify.CommonUtils.ShopifyTestBase;
import com.Shopify.Reports.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class LoginPage extends ShopifyTestBase {

	@FindBy(css="._2zrpKA")
	private WebElement user_Name;
	
	@FindBy(css="._2zrpKA._3v41xv")
	private WebElement user_password;
	
	@FindBy(css="body > div.mCRfo9 > div > div > div > div > div.Km0IJL.col.col-3-5 > div > form > div._1avdGP > button")
	private WebElement submit;
	
	@FindBy(css="#container > div > header > div._1tz-RS > div > div > div > div._1Wr4v5 > div:nth-child(1) > div > div > div > span > div")
	private WebElement my_Account;
	
	 public LoginPage() {
		PageFactory.initElements(driver,this);
	}

	 @Test
	public void flipLogin() throws Exception{
		 System.out.println("HI you are in login page");
//			System.setProperty("webdriver.chrome.driver", "/Shopify/com.shopify/src/main/resources/com/Shopify/Driver/chromedriver.exe");
//		WebDriver driver= new ChromeDriver();
	//	 driver.get("https://www.google.com/");
		 Thread.sleep(5000);  // Let the user actually see something!
	//	    driver.quit();
		 //String username , String password, String TestCaseName
//		user_Name.click();
//		user_Name.sendKeys(username);
//		Log.info("Username is entered for test case " + TestCaseName);
//		user_password.click();
//		user_password.sendKeys(password);
//		Log.info("Password is entered for test case " + TestCaseName);
//		submit.click();
//		assertElement(my_Account, TestCaseName);
//		Log.info("User is logged in for testcase " +TestCaseName);
	}
}
