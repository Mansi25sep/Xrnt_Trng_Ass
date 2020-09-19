package com.qa.xoriant.apiDemo.test;

import java.text.ParseException;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import pojoandData.BuildcreateUserData;

public class CreateUser extends BaseClass {
	
	BuildcreateUserData cud = new BuildcreateUserData();
	ResponseSpecification resspec;
	Response response;

	
	@Test(dataProvider="CreateNewUser")
	public void createNewUserWithIDValidation(String name, String job)
	{
		
		baseURL();
		String responseBody = RestAssured.given().body(cud.createUserData(name,job))
		.when().post("/api/users")
		.then().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath jsp = new JsonPath(responseBody);
		
		String resp = jsp.getString("id");
		Assert.assertTrue(resp!=null);
	}
	
	@Test(dataProvider="CreateNewUser")
	public void createNewUserWithDateValidation(String name, String job) throws ParseException
	{
		
		baseURL();
		String responseBody = RestAssured.given().body(cud.createUserData(name,job))
		.when().post("/api/users")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath jsp = new JsonPath(responseBody);
		
		String actualdatetime = jsp.getString("createdAt");
		String actualdate[] = actualdatetime.split("T");
		System.out.println(actualdate[0]);  
		   LocalDate expectedDate = LocalDate.now();
		   System.out.println(expectedDate.toString());
		Assert.assertEquals(actualdate[0], expectedDate.toString());
	}
	
	@DataProvider(name = "CreateNewUser")
	public static Object[][] getData()
	{
		
		return new Object[][]
			{
				{"User1","Associate"},
				{"User2","Engineer"}
			};
		
	}

}
