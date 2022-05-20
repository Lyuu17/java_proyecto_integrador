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
	 * @param direccion direcci�n de la tienda
	 * @param numero n� de la tienda
	 * @param codigo_postal c�digo postal de la tienda
	 * @param poblacion poblaci�n de la tienda
	 * @param ciudad ciudad de la tienda
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
