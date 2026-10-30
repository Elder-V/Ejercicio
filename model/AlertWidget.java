package model;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AlertWidget extends WidgetBase implements Interactivo {

    public enum Prioridad { BAJA, MEDIA, ALTA, CRITICA }

    private final String mensaje;
    private final Prioridad prioridad;
    private boolean leida = false;

    public AlertWidget(String nombre, int memoriaKB, String mensaje, Prioridad prioridad) {
        super(nombre, memoriaKB);
        this.mensaje = mensaje;
        this.prioridad = prioridad;
    }

    private String textoHtml() {
        return "<html><center>[" + prioridad + "]<br>" + mensaje
                + "<br><i>" + (leida ? "(leída)" : "(NUEVA)") + "</i></center></html>";
    }

    private Color color() {
        if (leida) return new Color(0x777777);
        return prioridad.ordinal() >= Prioridad.ALTA.ordinal() ? new Color(0xB02020) : new Color(0xB36B00);
    }

    @Override
    public void render() {
        System.out.println("[" + prioridad + "] " + mensaje + (leida ? " (leída)" : " (NUEVA)"));
    }

    @Override
    public String interactuar() {
        if (leida) return "La alerta ya estaba leída.";
        leida = true;
        return "Alerta marcada como leída.";
    }

    @Override
    public JComponent obtenerVista() {
        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.setBorder(BorderFactory.createTitledBorder(getNombre()));

        JLabel lblMensaje = new JLabel(textoHtml(), SwingConstants.CENTER);
        lblMensaje.setForeground(color());
        JButton btnLeida = new JButton("Marcar como leída");
        btnLeida.addActionListener(e -> {
            interactuar();
            lblMensaje.setText(textoHtml());
            lblMensaje.setForeground(color());
        });

        panel.add(lblMensaje, BorderLayout.CENTER);
        panel.add(btnLeida, BorderLayout.SOUTH);
        return panel;
    }

    @Override
    public String getConfiguracion() {
        return "Prioridad=" + prioridad + ", Leída=" + leida;
    }
}
