package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Dashboard {
    private final List<Renderizable> widgets = new ArrayList<>();

    public void agregar(Renderizable w) { widgets.add(w); }

    public List<Renderizable> getWidgets() {
        return Collections.unmodifiableList(widgets);
    }

    public Optional<Renderizable> buscarPorId(int id) {
        return widgets.stream().filter(w -> w.getId() == id).findFirst();
    }

    public Optional<Renderizable> buscarPorNombre(String nombre) {
        return widgets.stream().filter(w -> w.getNombre().equalsIgnoreCase(nombre)).findFirst();
    }

    public Optional<Renderizable> buscar(String texto) {
        String q = texto.trim();
        try {
            return buscarPorId(Integer.parseInt(q));
        } catch (NumberFormatException e) {
            return buscarPorNombre(q);
        }
    }

    public String interactuar(int id) {
        Optional<Renderizable> w = buscarPorId(id);
        if (w.isEmpty()) return "No existe un widget con ID " + id;
        if (w.get() instanceof Interactivo i) return i.interactuar();
        return "El widget '" + w.get().getNombre() + "' es solo informativo.";
    }

    public int interactuarConTodos() {
        int cantidad = 0;
        for (Renderizable w : widgets) {
            if (w instanceof Interactivo i) {
                i.interactuar();
                cantidad++;
            }
        }
        return cantidad;
    }

    public void ordenarPorMemoria() { Collections.sort(widgets); }

    public void ordenarPorId() { widgets.sort(Comparator.comparingInt(Renderizable::getId)); }
}
