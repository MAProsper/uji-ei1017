package Llamadas;

import java.util.Date;

public class Llamada {
    String telefono;
    Date fecha;
    float duracion;

    public Llamada(String telefono, Date fecha, float duracion) {
        this.telefono = telefono;
        this.fecha = fecha;
        this.duracion = duracion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Date getFecha() {
        return fecha;
    }

    public float getDuracion() {
        return duracion;
    }
}
