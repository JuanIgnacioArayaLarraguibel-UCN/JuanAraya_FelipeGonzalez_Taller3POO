package JuanAraya_FelipeGonzalez_Taller3POO;
//Integrantes: Juan Araya RUT: 21.566.260-8 ; Felipe Gonzalez RUT: 21.776.516-1
//Carrera: Ingenieria en Tecnologias de Informacion

/**
 * Creacion de la clase Main
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Sistema de Gestion de Proyectos");
        System.out.println("TaskForge Ltda. - 2035");
        System.out.println("========================================");
        
        SistemaGestion sistema = SistemaGestion.getInstancia();
        sistema.ejecutar();
    }
}
