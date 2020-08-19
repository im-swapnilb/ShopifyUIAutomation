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


public class BulkTest {

	public String ruleName = "Bulk";
	public String custName = "Shubham";
	WebDriver driver = null;
	Properties Obj = null;
	public String productName = "Jeans";
	public String discount_mode = "Percentage";
	public String discount_mode_value = "20";
	public String bulk_quantity = "4";
	public String end_date = "2020-07-26";

	@BeforeTest
	public void setup() {
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

		Select s1 = new Select(driver.findElement(By.xpath(Obj.getProperty("buy_type_dd"))));
		s1.selectByVisibleText("Specific product");
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_product_input"))).sendKeys(productName);
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_first_product"))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_add_button"))).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath(Obj.getProperty("bulk_quantity"))).sendKeys(bulk_quantity);
		Thread.sleep(5000);
		Select s2 = new Select(driver.findElement(By.xpath(Obj.getProperty("discount_type_dd"))));
		s2.selectByVisibleText(discount_mode);
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("discount_value"))).sendKeys(discount_mode_value);
		Thread.sleep(5000);
		Select s4 = new Select(driver.findElement(By.xpath(Obj.getProperty("customer_eligible_dd"))));
		s4.selectByVisibleText("Everyone");
//		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("popup_search_cust_name_input"))).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("popup_search_cust_name_input"))).sendKeys(custName);
//		Thread.sleep(15000);
//		driver.findElement(By.xpath(Obj.getProperty("popup_search_first_cust_name"))).click();
//		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("popup_search_add_button"))).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath(Obj.getProperty("continue_btn"))).click();
		Thread.sleep(3000);

		driver.findElement(By.id(Obj.getProperty("launch_page_dis_name"))).sendKeys(ruleName + "Discount");
		driver.findElement(By.xpath(Obj.getProperty("launch_page_start_date_input"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("date_picker_start_today"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_page_end_date_input"))).click();
//		driver.findElement(By.xpath(Obj.getProperty("date_picker_end_today"))).click();
		RuleDatePicker.selectDate(driver, end_date);
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("launch_page_start_time"))).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText(Obj.getProperty("time_picker_start_today"))).click();
		Thread.sleep(5000);
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("launch_page_end_time"))).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText(Obj.getProperty("time_picker_end_today"))).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_button"))).click();

		Thread.sleep(6000);
		String productArray[] = new String[] { productName };
		CartPageDetalis cartPageDetalis = DiscountApplicationTest.testApp(ruleName, driver,
				Integer.parseInt(bulk_quantity), productArray);

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

		if (discount_mode.equals("Percentage")) {
			if (subtotal_amount
					- (subtotal_amount * (Double.parseDouble(discount_mode_value) / 100)) == total_amount_to_pay) {
				System.out.println(discount_mode + " " + ruleName + " is verified");
			}
		} else if (discount_mode.equals("Fixed amount")) {
			if (subtotal_amount - (num_discount_mode_value) == total_amount_to_pay) {
				System.out.println(discount_mode + " " + ruleName + " is verified");
			}
		}

		else if (discount_mode.equals("Fixed At Price")) {
			if (subtotal_amount == total_amount_to_pay) {
				System.out.println(discount_mode + " " + ruleName + " is verified");
			}
		}

	}

	@AfterTest
	public void cleanUp() {
		driver.quit();
	}

}
