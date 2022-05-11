package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import app.Cuenta;
import app.Idiomas;
import app.Principal;
import model.SesionModel;
import view.SesionView;

/**
 * 
 * @author daw
 *
 */
public class SesionController {
	private SesionModel model;
	private SesionView view;
	
	/**
	 * 
	 */
	public SesionController() {
		this.model = new SesionModel();
        this.view = new SesionView();
        this.view.setTitle(Principal.PROGRAMA_NOMBRE);
        this.view.addAccionListener(new Accion());
        this.view.addRegistrarseListener(new Registrarse());
    }
	
	/**
	 * mostrar()
	 */
	public void mostrar() {
		this.view.setVisible(true);
	}
	
	/**
	 * 
	 * @author daw
	 *
	 */
	class Accion implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int id = model.getCuenta(view.getUsuario(), view.getContraseña());
			if (id == -1) {
				JOptionPane.showMessageDialog(view.getContentPane(), Idiomas.getTraduccionFormato("SESION_USUARIO_CONTRA_INVALIDA"), Idiomas.getTraduccionFormato("SESION_INICION_SESION_TITULO"), JOptionPane.ERROR_MESSAGE);
				
				return;
			}

			Cuenta.setIniciadoSesion(true);
			Cuenta.setUsuario(view.getUsuario());
			Cuenta.setUsuarioID(id);
			
			LocalTime tiempoAhora = LocalTime.now();
			
			String tiempo = tiempoAhora.getHour() + ":" + tiempoAhora.getMinute();
			
			JOptionPane.showMessageDialog(view.getContentPane(), Idiomas.getTraduccionFormato("BIENVENIDO", Cuenta.getUsuario(), tiempo), Idiomas.getTraduccionFormato("SESION_INICION_SESION_TITULO"), JOptionPane.PLAIN_MESSAGE);

			view.dispose();
		}
	}
	
	/**
	 * 
	 * @author daw
	 *
	 */
	class Registrarse implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new RegistroController().mostrar();
		}
	}
}
