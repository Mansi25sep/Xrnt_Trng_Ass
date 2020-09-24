package com.qa.xoriant.apiDemo.test;

import java.util.List;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import base.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.TestUtils;

public class ListUsers extends BaseClass{
	
	Response resp;
	RequestSpecification reqSpec;
	
	@Test
	@Parameters("pgno")
	public void UserListReq(String pgno)
	{
		baseURL();
		resp=TestUtils.getReqEndpoint(pgno);
		int res = resp.getStatusCode();
		System.out.println(res);
		Assert.assertEquals(200, res);
	}
	
	@Parameters("pgno")
	@Test
	public void UserListReqwithGiven(String pgno)
	{
		baseURL();
		resp =TestUtils.getReqEndpoint(pgno);
		int res= resp.getStatusCode();
		System.out.println(res);
		Assert.assertEquals(200, res);
	}
	
	@Parameters("pgno")
	@Test
	public void GetfirstNamesingRestAssuredBodyMethod(String pgno)
	{
		baseURL();
		TestUtils.getReqEndpoint(pgno).then().statusCode(200).and().
		body("data.first_name", hasItem("Lindsay"));
	}

	@Test(dataProvider="GetPageNo")
	public void GetIDusingJsonPath(String pageno)
	{
		baseURL();
		resp = TestUtils.getReqEndpoint(pageno).then().statusCode(200).extract().response();
		String resbody = resp.body().asString();
		JsonPath jsp = new JsonPath(resbody);
		List<Integer> id = jsp.get("data.id");
		System.out.println(resbody);
		Assert.assertEquals(true, id.contains(7));
	}
	
	@DataProvider(name = "GetPageNo")
	public static Object[][] getData()
	{
		Object[][] obj = new Object[1][1];
		obj[0][0]="2";
		
		return obj;
		
	}
	
	@Test
	public void JSONSchemaValidator()
	{
		given().contentType(ContentType.JSON).when().
		get("https://reqres.in/api/users?page=2").then().assertThat().statusCode(200).and().
		body(matchesJsonSchemaInClasspath("ListUserJSONSchema.json"));
	}
}