package com.estalkme.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.estalkme.tools.Constants;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class GUINoInternetConnection extends JFrame {

	private JFrame frame;
	private static JPanel window;
	
	public GUINoInternetConnection(JFrame search, String firstName, String lastName) {
		setAlwaysOnTop(true);
		init(this, search);
	}

	private void init(JFrame f, JFrame search) {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 350, 180);
		
		window = new JPanel();
		window.setBackground(Color.WHITE);
		window.setBorder(new EmptyBorder(5, 5, 5, 5));
		f.setContentPane(window);
		window.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblLapplicationNecessiteUne = new JLabel("L'application necessite une connexion \u00E0 internet");
		window.add(lblLapplicationNecessiteUne, "6, 2");
		
		JButton btnReessayer = new JButton("Reessayer");
		btnReessayer.setBackground(SystemColor.controlHighlight);
		btnReessayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Constants.refresh = true;
				// Close Start Window
				setVisible(false); 
				dispose();
			}
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Joey\\Documents\\workspace-javaee\\EStalkMe\\src\\com\\estalkme\\gui\\img\\connexion.png"));
		window.add(label, "6, 4, center, default");
		window.add(btnReessayer, "6, 6, center, default");
	}

}
