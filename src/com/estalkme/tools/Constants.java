package com.estalkme.tools;

import java.awt.Color;
import java.awt.Dimension;

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
	
	// Images
	public static String urlImageConnected = "WebContent/img/ic_www_connection_ok.png";
	public static String statusConnected = "Server avalaible";
	public static String urlImageNotConnected = "WebContent/img/ic_www_connection_nok.png";
	public static String statusNotConnected = "Server error";
	public static String urlImageAppWelcome = "WebContent/img/im_profile.png";
	
	// Google Search API
	public static String GoogleSearchAdr = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
}
