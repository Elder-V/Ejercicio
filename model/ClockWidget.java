package model;

import java.awt.BorderLayout;
import java.awt.Font;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class ClockWidget extends WidgetBase {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final String zona;

    public ClockWidget(String nombre, int memoriaKB, String zona) {
        super(nombre, memoriaKB);
        this.zona = zona;
    }

    private String horaActual() {
        return LocalTime.now().format(FMT);
    }

    @Override
    public void render() {
        System.out.println("Reloj " + horaActual() + " (" + zona + ")");
    }

    @Override
    public JComponent obtenerVista() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(getNombre() + " · " + zona));

        JLabel lblHora = new JLabel(horaActual(), SwingConstants.CENTER);
        lblHora.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        panel.add(lblHora, BorderLayout.CENTER);

        new Timer(1000, e -> {
            if (lblHora.getParent() == null) { ((Timer) e.getSource()).stop(); return; }
            lblHora.setText(horaActual());
        }).start();
        return panel;
    }

    @Override
    public String getConfiguracion() {
        return "Formato=HH:mm:ss, Zona=" + zona;
    }
}
