public interface Widget extends Comparable<Widget> {
    int getId();
    String getNombre();
    int getMemoriaKB();

    void render();

    String getConfiguracion();

    @Override
    default int compareTo(Widget otro) {
        return Integer.compare(this.getMemoriaKB(), otro.getMemoriaKB());
    }
}
