package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import app.Principal;
import app.Producto;
import view.CantidadView;

/**
 * 
 * @author daw
 *
 */
public class CantidadController {
	private CantidadView view;
	private ArrayList<Producto> productos;
	private Producto producto;
	
	/**
	 * 
	 * @param productos
	 * @param p
	 */
	public CantidadController(ArrayList<Producto> productos, Producto p) {
        this.view = new CantidadView(p);
        this.productos = productos;
        this.producto = p;
        this.view.setTitle(Principal.PROGRAMA_NOMBRE);
        this.view.addAñadirListener(new AñadirCantidad());
    }
	
	/**
	 * Mostrar la vista
	 */
	public void mostrar() {
		this.view.setVisible(true);
	}

	class AñadirCantidad implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			try {
				int cantidad = Integer.parseInt(view.getCantidad());
				if (cantidad == 0) return;

				producto.setCantidad(cantidad);
				productos.add(producto);
			}
			catch(Exception e1) {
				System.out.println(e1);
			}
				
			view.dispose();
		}
	}
}
