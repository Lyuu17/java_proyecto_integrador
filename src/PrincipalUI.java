import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
		
		String[] tablaNombreColumnas = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

		Object[][] tablaDatos = {
		{"Kathy", "Smith",
		"Snowboarding", new Integer(5), new Boolean(false)},
		{"John", "Doe",
		"Rowing", new Integer(3), new Boolean(true)},
		{"Sue", "Black",
		"Knitting", new Integer(2), new Boolean(false)},
		{"Jane", "White",
		"Speed reading", new Integer(20), new Boolean(true)},
		{"Joe", "Brown",
		"Pool", new Integer(10), new Boolean(false)}
		};

		final JTable tablaTiendas = new JTable(tablaDatos, tablaNombreColumnas);
		tablaTiendas.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tablaTiendas.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(tablaTiendas);
		scrollPane.setBounds(10, 45, 424, 215);
		contentPane.add(scrollPane);
	}
}
