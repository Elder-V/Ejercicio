import java.util.ArrayList;
import java.util.List;

public class TaskWidget extends WidgetBase implements Interactuable {

    private static class Tarea {
        final String descripcion;
        boolean completada;
        Tarea(String descripcion) { this.descripcion = descripcion; }
    }

    private final List<Tarea> tareas = new ArrayList<>();

    public TaskWidget(String nombre, int memoriaKB, String... descripciones) {
        super(nombre, memoriaKB);
        for (String d : descripciones) tareas.add(new Tarea(d));
    }

    @Override
    public void render() {
        System.out.println("Pendientes:");
        for (int i = 0; i < tareas.size(); i++) {
            Tarea t = tareas.get(i);
            System.out.println("   " + (i + 1) + ". [" + (t.completada ? "x" : " ") + "] " + t.descripcion);
        }
    }

    @Override
    public String interactuar() {
        for (Tarea t : tareas) {
            if (!t.completada) {
                t.completada = true;
                return "Tarea completada: " + t.descripcion;
            }
        }
        return "No quedan tareas pendientes.";
    }

    @Override
    public String getConfiguracion() {
        long hechas = tareas.stream().filter(t -> t.completada).count();
        return "Tareas=" + tareas.size() + ", Completadas=" + hechas;
    }
}
