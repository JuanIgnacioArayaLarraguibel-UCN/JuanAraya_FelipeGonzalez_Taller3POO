package JuanAraya_FelipeGonzalez_Taller3POO;

public class VisitorAcciones implements VisitorTarea {

	@Override
	public void visitar(Bug tarea) {
		// TODO Auto-generated method stub
	    System.out.println("Bug - Afecta criticidad del proyecto: " + tarea.getDescripcion());
		
	}

	@Override
	public void visitar(Feature tarea) {
		// TODO Auto-generated method stub
		System.out.println("Feature - Afecta a la estimacion del tiempo: " + tarea.getDescripcion());
	}

	@Override
	public void visitar(Documentacion tarea) {
		// TODO Auto-generated method stub
		System.out.println("Documentacion - Mejora la calidad del proyecto: " + tarea.getDescripcion());
	}

}
