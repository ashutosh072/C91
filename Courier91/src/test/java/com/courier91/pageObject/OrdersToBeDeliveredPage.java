package com.courier91.pageObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.exec.util.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
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

import com.courier91.apiWorkspace.OrderOtp;
import com.courier91.genric.ExcellUtilites;
import com.courier91.genric.WaitStatementLib;



public class OrdersToBeDeliveredPage {

	@FindBy(xpath="//div[@id='unScanned']//li")
	private List<WebElement> totleIdScanTAb;
	@FindBy(xpath="//li//div[1]//div[1]//div[1]//span[2]")
	private List<WebElement> orderId;	
	@FindBy(xpath="//div[@class='bottom-card .btn-box']/span[2]")
	private List<WebElement> deliveryKareBtns;
	@FindBy(xpath="//div[@class='bottom-card .btn-box']/span[1]")
	private List<WebElement> returnsKareBtns;
	@FindBy(xpath="//li[1]//div[3]//span[2]")
	private WebElement deliveryKareBtn;
	@FindBy(xpath="//li[1]//div[3]//span[1]")
	private WebElement returnKareBtn;

	@FindBy(xpath="//*[@id=\"global-scan\"]/div[2]/div[1]/div/input")
	private WebElement orderIdPlaceHoldrBtn;
	@FindBy(xpath="//button[@class='button']")
	private WebElement proceedKareBtn;
	@FindBy(xpath="//div[@id='scanlots-popup']/p")
	private WebElement successMsg;
	@FindBy(xpath="//button[@class='button']")
	private WebElement otpVerifyBtn;
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



	static WebDriver driver;
	public OrdersToBeDeliveredPage(WebDriver driver) {

		PageFactory.initElements(driver, this );
		this.driver= driver;

	}	

