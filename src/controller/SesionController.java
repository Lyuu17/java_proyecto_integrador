package controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import app.Cuenta;
import app.GestorBD;
import app.Idiomas;
import app.Principal;
import view.SesionView;

public class SesionController {
	private SesionView view;
	
	public SesionController() {
        this.view = new SesionView();
        this.view.setTitle(Principal.PROGRAMA_NOMBRE);
        this.view.addAccionListener(new Accion());
        this.view.addRegistrarseListener(new Registrarse());
    }
	
	class Accion implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String hashContraseña = utils.HashContraseña.hash(view.getContraseña());
			ResultSet rs = GestorBD.consulta("SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?", view.getUsuario(), hashContraseña);

			try {
				if (!rs.next()) {
					JOptionPane.showMessageDialog(view.getContentPane(), Idiomas.getTraduccionFormato("SESION_USUARIO_CONTRA_INVALIDA"), Idiomas.getTraduccionFormato("SESION_INICION_SESION_TITULO"), JOptionPane.ERROR_MESSAGE);
				}
				else {
					Cuenta.setIniciadoSesion(true);
					Cuenta.setUsuario(view.getUsuario());
					
					LocalTime tiempoAhora = LocalTime.now();
					
					String tiempo = tiempoAhora.getHour()+":"+tiempoAhora.getMinute();
					
					JOptionPane.showMessageDialog(view.getContentPane(), Idiomas.getTraduccionFormato("BIENVENIDO", Cuenta.getUsuario(), tiempo), Idiomas.getTraduccionFormato("SESION_INICION_SESION_TITULO"), JOptionPane.PLAIN_MESSAGE);

					view.dispose();
				}
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

				JOptionPane.showMessageDialog(view.getContentPane(), Idiomas.getTraduccionFormato("SESION_ERROR"), Idiomas.getTraduccionFormato("SESION_INICION_SESION_TITULO"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class Registrarse implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			RegistroController registroController = new RegistroController();
			registroController.getView().setVisible(true);
		}
	}
}
