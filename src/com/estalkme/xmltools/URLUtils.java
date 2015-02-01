package com.estalkme.xmltools;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLUtils {

	public static void openWebpage(URL url) {
		try {
			openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getDomainName(String url) {
		try {
			URI uri = new URI(url);
			String domain = uri.getHost();
			return domain.startsWith("www.") ? domain.substring(4) : domain;
		} catch(Exception e) {
			return url;
		}
	}

	/**
	 * Retrieves the Page's title.
	 * 
	 * If any problem during the retrieving, we return the URL
	 * 
	 * @param URL	The URL as a String
	 * @return		Title in the best case or URL if any problem encoured
	 */
	public static String getTitle(String URL) {
		String startTag = "<title>";
		String endTag = "</title>";
		int startTagLength = startTag.length();
		BufferedReader bufReader;
		String line;
		boolean foundStartTag = false;
		boolean foundEndTag = false;
		int startIndex, endIndex;
		String title = "";

		try {
			URL theURL = new URL(URL);
			bufReader = new BufferedReader( new InputStreamReader(theURL.openStream()) );

			while((line = bufReader.readLine()) != null && !foundEndTag) {
			
				if( !foundStartTag && (startIndex = line.toLowerCase().indexOf(startTag)) != -1) {
					foundStartTag = true;
				}
				else {
					startIndex = -startTagLength;
				}

				if(foundStartTag && (endIndex = line.toLowerCase().indexOf(endTag)) != -1) {
					foundEndTag = true;
				}
				else {
					endIndex = line.length();
				}

				if(foundStartTag || foundEndTag) {
					title += line.substring( startIndex + startTagLength, endIndex );
				}
			}
			bufReader.close();

			if(title.length() > 0) {
				return title;
			} else {
				return URL;
			}
		} catch(IOException e) {
			return URL;
		}

	}

	/**
	 * Cuts a long sentence into a desired number of words.
	 * 
	 * @param s			The full String
	 * @param nbOfWords	The number of word to get back
	 * @return			A String containing the quantity of desired words 	
	 */
	public static String getFirstWords(String s, int nbOfWords) {
		try {
			Pattern pattern = Pattern.compile("([\\S]+\\s*){1," + nbOfWords + "}");
			Matcher matcher = pattern.matcher(s);
			matcher.find();
			return matcher.group();	
		} catch (Exception e) {
			return s;
		}
	}
}