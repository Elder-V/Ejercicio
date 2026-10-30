package controller;

import java.util.Optional;
import model.Dashboard;
import model.Renderizable;
import view.DashboardView;

public class DashboardController {
    private final Dashboard modelo;
    private final DashboardView vista;

    public DashboardController(Dashboard modelo, DashboardView vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.addOrdenarListener(e -> ordenarPorMemoria());
        vista.addOriginalListener(e -> ordenOriginal());
        vista.addInteractuarTodosListener(e -> interactuarConTodos());
        vista.addBuscarListener(e -> buscar());

        actualizarVista();
        vista.setEstado(modelo.getWidgets().size() + " widgets cargados.");
    }

    private void actualizarVista() {
        vista.mostrarWidgets(modelo.getWidgets());
    }

    private void ordenarPorMemoria() {
        modelo.ordenarPorMemoria();
        actualizarVista();
        vista.setEstado("Ordenados por memoria (KB), de menor a mayor.");
    }

    private void ordenOriginal() {
        modelo.ordenarPorId();
        actualizarVista();
        vista.setEstado("Orden original restaurado.");
    }

    private void interactuarConTodos() {
        int n = modelo.interactuarConTodos();
        actualizarVista();
        vista.setEstado("Se interactuó con " + n + " widgets interactivos (los estáticos se omiten).");
    }

    private void buscar() {
        String texto = vista.getTextoBusqueda();
        if (texto.isBlank()) { vista.setEstado("Escribe un ID o un nombre."); return; }
        Optional<Renderizable> r = modelo.buscar(texto);
        vista.setEstado(r.map(w -> w + "  →  " + w.getConfiguracion())
                         .orElse("Widget no encontrado: " + texto));
    }
}
