package com.shopify.ShopifyTest;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Shopify.CommonUtils.FlashSaleTimePicker;
import com.Shopify.CommonUtils.ObjectRepo;


public class LimitedTimeTest {

	public String ruleName = "Limited Sale";
	public String custName = "Shubham";
	WebDriver driver = null;
	Properties Obj = null;
	public String end_date = "2020-07-26";
	public String productName1 = "Laptop";
	public String productName2 = "Jeans";
	public String customer_eligible_dd_value = "Everyone";
	public String applies_to_dd_value = "Entire order";
	public String discount_mode = "Percentage";
	// With fixed amount discount, At the price of one(lowest price item),At the
	// price of one(highest price item),Second one at half price(lowest price item)
	// Second one at half price(highest price item)

	public String discount_mode_value = "20";
//	public String get_quantity_value = "5";
//	public String buy_quantity_value = "10";
	public String customer_spend_amount = "1000";
	public String min_purchase_quantity = "5";
	public String max_purchase_quantity = "10";
	public String flash_sale_start_time = "08:11:31";
	public String flash_sale_end_time = "09:11:32";
	public String flash_sale_time_hrs = "12";
	public String flash_sale_time_min = "30";
	public String flash_sale_time_sec = "30";

	@BeforeTest
	public void setup() {
	}

	@Test
	public void testBulk() throws Exception {
		Obj = ObjectRepo.getInstance();
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
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

		Select s1 = new Select(driver.findElement(By.xpath(Obj.getProperty("limited_by_dd"))));
		s1.selectByVisibleText("Time (Flash Sale)");
		Thread.sleep(5000);
		Select s3 = new Select(driver.findElement(By.xpath(Obj.getProperty("discount_type_dd"))));
		s3.selectByVisibleText(discount_mode);
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("discount_value"))).sendKeys(discount_mode_value);
		Thread.sleep(5000);
//		System.out.println("dis applied");

		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_time_clear"))).click();

		driver.findElement(By.xpath(Obj.getProperty("flash_sale_start_time"))).click();
		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_time_clear"))).click();

		FlashSaleTimePicker.selectTime(driver, flash_sale_start_time);
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_start_time"))).sendKeys(Keys.ENTER);

//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_time_hrs")+"["+flash_sale_time_hrs+"]")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_time_min")+"["+flash_sale_time_min+"]")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_time_sec")+"["+flash_sale_time_sec+"]")).click();

//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_start_time"))).sendKeys("");
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_start_time"))).clear();
//		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_start_time"))).sendKeys(flash_sale_start_time);
//		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_start_time"))).sendKeys(Keys.ENTER);
//		Thread.sleep(5000);

		driver.findElement(By.xpath(Obj.getProperty("flash_sale_end_time"))).click();
//		Thread.sleep(5000);
		FlashSaleTimePicker.selectTime(driver, flash_sale_end_time);
		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_start_time"))).sendKeys("");
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_end_time"))).clear();
		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_time_hrs")+"["+flash_sale_time_hrs+"]")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_time_min")+"["+flash_sale_time_min+"]")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_time_sec")+"["+flash_sale_time_sec+"]")).click();
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_end_time"))).sendKeys(flash_end_start_time);
//		Thread.sleep(5000);
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_end_time"))).sendKeys(Keys.ENTER);
		Thread.sleep(5000);

		Select s2 = new Select(driver.findElement(By.xpath(Obj.getProperty("applies_to_dd"))));
		s2.selectByVisibleText(applies_to_dd_value);
		Thread.sleep(5000);
		if (!applies_to_dd_value.equals("Entire order")) {
			driver.findElement(By.xpath(Obj.getProperty("popup_search_product_input"))).sendKeys(productName1);
			Thread.sleep(5000);
			driver.findElement(By.xpath(Obj.getProperty("popup_search_first_product"))).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath(Obj.getProperty("popup_search_add_button"))).click();
			Thread.sleep(5000);
		}

		Select s4 = new Select(driver.findElement(By.xpath(Obj.getProperty("customer_eligible_dd"))));
		s4.selectByVisibleText(customer_eligible_dd_value);
		Thread.sleep(5000);
		if (!customer_eligible_dd_value.equals("Everyone")) {
			driver.findElement(By.xpath(Obj.getProperty("popup_search_cust_name_input"))).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(Obj.getProperty("popup_search_cust_name_input"))).sendKeys(custName);
			Thread.sleep(15000);
			driver.findElement(By.xpath(Obj.getProperty("popup_search_first_cust_name"))).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath(Obj.getProperty("popup_search_add_button"))).click();
			Thread.sleep(5000);
		}

//		driver.findElement(By.xpath(Obj.getProperty("continue_btn"))).click();
//		Thread.sleep(3000);
//
//		driver.findElement(By.id(Obj.getProperty("launch_page_dis_name"))).sendKeys(ruleName + "Discount");
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("launch_page_start_date_input"))).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("date_picker_start_today"))).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("launch_page_end_date_input"))).click();
////		driver.findElement(By.xpath(Obj.getProperty("date_picker_end_today"))).click();
//		RuleDatePicker.selectDate(driver, end_date);
//
//		driver.findElement(By.xpath(Obj.getProperty("launch_page_start_time"))).click();
//		Thread.sleep(3000);
//		driver.findElement(By.linkText(Obj.getProperty("time_picker_start_today"))).click();
//
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("launch_page_end_time"))).click();
//		Thread.sleep(3000);
//		driver.findElement(By.linkText(Obj.getProperty("time_picker_end_today"))).click();
//
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(Obj.getProperty("launch_button"))).click();
//
//		Thread.sleep(6000);

//		String productArray[] = new String[] { productName1, productName2 };
//		CartPageDetalis cartPageDetalis = DiscountApplicationTest.testApp(ruleName, driver,Integer.parseInt(quantity), productArray);
//
//		System.out.println("Product Name : " + cartPageDetalis.getProduct_Name());
//		System.out.println("Product Unit Price : " + cartPageDetalis.getProduct_unit_price());
//		System.out.println("Applied discount amount : " + cartPageDetalis.getDiscount_amt());
//		System.out.println("Subtotal amount : " + cartPageDetalis.getSubtotal_amt());
//		System.out.println("Total amount to be paid : " + cartPageDetalis.getTotal_amt());
//
//		String result_product_name = cartPageDetalis.getProduct_Name();
//		Double product_unit_price = cartPageDetalis.getProduct_unit_price();
//		Double applied_discount = cartPageDetalis.getDiscount_amt();
//		Double subtotal_amount = cartPageDetalis.getSubtotal_amt();
//		Double total_amount_to_pay = cartPageDetalis.getTotal_amt();
//		Double num_discount_mode_value = Double.parseDouble(discount_mode_value);
	}

	@AfterTest
	public void cleanUp() {
		driver.quit();
	}
}
