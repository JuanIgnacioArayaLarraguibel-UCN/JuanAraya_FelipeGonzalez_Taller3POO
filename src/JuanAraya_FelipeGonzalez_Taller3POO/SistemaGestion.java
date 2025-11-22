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
    
	/**
	 * cargarUsuarios 
	 * se encarga de la lectura del txt "usuarios"
	 * 
	 * esta lectura es dividida mas tarde en 3 partes
	 * en caso de no haber txt a leer saltara: "Error al cargar usuarios: " + nombre del txt que trata de leer.
	 */
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
	
	/**
	 * cargarProyectos 
	 * se encarga de la lectura del txt "proyectos"
	 * 
	 * esta lectura es dividida mas tarde en 3 partes
	 * en caso de no haber txt a leer saltara: "Error al cargar proyectos: " + nombre del txt que trata de leer.
	 */
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
	
	/**
	 * cargarTareas 
	 * se encarga de la lectura del txt "tareas"
	 * 
	 * esta lectura es dividida mas tarde en 8 partes
	 * en caso de no haber txt a leer saltara: "Error al cargar tareas: " + nombre del txt que trata de leer.
	 */
	private void cargarTareas() {
		try {
			File archivo = new File("tareas.txt");
			Scanner lector = new Scanner(archivo);

			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				String[] partes = linea.split("\\|");
				if (partes.length == 8) {
					Tarea tarea = TareaFactory.crearTarea(partes[0], partes[1], partes[2], partes[3], partes[4],
							partes[5], partes[6], partes[7]);

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
        System.out.println("Iniciar Sesion= ");
        System.out.print("Usuario: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                usuarioActual = usuario;
                System.out.println("Bienvenido, " + username);
                return true;
            }
        }
        
        System.out.println("Usuario o password incorrectos.");
        return false;
    }
    
    public void logout() {
        usuarioActual = null;
        System.out.println("Sesion cerrada.");
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
    /**
     * el menuAdministrador. 
     * contiene 8 opciones 
     * Opciones disponibles:
     * 1 Ver lista completa de proyectos y tareas
     * 2 Agregar proyecto
     * 3 Eliminar proyecto
     * 4 Agregar tarea
     * 5 Eliminar tarea
     * 6 Asignar prioridades
     * 7 Generar reporte de proyectos
     * 8 Cerrar sesión
     */
    private void menuAdministrador() {
        while (usuarioActual != null && usuarioActual.getRol().equals("Administrador")) {
            System.out.println("Menu Administrador= ");
            System.out.println("1. Ver lista completa de proyectos y tareas");
            System.out.println("2. Agregar proyecto");
            System.out.println("3. Eliminar proyecto");
            System.out.println("4. Agregar tarea");
            System.out.println("5. Eliminar tarea");
            System.out.println("6. Asignar prioridades");
            System.out.println("7. Generar reporte de proyectos");
            System.out.println("8. Cerrar sesion");
            System.out.print("Seleccione una opcion: ");
            
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
                    System.out.println("Opcion invalida.");
            }
        }
    }
    
	private void verProyectosYTareasCompleto() {
		System.out.println("Lista de Tareas y Proyectos");
		for (Proyecto proyecto : proyectos) {
			System.out.println("Proyecto: " + proyecto.getNombre() + " (ID: " + proyecto.getId() + ")");
			System.out.println("Responsable: " + proyecto.getResponsable());
			System.out.println("Tareas:");

			if (proyecto.getTareas().isEmpty()) {
				System.out.println("  No hay tareas asignadas.");
			} else {
				for (Tarea tarea : proyecto.getTareas()) {
					System.out.println("  - " + tarea.getId() + ": " + tarea.getDescripcion() + " [" + tarea.getTipo() + ", " + tarea.getEstado() + "]");
				}
			}
		}
	}
    
    private void agregarProyecto() {
        System.out.println("Agregar Proyecto");
        System.out.print("ID del proyecto: ");
        String id = scanner.nextLine();
        System.out.print("Nombre del proyecto: ");
        String nombre = scanner.nextLine();
        System.out.print("Responsable: ");
        String responsable = scanner.nextLine();
        
        // Verificar si el ID ya existe
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId().equals(id)) {
                System.out.println("Error: Ya existe un proyecto con ese ID.");
                return;
            }
        }
        
        proyectos.add(new Proyecto(id, nombre, responsable));
        guardarProyectos();
        System.out.println("Proyecto agregado exitosamente.");
    }
    /**
     * agrega una nueva tarea según los datos ingresados por el usuario
     * solicita el id del proyecto y verifica que exista
     * también pide el id de la tarea y comprueba que no esté repetido
     * permite seleccionar tipo, estado y complejidad desde consola
     * asigna responsable y fecha actual a la tarea
     */
    private void eliminarProyecto() {
        System.out.println("Eliminar Proyecto");
        System.out.print("ID del proyecto a eliminar: ");
        String id = scanner.nextLine();
        
        Proyecto proyectoAEliminar = null;
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId().equals(id)) {
                proyectoAEliminar = proyecto;
                break;
            }
        }
        
        if (proyectoAEliminar != null) {
          
            List<Tarea> tareasAEliminar = new ArrayList<>();
            for (Tarea tarea : todasLasTareas) {
                if (tarea.getProyectoId().equals(id)) {
                    tareasAEliminar.add(tarea);
                }
            }
            todasLasTareas.removeAll(tareasAEliminar);
            
            proyectos.remove(proyectoAEliminar);
            guardarProyectos();
            guardarTareas();
            System.out.println("Proyecto y sus tareas eliminados exitosamente.");
        } else {
            System.out.println("No se encontro el proyecto con ID: " + id);
        }
    }
    
    private void agregarTarea() {
        System.out.println("Agregar Tarea");
        System.out.print("ID del proyecto: ");
        String proyectoId = scanner.nextLine();
        
        Proyecto proyectoSeleccionado = null;
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId().equals(proyectoId)) {
                proyectoSeleccionado = proyecto;
                break;
            }
        }
        
        if (proyectoSeleccionado == null) {
            System.out.println("Error: No existe el proyecto con ID: " + proyectoId);
            return;
        }
        
        System.out.print("ID de la tarea: ");
        String tareaId = scanner.nextLine();
        
        for (Tarea tarea : todasLasTareas) {
            if (tarea.getId().equals(tareaId)) {
                System.out.println("Error: Ya existe una tarea con ese ID.");
                return;
            }
        }
        
        System.out.println("Tipo de tarea:");
        System.out.println("1. Bug");
        System.out.println("2. Feature");
        System.out.println("3. Documentacion");
        System.out.print("Seleccione: ");
        String tipoOpcion = scanner.nextLine();
        
        String tipo = "";
        switch (tipoOpcion) {
            case "1": tipo = "Bug"; break;
            case "2": tipo = "Feature"; break;
            case "3": tipo = "Documentacion"; break;
            default:
                System.out.println("Opcion invalida.");
                return;
        }
        
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();
        
        System.out.println("Estado inicial:");
        System.out.println("1. Pendiente");
        System.out.println("2. En progreso");
        System.out.print("Seleccione: ");
        String estadoOpcion = scanner.nextLine();
        
        String estado = estadoOpcion.equals("2") ? "En progreso" : "Pendiente";
        
        System.out.print("Responsable: ");
        String responsable = scanner.nextLine();
        
        System.out.println("Complejidad:");
        System.out.println("1. Baja");
        System.out.println("2. Media");
        System.out.println("3. Alta");
        System.out.print("Seleccione: ");
        String compOpcion = scanner.nextLine();
        
        String complejidad = "Media";
        switch (compOpcion) {
            case "1": complejidad = "Baja"; break;
            case "3": complejidad = "Alta"; break;
        }
        
        String fecha = obtenerFechaActual();
        
        
        if (!verificarDisponibilidadResponsable(responsable, fecha)) {
            System.out.println("Error: El responsable ya tiene una tarea asignada para esta fecha.");
            return;
        }
        
        Tarea nuevaTarea = TareaFactory.crearTarea(
            proyectoId, tareaId, tipo, descripcion, estado, responsable, complejidad, fecha
        );
        
        if (nuevaTarea != null) {
            todasLasTareas.add(nuevaTarea);
            proyectoSeleccionado.agregarTarea(nuevaTarea);
            guardarTareas();
            System.out.println("Tarea agregada exitosamente.");
        }
    }
    
    private boolean verificarDisponibilidadResponsable(String responsable, String fecha) {
        for (Tarea tarea : todasLasTareas) {
            if (tarea.getResponsable().equals(responsable) && tarea.getFecha().equals(fecha)) {
                return false;
            }
        }
        return true;
    }
    
    private void eliminarTarea() {
        System.out.println("Eliminar Tarea");
        System.out.print("ID de la tarea a eliminar: ");
        String tareaId = scanner.nextLine();
        
        Tarea tareaAEliminar = null;
        for (Tarea tarea : todasLasTareas) {
            if (tarea.getId().equals(tareaId)) {
                tareaAEliminar = tarea;
                break;
            }
        }
        
        if (tareaAEliminar != null) {
            // Eliminar tarea del proyecto
            for (Proyecto proyecto : proyectos) {
                if (proyecto.getId().equals(tareaAEliminar.getProyectoId())) {
                    proyecto.eliminarTarea(tareaId);
                    break;
                }
            }
            
            todasLasTareas.remove(tareaAEliminar);
            guardarTareas();
            System.out.println("Tarea eliminada exitosamente.");
        } else {
            System.out.println("No se encontro la tarea con ID: " + tareaId);
        }
    }
    
    private void menuPrioridades() {
        System.out.println("Asignar Prioridades");
        System.out.println("1. Priorizar por fecha de creacion");
        System.out.println("2. Priorizar por impacto");
        System.out.println("3. Priorizar por complejidad");
        System.out.print("Seleccione estrategia: ");
        
        String opcion = scanner.nextLine();
        
        switch (opcion) {
            case "1":
                estrategiaPrioridad = new PrioridadPorFecha();
                break;
            case "2":
                estrategiaPrioridad = new PrioridadPorImpacto();
                break;
            case "3":
                estrategiaPrioridad = new PrioridadPorComplejidad();
                break;
            default:
                System.out.println("Opcion invalida.");
                return;
        }
        
        System.out.print("ID del proyecto (o 'all' para todos): ");
        String proyectoId = scanner.nextLine();
        
        List<Tarea> tareasAPriorizar = new ArrayList<>();
        
        if (proyectoId.equals("all")) {
            tareasAPriorizar = new ArrayList<>(todasLasTareas);
        } else {
            for (Tarea tarea : todasLasTareas) {
                if (tarea.getProyectoId().equals(proyectoId)) {
                    tareasAPriorizar.add(tarea);
                }
            }
        }
        
        if (tareasAPriorizar.isEmpty()) {
            System.out.println("No hay tareas para priorizar.");
            return;
        }
        
        List<Tarea> tareasPriorizadas = estrategiaPrioridad.priorizar(tareasAPriorizar);
        
        System.out.println("Tareas Priorizadas");
        for (Tarea tarea : tareasPriorizadas) {
            System.out.println("- " + tarea.getId() + ": " + tarea.getDescripcion() + 
                             " [" + tarea.getTipo() + ", " + tarea.getEstado() + 
                             ", " + tarea.getComplejidad() + ", " + tarea.getFecha() + "]");
        }
    }
    
    private void generarReporte() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("reporte.txt"));
            
            writer.println("Reporte de Proyecto - " + obtenerFechaActual());
            writer.println();
            
            for (Proyecto proyecto : proyectos) {
                writer.println("Proyecto: " + proyecto.getNombre() + " (ID: " + proyecto.getId() + ")");
                writer.println("Responsable: " + proyecto.getResponsable());
                writer.println("Tareas asignadas: " + proyecto.getTareas().size());
                writer.println();
                
                if (!proyecto.getTareas().isEmpty()) {
                    writer.println("Detalle de tarea:");
                    for (Tarea tarea : proyecto.getTareas()) {
                        writer.println("  - " + tarea.getId() + ": " + tarea.getDescripcion());
                        writer.println("    Tipo: " + tarea.getTipo() + " | Estado: " + tarea.getEstado());
                        writer.println("    Responsable: " + tarea.getResponsable() + " | Complejidad: " + tarea.getComplejidad());
                        writer.println("    Fecha: " + tarea.getFecha());
                        writer.println();
                    }
                }
                writer.println("---");
                writer.println();
            }
            
            writer.close();
            System.out.println("Reporte generado exitosamente en 'reporte.txt'");
        } catch (IOException e) {
            System.out.println("Error al generar reporte: " + e.getMessage());
        }
    }
    
    private String obtenerFechaActual() {
        return "2025-11-21"; 
    }
    
    private void menuColaborador() {
        while (usuarioActual != null && usuarioActual.getRol().equals("Colaborador")) {
            System.out.println("Menu colaborador");
            System.out.println("1. Ver proyectos disponibles");
            System.out.println("2. Ver mis tareas asignadas");
            System.out.println("3. Actualizar estado de una tarea");
            System.out.println("4. Aplicar Visitor sobre tareas");
            System.out.println("5. Cerrar sesion");
            System.out.print("Seleccione una opcion: ");
            
            String opcion = scanner.nextLine();
            
            switch (opcion) {
                case "1":
                    verProyectosDisponibles();
                    break;
                case "2":
                    verMisTareas();
                    break;
                case "3":
                    actualizarEstadoTarea();
                    break;
                case "4":
                    aplicarVisitorTareas();
                    break;
                case "5":
                    logout();
                    return;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }
    
    
    private void verProyectosDisponibles() {
        System.out.println("Proyectos");
        for (Proyecto proyecto : proyectos) {
            System.out.println("- " + proyecto.getId() + ": " + proyecto.getNombre());
            System.out.println("  Responsable: " + proyecto.getResponsable());
            System.out.println("  Tareas totales: " + proyecto.getTareas().size());
        }
    }
    
    private void verMisTareas() {
        System.out.println("Tareas Asignadas");
        String miUsuario = usuarioActual.getUsername();
        boolean tieneTareas = false;
        
        for (Proyecto proyecto : proyectos) {
            for (Tarea tarea : proyecto.getTareas()) {
                if (tarea.getResponsable().equals(miUsuario)) {
                    if (!tieneTareas) {
                        tieneTareas = true;
                    }
                    System.out.println("- Proyecto: " + proyecto.getNombre());
                    System.out.println("  Tarea: " + tarea.getId() + " - " + tarea.getDescripcion());
                    System.out.println("  Tipo: " + tarea.getTipo() + " | Estado: " + tarea.getEstado());
                    System.out.println("  Complejidad: " + tarea.getComplejidad() + " | Fecha: " + tarea.getFecha());
                    System.out.println();
                }
            }
        }
        
        if (!tieneTareas) {
            System.out.println("No tienes tareas asignadas.");
        }
    }
    
    private void actualizarEstadoTarea() {
        System.out.println("Actualizar Estado de Tarea");
        System.out.print("ID de la tarea: ");
        String tareaId = scanner.nextLine();
        
        Tarea tareaActualizar = null;
        for (Tarea tarea : todasLasTareas) {
            if (tarea.getId().equals(tareaId) && tarea.getResponsable().equals(usuarioActual.getUsername())) {
                tareaActualizar = tarea;
                break;
            }
        }
        
        if (tareaActualizar == null) {
            System.out.println("No se encontro la tarea o no eres el responsable.");
            return;
        }
        
        System.out.println("Tarea actual: " + tareaActualizar.getDescripcion());
        System.out.println("Estado actual: " + tareaActualizar.getEstado());
        System.out.println("\nSeleccione nuevo estado:");
        System.out.println("1. Pendiente");
        System.out.println("2. En progreso");
        System.out.println("3. Completada");
        System.out.print("Seleccione: ");
        String estadoOpcion = scanner.nextLine();
        
        String nuevoEstado = "";
        switch (estadoOpcion) {
            case "1": nuevoEstado = "Pendiente"; break;
            case "2": nuevoEstado = "En progreso"; break;
            case "3": nuevoEstado = "Completada"; break;
            default:
                System.out.println("Opcion invalida.");
                return;
        }
        
        tareaActualizar.setEstado(nuevoEstado);
        guardarTareas();
        System.out.println("Estado actualizado exitosamente a: " + nuevoEstado);
    }
    
    private void aplicarVisitorTareas() {
        System.out.println("Aplicar Visitor a tareas");
        String miUsuario = usuarioActual.getUsername();
        VisitorAcciones visitor = new VisitorAcciones();
        boolean tieneTareas = false;
        
        for (Tarea tarea : todasLasTareas) {
            if (tarea.getResponsable().equals(miUsuario)) {
                if (!tieneTareas) {
                    tieneTareas = true;
                }
                tarea.accept(visitor);
            }
        }
        
        if (!tieneTareas) {
            System.out.println("No tienes tareas asignadas para aplicar acciones.");
        }
    }

}
