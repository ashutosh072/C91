package com.courier91.apiWorkspace;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.courier91.pageObject.ReturnOrderPage;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostAPI {
         @Test
	public static void callApiForREadyToReturn()
	{
		RestAssured.config=RestAssured.config().sslConfig(SSLConfig.sslConfig().allowAllHostnames());
		RestAssured.baseURI="http://164.52.198.64:8069";
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("X-Auth-Id", "da4fb5c6e93e74d3df8527599fa62642");
		request.body("{}".toString());
		Response response = request.post("/orderdeny/"+ReturnOrderPage.getOrderIdforReadytoReturn());
		System.out.println(response.body().asString());
		Reporter.log("api response is ="+response.body().asString() , true);
        System.out.println(response.getStatusCode());
        Reporter.log("api status code is  ="+response.getStatusCode() , true);
		//int statusCode = response.getStatusCode();
		//Assert.assertEquals(statusCode, "200");



	}

}
