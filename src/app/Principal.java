package app;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import controller.TiendasController;
import model.TiendasModel;
import view.TiendasView;

/**
 * 
 * @author daw
 *
 */
public class Principal {
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

	    new TiendasController(new TiendasModel(), new TiendasView()).mostrar();
	}
}
