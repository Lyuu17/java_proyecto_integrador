package model;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.GestorBD;

/**
 * 
 * @author daw
 *
 */
public class SesionModel {
	public SesionModel() {
	}
	
	/**
	 * Obtener el id de la cuenta por usuario y contraseña pasados por parámetro
	 * @param usuario
	 * @param contraseña
	 * @return el id único de la cuenta
	 */
	public int getCuenta(String usuario, String contraseña) {
		ResultSet rs = GestorBD.consulta("SELECT ID FROM usuarios WHERE usuario = ? AND contraseña = ?", usuario, 
				utils.HashContraseña.hash(contraseña));

		try {
			if (!rs.next()) {
				return -1;
			}
			else {
				return rs.getInt("ID");
			}
		} catch (HeadlessException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			return -1;
		}
	}
}
