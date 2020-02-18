package Clientes;

import java.util.Date;

public class ClientePaticular extends Cliente {
    String apellidos;

    public ClientePaticular(String nombre, String apellidos, String NIF, Date fechaAlta) {
        super(nombre, NIF, fechaAlta);
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }
}
