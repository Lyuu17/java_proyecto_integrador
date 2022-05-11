package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.Idiomas;
import app.Producto;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author daw
 *
 */
@SuppressWarnings("serial")
public class CantidadView extends JFrame {

	private JPanel contentPane;
	private int cantidad = 1;
	
	private final JButton btnAñadir;
	private final JFormattedTextField fttdCantidad;
	private final JLabel lblCantidadMaxima;

	/**
	 * 
	 * @param p el producto
	 */
	public CantidadView(Producto p) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 274, 141);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fttdCantidad = new JFormattedTextField();
		fttdCantidad.setBounds(71, 29, 38, 20);
		fttdCantidad.setText(Integer.toString(cantidad));
		fttdCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(fttdCantidad);
		
		btnAñadir = new JButton(Idiomas.getTraduccionFormato("AÑADIR"));
		btnAñadir.setBounds(10, 68, 237, 23);
		contentPane.add(btnAñadir);
		
		JLabel lblCantidad = new JLabel(Idiomas.getTraduccionFormato("CANTIDAD"));
		lblCantidad.setBounds(10, 32, 60, 14);
		contentPane.add(lblCantidad);

		lblCantidadMaxima = new JLabel("/ ");
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

	/**
	 * 
	 * @param al ActionListener para el botón de Añadir
	 */
	public void addAñadirListener(ActionListener al) {
		btnAñadir.addActionListener(al);
	}
	
	/**
	 * 
	 * @return la cantidad en la entrada
	 */
	public String getCantidad() {
		return fttdCantidad.getText();
	}
}
