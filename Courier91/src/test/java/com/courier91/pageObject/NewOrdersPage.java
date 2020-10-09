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

public class NewOrdersPage extends BasePage{
	@FindBy(xpath="//div[@class='delivered-card']/div[1]//span[2]")
	private List<WebElement> NewOrdersBtn;
	@FindBy(xpath="//span[@class='icon-Btn goback']//*[local-name()='svg']")
	private WebElement backBtn;
	@FindBy(xpath="//span[@class='scan-elm']")
	private WebElement globalScannerBtn;



	@FindBy(xpath="//*[@id=\"global-scan\"]/div[2]/div[1]/div/input")
	private WebElement orderIdPlaceHoldrBtn;
	@FindBy(xpath="//div[@id='scanlots-popup']/p")
	private WebElement successMsg;
	@FindBy(xpath="//button[@class='button']")
	private WebElement proceedKareBtn;
	@FindBy(xpath="//*[@id=\"scanning-data\"]/div/div/div[3]/button")
	private WebElement actMsg;
	@FindBy(xpath="//div[@class='info-msg']")
	private WebElement infoMsg;
	@FindBy(xpath="//span[@class='close-btn']")
	private WebElement crossBtn;



	WebDriver driver;
	public NewOrdersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this );
		this.driver= driver;
	}
	public void selectOrderIdForIncomingTab() throws IOException {
		FileOutputStream fos=new FileOutputStream(new File("./TestData/testdata - Copy.xlsx"));

		Workbook wb=new XSSFWorkbook();
		Sheet sh=wb.createSheet("Sheet1");
		Row row=sh.createRow(1);
		Cell cell=row.createCell(3);
		int count = NewOrdersBtn.size();
		cell.setCellType(CellType.STRING);
		cell.setCellValue(NewOrdersBtn.get(count-1).getText().toString());
		System.out.println("Id in incoming tab is ="+NewOrdersBtn.get(count-1).getText().toString());
		wb.write(fos);
	}
	
	public void validateIncomingOrderFRomGlobalScanner() {
		globalScannerBtn.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		String id = ExcellUtilites.readData("sheet1", 1, 3);
		orderIdPlaceHoldrBtn.sendKeys(id);
		proceedKareBtn.click();
		WaitStatementLib.iSleep(1);
		String actResult = actMsg.getText();
		String expResult = "आनेवाले आर्डर पर जाएँ";
		AssertJUnit.assertEquals(expResult, actResult);
		Reporter.log(infoMsg.getText(),true);
		Reporter.log("INCOMING TAB ORDER SCAN SUCCESSFULY THROUGH GLOBAL SCANNER ",true);	
		crossBtn.click();

	}




}
