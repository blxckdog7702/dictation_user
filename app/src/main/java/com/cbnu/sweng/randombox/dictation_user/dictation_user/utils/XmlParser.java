package com.cbnu.sweng.randombox.dictation_user.dictation_user.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by son on 2017-09-14.
 */

public class XmlParser {

    // String 받을시 사용
    public static Document loadXmlString(String xmlData) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource xmlSrc = new InputSource(new StringReader(xmlData));
            Document doc = builder.parse(xmlSrc);
            doc.getDocumentElement().normalize();

            return doc;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //xml 파일 읽어들어서 램 상주.
    public static Document loadXmlFile(String FileUrl) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(FileUrl));
            doc.getDocumentElement().normalize();

            return doc;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * root Element 아래의 Tag text value 값을 반환한다.
     * @param root root Element object
     * @param tagName tag name
     * @return tag의 text value
     */
    public static String getTagValue(Document root, String tagName ) {
        NodeList nList = root.getElementsByTagName(tagName);
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node != null) {
                Node child = node.getFirstChild();
                if ((child != null) && (child.getNodeValue() != null)) {
                    return child.getNodeValue();
                }
            }
        }
        return "";
    }

    /**
     * Node의 attribute value를 반환한다.
     * @param node Node object
     * @param attrName attribute name
     * @return attribute의 value
     */
    public static String getAttribute(Node node, String attrName) {
        if ((node != null) && (node instanceof Element)) {
            return ((Element) node).getAttribute(attrName);
        }
        return "";
    }

    /**
     * Node의 attribute value를 반환한다.
     // @param node Node object
     * @param attrName attribute name
     * @return attribute의 value
     */
    public static String getAttribute(NodeList nl, String attrName) {
        String sAttribute = "";
        for(int k = 0; k<nl.getLength(); k++){
            Node n = nl.item(k);
            if(n != null){
                sAttribute = getAttribute(n,attrName);
            }
        }
        return sAttribute;
    }

}
