package com.estalkme.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.estalkme.gui.guiutils.dialogTools;
import com.estalkme.tools.Constants;
import com.estalkme.tools.TextPrompt;
import com.estalkme.tools.ValidateFields;
import com.estalkme.xmltools.XMLUtils;

public class GUIHome {

	public static JFrame createStartWindow(String title) throws Exception {
		
		// Window creation
		final JFrame window = new javax.swing.JFrame(title);
		
		// JFrame Icon
		window.setIconImage(ImageIO.read(new File(Constants.ICON)));

		// Body
		JPanel body = new JPanel();

		// App welcome picture
		JLabel img = new JLabel(new ImageIcon(Constants.urlImageAppWelcome), SwingConstants.LEFT);
		img.setAlignmentX(Component.CENTER_ALIGNMENT);
		body.add(img);
		body.setBackground(Color.WHITE);

		// Field First Name
		final JTextField fieldFirstName = new JTextField("Joey");
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
		final JTextField fieldLastName = new JTextField("Bronner");
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
		JButton stalkIt = new JButton("Stalk it!");
		stalkIt.setPreferredSize(Constants.dimButtonLittle);
		stalkIt.setBackground(Constants.white);
		stalkIt.setAlignmentX(Component.CENTER_ALIGNMENT);
		stalkIt.setBorder(Constants.whiteline);
		stalkIt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String firstName = fieldFirstName.getText().trim();
					String lastName = fieldLastName.getText().trim();
					if (ValidateFields.isValidName(firstName, lastName)) {
						System.out.println(firstName + " " + lastName + " est valide.");
						
						// XML Creation
						if (XMLUtils.existDocument(firstName, lastName)) {
							XMLUtils.updateXMLDocument(firstName, lastName);
						} else {
							XMLUtils.createNewXMLDocument(firstName, lastName);
						}
						
						Constants.firstName = firstName;
						Constants.lastName = lastName;
						
						GUISearch search = new GUISearch("EStalkMe - Search", Constants.dimFrame, firstName, lastName);
						search.setLocationRelativeTo(null); // center
						search.setVisible(true);
						
						// Close Start Window
						window.setVisible(false); 
						window.dispose();
					} else {
						dialogTools.showErrorMsg("Error", "Please enter a valid first and last name format (e.g. \"Barack OBAMA\")");
					}
				} catch (Exception e1) {
					// Error
					e1.printStackTrace();
				}
			}
		});
		body.add(stalkIt); 

		window.getContentPane().add(body, BorderLayout.CENTER);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		return window;
	}

}
