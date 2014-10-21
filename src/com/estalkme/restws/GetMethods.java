package com.estalkme.restws;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GetMethods {
	/**
	 * Returns client response.
	 * e.g : 
	 * GET http://localhost:8080/RESTfulWS/rest/UserInfoService/name/Pavithra 
	 * returned a response status of 200 OK
	 *
	 * @param service
	 * @return
	 */
	public static String getClientResponse(WebResource resource) {
		return resource.accept(MediaType.TEXT_XML).get(ClientResponse.class)
				.toString();
	}

	/**
	 * Returns the response as XML
	 * e.g : <User><Name>Pavithra</Name></User> 
	 * 
	 * @param service
	 * @return
	 */
	public static String getResponse(WebResource resource) {
		return resource.accept(MediaType.TEXT_XML).get(String.class);
	}
}
