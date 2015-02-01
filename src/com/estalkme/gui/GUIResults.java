package com.estalkme.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.collections15.Transformer;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.estalkme.gui.dev.windowTools;
import com.estalkme.gui.graph.SimpleGraphView;
import com.estalkme.tools.Constants;
import com.estalkme.xmltools.URLUtils;
import com.estalkme.xmltools.XMLRetrieveValues;
import com.estalkme.xmltools.XMLUtils;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;

public class GUIResults extends JFrame {

	private static final long serialVersionUID = 4105303052246253432L;
	private static JPanel window;
	Document doc;
	String firstName, lastName;
	JLabel lblPrnom;
	JLabel lblNom;
	JLabel lblFileLink;
	List<String> links;

	// Facebook crawler : https://code.google.com/p/facebook-crawler/source/browse/#svn%2Ftrunk%2FFacebook

	public GUIResults(String title, List<String> googleSearchResults) {
		try {
			links = googleSearchResults;
			loadLinks();
			init(this);
			loadFields();
		} catch (Exception e) {
			System.out.println("Erreur lors du chargement de la fenêtre... <com.estalkme.gui.GUIResult.java>\n" + e);
		}
	}

	private void loadLinks() throws Exception {
		doc = XMLUtils.getXMLFileAsDocument(XMLUtils.getXMLFile(Constants.firstName,Constants.lastName));
		Constants.goodLinks.clear();
		Constants.goodLinks = XMLRetrieveValues.getAllGoodLinks(doc);
		Constants.badLinks.clear();
		Constants.badLinks = XMLRetrieveValues.getAllBadLinks(doc);
	}

	private void loadFields() throws XPathExpressionException {
		lblPrnom.setText(XMLRetrieveValues.getFirstName(doc));
		lblNom.setText(XMLRetrieveValues.getLastName(doc));
		lblFileLink.setText(Constants.fileName);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void init(final JFrame f) throws Exception {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		JMenu fichier = new JMenu("Fichier");
		JMenuItem newSearch = new JMenuItem("Nouvelle recherche");
		newSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					windowTools.createStartWindow("EStalkMe - Start");
					setVisible(false); 
					dispose();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		fichier.add(newSearch);
		JMenuItem quit = new JMenuItem("Quitter...");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirm = JOptionPane.showOptionDialog(window,
						"Voulez-vous quitter l'application ?",
						"Quitter", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		fichier.add(quit);
		menuBar.add(fichier);
		setJMenuBar(menuBar);

		window = new JPanel();
		window.setBackground(Color.WHITE);
		window.setBorder(new EmptyBorder(5, 5, 5, 5));
		window.setLayout(new BorderLayout(0, 0));
		f.setContentPane(window);

		JPanel left = new JPanel();
		left.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		left.setBackground(Color.WHITE);
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
				FormFactory.DEFAULT_ROWSPEC,
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

		JButton imgFacebook = new JButton("");
		imgFacebook.setBackground(SystemColor.controlHighlight);
		imgFacebook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Open GUISocialLink
				GUISocialLink results = new GUISocialLink("facebook");
				results.setLocationRelativeTo(null); // center
				results.setVisible(true);
			}
		});
		imgFacebook.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/facebook.png"))));
		left.add(imgFacebook, "2, 12, center, default");

