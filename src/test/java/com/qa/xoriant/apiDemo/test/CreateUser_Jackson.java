package com.qa.xoriant.apiDemo.test;

import java.io.File;
import java.io.IOException;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;
import pojoandData.CreateUserPOJO;

public class CreateUser_Jackson {
	
	
	@Test
	public void createUser_usingObjectMapper_WriteValue() throws JsonGenerationException, JsonMappingException, IOException
	{
		CreateUserPOJO userpojo = new CreateUserPOJO("User10","HR");
		ObjectMapper om = new ObjectMapper();
		File file = new File("./src/test/resources/ObjMapper.json");
		
		om.writeValue(file, userpojo);
		String respbody = given().header("content-type","application/json").
		body(file).when().post("https://reqres.in/api/users").
		then().log().all().extract().response().asString();
		
		JsonPath jp = new JsonPath(respbody);
		
		Assert.assertEquals(jp.get("name"), "User10");
		Assert.assertEquals(jp.get("job"), "HR");
		Assert.assertTrue(jp.get("id") != null);
		
		
}
	
	@Test
	public void createUser_usingObjectMapper_ReadValue() throws JsonGenerationException, JsonMappingException, IOException
	{
		ObjectMapper om = new ObjectMapper();
		File file = new File("./src/test/resources/ObjMapper.json");
		CreateUserPOJO createuserpojo = om.readValue(file, CreateUserPOJO.class);
	
		String respbody = given().header("content-type","application/json").
		body(createuserpojo).when().post("https://reqres.in/api/users").
		then().log().all().extract().response().asString();
		
		JsonPath jp = new JsonPath(respbody);
		
		Assert.assertEquals(jp.get("name"), "User10");
		Assert.assertEquals(jp.get("job"), "HR");
		Assert.assertTrue(jp.get("id") != null);
		
		
}

}
