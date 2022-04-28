package app;

/**
 * 
 * @author daw
 *
 */
public class Tienda {
	private String direccion;
	private int numero;
	private int codigo_postal;
	private String poblacion;
	private String ciudad;

	/**
	 * 
	 * @param direccion
	 * @param numero
	 * @param codigo_postal
	 * @param poblacion
	 * @param ciudad
	 */
	public Tienda(String direccion, int numero, int codigo_postal, String poblacion, String ciudad) {
		super();
		this.direccion = direccion;
		this.numero = numero;
		this.codigo_postal = codigo_postal;
		this.poblacion = poblacion;
		this.ciudad = ciudad;
	}

	/**
	 * 
	 * @return la direcci�n de la Tienda
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * 
	 * @return el n�mero de la Tienda
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * 
	 * @return el c�digo postal de la Tienda
	 */
	public int getCodigo_postal() {
		return codigo_postal;
	}

	/**
	 * 
	 * @return la poblaci�n de la Tienda
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * 
	 * @return la ciudad de la Tienda
	 */
	public String getCiudad() {
		return ciudad;
	}
}
