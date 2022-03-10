
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorBD {
	private static String host = "localhost";
	private static int puerto = 1521;
	private static String usuario = "proyecto";
	private static String contraseña = "proyecto";
	
	private static Connection conn;

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
	
	public static Connection getConexion() {
		return conn;
	}
}
