import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Utils.ButtonColumn;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CarritoUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblTotalPrecio;

	/**
	 * Create the frame.
	 */
	public CarritoUI() {
		crearInterfaz();
	}
	
	public void crearInterfaz() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		cargarCarritoProductos();
	}
	
	private void cargarCarritoProductos() {
		
		String[] tablaNombreColumnas = {
				"" /*imagen*/,
				Idiomas.getTraduccionFormato("NOMBRE"),
				Idiomas.getTraduccionFormato("PRECIO"),
				Idiomas.getTraduccionFormato("CANTIDAD"),
				"", /*añadir*/
				"" /*eliminar*/
		};

		DefaultTableModel tablaDatos = new DefaultTableModel(tablaNombreColumnas, 0);

		for(Producto p : Principal.getProductosCarrito()) {
			Object[] row = crearFilaObjeto(p);
			tablaDatos.addRow(row);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 249);
		contentPane.add(scrollPane);

		JTable tablaCarrito = new JTable(tablaDatos) {
			public Class getColumnClass(int column) {
				return (column == 0) ? ImageIcon.class : Object.class;
			}
		};
		scrollPane.setViewportView(tablaCarrito);

		ButtonColumn btnAñadir = new ButtonColumn(tablaCarrito, new AbstractAction() {
			public void actionPerformed(ActionEvent e)
			{
				int id = Integer.valueOf(e.getActionCommand());

				Producto p = Principal.getProductosCarrito().get(id);
				if (p == null) return;
				
				p.setCantidad(p.getCantidad() + 1);

				actualizarFilaTabla(tablaDatos, id, crearFilaObjeto(p));
			}

			
		}, tablaNombreColumnas.length - 2);

		ButtonColumn btnEliminar = new ButtonColumn(tablaCarrito, new AbstractAction() {
			public void actionPerformed(ActionEvent e)
			{
				int id = Integer.valueOf(e.getActionCommand());

				Producto p = Principal.getProductosCarrito().get(id);
				if (p == null) return;
				if (p.getCantidad() == 0) return;

				p.setCantidad(p.getCantidad() - 1);

				actualizarFilaTabla(tablaDatos, id, crearFilaObjeto(p));
				
				if (p.getCantidad() == 0) {
					Principal.getProductosCarrito().remove(id);
					
					eliminarFilaTabla(tablaDatos, id);
				}
			}
		}, tablaNombreColumnas.length - 1);

		tablaCarrito.setRowHeight(60);
		tablaCarrito.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaCarrito.setFillsViewportHeight(true);
		
		lblTotalPrecio = new JLabel();	
		lblTotalPrecio.setBounds(10, 271, 208, 14);
		contentPane.add(lblTotalPrecio);
		
		calcularPrecioTotal();
	}

	public void calcularPrecioTotal() {
		double PrecioTotal = 0;
		for (Producto p : Principal.getProductosCarrito()) {
			PrecioTotal += p.getPrecio()*p.getCantidad();
		}
		lblTotalPrecio.setText("Precio Total: " + PrecioTotal + "€");
	}

	private Object[] crearFilaObjeto(Producto p) {
		Object[] row = {p.getIcono(), p.getNombre(), (p.getPrecio() * p.getCantidad()) + "€", p.getCantidad(), "+", "-"};
		return row;
	}
	
	private void actualizarFilaTabla(DefaultTableModel modeloTabla, int id, Object[] row) {
		modeloTabla.removeRow(id);
		modeloTabla.insertRow(id, row);
		calcularPrecioTotal();
	}
	
	private void eliminarFilaTabla(DefaultTableModel tablaDatos, int id) {
		tablaDatos.removeRow(id);
	}
}
