package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.Cuenta;
import app.Idiomas;
import app.Principal;
import model.TiendaProductosModel;
import model.TiendasModel;
import view.TiendaProductosView;
import view.TiendasView;

public class TiendasController {
	private TiendasModel model;
	private TiendasView view;
	
	String[] tablaNombreColumnas = {
			Idiomas.getTraduccionFormato("DIRECCION"),
            "Nº",
            Idiomas.getTraduccionFormato("CODIGO_POSTAL"),
            Idiomas.getTraduccionFormato("POBLACION"),
            Idiomas.getTraduccionFormato("CIUDAD")};

	public TiendasController(TiendasModel model, TiendasView view) {
		this.model = model;
		this.view = view;

		this.model.cargarTiendas();
		this.view.setTitle(Principal.PROGRAMA_NOMBRE);
		this.view.cargarListaTiendas(tablaNombreColumnas, this.model.getTiendas());
		this.view.addSeleccionarTiendaListener(new SeleccionarTienda());
		this.view.addActualizarTiendaListener(new ActualizarTiendas());
	}
	
	public void mostrar() {
		this.view.setVisible(true);
	}
	
	class ActualizarTiendas implements DocumentListener {
		@Override
		public void insertUpdate(DocumentEvent e) {
			actualizarListaTiendas();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			actualizarListaTiendas();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {}
		
		private void actualizarListaTiendas() {
			int cp = 0;
			try {
				cp = Integer.parseInt(view.getCodigoPostalIntroducido());
			}
			catch(Exception e) {
				
			}
			
			view.cargarListaTiendas(tablaNombreColumnas, model.getTiendas(cp));
		}
	}

	class SeleccionarTienda implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.dispose();
			
			Cuenta.setTiendaID(view.getTiendaFilaSeleccionada());
			
			new TiendaProductosController(new TiendaProductosModel(), new TiendaProductosView()).mostrar();
		}
	}
}
