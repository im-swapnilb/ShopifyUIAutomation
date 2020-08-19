package com.shopify.ShopifyTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SampleTest {

	WebDriver driver;
	Properties Obj;

	@BeforeTest
	public void beforeTest() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "/Shopify/com.shopify/src/main/resources/com/Shopify/Driver/chromedriver.exe");
		driver = new ChromeDriver();
		Obj = new Properties();
		FileInputStream objfile = new FileInputStream(new File(".//application.properties"));
		Obj.load(objfile);
		Thread.sleep(10000);
	}

	@Test
	public void f() throws InterruptedException {
		driver.get(Obj.getProperty("base_url"));
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

		driver.findElement(By.linkText(Obj.getProperty("createDiscounts_btn"))).click();
//		driver.findElement(By.linkText(Obj.getProperty("settings_btn")));
//		driver.findElement(By.id(Obj.getProperty("active")));
//		driver.findElement(By.id(Obj.getProperty("all")));
		
		driver.findElement(By.linkText("Rules Editor")).click();

	}

	@AfterTest
	public void afterTest() {
		System.out.println("Closing browser");
		driver.quit();
	}

}
