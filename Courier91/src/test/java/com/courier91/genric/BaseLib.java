package com.courier91.genric;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseLib {
	public WebDriver driver;


	@BeforeMethod
	public void setUp() {
		

		
		String browserName=GenricLib.getValueString("browser");
		System.out.println("browser name="+browserName);
		if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/home/ashutosh/Downloads/geckodriver-v0.26.0-linux64/geckodriver");
			WebDriver driver = new FirefoxDriver();       
			Reporter.log("firefox launched", true);
			driver.manage().window().maximize();
		}
		else if(browserName.equalsIgnoreCase("Chrome")) {
			
			ChromeOptions optionsC = new ChromeOptions();
			optionsC.addArguments(Arrays.asList("disable-infobars", "ignore-certificate-errors", "start-maximized","use-fake-ui-for-media-stream"));
			System.setProperty("webdriver.chrome.driver","/home/ashutosh/Downloads/chromedriver_linux64/chromedriver");
			driver = new ChromeDriver(optionsC);
			Reporter.log("chrome Launched", true);
			//	driver.manage().window().maximize();
			driver.manage().window().setSize(new Dimension(717,727));
		}
		WaitStatementLib.iWaitForSec(driver, 40);
		driver.navigate().to(GenricLib.getValueString("url"));

		Cookie xai = new Cookie("X-Auth-Id", "da4fb5c6e93e74d3df8527599fa62642");
		//		Thread.sleep(5000);
		Cookie xat = new Cookie("X-Auth-Token", "059829a2-ee91-4f90-86da-107b317559ab");
		driver.manage().addCookie(xai);
		//		Thread.sleep(5000);
		//  driver.manage().addCookie(xat);
		WaitStatementLib.iWaitForSec(driver, 30);
		driver.navigate().refresh();
		
	}

	
	@AfterMethod
	public void tearDown(ITestResult result){
		String scriptName = result.getMethod().getMethodName();
		if(result.isSuccess()) {
			Reporter.log("ScriptName   "+scriptName, true);
		}
		else {
			ScreenshotLib sl = new ScreenshotLib();
			sl.takeScreenshot(driver, scriptName);
			Reporter.log("screenshot has been taken", true);
		}
//			driver.close();
		Reporter.log("browser is closed" , true);
	}

}


