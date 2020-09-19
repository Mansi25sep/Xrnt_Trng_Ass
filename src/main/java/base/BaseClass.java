package base;

import java.io.FileInputStream;
import java.util.Properties;

import io.restassured.RestAssured;

public class BaseClass {
	
	public static Properties prop;
	

	static {
		try {

			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/config/config.properties");

			prop.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void baseURL()
	{
		RestAssured.baseURI=prop.getProperty("baseURI");
	}
}
	
	


