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
	
	/**
	 * Método principal que inicializa el gestor de base de datos,
	 * idiomas, el tema FlatLightLaf y crea la interfaz de usuario
	 * @param args sin usar
	 */
	public static void main(String[] args) {
		if (!GestorBD.conectar("localhost", 1521, "proyecto", "proyecto")) {
			System.out.println("Fallo al conectar a la base de datos");
			
			return;
		}
		
		Idiomas.init();

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
