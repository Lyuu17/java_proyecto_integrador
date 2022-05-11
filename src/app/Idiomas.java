package app;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public final static String IDIOMA_DEFECTO = "Espanol";
	
	private static String idiomaActual = IDIOMA_DEFECTO;
	
	/**
	 * inicializar los HashMap y poner los idiomas por defecto con sus iconos
	 */
	public static void init() {
		memoria = new HashMap<String, Map<String, String>>();
		iconos = new HashMap<String, ImageIcon>();
		
		/* idiomas por defecto */
		iconos.put("Espanol", new ImageIcon(Idiomas.class.getResource("/images/spain.png")));
		iconos.put("English", new ImageIcon(Idiomas.class.getResource("/images/uk.png")));

		idiomaActual = getIdiomaArchivo();
	}
	
	private static String getIdiomaArchivo() {
		String idioma = IDIOMA_DEFECTO;
		
		File f = new File("idioma_actual.txt");
		
		Scanner sc;
		try {
			sc = new Scanner(f);
			
			if (sc.hasNextLine())
				idioma = sc.nextLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return idioma;
	}
	
	private static boolean guardarIdiomaActualArchivo(String archivo) {
		File f = new File("idioma_actual.txt");
		
		try {
			BufferedWriter bfw = new BufferedWriter(new FileWriter(f));
			
			bfw.write(idiomaActual);
			
			bfw.close();
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
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
	 * Cargar traducciones con una ruta de archivo pasado por parámetro
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
	
	/**
	 * Obtener el mensaje traducido y formateado con los argumentos pasados por parámetro
	 * @param id id del mensaje
	 * @param args args para darle formato
	 * @return el mensaje formateado
	 */
	public static String getTraduccionFormato(String id, Object... args) {
		String str = getTraduccion(id);
		
		return String.format(str, args);
		
	}
	
	/**
	 * Obtener la traducción por el id del mensaje
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
	 * Obtener la traducción por el idioma y el id del mensaje
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
		
		guardarIdiomaActualArchivo(idiomaActual);
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