	public  void deliverykare() throws IOException {

		int count = orderId.size();
		Reporter.log("Totle no of order id in delivery kare  tab is = "+count , true);
		int count1 = orderId.size();
		for (int i = 0; i < count1; i++) {
		//	Reporter.log("Order id in delivery kare  is  = "+orderId.get(i).getText() , true);	
		}
		Reporter.log("selected order Id for 'Delivery ke liye bheje'  is  = "+orderId.get(count1-1).getText(), true);
		//deliveryKareBtn.click();
		deliveryKareBtns.get(count-1).click();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		orderIdPlaceHoldrBtn.sendKeys(orderId.get(count1-1).getText());
		
		FileOutputStream fos=new FileOutputStream(new File("./TestData/testdata - Copy.xlsx"));
		
		Workbook wb=new XSSFWorkbook();
		Sheet sh=wb.createSheet("Sheet1");
		Row row=sh.createRow(1);
		Cell cell=row.createCell(1);
		
		cell.setCellType(CellType.STRING);
		cell.setCellValue(orderId.get(count1-1).getText().toString());
		wb.write(fos);
		proceedKareBtn.click();
	}
	public void returnkare() throws IOException {

		int count = orderId.size();
		Reporter.log("Totle no of order id in delivery kare  tab is = "+count , true);
		int count1 = orderId.size();
		for (int i = 0; i < count1; i++) {
		//	Reporter.log("Order id in delivery kare  is  = "+orderId.get(i).getText() , true);	
		}
		Reporter.log("selected order Id for 'Return kare'  is  = "+orderId.get(count1-1).getText(), true);
//		returnKareBtn.click();
		
FileOutputStream fos=new FileOutputStream(new File("./TestData/testdata - Copy.xlsx"));
		
		Workbook wb=new XSSFWorkbook();
		Sheet sh=wb.createSheet("Sheet1");
		Row row=sh.createRow(1);
		Cell cell=row.createCell(2);
		
		cell.setCellType(CellType.STRING);
		cell.setCellValue(orderId.get(count1-1).getText().toString());
		wb.write(fos);
		
		
		
		
		
		
		returnsKareBtns.get(count-1).click();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		orderIdPlaceHoldrBtn.sendKeys(orderId.get(count1-1).getText());
		proceedKareBtn.click();
		WaitStatementLib.iSleep(1);
		returnKareBtnfinal.click();	
		WaitStatementLib.eWaitForVisibility(driver, 10, successMsg);
		String actResult = successMsg.getText();
		Reporter.log(actResult , true);
		driver.navigate().to("https://c91-test.services-money91.com/");
        WaitStatementLib.iSleep(2);
	
	}
	public  static String getOrderIdForDeliver() 
	{
		List<WebElement> orderId1 = driver.findElements(By.xpath("//li//div[1]//div[1]//div[1]//span[2]"));
		int count = orderId1.size();
		//   		Reporter.log("Totle no of order id in delivery kare  tab is = "+count , true);
		int count1 = orderId1.size();
		for (int i = 0; i < count1; i++) {
			//   			Reporter.log("Order id in delivery kare  is  = "+orderId1.get(i).getText() , true);	
		}
		String id= orderId1.get(count1-1).getText();
		System.out.println(id);

	

		return id;
		
	
			
			}
	
	
	
	
	public void sendOtp() throws IOException {
		WaitStatementLib.iSleep(2);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", otpVerifyBtn);
		String otpString = OrderOtp.getOtp();
		int num= Integer.parseInt(otpString);
		int n4 = num%10;
		int n3 = (num-n4)%100/10;
		int n2 = (num-n4-n3)%1000/100;
		int n1 = (num-n4-n3-n2)%10000/1000;
		String first=String.valueOf(n1);
		String second=String.valueOf(n2);
		String third=String.valueOf(n3);
		String fourth=String.valueOf(n4);

		driver.findElement(By.xpath("//input[@name='otpDigit1']")).sendKeys(first);
		driver.findElement(By.xpath("//input[@name='otpDigit2']")).sendKeys(second);
		driver.findElement(By.xpath("//input[@name='otpDigit3']")).sendKeys(third);
		driver.findElement(By.xpath("//input[@name='otpDigit4']")).sendKeys(fourth);
		otpVerifyBtn.click();
		WaitStatementLib.eWaitForVisibility(driver, 10, successMsg);
		String actResult = successMsg.getText();
		System.out.println(actResult);
		String expResult = "ओटीपी वेरीफाई और ऑर्डर डिलीवर";
		Assert.assertEquals(actResult,expResult);
		Reporter.log("you have delivered your order successfully" , true);

	}
	public void selectOrderIdForToBeDeliveredTab() throws IOException {
		FileOutputStream fos=new FileOutputStream(new File("./TestData/testdata - Copy.xlsx"));

		Workbook wb=new XSSFWorkbook();
		Sheet sh=wb.createSheet("Sheet1");
		Row row=sh.createRow(1);
		Cell cell=row.createCell(6);
		int count1 = orderId.size();
		cell.setCellType(CellType.STRING);
		cell.setCellValue(orderId.get(count1-1).getText().toString());
		System.out.println("Id in to be delivered Tab is ="+orderId.get(count1-1).getText().toString());
		wb.write(fos);
	}
	public void validateToBeDeliveredTabOrderIdFRomGlobalScanner() {
		WaitStatementLib.eWaitForClickable(driver, 10, globalScannerBtn);
		WaitStatementLib.iSleep(1);
		globalScannerBtn.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		String id = ExcellUtilites.readData("sheet1", 1, 6);
		orderIdPlaceHoldrBtn.sendKeys(id);
		proceedKareBtn.click();
		WaitStatementLib.iSleep(1);
		String actResult = actMsg.getText();
		String expResult = "डिलीवर करें";
		AssertJUnit.assertEquals(expResult, actResult);
		Reporter.log(infoMsg.getText(),true);
		Reporter.log("TO BE DELIVERED TAB ORDER SCAN SUCCESSFULLY THROUGH GLOBAL SCANNER  ",true);	
		crossBtn.click();

	}
}	

