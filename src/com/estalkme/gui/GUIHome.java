package com.estalkme.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.w3c.dom.Document;

import com.estalkme.gui.guiutils.dialogTools;
import com.estalkme.tools.Constants;
import com.estalkme.tools.TextPrompt;
import com.estalkme.tools.ValidateFields;
import com.estalkme.tools.Values;
import com.estalkme.xmltools.XMLManageValues;
import com.estalkme.xmltools.XMLUtils;

public class GUIHome {

	public static JFrame createStartWindow(String title) throws Exception {

		// Window creation
		final JFrame window = new javax.swing.JFrame(title);

		// JFrame Icon
		window.setIconImage(ImageIO.read(GUIHome.class.getResource(Values.IMG_USER)));

		// Body
		JPanel body = new JPanel();

		// App welcome picture
		JLabel img = new JLabel(new ImageIcon(ImageIO.read(GUIHome.class.getResource(Values.IMG_ICON))), SwingConstants.LEFT);
		img.setAlignmentX(Component.CENTER_ALIGNMENT);
		body.add(img);
		body.setBackground(Color.WHITE);

		// Field First Name
		final JTextField fieldFirstName = new JTextField("");
		fieldFirstName.setBackground(SystemColor.controlHighlight);
		TextPrompt tpTFFirstName;
		tpTFFirstName = new TextPrompt("Barack", fieldFirstName);
		tpTFFirstName.setForeground(Color.DARK_GRAY);
		tpTFFirstName.changeAlpha(0.5f);
		tpTFFirstName.changeStyle(Font.ITALIC);
		fieldFirstName.setPreferredSize(Constants.dimTextField);
		fieldFirstName.setBorder(Constants.whiteline);
		fieldFirstName.setAlignmentX(Component.CENTER_ALIGNMENT);
		body.add(fieldFirstName); 

		// Field Last Name
		final JTextField fieldLastName = new JTextField("");
		fieldLastName.setBackground(SystemColor.controlHighlight);
		TextPrompt tpTFLastName;
		tpTFLastName = new TextPrompt("OBAMA", fieldLastName);
		tpTFLastName.setForeground(Color.DARK_GRAY);
		tpTFLastName.changeAlpha(0.5f);
		tpTFLastName.changeStyle(Font.ITALIC);
		fieldLastName.setPreferredSize(Constants.dimTextField);
		fieldLastName.setBorder(Constants.whiteline);
		fieldLastName.setAlignmentX(Component.CENTER_ALIGNMENT);
		body.add(fieldLastName);

		// Button
		JButton stalkIt = new JButton(Values.BT_STALK);
		stalkIt.setPreferredSize(Constants.dimButtonLittle);
		stalkIt.setBackground(Constants.white);
		stalkIt.setAlignmentX(Component.CENTER_ALIGNMENT);
		stalkIt.setBorder(Constants.whiteline);
		stalkIt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					final JFrame loading = GUILoading.loadingFrame();
					Runnable runnable = new Runnable() {
						public void run() {
							try {
								String firstName = fieldFirstName.getText().trim();
								String lastName = fieldLastName.getText().trim();
								if (ValidateFields.isValidName(firstName, lastName)) {
									Constants.firstName = firstName;
									Constants.lastName = lastName;
									// XML Creation or Update
									if (XMLUtils.existDocument(firstName, lastName)) {
										XMLUtils.updateXMLDocument(firstName, lastName);
									} else {
										XMLUtils.createNewXMLDocument(firstName, lastName);
									}
									// Write in estalkme_lastprocess.xml
									Document doc = XMLUtils.getXMLFileAsDocument(XMLUtils.getLastProcessXMLFile());
									doc = XMLManageValues.setLastProcessFirstAndLastNames(doc, firstName, lastName);
									XMLUtils.saveXMLDocumentAsFile(doc, XMLUtils.getLastProcessXMLFile());

									GUISearch search = new GUISearch(Values.SEARCH_TITLE, Constants.dimFrame, firstName, lastName);
									search.setLocationRelativeTo(null);
									search.setVisible(true);

									// Close Start Window
									window.setVisible(false); 
									window.dispose();
								} else {
									dialogTools.showErrorMsg(Values.TITLE_ERREUR, Values.MSG_ERREUR_VAL);
								}
								SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										loading.setVisible(false);
									}
								});
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					};
					new Thread(runnable).start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		body.add(stalkIt); 

		// Button load last 
		JButton reloadLast = new JButton("");
		reloadLast.setIcon(new ImageIcon(ImageIO.read(GUIHome.class.getResource("img/reload.png"))));
		reloadLast.setPreferredSize(new Dimension(32, 32));
		reloadLast.setBackground(Constants.white);
		reloadLast.setAlignmentX(Component.CENTER_ALIGNMENT);
		reloadLast.setBorder(Constants.whiteline);
		reloadLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFrame loading = GUILoading.loadingFrame();
				Runnable runnable = new Runnable() {
					public void run() {
						// Write in estalkme_lastprocess.xml
						try {
							Document doc = XMLUtils.getXMLFileAsDocument(XMLUtils.getLastProcessXMLFile());
							String f = XMLManageValues.getLastProcessFirstName(doc);
							String l = XMLManageValues.getLastProcessLastName(doc);
							
							Constants.firstName = f;
							Constants.lastName = l;

							GUISearch search = new GUISearch(Values.SEARCH_TITLE, Constants.dimFrame, f, l);
							search.setLocationRelativeTo(null);
							search.setVisible(true);

							// Close Start Window
							window.setVisible(false); 
							window.dispose();
						} catch (Exception eReload) {
							eReload.printStackTrace();
						}
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								loading.setVisible(false);
							}
						});
					}
				};
				new Thread(runnable).start();
			}
		});
		body.add(reloadLast);

		window.getContentPane().add(body, BorderLayout.CENTER);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		return window;
	}
}
