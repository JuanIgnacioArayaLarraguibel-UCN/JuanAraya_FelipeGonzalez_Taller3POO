package JuanAraya_FelipeGonzalez_Taller3POO;

import java.util.ArrayList;
import java.util.List;

public class PrioridadPorImpacto implements EstrategiaPrioridad{
	/**
	 * 
	 * @param tipo = la prioridad para bug, feature o documentacion
	 * @return
	 */
	private int obtenerValorPrioridad(String tipo) {
		if(tipo.equals("Bug")) {
			return 3;
		}
		if(tipo.equals("Feature")) {
			return 2;
		}
		if(tipo.equals("Documentacion")) {
			return 1;
		}
		return 0;
	}

	@Override
	public List<Tarea> priorizar(List<Tarea> tareas) {
		List<Tarea> tareasOrdenadas = new ArrayList<>(tareas);
		for(int i=0;i<tareasOrdenadas.size()-1;i++) {
			for(int j=0;j<tareasOrdenadas.size()-i-1;j++) {
				int prioridad1 = obtenerValorPrioridad(tareasOrdenadas.get(j).getTipo());
				int prioridad2 = obtenerValorPrioridad(tareasOrdenadas.get(j+1).getTipo());
				
				if(prioridad1<prioridad2) {
					Tarea temp = tareasOrdenadas.get(j);
					tareasOrdenadas.set(j, tareasOrdenadas.get(j+1));
					tareasOrdenadas.set(j+1, temp);
				}
			}
		}
		return tareasOrdenadas;
	}

}
