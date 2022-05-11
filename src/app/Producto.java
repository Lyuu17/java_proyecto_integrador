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
	private String fechaAñadido;
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
	 * @param fechaAñadido
	 * @param cantidad
	 * @param cantidadMaxima
	 * @param categoria
	 */
	public Producto(int id, String nombre, String descripcion, double precio, double rebaja, String fechaAñadido, int cantidad, int cantidadMaxima, int categoria) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.rebaja = rebaja;
		this.fechaAñadido = fechaAñadido;
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
	 * @return la fechaAñadido
	 */
	public String getFechaAñadido() {
		return fechaAñadido;
	}

	/**
	 * @param fechaAñadido la fechaAñadido a establecer
	 */
	public void setFechaAñadido(String fechaAñadido) {
		this.fechaAñadido = fechaAñadido;
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
