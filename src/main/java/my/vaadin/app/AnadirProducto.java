package my.vaadin.app;

import com.prc3.practica3.CreateJava;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class AnadirProducto extends FormLayout {
	
	private TextField name = new TextField("Nombre");
	private TextField precio = new TextField("Precio");
	private TextField cantidad = new TextField("Cantidad");
	private TextField descripcion = new TextField("Descripción");
	private TextField codigo = new TextField("Código");
	private Button anadir = new Button("Anadir");
	private Button cancelar = new Button("Cancelar");

	private ProductoService service = new ProductoService();
	private Producto producto;
	private MyUI myUI;
	
	public AnadirProducto(MyUI myUI) {
		this.myUI = myUI;
		
		setSizeUndefined();
		
		HorizontalLayout buttons = new HorizontalLayout(anadir,cancelar);
		addComponents(name,precio,cantidad,descripcion,codigo,buttons);
		
		anadir.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		anadir.addClickListener(e -> anadir_nuevo_producto());
		cancelar.addClickListener(e -> CancelarOperacion());
		anadir.setClickShortcut(KeyCode.ENTER);
	}
	
	public void  anadir_nuevo_producto() {
		CreateJava anadir = new CreateJava();
		anadir.AnadirProducto(name.getValue(), precio.getValue(), cantidad.getValue(), descripcion.getValue(),codigo.getValue());
		myUI.refreshProductos();
		setVisible(false);
	}
	
	public void CancelarOperacion() {
		setVisible(false);
	}
	public void newProducto() {
		
		setVisible(true);
	}
	
}
