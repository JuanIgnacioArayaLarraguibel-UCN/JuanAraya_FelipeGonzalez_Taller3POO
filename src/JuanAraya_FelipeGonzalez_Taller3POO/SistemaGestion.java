package JuanAraya_FelipeGonzalez_Taller3POO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaGestion {
	// supongo que aqui ira todo el desastre?

	private static SistemaGestion instancia;
    private List<Usuario> usuarios;
    private List<Proyecto> proyectos;
    private List<Tarea> todasLasTareas;
    private Usuario usuarioActual;
    private Scanner scanner;

    private EstrategiaPrioridad estrategiaPrioridad;
    
    private SistemaGestion() {
        usuarios = new ArrayList<>();
        proyectos = new ArrayList<>();
        todasLasTareas = new ArrayList<>();
        scanner = new Scanner(System.in);
        cargarDatos();
    }
    
    public static SistemaGestion getInstancia() {
        if (instancia == null) {
            instancia = new SistemaGestion();
        }
        return instancia;
    }
    
    /**
     * cargar los datos para el ordenamiento y las preferencias
     */
    private void cargarDatos() {
        cargarUsuarios();
        cargarProyectos();
        cargarTareas();
    }
    
	// lectores
	public void cargarUsuarios() {
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
