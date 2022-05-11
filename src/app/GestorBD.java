package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author daw
 *
 */
public class GestorBD {
	private static Connection conn;

	/**
	 * Conectar a la base de datos de Oracle con los datos pasados por parámetro
	 * @param host El host de la BBDD
	 * @param puerto Puerto de la BBDD
	 * @param usuario Usuario de la cuenta de la BBDD
	 * @param contraseña Contraseña de la cuenta de la BBDD
	 * @return true=correcto, false=error
	 */
	public static boolean conectar(String host, int puerto, String usuario, String contraseña) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + puerto, usuario, contraseña);
			
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Desconectar de la base de datos
	 * @return false=no existe conexión, true=cerrada
	 */
	public static boolean desconectar() {
		if (conn == null) return false;
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * Realizar una consulta a la base de datos con solo String como parámetros
	 * @param q Query
	 * @param args Argumentos
	 * @return ResultSet
	 */
	public static ResultSet consulta(String q, String... args) {
		PreparedStatement stmt;
		ResultSet res = null;
		try {
			stmt = conn.prepareStatement(q);
			
			int i = 1;
			for(String paramStr : args) {
				stmt.setString(i, paramStr);
				i++;
			}

			return res = stmt.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Realizar una consulta con actualización a la BBDD con solo String como parámetro
	 * @param stmt Statement preparado
	 * @param args Argumentos
	 * @return el valor retornado de executeUpdate
	 */
	public static int consulta(PreparedStatement stmt, String... args) {
		int res = 0;
		try {
			int i = 1;
			for(String paramStr : args) {
				stmt.setString(i, paramStr);
				i++;
			}

			return res = stmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return res;
	}

	/**
	 * Obtener la conexión actual a la BBDD
	 * @return la conexión
	 */
	public static Connection getConexion() {
		return conn;
	}
}
