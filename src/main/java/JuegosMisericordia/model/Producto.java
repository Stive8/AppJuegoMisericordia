package JuegosMisericordia.model;

public class Producto {
    public static final String ESTADO_ACTIVO = "ACTIVO  ";
    public static final String ESTADO_INACTIVO = "INACTIVO";
    private String codigo;
    private String nombre;
    private double valorUnitario;
    private int unidadesDisponibles;
    private String estado;


    public Producto( String codigo,String nombre, double valorUnitario, int unidadesDisponibles, String estado) {
        this.codigo= codigo;
        this.nombre = nombre;
        this.valorUnitario = valorUnitario;
        this.unidadesDisponibles = unidadesDisponibles;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
