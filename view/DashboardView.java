package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Renderizable;

public class DashboardView extends JFrame {
    private final JPanel panelWidgets = new JPanel(new GridLayout(0, 3, 12, 12));
    private final JTextField txtBuscar = new JTextField(14);
    private final JButton btnBuscar = new JButton("Buscar (ID / nombre)");
    private final JButton btnOrdenar = new JButton("Ordenar por memoria");
    private final JButton btnOriginal = new JButton("Orden original");
    private final JButton btnTodos = new JButton("Interactuar con todos");
    private final JLabel lblEstado = new JLabel(" ");

    public DashboardView() {
        super("Dashboard Empresarial");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Dashboard Empresarial", JLabel.CENTER);
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 22f));
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(0x1F2A44));
        titulo.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));

        JPanel barra = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        barra.add(btnOrdenar);
        barra.add(btnOriginal);
        barra.add(btnTodos);
        barra.add(new JLabel("   Buscar:"));
        barra.add(txtBuscar);
        barra.add(btnBuscar);

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(titulo, BorderLayout.NORTH);
        norte.add(barra, BorderLayout.SOUTH);

        panelWidgets.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        lblEstado.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));

        add(norte, BorderLayout.NORTH);
        add(new JScrollPane(panelWidgets), BorderLayout.CENTER);
        add(lblEstado, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(960, 640));
        pack();
        setLocationRelativeTo(null);
    }

    public void mostrarWidgets(List<Renderizable> widgets) {
        panelWidgets.removeAll();
        for (Renderizable w : widgets) {
            panelWidgets.add(w.obtenerVista());
        }
        panelWidgets.revalidate();
        panelWidgets.repaint();
    }

    public String getTextoBusqueda() { return txtBuscar.getText(); }
    public void setEstado(String mensaje) { lblEstado.setText(mensaje); }

    public void addOrdenarListener(ActionListener l) { btnOrdenar.addActionListener(l); }
    public void addOriginalListener(ActionListener l) { btnOriginal.addActionListener(l); }
    public void addInteractuarTodosListener(ActionListener l) { btnTodos.addActionListener(l); }
    public void addBuscarListener(ActionListener l) {
        btnBuscar.addActionListener(l);
        txtBuscar.addActionListener(l);  
    }
}
