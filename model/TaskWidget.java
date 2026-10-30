package model;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TaskWidget extends WidgetBase implements Interactivo {

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

    private String tareasHtml() {
        StringBuilder sb = new StringBuilder("<html>");
        for (Tarea t : tareas) {
            sb.append(t.completada ? "&#9745; <strike>" + t.descripcion + "</strike>"
                                   : "&#9744; " + t.descripcion).append("<br>");
        }
        return sb.append("</html>").toString();
    }

    @Override
    public void render() {
        System.out.println("Pendientes:");
        for (int i = 0; i < tareas.size(); i++) {
            Tarea t = tareas.get(i);
            System.out.println("  " + (i + 1) + ". [" + (t.completada ? "x" : " ") + "] " + t.descripcion);
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
    public JComponent obtenerVista() {
        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.setBorder(BorderFactory.createTitledBorder(getNombre()));

        JLabel lblTareas = new JLabel(tareasHtml());
        JButton btnCompletar = new JButton("Completar siguiente");
        btnCompletar.addActionListener(e -> {
            interactuar();
            lblTareas.setText(tareasHtml());
        });

        panel.add(lblTareas, BorderLayout.CENTER);
        panel.add(btnCompletar, BorderLayout.SOUTH);
        return panel;
    }

    @Override
    public String getConfiguracion() {
        long hechas = tareas.stream().filter(t -> t.completada).count();
        return "Tareas=" + tareas.size() + ", Completadas=" + hechas;
    }
}
