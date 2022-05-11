package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import app.Cuenta;
import app.Principal;
import model.RegistroModel;
import view.RegistroView;

/**
 * 
 * @author daw
 *
 */
public class RegistroController {
	private RegistroModel model;
	private RegistroView view;
	
	/**
	 * 
	 */
	public RegistroController() {
		this.model = new RegistroModel();
        this.view = new RegistroView();
        this.view.setTitle(Principal.PROGRAMA_NOMBRE);
        this.view.addEnviarListener(new Enviar());
    }
	
	/**
	 * Mostrar la vista
	 */
	public void mostrar() {
		this.view.setVisible(true);
	}

	class Enviar implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if(view.getNombre().isEmpty() ||
					view.getApellidos().isEmpty() ||
					view.getEmail().isEmpty() ||
					view.getNumTlf().isEmpty() ||
					view.getDireccion().isEmpty() ||
					view.getCiudad().isEmpty() ||
					view.getCodigoPostal().isEmpty()) {
				JOptionPane.showMessageDialog(view.getContentPane(), "Faltan campos por rellenar", "Registro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (!model.registrarUsuario(view.getUsuario(), view.getContraseña(), 
					view.getNombre(), view.getApellidos(), view.getEmail(), view.getNumTlf(), view.getDireccion(), view.getCiudad(), view.getCodigoPostal())) {
				return;
			}
			
			Cuenta.setIniciadoSesion(true);

			JOptionPane.showMessageDialog(view.getContentPane(), "OK", "Registro", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
