package com.courier91.pageObject;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.courier91.genric.ExcellUtilites;

public class HamburgerMenuPage extends BasePage {

	@FindBy(xpath="//ul[1]//li[1]//div[1]")
	private WebElement barcodeScannerBtn;
	@FindBy(xpath="//div[@class='model-header']")
	private WebElement headerBtn;
	@FindBy(xpath="//button[@class='button']")
	private WebElement proceedKareBtn;
	@FindBy(xpath="//input[@placeholder='Order ID number डाले']")
	private WebElement orderIdPlaceHoldrBtn;
	WebDriver driver;
	public HamburgerMenuPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this );
		this.driver= driver;

	}
	@Test
	public void test_BarcodeScannerFunctionality(){
		barcodeScannerBtn.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		String actResult = headerBtn.getText();
		String expResult = "स्कैन";
		AssertJUnit.assertEquals(expResult, actResult);
		Reporter.log("barcode scanner opening successfully",true);	
	}
	
	

}
