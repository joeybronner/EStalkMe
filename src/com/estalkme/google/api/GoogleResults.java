package com.estalkme.google.api;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.estalkme.tools.Constants;
import com.google.gson.Gson;

public class GoogleResults {

	// Gobal Variables
	private ResponseData responseData;
	public ResponseData getResponseData() { return responseData; }
	public void setResponseData(ResponseData responseData) { this.responseData = responseData; }
	@Override
	public String toString() { return "ResponseData[" + responseData + "]"; }

	public static class ResponseData {
		private List<Result> results;
		public List<Result> getResults() { return results; }
		public void setResults(List<Result> results) { this.results = results; }
		@Override
		public String toString() { return "Results[" + results + "]"; }
	}

	public static class Result {
		private String url;
		private String title;
		public String getUrl() { return url; }
		public String getTitle() { return title; }
		public void setUrl(String url) { this.url = url; }
		public void setTitle(String title) { this.title = title; }
		@Override
		public String toString() { return "Result[url:" + url +",title:" + title + "]"; }
	}

	public static List<String> readAsList(String firstName, String lastName) 
			throws Exception {

		// Reading from URL
		String query = firstName + " " + lastName;
		String charset = "UTF-8";
		List<String> result = new ArrayList<String>();

		Constants.searchSteps.add(4);
		Constants.searchSteps.add(8);
		Constants.searchSteps.add(12);
		Constants.searchSteps.add(16);
		
		for (int i=0; i<Constants.searchSteps.size();i++) {
			// Send URL
			URL url = new URL(Constants.GoogleSearchAdr + Constants.searchSteps.get(i) + Constants.GoogleSearchAdr2 +
					URLEncoder.encode(query, charset));
			System.out.println(url.toString());
			Reader reader = new InputStreamReader(url.openStream(), charset);
			GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

			for (int j=0; j<4; j++){
				try {
					result.add(results.getResponseData().getResults().get(j).getUrl());
				} catch (Exception e) {
					continue;
				}
			}
		}
		return result;
	}
}
