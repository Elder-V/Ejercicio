public class AlertWidget extends WidgetBase implements Interactuable {

    public enum Prioridad { BAJA, MEDIA, ALTA, CRITICA }

    private final String mensaje;
    private final Prioridad prioridad;
    private boolean leida = false;

    public AlertWidget(String nombre, int memoriaKB, String mensaje, Prioridad prioridad) {
        super(nombre, memoriaKB);
        this.mensaje = mensaje;
        this.prioridad = prioridad;
    }

    @Override
    public void render() {
        System.out.println("🚨 [" + prioridad + "] " + mensaje + (leida ? "  (leída)" : "  (NUEVA)"));
    }

    @Override
    public String interactuar() {
        if (leida) return "La alerta ya estaba leída.";
        leida = true;
        return "Alerta marcada como leída.";
    }

    @Override
    public String getConfiguracion() {
        return "Prioridad=" + prioridad + ", Leída=" + leida;
    }
}
