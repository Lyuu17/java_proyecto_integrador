package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import app.Cuenta;
import app.GestorBD;
import view.RegistroView;

public class RegistroController {
	private RegistroView view;
	
	public RegistroController() {
        this.view = new view.RegistroView();
        
        this.view.addEnviarListener(new Enviar());
    }
	
	public RegistroView getView() {
		return view;
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

			Connection conn = GestorBD.getConexion();
			PreparedStatement stmt;
			try {
				String hashContraseña = utils.HashContraseña.hash(view.getContraseña());

				stmt = conn.prepareStatement("INSERT INTO usuarios (id, usuario, contraseña) VALUES (ID_AUTO_INCREMENT_SEQ.NEXTVAL, ?, ?)");

				GestorBD.consulta(stmt, view.getUsuario(), hashContraseña);

				stmt = conn.prepareStatement("INSERT INTO datos_usuarios (id, nombre, apellidos, email, tlf, direccion, ciudad, codigopostal) VALUES (ID_AUTO_INCREMENT_SEQ.CURRVAL, ?, ?, ?, ?, ?, ?, ?)");

				GestorBD.consulta(stmt, view.getNombre(), view.getApellidos(), view.getEmail(), view.getNumTlf(), view.getDireccion(), view.getCiudad(), view.getCodigoPostal());

				Cuenta.setIniciadoSesion(true);

				JOptionPane.showMessageDialog(view.getContentPane(), "OK", "Registro", JOptionPane.PLAIN_MESSAGE);

				//dispose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
