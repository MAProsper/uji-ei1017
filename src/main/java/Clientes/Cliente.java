package Clientes;

import Direcciones.Direccion;
import Tarifas.Tarifa;

import java.util.Date;

public class Cliente {
    String nombre;
    String NIF;
    Direccion direccion;
    String correo;
    Date fechaAlta;
    Tarifa tarifa;

    public Cliente(String nombre, String NIF, Date fechaAlta) {
        this.nombre = nombre;
        this.NIF = NIF;
        this.fechaAlta = fechaAlta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNIF() {
        return NIF;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public Date getFecha() {
        return fechaAlta;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }
}
