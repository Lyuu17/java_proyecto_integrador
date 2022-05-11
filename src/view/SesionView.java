package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Idiomas;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * 
 * @author daw
 *
 */
@SuppressWarnings("serial")
public class SesionView extends JFrame {

	private JPanel contentPane;
	
	private JFormattedTextField fttdUsuario;
	private JFormattedTextField fttdContraseña;
	
	private JButton btnAccion;
	private JButton btnRegistrarse;

	/**
	 * 
	 */
	public SesionView() {
		setTitle("Cuenta");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		fttdUsuario = new JFormattedTextField();
		fttdUsuario.setBounds(109, 17, 143, 20);
		contentPane.add(fttdUsuario);
		
		fttdContraseña = new JFormattedTextField();
		fttdContraseña.setBounds(109, 48, 143, 20);
		contentPane.add(fttdContraseña);
		
		btnAccion = new JButton(Idiomas.getTraduccionFormato("SESION_INICIARSESION_BOTON"));
		btnAccion.setBounds(109, 79, 143, 23);
		contentPane.add(btnAccion);
		
		JLabel lblUsuario = new JLabel(Idiomas.getTraduccionFormato("USUARIO"));
		lblUsuario.setBounds(10, 20, 89, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContraseña = new JLabel(Idiomas.getTraduccionFormato("CONTRASENHA"));
		lblContraseña.setBounds(10, 52, 89, 14);
		contentPane.add(lblContraseña);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(269, 9, 10, 83);
		contentPane.add(separator);
		
		btnRegistrarse = new JButton(Idiomas.getTraduccionFormato("SESION_REGISTRARSE_BOTON"));
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRegistrarse.setBounds(291, 27, 104, 41);
		contentPane.add(btnRegistrarse);
	}
	
	/**
	 * 
	 * @param al ActionListener para el btnAccion
	 */
	public void addAccionListener(ActionListener al) {
		btnAccion.addActionListener(al);
	}
	
	/**
	 * 
	 * @param al ActionListener para el btnRegistrarse
	 */
	public void addRegistrarseListener(ActionListener al) {
		btnRegistrarse.addActionListener(al);
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
}
