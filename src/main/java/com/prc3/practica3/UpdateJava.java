package com.prc3.practica3;

import java.io.File;
import java.io.IOException;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class UpdateJava {
	public UpdateJava() {
		
	}
	/****************************************************************************
	 * INPUT DATA: NAME, PRECIO, CANTIDAD, DESCRIPCION
	 * OUTPUT DATA: XML
	 * 
	 * La función recibe como parámetros de entrada un XML a actualizar, un nombre que actúa como filtro
	 * y unos parametros a actualizar.
	 * 
	 *****************************************************************************/
	public void UpdateXml(String name, String precio_act, String cantidad_act, String descripcion_act) {
		
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
						if(hijos_producto.item(temp).getTextContent().equals(name)) {
							hijos_producto.item(3).setTextContent(precio_act);
							hijos_producto.item(5).setTextContent(cantidad_act);
							hijos_producto.item(7).setTextContent(descripcion_act);
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
	

