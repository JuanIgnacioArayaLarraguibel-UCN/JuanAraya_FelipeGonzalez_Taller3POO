package JuanAraya_FelipeGonzalez_Taller3POO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	private void guardarProyectos() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("proyectos.txt"));
            for (Proyecto proyecto : proyectos) {
                writer.println(proyecto.toString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar proyectos: " + e.getMessage());
        }
    }
    
    private void guardarTareas() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("tareas.txt"));
            for (Tarea tarea : todasLasTareas) {
                writer.println(tarea.toString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar tareas: " + e.getMessage());
        }
    }
    public boolean login() {
        System.out.println("Iniciar Sesión= ");
        System.out.print("Usuario: ");
        String username = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                usuarioActual = usuario;
                System.out.println("Bienvenido, " + username);
                return true;
            }
        }
        
        System.out.println("Usuario o contraseña incorrectos.");
        return false;
    }
    
    public void logout() {
        usuarioActual = null;
        System.out.println("Sesión cerrada.");
    }
    
    public void ejecutar() {
        while (true) {
            if (usuarioActual == null) {
                if (!login()) {
                    continue;
                }
            }
            
            if (usuarioActual.getRol().equals("Administrador")) {
                menuAdministrador();
            } else {
                menuColaborador();
            }
        }
    }
    
    private void menuAdministrador() {
        while (usuarioActual != null && usuarioActual.getRol().equals("Administrador")) {
            System.out.println("Menú Administrador= ");
            System.out.println("1. Ver lista completa de proyectos y tareas");
            System.out.println("2. Agregar proyecto");
            System.out.println("3. Eliminar proyecto");
            System.out.println("4. Agregar tarea");
            System.out.println("5. Eliminar tarea");
            System.out.println("6. Asignar prioridades");
            System.out.println("7. Generar reporte de proyectos");
            System.out.println("8. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine();
            
            switch (opcion) {
                case "1":
                    verProyectosYTareasCompleto();
                    break;
                case "2":
                    agregarProyecto();
                    break;
                case "3":
                    eliminarProyecto();
                    break;
                case "4":
                    agregarTarea();
                    break;
                case "5":
                    eliminarTarea();
                    break;
                case "6":
                    menuPrioridades();
                    break;
                case "7":
                    generarReporte();
                    break;
                case "8":
                    logout();
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

}
