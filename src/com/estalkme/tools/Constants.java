package com.estalkme.tools;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Constants
{
	//Styling
	public static Border blackline = BorderFactory.createLineBorder(Color.black);
	public static Border whiteline = BorderFactory.createLineBorder(Color.white);
	
	// Panel, frame and composents sizes
	public static final Dimension dimFrame = new Dimension(600, 400);
	public static final Dimension dimHeader = new Dimension(600, 100);
	public static final Dimension dimBodyL = new Dimension(220, 280);
	public static final Dimension dimBodyR = new Dimension(380, 280);
	public static final Dimension dimFooter = new Dimension(600, 20);
	public static final Dimension dimTextField = new Dimension(200, 24);
	public static final Dimension dimButtonLittle = new Dimension(200, 24);
	
	// Colors
	public static Color backColor = Color.decode("#DADFE1");
	public static Color white = Color.decode("#FFFFFF");
	public static Color GREEN = Color.decode("#27ae60");
	public static Color RED = Color.decode("#c0392b");
	public static Color GRAY = Color.decode("#7f8c8d");
	
	// Images
	public static String urlImageConnected = "WebContent/img/ic_www_connection_ok.png";
	public static String statusConnected = "Server avalaible";
	public static String urlImageNotConnected = "WebContent/img/ic_www_connection_nok.png";
	public static String statusNotConnected = "Server error";
	public static String urlImageAppWelcome = "WebContent/img/im_profile.png";
	
	// Google Search API
	public static String GoogleSearchAdr = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&start=";
	public static List<Integer> searchSteps = new ArrayList<Integer>();
	public static String GoogleSearchAdr2 = "&q=";
	
	// First & Last Name
	public static String firstName;
	public static String lastName;
	public static String name;
	public static List<String> goodLinks = new ArrayList<String>();
	public static List<String> badLinks = new ArrayList<String>();
	
	// File
	public static String fileName;
	public static String SAVE_PATH = "src/com/estalkme/xmlresults/";
	public static String filePath;
	
	// Refresh (no connection)
	public static boolean refresh = false;
}
