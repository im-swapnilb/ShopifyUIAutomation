package com.Shopify.CommonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductSelector {

	static Properties Obj;
//	static String productName = null;
	static WebDriver driver = null;

	public static void selectProductFromCatalog(WebDriver driver, WebElement productName, int quantity)
			throws IOException, Exception {

		productName.click();
		Properties Obj = ObjectRepo.getInstance();

		for (int i = 0; i < quantity; i++) {

			driver.findElement(By.xpath(Obj.getProperty("add_to_cart_btn"))).click();
			Thread.sleep(5000);
		}
	}

}

/*
 * }
 * 
 * public static void selectProduct(WebDriver driver, String arr[]) throws
 * IOException, Exception { List<WebElement> prod_name1 = new
 * ArrayList<WebElement>(); List<WebElement> prod_name2 = new
 * ArrayList<WebElement>(); prod_name1 =
 * driver.findElements(By.xpath(Obj.getProperty("all_product_name")));
 * 
 * for (WebElement k : prod_name1) { if (k.getText() == arr[0]) { k.click();
 * System.out.println("Clicking on " + arr[0]); } else { break; } } //
 * Thread.sleep(15000); // driver.navigate().back(); // Thread.sleep(5000);
 * 
 * if (driver.findElements(By.xpath(Obj.getProperty("pagination_next_button"))).
 * size() > 0) {
 * driver.findElement(By.xpath(Obj.getProperty("pagination_next_button"))).click
 * (); Thread.sleep(5000); prod_name2 =
 * driver.findElements(By.xpath(Obj.getProperty("all_product_name"))); for
 * (WebElement k : prod_name2) {
 * 
 * if (k.getText().equals(arr[1])) { k.click();
 * System.out.println("Clicking on " + arr[1]); } else { break; } }
 * 
 * // List<WebElement> allProducts = new ArrayList<WebElement>(prod_name1); //
 * allProducts.addAll(prod_name2); // for (WebElement k : allProducts) { //
 * System.out.println(k.getText()); // }
 * 
 * driver.quit(); }
 * 
 * }
 */