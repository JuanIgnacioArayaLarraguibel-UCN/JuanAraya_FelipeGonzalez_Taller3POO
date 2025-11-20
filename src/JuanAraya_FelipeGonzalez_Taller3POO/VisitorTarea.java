package JuanAraya_FelipeGonzalez_Taller3POO;

public interface VisitorTarea {
    void visitar(Bug tarea);
    void visitar(Feature tarea);
    void visitar(Documentacion tarea);
}
