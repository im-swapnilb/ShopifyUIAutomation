package com.shopify.ShopifyTest;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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


public class GoalTest {

	public String ruleName = "Goal";
	public String custName = "Shubham";
	WebDriver driver = null;
	Properties Obj = null;
	public String productName1 = "Jeans";
//	public String productName2 = "Jeans";
	public String discount_mode = "Percentage";
	public String discount_mode_value = "20";
	public String customer_spend_amount = "1000";
	public String end_date = "2020-07-26";
	public String customer_eligible = "Everyone";
	public String applies_to_dd_value = "Specific product";
	public String quantity = "3";
	
	@BeforeTest
	public void setup() {
		System.out.println("hh");
	}

	@Test
	public void testBulk() throws Exception {
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

		Select s3 = new Select(driver.findElement(By.xpath(Obj.getProperty("discount_type_dd"))));
		s3.selectByVisibleText(discount_mode);
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("discount_value"))).sendKeys(discount_mode_value);
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("customer_spend"))).sendKeys(customer_spend_amount);

		Select s1 = new Select(driver.findElement(By.xpath(Obj.getProperty("applies_to_dd"))));
		s1.selectByVisibleText(applies_to_dd_value);
		if (!applies_to_dd_value.equals("Entire order")) {
			Thread.sleep(5000);
			driver.findElement(By.xpath(Obj.getProperty("popup_search_product_input"))).sendKeys(productName1);
			Thread.sleep(5000);
			driver.findElement(By.xpath(Obj.getProperty("popup_search_first_product"))).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath(Obj.getProperty("popup_search_add_button"))).click();
			Thread.sleep(5000);
		}

		Select s4 = new Select(driver.findElement(By.xpath(Obj.getProperty("customer_eligible_dd"))));
		s4.selectByVisibleText(customer_eligible);
		Thread.sleep(5000);
		if (!customer_eligible.equals("Everyone")) {
			driver.findElement(By.xpath(Obj.getProperty("popup_search_cust_name_input"))).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(Obj.getProperty("popup_search_cust_name_input"))).sendKeys(custName);
			Thread.sleep(15000);
			driver.findElement(By.xpath(Obj.getProperty("popup_search_first_cust_name"))).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath(Obj.getProperty("popup_search_add_button"))).click();
			Thread.sleep(5000);
		}
		driver.findElement(By.xpath(Obj.getProperty("continue_btn"))).click();
		Thread.sleep(3000);

		driver.findElement(By.id(Obj.getProperty("launch_page_dis_name"))).sendKeys(ruleName + "Discount");
		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_page_start_date_input"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("date_picker_start_today"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_page_end_date_input"))).click();
//		driver.findElement(By.xpath(Obj.getProperty("date_picker_end_today"))).click();
		RuleDatePicker.selectDate(driver, end_date);

		driver.findElement(By.xpath(Obj.getProperty("launch_page_start_time"))).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(Obj.getProperty("time_picker_start_today"))).click();

		Thread.sleep(5000);

		driver.findElement(By.xpath(Obj.getProperty("launch_page_end_time"))).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(Obj.getProperty("time_picker_end_today"))).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath(Obj.getProperty("launch_button"))).click();

		Thread.sleep(6000);
		String productArray[] = new String[] { productName1 };
		CartPageDetalis cartPageDetalis = DiscountApplicationTest.testApp(ruleName, driver, Integer.parseInt(quantity),
				productArray);

		System.out.println("Product Name : " + cartPageDetalis.getProduct_Name());
		System.out.println("Product Unit Price : " + cartPageDetalis.getProduct_unit_price());
		System.out.println("Applied discount amount : " + cartPageDetalis.getDiscount_amt());
		System.out.println("Subtotal amount : " + cartPageDetalis.getSubtotal_amt());
		System.out.println("Total amount to be paid : " + cartPageDetalis.getTotal_amt());

		String result_product_name = cartPageDetalis.getProduct_Name();
		Double product_unit_price = cartPageDetalis.getProduct_unit_price();
		Double applied_discount = cartPageDetalis.getDiscount_amt();
		Double subtotal_amount = cartPageDetalis.getSubtotal_amt();
		Double total_amount_to_pay = cartPageDetalis.getTotal_amt();
		Double num_discount_mode_value = Double.parseDouble(discount_mode_value);
	}

	@AfterTest
	public void cleanUp() {
		driver.quit();
	}

}
