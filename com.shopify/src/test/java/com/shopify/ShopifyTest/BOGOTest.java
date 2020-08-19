package com.shopify.ShopifyTest;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.Shopify.CommonUtils.CartPageDetalis;
import com.Shopify.CommonUtils.DiscountApplicationTest;
import com.Shopify.CommonUtils.ObjectRepo;
import com.Shopify.CommonUtils.RuleDatePicker;


public class BOGOTest {

	public String ruleName = "BOGO";
	public String custName = "Shubham";
	WebDriver driver = null;
	Properties Obj = null;
	public String productName1 = "Jeans";
	public String productName2 = "Jeans";
	public String discount_mode = "Fixed amount";
	public String discount_mode1 = "Fixed amount";
	public String discount_mode_value = "500";
	public String discount_mode_value1 = "10";
//	public String discount_amount = "500";
	public String end_date = "2020-07-25";
	public String buyQuantity = "1";
	public String buyQuantity1 = "2";
	public String getQuantity = "1";
	public String productArray[];
	
	
	@BeforeMethod
	public void setup() {

	}

	@Test (priority = 0)
	public void TestFreeShipping() throws Exception {
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
		s.selectByVisibleText("BOGO");

		driver.findElement(By.xpath(Obj.getProperty("rule_name"))).sendKeys(ruleName + (int)(Math.random() * (100 - 10 + 1) + 10));

		Select s1 = new Select(driver.findElement(By.xpath(Obj.getProperty("buy_type_dd"))));
		s1.selectByVisibleText("Specific product");

		driver.findElement(By.xpath(Obj.getProperty("popup_search_product_input"))).sendKeys(productName1);
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_first_product"))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_add_button"))).click();

		Thread.sleep(5000);
		Select s2 = new Select(driver.findElement(By.xpath(Obj.getProperty("customer_getType_dd"))));
		s2.selectByVisibleText("Specific product");
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_product_input"))).sendKeys(productName2);
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_first_product"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_add_button"))).click();
		Thread.sleep(3000);

		System.out.println("Yet to provide quantity");
		driver.findElement(By.xpath(Obj.getProperty("buy_quantity"))).sendKeys(buyQuantity1);
		driver.findElement(By.xpath(Obj.getProperty("get_quantity"))).sendKeys(getQuantity);

		Select s3 = new Select(driver.findElement(By.xpath(Obj.getProperty("discount_type_dd"))));
		s3.selectByVisibleText(discount_mode1);
		driver.findElement(By.xpath(Obj.getProperty("discount_value"))).sendKeys(discount_mode_value1);
//		driver.findElement(By.xpath(Obj.getProperty("limit_per_user_chkBox"))).click();
		driver.findElement(By.xpath(Obj.getProperty("continue_btn"))).click();
		Thread.sleep(3000);

		driver.findElement(By.id(Obj.getProperty("launch_page_dis_name"))).sendKeys(ruleName + (int)(Math.random() * (100 - 10 + 1) + 10));
		driver.findElement(By.xpath(Obj.getProperty("launch_page_start_date_input"))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("date_picker_start_today"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_page_end_date_input"))).click();
		Thread.sleep(5000);

		RuleDatePicker.selectDate(driver, end_date);

//		driver.findElement(By.xpath(Obj.getProperty("date_picker_end_today"))).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath(Obj.getProperty("launch_page_start_time"))).click();
		Thread.sleep(5000);

		driver.findElement(By.linkText((Obj.getProperty("time_picker_start_today")))).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_page_end_time"))).click();
		Thread.sleep(5000);

		driver.findElement(By.linkText((Obj.getProperty("time_picker_end_today")))).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_button"))).click();

		Thread.sleep(6000);
		Thread.sleep(10000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		
		productArray = new String[] {productName1,productName2};
		
		CartPageDetalis cartPageDetalis = DiscountApplicationTest.testApp(ruleName, driver, (Integer.parseInt(buyQuantity)+Integer.parseInt(getQuantity)), productArray);

		System.out.println("Product Name : "+cartPageDetalis.getProduct_Name());
		System.out.println("Product Unit Price : "+cartPageDetalis.getProduct_unit_price());
		System.out.println("Applied discount amount : "+cartPageDetalis.getDiscount_amt());
		System.out.println("Subtotal amount : "+cartPageDetalis.getSubtotal_amt());
		System.out.println("Total amount to be paid : "+cartPageDetalis.getTotal_amt());
		
		
		String result_product_name = cartPageDetalis.getProduct_Name();
		Double product_unit_price = cartPageDetalis.getProduct_unit_price();
		Double applied_discount = cartPageDetalis.getDiscount_amt();
		Double subtotal_amount = cartPageDetalis.getSubtotal_amt();
		Double total_amount_to_pay = cartPageDetalis.getTotal_amt();
		
		Double num_discount_mode_value = Double.parseDouble(discount_mode_value);
		
		if(discount_mode.equals("Percentage")) {
			if(subtotal_amount -(product_unit_price * Double.parseDouble(getQuantity) * (num_discount_mode_value/100.00))==total_amount_to_pay)
			{
				System.out.println(discount_mode+" "+ruleName+" is verified");
			}
		}
		else if(discount_mode.equals("Fixed amount")) {
			if(subtotal_amount -(num_discount_mode_value)==total_amount_to_pay) {
				System.out.println(discount_mode+" "+ruleName+" is verified");
			}
		}
		
		
	}
	
	@Test (priority = 1)
	public void TestBogo() throws Exception {
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
		s.selectByVisibleText("BOGO");

		driver.findElement(By.xpath(Obj.getProperty("rule_name"))).sendKeys(ruleName + (int)(Math.random() * (100 - 10 + 1) + 10));

		Select s1 = new Select(driver.findElement(By.xpath(Obj.getProperty("buy_type_dd"))));
		s1.selectByVisibleText("Specific product");

		driver.findElement(By.xpath(Obj.getProperty("popup_search_product_input"))).sendKeys(productName1);
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_first_product"))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_add_button"))).click();

		Thread.sleep(5000);
		Select s2 = new Select(driver.findElement(By.xpath(Obj.getProperty("customer_getType_dd"))));
		s2.selectByVisibleText("Specific product");
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_product_input"))).sendKeys(productName2);
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_first_product"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("popup_search_add_button"))).click();
		Thread.sleep(3000);

		System.out.println("Yet to provide quantity");
		driver.findElement(By.xpath(Obj.getProperty("buy_quantity"))).sendKeys(buyQuantity);
		driver.findElement(By.xpath(Obj.getProperty("get_quantity"))).sendKeys(getQuantity);

		Select s3 = new Select(driver.findElement(By.xpath(Obj.getProperty("discount_type_dd"))));
		s3.selectByVisibleText(discount_mode);
		driver.findElement(By.xpath(Obj.getProperty("discount_value"))).sendKeys(discount_mode_value);
