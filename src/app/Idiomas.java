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
	
	public static void init() {
		memoria = new HashMap<String, Map<String, String>>();
		iconos = new HashMap<String, ImageIcon>();
		iconos.put("Espanol", new ImageIcon(Idiomas.class.getResource("/images/spain.png")));
		iconos.put("English", new ImageIcon(Idiomas.class.getResource("/images/uk.png")));

	}
	
	public static boolean cargarTraducciones() throws FileNotFoundException {
		return cargarTraducciones("idiomas.dat");
	}
	
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
				
				continue; //siguiente línea
			}
			
			/*
			 * presuponemos que lo siguiente es texto a añadir
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
	
	public static String getTraduccionFormato(String id, Object... args) {
		String str = getTraduccion(id);
		
		return String.format(str, args);
		
	}
	
	/**
	 * @return the traduccion
	 */
	public static String getTraduccion(String id) {
		//todo: excepciones?
		if (!memoria.containsKey(idiomaActual)) return "<<ERROR>>";
		if (!memoria.get(idiomaActual).containsKey(id)) return "<<ERROR>>";
		
		return memoria.get(idiomaActual).get(id);
	}
	
	/**
	 * @return the traduccion
	 */
	public static String getTraduccion(String idioma, String id) {
		if (!memoria.containsKey(idioma)) return "<<ERROR>>";
		if (!memoria.containsKey(id)) return "<<ERROR>>";
		
		return memoria.get(idioma).get(id);
	}

	/**
	 * @return the idiomaActual
	 */
	public static String getIdiomaActual() {
		return idiomaActual;
	}

	/**
	 * @param idiomaActual the idiomaActual to set
	 */
	public static void setIdiomaActual(String idiomaActual) {
		Idiomas.idiomaActual = idiomaActual;
	}
	
	public static ImageIcon getIdiomaIcono(String idioma) {
		if (!iconos.containsKey(idioma)) return null;
		return iconos.get(idioma);
	}
}