		JButton imgTwitter = new JButton("");
		imgTwitter.setBackground(SystemColor.controlHighlight);
		imgTwitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Open GUISocialLink
				GUISocialLink results = new GUISocialLink("twitter");
				results.setLocationRelativeTo(null); // center
				results.setVisible(true);
			}
		});
		imgTwitter.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/twitter.png"))));
		left.add(imgTwitter, "2, 14, center, default");

		JButton imgLinkedin = new JButton("");
		imgLinkedin.setBackground(SystemColor.controlHighlight);
		imgLinkedin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Open GUISocialLink
				GUISocialLink results = new GUISocialLink("linkedin");
				results.setLocationRelativeTo(null); // center
				results.setVisible(true);
			}
		});
		imgLinkedin.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/linkedin.png"))));
		left.add(imgLinkedin, "2, 16, center, default");

		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		header.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		window.add(header, BorderLayout.NORTH);
		GridBagLayout gbl_header = new GridBagLayout();
		gbl_header.columnWidths = new int[]{0, 0, 0, 0};
		gbl_header.rowHeights = new int[]{0, 0};
		gbl_header.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_header.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		header.setLayout(gbl_header);

		JLabel lblLogoApp = new JLabel("");
		lblLogoApp.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/estalkme_logo128.png"))));
		GridBagConstraints gbc_lblLogoApp = new GridBagConstraints();
		gbc_lblLogoApp.insets = new Insets(0, 0, 0, 5);
		gbc_lblLogoApp.gridx = 0;
		gbc_lblLogoApp.gridy = 0;
		header.add(lblLogoApp, gbc_lblLogoApp);

		JPanel footer = new JPanel();
		footer.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		footer.setBackground(Color.WHITE);
		window.add(footer, BorderLayout.SOUTH);
		GridBagLayout gbl_footer = new GridBagLayout();
		gbl_footer.columnWidths = new int[]{0, 0, 0};
		gbl_footer.rowHeights = new int[]{0, 0};
		gbl_footer.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_footer.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		footer.setLayout(gbl_footer);

		JLabel lblFichierSource = new JLabel("Fichier source: ");
		GridBagConstraints gbc_lblFichierSource = new GridBagConstraints();
		gbc_lblFichierSource.insets = new Insets(0, 0, 0, 5);
		gbc_lblFichierSource.gridx = 0;
		gbc_lblFichierSource.gridy = 0;
		footer.add(lblFichierSource, gbc_lblFichierSource);

		lblFileLink = new JLabel("link");
		lblFileLink.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblFileLink = new GridBagConstraints();
		gbc_lblFileLink.gridx = 1;
		gbc_lblFileLink.gridy = 0;
		footer.add(lblFileLink, gbc_lblFileLink);

		JPanel right = new JPanel();
		right.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		right.setBackground(Color.WHITE);
		window.add(right, BorderLayout.EAST);

		JPanel body = new JPanel();
		body.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		body.setBackground(Color.WHITE);
		window.add(body, BorderLayout.CENTER);
		GridBagLayout gbl_body = new GridBagLayout();
		gbl_body.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_body.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_body.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_body.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		body.setLayout(gbl_body);

		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.insets = new Insets(0, 0, 5, 5);
		gbc_tree.fill = GridBagConstraints.BOTH;
		gbc_tree.gridx = 0;
		gbc_tree.gridy = 0;

		// -------------------------------------------------------------------------
		// Graph
		// -------------------------------------------------------------------------
		SimpleGraphView sgv = new SimpleGraphView(links);
		Layout<String, String> layout = new CircleLayout(sgv.g);
		VisualizationViewer<String, String> vv = new VisualizationViewer<String, String>(layout);
		vv.setBackground(Color.WHITE);

		Transformer<Integer,Paint> vertexPaint = new Transformer<Integer,Paint>() {
			public Paint transform(Integer i) {
				return Color.GRAY;
			}
		};

		float dash[] = {10.0f};
		final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);

		Transformer<String, Stroke> edgeStrokeTransformer = new Transformer<String, Stroke>() {
			public Stroke transform(String s) {
				return edgeStroke;
			}
		};


		vv.getRenderContext().setVertexLabelTransformer(new Transformer<String, String>() {
			@Override
			public String transform(String arg0) {
				return arg0;
			}
		});
		vv.addGraphMouseListener(new GraphMouseListener() {

			@Override
			public void graphClicked(Object v, MouseEvent me) {
				if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 1) {
					try {
						URLUtils.openWebpage(new URL(v.toString()));
						GUINodeDetail results = new GUINodeDetail(v.toString());
						results.setLocationRelativeTo(null); // center
						results.setVisible(true);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				me.consume();
			}

			@Override
			public void graphPressed(Object arg0, MouseEvent arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void graphReleased(Object arg0, MouseEvent arg1) {
				// TODO Auto-generated method stub

			}
		});
		vv.getRenderContext().setEdgeLabelTransformer(new Transformer<String, String>() {
			@Override
			public String transform(String arg0) {
				return arg0;
			}
		});


		vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);


		// Paint
		vv.getRenderer().setVertexRenderer(new MyRenderer());

		// Create a graph mouse and add it to the visualization component
		//DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		//gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		//vv.setGraphMouse(gm); 
		// Add the mouses mode key listener to work it needs to be added to the visualization component
		//vv.addKeyListener(gm.getModeKeyListener());

		body.add(vv, gbc_tree);

	}

	static class MyRenderer implements Renderer.Vertex<String, String> {
		@Override
		public void paintVertex(RenderContext<String, String> rc, Layout<String, String> layout, String vertex) {
			GraphicsDecorator graphicsContext = rc.getGraphicsContext();
			Point2D center = layout.transform(vertex);
			Shape shape = null;
			Color color = null;

			if (Constants.goodLinks.contains(vertex.toString())) {
				shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 40, 40);
				color = Constants.GREEN;
			} else if (Constants.badLinks.contains(vertex.toString())) {
				shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 40, 40);
				color = Constants.RED;
			} else if(vertex.equals("Square")) {
				//shape = new Rectangle((int) center.getX() - 10, (int) center.getY() - 10, 20, 20);
				shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 40, 40);
				color = Constants.RED;
			} else if(vertex.equals("Rectangle")) {
				//shape = new Rectangle((int) center.getX() - 10, (int) center.getY() - 20, 20, 40);
				shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 40, 40);
				color = Constants.GREEN;
			} else if(vertex.equals("Circle")) {
				shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 40, 40);
				color = Constants.GRAY;
			} else {
				shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 40, 40);
				color = Constants.GRAY;
			}
			graphicsContext.setPaint(color);
			graphicsContext.fill(shape);
		}

	}
}