//		driver.findElement(By.xpath(Obj.getProperty("limit_per_user_chkBox"))).click();
		driver.findElement(By.xpath(Obj.getProperty("continue_btn"))).click();
		Thread.sleep(3000);

		driver.findElement(By.id(Obj.getProperty("launch_page_dis_name"))).sendKeys(ruleName + (int)(Math.random() * (100 - 10 + 1) + 10));
		driver.findElement(By.xpath(Obj.getProperty("launch_page_start_date_input"))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(Obj.getProperty("date_picker_start_today"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_page_end_date_input"))).click();
		Thread.sleep(5000);

		RuleDatePicker.selectDate(driver, end_date);

//		driver.findElement(By.xpath(Obj.getProperty("date_picker_end_today"))).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath(Obj.getProperty("launch_page_start_time"))).click();
		Thread.sleep(5000);

		driver.findElement(By.linkText((Obj.getProperty("time_picker_start_today")))).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_page_end_time"))).click();
		Thread.sleep(5000);

		driver.findElement(By.linkText((Obj.getProperty("time_picker_end_today")))).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath(Obj.getProperty("launch_button"))).click();

		Thread.sleep(6000);
		Thread.sleep(10000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		
		productArray = new String[] {productName1,productName2};
		
		CartPageDetalis cartPageDetalis = DiscountApplicationTest.testApp(ruleName, driver, (Integer.parseInt(buyQuantity)+Integer.parseInt(getQuantity)), productArray);

		System.out.println("Product Name : "+cartPageDetalis.getProduct_Name());
		System.out.println("Product Unit Price : "+cartPageDetalis.getProduct_unit_price());
		System.out.println("Applied discount amount : "+cartPageDetalis.getDiscount_amt());
		System.out.println("Subtotal amount : "+cartPageDetalis.getSubtotal_amt());
		System.out.println("Total amount to be paid : "+cartPageDetalis.getTotal_amt());
		
		
		String result_product_name = cartPageDetalis.getProduct_Name();
		Double product_unit_price = cartPageDetalis.getProduct_unit_price();
		Double applied_discount = cartPageDetalis.getDiscount_amt();
		Double subtotal_amount = cartPageDetalis.getSubtotal_amt();
		Double total_amount_to_pay = cartPageDetalis.getTotal_amt();
		
		Double num_discount_mode_value = Double.parseDouble(discount_mode_value);
		
		if(discount_mode.equals("Percentage")) {
			if(subtotal_amount -(product_unit_price * Double.parseDouble(getQuantity) * (num_discount_mode_value/100.00))==total_amount_to_pay)
			{
				System.out.println(discount_mode+" "+ruleName+" is verified");
			}
		}
		else if(discount_mode.equals("Fixed amount")) {
			if(subtotal_amount -(num_discount_mode_value)==total_amount_to_pay) {
				System.out.println(discount_mode+" "+ruleName+" is verified");
			}
		}
		
		
	}

	@AfterMethod
	public void closer() {
		driver.quit();

	}
}
