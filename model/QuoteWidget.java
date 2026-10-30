package model;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
        System.out.println("\"" + cita + "\" - " + autor);
    }

    @Override
    public JComponent obtenerVista() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(getNombre()));
        JLabel lbl = new JLabel("<html><center><i>“" + cita + "”</i><br>— " + autor + "</center></html>",
                SwingConstants.CENTER);
        panel.add(lbl, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public String getConfiguracion() {
        return "Autor=" + autor;
    }
}
