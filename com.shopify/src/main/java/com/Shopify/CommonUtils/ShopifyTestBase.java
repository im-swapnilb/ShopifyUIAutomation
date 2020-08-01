package com.Shopify.CommonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Shopify.Reports.Log;
import com.google.common.io.Files;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class ShopifyTestBase {

	
	public static int totalNoofCols;
	public static int totalNoofRows;
	public static String status ;
	public static WebDriver driver;
	@Test(dataProvider= "shopify")
	
	
	public static WebDriver fn_LaunchBrowser(String TestCaseName,String Browser,String URL) throws Exception{
		try { 
		if(Browser.equalsIgnoreCase("CH")){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\swapnilband\\driver\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	else if(Browser.equalsIgnoreCase("FF")){
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\swapnilband\\driver\\geckodriver.exe");
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	 	  driver.navigate().to(URL);
	 	  
	  } catch (Exception e) { 
		  Log.error("URL is not valid for " + TestCaseName);
		  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	       System.out.println("FAILURE: URL did not load: " + URL); 
	       status = "Failed";
	       takeScreenshot(TestCaseName);
	       Assert.fail();
	       throw new Exception("URL did not load");  
	       
	     }  
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	return driver;
}

	public boolean isElementPresentCheckUsingJavaScriptExecutor(WebElement element, String TestCaseName) {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
        try {
            Object obj = jse.executeScript("return typeof(arguments[0]) != 'undefined' && arguments[0] != null;",
                    element);
            if (obj.toString().contains("true")) {
                System.out.println("isElementPresentCheckUsingJavaScriptExecutor: SUCCESS");
                return true;
            } else {
                System.out.println("isElementPresentCheckUsingJavaScriptExecutor: FAIL");
            }

        } catch (NoSuchElementException e) {
        	Log.error("No element present " + TestCaseName);
            System.out.println("isElementPresentCheckUsingJavaScriptExecutor: FAIL");
        }catch (Exception e) {
        	Log.error("No element present " + TestCaseName);
            System.out.println("isElementPresentCheckUsingJavaScriptExecutor: FAIL");
        }
        return false;
    }
	
	public void assertElement(WebElement selector, String TestCaseName) throws Exception{
		try{
			status = "Passed";
			 Assert.assertEquals(true, selector.isDisplayed());
			 System.out.println("TC is " +TestCaseName + " and satus is :"+ status);
		 
		}
		catch (Exception e) {
			Log.error("No element present assert fail " + TestCaseName);
			status = "Failed";
			takeScreenshot(TestCaseName);
			System.out.println("TC is " +TestCaseName + " and satus is :"+ status);
            Assert.fail();
        }
	}
	
	public void mouseHover(WebElement selector, String TestCaseName) throws Exception{
		try{
		Actions action = new Actions(driver);
		action.moveToElement(selector).perform();
		} catch (Exception e) {
			Log.error("Element is not enabled " + TestCaseName);
			status = "Failed";
			takeScreenshot(TestCaseName);
            System.out.println("Exception " + status);
        }
	}
	
	
	protected static String isEnabled(WebElement selector, String TestCaseName) throws Exception {
		try{
			boolean data = selector.isEnabled();
			System.out.println("TC is " + data);
			if(data){
					status = "Passed";
					System.out.println("TC is " + status);
				} else{
					Log.error("Element is not enabled " + TestCaseName);
					takeScreenshot(TestCaseName);
					System.out.println("TC is " + status);
				}
			Assert.assertEquals(true, selector.isDisplayed());
		}
		catch(NoSuchElementException e){
			Log.error("Element is not enabled " + TestCaseName);
			status = "Failed";
			takeScreenshot(TestCaseName);
			System.out.println("Element not present " + status);
		}
		catch (Exception e) {
			Log.error("Element is not enabled " + TestCaseName);
			status = "Failed";
			takeScreenshot(TestCaseName);
            System.out.println("Exception " + status);
        }
		return status;
	}

		// Read excel code.
		public static String[][] getExcelData(String fileName,String sheetName) throws BiffException{
			String[][] arrayExcelData = null;
			
			try{
				FileInputStream fs=new FileInputStream("D://FlipExcel.xls");
				
				Workbook wb=Workbook.getWorkbook(fs);
				Sheet sh=wb.getSheet(sheetName);
				
				
				System.out.println("Current col and row from the sheet: " +sheetName);
				totalNoofCols=sh.getColumns();
				totalNoofRows=sh.getRows();
				
				System.out.println("the current col abd row number: col "+totalNoofCols+ "Row:"+totalNoofRows);
				arrayExcelData= new String[totalNoofRows-1][totalNoofCols];
				
				for (int i = 1; i < totalNoofRows; i++) {
					for (int j = 0; j < totalNoofCols; j++) {
						arrayExcelData[i-1][j]=sh.getCell(j, i).getContents();
						System.out.println(sh.getCell(j, i).getContents());
					}
				}
			}
			
			catch(FileNotFoundException e){
				Log.error("File not found ");
				e.printStackTrace();
			}
			
			catch(IOException e){
				Log.error("IO expection found");
				e.printStackTrace();
				e.printStackTrace();
			}
			
			return arrayExcelData;
		}
		public void waitForElementToBeVisible(By selector) throws Exception {  
		    try {  
		       WebDriverWait wait = new WebDriverWait(driver, 5);  
		       wait.until(ExpectedConditions.presenceOfElementLocated(selector));  
	     } catch (Exception e) {  
	    	 Log.error("Element is not visible");
	    	 throw new NoSuchElementException(String.format("The following element was not visible: %s", selector));  
     }  
   }
		public static String getPopupMessage(final WebDriver driver) throws Exception {
			 String message = null;
			 try {
				 Alert alert = driver.switchTo().alert();
				 message = alert.getText();
				 alert.accept();
			 } catch (Exception e) {
				 Log.error("Pop up error.");
				 message = null;
			 }
			 System.out.println("message"+message);
			 return message;
		}
			
			//It will cancel pop-up message.
			public static String cancelPopupMessageBox(final WebDriver driver) throws Exception {
			 String message = null;
			 try {
				 Alert alert = driver.switchTo().alert();
				 message = alert.getText();
				 alert.dismiss();
			 } catch (Exception e) {
				 Log.error("Cancel pop up error.");
				 message = null;
			 }
			 return message;
		}
			//Reading ToolTip text in in Selenium-WebDriver
			public static String tooltipText(WebDriver driver, By locator) throws Exception{
				try {
					String tooltip = driver.findElement(locator).getAttribute("title");
					return tooltip;
			} catch (Exception e) {
				Log.error("Tooltip error.");
					 return null;
				 }
			}
	 
			public static void selectRadioButton(WebDriver driver, By locator, String value) throws Exception{ 
				try {
					List<WebElement> select = driver.findElements(locator);
					for (WebElement element : select)
					{
						if (element.getAttribute("value").equalsIgnoreCase(value)){
						element.click();
						}
					}
				} catch (Exception e) {
					Log.error("Radio button error.");
					 return;
				}
			}
			
			// Selecting searched dropdown in Selenium-WebDriver
			public static void selectSearchDropdown(WebDriver driver, By locator, String value) throws Exception{
				try {
				driver.findElement(locator).click();
				driver.findElement(locator).sendKeys(value);
				driver.findElement(locator).sendKeys(Keys.TAB);
				} catch (Exception e) {
					Log.error("select dropdown error.");
			}
		}
			
			//Uploading file using  Selenium-WebDriver
			public static void uploadFile(WebDriver driver, By locator, String path) throws Exception{
				try {
					driver.findElement(locator).sendKeys(path);
				} catch (Exception e) {
					Log.error("UploadFile error.");
			}
		}
			
			//Downloading file in Selenium-WebDriver
			public static void dragAndDrop(WebElement source, WebElement target, WebDriver driver ) throws Exception{
				try {
					WebElement element = driver.findElement(By.name("source"));
					WebElement newTarget = driver.findElement(By.name("target"));
					(new Actions(driver)).dragAndDrop(element, newTarget).perform();
			} catch (Exception e) {
				Log.error("dragAndDrop error.");
				
			}
		}	
	
			public static void takeScreenshot(String TestCaseName) throws Exception {
		       try{ Date date = new Date();
		        SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("yyyyMMdd");
		        String date_to_string = dateformatyyyyMMdd.format(date);
		        File dir = new File(date_to_string);
		        dir.mkdir( );
		        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		        Files.copy(scrFile, new File("C:\\Users\\swapnilband\\Documents\\workspace-sts-3.7.0.RELEASE\\flipTest\\"+ dir +"\\" + TestCaseName  + ".png"));
		       } catch (Exception e) {
				//	Log.error("takeScreenshot error.");
		    }
    }
	
	
//	@DataProvider(name="shopify")
    public static Object[][] loginData() throws BiffException{
     
     Object[][] arrayObject=getExcelData("shopify", "shopify");
     return arrayObject;
     }	
	 protected static void waitForElementToBeClickable(WebElement selector) {
	 WebDriverWait wait = new WebDriverWait(driver, 15);
	    wait.until(ExpectedConditions.elementToBeClickable(selector));
}
	 
	 private static void forwardNavigation(String selector) throws InterruptedException {
	 WebDriver driver =new FirefoxDriver();
	 driver.get("http://seleniumhq.org/");
	 driver.findElement(By.linkText(selector)).click();
	 Thread.sleep(3000);            //delay
	
	 driver.navigate().forward();
}
	 
	 private static void backNavigation(String selector) throws InterruptedException {
		 WebDriver driver =new FirefoxDriver();
		 driver.get("http://seleniumhq.org/");
		 driver.findElement(By.linkText(selector)).click();
		 Thread.sleep(3000);            //delay
		driver.navigate().back();
	 }	
	
}
