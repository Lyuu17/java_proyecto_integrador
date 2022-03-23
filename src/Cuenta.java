
public class Cuenta {
	private static int idTienda;
	private static String usuario;
	private static boolean iniciadoSesion;
	private static boolean admin;
	
	public static int getTiendaID() {
		return idTienda;
	}
	
	public static void setTiendaID(int tiendaID) {
		Cuenta.idTienda = tiendaID;
	}

	public static String getUsuario() {
		return usuario;
	}
	
	public static void setUsuario(String usuario) {
		Cuenta.usuario = usuario;
	}
	
	public static boolean isIniciadoSesion() {
		return iniciadoSesion;
	}
	
	public static void setIniciadoSesion(boolean iniciadoSesion) {
		Cuenta.iniciadoSesion = iniciadoSesion;
	}
	
	public static boolean isAdmin() {
		return admin;
	}
	
	public static void setAdmin(boolean admin) {
		Cuenta.admin = admin;
	}
}
