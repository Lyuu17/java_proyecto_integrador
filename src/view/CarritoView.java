package view;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import app.Producto;
import utils.ButtonColumn;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class CarritoView extends JFrame {

	private JPanel contentPane;
	private JLabel lblTotalPrecio;

	private JTable tablaCarrito;
	private JScrollPane scrollPane;
	private DefaultTableModel tablaDatos;
	private JButton btnComprar;
	
	private ArrayList<Producto> productos;
	
	/**
	 * Create the frame.
	 */
	public CarritoView(ArrayList<Producto> productos) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 249);
		contentPane.add(scrollPane);
		
		int columnaImagen = 0;
		tablaCarrito = new JTable() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column) {
				return (column == columnaImagen) ? ImageIcon.class : Object.class;
			}
		};
		
		tablaCarrito.setRowHeight(60);
		tablaCarrito.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaCarrito.setFillsViewportHeight(true);
		
		lblTotalPrecio = new JLabel();	
		lblTotalPrecio.setBounds(10, 271, 208, 14);
		contentPane.add(lblTotalPrecio);
		
		btnComprar = new JButton("Comprar");
		btnComprar.setBounds(324, 264, 98, 26);
		contentPane.add(btnComprar);
		
		this.productos = productos;
	}
	
	public void addComprarListener(ActionListener al) {
		btnComprar.addActionListener(al);
	}
	
	public void cargarCarritoProductos(String[] tablaNombreColumnas, AbstractAction actAñadir, AbstractAction actEliminar) {
		tablaDatos = new DefaultTableModel(tablaNombreColumnas, 0);

		insertarProductos(productos);
		
		tablaCarrito.setModel(tablaDatos);

		new ButtonColumn(tablaCarrito, actAñadir, tablaNombreColumnas.length - 2);
		new ButtonColumn(tablaCarrito, actEliminar, tablaNombreColumnas.length - 1);

		scrollPane.setViewportView(tablaCarrito);
		
		calcularPrecioTotal();
	}

	public void calcularPrecioTotal() {
		double PrecioTotal = 0;
		for (Producto p : productos) {
			PrecioTotal += p.getPrecio()*p.getCantidad();
		}
		lblTotalPrecio.setText("Precio Total: " + PrecioTotal + "€");
	}

	public void insertarProductos(ArrayList<Producto> productos) {
		for(Producto producto : productos) {
			insertarProducto(producto);
		}
	}
	
	public void insertarProducto(Producto p) {
		Object[] row = {p.getIcono(), p.getNombre(), (p.getPrecio() * p.getCantidad()) + "€", p.getCantidad(), "+", "-"};
		tablaDatos.addRow(row);
	}
	
	public Object[] crearFilaObjeto(Producto p) {
		Object[] row = {p.getIcono(), p.getNombre(), (p.getPrecio() * p.getCantidad()) + "€", p.getCantidad(), "+", "-"};
		return row;
	}

	public void actualizarFilaTabla(int id, Object[] row) {
		tablaDatos.removeRow(id);
		tablaDatos.insertRow(id, row);
		calcularPrecioTotal();
	}
	
	public void eliminarFilaTabla(int id) {
		tablaDatos.removeRow(id);
	}
}
