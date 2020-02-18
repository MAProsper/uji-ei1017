package Direcciones;

public class Direccion {

    String codigo_postal;
    String provincia;
    String poblacion;

    public Direccion(String codigo_postal, String provincia, String poblacion) {
        this.codigo_postal = codigo_postal;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPoblacion() {
        return poblacion;
    }
}
