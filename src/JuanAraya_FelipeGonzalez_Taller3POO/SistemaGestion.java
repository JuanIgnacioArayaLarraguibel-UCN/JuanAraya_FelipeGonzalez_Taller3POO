package JuanAraya_FelipeGonzalez_Taller3POO;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class SistemaGestion {
	// supongo que aqui ira todo el desastre?

	private List<Usuario> usuarios;

	// lectores
	public void lectorUsuario() {
		try {
			File archivo = new File("usuarios.txt");
			Scanner lectorScanner = new Scanner(archivo);

			while (lectorScanner.hasNextLine()) {
				String linea = lectorScanner.nextLine();
				String[] parte = linea.split("\\|");
				if (parte.length == 3) {
					usuarios.add(new Usuario(parte[1], parte[2], parte[3]));
				}
			}
			lectorScanner.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al cargar usuarios: " + e.getMessage());
		}
	}

}
