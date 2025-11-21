package JuanAraya_FelipeGonzalez_Taller3POO;

public class TareaFactory {
	public static Tarea crearTarea(String proyectoId, String id, String tipo, String descripcion, String estado,
			String responsable, String complejidad, String fecha) {
		if(tipo.equals("Bug")) {
			return new Bug(proyectoId, id, descripcion, estado, responsable, complejidad, fecha);
		} else if (tipo.equals("Documento")) {
			return new Documentacion(proyectoId, id, descripcion, estado, responsable, complejidad, fecha);
		} else if(tipo.equals("Feature")) {
			return new Feature(proyectoId, id, descripcion, estado, responsable, complejidad, fecha);	
		}
		return null;
	}
}