package core;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class AllParsersXml extends DefaultHandler {

    public static void DOM_XML_PARSER_NO_XPATH() throws Throwable, Exception, ParserConfigurationException
    {
        String url = "http://learn2test.net/sdc.xml";

        String node = "kadu-response";

        String element_01 = "orig-kw";
        String element_02 = "engine";
        String element_03 = "kadu-version";
        String element_04 = "response-time";
        String element_05 = "deals";
        String attribute_01 = "count";

        // Get the DOM Builder Factory
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();

        // Get the DOM Builder
        DocumentBuilder b = f.newDocumentBuilder();

        // Load and Parse the XML document contains the complete XML as a Tree.
        Document doc = b.parse(url);

        // Optional
        doc.getDocumentElement().normalize();

        // Parsing elements
        NodeList nList = doc.getElementsByTagName(node);

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("Key Word: "
                        + eElement.getElementsByTagName(element_01).item(0)
                        .getChildNodes().item(0).getNodeValue());
                System.out.println("Engine: "
                        + eElement.getElementsByTagName(element_02).item(0)
                        .getChildNodes().item(0).getNodeValue());
                System.out.println("Version: "
                        + eElement.getElementsByTagName(element_03).item(0)
                        .getChildNodes().item(0).getNodeValue().trim());
                System.out.println("Response time: "
                        + eElement.getElementsByTagName(element_04).item(0)
                        .getChildNodes().item(0).getNodeValue().trim());
            }
        }

        // Parsing attribute
        NodeList nList2 = doc.getElementsByTagName(element_05);

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList2.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("Number of deals: "
                        + eElement.getAttribute(attribute_01).trim());

            }
        }
    }



    public static void DOM_XML_PARSER_XPATH() throws Throwable, Exception, ParserConfigurationException
    {
        String url = "http://learn2test.net/sdc.xml";

        String xpath_element_01 = "//server/orig-kw";
        String xpath_element_02 = "//server/engine";
        String xpath_element_03 = "//server/kadu-version";

        String xpath_element_04 = "//response-time";
        String xpath_element_05 = "//category/name";
        String xpath_element_06 ="//deal[1]/name";

        String xpath_attribute_01 = "//deals/@count";
        String xpath_attribute_02 ="//deals/deal[1]/@docId";

        // Get the DOM Builder Factory
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();

        // Get the DOM Builder
        DocumentBuilder b = f.newDocumentBuilder();

        // Load and Parse the XML document contains the complete XML as a Tree.
        Document d = b.parse(url);

        // Optional
        d.getDocumentElement().normalize();

        // xPath
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        String element_01 = xpath.compile(xpath_element_01).evaluate(d);
        String element_02 = xpath.compile(xpath_element_02).evaluate(d);
        String element_03 = xpath.compile(xpath_element_03).evaluate(d);

        String element_04 = xpath.compile(xpath_element_04).evaluate(d);
        String element_05 = xpath.compile(xpath_element_05).evaluate(d);
        String element_06 = xpath.compile(xpath_element_06).evaluate(d);

        String attribute_01 = xpath.compile(xpath_attribute_01).evaluate(d);
        String attribute_02 = xpath.compile(xpath_attribute_02).evaluate(d);

        System.out.println("Key Word: " + element_01);
        System.out.println("Engine: " + element_02);
        System.out.println("Version: " + element_03);

        System.out.println("Category name: " + element_05);
        System.out.println("Deal name: " + element_06);
        System.out.println("Deal docID: " + attribute_02);

        System.out.println("Response time: " + element_04);
        System.out.println("Number of deals: " + attribute_01);
    }


    public static void SAXParser () throws IOException, SAXException, ParserConfigurationException
    {
        String url = "http://learn2test.net/sdc.xml";

        final String element_01 = "orig-kw";
        final String element_02 = "engine";
        final String element_03 = "kadu-version";
        final String element_04 = "response-time";
        final String element_05 = "deals";

        final String element_name_01 = "Key Word: ";
        final String element_name_02 = "Engine: ";
        final String element_name_03 = "Version: ";
        final String element_name_04 = "Response time: ";
        final String element_name_05 = "Number of deals: ";

        // Create a "parser factory" for creating SAX parsers
        SAXParserFactory f = SAXParserFactory.newInstance();

        // Now use the parser factory to create a SAXParser object
        SAXParser p = f.newSAXParser();

        // Create an instance of this class; it defines all the handler methods
        DefaultHandler h = new DefaultHandler() {

            boolean handler_01 = false;
            boolean handler_02 = false;
            boolean handler_03 = false;
            boolean handler_04 = false;
            boolean handler_05 = false;
            String attribute_01;

            // a - uri; b - localName; c - qName; d - attributes
            public void startElement(String a, String b, String c, Attributes d)
                    throws SAXException {

                if (c.equalsIgnoreCase(element_01)) {
                    handler_01 = true;
                }
                if (c.equalsIgnoreCase(element_02)) {
                    handler_02 = true;
                }
                if (c.equalsIgnoreCase(element_03)) {
                    handler_03 = true;
                }
                if (c.equalsIgnoreCase(element_04)) {
                    handler_04 = true;
                }

                if (c.equalsIgnoreCase(element_05)) {
                    handler_05 = true;
                    attribute_01 = d.getValue("count");
                }
            }

            public void endElement(String a, String b, String c)
                    throws SAXException {
            }

            public void characters(char ch[], int start, int length)
                    throws SAXException {

                if (handler_01) {
                    System.out.println(element_name_01
                            + new String(ch, start, length));
                    handler_01 = false;
                }
                if (handler_02) {
                    System.out.println(element_name_02
                            + new String(ch, start, length));
                    handler_02 = false;
                }
                if (handler_03) {
                    System.out.println(element_name_03
                            + new String(ch, start, length));
                    handler_03 = false;
                }
                if (handler_04) {
                    System.out.println(element_name_04
                            + new String(ch, start, length));
                    handler_04 = false;
                }

                if (handler_05) {
                    System.out.println(element_name_05 + attribute_01);
                    handler_05 = false;
                }
            }
        };
        p.parse(url, h);


    }

    public static void StAXParser() throws XMLStreamException, IOException
    {
        URL url = new URL("http://learn2test.net/sdc.xml");

        final String element_01 = "orig-kw";
        final String element_02 = "engine";
        final String element_03 = "kadu-version";

        final String element_04 = "response-time";
        final String element_05 = "deals";


        final String element_name_01 = "Key Word: ";
        final String element_name_02 = "Engine: ";
        final String element_name_03 = "Version: ";

        final String element_name_04 = "Response time: ";
        final String element_name_05 = "Number of deals: ";

        InputStream in = url.openStream();

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(in);
        int eventType = reader.getEventType();

        while (reader.hasNext()) {

            eventType = reader.next();

            if (eventType == XMLStreamReader.START_ELEMENT) {
                if (reader.getLocalName() == element_01) {
                    System.out.println(element_name_01 + reader.getElementText());
                }
                if (reader.getLocalName() == element_02) {
                    System.out.println(element_name_02 + reader.getElementText());
                }
                if (reader.getLocalName() == element_03) {
                    System.out.println(element_name_03 + reader.getElementText());
                }
                if (reader.getLocalName() == element_04) {
                    System.out.println(element_name_04 + reader.getElementText());
                }
                if (reader.getLocalName() == element_05) {
                    System.out.println(element_name_05 + reader.getAttributeValue(0));
                }


            }
        }

    }

    //Run Point
    public static void main(String[] args) throws

            ParserConfigurationException,
            Exception,
            Throwable,
            IOException,
            SAXException,
            XMLStreamException

    {
        //DOM (without XPath), DOM (XPath), SAX, StAX
        System.out.println("DOM (without XPath) ==>");
        AllParsersXml.DOM_XML_PARSER_NO_XPATH();

        System.out.println();
        System.out.println("DOM (XPath) ==>");
        AllParsersXml.DOM_XML_PARSER_XPATH();

        System.out.println();
        System.out.println("SAX ==>");
        AllParsersXml.SAXParser();

        System.out.println();
        System.out.println("StAX ==>");
        AllParsersXml.StAXParser();
    }

}