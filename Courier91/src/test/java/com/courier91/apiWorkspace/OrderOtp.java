package com.courier91.apiWorkspace;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.courier91.pageObject.OrdersToBeDeliveredPage;;

public class OrderOtp
{
	
	
	
     	@Test
		public static String getOtp() 
		{
	
		RestAssured.baseURI="http://101.53.154.57:8090";
		
		//	http://101.53.158.117:8090
		
		//http://101.53.153.134:8090
		
		Response res = given().
				queryParam("X-Auth-Id","da4fb5c6e93e74d3df8527599fa6264").
				queryParam("Content-Type","application/json" ).log().all().
		when().
			get("/orderService/order-by-id/"+OrdersToBeDeliveredPage.getOrderIdForDeliver()).
		then().
			assertThat().statusCode(200).contentType(ContentType.JSON).and().
		extract().response();
		
		
		String respString=res.asString();
		JsonPath js=new JsonPath(respString);
		String otp=js.get("orderOTP");
		Reporter.log("otp is = "+otp , true);
		return otp;
			
	}
     	
}
