package com.estalkme.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.estalkme.google.api.GoogleResults;

import net.miginfocom.swing.MigLayout;

import javax.swing.JList;

public class GUISearch extends JFrame {

	private static JPanel window;
	private static JList list;

	/**
	 * Create the frame.
	 */
	public GUISearch(String title, Dimension size, String firstName, String lastName) {
		try {
			init(this);
			fillList(firstName, lastName);
		} catch (Exception e) {
			System.out.println("Erreur lors de la recherche... <com.estalkme.gui.GUISearch.java>");
		}
	}

	@SuppressWarnings("deprecation")
	private static void fillList(String firstName, String lastName) throws Exception {
		java.util.List<String> googleSearchResults = GoogleResults.readAsList(firstName, lastName);
		// JList
		list.setListData(googleSearchResults.toArray());
	}

	private static void init(JFrame f) {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 450, 300);
		window = new JPanel();
		window.setBorder(new EmptyBorder(5, 5, 5, 5));
		window.setLayout(new BorderLayout(0, 0));
		f.setContentPane(window);

		JPanel panelBody = new JPanel();
		window.add(panelBody, BorderLayout.CENTER);
		panelBody.setLayout(new MigLayout("", "[250px,grow,fill]", "[250px,grow,fill]"));
		
		list = new JList();
		panelBody.add(list, "cell 0 0,grow");

		JPanel panelFooter = new JPanel();
		window.add(panelFooter, BorderLayout.SOUTH);

		JButton btnSuivant = new JButton("Suivant");
		panelFooter.add(btnSuivant);

		JPanel panel = new JPanel();
		window.add(panel, BorderLayout.EAST);
		panel.setLayout(new MigLayout("", "[158px]", "[14px]"));

		JLabel lblAjouterCeLien = new JLabel("Ajouter ce lien aux liens valid\u00E9s ?");
		panel.add(lblAjouterCeLien, "cell 0 0,alignx left,aligny top");
	}

}
