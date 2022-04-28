package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import app.Idiomas;

/**
 * 
 * @author daw
 *
 */
@SuppressWarnings("serial")
public class RegistroView extends JFrame {

	private JPanel contentPane;
	
	private final JFormattedTextField fttdUsuario, fttdContraseña, 
				fttdNombre, fttdApellidos, 
				fttdEmail, fttdNumTlf, 
				fttdDireccion1, fttdCiudad, fttdCodigoPostal;
	private final JDateChooser dateFechaNac;
	
	private final JButton btnEnviar;

	/**
	 * 
	 */
	public RegistroView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		fttdNombre = new JFormattedTextField();
		fttdNombre.setBounds(263, 11, 132, 20);
		contentPane.add(fttdNombre);
		
		fttdApellidos = new JFormattedTextField();
		fttdApellidos.setBounds(263, 42, 132, 20);
		contentPane.add(fttdApellidos);
		
		JSeparator s = new JSeparator();
		s.setToolTipText("Datos de contacto");
		s.setBounds(164, 104, 231, 10);
		contentPane.add(s);
		
		fttdEmail = new JFormattedTextField();
		fttdEmail.setBounds(263, 132, 132, 20);
		contentPane.add(fttdEmail);
		
		fttdCodigoPostal = new JFormattedTextField();
		fttdCodigoPostal.setBounds(263, 250, 132, 20);
		contentPane.add(fttdCodigoPostal);
		
		fttdNumTlf = new JFormattedTextField();
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
		
		fttdDireccion1 = new JFormattedTextField();
		fttdDireccion1.setBounds(263, 203, 132, 20);
		contentPane.add(fttdDireccion1);
		
		JLabel lblCodigoPostal = new JLabel(Idiomas.getTraduccionFormato("CODIGO_POSTAL"));
		lblCodigoPostal.setBounds(164, 253, 89, 14);
		contentPane.add(lblCodigoPostal);
		
		JLabel lblCiudad = new JLabel(Idiomas.getTraduccionFormato("CIUDAD"));
		lblCiudad.setBounds(164, 230, 89, 14);
		contentPane.add(lblCiudad);
		
		fttdCiudad = new JFormattedTextField();
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
		
		btnEnviar = new JButton(Idiomas.getTraduccionFormato("ENVIAR"));
		btnEnviar.setBounds(10, 278, 385, 23);
		contentPane.add(btnEnviar);
		
		fttdContraseña = new JFormattedTextField();
		fttdContraseña.setBounds(10, 157, 132, 20);
		contentPane.add(fttdContraseña);
		
		fttdUsuario = new JFormattedTextField();
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
		
		dateFechaNac = new JDateChooser();
		dateFechaNac.setBounds(263, 73, 132, 20);
		contentPane.add(dateFechaNac);
	}
	
	/**
	 * 
	 * @param al ActionListener para el btnEnviar
	 */
	public void addEnviarListener(ActionListener al) {
		btnEnviar.addActionListener(al);
	}

	/**
	 * 
	 * @return el usuario
	 */
	public String getUsuario() {
		return fttdUsuario.getText();
	}

	/**
	 * 
	 * @return la contraseña
	 */
	public String getContraseña() {
		return fttdContraseña.getText();
	}

	/**
	 * 
	 * @return el nombre
	 */
	public String getNombre() {
		return fttdNombre.getText();
	}

	/**
	 * 
	 * @return los apellidos
	 */
	public String getApellidos() {
		return fttdApellidos.getText();
	}

	/**
	 * 
	 * @return el email
	 */
	public String getEmail() {
		return fttdEmail.getText();
	}

	/**
	 * 
	 * @return el num tlf
	 */
	public String getNumTlf() {
		return fttdNumTlf.getText();
	}

	/**
	 * 
	 * @return la direccion
	 */
	public String getDireccion() {
		return fttdDireccion1.getText();
	}

	/**
	 * 
	 * @return la ciudad
	 */
	public String getCiudad() {
		return fttdCiudad.getText();
	}
	
	/**
	 * 
	 * @return el codigo postal
	 */
	public String getCodigoPostal() {
		return fttdCodigoPostal.getText();
	}

	/**
	 * 
	 * @return la fecha de nacimiento
	 */
	public String getFechaNac() {
		return dateFechaNac.toString();
	}
}
