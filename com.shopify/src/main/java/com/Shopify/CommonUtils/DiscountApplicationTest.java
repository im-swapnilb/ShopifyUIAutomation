package com.Shopify.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DiscountApplicationTest {
	public WebDriver driver;
	public WebElement settings_btn;
	public WebElement createDiscounts_btn;
	public WebElement active_tab;
	public WebElement all_tab;
	
	

	public static CartPageDetalis testApp(String rule_Name, WebDriver driver, int quantity, String productArray[])
			throws InterruptedException, Exception {

		
		Properties Obj;
		Obj = ObjectRepo.getInstance();
		

		List<WebElement> prod_name = new ArrayList<WebElement>();
		List<WebElement> prod_price = new ArrayList<WebElement>();

		System.out.println("Testing rule name " + rule_Name);

//		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
//		driver = new ChromeDriver();
//		System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
//		driver = new FirefoxDriver();

		driver.manage().deleteAllCookies();
		Thread.sleep(10000);
		driver.get(Obj.getProperty("home_page"));

		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(5000);

		driver.findElement(By.linkText(Obj.getProperty("catalog_href"))).click();
		Thread.sleep(5000);

//		driver.findElement(By.xpath("//div[@id='Collection']/ul/li/div/div[contains(text(),'Laptop']/../a")).click();

		WebElement product1 = driver.findElement(By.xpath(Obj.getProperty("product_name1")));

		ProductSelector.selectProductFromCatalog(driver, product1, quantity);

		if (rule_Name.equals("Purchase with purchase") || rule_Name.equals("Upsell/Cross-sell")) {
			driver.navigate().back();
			Thread.sleep(6000);
			WebElement product2 = driver.findElement(By.xpath(Obj.getProperty("product_name2")));
			ProductSelector.selectProductFromCatalog(driver, product2, quantity+1);
		}

		Thread.sleep(10000);

		driver.findElement(By.xpath(Obj.getProperty("cart_popup_viewCart"))).click();
		Thread.sleep(5000);

		String cart_total_discount = driver.findElement(By.xpath(Obj.getProperty("cart_total_discount"))).getText()
				.trim();
		String cart_dis_rule_type_amount = driver.findElement(By.xpath(Obj.getProperty("cart_dis_rule_type_amount")))
				.getText().trim();
		String cart_dis_rule_type_name = driver.findElement(By.xpath(Obj.getProperty("cart_dis_rule_type_name")))
				.getText().trim();
		String cart_total_amount = driver.findElement(By.xpath(Obj.getProperty("cart_total_amount"))).getText().trim();
		String cart_subTotal_amount = driver.findElement(By.xpath(Obj.getProperty("cart_subTotal_amount"))).getText()
				.trim();

		String cart_product_name = driver.findElement(By.xpath(Obj.getProperty("cart_product_name"))).getText().trim();
		String cart_product_price = driver.findElement(By.xpath(Obj.getProperty("cart_product_price"))).getText()
				.trim();
		String cart_page_item_quantity = driver.findElement(By.xpath(Obj.getProperty("cart_page_item_quantity")))
				.getText().trim();
		String cart_product_total_price = driver.findElement(By.xpath(Obj.getProperty("cart_product_total_price")))
				.getText().trim();

		CartPageDetalis allAmount = new CartPageDetalis();
		allAmount.setDiscount_amt(cart_total_discount);
		allAmount.setSubtotal_amt(cart_subTotal_amount);
		allAmount.setTotal_amt(cart_total_amount);

		allAmount.setDiscount_name(cart_dis_rule_type_name);
		allAmount.setProduct_Name(cart_product_name);
		allAmount.setProduct_unit_price(cart_product_price);
		return allAmount;

	}

}
