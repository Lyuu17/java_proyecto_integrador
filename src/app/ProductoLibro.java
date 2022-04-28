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
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param precio
	 * @param rebaja
	 * @param fechaAñadido
	 * @param categoria
	 * @param cantidad
	 * @param cantidadMaxima
	 * @param autor
	 * @param numeroPaginas
	 * @param editorial
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
