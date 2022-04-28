package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.SwingConstants;

/**
 * 
 * @author daw
 *
 */
@SuppressWarnings("serial")
public class ProductoView extends JFrame {

	private JPanel contentPane;
	
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JLabel lblImagen;
	private JLabel lblMasDatos;
	
	private JButton btnA�adir;

	/**
	 * 
	 */
	public ProductoView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 398, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		lblNombre = new JLabel();
		lblNombre.setBounds(128, 11, 97, 14);
		contentPane.add(lblNombre);
		
		/** descripcion **/
		JPanel panelDescripcion = new JPanel(null);
		panelDescripcion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDescripcion.setBounds(128, 36, 247, 121);
		contentPane.add(panelDescripcion);
		
		lblDescripcion = new JLabel();
		lblDescripcion.setBounds(10, 11, 227, 99);
		panelDescripcion.add(lblDescripcion);
		lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
		
		/** imagen **/
		JPanel panelImagen = new JPanel(new GridBagLayout());
		panelImagen.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelImagen.setBounds(10, 11, 109, 146);
		contentPane.add(panelImagen);

		lblImagen = new JLabel("");
		lblImagen.setBounds(10, 60, 46, 14);
		panelImagen.add(lblImagen);
		
		/** mas datos **/
		JPanel panelMasDatos = new JPanel(null);
		panelMasDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMasDatos.setBounds(10, 164, 365, 102);
		contentPane.add(panelMasDatos);
		
		lblMasDatos = new JLabel();
		lblMasDatos.setVerticalAlignment(SwingConstants.TOP);
		lblMasDatos.setBounds(10, 11, 345, 80);
		panelMasDatos.add(lblMasDatos);
		
		btnA�adir = new JButton("A\u00F1adir al carrito");
		btnA�adir.setBounds(116, 277, 150, 23);
		contentPane.add(btnA�adir);
	}
	
	/**
	 * 
	 * @param al ActionListener para el bot�n btnA�adir
	 */
	public void addA�adirListener(ActionListener al) {
		btnA�adir.addActionListener(al);
	}
	
	/**
	 * 
	 * @param nombre el nombre
	 */
	public void setNombre(String nombre) {
		lblNombre.setText(nombre);
	}

	/**
	 * 
	 * @param desc la descripci�n
	 */
	public void setDescripcion(String desc) {
		lblDescripcion.setText(desc);
	}

	/**
	 * 
	 * @param masDatos los mas datos
	 */
	public void setMasDatos(String masDatos) {
		lblMasDatos.setText(masDatos);
	}

	/**
	 * 
	 * @param icon el icono en ImageIcon
	 */
	public void setIcono(ImageIcon icon) {
		lblImagen.setIcon(icon);
	}
}
