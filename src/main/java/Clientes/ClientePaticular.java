package Clientes;

import Direcciones.Direccion;

import java.util.Date;

import static Helpers.HelperArgument.*;

public class ClientePaticular extends Cliente {
    String apellidos;

    public ClientePaticular(String nombre, String apellidos, String NIF, Direccion direccion, String correo, Date fechaAlta) {
        super(nombre, NIF, direccion, correo, fechaAlta);
        this.apellidos = stringNotEmpty("apellidos", referenceNotNull("apellidos", apellidos));
    }

    public String getApellidos() {
        return apellidos;
    }
}
