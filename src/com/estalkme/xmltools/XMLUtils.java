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

			// Creates root tag 'ecole'
			Element school = doc.createElement("school");
			school.setAttribute("name", "ESGi");
			doc.appendChild(school);

			// Creates new students for AL2
			Element promoAL2 = doc.createElement("promo");
			Node studentsAL2 = doc.createElement("students");
			for (int i = 0; i < STUDENTSAL2.length; i++) {
				// Creates child element 'promo'
				promoAL2.setAttribute("name", "Architecture des Logiciels 2");
				school.appendChild(promoAL2);

				// Creates 3 new nodes
				Node student = doc.createElement("student");
				Element lastname = doc.createElement("name");
				lastname.appendChild(doc.createTextNode(STUDENTSAL2[i].toString()));

				// Adds student personal informations to student element
				student.appendChild(lastname);
				studentsAL2.appendChild(student);

			}
			promoAL2.appendChild(studentsAL2);

			// Creates new students for IAM
			Element promo = doc.createElement("promo");
			Node studentsIAM = doc.createElement("students");
			for (int i = 0; i < STUDENTSIAM.length; i++) {
				// Creates child element 'promo'
				promo.setAttribute("name", "Ingénérie des Applications Mobiles");
				school.appendChild(promo);

				// Creates 3 new nodes
				Node student = doc.createElement("student");
				Element lastname = doc.createElement("name");
				lastname.appendChild(doc.createTextNode(STUDENTSIAM[i].toString()));

				// Adds student personal informations to student element
				student.appendChild(lastname);
				studentsIAM.appendChild(student);

			}
			promo.appendChild(studentsIAM);

			// Indent & Doctype declaration
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
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

	public static boolean existDocument(String firstName, String lastName) {
		File f = new File(SAVE_PATH + buildFileName(firstName, lastName));
		if(f.exists() && !f.isDirectory()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String buildFileName(String firstName, String lastName) {
		return "estalkme_result_[" + firstName + lastName + "].xml";
	}
}
