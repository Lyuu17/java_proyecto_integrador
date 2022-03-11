import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PrincipalUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PrincipalUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JButton btnCuenta = new JButton("Cuenta");
		btnCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SesionUI sesionUI = new SesionUI();
				sesionUI.setVisible(true);
			}
		});
		btnCuenta.setBounds(10, 11, 89, 23);
		contentPane.add(btnCuenta);
		
		ResultSet rsDatosLength = GestorBD.consulta("SELECT COUNT(*) FROM tiendas");
		ResultSet rs = GestorBD.consulta("SELECT direccion, numero, codigo_postal, poblacion, ciudad FROM tiendas");
		
		String[] tablaNombreColumnas = {"Dirección",
                "Nº",
                "Código Postal",
                "Población",
                "Ciudad"};

		Object[][] tablaDatos = null;

		try {
			rsDatosLength.next();
			
			tablaDatos = new Object[rsDatosLength.getInt(1)][];
			
			int row = 0;
			while(rs.next()) {
				tablaDatos[row] = new Object[tablaNombreColumnas.length];
				
				for(int i = 0; i < tablaNombreColumnas.length; i++) {					
					tablaDatos[row][i] = rs.getString(i + 1);
				}
				
				row++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		final JTable tablaTiendas = new JTable(tablaDatos, tablaNombreColumnas);
		tablaTiendas.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tablaTiendas.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(tablaTiendas);
		scrollPane.setBounds(10, 45, 424, 215);
		contentPane.add(scrollPane);
	}
}
