package com.prc3.practica3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Delete_Update {
	
	public void Borrado_Update(String nombre_borrar) {
		
		org.w3c.dom.Document xml=null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder;
		try {
			
			builder = factory.newDocumentBuilder();
			builder = factory.newDocumentBuilder();
			xml = builder.parse("xml_productos.xml");					
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NodeList producto =  xml.getElementsByTagName("producto");//Second node of the document
		for(int j = 0;j<producto.getLength();j++){
		NodeList hijos_producto = producto.item(j).getChildNodes();//List of Nodes inside your producto
		
		/* This for will pass through all the parts of the producto, asking if you want to change it or not*/
		for( int temp = 0; temp < hijos_producto.getLength();temp++) {
			if(hijos_producto.item(temp).getNodeType()==Node.ELEMENT_NODE) {//check its node type
				if(hijos_producto.item(temp).getTextContent().equals(nombre_borrar)) {
					Node node = hijos_producto.item(3).getParentNode();
	        	    node.getParentNode().removeChild(node);
					
					}
			}
		}	 
	}
			
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	try {
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(xml);
		StreamResult result = new StreamResult(new File("xml_productos.xml"));
		transformer.transform(source, result);
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	
}
