package com.prc3.practica3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class CreateJava {
	// name_act.getValue(), precio_act.getValue(), cantidad_act.getValue(), descripcion_act.getValue()
	public void AnadirProducto(String nombre_add, String precio_add,String cantidad_add, String descripcion_add, String codigo_add) {
		//Primero se debe buscar si existe ya el producto y, en caso afirmativo, mandar 
		//un mensaje de que no es posible anadir el producto porque ya existe
		//En caso de que no exista el producto se procedera a la creacion del producto
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("xml_productos.xml");
			Element element = doc.getDocumentElement();
			Node node = doc.createElement("producto");
			element.appendChild(node);
			Node nodo_nombre = doc.createElement("nombre");
			node.appendChild(nodo_nombre);			
			nodo_nombre.setTextContent(nombre_add);
			
			Node nodo_precio = doc.createElement("precio");
			node.appendChild(nodo_precio);
			nodo_precio.setTextContent(precio_add);
			
			Node nodo_cantidad = doc.createElement("cantidad");
			node.appendChild(nodo_cantidad);
			nodo_cantidad.setTextContent(cantidad_add);
			
			Node nodo_descripcion = doc.createElement("descripcion");
			node.appendChild(nodo_descripcion);
			nodo_descripcion.setTextContent(descripcion_add);
			
			Node nodo_codigo = doc.createElement("codigoean");
			node.appendChild(nodo_codigo);
			nodo_codigo.setTextContent(codigo_add);
			
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("xml_productos.xml"));
			transformer.transform(source, result);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
}




