package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.Cuenta;
import app.GestorBD;

/**
 * 
 * @author daw
 *
 */
public class RegistroModel {
	public RegistroModel() {
	}
	
	/**
	 * Registrar a un usuario con los datos pasados por par�metro
	 * @param usuario
	 * @param contrase�a
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param numTlf
	 * @param direccion
	 * @param ciudad
	 * @param codigoPostal
	 * @return true=ok, false=error
	 */
	public boolean registrarUsuario(String usuario, String contrase�a, String nombre, String apellidos, String email, String numTlf, String direccion, String ciudad, String codigoPostal) {
		Connection conn = GestorBD.getConexion();
		PreparedStatement stmt;
		try {
			String hashContrase�a = utils.HashContrase�a.hash(contrase�a);

			stmt = conn.prepareStatement("INSERT INTO usuarios (id, usuario, contrase�a) VALUES (ID_AUTO_INCREMENT_SEQ.NEXTVAL, ?, ?)");

			GestorBD.consulta(stmt, usuario, hashContrase�a);

			stmt = conn.prepareStatement("INSERT INTO datos_usuarios (id, nombre, apellidos, email, tlf, direccion, ciudad, codigopostal) VALUES (ID_AUTO_INCREMENT_SEQ.CURRVAL, ?, ?, ?, ?, ?, ?, ?)");

			GestorBD.consulta(stmt, nombre, apellidos, email, numTlf, direccion, ciudad, codigoPostal);

			Cuenta.setIniciadoSesion(true);
			
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
	}
}
