import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;

@SuppressWarnings("serial")
public class SesionUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SesionUI() {
		setTitle("Cuenta");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JFormattedTextField fttdUsuario = new JFormattedTextField();
		fttdUsuario.setBounds(109, 17, 143, 20);
		contentPane.add(fttdUsuario);
		
		JFormattedTextField fttdContraseña = new JFormattedTextField();
		fttdContraseña.setBounds(109, 48, 143, 20);
		contentPane.add(fttdContraseña);
		
		JButton btnAccion = new JButton(Idiomas.getTraduccionFormato("SESION_INICIARSESION_BOTON"));
		btnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hashContraseña = Utils.hashContraseña(fttdContraseña.getText());
				ResultSet rs = GestorBD.consulta("SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?", fttdUsuario.getText(), hashContraseña);

				try {
					if (!rs.next()) {
						JOptionPane.showMessageDialog(contentPane, Idiomas.getTraduccionFormato("SESION_USUARIO_CONTRA_INVALIDA"), Idiomas.getTraduccionFormato("SESION_INICION_SESION_TITULO"), JOptionPane.ERROR_MESSAGE);
					}
					else {
						Cuenta.setIniciadoSesion(true);
						Cuenta.setUsuario(fttdUsuario.getText());
						
						JOptionPane.showMessageDialog(contentPane, Idiomas.getTraduccionFormato("BIENVENIDO", Cuenta.getUsuario()), Idiomas.getTraduccionFormato("SESION_INICION_SESION_TITULO"), JOptionPane.PLAIN_MESSAGE);

						dispose();
					}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

					JOptionPane.showMessageDialog(contentPane, Idiomas.getTraduccionFormato("SESION_ERROR"), Idiomas.getTraduccionFormato("SESION_INICION_SESION_TITULO"), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
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
		
		JButton btnRegistrarse = new JButton(Idiomas.getTraduccionFormato("SESION_REGISTRARSE_BOTON"));
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal.getRegistroUI().setVisible(true);
			}
		});
		btnRegistrarse.setBounds(291, 27, 104, 41);
		contentPane.add(btnRegistrarse);
	}
}
