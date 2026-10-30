package app;

import controller.DashboardController;
import javax.swing.SwingUtilities;
import model.*;
import model.AlertWidget.Prioridad;
import view.DashboardView;

public class Main {

    private static DashboardController controller;

    public static void init(Dashboard d) {
        d.agregar(new WeatherWidget("Clima Guatemala", 120, "Ciudad de Guatemala", 24.5));
        d.agregar(new WeatherWidget("Clima Antigua", 115, "Antigua Guatemala", 21.0));
        d.agregar(new WeatherWidget("Clima Xela", 118, "Quetzaltenango", 15.0));
        d.agregar(new TaskWidget("Tareas Trabajo", 250, "Enviar reporte", "Revisar PR", "Reunión con QA"));
        d.agregar(new TaskWidget("Tareas Personales", 230, "Pagar servicios", "Ir al gimnasio"));
        d.agregar(new AlertWidget("Alerta Servidor", 90, "Servidor de BD sin espacio", Prioridad.CRITICA));
        d.agregar(new AlertWidget("Alerta Backup", 85, "Backup nocturno retrasado", Prioridad.MEDIA));
        d.agregar(new ClockWidget("Reloj Local", 60, "GMT-6"));
        d.agregar(new ClockWidget("Reloj Tokio", 62, "GMT+9"));
        d.agregar(new QuoteWidget("Frase del día", 40, "La práctica hace al maestro", "Proverbio"));
        d.agregar(new QuoteWidget("Frase motivacional", 45, "Haz hoy lo que otros no quieren", "Anónimo"));
    }

    public static void main(String[] args) {
        Dashboard modelo = new Dashboard();
        init(modelo);
        SwingUtilities.invokeLater(() -> {
            DashboardView vista = new DashboardView();
            controller = new DashboardController(modelo, vista);
            vista.setVisible(true);
        });
    }
}
