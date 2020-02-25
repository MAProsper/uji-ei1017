package Direcciones;

public class Direccion {

    int codigo_postal;
    String provincia;
    String poblacion;

    public Direccion(int codigo_postal, String provincia, String poblacion) {
        this.codigo_postal = codigo_postal;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPoblacion() {
        return poblacion;
    }
}
