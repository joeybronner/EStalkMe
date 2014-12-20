package com.estalkme.xmltools;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLUtils {

	protected static final String SAVE_PATH = "src/com/estalkme/xmlresults/"; 
	private static final String[] STUDENTSAL2 = {"Joey", "Mathieu", "Thomas"};
	private static final String[] STUDENTSIAM = {"Matthieu", "Antonin", "Clément"};
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static Document createNewXMLDocument(String firstName, String lastName) {
		Document doc = null;
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
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

			// Indent & Doctype declaration
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			//transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "School - YYYY-MM-DD HH.MM.SS.dtd");

			// Prepares to write
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(doc);

			// Output to file
			String name = SAVE_PATH + buildFileName(firstName, lastName);
			result = new StreamResult(new File(name));
			transformer.transform(source, result);

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
		} catch (TransformerException tfe) {
			//Utils.trace("Error: " + tfe.getMessage());
		}
		return doc;
	}
	
	public static void updateXMLDocument(String firstName, String lastName) {
		
	}

	public static boolean existDocument(String firstName, String lastName) {
		File f = new File(SAVE_PATH + buildFileName(firstName, lastName));
		if(f.exists() && !f.isDirectory()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static File getXMLFile(String firstName, String lastName) {
		return new File(SAVE_PATH + buildFileName(firstName, lastName));
	}
	
	public static String buildFileName(String firstName, String lastName) {
		return "estalkme_result_[" + firstName + lastName + "].xml";
	}
}
