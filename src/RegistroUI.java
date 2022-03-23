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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class RegistroUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public RegistroUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JFormattedTextField fttdNombre = new JFormattedTextField();
		fttdNombre.setBounds(263, 11, 132, 20);
		contentPane.add(fttdNombre);
		
		JFormattedTextField fttdApellidos = new JFormattedTextField();
		fttdApellidos.setBounds(263, 42, 132, 20);
		contentPane.add(fttdApellidos);
		
		JSeparator s = new JSeparator();
		s.setToolTipText("Datos de contacto");
		s.setBounds(164, 104, 231, 10);
		contentPane.add(s);
		
		JFormattedTextField fttdEmail = new JFormattedTextField();
		fttdEmail.setBounds(263, 132, 132, 20);
		contentPane.add(fttdEmail);
		
		JFormattedTextField fttdCodigoPostal = new JFormattedTextField();
		fttdCodigoPostal.setBounds(263, 250, 132, 20);
		contentPane.add(fttdCodigoPostal);
		
		JFormattedTextField fttdNumTlf = new JFormattedTextField();
		fttdNumTlf.setBounds(263, 157, 132, 20);
		contentPane.add(fttdNumTlf);
		
		JLabel lblNombre = new JLabel(Idiomas.getTraduccionFormato("NOMBRE"));
		lblNombre.setBounds(164, 14, 89, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellidos = new JLabel(Idiomas.getTraduccionFormato("APELLIDOS"));
		lblApellidos.setBounds(164, 45, 89, 14);
		contentPane.add(lblApellidos);
		
		JLabel lblDireccion1 = new JLabel(Idiomas.getTraduccionFormato("DIRECCION"));
		lblDireccion1.setBounds(164, 206, 89, 14);
		contentPane.add(lblDireccion1);
		
		JLabel lblEmail = new JLabel(Idiomas.getTraduccionFormato("EMAIL"));
		lblEmail.setBounds(164, 135, 89, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNumTlf = new JLabel(Idiomas.getTraduccionFormato("TELEFONO"));
		lblNumTlf.setBounds(164, 160, 89, 14);
		contentPane.add(lblNumTlf);
		
		JFormattedTextField fttdDireccion1 = new JFormattedTextField();
		fttdDireccion1.setBounds(263, 203, 132, 20);
		contentPane.add(fttdDireccion1);
		
		JLabel lblCodigoPostal = new JLabel(Idiomas.getTraduccionFormato("CODIGO_POSTAL"));
		lblCodigoPostal.setBounds(164, 253, 89, 14);
		contentPane.add(lblCodigoPostal);
		
		JLabel lblCiudad = new JLabel(Idiomas.getTraduccionFormato("CIUDAD"));
		lblCiudad.setBounds(164, 230, 89, 14);
		contentPane.add(lblCiudad);
		
		JFormattedTextField fttdCiudad = new JFormattedTextField();
		fttdCiudad.setBounds(263, 227, 132, 20);
		contentPane.add(fttdCiudad);
		
		JLabel lblNewLabel = new JLabel(Idiomas.getTraduccionFormato("DATOS_CONTACTO"));
		lblNewLabel.setBounds(164, 114, 231, 14);
		contentPane.add(lblNewLabel);
		
		JSeparator s_1 = new JSeparator();
		s_1.setToolTipText(Idiomas.getTraduccionFormato("DATOS_ENVIO"));
		s_1.setBounds(164, 185, 231, 10);
		contentPane.add(s_1);
		
		JLabel lblNewLabel_1 = new JLabel(Idiomas.getTraduccionFormato("DATOS_ENVIO"));
		lblNewLabel_1.setBounds(164, 185, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnEnviar = new JButton(Idiomas.getTraduccionFormato("ENVIAR"));
		btnEnviar.setBounds(10, 278, 385, 23);
		contentPane.add(btnEnviar);
		
		JFormattedTextField fttdContraseña = new JFormattedTextField();
		fttdContraseña.setBounds(10, 157, 132, 20);
		contentPane.add(fttdContraseña);
		
		JFormattedTextField fttdUsuario = new JFormattedTextField();
		fttdUsuario.setBounds(10, 104, 132, 20);
		contentPane.add(fttdUsuario);
		
		JLabel lblUsuario = new JLabel(Idiomas.getTraduccionFormato("USUARIO"));
		lblUsuario.setBounds(10, 86, 132, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContraseña = new JLabel(Idiomas.getTraduccionFormato("CONTRASENHA"));
		lblContraseña.setBounds(10, 135, 132, 14);
		contentPane.add(lblContraseña);
		
		JLabel lblFechaNacimiento = new JLabel(Idiomas.getTraduccionFormato("FECHA_NAC"));
		lblFechaNacimiento.setBounds(163, 77, 89, 14);
		contentPane.add(lblFechaNacimiento);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(148, 11, 6, 243);
		contentPane.add(separator);
		
		JDateChooser dateFechaNac = new JDateChooser();
		dateFechaNac.setBounds(263, 73, 132, 20);
		contentPane.add(dateFechaNac);
		
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
					String hashContraseña = Utils.hashContraseña(fttdContraseña.getText());

					stmt = conn.prepareStatement("INSERT INTO usuarios (id, usuario, contraseña) VALUES (ID_AUTO_INCREMENT_SEQ.NEXTVAL, ?, ?)");

					GestorBD.consulta(stmt, fttdUsuario.getText(), hashContraseña);
					
					stmt = conn.prepareStatement("INSERT INTO datos_usuarios (id, nombre, apellidos, email, tlf, direccion, ciudad, codigopostal) VALUES (ID_AUTO_INCREMENT_SEQ.CURRVAL, ?, ?, ?, ?, ?, ?, ?)");
					
					GestorBD.consulta(stmt,fttdNombre.getText(), fttdApellidos.getText(), fttdEmail.getText(), fttdNumTlf.getText(), fttdDireccion1.getText(), fttdCiudad.getText(), fttdCodigoPostal.getText());
					
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
