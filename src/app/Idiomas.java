package app;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.ImageIcon;

/**
 * Cargar diferentes idiomas a traves de ficheros
 * @author daw
 *
 */
public class Idiomas {
	private static Map<String, Map<String, String>> memoria;
	private static Map<String, ImageIcon> iconos;
	
	private static String idiomaActual = "";
	
	/**
	 * inicializar los HashMap y poner los idiomas por defecto con sus iconos
	 */
	public static void init() {
		memoria = new HashMap<String, Map<String, String>>();
		iconos = new HashMap<String, ImageIcon>();
		
		/* idiomas por defecto */
		iconos.put("Espanol", new ImageIcon(Idiomas.class.getResource("/images/spain.png")));
		iconos.put("English", new ImageIcon(Idiomas.class.getResource("/images/uk.png")));

	}
	
	/**
	 * cargarTraducciones del archivo 'idiomas.dat' por defecto
	 * @return true/false
	 * @throws FileNotFoundException
	 */
	public static boolean cargarTraducciones() throws FileNotFoundException {
		return cargarTraducciones("idiomas.dat");
	}
	
	/**
	 * Cargar traducciones con una ruta de archivo pasado por par�metro
	 * @param archivo archivo a leer
	 * @return true/false
	 * @throws FileNotFoundException
	 */
	public static boolean cargarTraducciones(String archivo) throws FileNotFoundException {
		File f = new File(archivo);
		if (!f.exists()) {
			throw new FileNotFoundException();
		}
		
		String idioma = "";
		Scanner scan = new Scanner(f, "utf-8");
		while(scan.hasNextLine()) {
			String linea = scan.nextLine();

			/*
			 * Formato:
			 * [IDIOMA]
			 */
			if (linea.matches("^\\[\\w+\\]$")) {
				/*
				 * Eliminamos los corchetes
				 */
				idioma = linea.replaceAll("^\\[|\\]$", "").trim();

				memoria.put(idioma, new HashMap<String, String>());
				
				continue; //siguiente l�nea
			}
			
			/*
			 * presuponemos que lo siguiente es texto a a�adir
			 * Formato:
			 * ID = TEXTO
			*/
			String[] arr = linea.split("=");
			if (arr.length != 2) continue; //si no cumple

			memoria.get(idioma).put(arr[0].trim(), arr[1].substring(1));
		}
		
		scan.close();
		
		return true;
	}
	
	/**
	 * Obtener el mensaje traducido y formateado con los argumentos pasados por par�metro
	 * @param id id del mensaje
	 * @param args args para darle formato
	 * @return el mensaje formateado
	 */
	public static String getTraduccionFormato(String id, Object... args) {
		String str = getTraduccion(id);
		
		return String.format(str, args);
		
	}
	
	/**
	 * Obtener la traducci�n por el id del mensaje
	 * @param id id del mensaje
	 * @return el mensaje
	 */
	public static String getTraduccion(String id) {
		//todo: excepciones?
		if (!memoria.containsKey(idiomaActual)) return "<<ERROR>>";
		if (!memoria.get(idiomaActual).containsKey(id)) return "<<ERROR>>";
		
		return memoria.get(idiomaActual).get(id);
	}
	
	/**
	 * Obtener la traducci�n por el idioma y el id del mensaje
	 * @param idioma el idioma
	 * @param id el id del mensaje
	 * @return el mensaje del idioma
	 */
	public static String getTraduccion(String idioma, String id) {
		if (!memoria.containsKey(idioma)) return "<<ERROR>>";
		if (!memoria.containsKey(id)) return "<<ERROR>>";
		
		return memoria.get(idioma).get(id);
	}

	/**
	 * Obtener el idioma actual
	 * @return el idiomaActual
	 */
	public static String getIdiomaActual() {
		return idiomaActual;
	}

	/**
	 * @param idiomaActual establecer el idioma actual
	 */
	public static void setIdiomaActual(String idiomaActual) {
		Idiomas.idiomaActual = idiomaActual;
	}
	
	/**
	 * Obtener el icono del idioma
	 * @param idioma
	 * @return el ImageIcon del idioma
	 */
	public static ImageIcon getIdiomaIcono(String idioma) {
		if (!iconos.containsKey(idioma)) return null;
		return iconos.get(idioma);
	}
}
