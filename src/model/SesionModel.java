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
	 * Obtener el id de la cuenta por usuario y contrase�a pasados por par�metro
	 * @param usuario
	 * @param contrase�a
	 * @return el id �nico de la cuenta
	 */
	public int getCuenta(String usuario, String contrase�a) {
		ResultSet rs = GestorBD.consulta("SELECT ID FROM usuarios WHERE usuario = ? AND contrase�a = ?", usuario, 
				utils.HashContrase�a.hash(contrase�a));

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
