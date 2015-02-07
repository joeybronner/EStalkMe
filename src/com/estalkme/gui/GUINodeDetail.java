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
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("right:default"),},
					new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,}));

			lblTitreLink = new JLabel("titre");
			lblTitreLink.setFont(new Font("Tahoma", Font.PLAIN, 10));
			getContentPane().add(lblTitreLink, "2, 2, 11, 1");

			lblLienLink = new JLabel("lien");
			lblLienLink.setFont(new Font("Tahoma", Font.PLAIN, 10));
			getContentPane().add(lblLienLink, "2, 4, 11, 1");

			// Load all infos about this node
			if (link.getTitle() == link.getLink()) {
				lblTitreLink.setText("Pas de titre disponible...");

			} else {
				lblTitreLink.setText(link.getTitle());
			}
			lblLienLink.setText(link.getLink());

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

			getContentPane().add(btnOpenLink, "12, 6, left, default");
		} catch (Exception e) {
			System.out.println("Erreur... <com.estalkme.gui.GUINodeDetail.java>\n" + e);
		}

	}


	private void init(JFrame f, Link link) throws IOException {
		f.setBounds(100, 100, 350, 125);

		// JFrame Icon
		f.setIconImage(ImageIO.read(new File(Constants.ICON)));

		window = new JPanel();
		window.setBackground(Color.WHITE);
		window.setBorder(new EmptyBorder(5, 5, 5, 5));

	}

}
