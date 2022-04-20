package app;

public class ProductoComida extends Producto {
	private String fabricante;
	private double peso;

	public ProductoComida(int id, String nombre, String descripcion, double precio, double rebaja, String fechaA�adido, int categoria, int cantidad, int cantidadMaxima, 
			String fabricante, double peso) {
		super(id, nombre, descripcion, precio, rebaja, fechaA�adido, cantidad, cantidadMaxima, categoria);
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
