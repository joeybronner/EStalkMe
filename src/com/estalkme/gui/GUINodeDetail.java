package com.estalkme.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.estalkme.obj.Link;
import com.estalkme.tools.Constants;
import com.estalkme.xmltools.URLUtils;
import com.estalkme.xmltools.XMLUtils;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class GUINodeDetail extends JFrame {

	private static JPanel window;
	JLabel lblTitreLink, lblLienLink;
	private JButton btnSefier;
	private JButton btnNepassefier;

	public GUINodeDetail(final Link link) {
		try {
			init(this, link);	
			getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("160dlu"),},
					new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,}));

			lblTitreLink = new JLabel("titre");
			lblTitreLink.setFont(new Font("Tahoma", Font.PLAIN, 10));
			getContentPane().add(lblTitreLink, "2, 2, center, default");

			lblLienLink = new JLabel("lien");
			lblLienLink.setFont(new Font("Tahoma", Font.PLAIN, 10));
			getContentPane().add(lblLienLink, "2, 4, center, default");

			// Load all infos about this node
			if (link.getTitle() == link.getLink()) {
				lblTitreLink.setText("Pas de titre disponible...");

			} else {
				lblTitreLink.setText(link.getTitle());
			}
			lblLienLink.setText(link.getLink());

			JButton btnOpenLink = new JButton("Ouvrir le lien dans un navigateur");
			btnOpenLink.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						URLUtils.openWebpage(new URL(link.getLink()));
					} catch (MalformedURLException e) {
						System.out.println("Erreur, impossible d'ouvrir le lien");
					}
				}
			});

			btnSefier = new JButton("");
			btnSefier.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/good.PNG"))));
			btnSefier.setBackground(SystemColor.controlHighlight);
			btnSefier.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (link != null && !link.equals("")) {
						XMLUtils.removeLink(XMLUtils.getXMLFile(Constants.firstName, Constants.lastName), link.getLink());
						XMLUtils.addLink(XMLUtils.getXMLFile(Constants.firstName, Constants.lastName), "good", link.getLink());
						// Close Start Window
						setVisible(false); 
						dispose();
					} else {
						System.out.println("You must select a link if the list.");
					}
				}
			});
			getContentPane().add(btnSefier, "2, 6, center, default");

			btnNepassefier = new JButton("");
			btnNepassefier.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/bad.PNG"))));
			btnNepassefier.setBackground(SystemColor.controlHighlight);
			btnNepassefier.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (link != null && !link.equals("")) {
						XMLUtils.removeLink(XMLUtils.getXMLFile(Constants.firstName, Constants.lastName), link.getLink());
						XMLUtils.addLink(XMLUtils.getXMLFile(Constants.firstName, Constants.lastName), "bad", link.getLink());
						// Close Start Window
						setVisible(false); 
						dispose();
					} else {
						System.out.println("You must select a link if the list.");
					}
				}
			});
			getContentPane().add(btnNepassefier, "2, 8, center, default");

			getContentPane().add(btnOpenLink, "2, 10, center, default");
		} catch (Exception e) {
			System.out.println("Erreur... <com.estalkme.gui.GUINodeDetail.java>\n" + e);
		}

	}


	private void init(JFrame f, Link link) throws IOException {
		f.setBounds(100, 100, 350, 220);

		// JFrame Icon
		f.setIconImage(ImageIO.read(new File(Constants.ICON)));

		window = new JPanel();
		window.setBackground(Color.WHITE);
		window.setBorder(new EmptyBorder(5, 5, 5, 5));

	}

}
