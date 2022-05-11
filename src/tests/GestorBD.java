package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class GestorBD {

	@Test
	void conectar() {
		if (!app.GestorBD.conectar("localhost", 1521, "proyecto", "proyecto"))
			fail("Error al conectar la base de datos");
	}
	
	@Test
	void consulta() throws SQLException {
		ResultSet rs = app.GestorBD.consulta("SELECT table_name FROM user_tables");
		
		
		String[] tablas = {"HISTORIAL_COMPRAS",
				"USUARIOS",
				"DATOS_USUARIOS",
				"TIENDAS",
				"PRODUCTOS",
				"PRODUCTOS_TIENDA",
				"PRODUCTO_ROPA",
				"PRODUCTO_COMIDA",
				"PRODUCTO_LIBROS",
				"EDITORIALES",
				"MARCAS",
				"AUTORES",
				"FABRICANTES"};
		List<String> listaTablas = Arrays.asList(tablas);
		
		while(rs.next()) {
			if (!listaTablas.contains(rs.getString(1))) {
				fail("Faltan tablas en la base de datos");
			}
		}
	}

}
