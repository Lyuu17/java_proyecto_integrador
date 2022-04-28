package app;
import java.io.FileNotFoundException;
import com.formdev.flatlaf.FlatLightLaf;

import controller.LogoController;
import view.LogoView;

/**
 * 
 * @author daw
 *
 */
public class Principal {
	public static final String PROGRAMA_NOMBRE = "Pickify";
	
	public static void main(String[] args) {
		if (!GestorBD.conectar()) {
			System.out.println("Fallo al conectar a la base de datos");
			
			return;
		}
		
		Idiomas.init();
	    Idiomas.setIdiomaActual("Espanol");

	    try {
	    	Idiomas.cargarTraducciones(); //idiomas.dat
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    /* look'n'feel */
	    FlatLightLaf.setup();

	    new LogoController(null, new LogoView()).mostrar();
	}
}
