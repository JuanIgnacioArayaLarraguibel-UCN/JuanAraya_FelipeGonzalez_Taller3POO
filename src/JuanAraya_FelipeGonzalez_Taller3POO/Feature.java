package JuanAraya_FelipeGonzalez_Taller3POO;

public class Feature extends Tarea {

	/**
	 * 
	 * @param proyectoId  id del proyecto al que pertenece la tarea
	 * @param id          id unico de la tarea
	 * @param descripcion detalle breve de la funcionalidad a implementar
	 * @param estado      estado actual de la tarea
	 * @param responsable persona asignada para desarrollarla
	 * @param complejidad nivel de dificultad estimado
	 * @param fecha       fecha de creacion
	 */
	public Feature(String proyectoId, String id, String descripcion, String estado, String responsable,
			String complejidad, String fecha) {
		super(proyectoId, id, "Feature", descripcion, estado, responsable, complejidad, fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(VisitorTarea visitor) {
		// TODO Auto-generated method stub
		visitor.visitar(this);

	}

}
