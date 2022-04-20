package model;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.GestorBD;

public class SesionModel {
	public SesionModel() {
	}
	
	public boolean getCuentaPorUsuarioYContrase�a(String usuario, String contrase�a) {
		ResultSet rs = GestorBD.consulta("SELECT * FROM usuarios WHERE usuario = ? AND contrase�a = ?", usuario, 
				utils.HashContrase�a.hash(contrase�a));

		try {
			if (!rs.next()) {
				return false;
			}
			else {
				return true;
			}
		} catch (HeadlessException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			return false;
		}
	}
}
