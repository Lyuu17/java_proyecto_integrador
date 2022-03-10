
public class Principal {
	public static void main(String[] args) {
		if (!GestorBD.conectar()) {
			System.out.println("Fallo al conectar a la base de datos");
			
			return;
		}
		
		PrincipalUI principalUI = new PrincipalUI();
		principalUI.setVisible(true);
	}
}
