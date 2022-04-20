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
	private static String host = "localhost";
	private static int puerto = 1521;
	private static String usuario = "proyecto";
	private static String contraseña = "proyecto";
	
	private static Connection conn;

	/**
	 * 
	 * @return true=conexion correcta, false=incorrecta
	 */
	public static boolean conectar() {
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
	 * 
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
	 * 
	 * @return la conexión
	 */
	public static Connection getConexion() {
		return conn;
	}
	
	/**
	 * 
	 * @param q =query
	 * @param args =argumentos
	 * @return ResultSet
	 */
	public static ResultSet consulta(String q, String... args) {
		Connection conn = GestorBD.getConexion();
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
	 * 
	 * @param stmt statement preparado
	 * @param paramArray
	 * @return update
	 */
	public static int consulta(PreparedStatement stmt, String... paramArray) {
		int res = 0;
		try {
			int i = 1;
			for(String paramStr : paramArray) {
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
}
