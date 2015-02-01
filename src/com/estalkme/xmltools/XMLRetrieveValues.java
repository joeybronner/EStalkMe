package com.estalkme.xmltools;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLRetrieveValues {

	protected static String getString(String tagName, Element element) {
		NodeList list = element.getElementsByTagName(tagName);
		if (list != null && list.getLength() > 0) {
			NodeList subList = list.item(0).getChildNodes();
			if (subList != null && subList.getLength() > 0) {
				return subList.item(0).getNodeValue();
			}
		}
		return "null";
	}

	public static NodeList getNodeList(Document doc, String expr) {
		try {
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xpath = xPathFactory.newXPath();
			XPathExpression pathExpr = xpath.compile(expr);
			return (NodeList) pathExpr.evaluate(doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getFirstName(Document doc) throws XPathExpressionException {
		Element rootElement = doc.getDocumentElement();
		return getString("firstname", rootElement);
	}

	public static String getLastName(Document doc) throws XPathExpressionException {
		Element rootElement = doc.getDocumentElement();
		return getString("lastname", rootElement);
	}

	public static List<String> getAllGoodLinks(Document doc) {
		return getListOfNodeContent(doc, "goods");
	}

	public static List<String> getAllBadLinks(Document doc) {
		return getListOfNodeContent(doc, "bads");
	}
	
	private static List<String> getListOfNodeContent(Document doc, String parentNode) {
		List<String> texts = new ArrayList<String>();
		NodeList c = doc.getElementsByTagName(parentNode);
		for (int i = 0; i < c.getLength(); i++) {
			if (c.item(i) instanceof Element) {
				NodeList children = c.item(i).getChildNodes();

				for (int j = 0; j < children.getLength(); j++) {
					if (children.item(j) instanceof Element) {
						texts.add(children.item(j).getTextContent());
					}
				}
			}
		}
		return texts;
	}
}
