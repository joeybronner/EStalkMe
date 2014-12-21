package com.estalkme.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.estalkme.google.api.GoogleResults;
import com.estalkme.tools.Constants;
import com.estalkme.xmltools.XMLUtils;

import net.miginfocom.swing.MigLayout;

import javax.swing.JList;
import javax.swing.JScrollBar;

import java.awt.SystemColor;

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
			System.out.println("Erreur lors de la recherche... <com.estalkme.gui.GUISearch.java>\n" + e);
		}
	}

	@SuppressWarnings("deprecation")
	private void fillList(String firstName, String lastName) throws Exception {
		java.util.List<String> googleSearchResults = GoogleResults.readAsList(firstName, lastName);
		// JList
		list.setListData(googleSearchResults.toArray());
	}

	private void init(JFrame f) throws Exception {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 450, 450);
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
		btnSuivant.setBackground(SystemColor.controlHighlight);
		panelFooter.add(btnSuivant);

		JPanel panel = new JPanel();
		window.add(panel, BorderLayout.EAST);
		panel.setLayout(new MigLayout("", "[158px]", "[14px][][][]"));

		JLabel lblAjouterCeLien = new JLabel("Ajouter ce lien aux liens valides ?");
		panel.add(lblAjouterCeLien, "cell 0 0,alignx left,aligny top");
		
		JButton btnSeFier = new JButton();
		btnSeFier.setBackground(SystemColor.controlHighlight);
		btnSeFier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String link = (String) list.getSelectedValue();
				if (link != null && !link.equals("")) {
					XMLUtils.addLink(XMLUtils.getXMLFile(Constants.firstName, Constants.lastName), "good", link);
				} else {
					System.out.println("You must select a link if the list.");
				}
			}
		});
		btnSeFier.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/good.PNG"))));
		panel.add(btnSeFier, "cell 0 2,alignx center");
		
		JButton btnNePasSe = new JButton();
		btnNePasSe.setBackground(SystemColor.controlHighlight);
		btnNePasSe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String link = (String) list.getSelectedValue();
				if (link != null && !link.equals("")) {
					XMLUtils.addLink(XMLUtils.getXMLFile(Constants.firstName, Constants.lastName), "bad", link);
				} else {
					System.out.println("You must select a link if the list.");
				}
			}
		});
		btnNePasSe.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/bad.PNG"))));
		panel.add(btnNePasSe, "cell 0 3,alignx center");
	}

}
