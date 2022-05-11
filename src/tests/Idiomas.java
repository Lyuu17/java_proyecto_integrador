package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Idiomas {

	@Test
	void setIdiomaActual() {
		app.Idiomas.setIdiomaActual(app.Idiomas.IDIOMA_DEFECTO);
		app.Idiomas.init();
		System.out.println(app.Idiomas.getIdiomaActual());
		if (!app.Idiomas.getIdiomaActual().equals(app.Idiomas.IDIOMA_DEFECTO)) {
			fail("Error en la carga/guardado de idiomas");
		}
			
	}

}
