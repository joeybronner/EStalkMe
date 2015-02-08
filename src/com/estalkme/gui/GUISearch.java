package com.estalkme.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import com.estalkme.google.api.GoogleResults;
import com.estalkme.obj.Link;
import com.estalkme.tools.Constants;
import com.estalkme.xmltools.URLUtils;
import com.estalkme.xmltools.XMLUtils;

public class GUISearch extends JFrame {

	private static final long serialVersionUID = 8086390580361973467L;
	private static JPanel window;
	@SuppressWarnings("rawtypes")
	private static JList list = new JList();
	private static List<String> googleSearchLinks;
	private static List<Link> googleSearchResults = new ArrayList<Link>();

	public GUISearch(String title, Dimension size, final String firstName, final String lastName) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				if (Constants.refresh == true) {
					fillList(firstName, lastName);
				}
			}
		});
		setBackground(Color.WHITE);
		try {
			init(this);
			initConstants(firstName, lastName);
			fillList(firstName, lastName);
		} catch (Exception e) {
			System.out.println("Erreur lors de la recherche... <com.estalkme.gui.GUISearch.java>\n" + e);
		}
	}

	public void initConstants(String firstName, String lastName) {
		Constants.fileName = XMLUtils.buildFileName(firstName, lastName);
		Constants.filePath = Constants.SAVE_PATH + Constants.fileName;
	}

	@SuppressWarnings("unchecked")
	public void fillList(String firstName, String lastName) {
		try {
			// Get results from Google
			googleSearchResults.clear();
			googleSearchLinks = GoogleResults.readAsList(firstName, lastName);
			for (int i=0 ; i<googleSearchLinks.size() ; i++) {
				String l = googleSearchLinks.get(i).toString();
				String title = URLUtils.getTitle(googleSearchLinks.get(i).toString());
				String minimalTitle = URLUtils.getFirstWords(title, 5);
				googleSearchResults.add(new Link(title, l, minimalTitle));
			}
			list.setBackground(SystemColor.controlHighlight);
			list.setListData(googleSearchLinks.toArray());
		} catch (Exception e) {
			// Open GUIResults
			GUINoInternetConnection connection = new GUINoInternetConnection(this, firstName, lastName);
			connection.setLocationRelativeTo(null); // center
			connection.setVisible(true);
		}
	}

	private void init(JFrame f) throws Exception {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 750, 450);
		window = new JPanel();
		window.setBackground(Color.WHITE);
		window.setBorder(new EmptyBorder(5, 5, 5, 5));
		window.setLayout(new BorderLayout(0, 0));
		f.setContentPane(window);

		// JFrame Icon
		f.setIconImage(ImageIO.read(getClass().getResource("img/icon.png")));

		JPanel panelBody = new JPanel();
		panelBody.setBackground(SystemColor.window);
		window.add(panelBody, BorderLayout.CENTER);
		panelBody.setLayout(new MigLayout("", "[250px,grow,fill]", "[250px,grow,fill]"));
		panelBody.add(list, "cell 0 0,grow");

		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(Color.WHITE);
		window.add(panelFooter, BorderLayout.SOUTH);

		JButton btnSuivant = new JButton("Suivant");
		btnSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				final JFrame loading = GUILoading.loadingFrame();
				Runnable runnable = new Runnable() {
					public void run() {
						// If some links are found
						if(googleSearchResults.size() > 0) {
							// Open GUIResults
							GUIResults results = new GUIResults("EStalkMe - Results", googleSearchResults);
							results.setLocationRelativeTo(null); // center
							results.setVisible(true);
							// Close Start Window
							setVisible(false); 
							dispose();
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
		btnSuivant.setBackground(SystemColor.controlHighlight);
		panelFooter.add(btnSuivant);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		window.add(panel, BorderLayout.EAST);
		panel.setLayout(new MigLayout("", "[158px]", "[14px][][][][][]"));

		JButton btnNePasSe = new JButton();
		btnNePasSe.setBackground(SystemColor.controlHighlight);
		btnNePasSe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String link = (String) list.getSelectedValue();
				if (link != null && !link.equals("")) {
					XMLUtils.removeLink(XMLUtils.getXMLFile(Constants.firstName, Constants.lastName), link);
					XMLUtils.addLink(XMLUtils.getXMLFile(Constants.firstName, Constants.lastName), "bad", link);
				} else {
					System.out.println("You must select a link if the list.");
				}
			}
		});

		JButton btnSeFier = new JButton();
		btnSeFier.setBackground(SystemColor.controlHighlight);
		btnSeFier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String link = (String) list.getSelectedValue();
				if (link != null && !link.equals("")) {
					XMLUtils.removeLink(XMLUtils.getXMLFile(Constants.firstName, Constants.lastName), link);
					XMLUtils.addLink(XMLUtils.getXMLFile(Constants.firstName, Constants.lastName), "good", link);
				} else {
					System.out.println("You must select a link if the list.");
				}
			}
		});

		JLabel imgGoogle = new JLabel("");
		imgGoogle.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/google_logo.PNG"))));
		panel.add(imgGoogle, "cell 0 1,alignx center");

		JLabel lblAjouterCeLien = new JLabel("Ajouter ce lien aux liens valides ?");
		panel.add(lblAjouterCeLien, "cell 0 3,alignx left,aligny top");
		btnSeFier.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/good.PNG"))));
		panel.add(btnSeFier, "cell 0 4,alignx center");
		btnNePasSe.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/bad.PNG"))));
		panel.add(btnNePasSe, "cell 0 5,alignx center");
	}
}
