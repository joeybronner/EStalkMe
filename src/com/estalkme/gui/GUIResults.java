package com.estalkme.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.awt.Color;

public class GUIResults extends JFrame {

	private static final long serialVersionUID = 4105303052246253432L;
	private static JPanel window;

	public GUIResults(String title) {
		try {
			init(this);
		} catch (Exception e) {
			System.out.println("Erreur lors de la recherche... <com.estalkme.gui.GUISearch.java>\n" + e);
		}
	}

	private void init(JFrame f) throws Exception {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		window = new JPanel();
		window.setBackground(Color.WHITE);
		window.setBorder(new EmptyBorder(5, 5, 5, 5));
		window.setLayout(new BorderLayout(0, 0));
		f.setContentPane(window);
	}
}
