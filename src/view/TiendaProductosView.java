package view;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import app.Idiomas;
import app.Producto;
import app.ProductosCategorias;
import utils.ButtonColumn;
import utils.DefaultTreeNodeCategorias;
import utils.ImageIconResize;
import utils.JTreeExpand;
import utils.NodeTreeCellRenderer;
import utils.PlaceHolderFormattedTextField;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * 
 * @author daw
 *
 */
@SuppressWarnings("serial")
public class TiendaProductosView extends JFrame {

	private JPanel contentPane;
	
	private JButton btnCuenta;
	
	private JScrollPane scrollPane;
	private DefaultTableModel tablaDatos;
	private JTable tablaProductos;
	
	private JButton btnCarrito;
	private JButton btnAtras;
	
	private JTreeExpand treeCategorias;
	private PlaceHolderFormattedTextField fttdBuscarProductos;

	/**
	 * 
	 */
	public TiendaProductosView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		fttdBuscarProductos = new PlaceHolderFormattedTextField();
		fttdBuscarProductos.setBounds(151, 12, 292, 20);
		fttdBuscarProductos.setPlaceholder(Idiomas.getTraduccionFormato("BUSCAR_PRODUCTOS"));
		contentPane.add(fttdBuscarProductos);

		btnCuenta = new JButton(Idiomas.getTraduccionFormato("CUENTA"));
		btnCuenta.setBounds(455, 12, 110, 21);
		contentPane.add(btnCuenta);

		DefaultMutableTreeNode nodeCategorias = new DefaultTreeNodeCategorias(Idiomas.getTraduccionFormato("CATEGORIAS"), 0);
		treeCategorias = new JTreeExpand(nodeCategorias);
		treeCategorias.setFont(new Font("Tahoma", Font.PLAIN, 16));
		treeCategorias.setBorder(new LineBorder(new Color(0, 0, 0)));
		treeCategorias.setBounds(10, 14, 131, 306);
		treeCategorias.setCellRenderer(new NodeTreeCellRenderer("categorias"));

		nodeCategorias.add(new DefaultTreeNodeCategorias(Idiomas.getTraduccionFormato("ROPA"), ProductosCategorias.CAT_ROPA));
		nodeCategorias.add(new DefaultTreeNodeCategorias(Idiomas.getTraduccionFormato("COMIDA"), ProductosCategorias.CAT_COMIDA));
		nodeCategorias.add(new DefaultTreeNodeCategorias(Idiomas.getTraduccionFormato("LIBROS"), ProductosCategorias.CAT_LIBROS));

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
		
		btnCarrito = new JButton();
		btnCarrito.setBounds(577, 13, 68, 46);
		
		ImageIconResize iconoCarrito = new ImageIconResize(TiendaProductosView.class.getResource("/images/carrito.png")).getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
		btnCarrito.setIcon(iconoCarrito);
		
		contentPane.add(btnCarrito);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(151, 71, 494, 249);
		contentPane.add(scrollPane);
	}
	
	/**
	 * 
	 * @param dl DocumentListener para el documento fttdBuscarProductos
	 */
	public void addActualizarProductosListener(DocumentListener dl) {
		fttdBuscarProductos.getDocument().addDocumentListener(dl);
	}
	
	/**
	 * 
	 * @param ma MouseAdapter para el JTree Categorias
	 */
	public void addActualizarProductosCategoriasListener(MouseAdapter ma) {
		treeCategorias.addMouseListener(ma);
	}
	
	/**
	 * 
	 * @return el nodo de arbol seleccionado del JTree Categorias
	 */
	public DefaultMutableTreeNode getTreeNodeLastSelectedPathComponent() {
		return (DefaultMutableTreeNode) treeCategorias.getLastSelectedPathComponent();
	}
	
	/**
	 * 
	 * @return el texto de buscar productos
	 */
	public String getBuscarProductosTexto() {
		return fttdBuscarProductos.getText();
	}
	
	/**
	 * 
	 * @param al ActionListener para el boton btnCarrito
	 */
	public void addCarritoListener(ActionListener al) {
		btnCarrito.addActionListener(al);
	}
	
	/**
	 * 
	 * @param al ActionListener para el boton btnAtras
	 */
	public void addAtrasListener(ActionListener al) {
		btnAtras.addActionListener(al);
	}
	
	/**
	 * 
	 * @param al ActionListener para el boton btnCuenta
	 */
	public void addCuentaListener(ActionListener al) {
		btnCuenta.addActionListener(al);
	}
	
	/**
	 * Insertar los productos de un ArrayList
	 * @param productos productos a insertar
	 */
	public void insertarProductos(ArrayList<Producto> productos) {
		for(Producto producto : productos) {
			insertarProducto(producto);
		}
	}
	
	/**
	 * Insertar producto pasado por parámetro
	 * @param p producto a insertar
	 */
	public void insertarProducto(Producto p) {
		Object[] row = {p.getIcono(), p.getNombre(), p.getPrecio() + "€", Idiomas.getTraduccionFormato("VER")};
		tablaDatos.addRow(row);
	}

	/**
	 * Cargar la lista de productos
	 * @param tablaNombreColumnas Array de String con el nombre de las columnas
	 * @param actConsultar AbstractAction para el boton de columna Consultar
	 * @param productos ArrayList de productos
	 */
	public void cargarListaProductos(String[] tablaNombreColumnas, AbstractAction actConsultar, ArrayList<Producto> productos) {
		tablaDatos = new DefaultTableModel(tablaNombreColumnas, 0);
		
		insertarProductos(productos);
		
		int columnaImagen = 0;
		tablaProductos = new JTable() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
				return (column == columnaImagen) ? ImageIcon.class : Object.class;
			}
		};
		tablaProductos.setModel(tablaDatos);
		tablaProductos.setRowHeight(60);
		tablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaProductos.setFillsViewportHeight(true);

		ButtonColumn btnConsultar = new ButtonColumn(tablaProductos, actConsultar, 3);

		btnConsultar.setMnemonic(KeyEvent.VK_D);

		scrollPane.setViewportView(tablaProductos);
	}
}
