import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import utils.ButtonColumn;
import utils.ImageIconResize;
import utils.JTreeExpand;
import utils.NodeTreeCellRenderer;
import utils.PlaceHolderFormattedTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.CardLayout;

@SuppressWarnings("serial")
public class TiendaProductosUI extends JFrame implements InterfazComun {

	private JPanel contentPane;

	JComboBox<String> comboIdiomas;

	public TiendaProductosUI() {
		crearInterfaz();
	}

	/**
	 * Create the frame.
	 */
	public void crearInterfaz() {
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

		JButton btnCuenta = new JButton(Idiomas.getTraduccionFormato("CUENTA"));
		btnCuenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SesionUI sesionUI = new SesionUI();
				sesionUI.setVisible(true);
			}
		});
		btnCuenta.setBounds(455, 12, 110, 21);
		contentPane.add(btnCuenta);

		cargarListaProductos();

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
		contentPane.add(lblUltimosProds);
		lblUltimosProds.setFont(new Font("Tahoma", Font.BOLD, 15));

		JButton btnAtras = new JButton(Idiomas.getTraduccionFormato("ATRAS"));
		btnAtras.setBounds(10, 324, 89, 23);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal.getTiendasUI().setVisible(true);

				dispose();
			}
		});

		contentPane.add(btnAtras);

		comboIdiomas = new JComboBox<String>();
		comboIdiomas.setBounds(109, 324, 94, 23);
		//comboIdiomas.setRenderer(new IdiomaRenderer());
		comboIdiomas.addItem("Espanol");
		comboIdiomas.addItem("English");

		/**
		 * https://stackoverflow.com/questions/58939/jcombobox-selection-change-listener
		 */
		comboIdiomas.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				String idiomaSeleccionado = comboIdiomas.getItemAt(comboIdiomas.getSelectedIndex());
				if (idiomaSeleccionado.equals(Idiomas.getIdiomaActual())) return;

				Idiomas.setIdiomaActual(idiomaSeleccionado);

				refrescarIdioma();
			}
		});

		contentPane.add(comboIdiomas);
		
		JButton btnCarrito = new JButton();
		btnCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarritoUI carritoUI = new CarritoUI();
				carritoUI.setVisible(true);
			}
		});
		btnCarrito.setBounds(577, 13, 68, 46);
		
		ImageIconResize iconoCarrito = new ImageIconResize(TiendaProductosUI.class.getResource("/images/carrito.png")).getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
		btnCarrito.setIcon(iconoCarrito);
		
		contentPane.add(btnCarrito);
	}

	private void cargarListaProductos() {
		String[] tablaNombreColumnas = {
				"" /*imagen*/,
				Idiomas.getTraduccionFormato("NOMBRE"),
				Idiomas.getTraduccionFormato("PRECIO"),
				"" /*consultar*/
		};

		DefaultTableModel tablaDatos = new DefaultTableModel(tablaNombreColumnas, 0);

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

				URL urlIcono = TiendaProductosUI.class.getResource("/productos/" + rs.getInt("PRODUCTO_ID") + ".png");
				if (urlIcono == null) continue;

				ImageIconResize prodIcono = new ImageIconResize(urlIcono).getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);

				prod.setIcono(prodIcono);

				Principal.getProductos().add(prod);

				Object[] row = {prod.getIcono(), prod.getNombre(), prod.getPrecio() + "€", Idiomas.getTraduccionFormato("VER")};
				tablaDatos.addRow(row);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(151, 71, 494, 249);
		contentPane.add(scrollPane);

		JTable tablaProductos = new JTable(tablaDatos) {
			public Class getColumnClass(int column) {
				return (column == 0) ? ImageIcon.class : Object.class;
			}
		};

		ButtonColumn btnConsultar = new ButtonColumn(tablaProductos, new AbstractAction() {
			public void actionPerformed(ActionEvent e)
			{
				int id = Integer.valueOf(e.getActionCommand());

				Producto p = Principal.getProductos().get(id);
				ProductoUI prodUI = new ProductoUI(p);
				prodUI.setVisible(true);
			}
		}, 3);

		btnConsultar.setMnemonic(KeyEvent.VK_D);

		tablaProductos.setRowHeight(60);
		tablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaProductos.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablaProductos);
	}

	@Override
	public void procesar() {

	}

	@Override
	public void refrescarIdioma() {
		int id = comboIdiomas.getSelectedIndex();
		boolean visibilidad = isVisible();

		crearInterfaz();

		setVisible(visibilidad);
		comboIdiomas.setSelectedIndex(id);
	}
}
