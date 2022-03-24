import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CantidadUI extends JFrame {

	private JPanel contentPane;
	private int cantidad = 1;
	
	/**
	 * Create the frame.
	 */
	public CantidadUI(Producto p) {
		crearInterfaz(p);
	}
	
	public void crearInterfaz(Producto p) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 274, 141);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JFormattedTextField fttdCantidad = new JFormattedTextField();
		fttdCantidad.setBounds(71, 29, 38, 20);
		fttdCantidad.setText(Integer.toString(cantidad));
		fttdCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(fttdCantidad);
		
		JButton btnAñadir = new JButton("A\u00F1adir");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cantidad = Integer.parseInt(fttdCantidad.getText());
					if (cantidad == 0) return;
					
					p.setCantidad(cantidad);
					
					Principal.getProductosCarrito().add(p);
				}
				catch(Exception e1) {
					System.out.println(e1);
				}
				
				dispose();
			}
		});
		btnAñadir.setBounds(10, 68, 237, 23);
		contentPane.add(btnAñadir);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 32, 60, 14);
		contentPane.add(lblCantidad);

		JLabel lblCantidadMaxima = new JLabel("/ ");
		lblCantidadMaxima.setBounds(119, 32, 46, 14);
		lblCantidadMaxima.setText("/ " + p.getCantidadMaxima());
		contentPane.add(lblCantidadMaxima);
		
		JButton btnMas = new JButton("+");
		btnMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cantidad == p.getCantidadMaxima()) return;
				
				cantidad += 1;
				
				fttdCantidad.setText(Integer.toString(cantidad));
			}
		});
		btnMas.setBounds(155, 28, 46, 23);
		contentPane.add(btnMas);
		
		JButton btnMenos = new JButton("-");
		btnMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cantidad == 0) return;

				cantidad -= 1;
					
				fttdCantidad.setText(Integer.toString(cantidad));
			}
		});
		btnMenos.setBounds(201, 28, 46, 23);
		contentPane.add(btnMenos);
	}
}
