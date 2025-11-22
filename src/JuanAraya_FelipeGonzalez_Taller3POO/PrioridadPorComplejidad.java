package JuanAraya_FelipeGonzalez_Taller3POO;

import java.util.ArrayList;
import java.util.List;

public class PrioridadPorComplejidad implements EstrategiaPrioridad {
	/**
	 * 
	 * @param complejidad: valor de cuanta complejidad tiene la tarea
	 * @return
	 */
	private int obtenerValorComplejidad(String complejidad) {
		if (complejidad.equals("Alta")) {
			return 3;
		}
		if (complejidad.equals("Media")) {
			return 2;
		}
		if (complejidad.equals("Baja")) {
			return 1;
		}
		return 0;
	}

	@Override
	public List<Tarea> priorizar(List<Tarea> tareas) {
		List<Tarea> tareasOrdenadas = new ArrayList<>(tareas);
		for (int i = 0; i < tareasOrdenadas.size() - 1; i++) {
			for (int j = 0; j < tareasOrdenadas.size() - i - 1; j++) {
				int comp1 = obtenerValorComplejidad(tareasOrdenadas.get(j).getComplejidad());
				int comp2 = obtenerValorComplejidad(tareasOrdenadas.get(j + 1).getComplejidad());

				if (comp1 < comp2) {
					Tarea temp = tareasOrdenadas.get(j);
					tareasOrdenadas.set(j, tareasOrdenadas.get(j + 1));
					tareasOrdenadas.set(j + 1, temp);
				}
			}
		}
		return tareasOrdenadas;
	}

}
