package view;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Idiomas;
import app.Principal;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;

/**
 * 
 * @author daw
 *
 */
@SuppressWarnings("serial")
public class LogoView extends JFrame {

	private JPanel contentPane;

	private JButton btnInvitado;
	
	/**
	 * 
	 */
	public LogoView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setTitle(Principal.PROGRAMA_NOMBRE);
		
		JLabel lblLogo = new JLabel();
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLogo);
		
		lblLogo.setIcon(new ImageIcon(LogoView.class.getResource("/images/logo.png")));
		
		btnInvitado = new JButton(Idiomas.getTraduccionFormato("CONTINUAR_INVITADO"));
		contentPane.add(btnInvitado, BorderLayout.SOUTH);
	}
	
	/**
	 * 
	 * @param al ActionListener para el botón btnInvitado
	 */
	public void addContinuarListener(ActionListener al) {
		btnInvitado.addActionListener(al);
	}

}
