package model;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WeatherWidget extends WidgetBase implements Interactivo {
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
        System.out.println("Clima " + ciudad + ": " + temperaturaTexto());
    }

    @Override
    public String interactuar() {
        enCelsius = !enCelsius;
        return "Unidad cambiada a " + (enCelsius ? "Celsius" : "Fahrenheit");
    }

    @Override
    public JComponent obtenerVista() {
        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.setBorder(BorderFactory.createTitledBorder(getNombre() + " · " + ciudad));

        JLabel lblTemp = new JLabel(temperaturaTexto(), SwingConstants.CENTER);
        lblTemp.setFont(lblTemp.getFont().deriveFont(Font.BOLD, 26f));

        JButton btnUnidad = new JButton("Cambiar °C / °F");
        btnUnidad.addActionListener(e -> {
            interactuar();
            lblTemp.setText(temperaturaTexto());
        });

        panel.add(lblTemp, BorderLayout.CENTER);
        panel.add(btnUnidad, BorderLayout.SOUTH);
        return panel;
    }

    @Override
    public String getConfiguracion() {
        return "Ciudad=" + ciudad + ", Temp=" + temperaturaTexto();
    }
}
