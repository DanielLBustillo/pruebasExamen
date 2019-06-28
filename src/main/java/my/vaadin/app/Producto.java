package my.vaadin.app;

import java.io.Serializable;

@SuppressWarnings("serial")

public class Producto implements Serializable, Cloneable{

	private String name;
	
	private String precio;
	
	private String cantidad;
	
	private String descripcion;
	
	/*************************
	 * Get y Set name
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/*************************
	 * Get y Set precio
	 */
	public String getPrecio() {
		return precio;
	}
	
	public void setPrecio(String precio) {
		this.precio= precio;
	}
	
	/*************************
	 * Get y Set cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
	/*************************
	 * Get y Set descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
