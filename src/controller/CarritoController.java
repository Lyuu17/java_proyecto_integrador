package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import app.Idiomas;
import app.Principal;
import app.Producto;
import view.CarritoView;

public class CarritoController {
	private CarritoView view;
	
	private ArrayList<Producto> productosCarrito;
	
	private String[] tablaNombreColumnas = {
			"" /*imagen*/,
			Idiomas.getTraduccionFormato("NOMBRE"),
			Idiomas.getTraduccionFormato("PRECIO"),
			Idiomas.getTraduccionFormato("CANTIDAD"),
			"", /*añadir*/
			"" /*eliminar*/
	};
	
	public CarritoController(ArrayList<Producto> productosCarrito) {
        this.view = new view.CarritoView(productosCarrito);
        this.view.setTitle(Principal.PROGRAMA_NOMBRE);
        this.view.cargarCarritoProductos(tablaNombreColumnas, new BotonAñadir(), new BotonEliminar());
        
        this.productosCarrito = productosCarrito;
    }
	
	public CarritoView getView() {
		return view;
	}
	
	public void mostrar() {
		this.view.setVisible(true);
	}
	
	@SuppressWarnings("serial")
	class BotonAñadir extends AbstractAction {
		public void actionPerformed(ActionEvent e)
		{
			int id = Integer.valueOf(e.getActionCommand());

			Producto p = productosCarrito.get(id);
			if (p == null) return;
			
			p.setCantidad(p.getCantidad() + 1);

			view.actualizarFilaTabla(id, view.crearFilaObjeto(p));
		}
	}
	
	@SuppressWarnings("serial")
	class BotonEliminar extends AbstractAction {
		public void actionPerformed(ActionEvent e)
		{
			int id = Integer.valueOf(e.getActionCommand());

			Producto p = productosCarrito.get(id);
			if (p == null) return;
			if (p.getCantidad() == 0) return;

			p.setCantidad(p.getCantidad() - 1);

			view.actualizarFilaTabla(id, view.crearFilaObjeto(p));
			
			if (p.getCantidad() == 0) {
				productosCarrito.remove(id);
				
				view.eliminarFilaTabla(id);
			}
		}
	}
}
