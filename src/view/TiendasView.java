package view;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
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
		
		tablaTiendas = new JTable() {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
				
		btnSeleccionarTienda = new JButton(Idiomas.getTraduccionFormato("SELECCIONAR_TIENDA"));
		btnSeleccionarTienda.setBounds(132, 226, 163, 23);
		contentPane.add(btnSeleccionarTienda);
				
		PlaceHolderFormattedTextField fttdCP = new PlaceHolderFormattedTextField();
		fttdCP.setPlaceholder(Idiomas.getTraduccionFormato("BUSCAR_POR_CP"));
		fttdCP.setBounds(482, 227, 178, 20);
		
		fttdCP.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				actualizarListaTiendas(scrollPane, fttdCP);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				actualizarListaTiendas(scrollPane, fttdCP);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {}
			
			private void actualizarListaTiendas(JScrollPane scrollPane,
					PlaceHolderFormattedTextField fttdCP) {
			}
		});
		
		contentPane.add(fttdCP);
		
		comboIdiomas = new JComboBox<String>();
		comboIdiomas.setBounds(10, 226, 114, 23);
		comboIdiomas.setRenderer(new IdiomaRenderer());
		comboIdiomas.addItem("Espanol");
		comboIdiomas.addItem("English");
		
		contentPane.add(comboIdiomas);
	}
	
	public void addSeleccionarTiendaListener(ActionListener al) {
		btnSeleccionarTienda.addActionListener(al);
	}
	
	public int getTiendaFilaSeleccionada() {
		return tablaTiendas.getSelectedRow();
	}
	
	public void cargarListaTiendas(String tablaNombreColumnas[], ArrayList<Tienda> tiendas) {
		tablaDatos = new DefaultTableModel(tablaNombreColumnas, 0);
		
		insertarTiendas(tiendas);
		
		tablaTiendas.setModel(tablaDatos);
		tablaTiendas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaTiendas.setFillsViewportHeight(true);
		
		scrollPane.setViewportView(tablaTiendas);
	}
	
	public void insertarTiendas(ArrayList<Tienda> tiendas) {
		for(Tienda tienda : tiendas) {
			insertarTienda(tienda);
		}
	}
	
	public void insertarTienda(Tienda tienda) {
		Object[] row = {tienda.getDireccion(), tienda.getNumero(), tienda.getCodigo_postal(), tienda.getPoblacion(), tienda.getCiudad()};
		tablaDatos.addRow(row);
	}
}
