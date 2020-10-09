package com.courier91.pageObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
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

public class ReturnOrderPage extends BasePage{
	@FindBy(xpath="//span[text()=' आर्डर आईडी  :']/..//strong")
	private List<WebElement> orderId;
	@FindBy(xpath="//li[@class='ctgtab']")
	private WebElement readyToReturntab;
	@FindBy(xpath="//span[@class='scan-elm']")
	private WebElement scannerBtn;
	@FindBy(xpath="//*[@id=\"global-scan\"]/div[2]/div[1]/div/input")
	private WebElement oderIdPlaceholder;
	@FindBy(xpath="//div[@class='process-ftr']//button[@class='button']")
	private WebElement proceedKareBtn;
	@FindBy(xpath="//div[@id='scanlots-popup']/p")
	private WebElement successMsg;
	@FindBy(xpath="//button[@class='button']")
	private WebElement orderReturnKareBtn;
	@FindBy(xpath="//div[@class='confirm-box']//span[2]")
	private WebElement popupYesBtn;
	@FindBy(xpath="//span[@class='close-btn']//*[local-name()='svg']")
	private WebElement crossBtn;
	@FindBy(xpath="//*[@id=\"scanning-data\"]/div/div/div[3]/button")  //button[@class='button']
	private WebElement returnKareBtnfinal;
	@FindBy(xpath="//span[@class='scan-elm']")
	private WebElement globalScannerBtn;
	@FindBy(xpath="//*[@id=\"scanning-data\"]/div/div/div[3]/button")
	private WebElement actMsg;
	@FindBy(xpath="//div[@class='info-msg']")
	private WebElement infoMsg;
	@FindBy(xpath="//*[@id=\"global-scan\"]/div[2]/div[1]/div/input")
	private WebElement orderIdPlaceHoldrBtn;
	

	static WebDriver driver;
	public ReturnOrderPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this );
		this.driver= driver;

	}
	public void orderIdInUnderProcessing() {
		int count = orderId.size();
		Reporter.log("Totle no of order id in Under Processing tab is = "+count , true);
		int count1 = orderId.size();
		for (int i = 0; i < count1; i++) {
			Reporter.log("Order id In Under Processing is  = "+orderId.get(i).getText() , true);	
		}
		Reporter.log("selected order Id for 'Ready to return ' tab   is  = "+orderId.get(count1-1).getText(), true);
	}
	
	public static String getOrderIdforReadytoReturn() 
		
		{

			List<WebElement> orderId1 = driver.findElements(By.xpath("//span[text()=' आर्डर आईडी  :']/..//strong"));
			int count1 = orderId1.size();
			for (int i = 0; i < count1; i++) {
			}
			String id= orderId1.get(count1-1).getText();
			return id;
		
		
	}
	public void clickOnReadyToReturnTab(){
		WaitStatementLib.iSleep(1);
	readyToReturntab.click();
	WaitStatementLib.iSleep(1);
	int count = orderId.size();
	Reporter.log("Totle no of order id in Ready to return tab is = "+count , true);
	int count1 = orderId.size();
	for (int i = 0; i < count1; i++) {
		Reporter.log("Order id In Ready to return tab is  = "+orderId.get(i).getText() , true);	
	}
	Reporter.log("selected order Id for  return   is  = "+orderId.get(count1-1).getText(), true);
	scannerBtn.click();
	new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
	driver.switchTo().alert().accept();
	WaitStatementLib.iSleep(1);
	oderIdPlaceholder.sendKeys(orderId.get(count1-1).getText());
	proceedKareBtn.click();
	WaitStatementLib.eWaitForVisibility(driver, 10, successMsg);
	String actResult = successMsg.getText();
	Reporter.log(actResult , true);
	WaitStatementLib.eWaitForVisibility(driver, 10, orderReturnKareBtn);
	orderReturnKareBtn.click();
	WaitStatementLib.iSleep(1);
	popupYesBtn.click();
	WaitStatementLib.iSleep(2);
	String actResult1 = successMsg.getText();
	System.out.println(actResult);
	String expResult = "आर्डर सफलतापूर्वक वापस";
	Assert.assertEquals(actResult1,expResult);
	Reporter.log("Orders return Successfully and moved to 'Order Tracking' tab" , true);
	  driver.navigate().to("https://c91-test.services-money91.com/");
      WaitStatementLib.iSleep(2);
	
	
	
	
	
	
	}
	public void selectOrderIdForUnderProcessingTab() throws IOException {
		FileOutputStream fos=new FileOutputStream(new File("./TestData/testdata - Copy.xlsx"));
		Workbook wb=new XSSFWorkbook();
		Sheet sh=wb.createSheet("Sheet1");
		Row row=sh.createRow(1);
		Cell cell=row.createCell(8);
		int count1 = orderId.size();
		cell.setCellType(CellType.STRING);
		cell.setCellValue(orderId.get(count1-1).getText().toString());
		System.out.println("Id in underprosseing  tab is ="+orderId.get(count1-1).getText().toString());
		wb.write(fos);
	
	}
	public void validateUnderProcessingIdFRomGlobalScanner() {
		WaitStatementLib.eWaitForClickable(driver, 10, globalScannerBtn);
		WaitStatementLib.iSleep(1);
		globalScannerBtn.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		String id = ExcellUtilites.readData("sheet1", 1, 8);
		orderIdPlaceHoldrBtn.sendKeys(id);
		proceedKareBtn.click();
		WaitStatementLib.iSleep(1);
		String actResult = actMsg.getText();
		String expResult = "अंडर प्रोसेसिंग आर्डर पर जाएँ";
		AssertJUnit.assertEquals(expResult, actResult);
		Reporter.log(infoMsg.getText(),true);
		Reporter.log("UNDERPROCESSING ORDER SCAN SUCCESSFULLY THROUGH GLOBAL SCANNER  ",true);	
		crossBtn.click();

	}
	public void selectOrderIdForReadyToReturnTab() throws IOException {
		WaitStatementLib.iSleep(2);
		readyToReturntab.click();
		FileOutputStream fos=new FileOutputStream(new File("./TestData/testdata - Copy.xlsx"));
		Workbook wb=new XSSFWorkbook();
		Sheet sh=wb.createSheet("Sheet1");
		Row row=sh.createRow(1);
		Cell cell=row.createCell(9);
		int count1 = orderId.size();
		cell.setCellType(CellType.STRING);
		cell.setCellValue(orderId.get(count1-1).getText().toString());
		System.out.println("Id in ready to return  tab is ="+orderId.get(count1-1).getText().toString());
		wb.write(fos);
	
	}
	public void validateReadyToREturnIdFRomGlobalScanner() {
		WaitStatementLib.eWaitForClickable(driver, 10, globalScannerBtn);
		WaitStatementLib.iSleep(1);
		globalScannerBtn.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		String id = ExcellUtilites.readData("sheet1", 1, 9);
		orderIdPlaceHoldrBtn.sendKeys(id);
		proceedKareBtn.click();
		WaitStatementLib.iSleep(1);
		String actResult = actMsg.getText();
		String expResult = "रिटर्न मार्क करें";
		AssertJUnit.assertEquals(expResult, actResult);
		Reporter.log(infoMsg.getText(),true);
		Reporter.log("READY TO RETURN ORDER  ID SCAN SUCCESSFULLY THROUGH GLOBAL SCANNER  ",true);	
		crossBtn.click();

	}
	
}
