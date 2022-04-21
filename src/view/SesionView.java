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

@SuppressWarnings("serial")
public class SesionView extends JFrame {

	private JPanel contentPane;
	
	private JFormattedTextField fttdUsuario;
	private JFormattedTextField fttdContrase�a;
	
	private JButton btnAccion;
	private JButton btnRegistrarse;

	/**
	 * Create the frame.
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
		
		fttdContrase�a = new JFormattedTextField();
		fttdContrase�a.setBounds(109, 48, 143, 20);
		contentPane.add(fttdContrase�a);
		
		btnAccion = new JButton(Idiomas.getTraduccionFormato("SESION_INICIARSESION_BOTON"));
		btnAccion.setBounds(109, 79, 143, 23);
		contentPane.add(btnAccion);
		
		JLabel lblUsuario = new JLabel(Idiomas.getTraduccionFormato("USUARIO"));
		lblUsuario.setBounds(10, 20, 89, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrase�a = new JLabel(Idiomas.getTraduccionFormato("CONTRASENHA"));
		lblContrase�a.setBounds(10, 52, 89, 14);
		contentPane.add(lblContrase�a);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(269, 9, 10, 83);
		contentPane.add(separator);
		
		btnRegistrarse = new JButton(Idiomas.getTraduccionFormato("SESION_REGISTRARSE_BOTON"));
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRegistrarse.setBounds(291, 27, 104, 41);
		contentPane.add(btnRegistrarse);
	}
	
	public void addAccionListener(ActionListener al) {
		btnAccion.addActionListener(al);
	}
	
	public void addRegistrarseListener(ActionListener al) {
		btnRegistrarse.addActionListener(al);
	}

	public String getUsuario() {
		return fttdUsuario.getText();
	}

	public String getContrase�a() {
		return fttdContrase�a.getText();
	}
}
