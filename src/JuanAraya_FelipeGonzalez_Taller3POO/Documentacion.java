package JuanAraya_FelipeGonzalez_Taller3POO;

public class Documentacion extends Tarea {

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
