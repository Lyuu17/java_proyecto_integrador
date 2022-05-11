package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import app.Cuenta;
import app.Idiomas;
import app.Principal;
import app.Producto;
import model.CarritoModel;
import view.CarritoView;

/**
 * 
 * @author daw
 *
 */
public class CarritoController {
	private CarritoModel model;
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
	
	/**
	 * 
	 * @param productosCarrito
	 */
	public CarritoController(ArrayList<Producto> productosCarrito) {
		this.model = new CarritoModel();
        this.view = new view.CarritoView(productosCarrito);
        this.view.setTitle(Principal.PROGRAMA_NOMBRE);
        this.view.addComprarListener(new Comprar());
        this.view.cargarCarritoProductos(tablaNombreColumnas, new BotonAñadir(), new BotonEliminar());
        
        this.productosCarrito = productosCarrito;
    }
	
	/**
	 * Mostrar la vista
	 */
	public void mostrar() {
		this.view.setVisible(true);
	}

	class Comprar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!Cuenta.isIniciadoSesion()) {
				JOptionPane.showMessageDialog(view.getContentPane(), Idiomas.getTraduccionFormato("NO_INICIADO_SESION"), Idiomas.getTraduccionFormato("CARRITO_COMPRA_TITULO"), JOptionPane.ERROR_MESSAGE);
				
				return;
			}
			
			boolean res = model.comprar(productosCarrito);
			if (!res) {
				JOptionPane.showMessageDialog(view.getContentPane(), Idiomas.getTraduccionFormato("CARRITO_COMPRA_FALLO"), Idiomas.getTraduccionFormato("CARRITO_COMPRA_TITULO"), JOptionPane.ERROR_MESSAGE);
				
				return;
			}
			
			productosCarrito.clear();
			view.dispose();
			
			JOptionPane.showMessageDialog(view.getContentPane(), Idiomas.getTraduccionFormato("CARRITO_COMPRA_OK"), Idiomas.getTraduccionFormato("CARRITO_COMPRA_TITULO"), JOptionPane.PLAIN_MESSAGE);
		}
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
