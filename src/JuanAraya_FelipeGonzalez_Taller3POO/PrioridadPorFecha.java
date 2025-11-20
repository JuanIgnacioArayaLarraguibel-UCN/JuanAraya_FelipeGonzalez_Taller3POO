package JuanAraya_FelipeGonzalez_Taller3POO;

import java.util.ArrayList;
import java.util.List;


public class PrioridadPorFecha implements EstrategiaPrioridad{

	@Override
	public List<Tarea> priorizar(List<Tarea> tareas) {
		List<Tarea> tareasOrdenadas= new ArrayList<>(tareas);
		for(int i=0; i<tareasOrdenadas.size()-1; i++) {
			for(int j=0;j<tareasOrdenadas.size()-i-1;j++) {
				String fecha1 = tareasOrdenadas.get(j).getFecha();
				String fecha2 = tareasOrdenadas.get(j+1).getFecha();
				
				if(fecha1.compareTo(fecha2)>0) {
					Tarea temp = tareasOrdenadas.get(j);
                    tareasOrdenadas.set(j, tareasOrdenadas.get(j+1));
                    tareasOrdenadas.set(j+1, temp);
				}
			}
		}
		return tareasOrdenadas;
	}

}
