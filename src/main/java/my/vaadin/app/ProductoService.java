package my.vaadin.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProductoService {

	public ProductoService() {
		
	}
	
	public List<Producto> Clasificador(){
		
		List<Producto> productos = new ArrayList<Producto>();
		
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
		System.out.println("PRODUCTO: ");
		Producto producto_nuevo = new Producto();
		NodeList hijos_producto = producto.item(j).getChildNodes();//List of Nodes inside your producto
		for( int temp = 0; temp < hijos_producto.getLength();temp++) {
		/* This for will pass through all the parts of the producto, asking if you want to change it or not*/
			if(hijos_producto.item(temp).getNodeType()==Node.ELEMENT_NODE) {//check its node type
				if(hijos_producto.item(temp).getNodeName().equals("nombre")) {
				producto_nuevo.setName(hijos_producto.item(temp).getTextContent());
				}
				if(hijos_producto.item(temp).getNodeName().equals("precio")) {
				producto_nuevo.setPrecio(hijos_producto.item(temp).getTextContent());
				}
				if(hijos_producto.item(temp).getNodeName().equals("cantidad")) {
				producto_nuevo.setCantidad(hijos_producto.item(temp).getTextContent());
				}
				if(hijos_producto.item(temp).getNodeName().equals("descripcion")) {
				producto_nuevo.setDescripcion(hijos_producto.item(temp).getTextContent());
					}
				}
			}
				
				productos.add(producto_nuevo);
				
			}
		
		return productos;
	}
}