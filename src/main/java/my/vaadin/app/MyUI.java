package my.vaadin.app;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	private ProductoService service = new ProductoService();
	private Grid<Producto> grid = new Grid<>(Producto.class);
	private ProductoForm form = new ProductoForm(this);
	private AnadirProducto form2 = new AnadirProducto(this);
	@Override
    protected void init(VaadinRequest vaadinRequest) {
	  final VerticalLayout layout = new VerticalLayout();
	  
	  Button anadirProducto = new Button("Añadir");
	  anadirProducto.addClickListener(e -> {
		  grid.asSingleSelect().clear();
		  form2.newProducto();
	  });
	  
	  HorizontalLayout toolbar = new HorizontalLayout(anadirProducto);
	  
	  grid.setColumns("name", "precio", "cantidad","descripcion");
	  layout.addStyleName("outlines");
	  
	  HorizontalLayout main = new HorizontalLayout(grid,form,form2);
	  main.setSizeFull();
	  main.setExpandRatio(grid, 1);
	  grid.setSizeFull();
	  
	  layout.addComponents(toolbar,main);

	  refreshProductos();
	  
	  setContent(layout); 
	  
	  form.setVisible(false);
	  form2.setVisible(false);
	  
	  grid.asSingleSelect().addValueChangeListener(event -> {
		  if(event.getValue() == null) {


		  }
		  else {
			  form.setProducto(event.getValue());
			  }
		  
	  });
    }

	public void refreshProductos() {
		List<Producto> productos = service.Clasificador();
		grid.setItems(productos);
	}
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}



