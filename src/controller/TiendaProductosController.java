package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;

import app.Idiomas;
import app.Principal;
import app.Producto;
import model.TiendaProductosModel;
import model.TiendasModel;
import view.TiendaProductosView;
import view.TiendasView;

public class TiendaProductosController {
	private TiendaProductosModel model;
	private TiendaProductosView view;
	
	private String[] tablaNombreColumnas = {
			"" /*imagen*/,
			Idiomas.getTraduccionFormato("NOMBRE"),
			Idiomas.getTraduccionFormato("PRECIO"),
			"" /*consultar*/
	};
	
	public TiendaProductosController(TiendaProductosModel model, TiendaProductosView view) {
		this.model = model;
		this.view = view;
		this.view.setTitle(Principal.PROGRAMA_NOMBRE);
		this.view.addAtrasListener(new Atras());
		this.view.addCarritoListener(new Carrito());
		this.view.addCuentaListener(new Cuenta());
	}
	
	public void mostrar() {
		this.view.setVisible(true);
		this.view.cargarListaProductos(tablaNombreColumnas, new Consultar(), this.model.getProductos());
	}
	
	class Atras implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.dispose();
			new TiendasController(new TiendasModel(), new TiendasView()).mostrar();
		}
	}
	
	@SuppressWarnings("serial")
	class Consultar extends AbstractAction {
		public void actionPerformed(ActionEvent e)
		{
			int id = Integer.valueOf(e.getActionCommand());

			Producto p = model.getProductos().get(id);
			new ProductoController(model.getProductosCarrito(), p).mostrar();
		}
	}
	
	class IdiomaSelector implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = view.getIdiomaComboboxItemSeleccionado();
			String idiomaSeleccionado = view.getIdiomaComboboxItem(index);
			if (idiomaSeleccionado.equals(Idiomas.getIdiomaActual())) return;

			Idiomas.setIdiomaActual(idiomaSeleccionado);
		}
	}
	
	class Carrito implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new CarritoController(model.getProductosCarrito()).mostrar();
		}
	}
	
	class Cuenta implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new SesionController().mostrar();
		}
	}
}
