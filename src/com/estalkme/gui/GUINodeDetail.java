package com.estalkme.gui;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GUINodeDetail extends JFrame {

	private static JPanel window;

	public GUINodeDetail(String link) {
		try {
			setAlwaysOnTop(true);
			init(this);	
		} catch (Exception e) {
			System.out.println("Erreur... <com.estalkme.gui.GUINoInternetConnection.java>\n" + e);
		}

	}


	private void init(JFrame f) throws IOException {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 350, 160);

		window = new JPanel();
		window.setBackground(Color.WHITE);
		window.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

}
