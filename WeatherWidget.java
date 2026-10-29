public class WeatherWidget extends WidgetBase implements Interactuable {
    private final String ciudad;
    private final double temperaturaC;
    private boolean enCelsius = true;

    public WeatherWidget(String nombre, int memoriaKB, String ciudad, double temperaturaC) {
        super(nombre, memoriaKB);
        this.ciudad = ciudad;
        this.temperaturaC = temperaturaC;
    }

    private String temperaturaTexto() {
        return enCelsius
                ? String.format("%.1f °C", temperaturaC)
                : String.format("%.1f °F", temperaturaC * 9 / 5 + 32);
    }

    @Override
    public void render() {
        System.out.println("☁  " + ciudad + ": " + temperaturaTexto());
    }

    @Override
    public String interactuar() {
        enCelsius = !enCelsius;
        return "Unidad cambiada a " + (enCelsius ? "Celsius" : "Fahrenheit");
    }

    @Override
    public String getConfiguracion() {
        return "Ciudad=" + ciudad + ", Temp=" + temperaturaTexto();
    }
}
