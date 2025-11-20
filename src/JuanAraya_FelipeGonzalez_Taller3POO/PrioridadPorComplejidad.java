package JuanAraya_FelipeGonzalez_Taller3POO;

import java.util.List;

public class PrioridadPorComplejidad implements EstrategiaPrioridad{
	/**
	 * 
	 * @param complejidad: valor de cuanta complejidad tiene la tarea
	 * @return
	 */
	private int obtenerValorComplejidad(String complejidad) {
		if(complejidad.equals("Alta")) {
			return 3;
		}
		if(complejidad.equals("Media")) {
			return 2;
		}
		if(complejidad.equals("Baja")) {
			return 1;
		}
		return 0;
	}

	@Override
	public List<Tarea> priorizar(List<Tarea> tareas) {
		// TODO Auto-generated method stub
		return null;
	}

}
