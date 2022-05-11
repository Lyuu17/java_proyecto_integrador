package app;
import javax.swing.ImageIcon;

/**
 * 
 * @author daw
 *
 */
public class Producto {
	private int id;
	private String nombre;
	private String descripcion;
	private double precio;
	private double rebaja;
	private String fechaA�adido;
	private int categoria;
	private int cantidad;
	private int cantidadMaxima;
	
	private ImageIcon icono;

	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param precio
	 * @param rebaja
	 * @param fechaA�adido
	 * @param cantidad
	 * @param cantidadMaxima
	 * @param categoria
	 */
	public Producto(int id, String nombre, String descripcion, double precio, double rebaja, String fechaA�adido, int cantidad, int cantidadMaxima, int categoria) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.rebaja = rebaja;
		this.fechaA�adido = fechaA�adido;
		this.cantidad = cantidad;
		this.setCantidadMaxima(cantidadMaxima);
		this.categoria = categoria;
	}

	/**
	 * @return el id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id el id a establecer
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre el nombre a establecer
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return la descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion la descripcion a establecer
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return el precio
	 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * @param precio el precio a establecer
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return la rebaja
	 */
	public double getRebaja() {
		return rebaja;
	}

	/**
	 * @param la the rebaja a establecer
	 */
	public void setRebaja(double rebaja) {
		this.rebaja = rebaja;
	}

	/**
	 * @return la fechaA�adido
	 */
	public String getFechaA�adido() {
		return fechaA�adido;
	}

	/**
	 * @param fechaA�adido la fechaA�adido a establecer
	 */
	public void setFechaA�adido(String fechaA�adido) {
		this.fechaA�adido = fechaA�adido;
	}

	/**
	 * @return la categoria
	 */
	public int getCategoria() {
		return categoria;
	}

	/**
	 * @return la cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad la cantidad para establecer
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return la cantidadMaxima
	 */
	public int getCantidadMaxima() {
		return cantidadMaxima;
	}

	/**
	 * @param cantidadMaxima la cantidadMaxima para establecer
	 */
	public void setCantidadMaxima(int cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	/**
	 * @return el icono
	 */
	public ImageIcon getIcono() {
		return icono;
	}

	/**
	 * @param icono el icono to set
	 */
	public void setIcono(ImageIcon icono) {
		this.icono = icono;
	}
}
