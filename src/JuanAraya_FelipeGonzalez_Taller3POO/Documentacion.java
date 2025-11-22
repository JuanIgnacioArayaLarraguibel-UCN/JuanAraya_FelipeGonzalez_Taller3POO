package JuanAraya_FelipeGonzalez_Taller3POO;

public class Documentacion extends Tarea {

	/**
	 * 
	 * @param proyectoId  id del proyecto al que pertenece la tarea
	 * @param id          id nico de la tarea
	 * @param descripcion descripcion breve del contenido a documentar
	 * @param estado      estado actual de la tarea
	 * @param responsable persona encargada de realizar la documentacion
	 * @param complejidad nivel de dificultad estimado
	 * @param fecha       fecha de creacion de la tarea
	 */
	public Documentacion(String proyectoId, String id, String descripcion, String estado, String responsable,
			String complejidad, String fecha) {
		super(proyectoId, id, "Documentacion", descripcion, estado, responsable, complejidad, fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(VisitorTarea visitor) {
		// TODO Auto-generated method stub
		visitor.visitar(this);

	}

}
