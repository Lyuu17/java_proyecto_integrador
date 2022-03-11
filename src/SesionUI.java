import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class SesionUI extends JFrame {

	private JPanel contentPane;

	JCheckBox chckbxRegistro;
	JCheckBox chckbxIniciarSesion;

	/**
	 * Create the frame.
	 */
	public SesionUI() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 184);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JFormattedTextField fttdUsuario = new JFormattedTextField();
		fttdUsuario.setBounds(281, 28, 143, 20);
		contentPane.add(fttdUsuario);
		
		JFormattedTextField fttdContrase�a = new JFormattedTextField();
		fttdContrase�a.setBounds(281, 60, 143, 20);
		contentPane.add(fttdContrase�a);
		
		JButton btnAccion = new JButton("Iniciar Sesi\u00F3n");
		btnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxRegistro.isSelected()) {
					RegistroUI reg = new RegistroUI(fttdUsuario.getText(), fttdContrase�a.getText());
					reg.setVisible(true);
				}
				else {
					String hashContrase�a = Utils.hashContrase�a(fttdContrase�a.getText());
					ResultSet rs = GestorBD.consulta("SELECT * FROM usuarios WHERE usuario = ? AND contrase�a = ?", fttdUsuario.getText(), hashContrase�a);
					
					try {
						if (!rs.next()) {
							JOptionPane.showMessageDialog(contentPane, "Usuario o contrase�a inv�lida", "Inicio de sesi�n", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "OK", "Inicio de sesi�n", JOptionPane.PLAIN_MESSAGE);
						}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
						JOptionPane.showMessageDialog(contentPane, "Ha ocurrido un error.", "Inicio de sesi�n", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAccion.setBounds(149, 117, 113, 23);
		contentPane.add(btnAccion);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 31, 89, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrase�a = new JLabel("Contrase\u00F1a");
		lblContrase�a.setBounds(10, 63, 89, 14);
		contentPane.add(lblContrase�a);
		
		ButtonGroup bg = new ButtonGroup();
		
		chckbxRegistro = new JCheckBox("No tengo cuenta");
		chckbxRegistro.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!chckbxRegistro.isSelected()) {
					chckbxIniciarSesion.setSelected(!chckbxRegistro.isSelected());
					btnAccion.setText("Iniciar Sesi�n");
				}
			}
		});
		chckbxRegistro.setBounds(201, 87, 134, 23);
		bg.add(chckbxRegistro);
		contentPane.add(chckbxRegistro);
		
		chckbxIniciarSesion = new JCheckBox("Tengo cuenta");
		chckbxIniciarSesion.setSelected(true);
		chckbxIniciarSesion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!chckbxIniciarSesion.isSelected()) {
					chckbxRegistro.setSelected(!chckbxIniciarSesion.isSelected());
					btnAccion.setText("Registrarse");
				}
				
			}
		});
		chckbxIniciarSesion.setBounds(84, 87, 115, 23);
		bg.add(chckbxIniciarSesion);
		contentPane.add(chckbxIniciarSesion);
	}
}
