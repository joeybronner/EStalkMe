package com.estalkme.xmltools;

import javax.xml.xpath.XPathExpressionException;

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

	public static String getFirstName(Document doc) throws XPathExpressionException {
		Element rootElement = doc.getDocumentElement();
		return getString("firstname", rootElement);
	}
	
	public static String getLastName(Document doc) throws XPathExpressionException {
		Element rootElement = doc.getDocumentElement();
		return getString("lastname", rootElement);
	}
	
}
