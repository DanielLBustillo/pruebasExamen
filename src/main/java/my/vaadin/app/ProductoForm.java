package my.vaadin.app;

import java.io.File;

import com.prc3.practica3.Delete_Update;
import com.prc3.practica3.Ean_Generate;
import com.prc3.practica3.UpdateJava;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class ProductoForm extends FormLayout {

	private TextField name = new TextField("Nombre");
	private TextField precio = new TextField("Precio");
	private TextField cantidad = new TextField("Cantidad");
	private TextField descripcion = new TextField("Descripción");
	private Button save = new Button("Guardar");
	private Button delete = new Button("Eliminar");
	private Button eangen =new Button ("Generar codigo de barras");
	private Button continuar = new Button("Continue");
	private Image image;


	private ProductoService service = new ProductoService();
	private Producto producto;
	private MyUI myUI;
	private Binder<Producto> binder = new Binder<>(Producto.class);
	
	public ProductoForm(MyUI myUI) {
		this.myUI = myUI;
		
		setSizeUndefined();
		
		HorizontalLayout buttons = new HorizontalLayout(save,delete,eangen,continuar);
		addComponents(name,precio,cantidad,descripcion,buttons);
		
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);
		delete.setStyleName(ValoTheme.BUTTON_DANGER);
		eangen.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		continuar.setStyleName(ValoTheme.NOTIFICATION_SYSTEM);
		
		
		binder.bindInstanceFields(this);
		continuar.setVisible(false);
		
		save.addClickListener(e -> save());
		delete.addClickListener(e -> delete());
		eangen.addClickListener(e -> eangen());
		



	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
		binder.setBean(producto);
		
		setVisible(true);
		name.selectAll();
	}
	
	
	private void delete() {
		Delete_Update borrado = new Delete_Update();
		borrado.Borrado_Update(name.getValue());
		myUI.refreshProductos();
		setVisible(false);
	}
	
	private void save() {
		UpdateJava actualizar = new UpdateJava();
		actualizar.UpdateXml(name.getValue(), precio.getValue(), cantidad.getValue(), descripcion.getValue());
		myUI.refreshProductos();
		setVisible(false);
	}
	private void eangen() {
		Ean_Generate generador = new Ean_Generate();
		generador.ean_generate(name.getValue());
		// Image as a file resource
		FileResource resource = new FileResource(new File("ean\\"+name.getValue()+".png"));

		// Show the image in the application
		image = new Image("", resource);
		addComponent(image);
		image.setVisible(true);
		myUI.refreshProductos();
		continuar.setVisible(true);
		continuar.addClickListener(e -> ContinuaApp());

		
		
	}
	
	private void ContinuaApp() {
		setVisible(false);
		continuar.setVisible(false);
		image.setVisible(false);
		removeComponent(image);
	}

}
