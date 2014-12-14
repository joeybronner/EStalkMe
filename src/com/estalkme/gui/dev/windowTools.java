package com.estalkme.gui.dev;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.estalkme.gui.GUISearch;
import com.estalkme.tools.Constants;
import com.estalkme.tools.TextPrompt;
import com.estalkme.tools.ValidateFields;

/**
 * <p>
 * This class contain all methods of window creation:
 * <ul>
 * 		<li>Starting (first & last name)</li>
 *  	<li>Home (list of links)</li>
 * </ul>
 * </p>
 * @param title Window title
 * @return JFrame
 */
public class windowTools {

	/**
	 * <p>
	 * Used to create the start window.
	 * </p>
	 * 
	 * @param title Window title
	 * @return JFrame
	 */
	protected static JFrame createStartWindow(String title) throws Exception {
		
		// Window creation
		final JFrame window = new javax.swing.JFrame(title);

		// Body
		JPanel body = new JPanel();
		body.setBorder(Constants.blackline);

		// App welcome picture
		JLabel img = new JLabel(new ImageIcon(Constants.urlImageAppWelcome), SwingConstants.LEFT);
		img.setAlignmentX(Component.CENTER_ALIGNMENT);
		body.add(img);

		// Field First Name
		final JTextField fieldFirstName = new JTextField();
		TextPrompt tpTFFirstName;
		tpTFFirstName = new TextPrompt("Barack", fieldFirstName);
		tpTFFirstName.setForeground(Color.GRAY);
		tpTFFirstName.changeAlpha(0.5f);
		tpTFFirstName.changeStyle(Font.ITALIC);
		fieldFirstName.setPreferredSize(Constants.dimTextField);
		fieldFirstName.setBorder(Constants.whiteline);
		fieldFirstName.setAlignmentX(Component.CENTER_ALIGNMENT);
		body.add(fieldFirstName); 
		
		// Field Last Name
		final JTextField fieldLastName = new JTextField();
		TextPrompt tpTFLastName;
		tpTFLastName = new TextPrompt("OBAMA", fieldLastName);
		tpTFLastName.setForeground(Color.GRAY);
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
						
						GUISearch search = new GUISearch("EStalkMe - Home", Constants.dimFrame, firstName, lastName);
						// search.pack();
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

	/**
	 * <p>
	 * Used to create the home window.
	 * This window retrieve the list of URL found.
	 * </p>
	 * 
	 * @param title Window title
	 * @param size	Window size
	 * @return JFrame
	 */
	/*
	protected static JFrame createHomeWindow(String title, Dimension size, 
			String firstName, String lastName)throws Exception {
		
		// Window creation
		JFrame window = new javax.swing.JFrame(title);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set size preference
		window.setPreferredSize(size);

		// Body
		JPanel body = new JPanel();
		body.setBorder(Constants.blackline);
		
		List<String> googleSearchResults = GoogleResults.readAsList(firstName, lastName);
        
        // JList
		JList<Object> list = new JList<Object>(googleSearchResults.toArray());
        body.add(list);

		window.getContentPane().add(body, BorderLayout.CENTER);

		return window;
	}*/
	
}
