package com.estalkme.gui;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.estalkme.obj.Link;
import com.estalkme.tools.Constants;
import com.estalkme.xmltools.URLUtils;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class GUINodeDetail extends JFrame {

	private static JPanel window;
	JLabel lblTitreLink, lblLienLink;

	public GUINodeDetail(final Link link) {
		try {
			init(this, link);	
			getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
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

			JButton btnOpenLink = new JButton("Ouvrir le lien dans un navigateur");
			btnOpenLink.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						URLUtils.openWebpage(new URL(link.getLink()));
					} catch (MalformedURLException e) {
						// TODO catch
					}
				}
			});

			JLabel lblTitre = new JLabel("Titre");
			lblTitre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			getContentPane().add(lblTitre, "2, 2");

			lblTitreLink = new JLabel("titre");
			lblTitreLink.setFont(new Font("Tahoma", Font.PLAIN, 10));
			getContentPane().add(lblTitreLink, "4, 2");

			JLabel lblLien = new JLabel("Lien");
			lblLien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			getContentPane().add(lblLien, "2, 4");

			lblLienLink = new JLabel("lien");
			lblLienLink.setFont(new Font("Tahoma", Font.PLAIN, 10));
			getContentPane().add(lblLienLink, "4, 4");

			getContentPane().add(btnOpenLink, "4, 8, left, default");

			// Load all infos about this node
			if (link.getTitle() == link.getLink()) {
				lblTitreLink.setText("Pas de titre disponible...");

			} else {
				lblTitreLink.setText(link.getTitle());
			}
			lblLienLink.setText(link.getLink());
		} catch (Exception e) {
			System.out.println("Erreur... <com.estalkme.gui.GUINodeDetail.java>\n" + e);
		}

	}


	private void init(JFrame f, Link link) throws IOException {
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 350, 160);

		// JFrame Icon
		f.setIconImage(ImageIO.read(new File(Constants.ICON)));

		window = new JPanel();
		window.setBackground(Color.WHITE);
		window.setBorder(new EmptyBorder(5, 5, 5, 5));

	}

}
