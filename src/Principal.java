import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author daw
 *
 */
public class Principal {
	private static ArrayList<Producto> productos = null;
	private static ArrayList<Producto> carritoCompra = null;
	
	private static RegistroUI registroUI = null;
	private static SesionUI sesionUI = null;
	private static TiendaProductosUI tiendaProductosUI = null;
	private static TiendasUI tiendasUI = null;
	
	private static Timer timerProcesar = null;

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
	    
	    productos = new ArrayList<Producto>();
	    carritoCompra = new ArrayList<Producto>();
	    
	    /**
	     * inicializar UI
	     */
		registroUI = new RegistroUI();
		registroUI.setVisible(false);
		
		sesionUI = new SesionUI();
		sesionUI.setVisible(false);
		
		tiendaProductosUI = new TiendaProductosUI();
		tiendaProductosUI.setVisible(false);
		
		tiendasUI = new TiendasUI();
		tiendasUI.crearInterfaz();
		tiendasUI.setVisible(true);
		
		/*
		 * inicializar temporizador
		 */
		TimerTask task = new TimerTask() {
	        public void run() {
	            Principal.procesar();
	        }
	    };
		
		timerProcesar = new Timer("Procesar");
		timerProcesar.schedule(task, 1, 1000);
	}

	public static void procesar() {
		if (tiendaProductosUI != null)
			tiendaProductosUI.procesar();
	}

	/**
	 * @return the producto
	 */
	public static ArrayList<Producto> getProductos() {
		return productos;
	}

	/**
	 * @return the registroUI
	 */
	public static RegistroUI getRegistroUI() {
		return registroUI;
	}

	/**
	 * @return the sesionUI
	 */
	public static SesionUI getSesionUI() {
		return sesionUI;
	}

	/**
	 * @return the tiendaProductosUI
	 */
	public static TiendaProductosUI getTiendaProductosUI() {
		return tiendaProductosUI;
	}
	
	/**
	 * @return the tiendasUI
	 */
	public static TiendasUI getTiendasUI() {
		return tiendasUI;
	}

	/**
	 * @return the carritoCompra
	 */
	public static ArrayList<Producto> getProductosCarrito() {
		return carritoCompra;
	}
}
