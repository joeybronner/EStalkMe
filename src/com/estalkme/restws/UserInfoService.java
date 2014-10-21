package com.estalkme.restws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author Joey Bronner
 * 
 */

// Example of URL : localhost:8080/EStalkMe/rest/UserInfoService/name/Joey

//@Path = The URI path that a resource class will serve requests for.
@Path("UserInfoService")
public class UserInfoService {

	//@GET = HTTP GET requests.
	@GET
	//@Path = The URI path that a resource class method will serve requests for.
	@Path("/name/{i}")
	//@Produces = media type(s) of a resource class can produce.
	@Produces(MediaType.TEXT_XML) 
	//@PathParam = value of URI parameter into the method
	public String userName(@PathParam("i") String i) {
		String name = i;
		return "<User>" + "<Name>" + name + "</Name>" + "</User>";
	}

	@GET 
	@Path("/age/{j}") 
	@Produces(MediaType.TEXT_XML)
	public String userAge(@PathParam("j") int j) {
		int age = j;
		return "<User>" + "<Age>" + age + "</Age>" + "</User>";
	}
}