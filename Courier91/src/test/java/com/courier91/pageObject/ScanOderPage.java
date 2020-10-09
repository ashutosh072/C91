package com.courier91.pageObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;

import com.courier91.genric.ExcellUtilites;
import com.courier91.genric.WaitStatementLib;

public class ScanOderPage extends BasePage {
	@FindBy(xpath="//div[@id='unScanned']//li")
	private List<WebElement> totleIdScanTAb;
	@FindBy(xpath="//li//div[1]//div[1]//div[1]//div[1]//span[2]")
	private List<WebElement> orderId;
	@FindBy(xpath="//*[@id=\"unScanned\"]//div/div[1]/div[1]/div[1]/span[2]")
	private List<WebElement> orderId1;
	@FindBy(xpath="//*[@id=\"unScanned\"]//div[1]/span[2]")
	private List<WebElement> orderId2;
	
	@FindBy(xpath="//span[@class='checkmark']")
	private List<WebElement> checkBx;
	
	
	
	@FindBy(xpath="//*[@id=\"global-scan\"]/div[2]/div[1]/div/input")
	private WebElement orderIdPlaceHoldrBtn;
	@FindBy(xpath="//div[@id='scanlots-popup']/p")
	private WebElement successMsg;
	@FindBy(xpath="//button[@class='button']")
	private WebElement proceedKareBtn;
	@FindBy(xpath="//ul[@class='lot-tab']//li[2]")
	private WebElement scannedTabBtn;
	@FindBy(xpath="//div[@class='scanned-ftr']/button")
	private WebElement deliveryKeliyeBhejeBtn;
	@FindBy(xpath="//div[@id='scanlots-popup']/p")
	private WebElement popUpMasg;
	
	@FindBy(xpath="//span[@class='scan-elm']")
	private WebElement globalScannerBtn;
	@FindBy(xpath="//*[@id=\"scanning-data\"]/div/div/div[3]/button")
	private WebElement actMsg;
	@FindBy(xpath="//div[@class='info-msg']")
	private WebElement infoMsg;
	@FindBy(xpath="//span[@class='close-btn']")
	private WebElement crossBtn;
	@FindBy(xpath="//span[@class='icon-Btn close-pop']")
	private WebElement closePopBtn;
	
	
	
	
	WebDriver driver;
	public ScanOderPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this );
		this.driver= driver;

	}
	public void FunctionalityOfUnscanToScan() {
	int count = totleIdScanTAb.size();
	Reporter.log("Totle no of order id in unscanned tab is = "+count , true);
	int count1 = orderId.size();
	for (int i = 0; i < count1; i++) {
		//Reporter.log("Order id is  = "+orderId.get(i).getText() , true);	
	}
	Reporter.log("selected order Id for Scan is  = "+orderId.get(count1-1).getText(), true);
	}
	public void scanOrderId() {
		int count1 = orderId.size();
		orderIdPlaceHoldrBtn.sendKeys(orderId.get(count1-1).getText());
		proceedKareBtn.click();
		WaitStatementLib.eWaitForVisibility(driver, 10, successMsg);
		String actResult = successMsg.getText();
		System.out.println(actResult);
		String expResult = "ऑर्डर सफलतापूर्वक स्कैन हो गया";
		Assert.assertEquals(actResult,expResult);
		Reporter.log("Order scan successfully and moved to scanned tab" , true);
		WaitStatementLib.eWaitForClickable(driver, 10, closePopBtn);
		closePopBtn.click();
		
	}		
	public void clickOnScannedTab() {
		scannedTabBtn.click();
		WaitStatementLib.iSleep(2);
		int count = totleIdScanTAb.size();
		Reporter.log("Totle no of order id in scanned tab is = "+count , true);
		int count1 = orderId1.size();
		for (int i = 0; i < count1; i++) {
			//Reporter.log("Order ids in scanned tab is  = "+orderId1.get(i).getText() , true);
			}
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", orderId1.get(count1-1));
		Reporter.log("selected order Id for 'Delivery ke liye bheje'  is  = "+orderId1.get(count1-1).getText(), true);
		WaitStatementLib.iSleep(1);
		deliveryKeliyeBhejeBtn.click();
		WaitStatementLib.iSleep(1);
		WaitStatementLib.eWaitForVisibility(driver, 10, popUpMasg);
		Reporter.log(""+popUpMasg.getText(), true);
        driver.navigate().to("https://c91-test.services-money91.com/");
        WaitStatementLib.iSleep(2);
		
	}
	public void selectOrderIdForUnscannedTab() throws IOException {
		FileOutputStream fos=new FileOutputStream(new File("./TestData/testdata - Copy.xlsx"));

		Workbook wb=new XSSFWorkbook();
		Sheet sh=wb.createSheet("Sheet1");
		Row row=sh.createRow(1);
		Cell cell=row.createCell(4);
		int count1 = orderId.size();
		cell.setCellType(CellType.STRING);
		cell.setCellValue(orderId.get(count1-1).getText().toString());
		System.out.println("Id in Unscanned tab is ="+orderId.get(count1-1).getText().toString());
		wb.write(fos);
	}
	public void validateUnscannedTabOrderFRomGlobalScanner() {
		WaitStatementLib.eWaitForClickable(driver, 10, globalScannerBtn);
		globalScannerBtn.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		String id = ExcellUtilites.readData("sheet1", 1, 4);
		orderIdPlaceHoldrBtn.sendKeys(id);
		proceedKareBtn.click();
		WaitStatementLib.iSleep(1);
		String actResult = actMsg.getText();
		String expResult = "स्कैन मार्क करें";
		AssertJUnit.assertEquals(expResult, actResult);
		Reporter.log(infoMsg.getText(),true);
		Reporter.log("UNSCANNED TAB ORDER SCAN SUCCESSFULLY THROUGH GLOBAL SCANNER ",true);	
		crossBtn.click();

	}
	public void selectOrderIdForScannedTab() throws IOException {
		WaitStatementLib.iSleep(2);
		scannedTabBtn.click();
		FileOutputStream fos=new FileOutputStream(new File("./TestData/testdata - Copy.xlsx"));

		Workbook wb=new XSSFWorkbook();
		Sheet sh=wb.createSheet("Sheet2");
		Row row=sh.createRow(2);
		Cell cell=row.createCell(5);
		int count1 = orderId1.size();
		cell.setCellType(CellType.STRING);
		cell.setCellValue(orderId2.get(count1-1).getText().toString());
		System.out.println("Id in scanned tab is ="+orderId2.get(count1-1).getText().toString());
		wb.write(fos);
		
		
		
	}
	public void validateScannedTabOrderFRomGlobalScanner() {
		WaitStatementLib.eWaitForClickable(driver, 10, globalScannerBtn);
		WaitStatementLib.iSleep(1);
		globalScannerBtn.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		String id = ExcellUtilites.readData("sheet2", 2, 5);
		orderIdPlaceHoldrBtn.sendKeys(id);
		proceedKareBtn.click();
		WaitStatementLib.iSleep(1);
		String actResult = actMsg.getText();
		String expResult = "डिलीवरी के लिए भेजें";
		AssertJUnit.assertEquals(expResult, actResult);
		Reporter.log(infoMsg.getText(),true);
		Reporter.log("SCANNED TAB ORDER SCAN SUCCESSFULLY THROUGH GLOBAL SCANNER  ",true);	
		crossBtn.click();

	}
	
	
	
	
	
	

}
