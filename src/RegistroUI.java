import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RegistroUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public RegistroUI(String usuario, String contraseña) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 267, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JFormattedTextField fttdNombre = new JFormattedTextField();
		fttdNombre.setBounds(109, 8, 132, 20);
		contentPane.add(fttdNombre);
		
		JFormattedTextField fttdApellidos = new JFormattedTextField();
		fttdApellidos.setBounds(109, 39, 132, 20);
		contentPane.add(fttdApellidos);
		
		JSeparator s = new JSeparator();
		s.setToolTipText("");
		s.setBounds(10, 70, 231, 10);
		contentPane.add(s);
		
		JFormattedTextField fttdEmail = new JFormattedTextField();
		fttdEmail.setBounds(109, 88, 132, 20);
		contentPane.add(fttdEmail);
		
		JFormattedTextField fttdCodigoPostal = new JFormattedTextField();
		fttdCodigoPostal.setBounds(109, 206, 132, 20);
		contentPane.add(fttdCodigoPostal);
		
		JFormattedTextField fttdNumTlf = new JFormattedTextField();
		fttdNumTlf.setBounds(109, 113, 132, 20);
		contentPane.add(fttdNumTlf);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 89, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 42, 89, 14);
		contentPane.add(lblApellidos);
		
		JLabel lblDireccion1 = new JLabel("Direcci\u00F3n 1");
		lblDireccion1.setBounds(10, 162, 89, 14);
		contentPane.add(lblDireccion1);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 91, 89, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNumTlf = new JLabel("Tlf");
		lblNumTlf.setBounds(10, 116, 89, 14);
		contentPane.add(lblNumTlf);
		
		JFormattedTextField fttdDireccion1 = new JFormattedTextField();
		fttdDireccion1.setBounds(109, 159, 132, 20);
		contentPane.add(fttdDireccion1);
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal");
		lblCodigoPostal.setBounds(10, 209, 89, 14);
		contentPane.add(lblCodigoPostal);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(10, 186, 89, 14);
		contentPane.add(lblCiudad);
		
		JFormattedTextField fttdCiudad = new JFormattedTextField();
		fttdCiudad.setBounds(109, 183, 132, 20);
		contentPane.add(fttdCiudad);
		
		JLabel lblNewLabel = new JLabel("Datos de contacto");
		lblNewLabel.setBounds(10, 70, 231, 14);
		contentPane.add(lblNewLabel);
		
		JSeparator s_1 = new JSeparator();
		s_1.setToolTipText("");
		s_1.setBounds(10, 141, 231, 10);
		contentPane.add(s_1);
		
		JLabel lblNewLabel_1 = new JLabel("Datos de env\u00EDo");
		lblNewLabel_1.setBounds(10, 141, 89, 14);
		contentPane.add(lblNewLabel_1);
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(79, 237, 89, 23);
		
		contentPane.add(btnEnviar);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fttdNombre.getText().isEmpty() ||
					fttdApellidos.getText().isEmpty() ||
					fttdEmail.getText().isEmpty() ||
					fttdNumTlf.getText().isEmpty() ||
					fttdDireccion1.getText().isEmpty() ||
					fttdCiudad.getText().isEmpty() ||
					fttdCodigoPostal.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Faltan campos por rellenar", "Registro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Connection conn = GestorBD.getConexion();
				PreparedStatement stmt;
				try {
					String hashContraseña = Utils.hashContraseña(contraseña);
					
					stmt = conn.prepareStatement("INSERT INTO usuarios (id, usuario, contraseña) VALUES (ID_AUTO_INCREMENT_SEQ.NEXTVAL, ?, ?)", Statement.RETURN_GENERATED_KEYS);
					stmt.setString(1, usuario);
					stmt.setString(2, hashContraseña);
					int id = stmt.executeUpdate();
					
					stmt = conn.prepareStatement("INSERT INTO datos_usuarios (id, nombre, apellidos, email, tlf, direccion, ciudad, codigopostal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
					stmt.setInt(1, id);
					stmt.setString(2, fttdNombre.getText());
					stmt.setString(3, fttdApellidos.getText());
					stmt.setString(4, fttdEmail.getText());
					stmt.setString(5, fttdNumTlf.getText());
					stmt.setString(6, fttdDireccion1.getText());
					stmt.setString(7, fttdCiudad.getText());
					stmt.setInt(8, Integer.parseInt(fttdCodigoPostal.getText()));
					stmt.executeUpdate();
					
					Cuenta.setIniciadoSesion(true);
					
					JOptionPane.showMessageDialog(contentPane, "OK", "Registro", JOptionPane.PLAIN_MESSAGE);
					
					//dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		
	}
}
