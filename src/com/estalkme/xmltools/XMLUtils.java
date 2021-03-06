package com.estalkme.xmltools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.estalkme.tools.Constants;

public class XMLUtils {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static Document createNewXMLDocument(String firstName, String lastName) {
		Document doc = null;
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();

			// Create STRUCTURE
			Element person = doc.createElement("person");
			person.setAttribute("id", firstName+lastName);
			doc.appendChild(person);

			// ABOUT node
			Element about = doc.createElement("about");
			person.appendChild(about);
			// Childs
			Node gender = doc.createElement("gender");
			about.appendChild(gender);
			Node firstname = doc.createElement("firstname");
			firstname.appendChild(doc.createTextNode(firstName));
			about.appendChild(firstname);
			Node lastname = doc.createElement("lastname");
			lastname.appendChild(doc.createTextNode(lastName));
			about.appendChild(lastname);
			Node dateofbirth = doc.createElement("dateofbirth");
			about.appendChild(dateofbirth);

			// FRIENDS node
			Element friends = doc.createElement("friends");
			person.appendChild(friends);

			// SOCIAL node
			Element social = doc.createElement("social");
			person.appendChild(social);

			// WORKEXPERIENCE node
			Element workexperience = doc.createElement("workexperience");
			person.appendChild(workexperience);

			// LINKS node
			Element links = doc.createElement("links");
			person.appendChild(links);
			// Reputation
			Node reputation = doc.createElement("reputation");
			links.appendChild(reputation);
			// Goods
			Node goods = doc.createElement("goods");
			reputation.appendChild(goods);
			// Bads
			Node bads = doc.createElement("bads");
			reputation.appendChild(bads);
			// Approvals
			Node approvals = doc.createElement("approvals");
			links.appendChild(approvals);
			// Disapprovals
			Node disapprovals = doc.createElement("disapprovals");
			links.appendChild(disapprovals);

			// Number of Searches
			Element nbofsearches = doc.createElement("nbofsearches");
			nbofsearches.setTextContent("1");
			person.appendChild(nbofsearches);

			// Save file
			saveXMLDocumentAsFile(doc, new File(Constants.SAVE_PATH + buildFileName(firstName, lastName)));

