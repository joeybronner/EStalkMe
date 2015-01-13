package com.estalkme.gui;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.io.IOException;

import com.estalkme.tools.Constants;
import com.estalkme.xmltools.XMLUtils;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.xml.xpath.XPathExpressionException;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUISocialLink extends JFrame {

	private static final long serialVersionUID = 2606571557996673249L;
	private static JPanel window;
	private JTextField textField;

	public GUISocialLink(String type) {
		setAlwaysOnTop(true);
		try {
			init(this, type);
		} catch (Exception e) {
			System.out.println("Erreur lors de la recherche... <com.estalkme.gui.GUISocialLink.java>\n" + e);
		}
	}

	private void init(JFrame f, final String type) throws IOException {
		f.setBounds(100, 100, 450, 90);
		f.setTitle("EStalkMe - Ajouter/Modifier un lien social");

		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		// Image Social Network
		JLabel imgSocial = new JLabel("");
		imgSocial.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/"+ type +".png"))));
		getContentPane().add(imgSocial, "2, 2, right, default");

		textField = new JTextField();
		getContentPane().add(textField, "4, 2, fill, default");
		textField.setColumns(10);

		JButton btnValider = new JButton("Valider");
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String newValue = textField.getText();
					if (!newValue.equals("") && newValue != null) {
						XMLUtils.addSocialLink(XMLUtils.getXMLFile(Constants.firstName, Constants.lastName), type, newValue);
						setVisible(false); 
						dispose();
					}
				} catch (XPathExpressionException e) {
					System.out.println("Erreur lors de la création/modification du noeud <social>/... \n" + e);
				}
			}
		});
		btnValider.setBackground(SystemColor.controlHighlight);
		getContentPane().add(btnValider, "6, 2, right, default");
	}

}
