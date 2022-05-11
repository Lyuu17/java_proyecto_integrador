package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import app.Cuenta;
import app.GestorBD;
import app.Producto;

/**
 * 
 * @author daw
 *
 */
public class CarritoModel {
	public CarritoModel() {
	}
	
	/**
	 * Comprar (insertar en la BBDD al historial de compras) los productos de un ArrayList pasados por parámetro
	 * @param productos ArrayList de productos
	 * @return true=correcto, false=error
	 */
	public boolean comprar(ArrayList<Producto> productos) {
		Connection conn = GestorBD.getConexion();
		PreparedStatement stmt;
		try {	
			stmt = conn.prepareStatement("INSERT INTO historial_compras (id_usuario, id_producto, id_tienda, cantidad, precio_total) "
					+ "VALUES (?, ?, ?, ?, ?)");
			
			for(Producto p : productos) {
				stmt.setInt(1, Cuenta.getUsuarioID());
				stmt.setInt(2, p.getId());
				stmt.setInt(3, Cuenta.getTiendaID());
				stmt.setInt(4, p.getCantidad());
				stmt.setDouble(5, p.getCantidad() * p.getPrecio());
				
				stmt.executeUpdate();
			}
			
			return true;

			//dispose();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
	}
}
