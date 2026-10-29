public abstract class WidgetBase implements Widget {
    private static int contador = 1;

    private final int id;
    private final String nombre;
    private final int memoriaKB;

    protected WidgetBase(String nombre, int memoriaKB) {
        this.id = contador++;
        this.nombre = nombre;
        this.memoriaKB = memoriaKB;
    }

    @Override public int getId() { return id; }
    @Override public String getNombre() { return nombre; }
    @Override public int getMemoriaKB() { return memoriaKB; }

    @Override
    public String toString() {
        return String.format("[%d] %-18s (%d KB)", id, nombre, memoriaKB);
    }
}
