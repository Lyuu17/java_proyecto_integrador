import javax.swing.ImageIcon;

public class Producto {
	private int id;
	private String nombre;
	private String descripcion;
	private double precio;
	private double rebaja;
	private String fechaA�adido;
	private int categoria;
	
	private ImageIcon icono;

	public Producto(int id, String nombre, String descripcion, double precio, double rebaja, String fechaA�adido, int categoria) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.rebaja = rebaja;
		this.fechaA�adido = fechaA�adido;
		this.categoria = categoria;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return the rebaja
	 */
	public double getRebaja() {
		return rebaja;
	}

	/**
	 * @param rebaja the rebaja to set
	 */
	public void setRebaja(double rebaja) {
		this.rebaja = rebaja;
	}

	/**
	 * @return the fechaA�adido
	 */
	public String getFechaA�adido() {
		return fechaA�adido;
	}

	/**
	 * @param fechaA�adido the fechaA�adido to set
	 */
	public void setFechaA�adido(String fechaA�adido) {
		this.fechaA�adido = fechaA�adido;
	}

	/**
	 * @return the categoria
	 */
	public int getCategoria() {
		return categoria;
	}

	/**
	 * @return the icono
	 */
	public ImageIcon getIcono() {
		return icono;
	}

	/**
	 * @param icono the icono to set
	 */
	public void setIcono(ImageIcon icono) {
		this.icono = icono;
	}
}
