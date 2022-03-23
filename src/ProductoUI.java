import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ProductoUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ProductoUI(Producto p) {
		crearInterfaz(p);
	}
	
	public void crearInterfaz(Producto prod) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 398, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		
		JLabel lblNombre = new JLabel(prod.getNombre());
		lblNombre.setBounds(128, 11, 97, 14);
		contentPane.add(lblNombre);
		
		/** descripcion **/
		JPanel panelDescripcion = new JPanel(null);
		panelDescripcion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDescripcion.setBounds(128, 36, 247, 121);
		contentPane.add(panelDescripcion);
		
		JLabel lblDescripcion = new JLabel(prod.getDescripcion());
		lblDescripcion.setBounds(10, 11, 227, 99);
		panelDescripcion.add(lblDescripcion);
		lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
		
		/** imagen **/
		JPanel panelImagen = new JPanel(new GridBagLayout());
		panelImagen.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelImagen.setBounds(10, 11, 109, 146);
		contentPane.add(panelImagen);

		JLabel lblImagen = new JLabel("");
		lblImagen.setBounds(10, 60, 46, 14);
		lblImagen.setIcon(prod.getIcono());
		panelImagen.add(lblImagen);
		
		
		/** mas datos **/
		JPanel panelMasDatos = new JPanel(null);
		panelMasDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMasDatos.setBounds(10, 164, 365, 102);
		contentPane.add(panelMasDatos);
		
		JLabel lblMasDatos = new JLabel();
		lblMasDatos.setVerticalAlignment(SwingConstants.TOP);
		lblMasDatos.setBounds(10, 11, 345, 80);
		panelMasDatos.add(lblMasDatos);
		
		switch(prod.getCategoria()) {
		case ProductosCategorias.CAT_ROPA:
			ProductoRopa prodRopa = (ProductoRopa)prod;
			lblMasDatos.setText(prodRopa.toString());
			
			break;
		case ProductosCategorias.CAT_COMIDA:
			ProductoComida prodComida = (ProductoComida)prod;
			lblMasDatos.setText(prodComida.toString());
			
			break;
		case ProductosCategorias.CAT_LIBROS:
			ProductoLibro prodLibro = (ProductoLibro)prod;
			lblMasDatos.setText(prodLibro.toString());
			
			break;
		}
	
	}
}
