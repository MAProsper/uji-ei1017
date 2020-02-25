package Clientes;

import Direcciones.Direccion;
import Tarifas.Tarifa;

import java.util.Date;

public class ClientePaticular extends Cliente {
    String apellidos;

    public ClientePaticular(String nombre, String apellidos, String NIF, Direccion direccion, String correo, Date fechaAlta, Tarifa tarifa) {
        super(nombre, NIF, direccion, correo, fechaAlta, tarifa);
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }
}
