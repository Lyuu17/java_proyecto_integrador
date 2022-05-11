package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import app.Principal;
import app.Producto;
import view.ProductoView;

/**
 * 
 * @author daw
 *
 */
public class ProductoController {
	private ProductoView view;
	
	private ArrayList<Producto> productosCarrito;
	private Producto producto;
	
	/**
	 * 
	 * @param productosCarrito
	 * @param p
	 */
	public ProductoController(ArrayList<Producto> productosCarrito, Producto p) {
        this.view = new ProductoView();
        this.view.setTitle(Principal.PROGRAMA_NOMBRE);
        this.view.setNombre(p.getNombre());
        this.view.setDescripcion(p.getDescripcion());
        this.view.setMasDatos(p.toString());
        this.view.setIcono(p.getIcono());
        this.view.addAñadirListener(new Añadir());
        
        this.productosCarrito = productosCarrito;
        this.producto = p;
    }
	
	/**
	 * Mostrar la vista
	 */
	public void mostrar() {
		this.view.setVisible(true);
	}

	class Añadir implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new CantidadController(productosCarrito, producto).mostrar();
		}
	}
}
