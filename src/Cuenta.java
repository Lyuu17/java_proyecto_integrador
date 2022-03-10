
public class Cuenta {
	private static String usuario;
	private static boolean iniciadoSesion;
	private static boolean admin;

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
