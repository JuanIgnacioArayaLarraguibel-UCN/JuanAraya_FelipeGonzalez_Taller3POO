package JuanAraya_FelipeGonzalez_Taller3POO;

public class Bug extends Tarea {
	/**
	 * 
	 * @param proyectoId  id del proyecto al que pertenece el bug
	 * @param id          id unico de la tarea
	 * @param descripcion explicacion breve del problema
	 * @param estado      estado actual del bug
	 * @param responsable persona asignada para resolverlo
	 * @param complejidad nivel de dificultad estimado
	 * @param fecha       fecha de creacion de la tarea
	 */
	public Bug(String proyectoId, String id, String descripcion, String estado, String responsable, String complejidad,
			String fecha) {
		super(proyectoId, id, "Bug", descripcion, estado, responsable, complejidad, fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(VisitorTarea visitor) {
		// TODO Auto-generated method stub
		visitor.visitar(this);
	}

}
