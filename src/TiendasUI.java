import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Utils.PlaceHolderFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class TiendasUI extends JFrame implements InterfazComun {

	private JPanel contentPane;
	
	private JTable tablaTiendas;
	private Object[][] tablaDatos;
	
	JComboBox<String> comboIdiomas;
	
	public TiendasUI() {
		
	}

	/**
	 * Create the frame.
	 */
	@Override
	public void crearInterfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		String[] tablaNombreColumnas = {
				Idiomas.getTraduccionFormato("DIRECCION"),
                "Nº",
                Idiomas.getTraduccionFormato("CODIGO_POSTAL"),
                Idiomas.getTraduccionFormato("POBLACION"),
                Idiomas.getTraduccionFormato("CIUDAD")};

		tablaDatos = crearListaTiendas(tablaNombreColumnas.length, "*");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 650, 205);
		contentPane.add(scrollPane);
		
		tablaTiendas = new JTable(tablaDatos, tablaNombreColumnas) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		scrollPane.setViewportView(tablaTiendas);
		tablaTiendas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaTiendas.setFillsViewportHeight(true);
				
		JButton btnSeleccionarTienda = new JButton(Idiomas.getTraduccionFormato("SELECCIONAR_TIENDA"));
		
		btnSeleccionarTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal.getTiendaProductosUI().setVisible(true);
				
				dispose();
			}
		});
		btnSeleccionarTienda.setBounds(114, 226, 163, 23);
		contentPane.add(btnSeleccionarTienda);
				
		PlaceHolderFormattedTextField fttdCP = new PlaceHolderFormattedTextField();
		fttdCP.setPlaceholder(Idiomas.getTraduccionFormato("BUSCAR_POR_CP"));
		fttdCP.setBounds(482, 227, 178, 20);
		
		fttdCP.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				actualizarListaTiendas(tablaNombreColumnas, scrollPane, fttdCP);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				actualizarListaTiendas(tablaNombreColumnas, scrollPane, fttdCP);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {}
			
			private void actualizarListaTiendas(String[] tablaNombreColumnas, JScrollPane scrollPane,
					PlaceHolderFormattedTextField fttdCP) {
				tablaDatos = crearListaTiendas(tablaNombreColumnas.length, fttdCP.getText());
				
				tablaTiendas = new JTable(tablaDatos, tablaNombreColumnas);
				scrollPane.setViewportView(tablaTiendas);
				tablaTiendas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tablaTiendas.setFillsViewportHeight(true);
			}
		});
		
		contentPane.add(fttdCP);
		
		comboIdiomas = new JComboBox<String>();
		comboIdiomas.setBounds(10, 227, 94, 23);
		comboIdiomas.setRenderer(new IdiomaRenderer());
		comboIdiomas.addItem("Espanol");
		comboIdiomas.addItem("English");
		comboIdiomas.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent  e) {
				int id = comboIdiomas.getSelectedIndex();
				String idiomaSeleccionado = comboIdiomas.getItemAt(id);
				if (idiomaSeleccionado.equals(Idiomas.getIdiomaActual())) return;
				
            	Idiomas.setIdiomaActual(idiomaSeleccionado);
            	
            	refrescarIdioma();
            }
        });
		
		contentPane.add(comboIdiomas);
	}
	
	private Object[][] crearListaTiendas(int columnas, String busq) {
		String str = "SELECT direccion, numero, codigo_postal, poblacion, ciudad FROM tiendas";
		String str2 = "SELECT COUNT(*) FROM tiendas";
		if (!busq.equals("*")) {
			str += " WHERE codigo_postal = '" + busq + "'";
			str2 += " WHERE codigo_postal = '" + busq + "'";
		}
		
		
		ResultSet rs = GestorBD.consulta(str);
		ResultSet rsDatosLength = GestorBD.consulta(str2);

		Object[][] tablaDatos = null;

		try {
			rsDatosLength.next();

			tablaDatos = new Object[rsDatosLength.getInt(1)][];
			
			int row = 0;
			while(rs.next()) {
				tablaDatos[row] = new Object[columnas];
				
				for(int i = 0; i < columnas; i++) {					
					tablaDatos[row][i] = rs.getString(i + 1);
				}
				
				row++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return tablaDatos;
	}

	@Override
	public void procesar() {
		// TODO Auto-generated method stub
		
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
