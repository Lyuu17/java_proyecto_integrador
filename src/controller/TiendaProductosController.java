package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import app.Idiomas;
import app.Principal;
import app.Producto;
import model.TiendaProductosModel;
import model.TiendasModel;
import utils.DefaultTreeNodeCategorias;
import view.TiendaProductosView;
import view.TiendasView;

/**
 * 
 * @author daw
 *
 */
public class TiendaProductosController {
	private TiendaProductosModel model;
	private TiendaProductosView view;
	
	private String[] tablaNombreColumnas = {
			"" /*imagen*/,
			Idiomas.getTraduccionFormato("NOMBRE"),
			Idiomas.getTraduccionFormato("PRECIO"),
			"" /*consultar*/
	};
	
	/**
	 * 
	 * @param model
	 * @param view
	 */
	public TiendaProductosController(TiendaProductosModel model, TiendaProductosView view) {
		this.model = model;
		this.view = view;
		this.view.setTitle(Principal.PROGRAMA_NOMBRE);
		this.view.addAtrasListener(new Atras());
		this.view.addCarritoListener(new Carrito());
		this.view.addCuentaListener(new Cuenta());
		this.view.addActualizarProductosListener(new ActualizarProductosBusqueda());
		this.view.addActualizarProductosCategoriasListener(new ActualizarProductosCategoria());
	}
	
	/**
	 * Mostrar la vista
	 */
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

	class ActualizarProductosBusqueda implements DocumentListener {
		@Override
		public void insertUpdate(DocumentEvent e) {
			actualizarListaProductos();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			actualizarListaProductos();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {}
		
		private void actualizarListaProductos() {
			String nombre = view.getBuscarProductosTexto();
			
			view.cargarListaProductos(tablaNombreColumnas, new Consultar(), model.getProductos(nombre));
		}
	}

	class ActualizarProductosCategoria extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() != 1) return;
			
			DefaultTreeNodeCategorias node = (DefaultTreeNodeCategorias) view.getTreeNodeLastSelectedPathComponent();
            if (node == null) return;
            
            ArrayList<Producto> productos = model.getProductos(node.getUUID());
            view.cargarListaProductos(tablaNombreColumnas, new Consultar(), productos);
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
