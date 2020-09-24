package com.qa.xoriant.apiDemo.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.hamcrest.Matchers.*;
import base.BaseClass;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojoandData.CreateUserPOJO;
import utils.TestUtils;

public class CreateUser_gson_jsonSimple extends BaseClass {
	
	@Test
	public void createUser_usingPOJOClassContructor()
	{
		baseURL();
		CreateUserPOJO cupojo = new CreateUserPOJO("XoriantUser", "STE");
		given().header("content-type", "application/json").body(cupojo)
		.when().post("/api/users")
		.then().log().all().assertThat().statusCode(201).body("name", equalToIgnoringCase("XoriantUser"));
		
	}
	
	@Test
	public void createUser_usingPOJOClassContructorasObjectinGSON()
	{
		Gson gs = new Gson();
		baseURL();
		CreateUserPOJO cupojo = new CreateUserPOJO("XoriantUser", "STE");
		given().header("content-type", "application/json").body(gs.toJson(cupojo))
		.when().post("/api/users")
		.then().log().all().assertThat().statusCode(201).body("name", equalToIgnoringCase("XoriantUser"));
		
	}
	
	@Test
	public void createUser_GSONHashMap()
	{
		Gson gs = new Gson();
		HashMap<String,String> hm = new HashMap<String, String>();
		hm.put("name", "XEmployee");
		hm.put("job", "API_Automation");
		baseURL();
		String respbody = given().header("content-type", "application/json")
		.body(gs.toJson(hm))
		.when().post("/api/users")
		.then().log().all().extract().response().asString();
		
		JsonPath jp = new JsonPath(respbody);

		Assert.assertEquals(jp.get("name"), "XEmployee");
		Assert.assertEquals(jp.get("job"), "API_Automation");
		Assert.assertTrue(jp.get("id") != null);
		
	}
	
	@Test
	public void createUser_GSONBuilder()
	{
		Gson gs = new Gson();
		GsonBuilder gb = new GsonBuilder();
		HashMap<String,String> hm = new HashMap<String, String>();
		hm.put("name", "Xornet");
		hm.put("job", "Development");
		baseURL();
		Response respbody = given().header("content-type", "application/json")
		.body(gs.toJson(hm))
		.when().post("/api/users")
		.then().log().all().extract().response();
		
		gs = gb.setPrettyPrinting().create();
		gs.toJson(respbody.asString());
		
		JsonPath jp = new JsonPath(respbody.asString());

		Assert.assertEquals(jp.get("name"), "Xornet");
		Assert.assertEquals(jp.get("job"), "Development");
		Assert.assertTrue(jp.get("id") != null);
		
	}
	

	@Test
	public void CreateUser_JsonParser_FileReader() throws IOException, ParseException {
		baseURL();
		String respbody = given().header("content-type", "application/json")
				.body(TestUtils.FileReader("./src/test/resources/createUserPayload.json")).when().post("/api/users")
				.then().log().all().extract().response().asString();

		JsonPath jp = new JsonPath(respbody);

		Assert.assertEquals(jp.get("name"), "morpheus");
		Assert.assertTrue(jp.get("id") != null);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void CreateUser_FileWriter() throws IOException, ParseException {
		JSONObject jb = new JSONObject();
		FileWriter fw = new FileWriter("./src/test/resources/createUserPayload1.json");
		jb.put("name", "User123");
		jb.put("job", "Tester");
		fw.write(jb.toString());
		fw.flush();
		fw.close();
		
		baseURL();

		String respbody = given().header("content-type", "application/json")
				.body(TestUtils.FileReader("./src/test/resources/createUserPayload1.json"))
				.when().post("/api/users")
				.then().log().all().extract().response().asString();

		JsonPath jp = new JsonPath(respbody);

		Assert.assertEquals(jp.get("name"), "User123");
		Assert.assertEquals(jp.get("job"), "Tester");
		Assert.assertTrue(jp.get("id") != null);

	}
	
	

}
