package com.courier91.apiWorkspace;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.courier91.genric.ExcellUtilites;
import com.courier91.genric.WaitStatementLib;
import com.courier91.pageObject.Order_to_be_picked_from_customer;
import com.courier91.pageObject.OrdersToBeDeliveredPage;
import com.courier91.pageObject.ReturnOrderPage;
import com.courier91.pageObject.ScanOderPage;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostApiForReturnProcess {
	
	@Test
	public static void ReturnOrderApi() {
		RestAssured.config=RestAssured.config().sslConfig(SSLConfig.sslConfig().allowAllHostnames());
		RestAssured.baseURI="http://101.53.154.57:8090";       //http://101.53.153.134:8090
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("X-Auth-Id", "da4fb5c6e93e74d3df8527599fa62642");
		JSONObject json = new JSONObject();			
		String id = ExcellUtilites.readData("sheet1", 1, 1);
		System.out.println("id------------"+id);
		json.put("orderId", id);
		json.put("reason", "आइटम दोषपूर्ण या काम नहीं करता है");
		json.put("comment", "");
		request.body(json.toJSONString());
		Response response = request.post("/returnService/order-return-initiate");
		int code = response.getStatusCode();
		AssertJUnit.assertEquals(code, 200);
		Reporter.log("api status code is  ="+response.getStatusCode() , true);
		Reporter.log("api response is ="+response.body().asString() , true);	
	}
}
