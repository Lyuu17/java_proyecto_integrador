package app;

/**
 * 
 * @author daw
 *
 */
public class ProductoLibro extends Producto{
	private String autor;
	private int numeroPaginas;
	private String editorial;
	
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
	 * @param autor autor del libro
	 * @param numeroPaginas nº de paginas del libro
	 * @param editorial editorial del libro
	 */
	public ProductoLibro(int id, String nombre, String descripcion, double precio, double rebaja, String fechaAñadido, int categoria, int cantidad, int cantidadMaxima, 
			String autor, int numeroPaginas, String editorial) {
		super(id, nombre, descripcion, precio, rebaja, fechaAñadido, cantidad, cantidadMaxima, categoria);
		this.autor = autor;
		this.numeroPaginas = numeroPaginas;
		this.editorial = editorial;
	}

	/**
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}
	/**
	 * @param autor the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}
	/**
	 * @return the numeroPaginas
	 */
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	/**
	 * @param numeroPaginas the numeroPaginas to set
	 */
	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	/**
	 * @return the editorial
	 */
	public String getEditorial() {
		return editorial;
	}
	/**
	 * @param editorial the editorial to set
	 */
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	@Override
	public String toString() {
		return "<html>Autor: " + autor + "<br>Número de páginas: " + numeroPaginas + "<br>Editorial: " + editorial+"</html>";
	}
}
