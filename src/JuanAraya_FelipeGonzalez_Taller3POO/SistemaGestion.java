package JuanAraya_FelipeGonzalez_Taller3POO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import taller3pooprueba2.Proyecto;
import taller3pooprueba2.Tarea;
import taller3pooprueba2.TareaFactory;

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
	
	private void cargarProyectos() {
        try {
            File archivo = new File("proyectos.txt");
            Scanner lector = new Scanner(archivo);
            
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] partes = linea.split("\\|");
                if (partes.length == 3) {
                    proyectos.add(new Proyecto(partes[0], partes[1], partes[2]));
                }
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al cargar proyectos: " + e.getMessage());
        }
    }
	
	private void cargarTareas() {
        try {
            File archivo = new File("tareas.txt");
            Scanner lector = new Scanner(archivo);
            
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] partes = linea.split("\\|");
                if (partes.length == 8) {
                    Tarea tarea = TareaFactory.crearTarea(
                        partes[0], partes[1], partes[2], partes[3], 
                        partes[4], partes[5], partes[6], partes[7]
                    );
                    
                    if (tarea != null) {
                        todasLasTareas.add(tarea);
                        for (Proyecto proyecto : proyectos) {
                            if (proyecto.getId().equals(partes[0])) {
                                proyecto.agregarTarea(tarea);
                                break;
                            }
                        }
                    }
                }
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al cargar tareas: " + e.getMessage());
        }
    }

}
