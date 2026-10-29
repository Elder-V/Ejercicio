import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockWidget extends WidgetBase {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final String zona;

    public ClockWidget(String nombre, int memoriaKB, String zona) {
        super(nombre, memoriaKB);
        this.zona = zona;
    }

    @Override
    public void render() {
        System.out.println(LocalTime.now().format(FMT) + " (" + zona + ")");
    }

    @Override
    public String getConfiguracion() {
        return "Formato=HH:mm:ss, Zona=" + zona;
    }
}
