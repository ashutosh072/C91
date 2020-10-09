package com.courier91.pageObject;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.courier91.genric.WaitStatementLib;

public abstract class BasePage {



	@FindBy(xpath="//div[@class='pending-card']//div[2]//span[1]//span[1]")
	private WebElement unscannedTab;
	@FindBy(xpath="//span[@class='scan-elm']")
	private WebElement ScannerBtn;
	@FindBy(xpath="//div[@class='pending-card']//div[3]//span[1]//span[1]")
	private WebElement toBeDelivered;
	@FindBy(xpath="//div[@class='return-card']//div[2]")
	private WebElement toBeReturnedTab;
	@FindBy(xpath="//div[@class='return-card']//div[3]")
	private WebElement orderTrackingTab;
	@FindBy(xpath="//div[@class='return-card']//div[@class='status-blk']//div[1]")
	private WebElement toBePickedTab;
	@FindBy(xpath="//div[@class='tab-ctglist']")
	private WebElement redispatchedTab;
	@FindBy(xpath="//div[@id='slide-page']//span[@class='icon-Btn']")
	private WebElement hamburgerMenuBtn;
	@FindBy(xpath="//span[@class='text-link']")
	private WebElement notificationCloseBtn;
	@FindBy(xpath="//div[@class='pending-card']//div[@class='status-blk']//div[1]//span[1]//span[1]")
	private WebElement incomingTabBtn;
	@FindBy(xpath="//span[@class='icon-Btn goback']//*[local-name()='svg']")
	private WebElement backBtn;
	@FindBy(xpath="//div[@class='icon-Btn redispatch-btn']/following-sibling::span[1]")
	private WebElement redispatchBtn;

	



	WebDriver driver;
	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this );
		this.driver= driver;

	}
	public void clickOnUnscannedTAb() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//span[@class='text-link']")).click();
		unscannedTab.click();	
	}
	public void clickOnToBeDeliveredTAb() {
		driver.findElement(By.xpath("//span[@class='text-link']")).click();
		toBeDelivered.click();	
		WaitStatementLib.iSleep(2);
	}
	public void clickOnGlobalScanner() {
		ScannerBtn.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}
	public void clickOnTobeReturnedTAb() {
		driver.findElement(By.xpath("//span[@class='text-link']")).click();
		toBeReturnedTab.click();
	}
	public void clickcOnOrderTRackingTab() {
		driver.findElement(By.xpath("//span[@class='text-link']")).click();
		orderTrackingTab.click();	
	}
	public void clickcOnToBePickedTab() {
		driver.navigate().to("https://c91-test.services-money91.com/");
		WaitStatementLib.iSleep(2);
		driver.findElement(By.xpath("//span[@class='text-link']")).click();
		toBePickedTab.click();	
	}
	public void clickOnRedishpatchedTab() {
		redispatchedTab.click();
	}
	public void clickOnHamburgerMenu() {
		driver.findElement(By.xpath("//span[@class='text-link']")).click();
		hamburgerMenuBtn.click();

	}
	public void clickOntheIncomingTab() {
		notificationCloseBtn.click();
		incomingTabBtn.click();
	}
	public void clickOnBackButton() {
		WaitStatementLib.eWaitForClickable(driver, 10, backBtn);
		WaitStatementLib .iSleep(1);
		backBtn.click();

	}
	public void clickOntheREdispatchTab() {
		notificationCloseBtn.click();
		WaitStatementLib.eWaitForClickable(driver, 10, redispatchBtn);
		
		redispatchBtn.click();

	}







}

