
public class ProductoRopa extends Producto {
	private String talla;
	private String color;
	private String marca;

	public ProductoRopa(int id, String nombre, String descripcion, double precio, double rebaja, String fechaA�adido, int categoria, 
			String talla, String color, String marca) {
		super(id, nombre, descripcion, precio, rebaja, fechaA�adido, categoria);
		this.talla = talla;
		this.color = color;
		this.marca = marca;
	}

	/**
	 * @return the talla
	 */
	public String getTalla() {
		return talla;
	}
	/**
	 * @param talla the talla to set
	 */
	public void setTalla(String talla) {
		this.talla = talla;
	}
	
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	@Override
	public String toString() {
		return "<html>Talla: " + talla + "<br>Color: " + color + "<br>Marca: " + marca + "</html>";
	}
}
