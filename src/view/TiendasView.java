package view;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import app.IdiomaRenderer;
import app.Idiomas;
import app.Tienda;
import utils.PlaceHolderFormattedTextField;

import java.awt.event.ActionListener;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class TiendasView extends JFrame {

	private JPanel contentPane;
	
	private JTable tablaTiendas;
	private DefaultTableModel tablaDatos;
	
	private JScrollPane scrollPane;
	
	private JButton btnSeleccionarTienda;
	
	private JComboBox<String> comboIdiomas;

	private PlaceHolderFormattedTextField fttdCP;
	
	/**
	 * 
	 */
	public TiendasView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 650, 205);
		contentPane.add(scrollPane);
				
		btnSeleccionarTienda = new JButton(Idiomas.getTraduccionFormato("SELECCIONAR_TIENDA"));
		btnSeleccionarTienda.setBounds(132, 226, 163, 23);
		contentPane.add(btnSeleccionarTienda);
				
		fttdCP = new PlaceHolderFormattedTextField();
		fttdCP.setPlaceholder(Idiomas.getTraduccionFormato("BUSCAR_POR_CP"));
		fttdCP.setBounds(482, 227, 178, 20);
		
		contentPane.add(fttdCP);
		
		comboIdiomas = new JComboBox<String>();
		comboIdiomas.setBounds(10, 226, 114, 23);
		comboIdiomas.setRenderer(new IdiomaRenderer());
		comboIdiomas.addItem("Espanol");
		comboIdiomas.addItem("English");
		
		contentPane.add(comboIdiomas);
	}
	
	/**
	 * 
	 * @param al ActionListener para el boton btnSeleccionarTienda
	 */
	public void addSeleccionarTiendaListener(ActionListener al) {
		btnSeleccionarTienda.addActionListener(al);
	}
	
	/**
	 * 
	 * @param dl DocumentListener para el documento fttdCP
	 */
	public void addActualizarTiendaListener(DocumentListener dl) {
		fttdCP.getDocument().addDocumentListener(dl);
	}
	
	/**
	 * 
	 * @param al ActionListener
	 */
	public void addIdiomaComboboxListener(ActionListener al) {
		comboIdiomas.addActionListener(al);
	}
	
	/**
	 * 
	 * @return el idioma seleccionado en el combobox de idiomas
	 */
	public int getIdiomaComboboxItemSeleccionado() {
		return comboIdiomas.getSelectedIndex();
	}
	
	/**
	 * 
	 * @param id el id del combobox de idiomas
	 * @return el string que contiene el combobox
	 */
	public String getIdiomaComboboxItem(int id) {
		return comboIdiomas.getItemAt(id);
	}
	
	/**
	 * 
	 * @return el id de la fila seleccionada en la JTable Tiendas
	 */
	public int getTiendaFilaSeleccionada() {
		return tablaTiendas.getSelectedRow();
	}
	
	/**
	 * 
	 * @param tablaNombreColumnas Array de String con el nombre de las oclumnas
	 * @param tiendas ArrayList con las Tiendas
	 */
	public void cargarListaTiendas(String tablaNombreColumnas[], ArrayList<Tienda> tiendas) {
		tablaDatos = new DefaultTableModel(tablaNombreColumnas, 0);
		
		insertarTiendas(tiendas);
		
		tablaTiendas = new JTable() {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};

		tablaTiendas.setModel(tablaDatos);
		tablaTiendas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaTiendas.setFillsViewportHeight(true);
		
		scrollPane.setViewportView(tablaTiendas);
	}
	
	/**
	 * Insertar tiendas de un ArrayList
	 * @param tiendas ArrayList de tiendas a añadir
	 */
	public void insertarTiendas(ArrayList<Tienda> tiendas) {
		for(Tienda tienda : tiendas) {
			insertarTienda(tienda);
		}
	}
	
	/**
	 * Insertar una tienda
	 * @param tienda la tienda a insertar
	 */
	public void insertarTienda(Tienda tienda) {
		Object[] row = {tienda.getDireccion(), tienda.getNumero(), tienda.getCodigo_postal(), tienda.getPoblacion(), tienda.getCiudad()};
		tablaDatos.addRow(row);
	}

	/**
	 * 
	 * @return el codigo postal introducido en String
	 */
	public String getCodigoPostalIntroducido() {
		return fttdCP.getText();
	}
}
