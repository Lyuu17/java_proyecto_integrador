package app;

/**
 * 
 * @author daw
 * 
 */
public class Cuenta {
	private static int idTienda;
	private static String usuario;
	private static int usuarioID;
	private static boolean iniciadoSesion;
	
	/**
	 * ID de la tienda guardado en el momento que se
	 * selecciona la tienda en el menú
	 * @return el ID de la tienda
	 */
	public static int getTiendaID() {
		return idTienda;
	}
	
	/**
	 * Guardar ID de la tienda seleccionada
	 * @param tiendaID
	 */
	public static void setTiendaID(int tiendaID) {
		Cuenta.idTienda = tiendaID;
	}

	/**
	 * 
	 * @return el usuario 
	 */
	public static String getUsuario() {
		return usuario;
	}
	
	/**
	 * 
	 * @param usuario el usuario
	 */
	public static void setUsuario(String usuario) {
		Cuenta.usuario = usuario;
	}
	
	/**
	 * 
	 * @return el id del usuario en la base de datos
	 */
	public static int getUsuarioID() {
		return usuarioID;
	}

	/**
	 * 
	 * @param usuarioID el id del usuario en la base de datos
	 */
	public static void setUsuarioID(int usuarioID) {
		Cuenta.usuarioID = usuarioID;
	}
	
	/**
	 * 
	 * @return si se ha iniciado sesión
	 */
	public static boolean isIniciadoSesion() {
		return iniciadoSesion;
	}
	
	/**
	 * 
	 * @param iniciadoSesion si ha o no iniciado sesión
	 */
	public static void setIniciadoSesion(boolean iniciadoSesion) {
		Cuenta.iniciadoSesion = iniciadoSesion;
	}
}
