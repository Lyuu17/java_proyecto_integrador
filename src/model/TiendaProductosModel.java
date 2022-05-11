package model;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.Cuenta;
import app.GestorBD;
import app.Producto;
import app.ProductoComida;
import app.ProductoLibro;
import app.ProductoRopa;
import app.ProductosCategorias;
import utils.ImageIconResize;
import view.TiendaProductosView;

/**
 * 
 * @author daw
 *
 */
public class TiendaProductosModel {
	private ArrayList<Producto> productos = null;
	private ArrayList<Producto> productosCarrito = null;
	
	public TiendaProductosModel() {
		this.productos = new ArrayList<Producto>();
		this.productosCarrito = new ArrayList<Producto>();
		
		cargarProductos();
	}
	
	/**
	 * Obtener los productos de la Tienda seleccionada
	 * @return los productos
	 */
	public ArrayList<Producto> getProductos() {
		return productos;
	}
	
	
	/**
	 * Obtener los productos de la Tienda seleccionada por nombre
	 * @param nombre buscar por nombre los productos
	 * @return
	 */
	public ArrayList<Producto> getProductos(String nombre) {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		for(Producto producto : getProductos()) {
			if (producto.getNombre().toLowerCase().indexOf(nombre.toLowerCase()) < 0 && !nombre.equals("")) continue;
			
			productos.add(producto);
		}
		
		return productos;
	}
	
	/**
	 * Obtener los productos de la Tienda seleccionada por categoría
	 * @param categoria buscar por categoria los productos
	 * @return
	 */
	public ArrayList<Producto> getProductos(int categoria) {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		for(Producto producto : getProductos()) {
			if (producto.getCategoria() != categoria && categoria != 0) continue;
			
			productos.add(producto);
		}
		
		return productos;
	}
	
	/**
	 * Obtener los productos en el carrito de compra
	 * @return los productos en el carrito
	 */
	public ArrayList<Producto> getProductosCarrito() {
		return productosCarrito;
	}
	
	/**
	 * Cargar los productos de la tienda seleccionada
	 */
	public void cargarProductos() {
		ResultSet rs = GestorBD.consulta("SELECT * FROM productos_tienda "
				+ "JOIN productos ON productos.id = productos_tienda.producto_id "
				+ "WHERE tienda_id = " + Cuenta.getTiendaID());
		try {
			while(rs.next()) {
				Producto prod = null;

				int id = rs.getInt("id");
				String n = rs.getString("nombre");
				String desc = rs.getString("descripcion");
				double precio = rs.getDouble("precio");
				double rebaja = rs.getDouble("rebaja");
				String fecha = rs.getString("fecha_añadido");
				int cat = rs.getInt("categoria");
				int cantidadMaxima = rs.getInt("cantidad");

				switch(cat) {
				case ProductosCategorias.CAT_ROPA:
					ResultSet rs_ropa = GestorBD.consulta("SELECT talla, color, marcas.marca FROM producto_ropa "
							+ "JOIN marcas ON marcas.id = producto_ropa.marca "
							+ "JOIN productos_tienda ON productos_tienda.producto_id = producto_ropa.id "
							+ "WHERE producto_ropa.id=" + id);
					if (!rs_ropa.next()) throw new SQLException();

					prod = new ProductoRopa(id, n, desc, precio, rebaja, fecha, cat, 0, cantidadMaxima,
							rs_ropa.getString("talla"), rs_ropa.getString("color"), rs_ropa.getString("marca"));
					break;
				case ProductosCategorias.CAT_COMIDA:
					ResultSet rs_comida = GestorBD.consulta("SELECT fabricantes.fabricante, peso FROM producto_comida "
							+ "JOIN fabricantes ON fabricantes.id = producto_comida.fabricante "
							+ "JOIN productos_tienda ON productos_tienda.producto_id = producto_comida.id "
							+ "WHERE producto_comida.id=" + id);
					if (!rs_comida.next()) throw new SQLException();

					prod = new ProductoComida(id, n, desc, precio, rebaja, fecha, cat, 0, cantidadMaxima,
							rs_comida.getString("fabricante"), rs_comida.getDouble("peso"));
					break;
				case ProductosCategorias.CAT_LIBROS:
					ResultSet rs_libro = GestorBD.consulta("SELECT editoriales.editorial, autores.autor, numeroPaginas FROM producto_libros "
							+ "JOIN autores ON autores.id = producto_libros.autor "
							+ "JOIN editoriales ON editoriales.id = producto_libros.editorial "
							+ "JOIN productos_tienda ON productos_tienda.producto_id = producto_libros.id "
							+ "WHERE producto_libros.id=" + id);
					if (!rs_libro.next()) throw new SQLException();

					prod = new ProductoLibro(id, n, desc, precio, rebaja, fecha, cat, 0, cantidadMaxima,
							rs_libro.getString("autor"), rs_libro.getInt("numeroPaginas"), rs_libro.getString("editorial"));
					break;
				default:
					break;
				}

				if (prod == null) continue;

				URL urlIcono = TiendaProductosView.class.getResource("/productos/" + rs.getInt("PRODUCTO_ID") + ".png");
				if (urlIcono == null) continue;

				ImageIconResize prodIcono = new ImageIconResize(urlIcono).getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);

				prod.setIcono(prodIcono);

				productos.add(prod);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
