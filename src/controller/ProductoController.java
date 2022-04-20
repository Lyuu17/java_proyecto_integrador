package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import app.Producto;
import view.ProductoView;

public class ProductoController {
	private ProductoView view;
	
	private ArrayList<Producto> productosCarrito;
	private Producto producto;
	
	public ProductoController(ArrayList<Producto> productosCarrito, Producto p) {
        this.view = new view.ProductoView();
        this.view.setNombre(p.getNombre());
        this.view.setDescripcion(p.getDescripcion());
        this.view.setMasDatos(p.toString());
        this.view.setIcono(p.getIcono());
        this.view.addAñadirListener(new Añadir());
        
        this.productosCarrito = productosCarrito;
        this.producto = p;
    }
	
	public void mostrar() {
		this.view.setVisible(true);
	}
	
	public ProductoView getView() {
		return view;
	}
	
	class Añadir implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new CantidadController(productosCarrito, producto).mostrar();
		}
	}
}
