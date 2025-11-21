package JuanAraya_FelipeGonzalez_Taller3POO;

public abstract class Tarea {
	private String proyectoId;
	private String id;
	private String tipo;
	private String descripcion;
	private String estado;
	private String responsable;
	private String complejidad;
	private String fecha;
	/**
	 * 
	 * @param proyectoId : id del prouecto al que pertenece
	 * @param id : el id de la tarea unica
	 * @param tipo : el tipo de la tarea
	 * @param descripcion : descripcion de la tarea
	 * @param estado : estado de la tarea
	 * @param responsable : quien se supone que hace la tarea
	 * @param complejidad : dificultad de la tarea
	 * @param fecha : la fecha de creacion de la tarea
	 */
	public Tarea(String proyectoId, String id, String tipo, String descripcion, String estado, String responsable,
			String complejidad, String fecha) {
		this.proyectoId = proyectoId;
		this.id = id;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.responsable = responsable;
		this.complejidad = complejidad;
		this.fecha = fecha;
	}
	
	public abstract void accept(VisitorTarea visitor);

	public String getProyectoId() {
		return proyectoId;
	}

	public String getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public String getResponsable() {
		return responsable;
	}

	public String getComplejidad() {
		return complejidad;
	}

	public String getFecha() {
		return fecha;
	}	
	
	public void setEstado(String estado) { this.estado = estado; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
        
    @Override
    public String toString() {
        return proyectoId + "|" + id + "|" + tipo + "|" + descripcion + "|" + 
               estado + "|" + responsable + "|" + complejidad + "|" + fecha;
    }
}
