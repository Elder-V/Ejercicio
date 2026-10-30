package model;

import javax.swing.JComponent;

public interface Renderizable extends Comparable<Renderizable> {
    int getId();
    String getNombre();
    int getMemoriaKB();

    void render();                   
    String getConfiguracion();       
    JComponent obtenerVista();       


    @Override
    default int compareTo(Renderizable otro) {
        return Integer.compare(this.getMemoriaKB(), otro.getMemoriaKB());
    }
}
