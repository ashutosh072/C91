package com.courier91.genric;




import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class WaitStatementLib {
	/*  1st Methods**********************************/

	public static void iSleep(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	/* 	2nd Methods**********************************/	

	public static void iWaitForSec( WebDriver driver,int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);

	}

	/* 3rd Methods ***************************/
	public static  void eWaitForVisibility(WebDriver driver , int sec ,WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,sec);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(ele)));
	}
	/* 4th Methods **************************/
	public static void eWaitForClickable(WebDriver driver , int sec , WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, sec);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(ele)));
	}

	/*5th Methods******************************/
	public static void eWaiitForTille(WebDriver driver, int sec,String title) {
		WebDriverWait wait = new WebDriverWait(driver, sec);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.titleIs(title)));
	}


	

}
