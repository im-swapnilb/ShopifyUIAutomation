package com.Shopify.CommonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlashSaleTimePicker {

	public static void selectTime(WebDriver driver, String flashSaletime) throws IOException, Exception {

		Properties Obj = ObjectRepo.getInstance();
		String parseTime[] = flashSaletime.split(":");

		System.out.println(parseTime[0] + " " + parseTime[1] + " " + parseTime[2]);

		List<WebElement> hrs = new ArrayList<WebElement>();
		List<WebElement> min = new ArrayList<WebElement>();
		List<WebElement> sec = new ArrayList<WebElement>();

		hrs = driver.findElements(By.cssSelector(Obj.getProperty("flash_sale_time_hrs")));
		for (WebElement j : hrs) {
			System.out.println("hrs :" + j.getText());
			if (j.getText().equals(parseTime[0])) {
				System.out.println("trying to click");
				j.click();
			}
		}
		min = driver.findElements(By.cssSelector(Obj.getProperty("flash_sale_time_min")));
		for (WebElement k : min) {
			System.out.println("min :" + k.getText());
			
			if (k.getText().equals(parseTime[1])) {
				System.out.println("trying to click");
				k.click();
			}
		}
		sec = driver.findElements(By.cssSelector(Obj.getProperty("flash_sale_time_sec")));

		for (WebElement l : min) {
			System.out.println("sec :" + l.getText());
			if (l.getText().equals(parseTime[2])) {
				System.out.println("trying to click");
				l.click();
			}
		}
		

		hrs.clear();
		min.clear();
		sec.clear();
//		new WebDriverWait(driver, 5000)
//				.until(ExpectedConditions
//						.elementToBeClickable(driver.findElement(By.xpath(Obj.getProperty("flash_sale_time_ok_btn")))))
//				.click();
		;
//		driver.findElement(By.xpath(Obj.getProperty("flash_sale_time_ok_btn"))).click();

	}
}
