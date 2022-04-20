package app;

public class Tienda {
	private String direccion;
	private int numero;
	private int codigo_postal;
	private String poblacion;
	private String ciudad;

	public Tienda(String direccion, int numero, int codigo_postal, String poblacion, String ciudad) {
		super();
		this.direccion = direccion;
		this.numero = numero;
		this.codigo_postal = codigo_postal;
		this.poblacion = poblacion;
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getNumero() {
		return numero;
	}

	public int getCodigo_postal() {
		return codigo_postal;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public String getCiudad() {
		return ciudad;
	}
}
