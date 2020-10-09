package com.courier91.apiWorkspace;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.courier91.genric.ExcellUtilites;
import com.courier91.genric.WaitStatementLib;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class OrderDenyUserConfirmation {
public static WebDriver driver ;
	@Test
	public static 	void OrderDenyUserConfirmationApi() {
	
	String id = ExcellUtilites.readData("sheet1", 1, 2);
	System.out.println("order id for going to confirmation return ="+id);
	Response response = RestAssured.get("http://164.52.198.64:8069/orderdenyuserconfirmation/C91Pv69g/"+id+"/1");
	int statusCode = response.getStatusCode();
	String stringResp = response.asString();
	//System.out.println(statusCode);
	System.out.println(stringResp );
	Assert.assertEquals(statusCode, 200);
	//System.out.println(response.getTime());
	WaitStatementLib.iSleep(2);
	Response response1 = RestAssured.get("http://164.52.198.64:8069/queue/consumer/redispatch");
	int statusCode1 = response1.getStatusCode();
	String stringResp1 = response1.asString();
	//System.out.println(statusCode);
	System.out.println(stringResp1 );
	Assert.assertEquals(statusCode1, 200);
	

	
}
}