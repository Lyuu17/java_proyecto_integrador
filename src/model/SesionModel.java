package model;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.GestorBD;

public class SesionModel {
	public SesionModel() {
	}
	
	public int getCuentaPorUsuarioYContraseña(String usuario, String contraseña) {
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
