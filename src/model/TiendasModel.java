package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.GestorBD;
import app.Tienda;

public class TiendasModel {
	private ArrayList<Tienda> tiendas;
	
	public TiendasModel() {
		this.tiendas = new ArrayList<Tienda>();
	}
	
	public void cargarTiendas() {
		String query = "SELECT direccion, numero, codigo_postal, poblacion, ciudad FROM tiendas";

		ResultSet rs = GestorBD.consulta(query);
		try {
			while(rs.next()) {
				tiendas.add(new Tienda(rs.getString("direccion"), rs.getInt("numero"), rs.getInt("codigo_postal"), rs.getString("poblacion"), rs.getString("ciudad")));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public ArrayList<Tienda> getTiendas() {
		return tiendas;
	}
}