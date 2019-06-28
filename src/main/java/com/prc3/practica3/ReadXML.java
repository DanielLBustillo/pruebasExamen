package com.prc3.practica3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class ReadXML {
	public String LeerXML() {
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
		
	    org.w3c.dom.Document xml=null;
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
	    try {
	        transformer = tf.newTransformer();
	         
	        
	        builder = factory.newDocumentBuilder();
			xml = builder.parse("xml_productos.xml");
	        
	        StringWriter writer = new StringWriter();
	 

	        transformer.transform(new DOMSource(xml), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();  
	        return xmlString;
	    }
	    catch (TransformerException e)
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
		return null;
	}
}
