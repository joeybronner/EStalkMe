package com.estalkme.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.collections15.Transformer;
import org.w3c.dom.Document;

import com.estalkme.gui.graph.SimpleGraphView;
import com.estalkme.tools.Constants;
import com.estalkme.xmltools.XMLRetrieveValues;
import com.estalkme.xmltools.XMLUtils;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class GUIResults extends JFrame {

	private static final long serialVersionUID = 4105303052246253432L;
	private static JPanel window;
	Document doc;
	String firstName, lastName;
	JLabel lblPrnom;
	JLabel lblNom;
	JLabel lblFileLink;

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
		lblFileLink.setText(Constants.fileName);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void init(JFrame f) throws Exception {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		JMenu fichier = new JMenu("Fichier");
		JMenuItem quit = new JMenuItem("Quitter...");
		quit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Quit
				//TODO: get the function to exit app.
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

		// Graph

		SimpleGraphView sgv = new SimpleGraphView();
		// The Layout<V, E> is parameterized by the vertex and edge types
		Layout<String, String> layout = new CircleLayout(sgv.g);
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		//VisualizationViewer<Integer,String> vv = new VisualizationViewer<Integer,String>(layout);
		VisualizationViewer<String, String> vv = new VisualizationViewer<String, String>(layout);
		//FlowLayout flowLayout = (FlowLayout) vv.getLayout();
		vv.setBackground(Color.WHITE);
		//vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size
		vv.setBorder(Constants.blackline);

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


		vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		
		// Create a graph mouse and add it to the visualization component
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm); 
        // Add the mouses mode key listener to work it needs to be added to the visualization component
        vv.addKeyListener(gm.getModeKeyListener());

		body.add(vv, gbc_tree);

	}
}
