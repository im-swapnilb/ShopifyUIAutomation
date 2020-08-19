package com.shopify.ShopifyTest;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Shopify.CommonUtils.CartPageDetalis;
import com.Shopify.CommonUtils.DiscountApplicationTest;
import com.Shopify.CommonUtils.ObjectRepo;
import com.Shopify.CommonUtils.RuleDatePicker;


public class DiscountTest {
	public String ruleName = "Discount";
	public String custName = "Shubham";
	public String endDate = "2020-07-23";
	WebDriver driver = null;
	Properties Obj = null;
	public String discount_amount = "500";
	public String discount_percentage = "50";
	public String productName = "Laptop";
	public int quantity = 1;

	@BeforeTest
	public void setup() {
//		System.out.println("hh");
	}

	@Test
	public void TestDiscount() throws Exception {
		Obj = ObjectRepo.getInstance();
		System.setProperty("webdriver.chrome.driver", "/Shopify/com.shopify/src/main/resources/com/Shopify/Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Obj.getProperty("base_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);

		Thread.sleep(5000);
		driver.findElement(By.linkText(Obj.getProperty("createDiscounts_btn"))).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(Obj.getProperty("rules_editor"))).click();
		Thread.sleep(3000);
		Select s = new Select(driver.findElement(By.xpath(Obj.getProperty("rule_types_dd"))));
		s.selectByVisibleText(ruleName);

		driver.findElement(By.xpath(Obj.getProperty("rule_name"))).sendKeys(ruleName + "Test");

		Select s1 = new Select(driver.findElement(By.xpath(Obj.getProperty("discount_type_dd"))));
		s1.selectByVisibleText("Percentage");
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("discount_value"))).sendKeys(discount_percentage);
		Thread.sleep(5000);

		Select s2 = new Select(driver.findElement(By.xpath(Obj.getProperty("applies_to_dd"))));
		s2.selectByVisibleText("Entire order");
		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("popup_search_product_input"))).sendKeys(productName);
//		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("popup_search_first_product"))).click();
//		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("popup_search_add_button"))).click();
//		Thread.sleep(5000);

		Select s3 = new Select(driver.findElement(By.xpath(Obj.getProperty("min_req_dd"))));
		s3.selectByVisibleText("None");
		Thread.sleep(5000);

//		driver.findElement(By.xpath(Obj.getProperty("min_req_value"))).sendKeys("1000");
		Thread.sleep(5000);
		Select s4 = new Select(driver.findElement(By.xpath(Obj.getProperty("customer_eligible_dd"))));
		s4.selectByVisibleText("Everyone");
		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("popup_search_cust_name_input"))).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("popup_search_cust_name_input"))).sendKeys(custName);
//		Thread.sleep(15000);
//		driver.findElement(By.xpath(Obj.getProperty("popup_search_first_cust_name"))).click();
//		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("popup_search_add_button"))).click();
		Thread.sleep(5000);

//		driver.findElement(By.xpath(Obj.getProperty("limit_per_user_chkBox"))).click();
//
//		Thread.sleep(5000);

		driver.findElement(By.xpath(Obj.getProperty("continue_btn"))).click();
		Thread.sleep(3000);

		driver.findElement(By.id(Obj.getProperty("launch_page_dis_name"))).sendKeys(ruleName + "Discount");
		driver.findElement(By.xpath(Obj.getProperty("launch_page_start_date_input"))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("date_picker_start_today"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_page_end_date_input"))).click();
//		driver.findElement(By.xpath(Obj.getProperty("date_picker_end_today"))).click(); 	//for testing using xpath
		Thread.sleep(3000);
		RuleDatePicker.selectDate(driver, endDate);
		Thread.sleep(3000);

//		driver.findElement(By.xpath("//table/tbody/tr/td[@title='2020-07-31']/div")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath(Obj.getProperty("launch_page_start_time"))).click();
//		driver.findElement(By.xpath(Obj.getProperty("time_picker_start_today"))).click();
		driver.findElement(By.linkText(Obj.getProperty("time_picker_start_today"))).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_page_end_time"))).click();
//		driver.findElement(By.xpath(Obj.getProperty("time_picker_end_today"))).click();
		driver.findElement(By.linkText((Obj.getProperty("time_picker_end_today")))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_button"))).click();

		Thread.sleep(10000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");

		String productArray[] = new String[] { productName };

		CartPageDetalis cartPageDetalis = DiscountApplicationTest.testApp(ruleName, driver, quantity, productArray);

		System.out.println(cartPageDetalis.getProduct_Name());
		System.out.println(cartPageDetalis.getDiscount_amt());
		System.out.println(cartPageDetalis.getSubtotal_amt());
		System.out.println(cartPageDetalis.getTotal_amt());

		if (cartPageDetalis.getSubtotal_amt() * (Double.parseDouble(discount_percentage) / 100) == cartPageDetalis
				.getTotal_amt()) {
			System.out.println(ruleName + "Verified successfully");
		}
//		System.out.println(cartPageDetalis.getSubtotal_amt());
//				System.out.println(Double.parseDouble(discount_percentage)/100.00);

	}

	@AfterTest
	public void closer() {
		driver.quit();

	}
}
