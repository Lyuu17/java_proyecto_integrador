package view;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import app.IdiomaRenderer;
import app.Idiomas;
import app.Producto;
import utils.ButtonColumn;
import utils.ImageIconResize;
import utils.JTreeExpand;
import utils.NodeTreeCellRenderer;
import utils.PlaceHolderFormattedTextField;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class TiendaProductosView extends JFrame {

	private JPanel contentPane;

	private JComboBox<String> comboIdiomas;
	
	private JButton btnCuenta;
	
	private JScrollPane scrollPane;
	private DefaultTableModel tablaDatos;
	private JTable tablaProductos;
	
	private JButton btnCarrito;
	private JButton btnAtras;

	public TiendaProductosView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		PlaceHolderFormattedTextField fttdBuscarProductos = new PlaceHolderFormattedTextField();
		fttdBuscarProductos.setBounds(151, 12, 292, 20);
		fttdBuscarProductos.setPlaceholder(Idiomas.getTraduccionFormato("BUSCAR_PRODUCTOS"));
		contentPane.add(fttdBuscarProductos);

		btnCuenta = new JButton(Idiomas.getTraduccionFormato("CUENTA"));
		btnCuenta.setBounds(455, 12, 110, 21);
		contentPane.add(btnCuenta);

		DefaultMutableTreeNode nodeCategorias = new DefaultMutableTreeNode(Idiomas.getTraduccionFormato("CATEGORIAS"));
		JTreeExpand treeCategorias = new JTreeExpand(nodeCategorias);
		treeCategorias.setFont(new Font("Tahoma", Font.PLAIN, 16));
		treeCategorias.setBorder(new LineBorder(new Color(0, 0, 0)));
		treeCategorias.setBounds(10, 14, 131, 306);
		treeCategorias.setCellRenderer(new NodeTreeCellRenderer("categorias"));

		nodeCategorias.add(new DefaultMutableTreeNode(Idiomas.getTraduccionFormato("ROPA")));
		nodeCategorias.add(new DefaultMutableTreeNode(Idiomas.getTraduccionFormato("COMIDA")));
		nodeCategorias.add(new DefaultMutableTreeNode(Idiomas.getTraduccionFormato("LIBROS")));

		treeCategorias.expandTree(true);
		contentPane.add(treeCategorias);

		JLabel lblUltimosProds = new JLabel(Idiomas.getTraduccionFormato("ULTIMOS_PRODUCTOS"));
		lblUltimosProds.setForeground(Color.BLACK);
		lblUltimosProds.setBounds(151, 40, 394, 19);
		lblUltimosProds.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblUltimosProds);

		btnAtras = new JButton(Idiomas.getTraduccionFormato("ATRAS"));
		btnAtras.setBounds(10, 324, 89, 23);

		contentPane.add(btnAtras);

		comboIdiomas = new JComboBox<String>();
		comboIdiomas.setBounds(109, 324, 131, 23);
		comboIdiomas.setRenderer(new IdiomaRenderer());
		comboIdiomas.addItem("Espanol");
		comboIdiomas.addItem("English");
		contentPane.add(comboIdiomas);
		
		btnCarrito = new JButton();
		btnCarrito.setBounds(577, 13, 68, 46);
		
		ImageIconResize iconoCarrito = new ImageIconResize(TiendaProductosView.class.getResource("/images/carrito.png")).getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
		btnCarrito.setIcon(iconoCarrito);
		
		contentPane.add(btnCarrito);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(151, 71, 494, 249);
		contentPane.add(scrollPane);
		
		int columnaImagen = 0;
		tablaProductos = new JTable() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
				return (column == columnaImagen) ? ImageIcon.class : Object.class;
			}
		};
	}
	
	public void addIdiomaComboboxListener(ActionListener al) {
		comboIdiomas.addActionListener(al);
	}
	
	public int getIdiomaComboboxItemSeleccionado() {
		return comboIdiomas.getSelectedIndex();
	}
	
	public String getIdiomaComboboxItem(int id) {
		return comboIdiomas.getItemAt(id);
	}
	
	public void addCarritoListener(ActionListener al) {
		btnCarrito.addActionListener(al);
	}
	
	public void addAtrasListener(ActionListener al) {
		btnAtras.addActionListener(al);
	}
	
	public void addCuentaListener(ActionListener al) {
		btnCuenta.addActionListener(al);
	}
	
	public void insertarProductos(ArrayList<Producto> productos) {
		for(Producto producto : productos) {
			insertarProducto(producto);
		}
	}
	
	public void insertarProducto(Producto p) {
		Object[] row = {p.getIcono(), p.getNombre(), p.getPrecio() + "€", Idiomas.getTraduccionFormato("VER")};
		tablaDatos.addRow(row);
	}

	public void cargarListaProductos(String[] tablaNombreColumnas, AbstractAction actConsultar, ArrayList<Producto> productos) {
		tablaDatos = new DefaultTableModel(tablaNombreColumnas, 0);
		
		insertarProductos(productos);
		
		tablaProductos.setModel(tablaDatos);
		tablaProductos.setRowHeight(60);
		tablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaProductos.setFillsViewportHeight(true);

		ButtonColumn btnConsultar = new ButtonColumn(tablaProductos, actConsultar, 3);

		btnConsultar.setMnemonic(KeyEvent.VK_D);

		scrollPane.setViewportView(tablaProductos);
	}
}
