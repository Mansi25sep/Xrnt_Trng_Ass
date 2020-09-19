package utils;

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
	

}
