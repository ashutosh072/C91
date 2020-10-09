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
import org.testng.AssertJUnit;
import org.testng.Reporter;

import com.courier91.genric.ExcellUtilites;
import com.courier91.genric.WaitStatementLib;

public class Order_to_be_picked_from_customer extends BasePage  {
	
	@FindBy(xpath="//li[@class='scan-mark']")
	private List<WebElement> orderDetails;
	@FindBy(xpath="//span[text()=' आर्डर आईडी  :']/following-sibling::span")
	private List<WebElement> orderId;
	@FindBy(xpath="//span[@class='scan-elm']")
	private WebElement ScannerBtn;
	@FindBy(xpath="//html/body/pre")
	private WebElement queueMsg;
	@FindBy(xpath="//*[@id=\"global-scan\"]/div[2]/div[1]/div/input")
	private WebElement orderIdPlaceHoldrBtn;
	@FindBy(xpath="//*[@id=\"global-scan\"]/div[2]/div[2]/div[3]/button")
	private WebElement proceedKareBtn;
	@FindBy(xpath="//div[@id='scanlots-popup']/p")
	private WebElement successMsg;
	@FindBy(xpath="//span[@class='scan-elm']")
	private WebElement globalScannerBtn;
	@FindBy(xpath="//*[@id=\"scanning-data\"]/div/div/div[3]/button")
	private WebElement actMsg;
	@FindBy(xpath="//div[@class='info-msg']")
	private WebElement infoMsg;
	@FindBy(xpath="//span[@class='close-btn']//*[local-name()='svg']")
	private WebElement crossBtn;
	
	
	
	
	static WebDriver driver;
	public Order_to_be_picked_from_customer(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this );
		this.driver= driver;

	}
	public void orderPickKareFunctionality() {
	
		int count = orderId.size();
		Reporter.log("Totle no of order id in in to be Picked Tab is  = "+count , true);
		int count1 = orderId.size();
		for (int i = 0; i < count1; i++) {
			Reporter.log("Order id is  = "+orderId.get(i).getText() , true);	
		}
		Reporter.log("selected order Id for to be picked is   = "+orderId.get(count1-1).getText(), true);
		
		ScannerBtn.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		orderIdPlaceHoldrBtn.sendKeys(orderId.get(count1-1).getText());
		proceedKareBtn.click();
		WaitStatementLib.eWaitForVisibility(driver, 10, successMsg);
		String actResult = successMsg.getText();
		Reporter.log(actResult , true);
		driver.navigate().to("https://c91-test.services-money91.com/");
        WaitStatementLib.iSleep(2);
}
	
	
	
	
	
	
	public  static String getOrderIdForpickedUp()
	{
		List<WebElement> orderId1 = driver.findElements(By.xpath("//span[text()=' Order ID: ']/following-sibling::span"));
		int count = orderId1.size();
		//   		Reporter.log("Totle no of order id in delivery kare  tab is = "+count , true);
		int count1 = orderId1.size();
		for (int i = 0; i < count1; i++) {
			//   			Reporter.log("Order id in delivery kare  is  = "+orderId1.get(i).getText() , true);	
		}
		String id= orderId1.get(count1-1).getText();
		return id;
	}	
	public void callApiForqueueRunning() {
		driver.get("http://164.52.198.64:8069/queue/consumer/order");
		WaitStatementLib.eWaitForVisibility(driver, 10, queueMsg);
		Reporter.log(queueMsg.getText() , true);
		driver.navigate().to("https://c91-test.services-money91.com/");
	}
	public void selectOrderIdForToBePickedTab() throws IOException {
		try {
		FileOutputStream fos=new FileOutputStream(new File("./TestData/testdata - Copy.xlsx"));
		Workbook wb=new XSSFWorkbook();
		Sheet sh=wb.createSheet("Sheet1");
		Row row=sh.createRow(1);
		Cell cell=row.createCell(7);
		int count1 = orderId.size();
		cell.setCellType(CellType.STRING);
		cell.setCellValue(orderId.get(count1-1).getText().toString());
		System.out.println("Id in To be picked tab is ="+orderId.get(count1-1).getText().toString());
		wb.write(fos);
		}
		catch(Exception e ) {
			System.out.println("No any order in To be picked Tab  ");
			e.printStackTrace();
		}
	
	}
	public void validateToBePickedTabOrderIdFRomGlobalScanner() {
		try {
		WaitStatementLib.eWaitForClickable(driver, 10, globalScannerBtn);
		WaitStatementLib.iSleep(1);
		globalScannerBtn.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		String id = ExcellUtilites.readData("sheet1", 1, 7);
		orderIdPlaceHoldrBtn.sendKeys(id);
		proceedKareBtn.click();
		WaitStatementLib.iSleep(1);
		String actResult = actMsg.getText();
		String expResult = "पिक होना है। आर्डर पर जाएँ";
		AssertJUnit.assertEquals(expResult, actResult);
		Reporter.log(infoMsg.getText(),true);
		Reporter.log("TO BE PICKED TAB ORDER SCAN SUCCESSFULLY THROUGH GLOBAL SCANNER  ",true);	
		crossBtn.click();
		}
		catch(Exception e ) {
			
		}
	}
}
