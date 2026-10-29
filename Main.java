import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static void init(Dashboard d) {
        d.agregar(new WeatherWidget("Clima Guatemala", 120, "Ciudad de Guatemala", 24.5));
        d.agregar(new WeatherWidget("Clima Antigua", 115, "Antigua Guatemala", 21.0));
        d.agregar(new WeatherWidget("Clima Xela", 118, "Quetzaltenango", 15.0));
        d.agregar(new TaskWidget("Tareas Trabajo", 250, "Enviar reporte", "Revisar PR", "Reunión con QA"));
        d.agregar(new TaskWidget("Tareas Personales", 230, "Pagar servicios", "Ir al gimnasio"));
        d.agregar(new AlertWidget("Alerta Servidor", 90, "Servidor de BD sin espacio", AlertWidget.Prioridad.CRITICA));
        d.agregar(new AlertWidget("Alerta Backup", 85, "Backup nocturno retrasado", AlertWidget.Prioridad.MEDIA));
        d.agregar(new ClockWidget("Reloj Local", 60, "GMT-6"));
        d.agregar(new ClockWidget("Reloj Tokio", 62, "GMT+9"));
        d.agregar(new QuoteWidget("Frase del día", 40, "La práctica hace al maestro", "Proverbio"));
        d.agregar(new QuoteWidget("Frase motivacional", 45, "Haz hoy lo que otros no quieren", "Anónimo"));
    }

    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
        init(dashboard);

        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n===== DASHBOARD =====");
            System.out.println("1. Listar widgets");
            System.out.println("2. Buscar widget (ID o nombre)");
            System.out.println("3. Interactuar con un widget (por ID)");
            System.out.println("4. Interactuar con todos los interactivos");
            System.out.println("5. Ordenar por memoria (KB)");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> dashboard.listar();
                case 2 -> {
                    System.out.print("ID o nombre: ");
                    String q = sc.nextLine().trim();
                    Optional<Widget> r;
                    try {
                        r = dashboard.buscarPorId(Integer.parseInt(q));
                    } catch (NumberFormatException e) {
                        r = dashboard.buscarPorNombre(q);
                    }
                    if (r.isPresent()) {
                        System.out.println(r.get());
                        System.out.println("     Config: " + r.get().getConfiguracion());
                    } else {
                        System.out.println("Widget no encontrado.");
                    }
                }
                case 3 -> {
                    System.out.print("ID del widget: ");
                    System.out.println(dashboard.interactuar(leerEntero(sc)));
                }
                case 4 -> dashboard.interactuarConTodos();
                case 5 -> {
                    dashboard.ordenarPorMemoria();
                    System.out.println("Ordenado por memoria (menor a mayor):");
                    dashboard.listar();
                }
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static int leerEntero(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
