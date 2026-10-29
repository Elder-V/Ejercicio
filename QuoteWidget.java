/** Puramente informativo: NO implementa Interactuable. */
public class QuoteWidget extends WidgetBase {
    private final String cita;
    private final String autor;

    public QuoteWidget(String nombre, int memoriaKB, String cita, String autor) {
        super(nombre, memoriaKB);
        this.cita = cita;
        this.autor = autor;
    }

    @Override
    public void render() {
        System.out.println("\"" + cita + "\" — " + autor);
    }

    @Override
    public String getConfiguracion() {
        return "Autor=" + autor;
    }
}
