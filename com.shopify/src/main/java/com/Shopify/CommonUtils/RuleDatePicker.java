package com.Shopify.CommonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RuleDatePicker {

	public static Properties Obj;

	/*
	 * public static void main(String[] args) throws IOException, Exception {
	 * System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	 * WebDriver driver = new ChromeDriver();
	 * driver.get("https://discount.appsdart.com/home?store_id=10");
	 * Thread.sleep(5000); driver.findElement(By.id("270")).click();
	 * Thread.sleep(4000);
	 * driver.findElement(By.xpath(Obj.getProperty("continue_btn"))).click();
	 * Thread.sleep(3000);
	 * 
	 * driver.findElement(By.xpath(Obj.getProperty("launch_page_end_date_input"))).
	 * click(); Thread.sleep(3000); RuleDatePicker.selectDate(driver, "2020-07-23");
	 * 
	 * }
	 */

	public static void selectDate(WebDriver driver, String date) throws IOException, Exception {
		List<WebElement> activeDates = new ArrayList<WebElement>();
		String[] dateParse = date.split("-");
		Obj = ObjectRepo.getInstance();
//
//		driver.findElement(By.cssSelector(
//				"body > div:nth-child(4) > div > div > div > div > div.ant-picker-date-panel > div.ant-picker-body > table > tbody > tr:nth-child(4) > td:nth-child(3) > div"))
//				.click();
		Thread.sleep(3000);
		activeDates = driver.findElements(By.xpath(Obj.getProperty("date_picker_active_dates")));
		Thread.sleep(5000);
		//// table[@class='ant-picker-content']/tbody/tr/td[@class='ant-picker-cell
		//// ant-picker-cell-in-view' and @title='2020-07-21']/div[normalize-space()]

		for (WebElement j : activeDates) {
//			System.out.println(j.getText());
			if (j.getText().equals(dateParse[2])) {
//				System.out.println("trying to click");
				j.click();
			}
		}

	}

}