			/*
			Utils.trace("File saved (" + name + ")");
			Utils.trace("Would you like to check if XML generated is valide ? (Y/N)");

			if (br.readLine().equals("Y")) {
				// XML Validation 
				if (validateWithDTDUsingDOM(name)) {
					Utils.trace("[V] XML file generated is valide.");
				} else {
					Utils.trace("[X] XML file generated isn't valide.");
				}
			}
			 */	

		} catch (ParserConfigurationException pce) {
			//Utils.trace("Error: " + pce.getMessage());
		}
		return doc;
	}

	public static void updateXMLDocument(String firstName, String lastName)
			throws XPathExpressionException, TransformerFactoryConfigurationError, TransformerException {
		// Get & Print the document
		Document doc = getXMLFileAsDocument(getXMLFile(firstName, lastName));
		// +1 for the number of searches
		int newNbOfSearches = Integer.valueOf(XMLManageValues.getNumberOfSearches(doc)) + 1;
		Constants.nbOfSearches = String.valueOf(newNbOfSearches);
		doc = XMLManageValues.setNumberOfSearches(doc, Constants.nbOfSearches);
		printDocument(doc, System.out);
		saveXMLDocumentAsFile(doc, getXMLFile(Constants.firstName, Constants.lastName));
	}

	public static void removeLink(File file, String link) {
		Document doc = getXMLFileAsDocument(file);
		NodeList c;
		// Remove from goods
		c = doc.getElementsByTagName("goods");
		for (int i = 0; i < c.getLength(); i++) {
			if (c.item(i) instanceof Element) {
				NodeList children = c.item(i).getChildNodes();
				for (int j = 0; j < children.getLength(); j++) {
					if (children.item(j) instanceof Element) {
						Element good = (Element) children.item(j);
						if (good.getTextContent().equals(link)) {
							good.getParentNode().removeChild(good);
						}
					}
				}
			}
		}
		// Remove from bads
		c = doc.getElementsByTagName("bads");
		for (int i = 0; i < c.getLength(); i++) {
			if (c.item(i) instanceof Element) {
				NodeList children = c.item(i).getChildNodes();
				for (int j = 0; j < children.getLength(); j++) {
					if (children.item(j) instanceof Element) {
						Element bad = (Element) children.item(j);
						if (bad.getTextContent().equals(link)) {
							bad.getParentNode().removeChild(bad);
						}
					}
				}
			}
		}
		//printDocument(doc, System.out);
		saveXMLDocumentAsFile(doc, file);
	}

	public static void addLink(File file, String type, String link) {
		Document doc = getXMLFileAsDocument(file);
		NodeList nodes = null;
		Element linkNode = null;

		// Add new bad or good link
		if (type.equals("good")) {
			// Get parent Node
			nodes = doc.getElementsByTagName("goods");
			// New link 
			linkNode = doc.createElement("good"); 
			Text linkValue = doc.createTextNode(link);
			linkNode.appendChild(linkValue); 
		} else if (type.equals("bad")) {
			// Get parent Node
			nodes = doc.getElementsByTagName("bads");
			// New link 
			linkNode = doc.createElement("bad"); 
			Text linkValue = doc.createTextNode(link);
			linkNode.appendChild(linkValue); 
		}

		Node n = nodes.item(0);
		n.appendChild(linkNode);

		// Print
		printDocument(doc, System.out);

		// Save
		saveXMLDocumentAsFile(doc, file);
	}

	public static String getSocialLink(File file, String socialmedia) throws XPathExpressionException {
		Document doc = getXMLFileAsDocument(file);

		// Add new bad or good link
		if (socialmedia.equals("facebook") || socialmedia.equals("twitter") || socialmedia.equals("linkedin")) {
			// Node exists ?
			if (doc.getElementsByTagName(socialmedia).getLength() > 0) {
				// Locate the node
				XPath xpath = XPathFactory.newInstance().newXPath();
				NodeList social = (NodeList)xpath.evaluate("//social/" + socialmedia, doc, XPathConstants.NODESET);
				// Retrieve link
				return social.item(0).getTextContent();
			}
		}
		return "";
	}

	public static void addSocialLink(File file, String socialmedia, String link) throws XPathExpressionException {
		Document doc = getXMLFileAsDocument(file);
		Element linkNode = null;

		// Add new bad or good link
		if (socialmedia.equals("facebook") || socialmedia.equals("twitter") || socialmedia.equals("linkedin")) {
			// Node exists ?
			if (doc.getElementsByTagName(socialmedia).getLength() > 0) {
				// Locate the node
				XPath xpath = XPathFactory.newInstance().newXPath();
				NodeList social = (NodeList)xpath.evaluate("//social/" + socialmedia, doc, XPathConstants.NODESET);
				// Make the change
				for (int i = 0; i < social.getLength(); i++) {
					social.item(i).setTextContent(link);
				}
			} else {
				// Get parent Node
				NodeList nodes = doc.getElementsByTagName("social");
				// New link 
				linkNode = doc.createElement(socialmedia); 
				Text linkValue = doc.createTextNode(link);
				linkNode.appendChild(linkValue);
				Node n = nodes.item(0);
				n.appendChild(linkNode);
			}
		}

		// Print
		printDocument(doc, System.out);

		// Save
		saveXMLDocumentAsFile(doc, file);
	}

	public static boolean existDocument(String firstName, String lastName) {
		File f = new File(Constants.SAVE_PATH + buildFileName(firstName, lastName));
		if(f.exists() && !f.isDirectory()) {
			return true;
		} else {
			return false;
		}
	}

	public static File getXMLFile(String firstName, String lastName) {
		return new File(Constants.SAVE_PATH + buildFileName(firstName, lastName));
	}
	
	public static File getLastProcessXMLFile() {
		return new File(Constants.SAVE_PATH + "estalkme_lastprocess.xml");
	}

	public static String buildFileName(String firstName, String lastName) {
		return "estalkme_result_[" + firstName + lastName + "].xml";
	}

	public static Document getXMLFileAsDocument(File file) {
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder = null;
		Document doc = null;
		try {
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

	public static void printDocument(Document doc, OutputStream out) {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(out, "UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while printing the document.");
		}
	}

	public static void saveXMLDocumentAsFile(Document doc, File file) {
		try {
			// Indent & Doctype declaration
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			// Prepares to write
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(doc);

			// Output to file
			result = new StreamResult(file);
			transformer.transform(source, result);
		} catch (Exception e) {
			System.out.println("Error on saving XML file.");
			e.printStackTrace();
		}
	}
	
	public static boolean validateWithDTDUsingDOM(String xml) throws ParserConfigurationException, IOException, SAXException {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			factory.setNamespaceAware(true);

			DocumentBuilder builder = factory.newDocumentBuilder();

			builder.setErrorHandler(
					new ErrorHandler() {
						@Override
						public void warning(SAXParseException e) throws SAXException {
							System.out.println("WARNING : " + e.getMessage()); // do nothing
						}

						@Override
						public void error(SAXParseException e) throws SAXException {
							System.out.println("ERROR : " + e.getMessage());
							throw e;
						}

						@Override
						public void fatalError(SAXParseException e) throws SAXException {
							System.out.println("FATAL : " + e.getMessage());
							throw e;
						}
					}
					);
			builder.parse(new InputSource(xml));
			return true;
	}
}
