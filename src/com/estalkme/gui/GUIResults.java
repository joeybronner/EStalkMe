package com.estalkme.gui;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import com.estalkme.tools.Constants;
import com.estalkme.xmltools.XMLRetrieveValues;
import com.estalkme.xmltools.XMLUtils;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JSeparator;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

public class GUIResults extends JFrame {

	private static final long serialVersionUID = 4105303052246253432L;
	private static JPanel window;
	Document doc;
	String firstName, lastName;
	JLabel lblPrnom;
	JLabel lblNom;

	public GUIResults(String title) {
		try {
			init(this);
			loadInfos();
		} catch (Exception e) {
			System.out.println("Erreur lors de la recherche... <com.estalkme.gui.GUISearch.java>\n" + e);
		}
	}
	
	private void loadInfos() throws XPathExpressionException {
		doc = XMLUtils.getXMLFileAsDocument(XMLUtils.getXMLFile(Constants.firstName,Constants.lastName));
		lblPrnom.setText(XMLRetrieveValues.getFirstName(doc));
		lblNom.setText(XMLRetrieveValues.getLastName(doc));
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
		
		JPanel left = new JPanel();
		window.add(left, BorderLayout.WEST);
		left.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("185px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("17px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblCarteDidentitNumrique = new JLabel("Carte d'identit\u00E9 num\u00E9rique");
		lblCarteDidentitNumrique.setFont(new Font("Tahoma", Font.BOLD, 14));
		left.add(lblCarteDidentitNumrique, "2, 2, left, top");
		
		JLabel imgUser = new JLabel("");
		imgUser.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/user.png"))));
		left.add(imgUser, "2, 4, center, default");
		
		lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		left.add(lblPrnom, "2, 6, center, default");
		
		lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		left.add(lblNom, "2, 8, center, default");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		left.add(separator, "2, 10");
		
		JPanel header = new JPanel();
		window.add(header, BorderLayout.NORTH);
		
		JPanel footer = new JPanel();
		window.add(footer, BorderLayout.SOUTH);
		
		JPanel right = new JPanel();
		window.add(right, BorderLayout.EAST);
		
		JPanel body = new JPanel();
		window.add(body, BorderLayout.CENTER);
	}
}
