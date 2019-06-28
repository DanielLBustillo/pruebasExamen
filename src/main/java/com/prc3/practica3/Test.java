package com.prc3.practica3;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test {

	@org.junit.Test
	public void test_Create() {
		//Fase para ver longitud de xml
		File inputFile = new File("xml_productos.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		int longitud_inicial;
		int longitud_final;
		try {
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("producto");
		int long_init = nList.getLength();
		longitud_inicial = long_init;
		} catch (ParserConfigurationException | SAXException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		longitud_inicial = 0;
		}
		//Funcion crear producto
		CreateJava create = new CreateJava();
		create.AnadirProducto("HP", "700", "1", "Breve descripcion de HP","123456789123");
		//Fase para ver longitud de xml ya con un nuevo producto
		try {
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("producto");
		int long_fin = nList.getLength();
		longitud_final = long_fin;
		//System.out.println(longitud_final);
		} catch (ParserConfigurationException | SAXException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		longitud_final = 0;
		}
		//Comprobacion longitud
		assertEquals(longitud_final, longitud_inicial+1);
	}
	
	@org.junit.Test
	public void test_Update() {
		String name_new = "0";
		String name_prev = "0";
		//Comprobar precio actual
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

		NodeList producto = xml.getElementsByTagName("producto");//Second node of the document
		for(int j = 0;j<producto.getLength();j++){
		NodeList hijos_producto = producto.item(j).getChildNodes();//List of Nodes inside your producto

		/* This for will pass through all the parts of the producto, asking if you want to change it or not*/
		for( int temp = 0; temp < hijos_producto.getLength();temp++) {
		if(hijos_producto.item(temp).getNodeType()==Node.ELEMENT_NODE) {//check its node type
		System.out.println(hijos_producto.item(temp).getTextContent());
		System.out.println(hijos_producto.item(temp).getTextContent().equals("Asus R510VX"));
		if(hijos_producto.item(temp).getTextContent().equals("Asus R510VX")) {
		name_prev = hijos_producto.item(3).getTextContent();
		System.out.println("--------------------------->prev: "+name_prev);
		}
		}
		}
		}

		//Funcion actualizar
		UpdateJava actu = new UpdateJava();
		actu.UpdateXml("Asus R510VX", "9859", "2", "Descripcion nueva");

		//Comprobar precio nuevo
		org.w3c.dom.Document xml1=null;

		DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder1;
		try {

		builder1 = factory1.newDocumentBuilder();
		builder1 = factory1.newDocumentBuilder();
		xml1 = builder1.parse("xml_productos.xml");
		} catch (ParserConfigurationException | SAXException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		NodeList producto_act = xml1.getElementsByTagName("producto");//Second node of the document
		for(int j = 0;j<producto_act.getLength();j++){
		NodeList hijos_producto = producto_act.item(j).getChildNodes();//List of Nodes inside your producto
		/* This for will pass through all the parts of the producto, asking if you want to change it or not*/
		for( int temp = 0; temp < hijos_producto.getLength();temp++) {
		if(hijos_producto.item(temp).getNodeType()==Node.ELEMENT_NODE) {//check its node type
		System.out.println(hijos_producto.item(temp).getTextContent());
		System.out.println(hijos_producto.item(temp).getTextContent().equals("Asus R510VX"));
		if(hijos_producto.item(temp).getTextContent().equals("Asus R510VX")) {
		name_new = hijos_producto.item(3).getTextContent();
		System.out.println("--------------------------->new: "+name_new);
		}
		}
		}
		}


		//Assert
		System.out.println(name_new);
		System.out.println(name_prev);
		assertNotEquals(name_new, name_prev);
		actu.UpdateXml("Asus R510VX", "989", "2", "Asus, la descripcion");
		}
	
	@org.junit.Test
	public void test_Delete() {
		//Fase para ver longitud de xml
		File inputFile = new File("xml_productos.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		int longitud_inicial;
		int longitud_final;
		try {
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("producto");
		int long_init = nList.getLength();
		longitud_inicial = long_init;
		} catch (ParserConfigurationException | SAXException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		longitud_inicial = 0;
		}
		//Funcion crear producto
		Delete_Update delete = new Delete_Update();
		delete.Borrado_Update("HP");
		//Fase para ver longitud de xml ya con un nuevo producto
		try {
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("producto");
		int long_fin = nList.getLength();
		longitud_final = long_fin;
		//System.out.println(longitud_final);
		} catch (ParserConfigurationException | SAXException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		longitud_final = 0;
		}
		//Comprobacion longitud
		System.out.println("------------------------------------------>final: "+longitud_final);
		System.out.println("------------------------------------------>inicial: "+longitud_inicial);
		assertEquals(longitud_final+1, longitud_inicial);

		}

	
	

}
