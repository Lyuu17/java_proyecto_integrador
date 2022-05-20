package app;

/**
 * 
 * @author daw
 *
 */
public class ProductoComida extends Producto {
	private String fabricante;
	private double peso;

	/**
	 * 
	 * @param id ID del producto
	 * @param nombre nombre del producto
	 * @param descripcion descripción del producto
	 * @param precio precio del producto
	 * @param rebaja rebaja del producto, si hay
	 * @param fechaAñadido fecha añadido del producto
	 * @param categoria categoria del producto
	 * @param cantidad cantidad del producto
	 * @param cantidadMaxima cantidad maxima del producto en la tienda
	 * @param fabricante fabricante del producto
	 * @param peso peso del producto
	 */
	public ProductoComida(int id, String nombre, String descripcion, double precio, double rebaja, String fechaAñadido, int categoria, int cantidad, int cantidadMaxima, 
			String fabricante, double peso) {
		super(id, nombre, descripcion, precio, rebaja, fechaAñadido, cantidad, cantidadMaxima, categoria);
		this.fabricante = fabricante;
		this.peso = peso;
	}

	/**
	 * @return the fabricante
	 */
	public String getFabricante() {
		return fabricante;
	}

	/**
	 * @param fabricante the fabricante to set
	 */
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "<html>Fabricante: " + fabricante + "<br>Peso: " + peso + "</html>";
	}
}
