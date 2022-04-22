package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.LogoModel;
import model.TiendasModel;
import view.LogoView;
import view.TiendasView;

public class LogoController {
	private LogoModel model;
	private LogoView view;
	
	public LogoController(LogoModel model, LogoView view) {
		this.model = model;
		this.view = view;
		this.view.addContinuarListener(new Continuar());
	}
	
	private class Continuar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new TiendasController(new TiendasModel(), new TiendasView()).mostrar();
		}
	}

	public void mostrar() {
		this.view.setVisible(true);
	}
}
