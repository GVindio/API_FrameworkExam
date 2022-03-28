package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static io.restassured.RestAssured.*;

public class ReadAllProducts {
	
	@Test
	public void readAllProducts() {
		
		SoftAssert softAssert = new SoftAssert();
		
		Response response = 
		
		given()
			.baseUri("https://techfios.com/api-prod/api/product")
			.header("Content-Type","application/json; chasrset=UTF-8")
			.auth().preemptive().basic("demo@techfios.com", "abc123")
			.log().all().
		when().log().all()
			.get("/read.php").
		then().log().all()
			.extract().response();
		
		int actualStatusCode = response.getStatusCode();
		System.out.println("actualStatusCode: " + actualStatusCode);
		Assert.assertEquals(actualStatusCode, 200);
		
		String actualHeader =response.getHeader("Content-Type");
		System.out.println("actualHeader: " + actualHeader);
		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");
		
		
		String actualResponseBody = response.getBody().asString();
		System.out.println("actualResponseBody: " + actualResponseBody);
		
		JsonPath jp = new JsonPath(actualResponseBody);
		String firstProductId = jp.get("records[0].id");
		System.out.println("firstProductId: " + firstProductId);
		
		if(firstProductId !=null) {
			System.out.println("Records are not null.");
			
		}else {
			System.out.println("Records are null");
		}
		
	}

}
