import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Dashboard {
    private final List<Widget> widgets = new ArrayList<>();

    public void agregar(Widget w) { widgets.add(w); }

    public void listar() {
        if (widgets.isEmpty()) { System.out.println("(sin widgets)"); return; }
        for (Widget w : widgets) {
            System.out.println(w);
            System.out.print("     ");
            w.render();
        }
    }

    public Optional<Widget> buscarPorId(int id) {
        return widgets.stream().filter(w -> w.getId() == id).findFirst();
    }

    public Optional<Widget> buscarPorNombre(String nombre) {
        return widgets.stream().filter(w -> w.getNombre().equalsIgnoreCase(nombre)).findFirst();
    }

    public String interactuar(int id) {
        Optional<Widget> w = buscarPorId(id);
        if (w.isEmpty()) return "No existe un widget con ID " + id;
        if (w.get() instanceof Interactuable i) return i.interactuar();
        return "El widget '" + w.get().getNombre() + "' es solo informativo.";
    }

    public void interactuarConTodos() {
        for (Widget w : widgets) {
            if (w instanceof Interactuable i) {
                System.out.println(w.getNombre() + " -> " + i.interactuar());
            }
        }
    }

    public void ordenarPorMemoria() {
        Collections.sort(widgets);
    }
}
