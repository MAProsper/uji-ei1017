package Clientes;

import Direcciones.Direccion;
import Tarifas.Tarifa;

import java.util.Date;

import static Helpers.HelperArgument.*;

abstract public class Cliente {
    String nombre;
    String NIF;
    Direccion direccion;
    String correo;
    Date fechaAlta;
    Tarifa tarifa;

    public Cliente(String nombre, String NIF, Direccion direccion, String correo, Date fechaAlta, Tarifa tarifa) {
        this.nombre = stringNotEmpty("nombre", referenceNotNull("nombre", nombre));
        this.NIF = generic(stringNotEmpty("NIF", referenceNotNull("NIF", NIF)), NIF.length() == 9, "formato de NIF invalido");
        this.direccion = direccion;
        this.correo = generic(stringNotEmpty("correo", referenceNotNull("correo", correo)), correo.contains("@"), "formato de correo invalido");
        this.fechaAlta = fechaAlta;
        this.tarifa = tarifa;
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
