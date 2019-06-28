package com.prc3.practica3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;



public class Ean_Generate {
	
		
	
	/****************************************************************************
	 * INPUT DATA: NAME, PRECIO, CANTIDAD, DESCRIPCION
	 * OUTPUT DATA: XML
	 * 
	 * La función recibe como parámetros de entrada un XML a actualizar, un nombre que actúa como filtro
	 * y unos parametros a actualizar.
	 * @throws IOException 
	 * @throws WriterException 
	 * 
	 *****************************************************************************/
	public void ean_generate(String name)  {
		
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
		for(int n = 0; n < hijos_producto.getLength();n++) {
			if(hijos_producto.item(n).getNodeType()==Node.ELEMENT_NODE) {
				if(hijos_producto.item(n).getTextContent().equals(name)) {
					for( int temp = 0; temp < hijos_producto.getLength();temp++) {
						if(hijos_producto.item(temp).getNodeType()==Node.ELEMENT_NODE) {//check its node type
							if(hijos_producto.item(temp).getNodeName().equals("codigoean")) {
								String eanproducto= hijos_producto.item(temp).getTextContent();
								EAN13Writer ean13Writer = new EAN13Writer();
						        BitMatrix matrix1 = null;
								try {
									matrix1 = ean13Writer.encode(eanproducto, BarcodeFormat.EAN_13, 300, 200);
								} catch (WriterException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	
						        Path path1 = FileSystems.getDefault().getPath("./ean/"+name+".png");
						        try {
									MatrixToImageWriter.writeToPath(matrix1, "png", path1);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	
								}
							}
						}	
					}
				}
			}	
		}
	} 
} 

	