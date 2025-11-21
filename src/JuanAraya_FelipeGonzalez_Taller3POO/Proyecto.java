package JuanAraya_FelipeGonzalez_Taller3POO;

import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    private String id;
    private String nombre;
    private String responsable;
    private List<Tarea> tareas;
    /**
     * 
     * @param id : id unico del proyecto
     * @param nombre : el nombre del proyecto
     * @param responsable : responsable del proyecto
     * 
     */
	public Proyecto(String id, String nombre, String responsable) {
		this.id = id;
		this.nombre = nombre;
		this.responsable = responsable;
        this.tareas = new ArrayList<>();
	}
	public String getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getResponsable() {
		return responsable;
	}
	public List<Tarea> getTareas() {
		return tareas;
	}
	/*
	 * tarea agregada
	 */
	public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }
	public void eliminarTarea(String tareaId) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId().equals(tareaId)) {
                tareas.remove(i);
                break;
            }
        }
    }
    public Tarea buscarTarea(String tareaId) {
        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(tareaId)) {
                return tarea;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return id + "|" + nombre + "|" + responsable;
    }
}
