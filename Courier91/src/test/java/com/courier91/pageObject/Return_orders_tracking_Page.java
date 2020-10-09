package com.courier91.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class Return_orders_tracking_Page extends BasePage{
	@FindBy(xpath="//div[@class='orderlot-popup active-pop']//p")
	private WebElement popUpMasg;
	@FindBy(xpath="//div[@class='lot-block']//h2")
	private List<WebElement> lotIds;
	@FindBy(xpath="//li[1]//div[1]//div[2]//span[1]")
	private  WebElement viewOrderBtn;
	@FindBy(xpath="//div[@class='lot-titlebox']")
	private  WebElement viewOrderDetailsBtn;
	@FindBy(xpath="//li[@class='scan-mark']")
	private List<WebElement> orderDetails;



	static WebDriver driver;
	public Return_orders_tracking_Page(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this );
		this.driver= driver;

	}
	public void checkLotId() {
		int count = lotIds.size();
		Reporter.log("Totle no of Lot id in order Tracking tab is = "+count , true);
		for (int i = 0; i < count; i++) {
			Reporter.log("lot Id  In order Tracking  = "+lotIds.get(i).getText() , true);	
		}
		viewOrderBtn.click();
		Reporter.log(viewOrderDetailsBtn.getText() , true);

		int count1 = orderDetails.size();
		Reporter.log("Totle no of order in this lot id is  = "+count1 , true);
		for (int i = 0; i < count1; i++) {
			Reporter.log("order details in this lot is    = "+orderDetails.get(i).getText() , true);	
		}


	}

	public void clickOnViewOrders() {
viewOrderBtn.click();



	}	









}
