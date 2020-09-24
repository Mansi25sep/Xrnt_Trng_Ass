package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestUtils extends BaseClass{
	
	public static RequestSpecification reqspec;
	public static Response resp;
	
	public RequestSpecification reqSpec(String pageno)
	{
		if(reqspec==null)
		{
			reqspec = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseURI")).
					addQueryParam("page", pageno).setContentType(ContentType.JSON).build();
			return reqspec;
		}
		return reqspec;
		
	}
	
	public static Response getReqEndpoint(String pageno)
	{
		resp = RestAssured.given().queryParam("page", pageno).when().get("/api/users");
		return resp;
	}
	
	public static JSONObject FileReader(String file) throws IOException, ParseException
	{
		JSONParser jsp = new JSONParser();
		FileReader flr = new FileReader(file);
		JSONObject jsonobj = (JSONObject)jsp.parse(flr);
		
		return jsonobj;
	}
	

}
